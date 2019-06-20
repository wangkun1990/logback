package com.redis.controller;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import com.redis.mq.RedisMessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Created by wangkun on 2017/4/28.
 */
@RestController
public class LogController {

    private final Logger logger = LoggerFactory.getLogger(LogController.class);

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private volatile String length;

    @Resource
    private RedisMessageListener messageListener;

    @PostConstruct
    public void setLength() {
        this.length = stringRedisTemplate.opsForValue().get("length");
    }

    @RequestMapping(value = "getlength", method = RequestMethod.GET)
    public void getLength() {
        if (messageListener.getObject() != null) {
            this.length = (String) messageListener.getObject();
        }
        System.out.println(this.length);
    }


    @RequestMapping(value = "/getstudent", method = RequestMethod.GET)
    public void getStudent() {
        logger.debug("debug level");
        logger.info("info level");
        logger.warn("warn level");
        logger.error("error level");
    }

    @RequestMapping(value = "updatelevel", method = RequestMethod.GET)
    public void dynamicUpdateLevel() throws Exception {
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        //从redis中读取配置信息
        String logback = stringRedisTemplate.opsForValue().get("logback");
        context.getStatusManager().clear();
        context.reset();
        JoranConfigurator configurator = new JoranConfigurator();
        configurator.setContext(context);
        InputStream is = new ByteArrayInputStream(logback.getBytes("utf-8"));
        configurator.doConfigure(is);
        is.close();
    }

    /**
     * 动态更新log4j日志级别
     * @param xml logback.xml文件内容
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateLogLevel(@RequestBody String xml) {
        logger.info("param = {}", xml);
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        context.getStatusManager().clear();
        context.reset();
        JoranConfigurator configurator = new JoranConfigurator();
        configurator.setContext(context);
        try {
            InputStream is = new ByteArrayInputStream(xml.getBytes("utf-8"));
            configurator.doConfigure(is);
            is.close();
        } catch (Exception e) {
            logger.error("update log level error", e);
        }
        return "{\"success\":\"true\"}";
    }
}
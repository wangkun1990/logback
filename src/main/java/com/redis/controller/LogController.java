package com.redis.controller;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import com.redis.mq.RedisMessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    public void getStudent(@RequestParam("id") String id) {
        logger.info("id = " + id);
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
}
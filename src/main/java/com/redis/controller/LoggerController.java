package com.redis.controller;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import com.redis.constant.RedisKeyConstant;
import com.redis.entity.LoggerRequestVo;
import com.redis.util.XmlUtils;
import com.redis.xml.Configuration;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
public class LoggerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerController.class);

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 添加或更新日志基本
     *
     * @param loggerRequestVo
     */
    @PostMapping(value = "/addLogger")
    public void addLogger(@RequestBody LoggerRequestVo loggerRequestVo) {
        try {
            if (loggerRequestVo != null && !CollectionUtils.isEmpty(loggerRequestVo.getLoggers())) {
                String loggerConfig = stringRedisTemplate.opsForValue().get(RedisKeyConstant.LOGGER_LEVEL_KEY);
                if (StringUtils.isNotBlank(loggerConfig)) {
                    InputStream inputStream = IOUtils.toInputStream(loggerConfig, "UTF-8");
                    Configuration configuration = XmlUtils.xmlToBean(inputStream, Configuration.class);
                    List<com.redis.xml.Logger> loggers = configuration.getLoggers();
                    if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(loggers)) {
                        Map<String, com.redis.xml.Logger> map = loggers.stream().collect(Collectors.toMap(com.redis.xml.Logger::getName, logger -> logger));
                        com.redis.xml.Logger logger;
                        for (com.redis.entity.Logger logger1 : loggerRequestVo.getLoggers()) {
                            if (map.containsKey(logger1.getFullClassName())) {
                                // 更新
                                logger = map.get(logger1.getFullClassName());
                                logger.setLevel(logger1.getLevel());
                            } else {
                                // 新增
                                logger = new com.redis.xml.Logger();
                                logger.setName(logger1.getFullClassName());
                                logger.setLevel(logger1.getLevel());
                                loggers.add(logger);
                            }
                        }
                    }
                    String xmlLoggerConfig = XmlUtils.toXML(configuration);
                    LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
                    context.getStatusManager().clear();
                    context.reset();
                    JoranConfigurator configurator = new JoranConfigurator();
                    configurator.setContext(context);
                    configurator.doConfigure(IOUtils.toInputStream(xmlLoggerConfig, "UTF-8"));
                    stringRedisTemplate.opsForValue().set(RedisKeyConstant.LOGGER_LEVEL_KEY, xmlLoggerConfig);
                }
            }
        } catch (Exception e) {
            LOGGER.error("addLoggerLevel error = ", e);
        }

    }


    /**
     * 删除日志基本
     *
     * @param loggerRequestVo
     */
    @PostMapping(value = "/deleteLogger")
    public void deleteLogger(@RequestBody LoggerRequestVo loggerRequestVo) {
        try {
            if (loggerRequestVo != null && !CollectionUtils.isEmpty(loggerRequestVo.getLoggers())) {
                String loggerConfig = stringRedisTemplate.opsForValue().get(RedisKeyConstant.LOGGER_LEVEL_KEY);
                if (StringUtils.isNotBlank(loggerConfig)) {
                    InputStream inputStream = IOUtils.toInputStream(loggerConfig, "UTF-8");
                    Configuration configuration = XmlUtils.xmlToBean(inputStream, Configuration.class);
                    List<com.redis.xml.Logger> loggers = configuration.getLoggers();
                    if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(loggers)) {
                        Iterator<com.redis.xml.Logger> iterator = loggers.iterator();
                        for (com.redis.entity.Logger logger1 : loggerRequestVo.getLoggers()) {
                            while (iterator.hasNext()) {
                                com.redis.xml.Logger logger = iterator.next();
                                if (StringUtils.equals(logger1.getFullClassName(), logger.getName())) {
                                    iterator.remove();
                                }
                            }
                        }
                    }
                    String xmlLoggerConfig = XmlUtils.toXML(configuration);
                    LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
                    context.getStatusManager().clear();
                    context.reset();
                    JoranConfigurator configurator = new JoranConfigurator();
                    configurator.setContext(context);
                    configurator.doConfigure(IOUtils.toInputStream(xmlLoggerConfig, "UTF-8"));
                    stringRedisTemplate.opsForValue().set(RedisKeyConstant.LOGGER_LEVEL_KEY, xmlLoggerConfig);
                }
            }
        } catch (Exception e) {
            LOGGER.error("addLoggerLevel error = ", e);
        }
    }
}
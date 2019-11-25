package com.redis.loggerconfig;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import com.redis.constant.RedisKeyConstant;
import com.redis.util.XmlUtils;
import com.redis.xml.Configuration;
import org.apache.commons.io.IOUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Component
public class RedisLoggerConfig implements InitializingBean {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void afterPropertiesSet() throws Exception {
        String loggerConfig = stringRedisTemplate.opsForValue().get(RedisKeyConstant.LOGGER_LEVEL_KEY);
        if (StringUtils.isEmpty(loggerConfig)) {
            // 加载本地的logback.xml配置文件
            InputStream inputStream = RedisLoggerConfig.class.getClassLoader().getResourceAsStream("logback.xml");
            String loggerXml = IOUtils.toString(inputStream, "UTF-8");
            stringRedisTemplate.opsForValue().set(RedisKeyConstant.LOGGER_LEVEL_KEY, loggerXml);
        } else {
            if (org.apache.commons.lang3.StringUtils.isNotBlank(loggerConfig)) {
                InputStream inputStream = IOUtils.toInputStream(loggerConfig, "UTF-8");
                Configuration configuration = XmlUtils.xmlToBean(inputStream, Configuration.class);
                String xmlLoggerConfig = XmlUtils.toXML(configuration);
                LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
                context.getStatusManager().clear();
                context.reset();
                JoranConfigurator configurator = new JoranConfigurator();
                configurator.setContext(context);
                configurator.doConfigure(IOUtils.toInputStream(xmlLoggerConfig, "UTF-8"));
            }
        }
    }
}

package com.redis.xml;

import com.alibaba.fastjson.JSON;
import com.redis.util.XmlUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration();
        List<Appender> appenders = new ArrayList<>();
        Appender appender = new Appender();
        appender.setName("STDOUT");
        appender.setFullClassName("ch.qos.logback.core.ConsoleAppender");
        appenders.add(appender);

        Encoder encoder = new Encoder();
        encoder.setEncoderFullClassName("ch.qos.logback.classic.encoder.PatternLayoutEncoder");
        encoder.setPattern("%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %c-%L - %msg%n");
        appender.setEncoder(encoder);


        Appender fileAppender = new Appender();
        fileAppender.setName("FILE");
        fileAppender.setFullClassName("ch.qos.logback.core.rolling.RollingFileAppender");
        fileAppender.setFile("/opt/tuniu/logs/tomcat/app/${tomcat_app_base}/login.log");

        RollingPolicy rollingPolicy = new RollingPolicy();
        rollingPolicy.setRollingPolicyFullClassName("ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy");
        rollingPolicy.setFileNamePattern("/opt/tuniu/logs/tomcat/app/${tomcat_app_base}/login.%d{yyyy-MM-dd}-%i.log");
        rollingPolicy.setMaxFileSize("20MB");
        rollingPolicy.setMaxHistory("14");
        rollingPolicy.setTotalSizeCap("20GB");
        Encoder fileEncoder = new Encoder();
        fileEncoder.setPattern("%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{32} -%L - %X{traceId} - %msg%n");
        fileAppender.setEncoder(fileEncoder);
        fileAppender.setRollingPolicy(rollingPolicy);
        appenders.add(fileAppender);


        Root root = new Root();
        root.setLevel("error");

        AppenderRef appenderRef = new AppenderRef();
        appenderRef.setRef("STDOUT");
        root.setAppenderRef(appenderRef);

        configuration.setRoot(root);


        List<Logger> loggers = new ArrayList<>();
        Logger logger = new Logger();
        logger.setLevel("info");
        logger.setName("com.redis.controller.TestController");
        loggers.add(logger);
        logger = new Logger();
        logger.setLevel("warn");
        logger.setName("com.redis.controller.LoggerController");
        loggers.add(logger);

        configuration.setLoggers(loggers);

        configuration.setAppenders(appenders);



        System.err.println(XmlUtils.toXML(configuration));


        /*InputStream inputStream = new FileInputStream(new File("D:\\gitproject\\logback\\src\\main\\resources\\logback.xml"));
        Configuration configuration1 = XmlUtils.xmlToBean(inputStream, Configuration.class);
        System.out.println(JSON.toJSONString(configuration1));


        System.out.println(XmlUtils.toXML(configuration1));*/


    }
}

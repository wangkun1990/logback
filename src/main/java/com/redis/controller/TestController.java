package com.redis.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @GetMapping(value = "/test")
    public void test() {
        LOGGER.debug("is debug = {}", LOGGER.isDebugEnabled());
        LOGGER.info("is info = {}", LOGGER.isInfoEnabled());
        LOGGER.warn("is warn = {}", LOGGER.isWarnEnabled());
        LOGGER.error("is error = {}", LOGGER.isErrorEnabled());
    }
}

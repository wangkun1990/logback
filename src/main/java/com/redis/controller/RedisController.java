package com.redis.controller;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class RedisController {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping(value = "/updatelength/{length}", method = RequestMethod.GET)
    public void updateLenth(@PathVariable("length") String length) {
        stringRedisTemplate.convertAndSend("ship", length);
        stringRedisTemplate.opsForValue().set("length", length);
    }
}

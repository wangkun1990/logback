package com.redis.controller;


import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.Resource;
import java.net.URLDecoder;

/**
 * Created by wangkun on 2017/4/29.
 */
@Controller
public class ConfigController {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping(value = "/logconfig", method = RequestMethod.GET)
    public String configPage() {
        return "config";
    }

    @RequestMapping(value = "/getconfig", method = RequestMethod.GET)
    @ResponseBody
    public String getConfig() {
        return stringRedisTemplate.opsForValue().get("logback");
    }

    @RequestMapping(value = "/updateconfig", method = RequestMethod.POST)
    public void update(@RequestParam("data") String config) {
        System.out.println(HtmlUtils.htmlUnescape(config));
    }


}

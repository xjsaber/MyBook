package com.xjsaber.learn.spring.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xjsaber
 */
@Controller
@RequestMapping("/redis")
public class RedisController {

    private RedisTemplate redisTemplate;

    private StringRedisTemplate stringRedisTemplate;

    public RedisController(RedisTemplate redisTemplate, StringRedisTemplate stringRedisTemplate) {
        this.redisTemplate = redisTemplate;
        this.stringRedisTemplate = stringRedisTemplate;
    }


}

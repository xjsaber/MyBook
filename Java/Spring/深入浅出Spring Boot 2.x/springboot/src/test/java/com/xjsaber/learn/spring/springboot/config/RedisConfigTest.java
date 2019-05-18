package com.xjsaber.learn.spring.springboot.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisConfigTest {

    @Autowired
    private RedisConfig redisConfig;

    @Test
    public void redisTemplateTest(){
        RedisTemplate<Object, Object> redisTemplate = redisConfig.initRedisTemplate();
    }
}

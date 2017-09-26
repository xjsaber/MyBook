package com.xjsaber.redis.jredis.ch1;

import com.xjsaber.redis.jredis.redis.RedisClient;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;

public class RedisUtils {

    ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
    BeanFactory factory = (BeanFactory) context;
    RedisClient redisClient = (RedisClient)factory.getBean("redisClient");

    private void redisString(){
        Jedis jedis = redisClient.getJedis();
        jedis.connect();
        jedis.select(0);
        jedis.set("hello", "world");
        System.out.println(jedis.get("hello"));
        jedis.del("hello");
        jedis.get("hello");
    }

    private void redisZSet(){
        Jedis jedis = redisClient.getJedis();
    }
}

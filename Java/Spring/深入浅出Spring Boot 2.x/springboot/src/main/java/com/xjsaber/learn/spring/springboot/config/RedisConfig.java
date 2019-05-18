package com.xjsaber.learn.spring.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author xjsaber
 */
@Configuration
public class RedisConfig {

    private RedisConnectionFactory connectionFactory = null;

    @Bean(name = "RedisConnectionFactory")
    public RedisConnectionFactory initRedisConnectionFactory(){
        if (this.connectionFactory != null){
            return this.connectionFactory;
        }
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // 最大空闲数
        poolConfig.setMaxIdle(30);
        // 最大连接数
        poolConfig.setMaxTotal(50);
        // 最大等待毫秒数
        poolConfig.setMaxWaitMillis(2000);
        // 创建Jedis连接工厂
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(poolConfig);
        // 单机
        RedisStandaloneConfiguration rsCfg = connectionFactory.getStandaloneConfiguration();
        connectionFactory.setHostName("localhost");
        connectionFactory.setPort(6379);
        connectionFactory.setPassword("123456");
        this.connectionFactory = connectionFactory;
        return connectionFactory;
    }

    @Bean(name = "redisTemplate")
    public RedisTemplate<Object, Object> initRedisTemplate(){
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(initRedisConnectionFactory());
        return redisTemplate;
    }
}

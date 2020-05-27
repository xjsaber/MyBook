package com.xjsaber.redis.jredis.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by xjsaber on 2017/6/6.
 *
 */
@Component
public class RedisClient {

    @Autowired
    private JedisPool jedisPool;

    /**
     * 获得jedis
     * @return Jedis
     */
    public Jedis getJedis(){
        return jedisPool.getResource();
    }

    public void set(String key, String value) throws Exception {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
        } finally {
            //返还到连接池
            jedis.close();
        }
    }

    public String get(String key) throws Exception  {

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } finally {
            //返还到连接池
            jedis.close();
        }
    }
}

package com.xjsaber.redis.jredis.ch7;

import com.xjsaber.redis.jredis.redis.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created by xjsaber on 2017/6/12.
 * 第7章 基于搜索的应用程序
 */
@Component(value = "myRegix")
public class MyRegix {

    private final RedisClient redisClient;
    private final Jedis jedis;

    @Autowired
    public MyRegix(RedisClient redisClient) {
        this.redisClient = redisClient;
        this.jedis = this.redisClient.getJedis();
        this.jedis.select(6); //index 6
    }

    // 对文档进行标记化处理并创建索引的函数
    String STOP_WORDS = "able about across after all almost also am among an and any are as at be because been but by can cannot could dear did do dose either else ever every for from get got had has have he her hers him";

    Pattern WORDS_RE = Pattern.compile("[a-z]']{2,}");

    /**
     * 标记化
     * @param content
     */
    public Set<String> tokenize(String content){
       Set<String> words = new HashSet<>();
       for (String word : WORDS_RE.matcher(content.toLowerCase()).group().split("'")){
            if (word.length() >= 2){
                words.add(word);
            }
       }
       return words;
    }

    /**
     * 可续化文档
     * @param docId 文档编号
     * @param content 内容
     */
    public int indexDocument(String docId, String content){
        Set<String> words = tokenize(content);

        Pipeline pipeline = jedis.pipelined();
        for (String word : words){
            pipeline.sadd("idx:" + word, docId);
        }
        return pipeline.exec().get().size();
    }
}

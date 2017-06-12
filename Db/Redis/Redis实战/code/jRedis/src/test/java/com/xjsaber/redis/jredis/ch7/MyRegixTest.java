package com.xjsaber.redis.jredis.ch7;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by xjsaber on 2017/6/12.
 * ch7 基本索引操作
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MyRegixTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyRegixTest.class);

    @Resource(name = "myRegix")
    private MyRegix myRegix;

    @Test
    public void tokenize() throws Exception {
        String content = "";
        for (String temp : myRegix.tokenize(content)){
            LOGGER.info(temp);
        }
        Assert.assertTrue(true);
    }

    @Test
    public void indexDocument() throws Exception {
    }

}
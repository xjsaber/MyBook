package com.xjsaber.learn.spring.springboot.service;

import com.alibaba.fastjson.JSON;
import com.xjsaber.learn.spring.springboot.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    JdbcTmpUserService jdbcTmpUserService;

    @Test
    public void getUserTest(){
        User user = jdbcTmpUserService.getUser(0L);
        System.out.println(JSON.toJSON(user));
    }
}

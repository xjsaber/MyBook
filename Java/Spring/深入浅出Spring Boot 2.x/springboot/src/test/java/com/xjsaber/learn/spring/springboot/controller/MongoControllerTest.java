package com.xjsaber.learn.spring.springboot.controller;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.alibaba.fastjson.JSON;
import com.xjsaber.learn.spring.springboot.pojo.MongoUser;
import com.xjsaber.learn.spring.springboot.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Assert;

import javax.servlet.AsyncContext;
import java.io.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoControllerTest {

    @InjectMocks
    private MongoController mongoController;

    @Autowired
    private UserService userService;

    private MockMvc mockMvc;

    @Test
    public void testSaveUser() throws Exception {
        MongoUser mongoUser = new MongoUser();
        mongoUser.setId(1L);
        mongoUser.setUserName("user_name_1");
        mongoUser.setNote("user_note_1");

        userService.saveUser(mongoUser);
        Assert.isTrue(true, "true");
    }
}

package com.xjsaber.learn.spring.springboot.controller;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.alibaba.fastjson.JSON;
import com.xjsaber.learn.spring.springboot.pojo.MongoUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoControllerTest {

    @InjectMocks
    private MongoController mongoController;

    private MockMvc mockMvc;

    @Test
    private void testSaveUser() throws Exception {
        String url = "";

        MockHttpServletRequest request = new MockHttpServletRequest("POST", "/mongo/save");
        MongoUser mongoUser = new MongoUser();
        mongoUser.setId(1L);
        mongoUser.setUserName("user_name_1");
        mongoUser.setNote("user_note_1");
        OutputStream out = new ByteArrayOutputStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream ;
        

        when
    }
}

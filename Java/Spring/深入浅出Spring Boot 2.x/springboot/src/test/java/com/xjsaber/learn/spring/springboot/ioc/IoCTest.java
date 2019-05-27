package com.xjsaber.learn.spring.springboot.ioc;

import com.xjsaber.learn.spring.springboot.config.AppConfig;
import com.xjsaber.learn.spring.springboot.config.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class IoCTest {

    public static void main(String []args){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        User user = ctx.getBean(User.class);
        log.info(String.valueOf(user.getNote()));
    }
}

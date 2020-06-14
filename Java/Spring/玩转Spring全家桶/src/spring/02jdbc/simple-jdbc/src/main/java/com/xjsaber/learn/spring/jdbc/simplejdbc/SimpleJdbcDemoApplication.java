package com.xjsaber.learn.spring.jdbc.simplejdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

@SpringBootApplication
@Slf4j
public class SimpleJdbcDemoApplication {

    @Autowired
    private FooDao fooDao;

    public static void main(String[] args){
        SpringApplication.run(SimpleJdbcDemoApplication.class, args);
    }

//    public SimpleJdbcInsert simpleJdbcInsert(JdbcTemplate jdbcTemplate){
//
//    }
}

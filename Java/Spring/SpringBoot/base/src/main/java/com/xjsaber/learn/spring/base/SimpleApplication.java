package com.xjsaber.learn.spring.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xjsaber
 */
@EnableAutoConfiguration
public class SimpleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExampleController.class, args);
    }
}

package com.xjsaber.learn.spring.springboot.service.impl;

import com.xjsaber.learn.spring.springboot.service.HelloService;
import org.springframework.stereotype.Service;

/**
 *
 * @author xjsaber
 */
@Service("helloService")
public class HelloServiceImpl implements HelloService {
    @Override
    public void sayHello(String name) {
        if (name == null || "".equals(name.trim())) {
            throw new RuntimeException("parameter is null!!");
        }
        System.out.println("hello " + name);
    }
}

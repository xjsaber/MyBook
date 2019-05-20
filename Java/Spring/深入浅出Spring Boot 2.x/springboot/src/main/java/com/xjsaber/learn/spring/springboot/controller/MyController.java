package com.xjsaber.learn.spring.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author xjsaber
 */
public class MyController {

    @GetMapping("/valid/page")
    public String validatePage(){
        return "/validator/pojo";
    }
}

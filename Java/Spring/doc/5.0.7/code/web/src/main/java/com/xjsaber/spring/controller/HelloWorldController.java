package com.xjsaber.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author xjsaber
 */
@Controller
public class HelloWorldController {

    @RequestMapping(value = "/hello" , method = RequestMethod.GET)
    public String hello(){
        System.out.println("helloworld");
        return "hello";
    }

    @GetMapping("/index")
    public String handler(Model model){
        model.addAttribute("message", "Hello World!");
        return "index";
    }

}

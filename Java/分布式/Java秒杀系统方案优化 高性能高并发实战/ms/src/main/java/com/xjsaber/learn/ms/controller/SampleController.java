package com.xjsaber.learn.ms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xjsaber
 */
@Controller
@RequestMapping("/demo")
public class SampleController {

    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("name", "xjsaber");
        return "hello";
    }

    @GetMapping("/db/tx")
    public String dbTx(){
        return "";
    }

    @GetMapping("/db/get")
    public String dbGet(){
        return "";
    }

    @GetMapping("/redis/get")
    public String redisGet(){
        return "";
    }
}

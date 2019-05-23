package com.xjsaber.learn.spring.springboot.controller;

import com.xjsaber.learn.spring.springboot.pojo.User;
import com.xjsaber.learn.spring.springboot.service.MyBatisUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xjsaber
 */
@Controller
@RequestMapping("/mybatis")
public class MyBatisController {

    private MyBatisUserService myBatisUserService = null;

    @RequestMapping("/getUser")
    @ResponseBody
    public User getUser(Long id){
        return myBatisUserService.getUser(id);
    }
}

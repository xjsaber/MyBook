package com.xjsaber.learn.spring.springboot.controller;

import com.xjsaber.learn.spring.springboot.mybatis.MybatisUser;
import com.xjsaber.learn.spring.springboot.service.MyBatisUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xjsaber
 */
@Controller
@RequestMapping("/mybatis")
public class MyBatisController {

    private MyBatisUserService myBatisUserService = null;

    public MyBatisController(MyBatisUserService myBatisUserService) {
        this.myBatisUserService = myBatisUserService;
    }

    @RequestMapping("/getUser")
    @ResponseBody
    public MybatisUser getUser(Long id){
        return myBatisUserService.getUser(id);
    }
}

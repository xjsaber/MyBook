package com.xjsaber.learn.spring.springboot.controller;

import com.mongodb.client.result.DeleteResult;
import com.xjsaber.learn.spring.springboot.pojo.MongoUser;
import com.xjsaber.learn.spring.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xjsaber
 */
@Controller
@RequestMapping("mongo")
public class MongoController {

    private UserService userService = null;

    public MongoController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/save")
    @ResponseBody
    public MongoUser saveUser(@RequestBody MongoUser user){
        userService.saveUser(user);
        return user;
    }
}

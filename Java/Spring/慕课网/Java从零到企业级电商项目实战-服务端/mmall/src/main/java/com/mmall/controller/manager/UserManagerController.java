package com.mmall.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/manager/user")
public class UserManagerController {

    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    public Object login(){
        return "";
    }

    @RequestMapping(value = "list.do", method = RequestMethod.GET)
    public Object list(){
        return "";
    }
}

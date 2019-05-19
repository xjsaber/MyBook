package com.xjsaber.learn.spring.springboot.controller;

import com.xjsaber.learn.spring.springboot.pojo.User;
import com.xjsaber.learn.spring.springboot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author xjsaber
 */
@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/table")
    public ModelAndView table(){
        List<User> userList = userService.findUsers(null, null);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user/table");
        mv.addObject("userList", userList);
        return mv;
    }

    @ResponseBody
    @RequestMapping("/list")
    public List<User> list(
            @RequestParam(value = "userName", required = false) String userName,
            @RequestParam(value = "note", required = false) String note) {
        return userService.findUsers(userName, note);
    }
}

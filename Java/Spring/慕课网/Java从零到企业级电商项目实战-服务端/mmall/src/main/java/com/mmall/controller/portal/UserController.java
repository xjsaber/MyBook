package com.mmall.controller.portal;

import com.mmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/")
public class UserController {

    private final IUserService iUserService;

    @Autowired
    public UserController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @param session Session
     * @return User
     */
    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Object login(String username, String password, HttpSession session){
//        service -> mybatis -> dao
        return "login success";
    }

    @ResponseBody
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public Object register(){
        return "register success";
    }

    @ResponseBody
    @RequestMapping(value = "check_valid", method = RequestMethod.GET)
    public Object checkValid(){
        return "checkValid success";
    }

    @ResponseBody
    @RequestMapping(value = "get_user_info", method = RequestMethod.GET)
    public Object getUserInfo(){
        return "get_user_info success";
    }


}

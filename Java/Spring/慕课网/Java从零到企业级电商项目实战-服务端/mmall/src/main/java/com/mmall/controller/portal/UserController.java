package com.mmall.controller.portal;

import com.mmall.common.Const;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author xjsaber
 */
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
    public ServerResponse<User> login(String username, String password, HttpSession session){
//        service -> mybatis -> dao
        return iUserService.login(username, password);
    }

    @ResponseBody
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ServerResponse<String> register(User user){
        iUserService.register(user);
        return ServerResponse.createBySuccess();
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

    @ResponseBody
    @RequestMapping(value = "logout.do", method = RequestMethod.GET)
    public ServerResponse<String> loginOut(HttpSession session){
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }



}

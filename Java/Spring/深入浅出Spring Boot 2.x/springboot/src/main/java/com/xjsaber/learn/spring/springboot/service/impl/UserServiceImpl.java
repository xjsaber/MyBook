package com.xjsaber.learn.spring.springboot.service.impl;

import com.xjsaber.learn.spring.springboot.pojo.User;
import com.xjsaber.learn.spring.springboot.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xjsaber
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Override
    public void printUser(User user) {
        if (user == null){
            throw new RuntimeException("检查用户参数是否为空......");
        }
        System.out.println("id = " + user.getId());
        System.out.println("\tusername = " + user.getUsername());
        System.out.println("\tpassword = " + user.getPassword());
    }

    @Override
    public List<User> findUsers(Object a, Object b) {
        return null;
    }
}

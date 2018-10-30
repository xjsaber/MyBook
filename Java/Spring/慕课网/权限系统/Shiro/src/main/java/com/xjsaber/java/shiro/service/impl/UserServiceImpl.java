package com.xjsaber.java.shiro.service.impl;

import com.xjsaber.java.shiro.dao.UserInfoDao;
import com.xjsaber.java.shiro.mapper.UserMapper;
import com.xjsaber.java.shiro.model.User;
import com.xjsaber.java.shiro.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
        System.out.println("UserInfoServiceImpl.findByUsername()");
        return userMapper.findByUsername(username);
    }
}

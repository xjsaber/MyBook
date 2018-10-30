package com.xjsaber.java.shiro.service;

import com.xjsaber.java.shiro.model.User;

public interface UserService {

    /**通过username查找用户信息;*/
    User findByUsername(String username);
}

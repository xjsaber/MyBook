package com.xjsaber.learn.spring.springboot.pojo.impl;

import com.xjsaber.learn.spring.springboot.pojo.User;
import com.xjsaber.learn.spring.springboot.pojo.UserValidator;

/**
 * @author xjsaber
 */
public class UserValidatorImpl implements UserValidator {

    @Override
    public boolean validator(User user) {
        System.out.println("引入新的接口：" + UserValidator.class.getSimpleName());
        return user != null;
    }
}

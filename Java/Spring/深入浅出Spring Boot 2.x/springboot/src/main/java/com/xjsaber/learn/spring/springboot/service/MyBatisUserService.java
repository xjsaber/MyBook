package com.xjsaber.learn.spring.springboot.service;

import com.xjsaber.learn.spring.springboot.pojo.User;

/**
 * @author xjsaber
 */
public interface MyBatisUserService {

    /**
     *
     */
    User getUser(Long id);
}

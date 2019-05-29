package com.xjsaber.learn.spring.springboot.service;

import com.xjsaber.learn.spring.springboot.mybatis.MybatisUser;

/**
 * @author xjsaber
 */
public interface MyBatisUserService {

    /**
     * 获得用户信息
     */
    MybatisUser getUser(Long id);
}

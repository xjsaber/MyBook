package com.xjsaber.learn.spring.springboot.service;

import com.xjsaber.learn.spring.springboot.pojo.User;

import java.util.List;

/**
 * @author xjsaber
 */
public interface JdbcTmpUserService {

    User getUser(Long id);

    List<User> findUsers(String userName, String note);

    int insertUser(User user);

    int updateUser(User user);

    int deleteUser(long id);
}

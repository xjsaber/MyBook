package com.xjsaber.learn.spring.springboot.service;

import com.xjsaber.learn.spring.springboot.pojo.JpaUser;
import com.xjsaber.learn.spring.springboot.pojo.User;

import java.util.List;

/**
 * @author xjsaber
 */
public interface JdbcTmpUserService {

    /**
     * 获得用户信息
     * @param id 编号
     * @return 用户
     */
    User getUser(Long id);

    /**
     * 用户列表
     * @param userName 用户姓名
     * @param note 文摘
     * @return 用户列表
     */
    List<User> findUsers(String userName, String note);

    /**
     * 新增用户信息
     * @param user 用户信息
     * @return
     */
    int insertUser(User user);

    /**
     * 修改用户
     * @param user 用户信息
     * @return 修改
     */
    int updateUser(User user);

    /**
     * 删除用户信息
     * @param id 编号
     * @return 删除结果
     */
    int deleteUser(long id);
}

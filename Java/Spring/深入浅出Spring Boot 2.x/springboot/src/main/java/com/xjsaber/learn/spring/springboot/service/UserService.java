package com.xjsaber.learn.spring.springboot.service;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.xjsaber.learn.spring.springboot.pojo.MongoUser;
import com.xjsaber.learn.spring.springboot.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author xjsaber
 */
public interface UserService {

    /**
     *
     * @param user
     */
    void printUser(User user);

    List<User> findUsers(Object a, Object b);

    /********************************************************************************
     * MongoDB服务
     ********************************************************************************/

    List<MongoUser> findUser(String userName, String note, int skip, int limit);

    /**
     * 保存用户信息
     * @param user 用户信息
     */
    void saveUser(MongoUser user);

    /**
     * 获得Mongo User信息
     * @param id 用户编号
     * @return MongoUser
     */
    MongoUser getUser(Long id);

    /**
     * 修改用户信息
     * @param id 用户编号
     * @param userName 用户名
     * @param note 用户信息
     * @return MongoDB的数据模型 UpdateResult
     */
    UpdateResult updateUser(Long id, String userName, String note);

    /**
     * 删除用户信息
     * @param id 编号
     * @return MongoDB的数据模型 DeleteResult
     */
    DeleteResult deleteUser(Long id);
}

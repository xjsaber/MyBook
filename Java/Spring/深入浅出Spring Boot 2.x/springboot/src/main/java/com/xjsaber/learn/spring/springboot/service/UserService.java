package com.xjsaber.learn.spring.springboot.service;

import com.xjsaber.learn.spring.springboot.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author xjsaber
 */
@Service
public interface UserService {

    /**
     *
     * @param user
     */
    void printUser(User user);

    List<User> findUsers(Object a, Object b);
}

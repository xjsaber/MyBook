package com.xjsaber.learn.spring.springboot.service;

import com.xjsaber.learn.spring.springboot.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<User> findUsers(Object a, Object b);
}

package com.xjsaber.learn.spring.springboot.service.impl;

import com.xjsaber.learn.spring.springboot.mybatis.MyBatisUserRepository;
import com.xjsaber.learn.spring.springboot.mybatis.MybatisUser;
import com.xjsaber.learn.spring.springboot.service.MyBatisUserService;
import org.springframework.stereotype.Service;

/**
 * @author xjsaber
 */
@Service
public class MyBatisUserServiceImpl implements MyBatisUserService {

    private MyBatisUserRepository myBatisUserRepository;

    public MyBatisUserServiceImpl(MyBatisUserRepository myBatisUserRepository) {
        this.myBatisUserRepository = myBatisUserRepository;
    }

    @Override
    public MybatisUser getUser(Long id) {
        return myBatisUserRepository.getUser(id);
    }
}

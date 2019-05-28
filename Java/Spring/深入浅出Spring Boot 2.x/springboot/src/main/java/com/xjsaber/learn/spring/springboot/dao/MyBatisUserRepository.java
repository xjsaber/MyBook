package com.xjsaber.learn.spring.springboot.dao;

import com.xjsaber.learn.spring.springboot.pojo.MybatisUser;
import org.springframework.stereotype.Repository;

/**
 * @author xjsaber
 */
@Repository
public interface MyBatisUserRepository {

    MybatisUser getUser(Long id);
}

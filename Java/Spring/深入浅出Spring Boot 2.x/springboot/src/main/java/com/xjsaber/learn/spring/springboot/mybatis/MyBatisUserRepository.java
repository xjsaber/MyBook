package com.xjsaber.learn.spring.springboot.mybatis;

import org.springframework.stereotype.Repository;

/**
 * @author xjsaber
 */
@Repository
public interface MyBatisUserRepository {

    /**
     * 获得用户信息
     * @param id 编号
     * @return
     */
    MybatisUser getUser(Long id);
}

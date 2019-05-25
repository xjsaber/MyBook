package com.xjsaber.learn.spring.springboot.dao;

import com.xjsaber.learn.spring.springboot.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xjsaber
 */
public interface JpaUserRepository
    extends JpaRepository<User, Long> {
}

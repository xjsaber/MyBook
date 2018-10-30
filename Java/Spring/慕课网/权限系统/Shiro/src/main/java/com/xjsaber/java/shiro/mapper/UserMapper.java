package com.xjsaber.java.shiro.mapper;

import com.xjsaber.java.shiro.model.User;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    User findByUsername(@Param("username") String username);
}

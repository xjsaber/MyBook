package com.xjsaber.learn.spring.springboot.pojo;

import com.xjsaber.learn.spring.springboot.enumeration.SexEnum;
import org.apache.ibatis.type.Alias;

/**
 * @author xjsaber
 */
@Alias(value="user")
public class UserMybatis {

    private Long id = null;

    private String username;

    private String password;

    /**
     * 性别枚举，这里需要使用typeHandler进行转换
     */
    private SexEnum sex = null;

    private String note = null;
}

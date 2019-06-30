package com.xjsaber.learn.spring.springboot.mybatis;

import com.xjsaber.learn.spring.springboot.enumeration.SexEnum;
import org.apache.ibatis.type.Alias;

/**
 * @author xjsaber
 */
@Alias(value="mybatisUser")
public class MybatisUser {

    private Long id = null;

    private String username;

    private String password;

    /**
     * 性别枚举，这里需要使用typeHandler进行转换
     */
    private SexEnum sex = null;

    private String note = null;
}

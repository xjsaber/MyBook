package com.xjsaber.learn.spring.springboot.pojo;

import com.xjsaber.learn.spring.springboot.enumeration.SexEnum;
import lombok.Data;

/**
 * @author xjsaber
 */
@Data
public class User {

    private long id;

    private String username;

    private String password;

    /**
     * 枚举
     */
    private SexEnum sex = null;

    private String note = null;
}

package com.xjsaber.learn.spring.springboot.pojo;

import lombok.Data;

/**
 * @author xjsaber
 */
@Data
public class User {

    private long id;

    private String username;

    private String password;

    private String note;
}

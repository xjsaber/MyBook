package com.xjsaber.learn.spring.springboot.pojo;

import com.xjsaber.learn.spring.springboot.enumeration.SexEnum;

import javax.persistence.*;

/**
 * @author xjsaber
 */
@Entity(name = "user")
@Table(name = "t_user")
public class UserJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_name")
    private String username;

    private String password;

    /**
     * 枚举
     */
//    @Convert(converter = SexConverter)
    private SexEnum sex = null;

    private String note = null;
}

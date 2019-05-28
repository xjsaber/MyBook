package com.xjsaber.learn.spring.springboot.pojo;

import com.xjsaber.learn.spring.springboot.enumeration.SexEnum;

import javax.persistence.*;

/**
 * @author xjsaber
 */
@Entity(name = "userJpa")
@Table(name = "t_user")
public class UserJpa {

    /**
     * #@Id 标明主键
     * #GeneratedValue 主键策略，递增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * #@Column 定义属性和表的映射关系
     */
    @Column(name = "user_name")
    private String username;

    private String password;

    /**
     * 枚举
     * #Convert 定义转换器
     */
//    @Convert(converter = SexConverter.c)
    private SexEnum sex = null;

    private String note = null;
}

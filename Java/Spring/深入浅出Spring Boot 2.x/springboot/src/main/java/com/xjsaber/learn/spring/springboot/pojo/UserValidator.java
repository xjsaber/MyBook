package com.xjsaber.learn.spring.springboot.pojo;

/**
 * @author xjsaber
 */
public interface UserValidator {

    /**
     * 检测用户对象是否为空
     * @param user
     * @return
     */
    boolean validator(User user);
}

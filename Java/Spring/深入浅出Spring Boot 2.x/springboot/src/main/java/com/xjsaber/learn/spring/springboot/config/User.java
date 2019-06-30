package com.xjsaber.learn.spring.springboot.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author xjsaber
 */
@Data
@Component("user")
public class User {

    @Value("1")
    private Long id;
    @Value("user_name_1")
    private String username;
    @Value("note_1")
    private String note;
}

package com.xjsaber.learn.spring.springboot.pojo;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author xjsaber
 */
//@Component
public class DataBaseProperties {

    @Value("${database.driverName}")
    private String driverName = null;

    @Value("${database.url}")
    private String url = null;

    private String username = null;
    private String password = null;

    public void setDriverName(String driverName){
        System.out.println(driverName);
        this.driverName = driverName;
    }

    public void setUrl(String url){
        System.out.println(url);
        this.url = url;
    }

    @Value("${database.username}")
    public void setUsername(String username){
        System.out.println(username);
        this.username = username;
    }

    @Value("${database.password}")
    public void setPassword(String password){
        System.out.println(password);
        this.password = password;
    }
}

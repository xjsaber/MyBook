package com.xjsaber.java.shiro.model;

import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author xjsaber
 */
public @Data class User implements Serializable {

    private int uid;

    private String username;

    private String password;

    private Set<Role> roles = new HashSet<>();
}

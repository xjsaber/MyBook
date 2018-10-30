package com.xjsaber.java.shiro.model;

import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public @Data class Role implements Serializable {

    private Integer rid;

    private String name;

    private Set<Permission> permissions = new HashSet<>();

    private Set<User> users = new HashSet<>();
}

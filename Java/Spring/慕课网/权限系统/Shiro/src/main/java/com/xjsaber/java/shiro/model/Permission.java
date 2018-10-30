package com.xjsaber.java.shiro.model;

import lombok.Data;
import java.io.Serializable;

public @Data class Permission implements Serializable {

    private int pid;

    private String name;

    private String url;
}

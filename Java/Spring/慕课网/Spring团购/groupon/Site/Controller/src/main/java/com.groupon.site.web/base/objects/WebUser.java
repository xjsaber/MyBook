package com.groupon.site.web.base.objects;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class WebUser implements Serializable {

    /**
     * 用户编号
     */
    @Getter
    @Setter
    private Long userId;

    /**
     * 用户名
     */
    @Getter
    @Setter
    private String username;

    /**
     * 登录状态
     */
    @Getter
    @Setter
    private int loginStatus;


}

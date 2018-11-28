package com.mmall.common;

import com.google.common.collect.Sets;

import java.util.Set;

public class Const {

    public static final String CURRENT_USER = "currentUser";

    public static final String EMAIL = "email";
    public static final String USERNAME = "username";

    public interface ProductListOrderBy{
        Set<String> PRICE_ASC_DESC = Sets.newHashSet("price_desc", "price_asc");
    }

    public interface Cart{
        int CHECKED = 1;
        int UN_CHECKED = 0;

        String LIMIT_NUM_FAIL = "LIMIT_NUM_FAIL";
        String LIMIT_NUM_SUCCESS = "LIMIT_NUM_SUCCESS";
    }

    public static final String NULL = "null";

    /**
     * 不像枚举这么重，可以使用接口类中的接口进行分类
     */
    public interface Role{
        /*
         * 普通用户
         */
        int ROLE_CUSTOMER = 0;
        /*
         * 管理员
         */
        int ROLE_ADMIN = 10;
    }
}

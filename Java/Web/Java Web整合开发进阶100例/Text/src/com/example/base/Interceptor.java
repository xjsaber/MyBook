package com.example.base;

/**
 * Created by xjsaber on 3/12/2016.
 */
public class Interceptor {
    public void beforeDoing(){  //拦截执行功能类之前执行
        System.out.println("before doing sth...");
    }
    public void afterDoing(){  //拦截执行功能类之后执行
        System.out.println("after doing sth...");
    }
}

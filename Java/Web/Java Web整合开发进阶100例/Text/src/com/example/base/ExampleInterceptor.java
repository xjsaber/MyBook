package com.example.base;

/**
 * Created by xjsaber on 3/12/2016.
 */
public class ExampleInterceptor extends Interceptor {
    //设置新参数
    private String newParam;
    public String getNewParam(){
        return newParam;
    }
    public void setNewParam(String newParam){
        this.newParam = newParam;
    }

}

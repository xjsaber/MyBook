package com.example.base;

import com.example.impl.ExcuteFunction;
import com.example.inerface.ExcuteFunctionInterface;

/**
 * Created by xjsaber on 3/12/2016.
 * 测速执行类和拦截器类是否执行
 */
public class TestInterceptor {
    public static void main(String[] args){
        ExcuteFunctionInterface test = new ExcuteFunction();

        //得到执行类的一个代理对象实例
        ExcuteFunctionInterface resultObject = (ExcuteFunctionInterface)
                new ProxyObject().getProxy(test);
        resultObject.execute(); //代理对象执行
    }
}

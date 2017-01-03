package com.example.base;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by xjsaber on 3/12/2016.
 * 拦截器处理类
 */
public class InterceptorHandler implements InvocationHandler {
    private Object object;

    private Interceptor interceptor = new Interceptor();
    public void setObject(Object object){ //设置需要拦截的执行功能类
        this.object = object;
    }

    @Override
    /* 接口invoke方法，proxy是代理实例，method是实例方法，args是代理类传入的方法参数*/
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        interceptor.beforeDoing();
        //Object提供该方法的类实例，args是调用方法所需的参数值的数组
        Object returnObject = method.invoke(object, args);
        interceptor.afterDoing();
        return returnObject;
    }
}

package com.xjsaber.learn.spring.springboot.invoke;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 调用
 * @author xjsaber
 */
public class Invocation {

    private Object[] params;
    private Method method;
    private Object target;

    public Invocation(Object target, Method method, Object[] params){
        this.target = target;
        this.method = method;
        this.params = params;
    }

    /**
     * 反射方法
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public Object process() throws IllegalAccessException, InvocationTargetException {
        return method.invoke(target, params);
    }
}

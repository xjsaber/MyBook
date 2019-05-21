package com.xjsaber.learn.spring.springboot.invoke;

import java.lang.reflect.Method;

/**
 * @author xjsaber
 */
public interface InvocationHandler {

    /**
     * 处理代理对象方法逻辑
     * @param proxy 目标对象
     * @param method 方法
     * @param args 参数
     * @return 方法调用结果
     */
    Object invoke(Object proxy, Method method, Object[] args);
}

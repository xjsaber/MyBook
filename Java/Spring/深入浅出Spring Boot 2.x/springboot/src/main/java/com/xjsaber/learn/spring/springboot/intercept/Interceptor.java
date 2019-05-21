package com.xjsaber.learn.spring.springboot.intercept;
import com.xjsaber.learn.spring.springboot.invoke.Invocation;

import java.lang.reflect.InvocationTargetException;

/**
 * 拦截器
 * @author xjsaber
 */
public interface Interceptor {

    /**
     * 事前方法
     * @return boolean true和false，如果true则是执行，false则是不执行
     */
    boolean before();

    /**
     * 事后方法
     */
    void after();

    /**
     * 事后事件，取代原有的方法
     * @param invocation -- 回调参数，可以通过它的process方法，回调原有事件
     * @return 原有事件返回对象
     * @throws InvocationTargetException 异常处理
     * @throws IllegalAccessException 异常处理
     */
    Object around(Invocation invocation) throws Throwable;

    /**
     * 事后异常方法，当事件发生异常后执行
     */
    void afterReturning();

    /**
     * 事后异常方法，当事件发生异常后执行
     */
    void afterThrowing();

    /**
     * 是否使用around方法取代原有方法
     * @return true和false
     */
    boolean useAround();
}

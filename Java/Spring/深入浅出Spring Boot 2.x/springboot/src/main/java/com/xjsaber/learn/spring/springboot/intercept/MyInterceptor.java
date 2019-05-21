package com.xjsaber.learn.spring.springboot.intercept;

import com.xjsaber.learn.spring.springboot.invoke.Invocation;

/**
 * @author xjsaber
 */
public class MyInterceptor implements Interceptor{

    @Override
    public boolean before() {
        System.out.println("before ...");
        return true;
    }

    @Override
    public void after() {
        System.out.println("after ...");
    }

    @Override
    public Object around(Invocation invocation) throws Throwable {
        System.out.println("around before ...");
        Object obj = invocation.process();
        System.out.println("around after ...");
        return obj;
    }

    @Override
    public void afterReturning() {
        System.out.println("afterReturning ...");
    }

    @Override
    public void afterThrowing() {
        System.out.println("afterThrowing ...");
    }

    @Override
    public boolean useAround() {
        return true;
    }
}

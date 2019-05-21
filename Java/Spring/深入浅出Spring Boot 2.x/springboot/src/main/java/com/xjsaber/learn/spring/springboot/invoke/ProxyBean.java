package com.xjsaber.learn.spring.springboot.invoke;

import com.xjsaber.learn.spring.springboot.intercept.Interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author xjsaber
 */
public class ProxyBean implements InvocationHandler {

    private Object target = null;

    private Interceptor interceptor = null;

    /**
     * 绑定代理对象
     * @param target 被代理对象
     * @param interceptor 拦截器
     * @return 代理对象
     */
    public static Object getProxyBean(Object target, Interceptor interceptor){
        ProxyBean proxyBean = new ProxyBean();
        // 保存被代理
        proxyBean.target = target;
        // 保存拦截器
        proxyBean.interceptor = interceptor;

        Object proxy = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), proxyBean);
        // 生成代理对象
        return proxy;
    }

    /**
     * 处理代理对象方法逻辑
     * @param proxy 目标对象
     * @param method 方法
     * @param args 参数
     * @return 方法调用结果
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        // 异常标识
        boolean exceptionFlag = false;
        Invocation invocation = new Invocation(target, method, args);
        Object object = null;
        try {
            if (this.interceptor.before()){
                object = this.interceptor.around(invocation);
            }
        } catch (Throwable throwable) {
            // 产生异常
            exceptionFlag = true;
        }
        this.interceptor.after();
        if (exceptionFlag) {
            this.interceptor.afterThrowing();
        } else {
            this.interceptor.afterReturning();
            return object;
        }
        return null;
    }
}

package com.example.base;

import com.example.impl.ExcuteFunction;
import java.lang.reflect.Proxy;

/**
 * Created by xjsaber on 3/12/2016.
 */
public class ProxyObject {
    private InterceptorHandler handler = new InterceptorHandler();
    public Object getProxy(Object object){ //得到执行类的代理对象实例
        handler.setObject(object);
        //创建对象实例
        return Proxy.newProxyInstance(
                ExcuteFunction.class.getClassLoader(),
                object.getClass().getInterfaces(),
                handler);
        }
}

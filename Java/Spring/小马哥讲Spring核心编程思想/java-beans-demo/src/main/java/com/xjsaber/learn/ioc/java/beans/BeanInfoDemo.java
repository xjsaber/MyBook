package com.xjsaber.learn.ioc.java.beans;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;

/**
 * {@link java.beans.BeanInfo} 示例
 *
 * @author xjsaber
 */
public class BeanInfoDemo {

    public static void main(String[] args) throws IntrospectionException {

        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class);
    }
}

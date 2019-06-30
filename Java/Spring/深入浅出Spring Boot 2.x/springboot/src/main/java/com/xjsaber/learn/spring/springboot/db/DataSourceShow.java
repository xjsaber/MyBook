package com.xjsaber.learn.spring.springboot.db;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * 监测数据库连接池类型
 * 实现Spring Bean生命周期接口ApplicationContextAware
 * @author xjsaber
 */
@Component
public class DataSourceShow implements ApplicationContextAware {

    ApplicationContext applicationContext = null;

    /**
     * Spring容器会默认自动调用这个方法，注入Spring Ioc容器
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        DataSource dataSource = this.applicationContext.getBean(DataSource.class);
        System.out.println("----------------------------------------------------");
        System.out.println(dataSource.getClass().getName());
        System.out.println("----------------------------------------------------");
    }
}

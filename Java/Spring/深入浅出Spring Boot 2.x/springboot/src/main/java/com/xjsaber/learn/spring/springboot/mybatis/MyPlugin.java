package com.xjsaber.learn.spring.springboot.mybatis;

import com.xjsaber.learn.spring.springboot.intercept.Interceptor;
import com.xjsaber.learn.spring.springboot.invoke.Invocation;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Signature;

import java.sql.Connection;
import java.util.Properties;

/**
 * @author xjsaber
 */
@Intercepts({
        @Signature(type = StatementHandler.class,
        method = "prepare",
        args = { Connection.class, Integer.class})
})
public class MyPlugin implements Interceptor {

    Properties properties = null;

    @Override
    public boolean before() {
        return false;
    }

    @Override
    public void after() {

    }

    @Override
    public Object around(Invocation invocation) throws Throwable {
        return null;
    }

    @Override
    public void afterReturning() {

    }

    @Override
    public void afterThrowing() {

    }

    @Override
    public boolean useAround() {
        return false;
    }
}

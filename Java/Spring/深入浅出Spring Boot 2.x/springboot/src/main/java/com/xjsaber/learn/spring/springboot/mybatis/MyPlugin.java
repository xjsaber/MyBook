package com.xjsaber.learn.spring.springboot.mybatis;

import com.xjsaber.learn.spring.springboot.invoke.Invocation;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Plugin;
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

    private Properties properties = null;

    /**
     * 拦截逻辑
     */
    @Override
    public Object intercept(org.apache.ibatis.plugin.Invocation invocation) throws Throwable {
        System.out.println("插件拦截方法......");
        return invocation.proceed();
    }

    /**
     * 生成Mybatis拦截器拦截对象
     */
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    /**
     * 设置插件属性
     */
    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}

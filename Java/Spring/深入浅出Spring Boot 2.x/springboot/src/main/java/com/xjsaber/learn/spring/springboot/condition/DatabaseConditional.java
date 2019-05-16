package com.xjsaber.learn.spring.springboot.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author xjsaber
 */
public class DatabaseConditional implements Condition {

    /**
     * 数据库装配条件
     * @param context 条件上下文
     * @param annotatedTypeMetadata 注释类型的元数据
     * @return true 装配Bean，负责不装配
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata annotatedTypeMetadata) {
        Environment env = context.getEnvironment();
        return env.containsProperty("database.driverName")
                && env.containsProperty("database.url")
                && env.containsProperty("database.username")
                && env.containsProperty("database.password");
    }
}

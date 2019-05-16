package com.xjsaber.learn.spring.springboot.pojo.definition;

/**
 * @author xjsaber
 */
public interface Person {

    /**
     * 使用动物服务
     */
    void service();

    /**
     * 设置动物
     * @param animal 动物
     */
    void setAnimal(Animal animal);
}

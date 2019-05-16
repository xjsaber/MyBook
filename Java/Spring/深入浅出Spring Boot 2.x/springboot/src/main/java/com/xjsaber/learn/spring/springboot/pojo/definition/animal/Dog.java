package com.xjsaber.learn.spring.springboot.pojo.definition.animal;

import com.xjsaber.learn.spring.springboot.pojo.definition.Animal;
import org.springframework.stereotype.Component;

/**
 * @author xjsaber
 */
@Component
public class Dog implements Animal {
    @Override
    public void use() {
        System.out.println("狗【" + Dog.class.getSimpleName() + "】是看门用的");
    }
}

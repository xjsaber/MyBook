package com.xjsaber.learn.spring.springboot.pojo.definition.animal;

import com.xjsaber.learn.spring.springboot.pojo.definition.Animal;
import org.springframework.stereotype.Component;

/**
 *
 * @author xjsaber
 */
@Component
public class Cat implements Animal {
    @Override
    public void use() {
        System.out.println("猫【" + Cat.class.getSimpleName() + "】是卖萌的");
    }
}

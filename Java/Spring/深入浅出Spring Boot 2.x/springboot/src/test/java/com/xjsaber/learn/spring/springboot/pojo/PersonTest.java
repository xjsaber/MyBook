package com.xjsaber.learn.spring.springboot.pojo;

import com.xjsaber.learn.spring.springboot.pojo.definition.animal.Cat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonTest {

    @Autowired
    private BusinessPerson businessPerson;

    @Autowired
    private Cat cat;

    @Test
    public void setAnimalTest(){
        businessPerson.setAnimal(cat);
    }
}

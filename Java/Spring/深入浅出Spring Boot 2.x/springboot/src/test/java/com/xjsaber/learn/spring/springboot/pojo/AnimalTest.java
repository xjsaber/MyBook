package com.xjsaber.learn.spring.springboot.pojo;

import com.xjsaber.learn.spring.springboot.pojo.definition.animal.Cat;
import com.xjsaber.learn.spring.springboot.pojo.definition.animal.Dog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnimalTest {

    @Autowired
    private Cat catService;
    @Autowired
    private Dog dogService;

    @Test
    public void catNameTest(){
        catService.use();
    }

    @Test
    public void dogNameTest(){
        dogService.use();
    }
}

package com.xjsaber.learn.spring.springboot.pojo;

import com.xjsaber.learn.spring.springboot.pojo.definition.Animal;
import com.xjsaber.learn.spring.springboot.pojo.definition.Person;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * @author xjsaber
 */
@Component
public class BusinessPerson implements Person, BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean, DisposableBean {

    private Animal animal;

//    直接使用构造器进行配置
//    public BusinessPerson(@Autowired @Qualifier("dog") Animal animal){
//        this.animal = animal;
//    }

    @Override
    public void service() {
        this.animal.use();
    }

    @Override
    @Autowired @Qualifier(value = "dog")
    public void setAnimal(Animal animal) {
        System.out.println("延迟依赖加载");
        this.animal = animal;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("【" + this.getClass().getSimpleName() +  "】调用BeanFactoryAware的setBeanFactory");
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("【" + this.getClass().getSimpleName() +  "】调用BeanNameAware的setBeanName");
    }

    @PreDestroy
    public void destroy1() throws Exception {
        System.out.println("【" + this.getClass().getSimpleName() +  "】注解@PreDestory定义的自定义销毁方法");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("【" + this.getClass().getSimpleName() +  "】调用BeanNameAware的setBeanName");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("【" + this.getClass().getSimpleName() +  "】调用BeanNameAware的setBeanName");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("【" + this.getClass().getSimpleName() +  "】调用BeanNameAware的setBeanName");
    }
}

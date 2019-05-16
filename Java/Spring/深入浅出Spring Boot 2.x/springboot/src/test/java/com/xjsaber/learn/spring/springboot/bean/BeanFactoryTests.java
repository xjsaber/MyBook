package com.xjsaber.learn.spring.springboot.bean;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BeanFactoryTests {

	@Test
	public void scopeBeanTest(){
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ScopeBean scopeBean1 = ctx.getBean(ScopeBean.class);
		ScopeBean scopeBean2 = ctx.getBean(ScopeBean.class);
		System.out.println(scopeBean1 == scopeBean2);
	}

	@Test
	public void contextLoads() {

	}

}

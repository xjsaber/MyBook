package com.xjsaber.learn.spring.springboot;

import com.xjsaber.learn.spring.springboot.aspect.MyAspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author xjsaber
 */
@SpringBootApplication
public class DemoApplication {

	@Bean
	public MyAspect initMyAspect(){
		return new MyAspect();
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

package com.xjsaber.learn.spring.springboot.aop;

import com.xjsaber.learn.spring.springboot.aspect.MyAspect;
import com.xjsaber.learn.spring.springboot.intercept.MyInterceptor;
import com.xjsaber.learn.spring.springboot.invoke.ProxyBean;
import com.xjsaber.learn.spring.springboot.service.HelloService;
import com.xjsaber.learn.spring.springboot.service.impl.HelloServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AspectTest {

    @Bean(name = "myAspect")
    public MyAspect initMyAspect(){
        return new MyAspect();
    }

    @Test
    public void testProxy(){
        HelloService helloService = new HelloServiceImpl();
        HelloService proxy = (HelloService) ProxyBean.getProxyBean(helloService, new MyInterceptor());
        System.out.println("\n###############################name is null!!#######################\n");
        proxy.sayHello(null);
    }
}

package com.xjsaber.learn.spring.springboot.aspect;

import com.xjsaber.learn.spring.springboot.pojo.UserValidator;
import com.xjsaber.learn.spring.springboot.pojo.impl.UserValidatorImpl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * @author xjsaber
 */
@Aspect
public class MyAspect {

    @Pointcut("execution(* com.xjsaber.learn.spring.springboot.service.impl.UserServiceImpl.printUser(..))")
    public void pointCut() {

    }

    @DeclareParents(
            value = "com.xjsaber.learn.spring.springboot.service.impl.UserServiceImpl+",
            defaultImpl = UserValidatorImpl.class
    )
    public UserValidator userValidator;

    @Before("pointCut()")
    public void before(){
        System.out.println("before...");
    }

    @After("pointCut()")
    public void after(){
        System.out.println("after...");
    }

    @AfterReturning("pointCut()")
    public void afterReturning(){
        System.out.println("afterReturning...");
    }

    @AfterThrowing("pointCut()")
    public void afterThrowing(){
        System.out.println("afterThrowing...");
    }

    @Around("pointCut()")
    public void around(ProceedingJoinPoint jp) throws Throwable{
        System.out.println("around before");
        jp.proceed();
        System.out.println("around after");
    }
}

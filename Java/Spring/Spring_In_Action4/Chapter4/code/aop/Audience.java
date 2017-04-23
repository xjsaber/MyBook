package com.xjsaber.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * Created by xjsaber on 2017/4/22.
 *
 */
@Aspect
public class Audience {

    @Pointcut("execution(* com.xjsaber.spring.aop.Performance.perform(..))")
    public void performance() {}

    @Around(value = "performance()")
    public void watchPerformance(ProceedingJoinPoint jp){
        try {
            System.out.println("Silencing cell phones");
            System.out.println("Taking seats");
            jp.proceed();
            System.out.println("CLAP CLAP CLAP!!!");
        } catch (Throwable e) {
            System.out.println("Demanding a refund");
        }
    }

//    /**
//     * 表演之前
//     */
//    @Before("performance()")
//    public void silenceCellPhones(){
//
//        System.out.println("Silencing cell phones");
//    }
//
//    /**
//     * 表演之前
//     */
//    @Before("performance()")
//    public void taskSeats() {
//
//        System.out.println("Taking seats");;
//    }
//
//    /**
//     * 表演之后
//     */
//    @AfterReturning("performance()")
//    public void applause() {
//
//        System.out.println("CLAP CLAP CLAP!!!");;
//    }
//
//    /**
//     * 表演失败之后
//     */
//    @AfterThrowing("performance()")
//    public void demandRefund() {
//
//        System.out.println("Demanding a refund");
//    }
}

package com.xjsaber.diveinsprintboot.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Java7 for循环实现方式 {@link CalculateService}
 */
@Service
@Profile("Java7")
public class Java7CalculateService implements CalculateService{

    @Override
    public Integer sum(Integer... value) {
        System.out.println("Java7 for循环的实现方式");
        Integer sum = 0;
        for (Integer i : value){
            sum += i;
        }
        return sum;
    }

    public static void main(String[] args){
        Integer result = new Java7CalculateService().sum(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(result);
    }
}

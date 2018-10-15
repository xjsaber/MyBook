package com.xjsaber.diveinsprintboot.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Java8 lambda的实现方式 {@link CalculateService}
 */
@Service
@Profile("Java8")
public class Java8CalculateService implements CalculateService{

    @Override
    public Integer sum(Integer... value) {
        System.out.println("Java8 lambda的实现方式");
        return Arrays.stream(value).reduce(0, Integer::sum);
    }

    public static void main(String[] args){
        Integer result = new Java7CalculateService().sum(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(result);
    }
}

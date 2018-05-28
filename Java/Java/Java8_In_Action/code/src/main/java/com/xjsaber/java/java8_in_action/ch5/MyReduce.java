package com.xjsaber.java.java8_in_action.ch5;

import java.util.Arrays;
import java.util.List;

public class MyReduce {
    public static void main(String[] args){
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        Integer result = list.stream().reduce(0, Integer::sum);
        System.out.println(result);
    }
}

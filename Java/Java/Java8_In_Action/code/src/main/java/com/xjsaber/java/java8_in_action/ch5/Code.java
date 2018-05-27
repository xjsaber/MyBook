package com.xjsaber.java.java8_in_action.ch5;

import java.util.Arrays;
import java.util.List;

/**
 * @author xjsaber
 */
public class Code {
    public static void main(String[] args){
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);
    }
}

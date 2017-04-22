package com.xjsaber.spring.cache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.EmptyResultDataAccessException;

/**
 * Created by xjsaber on 2017/4/22.
 */
public class Test {

    @Cacheable("testCache")
    public static void main(String[] args){
        findOne();
    }

    private static Object findOne() {
        long id = 132;

        try {
            return 123;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
}

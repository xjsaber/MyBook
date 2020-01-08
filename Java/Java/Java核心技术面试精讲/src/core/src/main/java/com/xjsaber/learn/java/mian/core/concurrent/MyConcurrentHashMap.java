package com.xjsaber.learn.java.mian.core.concurrent;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xjsaber
 */
public class MyConcurrentHashMap {

    private final ConcurrentHashMap<String, String> myMap = new ConcurrentHashMap<>(16);
    public void init(){
        myMap.put("1", "1");
        myMap.put("2", "2");
    }

    public void readAll(){
        System.out.println(myMap.get("1"));
        System.out.println(myMap.get("2"));
    }
}

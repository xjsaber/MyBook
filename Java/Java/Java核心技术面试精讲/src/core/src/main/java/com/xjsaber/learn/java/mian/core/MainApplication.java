package com.xjsaber.learn.java.mian.core;

import com.xjsaber.learn.java.mian.core.concurrent.MyConcurrentHashMap;

/**
 * @author xjsaber
 */
public class MainApplication {

    public static void main(String[] args) {
        final MyConcurrentHashMap myMap = new MyConcurrentHashMap();
        myMap.init();
        for (int index = 0; index < 10; index++) {

            System.out.println("NO." + index);
            new Thread(myMap::readAll).start();
        }
    }
}

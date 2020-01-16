package com.xjsaber.learn.java.ch3;

/**
 * @author xjsaber
 */
public class SafeCalc {

    long value = 0L;
    long get(){
        synchronized (new Object()){
            return value;
        }
    }
    void addOne(){
        synchronized (new Object()){
            value += 1;
        }
    }
}

package com.xjsaber.java.thread.code.ch4.scheduled;

import java.util.Date;

/**
 * @author xjsaber
 */
public class Task implements Runnable{

    private String name;

    public Task(String name){
        this.name = name;
    }

    @Override
    public void run() {
        System.out.printf("%s: Starting at: %s\n", name ,new Date());
    }
}

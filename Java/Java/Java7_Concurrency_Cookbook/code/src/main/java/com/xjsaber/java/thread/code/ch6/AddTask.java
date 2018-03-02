package com.xjsaber.java.thread.code.ch6;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author xjsaber
 */
public class AddTask implements Runnable{

    private ConcurrentLinkedDeque<String> list;

    public AddTask(ConcurrentLinkedDeque<String> list){
        this.list = list;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        for (int i = 0; i < 10000; i++){
            list.add(name + ": Element " + i);
        }
    }
}

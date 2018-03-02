package com.xjsaber.java.thread.code.ch6;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author xjsaber
 */
public class PollTask implements Runnable{

    private ConcurrentLinkedDeque<String> list;

    public PollTask(ConcurrentLinkedDeque<String> list){
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5000; i++){
            list.pollFirst();
            list.pollLast();
        }
    }
}

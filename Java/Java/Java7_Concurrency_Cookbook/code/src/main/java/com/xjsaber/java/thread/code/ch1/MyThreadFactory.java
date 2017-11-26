package com.xjsaber.java.thread.code.ch1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadFactory;

/**
 * @author xjsaber
 */
public class MyThreadFactory implements ThreadFactory{

    private int counter;
    private String name;
    private List<String> stats;

    public MyThreadFactory(String name){
        this.counter = 0;
        this.name = name;
        this.stats = new ArrayList<>();
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, name + "-Thread_" + counter);
        counter++;
        stats.add(String.format("Create thread %d with name %s on %s\n", t.getId(), t.getName(), new Date()));
        return t;
    }

    public String getStats(){
        StringBuilder buffer = new StringBuilder();
        for (String stat : stats) {
            buffer.append(stat);
            buffer.append("\n");
        }
        return buffer.toString();
    }
}

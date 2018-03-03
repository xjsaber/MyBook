package com.xjsaber.java.thread.code.ch7.priority;

import java.util.concurrent.TimeUnit;

/**
 * @author xjsaber
 */
public class MyPriorityTask implements Runnable, Comparable<MyPriorityTask>{

    private int priority;
    private String name;

    MyPriorityTask(String name, int priority){
        this.priority = priority;
        this.name = name;
    }

    public int getPriority(){
        return this.priority;
    }

    @Override
    public int compareTo(MyPriorityTask o) {
        if (this.getPriority() < o.getPriority()){
            return 1;
        }
        else if (this.getPriority() > o.getPriority()){
            return -1;
        }
        return 0;
    }

    @Override
    public void run() {
        System.out.printf("MyPriorityTask: %s Priority: %d \n", name, priority);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

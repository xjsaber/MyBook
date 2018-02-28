package com.xjsaber.java.thread.code.ch4.rejected;

import java.util.concurrent.TimeUnit;

/**
 * @author xjsaber
 */
public class Task implements Runnable {

    private String name;

    public Task(String name){
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("Task " + name + ": Starting");
        try{
            long duration = (long)(Math.random() * 10);
            System.out.printf("Task %s: ReportGenerator: Generating a report during %d seconds\n", name, duration);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Task %s: Ending\n", name);
    }

    @Override
    public String toString(){
        return name;
    }
}

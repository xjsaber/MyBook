package com.xjsaber.java.thread.code.ch4.executable;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author xjsaber
 */
public class ExecutableTask implements Callable<String> {

    private String name;
    public String getName(){
        return this.name;
    }

    public ExecutableTask(String name){
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        try {
            long duration = (long)(Math.random() * 10);
            System.out.printf("%s: Waiting %d seconds for results.\n", this.name, duration);
            TimeUnit.MILLISECONDS.sleep(duration);
        } catch (InterruptedException ignored){ }
        return "Hello, World. I'm " + name;
    }
}

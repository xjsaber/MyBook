package com.xjsaber.java.thread.code.ch4.cancel;

import java.util.concurrent.Callable;

/**
 * @author xjsaber
 */
public class Task implements Callable<String> {

    private String name;

    @Override
    public String call() throws Exception{
        while (true) {
            System.out.print("Task: Test\n");
            Thread.sleep(100);
        }
    }
}

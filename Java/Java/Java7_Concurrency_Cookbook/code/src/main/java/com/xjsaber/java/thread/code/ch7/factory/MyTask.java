package com.xjsaber.java.thread.code.ch7.factory;

import java.util.concurrent.TimeUnit;

/**
 * @author xjsaber
 */
public class MyTask implements Runnable {

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

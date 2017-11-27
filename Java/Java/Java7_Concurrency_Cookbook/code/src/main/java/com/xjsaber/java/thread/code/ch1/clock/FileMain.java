package com.xjsaber.java.thread.code.ch1.clock;

import java.util.concurrent.TimeUnit;

/**
 * @author xjsaber
 */
public class FileMain {

    public static void main(String[] args){
        FileClock clock = new FileClock();
        Thread thread = new Thread(clock);
        thread.start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();
    }
}

package com.xjsaber.java.netty.guide.ch21;

import java.util.concurrent.TimeUnit;

public class ThreadStopExample {

    private static boolean stop;

    public static void main(String[] args) throws InterruptedException {
        Thread workThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (!stop){
                    i++;
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        workThread.start();
        TimeUnit.SECONDS.sleep(3);
        stop = true;
    }
}

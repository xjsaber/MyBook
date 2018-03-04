package com.xjsaber.java.thread.code.ch8.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author xjsaber
 */
public class Task implements Runnable {

    private Lock lock;

    public Task(Lock lock){
        this.lock = lock;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++){
            lock.lock();
            System.out.printf("%s: Get the Lock.\n", Thread.currentThread().getName());
            try {
                TimeUnit.MILLISECONDS.sleep(500);
                System.out.printf("%s: Free the Lock.\n", Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}

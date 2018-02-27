package com.xjsaber.java.thread.code.ch3.senaphore;

import java.util.concurrent.Semaphore;

/**
 * @author xjsaber
 */
public class PrintQueue {

    private final Semaphore semaphore;

    public PrintQueue(){
        semaphore = new Semaphore(1);
    }

    public void printJob(){
        try {
            semaphore.acquire();
            long duration = (long)(Math.random() * 10);
            System.out.printf("%s: PrintQueue: Printing a Job during %d seconds\n", Thread.currentThread().getName(), duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }
}

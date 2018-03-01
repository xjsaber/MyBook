package com.xjsaber.java.thread.code.ch5.exception;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * @author xjsaber
 */
public class Main {

    public static void main(String[] args){
        int[] array = new int[100];
        Task task = new Task(array, 0, 100);
        ForkJoinPool pool = new ForkJoinPool();
        pool.execute(task);
        pool.shutdown();

        try {
            pool.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (task.isCompletedAbnormally()) {
            System.out.print("Main: An exception has ocurred\n");
            System.out.printf("Main: %s\n", task.getException());
        }
        System.out.printf("Main: Result: %d", task.join());
    }
}

package com.xjsaber.java.thread.code.ch4.rejected;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author xjsaber
 */
public class Main {

    public static void main(String[] args){
        RejectedTaskController controller = new RejectedTaskController();
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        executor.setRejectedExecutionHandler(controller);
        System.out.print("Main: Starting.\n");
        for (int i = 0; i < 3; i++){
            Task task = new Task("Task" + i);
            executor.submit(task);
        }
        System.out.print("Main: Shutting down the Executor.\n");
        executor.shutdown();
        System.out.print("Main: Sending another Task.\n");
        Task task = new Task("RejectedTask");
        executor.submit(task);
        System.out.println("Main:End");
        System.out.println("Main: End.");
    }
}

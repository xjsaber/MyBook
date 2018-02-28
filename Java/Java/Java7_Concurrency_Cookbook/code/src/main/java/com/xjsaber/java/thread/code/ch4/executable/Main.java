package com.xjsaber.java.thread.code.ch4.executable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author xjsaber
 */
public class Main {

    public static void main(String [] args){
        ExecutorService executor = (ExecutorService) Executors.newCachedThreadPool();
        ResultTask[] resultTask = new ResultTask[5];
        for (int i = 0; i < 5; i++){
            ExecutableTask executableTask = new ExecutableTask("Task " + i);
            resultTask[i] = new ResultTask(executableTask);
            executor.submit(resultTask[i]);
        }
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (ResultTask item : resultTask) {
            item.cancel(true);
        }

        for (ResultTask item : resultTask) {
            try {
                if (!item.isCancelled()) {
                    System.out.printf("%s\n", item.get());
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
    }
}

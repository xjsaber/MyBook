package com.xjsaber.java.thread.code.ch1.group;

import java.util.concurrent.TimeUnit;

/**
 * @author xjsaber
 */
public class Main {

    public static void main(String[] args){
        ThreadGroup threadGroup = new ThreadGroup("Searcher");
        Result result = new Result();
        SearchTask searchTask = new SearchTask(result);

        for (int i = 0; i < 5; i++){
            Thread thread = new Thread(threadGroup, searchTask);
            thread.start();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("Number of Threads: %d\n", threadGroup.activeCount());
        System.out.print("Information about the Thread Group \n");
        threadGroup.list();;
    }
}

package com.xjsaber.java.thread.code.ch5.cancel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;

/**
 * @author xjsaber
 */
public class TaskManager {

    private List<ForkJoinTask<Integer>> tasks;

    TaskManager(){
        tasks = new ArrayList<>();
    }

    public void addTask(ForkJoinTask<Integer> task){
        tasks.add(task);
    }

    public void cancelTasks(ForkJoinTask<Integer> cancelTask){
        for (ForkJoinTask<Integer> task : tasks){
            task.cancel(true);
//            ((SearchNumberTask)task)
        }
    }
}

package com.xjsaber.java.thread.code.ch5.cancel;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * @author xjsaber
 */
public class SearchNumberTask extends RecursiveTask<Integer>{

    private int[] numbers;
    private int start, end;
    private int number;
    private TaskManager manager;
    private final static int NOT_FOUND = 1;

    public SearchNumberTask(int[] numbers, int start, int end, int number, TaskManager manager){
        this.numbers = numbers;
        this.start = start;
        this.end = end;
        this.number = number;
        this.manager = manager;
    }

    @Override
    protected Integer compute() {
        System.out.println("Task: " + start + ":" + end);
        int ret;
        if (end - start > 10){
            ret = launchTasks();
        } else {
            ret = lookForNumber();
        }
        return ret;
    }

    private int lookForNumber(){
        for (int i = start; i < end; i++){
            if (numbers[i] == number){
                System.out.printf("Task: Number %d found in position %d \n", number, i);
                manager.cancelTasks(this);
                return i;
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return NOT_FOUND;
    }

    private int launchTasks(){
        int mid = (start + end) / 2;
        SearchNumberTask task1 = new SearchNumberTask(numbers, start, mid + 1, number, manager);
        SearchNumberTask task2 = new SearchNumberTask(numbers, mid + 1, end, number, manager);
        manager.addTask(task1);
        manager.addTask(task2);
        task1.fork();
        task2.fork();
        int returnValue;
        returnValue = task1.join();
        if (returnValue != -1){
            return returnValue;
        }
        returnValue = task2.join();
        return returnValue;
    }

    public void writeCancelMessage(){
        System.out.printf("Task: Cancelled task from %d to %d", start, end);
    }
}

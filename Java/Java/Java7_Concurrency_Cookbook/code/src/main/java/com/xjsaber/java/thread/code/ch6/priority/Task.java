package com.xjsaber.java.thread.code.ch6.priority;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author xjsaber
 */
public class Task implements Runnable{

    private int id;
    private PriorityBlockingQueue<Event> queue;

    public Task(int id, PriorityBlockingQueue<Event> queue){
        this.id = id;
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++){
            Event event = new Event(id ,i);
            queue.add(event);
        }
    }
}

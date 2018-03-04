package com.xjsaber.java.thread.code.ch6.priority;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author xjsaber
 */
public class Main {

    public static void main(String[] args) {

        PriorityBlockingQueue<Event> queue = new PriorityBlockingQueue<>();
        Thread[] taskThreads = new Thread[5];
        for (int i = 0; i < taskThreads.length; i++) {
            Task task = new Task(i, queue);
            taskThreads[i] = new Thread(task);
        }
        for (Thread taskThread : taskThreads) {
            taskThread.start();
        }
        for (int i = 0; i < taskThreads.length; i++){
            try {
                taskThreads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("Main: Queue Size: %d\n", queue.size());
        for (int i = 0; i < taskThreads.length; i++) {
            Event event = queue.poll();
            System.out.printf("Thread %s: Priority %d\n", event.getThread(), event.getPriority());
        }

        System.out.printf("Main: Queue size: %d \n", queue.size());
        System.out.print("Main: End of the program\n");
    }
}

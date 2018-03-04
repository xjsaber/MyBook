package com.xjsaber.java.thread.code.ch8.lock;

/**
 * @author xjsaber
 */
public class Main {

    public static void main(String[] args){
        MyLock lock = new MyLock();
        Thread[] threads = new Thread[5];
        for (int i = 0; i < threads.length; i++){
            Task task = new Task(lock);
            threads[i] = new Thread(task);
            threads[i].start();
        }
        for (int i = 0; i < 15; i++){
            System.out.print("Main: Logging the Lock \n");
            System.out.print("***********************\n");
            System.out.printf("Lock: Owner : %s \n", lock.getOwnerName());
        }
    }
}

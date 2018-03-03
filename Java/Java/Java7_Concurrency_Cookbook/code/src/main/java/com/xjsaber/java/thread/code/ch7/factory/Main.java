package com.xjsaber.java.thread.code.ch7.factory;

/**
 * @author xjsaber
 */
public class Main {

    public static void main(String[] args)throws Exception {
        MyThreadFactory myFactory = new MyThreadFactory("MyThreadFactory");

        MyTask myTask = new MyTask();
        Thread thread = myFactory.newThread(myTask);
        thread.start();
        thread.join();

        System.out.print("Main: Thread information. \n");
        System.out.printf("%s \n", thread);
        System.out.print("Main: End of the example. \n");
    }
}

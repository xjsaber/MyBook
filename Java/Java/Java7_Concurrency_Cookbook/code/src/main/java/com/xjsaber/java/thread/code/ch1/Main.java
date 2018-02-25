package com.xjsaber.java.thread.code.ch1;

import java.util.concurrent.TimeUnit;

/**
 * @author xjsaber
 */
public class Main {

    public static void main(String[] args){

        /*
          t5
         */
        /////////////////////////////////////////////////////////////////////////////////////
//        FileSearch fileSearch = new FileSearch("C:\\", "eula.1028.txt");
//        Thread thread = new Thread(fileSearch);
//        thread.start();
//
//        try{
//            TimeUnit.SECONDS.sleep(10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        thread.interrupt();
        /////////////////////////////////////////////////////////////////////////////////////

        /*
          t11
         */
        /////////////////////////////////////////////////////////////////////////////////////

        ThreadGroup threadGroup = new ThreadGroup("Searcher");

        Result result = new Result();
        SearchTask searchTask = new SearchTask(result);

        for (int i = 0; i < 5; i++){
            Thread thread = new Thread(threadGroup, searchTask);
            thread.start();
            try {
                TimeUnit.SECONDS.sleep(1);;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("Number of Threads: %d\n", threadGroup.activeCount());
        System.out.printf("Information about the Thread Group \n");
        threadGroup.list();

        Thread[] threads = new Thread[threadGroup.activeCount()];
        threadGroup.enumerate(threads);
        for (int i = 0; i < threadGroup.activeCount(); i++){
            System.out.printf("Thread %s: %s\n", threads[i].getName(), threads[i].getState());
        }

        waitFinish(threadGroup);

        threadGroup.interrupt(); //中断这个组中的其余线程
        /////////////////////////////////////////////////////////////////////////////////////

        /*
          t12
         */
        /////////////////////////////////////////////////////////////////////////////////////
//        MyThreadGroup threadGroup = new MyThreadGroup("MyThreadGroup");
//
//        for (int i = 0; i < 2; i++){
//            Thread t = new Thread(threadGroup, task);
//            t.start();
//        }

        /////////////////////////////////////////////////////////////////////////////////////

        /*
          t13
         */
        /////////////////////////////////////////////////////////////////////////////////////
//        MyThreadFactory factory = new MyThreadFactory("MyThreadFactory");
//        Task task = new Task();
//        Thread thread;
//        System.out.println("Starting the Threads\n");
//        for (int i = 0; i < 10; i++){
//            thread = factory.newThread(task);
//            thread.start();
//        }
//
//        System.out.printf("Factory stats:\n");
//        System.out.printf("%s\n", factory.getStats());
        /////////////////////////////////////////////////////////////////////////////////////
    }

    private static void waitFinish(ThreadGroup threadGroup){
        while (threadGroup.activeCount() > 9){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

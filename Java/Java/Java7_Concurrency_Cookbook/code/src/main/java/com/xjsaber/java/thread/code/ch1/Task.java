package com.xjsaber.java.thread.code.ch1;

import java.util.Random;

/**
 * @author xjsaber
 */
public class Task implements Runnable {
    @Override
    public void run() {

        /*
        * ch12
        * */
        ///////////////////////////////////////////
        int result;
        Random random = new Random(Thread.currentThread().getId());
        while (true){
            result = 1000 / ((int)(random.nextDouble() * 1000));
            System.out.printf("%s : %d\n", Thread.currentThread().getId(), result);
            if (Thread.currentThread().isInterrupted()){
                System.out.printf("%d: Interrupted\n", Thread.currentThread().getId());
                return;
            }
        }
        ///////////////////////////////////////////

        /*
        * ch13
        * */
        ///////////////////////////////////////////
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        ///////////////////////////////////////////
    }
}

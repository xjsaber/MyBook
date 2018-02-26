package com.xjsaber.java.thread.code.ch1.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @author xjsaber
 */
public class Main {

    public static void main(String[] args){
        FileSearch searcher = new FileSearch("C:\\WorkPlace", "pac.txt");
        Thread thread = new Thread(searcher);
        thread.start();

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}

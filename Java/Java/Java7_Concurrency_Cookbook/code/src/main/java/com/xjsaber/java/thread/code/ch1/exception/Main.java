package com.xjsaber.java.thread.code.ch1.exception;

/**
 * @author xjsaber
 */
public class Main {

    public static void main(String[] args){

        Task task = new Task();
        Thread thread = new Thread(task);
        thread.setUncaughtExceptionHandler(new ExceptionHandler());
        thread.start();
    }
}

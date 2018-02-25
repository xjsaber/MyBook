package com.xjsaber.java.thread.code.ch1.exception;

/**
 * @author xjsaber
 */
public class ExceptionHandler implements Thread.UncaughtExceptionHandler{

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.print("An exception has been captured\n");
        System.out.printf("Thread: %s\n", t.getId());
        System.out.printf("Exception: %s: %s\n", e.getClass().getName(), e.getMessage());
        System.out.print("Stack Trace: \n");
        e.printStackTrace(System.out);
        System.out.printf("Thread status: %s \n", t.getState()
        );
    }
}

package com.xjsaber.java.thread.code.ch1.exception;

/**
 * @author xjsaber
 */
public interface UncaughtExceptionHandler {

    void uncaughtException(Thread t, Throwable e);
}

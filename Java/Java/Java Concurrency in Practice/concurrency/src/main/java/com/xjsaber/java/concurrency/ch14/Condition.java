package com.xjsaber.java.concurrency.ch14;

import java.util.concurrent.TimeUnit;

/**
 * @author xjsaber
 */
public interface Condition {

    void await() throws InterruptedException;
    boolean await(long time, TimeUnit unit) throws InterruptedException;

}

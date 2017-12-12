package com.xjsaber.java.concurrency.ch13;

import java.util.concurrent.locks.Lock;

/**
 * @author xjsaber
 */
public interface ReadWriteLock {

    /**
     * 读锁
     * @return
     */
    Lock readLock();

    /**
     * 写锁
     * @return
     */
    Lock writeLock();
}

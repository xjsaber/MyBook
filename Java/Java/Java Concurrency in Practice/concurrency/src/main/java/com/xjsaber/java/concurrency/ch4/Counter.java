package com.xjsaber.java.concurrency.ch4;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * @author xjsaber
 * 使用Java监视器模式的线程安全计数器
 */
@ThreadSafe
public final class Counter {

    @GuardedBy("this") private long value = 0;

    public synchronized long getValue() {
        return value;
    }

    public synchronized long increment(){
        if (value == Long.MAX_VALUE){
            throw new IllegalStateException("counter overflow");
        }
        return ++value;
    }
}

package com.xjsaber.java.concurrency.ch4;

import net.jcip.annotations.GuardedBy;

/**
 * @author xjsaber
 * 通过一个私有锁来保护状态
 */
public class PrivateLock {

    private final Object myLock = new Object();
    @GuardedBy("myLock") Widget widget;

    void someMethod(){
        synchronized (myLock){
            // 访问或修改Widget的状态
        }
    }
}

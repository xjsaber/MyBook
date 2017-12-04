package com.xjsaber.java.concurrency.ch3;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * @author xjsaber
 */
@ThreadSafe
public class SynchronizedInteger {

    @GuardedBy("this") private int value;

    public synchronized int get() {return value;}
    public synchronized void set(int value) {this.value = value;}
}

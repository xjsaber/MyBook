package com.xjsaber.java.concurrency.ch3;

import net.jcip.annotations.NotThreadSafe;

/**
 * @author xjsaber
 */
@NotThreadSafe
public class MutableInteger {

    private int value;

    public int get() {return value;}
    public void set(int value){this.value = value;}
}

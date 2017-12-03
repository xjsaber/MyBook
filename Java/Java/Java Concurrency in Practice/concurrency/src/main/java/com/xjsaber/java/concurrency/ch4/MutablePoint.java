package com.xjsaber.java.concurrency.ch4;

import net.jcip.annotations.NotThreadSafe;

/**
 * @author xjsaber
 * 与Java.awt.Point类似的可变Point类
 */
@NotThreadSafe
public class MutablePoint {
    public int x, y;
    public MutablePoint(){
        x = 0;
        y = 0;
    }
    public MutablePoint(MutablePoint p){
        this.x = p.x;
        this.y = p.y;
    }
}

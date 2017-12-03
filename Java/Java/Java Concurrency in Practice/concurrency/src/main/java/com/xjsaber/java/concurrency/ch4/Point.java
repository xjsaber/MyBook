package com.xjsaber.java.concurrency.ch4;

import net.jcip.annotations.Immutable;

/**
 * @author xjsaber
 */
@Immutable
public class Point {

    public final int x, y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}

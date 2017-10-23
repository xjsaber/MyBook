package com.xjsaber.java.io18.serialize;

import java.io.Serializable;

/**
 * @author xjsaber
 */
public class Worm {
}

class Data implements Serializable {
    private int n;
    public Data(int n) { this.n = n; }
    @Override
    public String toString() {return Integer.toString(n);}
}
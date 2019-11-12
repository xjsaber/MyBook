package com.xjsaber.learn.java;

public class Test {

    // write your code here
    private int x = 0;
    private volatile boolean v = false;

    public static void main(String[] args) {


    }

    public void writer() {
        x = 42;
        v = true;
    }

    public void reader() {
        if (v){
            // x = ?
        }
    }
}

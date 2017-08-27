package com.xjsaber.java.generator15.util;

public class TwoTuple<A, B> {

    final A first;
    final B second;
    public TwoTuple(A a, B b) {
        first = a;
        second = b;
    }

    public String toString(){
        return "(" + first + ", " + second + ")";
    }
}

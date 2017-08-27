package com.xjsaber.java.generator15.util;

public class TreeTuple<A, B, C> extends TwoTuple<A, B> {

    final C third;
    public TreeTuple(A a, B b, C c){
        super(a, b);
        third = c;
    }
    public String toString(){
        return "(" + first + ", " + second + ", " + third + ")";
    }
}

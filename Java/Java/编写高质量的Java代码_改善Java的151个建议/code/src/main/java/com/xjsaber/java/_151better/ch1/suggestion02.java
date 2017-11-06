package com.xjsaber.java._151better.ch1;

import java.util.Random;

public class suggestion02 {
    public static void main(String[] args){
        System.out.println(" 常量会变：" + Const.RAND_CONST);
    }
}

interface Const {
    public static final int RAND_CONST = new Random().nextInt();
}
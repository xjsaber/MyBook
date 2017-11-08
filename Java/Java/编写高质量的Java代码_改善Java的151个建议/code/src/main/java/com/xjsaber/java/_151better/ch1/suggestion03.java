package com.xjsaber.java._151better.ch1;

/**
 * @author xjsaber
 */
public class suggestion03 {

    public static void main(String[] args){
        int i = 80;
        String s = String.valueOf(i < 100 ? 90 : 100);
        String s1 = String.valueOf(i < 100 ? 90 : 100.0);
        System.out.println(" 两者是否相等：" + s.equals(s1));
    }
}

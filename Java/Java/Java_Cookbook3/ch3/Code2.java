package com.yunrer.test;

import java.util.StringTokenizer;

/**
 * Created by xjsaber on 2017/4/21.
 *
 */
public class Code2 {

    public static void main(String[] args){
        StringTokenizer st = new StringTokenizer("Hello,World|of Java", ",|", true);

        while (st.hasMoreTokens()){
            System.out.println("Token:" + st.nextToken());
        }
    }
}

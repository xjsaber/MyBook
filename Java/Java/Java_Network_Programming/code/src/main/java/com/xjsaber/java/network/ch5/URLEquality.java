package com.xjsaber.java.network.ch5;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * equals()方法指出它们是否相等
 * @author xjsaber
 */
public class URLEquality {

    public static void main(String[] args){
        try {
            URL www = new URL("http://www.baidu.com");
            URL baidu = new URL("http://baidu.com");
            if (baidu.equals(www)){
                System.out.println(baidu + " is the same as " + www);
            } else {
                System.out.println(baidu + " is not the same as " + www);
            }
        } catch (MalformedURLException e) {
            System.err.println(e);
        }
    }
}

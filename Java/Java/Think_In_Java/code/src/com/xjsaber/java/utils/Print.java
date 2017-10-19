package com.xjsaber.java.utils;

import java.io.PrintStream;

public class Print {

    // Print with a newline
    public static void print(Object obj) {
        System.out.println(obj);
    }
    // Print a newline by itself
    public static void print() {
        System.out.println();
    }
    // Print with no line break;
    public static void printnb(Object object) {
        System.out.print(object);
    }
    // The new Java SE5 printf() (from C):
    public static PrintStream printStream(String format, Object... args){
        String temp = "123";
        switch (temp){
            case "123":
                break;
        }
        return System.out.printf(format, args);
    }
}

package com.xjsaber.learn.datastruct.idea;

public class SimpleUtils {

    public static void swap(int[] a, int m, int n){
        int temp = a[m];
        a[m] = a[n];
        a[n] = temp;
    }
}

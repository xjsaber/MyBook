package com.xjsaber.java.thread.code.ch5.async;

import java.util.concurrent.ForkJoinPool;

/**
 * @author xjsaber
 */
public class Main {

    public static void main(String[] args){
        ForkJoinPool pool = new ForkJoinPool();
        FolderProcessor system = new FolderProcessor("C:\\Windows", "log");

    }
}

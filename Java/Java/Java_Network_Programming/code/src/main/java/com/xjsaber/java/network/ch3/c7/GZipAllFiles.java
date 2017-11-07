package com.xjsaber.java.network.ch3.c7;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xjsaber on 2017/3/22.
 */
public class GZipAllFiles {

    public final static int THREAD_COUNT = 4;

    public static void main(String[] args){

        ExecutorService pool = Executors.newFixedThreadPool(THREAD_COUNT);

        List<String> list = new ArrayList<>();
        list.add("txt1.txt");
        list.add("txt2.txt");
        list.add("txt3.txt");
        for (String filename : list){
            File file = new File(filename);
            if (file.exists()){
                if (!file.isDirectory()){ //不递归处理目录
                    Runnable task = new GZipRunnable(file);
                    pool.submit(task);
                }
                else {
                    Runnable task = new GZipRunnable(file);
                    pool.submit(task);
                }
            }
        }
        pool.shutdown();
    }
}

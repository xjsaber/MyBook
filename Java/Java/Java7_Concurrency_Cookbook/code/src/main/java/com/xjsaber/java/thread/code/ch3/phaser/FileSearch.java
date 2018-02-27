package com.xjsaber.java.thread.code.ch3.phaser;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Phaser;

/**
 * 文件查找类
 * @author xjsaber
 */
public class FileSearch implements Runnable{

    private String initPath;
    private String end;
    private List<String>  results;
    private Phaser phaser;

    public FileSearch(String initPath, String end, Phaser phaser){
        this.initPath = initPath;
        this.end = end;
        this.phaser = phaser;
        this.results = new ArrayList<>();
    }

    @Override
    public void run() {

    }
}

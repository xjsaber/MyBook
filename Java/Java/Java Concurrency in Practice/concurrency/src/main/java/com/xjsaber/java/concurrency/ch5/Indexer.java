package com.xjsaber.java.concurrency.ch5;

import java.io.File;
import java.util.concurrent.BlockingQueue;

/**
 * @author xjsaber
 */
public class Indexer implements Runnable{

    private final BlockingQueue<File> queue;

    public Indexer(BlockingQueue<File> queue){
        this.queue = queue;
    }

    @Override
    public void run() {

    }
}

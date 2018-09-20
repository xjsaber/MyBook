package com.xjsaber.java.netty.guide.ch2.io;

import java.util.concurrent.*;

/**
 * @author xjsaber
 */
public class TimeServerHandlerExecutorPool {

    private ExecutorService executor;

    TimeServerHandlerExecutorPool(int maxPoolSize, int queueSize){
        executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), maxPoolSize, 120L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(queueSize));
    }

    public void executor(Runnable task){
        executor.execute(task);
    }
}

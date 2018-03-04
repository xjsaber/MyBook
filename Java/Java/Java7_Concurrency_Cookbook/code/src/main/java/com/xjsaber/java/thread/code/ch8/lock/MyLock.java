package com.xjsaber.java.thread.code.ch8.lock;

import java.util.Collection;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xjsaber
 */
public class MyLock extends ReentrantLock{

    /**
     * 获得当前锁的名称
     * @return 锁的名称
     */
    public String getOwnerName(){
        if (this.getOwner() == null){
            return "None";
        }
        return this.getOwner().getName();
    }

    /**
     * 返回正在等待的获取此锁的线程列表
     * @return
     */
    public Collection<Thread> getThreads(){
        return this.getQueuedThreads();
    }
}

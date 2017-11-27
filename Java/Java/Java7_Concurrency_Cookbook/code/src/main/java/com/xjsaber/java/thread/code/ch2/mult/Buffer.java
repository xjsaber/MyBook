package com.xjsaber.java.thread.code.ch2.mult;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xjsaber
 */
public class Buffer {

    /**
     * 用来存放共享数据
     */
    private LinkedList<String> buffer;
    /**
     * 存放buffer的长度
     */
    private int maxSize;
    /**
     * 用来修改buffer的代码块进行控制
     */
    private ReentrantLock lock;
    /**
     * Condition的lines
     */
    private Condition lines;
    /**
     * Condition的lines
     */
    private Condition space;
    /**
     * 用来表明缓冲区是否还有数据
     */
    private boolean pendingLines;

    public Buffer(int maxSize){
        this.maxSize = maxSize;
        this.buffer = new LinkedList<>();
        this.lock = new ReentrantLock();
        this.lines = lock.newCondition();
        this.space = lock.newCondition();
        pendingLines = true;
    }

    public void insert(String line){
        lock.lock();
        try {
            while (buffer.size() == maxSize){
                space.await();
            }
            buffer.offer(line);
            System.out.printf("%s: Inserted Line: %d\n", Thread.currentThread().getName(), buffer.size());
        } catch (InterruptedException e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public String get(){
        String line = null;
        lock.lock();
        try {
            while ((buffer.size() == 0) && (hasPendingLines())){
                lines.await();
            }
            if (hasPendingLines()){
                line = buffer.poll();
                System.out.printf("%s: Line Readed: %d\n", Thread.currentThread().getName(), buffer.size());
                space.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return line;
    }

    public void setPendingLines(boolean pendingLines){
        this.pendingLines = pendingLines;
    }

    public boolean hasPendingLines(){
        return pendingLines || buffer.size() > 0;
    }
}

package com.xjsaber.java.concurrency.ch14;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用显式条件变量的有界缓存
 * @author xjsaber
 */
@ThreadSafe
public class ConditionBoundedBuffer<T> {

    private final int BUFFER_SZIE = 1024;
    protected final Lock lock = new ReentrantLock();
    /**
     * 条件谓词：notFull (count < item.length)
     */
    private final Condition notFull = lock.newCondition();
    /**
     * 条件谓词：notEmpty（count > 0）
     */
    private final Condition notEmpty = lock.newCondition();
    @GuardedBy("lock")
    private final T[] items = (T[])new Object[BUFFER_SZIE];
    @GuardedBy("lock") private int head, tail, count;

    /**
     * 阻塞并直到：notFull
     * @param x item
     */
    public void put(T x) throws InterruptedException{
        lock.lock();
        try {
            while (count == items.length){
                notFull.await();
            }
            items[tail] = x;
            if (++tail == items.length){
                tail = 0;
            }
            ++count;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 阻塞并直到：notEmpty
     * @return T
     */
    public T take() throws InterruptedException{
        lock.lock();
        try {
            while (count == 0){
                notEmpty.await();
            }
            T x = items[head];
            items[head] = null;
            if (++head == items.length){
                head = 0;
            }
            --count;
            notFull.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }
}

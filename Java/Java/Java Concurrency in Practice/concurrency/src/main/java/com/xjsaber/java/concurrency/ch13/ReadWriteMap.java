package com.xjsaber.java.concurrency.ch13;

import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author xjsaber
 */
public class ReadWriteMap<K, V> {

    private final Map<K, V> map;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock r = lock.readLock();
    private final Lock w = lock.writeLock();

    public ReadWriteMap(Map<K, V> map){
        this.map = map;
    }

    public V put(K key, V value){
        w.lock();
        try {
            return map.put(key, value);
        } finally {
            w.unlock();
        }
    } //对remove()，putAll(),clear()等方法执行相同的操作

    public V get(K key){
        w.lock();
        try {
            return map.get(key);
        } finally {
            w.unlock();
        }
    }
    // 对其他只读的Map方法执行相同的操作
}

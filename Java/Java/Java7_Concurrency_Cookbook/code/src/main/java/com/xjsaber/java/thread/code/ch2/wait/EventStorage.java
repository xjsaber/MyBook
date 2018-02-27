package com.xjsaber.java.thread.code.ch2.wait;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author xjsaber
 */
public class EventStorage {

    private int maxSize;
    private List<Date> storage;

    EventStorage(){
        maxSize = 10;
        storage = new LinkedList<>();
    }

    public synchronized void set(){
        while (storage.size() == maxSize){
            try {
                wait();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        storage.add(new Date());
        System.out.printf("Set: %d \n", storage.size());
        notifyAll();
    }

    public synchronized void get(){
        if (storage.size() > 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (storage.size() == 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("Get: %d: %s \n", storage.size(), ((LinkedList<?>) storage).poll());
        notifyAll();
    }
}

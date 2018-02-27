package com.xjsaber.java.thread.code.ch2.wait;

/**
 * @author xjsaber
 */
public class Producer implements Runnable{

    private EventStorage storage;
    Producer(EventStorage storage){
        this.storage = storage;
    }

    @Override
    public void run() {
        int max = 100;
        for (int i = 0; i < max; i++){
            storage.set();
        }
    }
}

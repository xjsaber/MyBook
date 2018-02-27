package com.xjsaber.java.thread.code.ch2.wait;

/**
 * 消费者
 * @author xjsaber
 */
public class Consumer implements Runnable{

    private EventStorage storage;
    public Consumer(EventStorage storage){
        this.storage = storage;
    }

    @Override
    public void run() {
        int max = 100;
        for (int i = 0; i < max; i++){
            storage.get();
        }
    }
}

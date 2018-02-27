package com.xjsaber.java.thread.code.ch2.wait;

/**
 * @author xjsaber
 */
public class Main {

    public static void main(String[] args){
        EventStorage eventStorage = new EventStorage();
        Producer producer = new Producer(eventStorage);
        Thread threadProducer = new Thread(producer);
        Consumer consumer = new Consumer(eventStorage);
        Thread threadConsumer = new Thread(consumer);
        threadConsumer.start();
        threadProducer.start();
    }
}

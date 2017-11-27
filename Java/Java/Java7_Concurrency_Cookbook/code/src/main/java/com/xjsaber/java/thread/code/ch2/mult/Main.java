package com.xjsaber.java.thread.code.ch2.mult;

/**
 * @author xjsaber
 */
public class Main {

    public static void main(String[] args){
        FileMock mock = new FileMock(100, 10);
        Buffer buffer = new Buffer(20);
        Producer producer = new Producer(mock, buffer);
        Thread threadProducer = new Thread(producer, "producer");

        Consumer consumers[] = new Consumer[3];
        Thread threadConsumers[] = new Thread[3];
        for (int i = 0; i < 3; i++){
            consumers[i] = new Consumer(buffer);
            threadConsumers[i] = new Thread(consumers[i], "Consumers " + i);
        }
        threadProducer.start();
        for (int i = 0; i < 3; i++){
            threadConsumers[i].start();
        }
    }

}

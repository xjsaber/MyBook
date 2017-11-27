package com.xjsaber.java.thread.code.ch2.readwrite;

/**
 * @author xjsaber
 */
public class Main {
    public static void main(String[] args){
        PricesInfo pricesInfo = new PricesInfo();

        Reader readers[] = new Reader[5];
        Thread threadReader[] = new Thread[5];

        for (int i = 0; i < 5; i++){
            readers[i] = new Reader(pricesInfo);
            threadReader[i] = new Thread(readers[i]);
        }

        Writer writer = new Writer(pricesInfo);
        Thread threadWriter = new Thread(writer);

        for (int i = 0; i < 5; i++){
            threadReader[i].start();
        }
        threadWriter.start();
    }
}

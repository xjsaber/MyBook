package com.xjsaber.java.thread.code.ch6.block;

import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * @author xjsaber
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingDeque<String> list = new LinkedBlockingDeque<>();
        Client client = new Client(list);
        Thread thread = new Thread(client);
        thread.start();

        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 3; j++){
                String request = list.take();
                System.out.printf("Main: Request: %s at %s. Size: %d \n", request, new Date(), list.size());
            }
            TimeUnit.MILLISECONDS.sleep(300);
        }
        System.out.print("Main: End of the program.\n");
    }
}

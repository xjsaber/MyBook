package com.xjsaber.java.thread.code.ch1.load;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author xjsaber
 */
public class DataSourcesLoader implements Runnable {

    @Override
    public void run() {
        System.out.printf("Beginning data sources loading: %s\n", new Date());
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Data loading: %s\n", new Date());
    }
}

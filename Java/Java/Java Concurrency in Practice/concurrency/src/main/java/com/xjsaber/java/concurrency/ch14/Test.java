package com.xjsaber.java.concurrency.ch14;

public class Test {

    public static void main(String[] args){
        final ConditionBoundedBuffer<Integer> list = new ConditionBoundedBuffer<Integer>();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        list.put(1);
                        list.put(2);
                        list.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

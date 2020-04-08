package com.xjsaber.behind.design.isp.door.base;

public class Door implements TimerClient{

    void lock(){}
    void unlock(){}
    boolean isDoorOpen(){ return false; }

    public void timeout() {
        lock();
    }
}

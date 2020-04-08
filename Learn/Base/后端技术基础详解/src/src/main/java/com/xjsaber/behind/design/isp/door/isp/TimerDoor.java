package com.xjsaber.behind.design.isp.door.isp;

public class TimerDoor implements TimerClient, Door {

    public void timeout() {

    }

    public void lock() {

    }

    public void unlock() {

    }

    public boolean isDoorOpen() {
        return false;
    }
}

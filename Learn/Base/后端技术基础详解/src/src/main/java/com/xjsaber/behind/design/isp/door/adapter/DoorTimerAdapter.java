package com.xjsaber.behind.design.isp.door.adapter;

public class DoorTimerAdapter implements TimerClient{

    Door door = new Door();
    public void timeout() {
        door.lock();
    }
}

package com.xjsaber.behind.design.isp.door.base;

public class Timer {

    Door door = new Door();

    void register(int timeout, TimerClient client){
        door.timeout();
    }
}

package com.xjsaber.behind.design.isp.door.adapter;

public class Timer {

    DoorTimerAdapter doorTimerAdapter = new DoorTimerAdapter();

    void register(int timeout, TimerClient client){
        doorTimerAdapter.timeout();
    }
}

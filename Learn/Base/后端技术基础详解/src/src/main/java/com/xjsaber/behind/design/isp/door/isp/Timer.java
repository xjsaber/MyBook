package com.xjsaber.behind.design.isp.door.isp;

public class Timer {

    TimerDoor timerDoor = new TimerDoor();

    void register(int timeout, TimerClient client){
        timerDoor.timeout();
    }
}

package com.xjsaber.behind.design.dip;

/**
 * @author xjsaber
 */
public class Lamp implements ButtonServer {

    public void turnOn(){
        System.out.println("turnOn");
    }

    public void turnOff(){
        System.out.println("turnOff");
    }
}

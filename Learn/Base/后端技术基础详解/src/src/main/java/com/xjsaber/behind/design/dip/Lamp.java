package com.xjsaber.behind.design.dip;

import java.util.ArrayList;

/**
 * @author xjsaber
 */
public class Lamp implements ButtonServer {

    public void turnOn(){
        ArrayList<Integer> list = new ArrayList<Integer>();
        System.out.println("turnOn");
    }

    public void turnOff(){
        System.out.println("turnOff");
    }
}

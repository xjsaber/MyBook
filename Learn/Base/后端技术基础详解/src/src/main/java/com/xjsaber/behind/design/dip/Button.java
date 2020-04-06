package com.xjsaber.behind.design.dip;

/**
 * @author xjsaber
 */
public class Button {

    ButtonServer buttonServer;

    void poll(){
        buttonServer.turnOn();
    }
}

package com.xjsaber.java.concurrency.ch3;

import java.awt.*;
import java.util.EventListener;

/**
 * @author xjsaber
 */
public class SafeListener {

    private final EventListener listener;

    private SafeListener(){
        listener = new EventListener() {
            public void onEvent(Event e){
//                doSomething(e);
            }
        };
    }

//    public static SafeListener newInstance(EventSource source){
//        SafeListener safe = new SafeListener();
////        source.reg
//    }
}

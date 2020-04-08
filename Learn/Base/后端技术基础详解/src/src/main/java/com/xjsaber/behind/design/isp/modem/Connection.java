package com.xjsaber.behind.design.isp.modem;

public interface Connection {

    void dial(String pno);
    void hangup();
}

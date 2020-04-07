package com.xjsaber.behind.design.isp;

public interface Modem {

    void dial(String pno);
    void hangup();
    void send(char c);
    void recv();
}

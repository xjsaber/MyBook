package com.xjsaber.java.network.ch8;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * @author xjsaber
 */
public class Demo {

    public static void main(String[] args){
        try {
            Socket socket = new Socket();
            SocketAddress address = new InetSocketAddress("www.baidu.com", 80);
            socket.connect(address);
            // 使用socket...
            System.out.println("连接");
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}

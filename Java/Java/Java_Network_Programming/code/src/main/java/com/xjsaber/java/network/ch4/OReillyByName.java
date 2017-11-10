package com.xjsaber.java.network.ch4;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author xjsaber
 */
public class OReillyByName {

    public static void main(String[] args) {
        InetAddress address = null;
        try {
            address = InetAddress.getByName("www.baidu.com");
            System.out.println(address);
        } catch (UnknownHostException e) {
            System.out.println("Could not find www.baidu.com");
        }
    }
}

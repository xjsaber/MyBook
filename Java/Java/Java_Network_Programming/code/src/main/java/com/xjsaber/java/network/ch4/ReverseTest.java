package com.xjsaber.java.network.ch4;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author xjsaber
 */
public class ReverseTest {

    public static void main(String[] args){
        InetAddress ia;
        try {
            ia = InetAddress.getByName("119.75.216.20");
            System.out.println(ia.getHostName());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}

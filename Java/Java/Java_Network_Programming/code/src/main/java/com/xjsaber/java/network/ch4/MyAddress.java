package com.xjsaber.java.network.ch4;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author xjsaber
 */
public class MyAddress {

    public static void main(String[] args){
        try {
            InetAddress me = InetAddress.getLocalHost();
            System.out.println(me);
            String dottedOuad = me.getHostAddress();
            System.out.println("My address is " + dottedOuad);
        } catch (UnknownHostException e){
            System.out.println("I'm sorry, I don't know my own address.");
        }
    }
}

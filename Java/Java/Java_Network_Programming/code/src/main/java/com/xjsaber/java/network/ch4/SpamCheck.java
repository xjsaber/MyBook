package com.xjsaber.java.network.ch4;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author xjsaber
 */
public class SpamCheck {

    public static final String BLACKHOLE = "sbl.spamhaus.org";

    public static void main(String[] args){
        for (String arg: args){

        }
    }

    private static boolean isSpammer(String arg){
        try {
            InetAddress address = InetAddress.getByName(arg);
            byte[] quad = address.getAddress();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return true;
    }
}

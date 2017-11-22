package com.xjsaber.java.netty.guide.ch7;

import org.msgpack.MessagePack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xjsaber
 */
public class MsgpackDemo {

    public static void main(String[] args){
        List<String> src = new ArrayList<>();
        src.add("msgpack");
        src.add("kumofs");
        src.add("vivers");
        MessagePack msgpack = new MessagePack();
        // Serialize
        try {
            byte[] raw = msgpack.write(src);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

package com.xjsaber.java.io18.externalizable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @author xjsaber
 */
public class Blips {

    public static void main(String[] args) throws IOException {
        System.out.println("Constructing objects");
        Blip1 b1 = new Blip1();
        Blip2 b2 = new Blip2();
        ObjectOutputStream o = new ObjectOutputStream(
                new FileOutputStream("Blips.out"));
        System.out.println("Saving objects");
        o.writeObject(b1);
        o.writeObject(b2);
        o.close();
    }
}

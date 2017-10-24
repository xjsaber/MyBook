package com.xjsaber.java.io18.serialize;

import java.io.FileOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 * @author xjsaber
 */
public class FreezeAlien {
    public static void main(String[] args) throws Exception{
        ObjectOutput out = new ObjectOutputStream(
                new FileOutputStream("X.file"));
        Alien quellek = new Alien();
        out.writeObject(quellek);
    }
}

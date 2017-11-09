package com.xjsaber.java.network.ch3;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author xjsaber
 */
public class CallbackDigest implements Runnable {

    private String filename;

    public CallbackDigest(String filename){
        this.filename = filename;
    }

    @Override
    public void run() {
        try {
            FileInputStream in = new FileInputStream(filename);
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            DigestInputStream din = new DigestInputStream(in, sha);
            while (din.read() != -1) {}; // 读取整个文件
            din.close();
            byte[] digest = sha.digest();
//            CallbackDig
        } catch (IOException | NoSuchAlgorithmException ex){
            System.err.println(ex);
        }
    }
}

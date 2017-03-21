package com.self.network.thread;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by xjsaber on 2017/3/21.
 *
 */
public class ReturnDigest extends Thread {

    private String filename;
    private byte[] digest;

    public ReturnDigest(String filename){
        this.filename = filename;
    }

    @Override
    public void run(){
        try {
            FileInputStream in = new FileInputStream(filename);
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            DigestInputStream din = new DigestInputStream(in, sha);
            while (din.read() != -1);
            din.close();
            byte[] digest = sha.digest();
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
    }

    public byte[] getDigest(){
        return digest;
    }
}

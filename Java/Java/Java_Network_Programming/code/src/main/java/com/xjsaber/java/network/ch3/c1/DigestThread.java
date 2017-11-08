package com.xjsaber.java.network.ch3.c1;

import javax.xml.bind.DatatypeConverter;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xjsaber on 2017/3/21.
 *
 */
public class DigestThread extends Thread {

    private String filename;

    public DigestThread(String filename){
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

            StringBuilder result = new StringBuilder(filename);
            result.append(": ");
            result.append(DatatypeConverter.printHexBinary(digest));
            System.out.println(result);
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        List<String> list = new ArrayList<>();
        list.add("txt1.txt");
        list.add("txt2.txt");
        list.add("txt3.txt");
        for (String filename : list){
            Thread t = new DigestThread(filename);
            t.start();
        }
    }
}

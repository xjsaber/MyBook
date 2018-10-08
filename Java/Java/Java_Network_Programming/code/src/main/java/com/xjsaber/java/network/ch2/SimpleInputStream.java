package com.xjsaber.java.network.ch2;

import java.io.*;

/**
 * @author xjsaber
 */
public class SimpleInputStream {

    public static void main(String[] args){
        try {
            FileInputStream fin = new FileInputStream(SimpleInputStream.class.getResource("data.txt").getPath());
            BufferedInputStream bin = new BufferedInputStream(fin);

//            SimpleInputStream.getMacCyrillicString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String getMacCyrillicString(InputStream in) throws IOException {
        Reader r = new InputStreamReader(in, "MacCyrillic");
        r = new BufferedReader(r, 1024);
        StringBuilder sb = new StringBuilder();
        int c;
        while ((c = r.read()) != -1) {
            sb.append((char)c);
        }
        return sb.toString();
    }
}

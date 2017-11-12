package com.xjsaber.java.network.ch5;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 下载一个Web页面
 * @author xjsaber
 */
public class SourceViewer {

    public static void main(String[] args){

        if (args.length > 0) {
            InputStream in = null;
            try {
                // 打开URL进行读取
                URL u = new URL(args[0]);
                in = u.openStream();
                // 缓冲输入以提高性能
                in = new BufferedInputStream(in);
                // 讲InputStream串链到一个Reader
                Reader r = new InputStreamReader(in);
                int c;
                while ((c = r.read()) != -1) {
                    System.out.println((char)c);
                }
            } catch (MalformedURLException ex) {
                System.err.println(args[0] + " is not a parseable URL ");
            } catch (IOException ex) {
                System.err.println(ex);
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException ignored){
                        //忽略
                    }
                }
            }
        }
    }
}

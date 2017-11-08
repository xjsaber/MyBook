package com.xjsaber.java.network.ch3.c7;

import java.io.*;
import java.util.zip.GZIPOutputStream;

/**
 * Created by xjsaber on 2017/3/22.
 *
 */
public class GZipRunnable implements Runnable {

    private final File input;

    public GZipRunnable(File input){
        this.input = input;
    }

    @Override
    public void run() {
        //不压缩已经压缩的文件
        if (!input.getName().endsWith(".7z")){
            File output = new File(input.getParent(), input.getName() + ".7z");
            if (!output.exists()) { //不覆盖已经存在的文件
                try (//with resources
                     InputStream in = new BufferedInputStream(new FileInputStream(input));
                     OutputStream out = new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream(output)));
                        ) {
                    int b;
                    while ((b = in.read()) != -1) out.write(b);
                    out.flush();
                } catch (IOException ex) {
                    System.err.println(ex);
                }
            }
        }
    }
}

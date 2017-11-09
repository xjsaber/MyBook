package com.xjsaber.java.network.ch3;

import java.io.*;
import java.util.zip.GZIPOutputStream;

/**
 * @author xjsaber
 */
public class GZipRunnable implements Runnable{

    private final File input;

    public GZipRunnable(File input){
        this.input = input;
    }

    @Override
    public void run() {
        if (!input.getName().endsWith(".gz")){
            File output = new File(input.getParent(), input.getName() + ".gz");
            if (!output.exists()){
                try (InputStream in = new BufferedInputStream(new FileInputStream(input));
                    OutputStream out = new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream(output)))){
                    new FileInputStream(output);
                } catch(IOException ex) {
                    System.err.println(ex);
                }
            }
        }
    }
}

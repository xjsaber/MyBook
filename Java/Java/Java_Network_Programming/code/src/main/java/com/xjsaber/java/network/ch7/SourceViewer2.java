package com.xjsaber.java.network.ch7;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class SourceViewer2 {

    public static void main(String[] args) {
        try {
            URL u;
            if (args.length > 0){
                u = new URL(args[0]);
            }
            else {
                u = new URL("http://www.baidu.com");
            }
            URLConnection uc = u.openConnection();
            try (InputStream raw = uc.getInputStream()){
                Reader reader = new InputStreamReader(raw);
                int c;
                while ((c = reader.read()) != -1){
                    System.out.print((char)c);
                }
            }
        } catch (MalformedURLException ex){
            System.err.println(args[0] + " is not a parseable URL");
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}

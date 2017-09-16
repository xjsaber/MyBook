package com.xjsaber.java.tomcat;

import java.io.*;
import java.net.Socket;

/**
 * Created by xjsaber on 2017/4/4.
 *
 */
public class SocketUtils {

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("127.0.0.1", 8080);
        OutputStream os = socket.getOutputStream();
        PrintWriter out = new PrintWriter(
                os, true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        // send an HTTP request to the web server
        out.println("GET /index.jsp HTTP/1.1");
        out.println("Host: localhost:8080");
        out.println("Connection: Close");
        out.println();
        // read the response
        Boolean loop = true;
        StringBuilder sb = new StringBuilder();
        while (loop){
            if (in.ready()){
                int i = 0;
                while (i != -1){
                    i = in.read();
                    sb.append((char)i);
                }
                loop = false;
            }
            Thread.sleep(50);
        }
        //display the response to the out console
        System.out.println(sb.toString());
        socket.close();
    }
}

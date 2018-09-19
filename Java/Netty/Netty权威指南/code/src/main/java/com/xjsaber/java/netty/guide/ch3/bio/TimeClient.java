package com.xjsaber.java.netty.guide.ch3.bio;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TimeClient {

    public static void main(String[] args){
        int port = 8080;
        if (args != null && args.length > 0){
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException ignored){}
        }
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            Socket = new Socket("127.0.0.1", port);
            in = new BufferedReader(socket.getOutputStream(), true);

        }
    }
}

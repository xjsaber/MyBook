package com.xjsaber.nio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by xjsaber on 2017/4/9.
 * NIO的TimeServer
 */
public class TimeServer {

    public static void main(String[] args) throws IOException{
        int port = 8080;
        if (args != null && args.length > 0){
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException ex){
                port = 8080;
            }
        }
        MultiplexerTimeServer timeServer = null;
        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("The time server is start in port :" + port);
            Socket socket = null;
            TimeServerHandlerExecutePool signleExecutor = new TimeServerHandlerExecutePool(50, 1000);
            while (true){
                socket = server.accept();
                signleExecutor.execute(new TimeServerHandler(socket));
            }
        }finally {
            if (server != null){
                System.out.println("The time server close");
                server.close();
                server = null;
            }
        }
    }
}

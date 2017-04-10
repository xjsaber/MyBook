package com.xjsaber.netty.io;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * Created by xjsaber on 2017/4/10.
 * Blocking networking without Netty
 */
public class PlainOioServer {

    public void server(int port) throws Exception {
        // bind server to port
        final ServerSocket serverSocket = new ServerSocket(port);
        try{
            while (true) {
                // accept connection
                final Socket socket = serverSocket.accept();
                System.out.println("Accepted connection from " + socket);
                // create new thread to handle connection
                new Thread(
                        () -> {
                            OutputStream out;
                            try {
                                out = socket.getOutputStream();
                                // write message to connected client
                                out.write("Hi!\r\n".getBytes(Charset.forName("UTF-8")));
                                socket.close();
                            } catch (IOException e) {
                                try{
                                    socket.close();
                                } catch (IOException ioEx){
                                    ioEx.printStackTrace();
                                }
                            }
                        }).start();//start thread to begin handling
            }
        } catch (Exception ex){
            ex.printStackTrace();
            serverSocket.close();
        }
    }
}

package com.xjsaber.java.core.advanced.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author xjsaber
 */
public class ThreadedEchoServer {

    public static void main(String[] args){
        try {
            int i = 1;
            ServerSocket s = new ServerSocket(8196);
            while (true){
                Socket incoming = s.accept();
                System.out.println("Spawning " + i);
//                Runnable r = ne
            }
        }
        catch (Exception e){

        }
    }
}

/**
 * This class handles the client input for one server socket connection
 */
class ThreadEchoHandler implements Runnable{
    private Socket incoming;

    ThreadEchoHandler(Socket i){
        incoming = i;
    }

    @Override
    public void run() {
        try {
            try {
                InputStream inStream = incoming.getInputStream();
                OutputStream outStream = incoming.getOutputStream();
            }
            finally {
                incoming.close();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
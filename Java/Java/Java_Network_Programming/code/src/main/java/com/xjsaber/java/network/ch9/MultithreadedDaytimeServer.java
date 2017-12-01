package com.xjsaber.java.network.ch9;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * @author xjsaber
 */
public class MultithreadedDaytimeServer {

    public final static int PORT  = 13;

    public static void main(String[] args){
        try (ServerSocket server = new ServerSocket()) {
//            while (true){
//                try (Socket socket = server.accept()){
//                    Thread task = new DigestThread()
//                }
//            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class DaytimeThread extends Thread {
        private Socket connection;

        DaytimeThread(Socket connection){
            this.connection = connection;
        }

        @Override
        public void run(){
            try {
                Writer out = new OutputStreamWriter(connection.getOutputStream());
                Date now = new Date();
                out.write(now.toString() + "\r\n");
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (IOException ignored){}
            }
        }
    }
}

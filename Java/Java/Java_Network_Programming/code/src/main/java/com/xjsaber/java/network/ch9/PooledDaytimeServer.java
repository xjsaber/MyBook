package com.xjsaber.java.network.ch9;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xjsaber
 */
public class PooledDaytimeServer {

    public static final int PORT = 13;

    public static void main(String[] args){

        ExecutorService pool = Executors.newFixedThreadPool(50);

        try (ServerSocket server = new ServerSocket(PORT)) {
            while (true){
                Socket connection = server.accept();
//                Callable<Void> task = new DaytimeServer(connection);
                Callable<Void> task =null;
                pool.submit(task);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class DaytimeTask implements Callable<Void> {

        private Socket connection;

        DaytimeTask(Socket connection){
            this.connection = connection;
        }

        @Override
        public Void call() throws Exception {
            try {
                Writer out = new OutputStreamWriter(connection.getOutputStream());
                Date now = new Date();
                out.write(now.toString() + "\r\n");
                out.flush();

            } catch (IOException ex){
                System.err.println(ex.getMessage());
            } finally {
                try {
                    connection.close();
                } catch (IOException ignored){}
            }
            return null;
        }
    }
}

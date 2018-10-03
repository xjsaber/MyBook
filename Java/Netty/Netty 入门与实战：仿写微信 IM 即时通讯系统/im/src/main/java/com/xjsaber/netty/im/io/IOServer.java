package com.xjsaber.netty.im.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author xjsaber
 */
public class IOServer {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8080);

        // 接收新连接线程
        new Thread(() -> {
            try {
                // 阻塞方法获取新的连接
                Socket socket = serverSocket.accept();

                // 每一个新的连接都创建一个线程，负责读取数据
                new Thread(() -> {
                    try {
                        InputStream is = socket.getInputStream();
                        byte[] bytes = new byte [1024];
                        while (is.read(bytes) != -1){
                            System.out.println(new String(bytes, 0, 1024));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}

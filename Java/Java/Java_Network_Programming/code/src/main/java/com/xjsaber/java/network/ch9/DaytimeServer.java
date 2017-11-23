package com.xjsaber.java.network.ch9;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 *
 * 外部try块会捕获在daytime端口上构造ServerSocket对象server时可能产生的任何IOException。内部try块会捕获在daytime端口上构造ServerSocket对象server时可能产生的任何IOException。内部try块则监视接受和处理连接时可能抛出的异常。accept()方法在一个无限循环中调用来监视新连接。与很多服务器一样，这个程序不会终止，而是会继续监听，直到抛出一个异常或者你手动让它停止。
 * @author xjsaber
 * @date 2017/3/13
 */
public class DaytimeServer {
    private final static int PORT = 13;

    public static void main(String[] args){
        try(ServerSocket server = new ServerSocket(PORT)){
            while (true){
                try(Socket connection = server.accept()){
                    Writer out = new OutputStreamWriter(connection.getOutputStream());
                    Date now = new Date();
                    out.write(now.toString() + "\r\n");
                    out.flush();
                    connection.close();
                }catch (IOException ignored){}
            }
        }catch (IOException ex){
            System.err.println(ex);
        }
    }
}

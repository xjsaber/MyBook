package com.xjsaber.java.core.advanced.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

public class SocketTest {

    public static void main(String[] args){
        try (Socket socket = new Socket("www.baidu.com", 13)){
            InputStream inputStream = socket.getInputStream();
            Scanner in = new Scanner(inputStream);

            while (in.hasNextLine()){
                String line = in.nextLine();
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

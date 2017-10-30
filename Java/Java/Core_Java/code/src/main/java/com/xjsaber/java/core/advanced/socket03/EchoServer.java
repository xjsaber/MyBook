package com.xjsaber.java.core.advanced.socket03;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author xjsaber
 */
public class EchoServer {

    /**
     * This program implements a simple server that listens to port 8189 and echoes back all client
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException{
        try (ServerSocket s = new ServerSocket(8189)){
            try (Socket incoming = s.accept()){
                InputStream inStream = incoming.getInputStream();
                OutputStream outStream = incoming.getOutputStream();

                try (Scanner in = new Scanner(inStream)){
                    PrintWriter out = new PrintWriter(outStream, true);

                    out.println("Hello! Enter BYE to exit");

                    // echo client input
                    boolean done = false;
                    while (!done && in.hasNextLine()){
                        String line = in.nextLine();
                        out.println("Echo: " + line);
                        if ("BYE".equals(line.trim())) {
                            done = true;
                        }
                    }
                }
            }
        }
    }
}

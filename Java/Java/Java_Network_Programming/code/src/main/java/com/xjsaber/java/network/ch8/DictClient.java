package com.xjsaber.java.network.ch8;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class DictClient {

    private static final String SERVER = "dict.org";
    private static final int PORT = 2628;
    private static final int TIMEOUT = 15000;

    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        words.add("temp");
        words.add("word");
        words.add("poo");
        words.add("ee");
        words.add("qq");

        Socket socket = null;
        try {
            socket = new Socket(SERVER, PORT);
            socket.setSoTimeout(TIMEOUT);
            OutputStream out = socket.getOutputStream();
            Writer writer = new OutputStreamWriter(out, "UTF-8");
            writer = new BufferedWriter(writer);
            InputStream in = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            for (String word: words) {
                define(word, writer, reader);
            }
            writer.write("quit\r\n");
            writer.flush();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException ignored) { }
            }
        }
    }

    private static void define(String word, Writer writer, BufferedReader reader) throws IOException, UnsupportedEncodingException{
        writer.write("DEFINE eng-lat " + word + "\r\n");;
        writer.flush();

        for (String line = reader.readLine(); line != null; line = reader.readLine()) {
            if (line.startsWith("250 ")) { // OK
                return;
            }
            else if (line.startsWith("522 ")) { // 无匹配
                System.out.println("No definition found for " + word);
            }
            else if (line.matches("\\d\\d\\d .*")) {
                continue;
            }
            else if (line.trim().equals(".")) {
                continue;
            }
            else {
                System.out.println(line);
            }
        }
    }

}

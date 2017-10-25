package com.xjsaber.java.io18.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * @author xjsaber
 */
public class BufferToText {

    private static final int BSIZE = 1024;
    public static void main(String[] args) throws Exception{
        FileChannel fc = new FileOutputStream("data2.txt").getChannel();
        fc.write(ByteBuffer.wrap("Some text".getBytes()));
        fc.close();
        fc = new FileInputStream("data2.txt").getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
        fc.read(buffer);
        buffer.flip();
        // Doesn't work
        System.out.println(buffer.asCharBuffer());
        // Decode using this system's default Charset
        buffer.rewind();
        String encoding = System.getProperty("file.encoding");
        System.out.println("Decoded using " + encoding + ": " + Charset.forName(encoding).decode(buffer));
        // Or, we could encode with something that will print
        fc = new FileOutputStream("data2.txt").getChannel();
        fc.write(ByteBuffer.wrap("Some text".getBytes(Charset.forName("UTF-16BE"))));
        fc.close();
        //
        fc = new FileInputStream("data2.txt").getChannel();
        buffer.clear();
        fc.read(buffer);
        buffer.flip();
        System.out.println(buffer.asCharBuffer());
        // Use a CharBuffer to write through
        fc = new FileOutputStream("data2.txt").getChannel();
    }
}

package com.xjsaber.java.io18.homework;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;

/**
 * @author xjsaber
 * 创建并测试一个实用的方法，使其可以打印出CharBuffer中的内容
 */
public class Q18 {

    public static void main(String[] args) throws FileNotFoundException {
        int BSIZE = 1024;
        FileChannel fc = new FileInputStream("").getChannel();
        CharBuffer charBuffer;
//        fc.read(charBuffer)

    }
}

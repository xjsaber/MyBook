package com.xjsaber.java.thread.code.ch2.mult;

/**
 * @author xjsaber
 */
public class FileMock {

    /**
     * 存储文件内容
     */
    private String[] content;
    /**
     * 用来表示要从这个文件读取的内容的行号
     */
    private int index;

    /**
     * 构造器初始化文件的内容
     * @param size
     * @param length
     */
    FileMock(int size, int length){
        content = new String[size];
        for (int i = 0; i < size; i++){
            StringBuilder builder = new StringBuilder(length);
            for (int j = 0; j < length; j++){
                int indice = (int) (Math.random() * 255);
                builder.append((char)indice);
            }
            content[i] = builder.toString();
        }
        index = 0;
    }

    public boolean hasMoreLines(){
        return index < content.length;
    }

    public String getLine(){
        if (this.hasMoreLines()){
            System.out.println("Mock: " + (content.length - index));
            return content[index++];
        }
        return null;
    }
}

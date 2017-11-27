package com.xjsaber.java.thread.code.ch2.mult;

/**
 * @author xjsaber
 */
public class FileMock {

    private String content[];
    private int index;

    public FileMock(int size, int length){
        content = new String[size];
        for (int i = 0; i < size; i++){
            StringBuilder builder = new StringBuilder(length);
            for (int j = 0; j < length; j++){
                int indice = (int)Math.random() * 255;
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

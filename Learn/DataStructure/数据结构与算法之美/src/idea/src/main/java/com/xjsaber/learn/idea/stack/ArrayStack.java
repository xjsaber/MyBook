package com.xjsaber.learn.idea.stack;

public class ArrayStack {
    // 数组
    private String[] item;
    // 栈中元素个数
    private int count;
    // 栈的大小
    private int n;

    public ArrayStack(int n){
        this.item = new String[n];
        this.count = 0;
        this.n = n;
    }

    public boolean push(String value){
        // 栈满
        if (count == n) return false;
        this.item[count] = value;
        count++;
        return true;
    }

    public String pop(){
        // 栈空
        if (count == 0) return "";
        String value = this.item[count-1];
        count--;
        return value;
    }
}

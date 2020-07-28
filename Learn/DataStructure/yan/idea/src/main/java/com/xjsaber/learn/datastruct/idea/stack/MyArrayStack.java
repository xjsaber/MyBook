package com.xjsaber.learn.datastruct.idea.stack;

public class MyArrayStack {
    // 数组
    private String[] item;
    // top序号
    private int top;
    // 栈的大小
    private final int MAXSIZE = 50;

    public MyArrayStack(int n){
        this.item = new String[MAXSIZE];
        this.top = -1;
    }

    public boolean push(String value){
        // 栈满
        if (top == MAXSIZE - 1) return false;
        this.item[top] = value;
        top++;
        return true;
    }

    public String pop(){
        // 栈空
        if (top == -1) return "";
        String value = this.item[top];
        top--;
        return value;
    }

    public String getTop(MyArrayStack stack){
        if (stack.top == -1) return "";  //栈空，返回空字符串
        return stack.item[stack.top];  //栈顶数据
    }
}

package com.xjsaber.learn.datastruct.idea.stack;

public class MyArrayQueue {

    // 数组
    private String[] item;
    // 队头序号
    private int front;
    // 队尾序号
    private int rear;
    // 最大元素个数
    private final int MAXSIZE = 50;

    /**
     * 初始化
     * @param queue
     */
    public void initQueue(MyArrayQueue queue) {
        this.item = new String[MAXSIZE];
        this.front = 0;
        this.rear = 0;
    }

    /**
     * 判队列空
     * @param queue
     * @return
     */
    public boolean QueueEmpty(MyArrayQueue queue) {
        if (front == rear) return true;
        else return false;
    }

    /**
     * 入队
     * @param queue
     * @param value
     * @return
     */
    public boolean enQueue(MyArrayQueue queue, String value){
        if (front == MAXSIZE) return false;
        this.item[front] = value;
        front++;
        return true;
    }

    /**
     * 出队
     * @return
     */
    public String deQueue(){
        // 队空
        if (front == rear) return "";
        String value = this.item[rear];
        rear++;
        return value;
    }

    /**
     * 读队头元素
     * @return
     */
    public String getHead(){
        // 队空
        if (front == rear) return "";
        return this.item[rear];
    }
}

package com.xjsaber.java.thread.code.ch1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author xjsaber
 */
public class Calculator2 implements Runnable{

    private int number;

    public Calculator2(int number){
        this.number = number;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++){
            System.out.printf("%s: %d * %d = %d\n", Thread.currentThread().getName(), number, i, i*number);
        }
    }

    public static void main(String[] args){

        Thread threads[] = new Thread[10];
        Thread.State status[] = new Thread.State[10];

        for (int i = 0; i < 10; i++){
            threads[i] = new Thread(new Calculator2(i));
            if ((i % 2) == 0){
                threads[i].setPriority(Thread.MAX_PRIORITY);
            } else {
                threads[i].setPriority(Thread.MIN_PRIORITY);
            }
            threads[i].setName("Thread " + i);
        }

        try (FileWriter file = new FileWriter(".\\data\\log.txt");
             PrintWriter pw = new PrintWriter(file)){
            for (int i = 0; i < 10; i++){
                pw.println("Main: Status of Thread " + i + " : " + threads[i].getState());
                status[i] = threads[i].getState();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

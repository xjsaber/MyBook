package com.xjsaber.java.thread.code.ch2.bank;

/**
 * @author xjsaber
 */
public class Account {

    private double balance;


    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * 在同一时间只允许一个线程改变这个值，使用sync关键字将方法标记成临界值
     * @param amount
     */
    public synchronized void addAmount(double amount){
        double tmp = balance;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tmp += amount;
        balance = tmp;
    }

    public synchronized void subtractAmount(double amount){
        double tmp = balance;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tmp -= amount;
        balance = tmp;
    }
}

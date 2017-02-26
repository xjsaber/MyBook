package xjsaber.core_java.multithreaded.unsynch;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xjsaber on 2017/2/25.
 */
public class Bank {
    private final double[] accounts;
    private Lock bankLock = new ReentrantLock(); //ReentrantLock implements the lock interface

    public Bank(int n, double initialBalance){
        accounts = new double[n];
        for (int i = 0; i < accounts.length; i++){
            accounts[i] = initialBalance;
        }
    }

    //在多线程当中不安全
    public void transfer(int from, int to, double amount){
        bankLock.lock();
        try {
            System.out.println(Thread.currentThread());
            accounts[from] -= amount;
            System.out.printf(" %10.2f from %d to %d", amount, from, to);
            accounts[to] += amount;
            System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
        }
        finally {
            bankLock.unlock();
        }
    }

    /**
     * 获得总的余额
     * @return
     */
    private double getTotalBalance(){
        double sum = 0;
        for (double a: accounts){
            sum += a;
        }
        return sum;
    }

    /**
     *
     * @return
     */
    public int size() {
        return accounts.length;
    }
}

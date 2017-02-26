package xjsaber.core_java.multithreaded.synch;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xjsaber on 2017/2/25.
 */
class Bank {
    private final double[] accounts;
    private Lock bankLock = new ReentrantLock(); //ReentrantLock implements the lock interface
    private Condition sufficientFunds;

    Bank(int n, double initialBalance){
        accounts = new double[n];
        for (int i = 0; i < accounts.length; i++){
            accounts[i] = initialBalance;
        }
        bankLock = new ReentrantLock();
        sufficientFunds = bankLock.newCondition();
    }

    //在多线程当中不安全
    void transfer(int from, int to, double amount){
        bankLock.lock();
        try {
            while (accounts[from] < amount){
                sufficientFunds.await();
            }
            System.out.println(Thread.currentThread());
            accounts[from] -= amount;
            System.out.printf(" %10.2f from %d to %d", amount, from, to);
            accounts[to] += amount;
            System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
            sufficientFunds.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bankLock.unlock();
        }
    }

    /**
     * 获得总的余额
     * @return
     */
    private double getTotalBalance(){
        bankLock.lock();
        try {
            double sum = 0;
            for (double a : accounts) {
                sum += a;
            }
            return sum;
        }
        finally {
            bankLock.unlock();
        }
    }

    /**
     *
     * @return
     */
    public int size() {
        return accounts.length;
    }
}

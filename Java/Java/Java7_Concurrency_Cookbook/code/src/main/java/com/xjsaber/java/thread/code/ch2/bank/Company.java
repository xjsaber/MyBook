package com.xjsaber.java.thread.code.ch2.bank;

/**
 * @author xjsaber
 */
public class Company implements Runnable{

    private Account account;

    Company(Account account){
        this.account = account;
    }

    @Override
    public void run() {
        int max = 100;
        for (int i = 0; i < max; i++){
            account.addAmount(1000);
            System.out.printf("Account: %s \n", account.getBalance());
        }
    }
}

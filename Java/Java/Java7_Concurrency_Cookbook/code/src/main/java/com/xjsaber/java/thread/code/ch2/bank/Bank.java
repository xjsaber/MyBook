package com.xjsaber.java.thread.code.ch2.bank;

/**
 * @author xjsaber
 */
public class Bank implements Runnable {

    private Account account;

    Bank(Account account){
        this.account = account;
    }

    @Override
    public void run() {
        int max = 100;
        for (int i = 0; i < max; i++){
            account.subtractAmount(1000);
            System.out.printf("Account: %s \n", account.getBalance());
        }
    }
}

package com.xjsaber.learn.java.ch6;

/**
 * @author xjsaber
 */
public class Account {
    private int balance;
    /**
     * @param target 账户
     * @param amt 金额
     */
    void transfer(Account target, int amt){
        // 锁定转出账户
        synchronized (this){
            // 锁定转入账户
            if (this.balance > amt){
                this.balance -= amt;
                target.balance += amt;
            }
        }
    }
}

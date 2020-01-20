package main.java.com.xjsaber.learn.java.ch6;

/**
 * @author xjsaber
 */
public class Account {
    private int balance = 2000;

    int getBalance(){
        return balance;
    }

    /**
     * @param target 账户
     * @param amt 金额
     */
    void transfer(Account target, int amt){
        // 锁定转出账户
        synchronized (this){
            // 锁定转入账户
            synchronized (target) {
                if (this.balance > amt) {
                    this.balance -= amt;
                    target.balance += amt;
                }
            }
        }
    }
}

package main.java.com.xjsaber.learn.java.ch6.als;

public class Account {
    // actr应该为单例
    private Allocator actr;
    private int balance;

    void transfer(Account target, int amt){
        while (!actr.apply(this, target))
            ;
        try {
            //锁定转出账户
            synchronized (this){
                //锁定转入账户
                synchronized (target){
                    if (this.balance > amt){
                        this.balance -= amt;
                        target.balance += amt;
                    }
                }
            }
        } finally {
            actr.free(this, target);
        }
    }
}

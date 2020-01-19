package main.java.com.xjsaber.learn.java.ch4;

public class Account {

    /**
     * 锁：保护账户余额
     */
    private final Object balLock = new Object();
    /**
     * 账户余额
     */
    private Integer balance;
    /**
     * 锁：保护账户密码
     */
    private final Object pwdLock = new Object();
    /**
     * 账户密码
     */
    private String password;

    /**
     * 取款
     * @param amt 金额
     */
    void withdraw(Integer amt){
        synchronized (balLock){
            if (this.balance > amt){
                this.balance -= amt;
            }
        }
    }

    Integer getBalance(){
        synchronized (balLock){
            return balance;
        }
    }

    void updatePassword(String password){
        synchronized (pwdLock) {
            this.password = password;
        }
    }

    String getPassword(){
        synchronized (pwdLock) {
            return password;
        }
    }
}

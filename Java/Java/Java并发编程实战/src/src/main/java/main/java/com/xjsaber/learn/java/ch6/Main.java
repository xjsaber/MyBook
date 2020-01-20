package main.java.com.xjsaber.learn.java.ch6;

public class Main {

    public static void main(String[] args) {
        Account accountAAAA = new Account();
        Account accountBBBB = new Account();

        new Thread(() -> {
            accountAAAA.transfer(accountBBBB, 100);
            System.out.println("AAAA:" + accountAAAA.getBalance());
            System.out.println("BBBB:" + accountBBBB.getBalance());
        }).start();

        new Thread(() -> {
            accountBBBB.transfer(accountAAAA, 100);
            System.out.println("AAAA:" + accountAAAA.getBalance());
            System.out.println("BBBB:" + accountBBBB.getBalance());
        }).start();
    }
}

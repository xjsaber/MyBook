package xjsaber.core_java.multithreaded.synch2;

/**
 * Created by xjsaber on 2017/2/25.
 */
public class TransferRunnable implements Runnable {
    private Bank bank;
    private int fromAccount;
    private double maxAmount;

    public TransferRunnable(Bank b, int from, double max){
        bank = b;
        fromAccount = from;
        maxAmount = max;
    }

    @Override
    public void run() {
        try {
            int toAccount = (int)(bank.size() * Math.random());
            double amount = maxAmount * Math.random();
            bank.transfer(fromAccount, toAccount, amount);
            int DELAY = 10;
            Thread.sleep((int)(DELAY * Math.random()));

        }
        catch (InterruptedException ignored){}
    }
}

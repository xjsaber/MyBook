package xjsaber.core_java.multithreaded.unsynch;

/**
 * Created by xjsaber on 2017/2/25.
 */
public class UnsynchBankTest {
    private static final int NACCOUNTS = 100;
    private static final double INTTIAL_BALANCE = 1000;

    public static void main(String[] args){
        Bank bank = new Bank(NACCOUNTS, INTTIAL_BALANCE);
        int i;
        for (i = 0; i < NACCOUNTS; i++) {
            TransferRunnable r = new TransferRunnable(bank, i, INTTIAL_BALANCE);
            Thread thread = new Thread(r);
            thread.start();
        }
    }
}

package main.java.com.xjsaber.learn.java.ch3;

/**
 * @author xjsaber
 */
public class Main {

    public static void main(String[] args) {
        SafeCalc safeCalc = new SafeCalc();
        for (int i = 0; i < 10; i++) {
            System.out.println("index:" + i);
            Thread thread = new Thread(()-> {
                SafeCalc.addOne();
                System.out.println(safeCalc.get());
            });
            thread.start();
        }
    }
}

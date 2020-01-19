package main.java.com.xjsaber.learn.java.base;

/**
 * @author xjsaber
 */
public class VolatileExample {

    private long count = 0;
    private void add10K(){
        int idx = 0;
        while (idx++ < 10000){
            count += 1;
        }
    }

    public static long calc() throws InterruptedException {
        final VolatileExample test = new VolatileExample();
        Thread th1 = new Thread(test::add10K);
        Thread th2 = new Thread(test::add10K);
        th1.start();
        th2.start();
        th1.join();
        th2.join();
        return 0;
//        return count;
    }



    public static void main(String[] args) {
        VolatileExample example = new VolatileExample();
//        ex6
//        example.getEx06();
//        ex1
//        for (int i = 0; i < 10; i++) {
//            System.out.println(i);
//            new Thread(() -> {
//                example.writer();
//                example.reader();
//            }).start();
//        }
    }

    private int x = 0;
    private volatile boolean v = false;
    private void writer(){
        x = 42;
        v = true;
    }
    private void reader(){
        if (v){
            System.out.println("x:" + x);
        }
    }

    private int temp = 1;
    private void getEx06(){
        Thread f = new Thread(()->{
            System.out.println("1:" + temp);
        });
        System.out.println("2:" + temp);
        temp = 3;
        f.start();
        temp = 4;
        System.out.println("3:" + temp);
        temp = 5;
    }
    private void getEx07(){
        Thread g = new Thread(()->{
            temp = 2;
            System.out.println("1:" + temp);
        });
        System.out.println("2:" + temp);
        temp = 3;
        g.start();
        try {
            g.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("3:" + temp);
    }
}

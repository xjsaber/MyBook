package main.java.com.xjsaber.learn.java;

public class Test {

    /**
     * write your code here
     */
    private static int x = 0;
    private static volatile boolean v = false;

    public static void main(String[] args) {
        writer();
        reader();
    }

    private static void writer() {
        x = 42;
        v = true;
    }

    private static void reader() {
        if (v){
            System.out.println(x);
            // x = ?
        }
    }
}

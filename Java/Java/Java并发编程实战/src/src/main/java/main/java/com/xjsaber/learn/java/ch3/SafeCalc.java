package main.java.com.xjsaber.learn.java.ch3;

/**
 * @author xjsaber
 */
public class SafeCalc {

//    long value = 0L;
//    long get(){
//        synchronized (new Object()){
//            return value;
//        }
//    }
//    void addOne(){
//        synchronized (new Object()){
//            value += 1;
//        }
//    }

    static long value = 0L;
    synchronized long get() {
        return value;
    }
    synchronized static void addOne() {
        value += 1;
    }
}

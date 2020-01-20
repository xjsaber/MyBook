package main.java.com.xjsaber.learn.java.base;

/**
 * @author xjsaber
 */
public class Singleton {

    static Singleton instance;
    static Singleton getInstance(){
        if (instance == null){
            synchronized (Singleton.class){
                if (instance == null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}

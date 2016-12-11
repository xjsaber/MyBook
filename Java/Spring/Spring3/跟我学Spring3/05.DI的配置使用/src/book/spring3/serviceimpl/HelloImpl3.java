package book.spring3.serviceimpl;

import book.spring3.servcie.HelloApi;

/**
 * Created by xjsaber on 3/15/2016.
 */
public class HelloImpl3 implements HelloApi {
    private String message;
    private int index;
    public HelloImpl3(String message, int index){
        this.message = message;
        this.index = index;
    }

    @Override
    public void sayHello() {
        System.out.println(index + ":" + message);
    }
}

package book.spring3.chapter3.serviceimpl;

import book.spring3.chapter3.servcie.HelloApi;

/**
 * Created by xjsaber on 3/14/2016.
 */
public class HelloImpl implements HelloApi {

    @Override
    public void sayHello() {
        System.out.println("Hello World!");
    }
}

package com.xjsaber.java.generator15.generics;

public class Holder3<T> {

    private T t;
    public Holder3(T t){this.t = t;}
    public void set(T t){this.t = t;}
    public T get() {return t;}
    public static void main(String[] args) {
        Holder3<Automobile> h3 = new Holder3<>(new Automobile());
        Automobile a = h3.get();//No cast needed
        // h3.set("No an Automobile");//Error
        // h3.set(1); //Error
    }
}

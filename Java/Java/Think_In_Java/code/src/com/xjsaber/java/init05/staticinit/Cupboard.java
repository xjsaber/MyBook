package com.xjsaber.java.init05.staticinit;

public class Cupboard {
    Bowl bowl3 = new Bowl(3);
    private static Bowl bowl4 = new Bowl(4);
    Cupboard() {
        System.out.println("Cupboard()");
        bowl4.f1(2);
    }
    void f3(int marker) {
        System.out.println("f3(" + marker + ")");
    }
    private static Bowl bowl5 = new Bowl(5);
}

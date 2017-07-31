package com.xjsaber.java.init.staticinit;

public class StaticInitialization {

    public static void main(String[] args){
        System.out.println("Create new Cupboard() in main");
        new Cupboard();
        System.out.println("Create new Cupboard() in main");
        new Cupboard();
        table.f2(1);
        cupboard.f3(1);
    }
    private static Table table = new Table();
    private static Cupboard cupboard = new Cupboard();
}

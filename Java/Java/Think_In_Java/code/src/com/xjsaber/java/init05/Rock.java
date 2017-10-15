package com.xjsaber.java.init05;

public class Rock {

    private String temp;

    public static void main(String[] args) {
        Init init = new Init();
        System.out.println(init.temp);
    }

    static class Init {
        public String temp;

       Init() {
//            temp = null;
       }
    }
}

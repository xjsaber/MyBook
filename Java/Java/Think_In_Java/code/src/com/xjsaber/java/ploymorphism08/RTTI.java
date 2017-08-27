package com.xjsaber.java.ploymorphism08;

class Useful{
    public void f(){System.out.println(1);}
    public void g(){System.out.println(2);}
}

class MoreUseful extends Useful{
    public void f() {System.out.println(11);}
    public void g() {System.out.println(12);}
    public void h() {System.out.println(13);}
}

public class RTTI {
    public static void main(String[] args){
        Useful[] x = {
                new Useful(),
                new MoreUseful()
        };
        x[0].f();
        x[1].g();
        // Compile time: method not found in Useful:
        // ! x[1].u();
        // (MoreUseful)x[1].u();
        // (MoreUseful)x[0].u();
    }
}

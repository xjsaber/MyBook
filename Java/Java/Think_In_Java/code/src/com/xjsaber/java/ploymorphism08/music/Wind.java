package com.xjsaber.java.ploymorphism08.music;

public class Wind extends Instrument  {

    // Redefine interface method:
    public void play(Note n){
        System.out.println("Wind.play() " + n);
    }
}

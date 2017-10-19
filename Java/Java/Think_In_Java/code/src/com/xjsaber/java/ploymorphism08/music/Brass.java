package com.xjsaber.java.ploymorphism08.music;

import static com.xjsaber.java.utils.Print.print;

public class Brass extends Instrument{

    public void play(Note n){
        print("Brass.play() " + n);
    }
}

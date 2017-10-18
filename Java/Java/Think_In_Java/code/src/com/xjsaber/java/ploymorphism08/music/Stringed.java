package com.xjsaber.java.ploymorphism08.music;

import static com.xjsaber.java.utils.Print.print;

public class Stringed extends Instrument {

    public void play(Note n){
        print("Stringed.play() " + n);
    }
}

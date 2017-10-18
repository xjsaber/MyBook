package com.xjsaber.java.ploymorphism08.music;

public class Music {

    private static void tune(Instrument i){
        i.play(Note.MIDDLE_C);
    }

    public static void main(String[] args){
        Wind flute = new Wind();
        tune(flute);
    }
}

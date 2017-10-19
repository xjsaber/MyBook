package com.xjsaber.java.ploymorphism08.music;

public class Music2 {

    private static void tune(Wind i){
        i.play(Note.MIDDLE_C);
    }
    private static void tune(Stringed i){
        i.play(Note.B_LEAF);
    }
    private static void tune(Brass i){
        i.play(Note.C_SHARP);
    }

    public static void main(String[] args){
        Wind flute = new Wind();
        Stringed violin = new Stringed();
        Brass frenchHorn = new Brass();
        tune(flute);
        tune(violin);
        tune(frenchHorn);
    }
}

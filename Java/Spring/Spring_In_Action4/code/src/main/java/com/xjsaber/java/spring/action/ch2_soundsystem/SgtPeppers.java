package com.xjsaber.java.spring.action.ch2_soundsystem;

import org.springframework.stereotype.Component;

/**
 * Created by xjsaber on 2016/7/6.
 */
@Component
public class SgtPeppers implements CompackDisc{

    private String title = "Sgt. Peppers's Lonely Hears Club Band";
    private String artist = "The Beatles";

    public void play(){
        System.out.println("playing " + title + " by " + artist);
    }
}

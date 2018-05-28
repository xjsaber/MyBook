package com.xjsaber.java.java8_in_action.ch5;

public class Trader {

    private final String name;
    private final String city;

    public Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }


    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }
}

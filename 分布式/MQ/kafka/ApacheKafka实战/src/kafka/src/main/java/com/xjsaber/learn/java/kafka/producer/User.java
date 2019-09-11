package com.xjsaber.learn.java.kafka.producer;

import lombok.Data;

@Data
public class User {

    private String firstName;
    private String lastName;
    private int age;
    private String address;

    @Override
    public String toString(){
        return "User{" +
                "firstName = '" + firstName + '\'' +
                ", lastName = '" + lastName + '\'' +
                ", age = " + age +
                ", address = '" + address + '\'' +
                "}";
    }
}

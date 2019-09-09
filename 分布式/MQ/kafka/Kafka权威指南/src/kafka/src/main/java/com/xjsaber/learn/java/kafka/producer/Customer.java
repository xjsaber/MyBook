package com.xjsaber.learn.java.kafka.producer;

public class Customer {

    private int customerID;
    private String customerName;

    public Customer(int ID, String name){
        this.customerID = ID;
        this.customerName = name;
    }

    public int getID(){
        return this.customerID;
    }

    public String getName(){
        return this.customerName;
    }

    public void setID(int ID){
        this.customerID = ID;
    }

    public void setName(String name){
        this.customerName = name;
    }
}

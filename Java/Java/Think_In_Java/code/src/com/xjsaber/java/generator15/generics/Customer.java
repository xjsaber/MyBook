package com.xjsaber.java.generator15.generics;

import com.xjsaber.java.generator15.util.Generator;

public class Customer {

    private static long counter = 1;
    private final long id = counter++;

    private Customer(){}
    public String toString() {
        return "Customer" + id;
    }

    // A method to produce Generator objects:
    public static Generator<Customer> generator() {
        return new Generator<Customer>() {
            @Override
            public Customer next() {
                return new Customer();
            }
        };
    }
}

package com.self;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by xjsaber on 2016/12/3.
 */
public class Employee {
    // instance fields
    private String name;
    private double salary;
    private Date hireDay;

    // constructor
    public Employee(String n, double s, int year, int month, int day)
    {
        this.name = n;
        this.salary = s;
        GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
        this.hireDay = calendar.getTime();
    }

    // a.method
    public String getName()
    {
        return this.name;
    }
}

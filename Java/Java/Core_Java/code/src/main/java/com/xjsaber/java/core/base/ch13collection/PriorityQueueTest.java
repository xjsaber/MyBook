package com.xjsaber.java.core.base.ch13collection;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.PriorityQueue;

public class PriorityQueueTest {

    public static void main(String[] args){
        PriorityQueue<GregorianCalendar> pq = new PriorityQueue<>();
        pq.add(new GregorianCalendar(1906, Calendar.DECEMBER, 9)); // G
        pq.add(new GregorianCalendar(1815, Calendar.DECEMBER, 10)); // G
        pq.add(new GregorianCalendar(1903, Calendar.DECEMBER, 3)); // G
        pq.add(new GregorianCalendar(1910, Calendar.DECEMBER, 22)); // G

        System.out.println("");
        for (GregorianCalendar date: pq)
            System.out.println(date.get(Calendar.YEAR));
        System.out.println("Removing elements");
        while (!pq.isEmpty())
            System.out.println(pq.remove().get(Calendar.YEAR));
    }
}

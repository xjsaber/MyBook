package com.xjsaber.learn.idea.stack;

import java.util.LinkedList;

public class LinkedStack<Elem> {

    private Node first;
    private int N;

    private class Node{
        Elem item;
        Node next;
    }

    public LinkedStack() {
        first = new Node();
        N = 0;
    }

    public boolean push(Elem value){
        Node node = new Node();
        node.item = value;
        while(first.next != null){
            first = first.next;
            N++;
        }
        first.next = node;
        return true;
    }

    public Elem pop(){
        while(first.next != null){
            first = first.next;
        }
        Elem value = first.next.item;
        first.next = null;
        N--;
        return value;
    }
}

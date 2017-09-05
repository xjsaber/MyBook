package com.xjsaber.book.java.base.ch5;

import java.util.Arrays;
import java.util.EmptyStackException;

public class StackT<T> {

    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public StackT() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(T e){
        ensureCapacity();
        elements[size++] = e;
    }

    public T pop(){
        if (size == 0) {
            throw new EmptyStackException();
        }
        @SuppressWarnings("unchecked")
        T result = (T)elements[--size];
        elements[size] = null;
        return result;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    private void ensureCapacity(){
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }
}

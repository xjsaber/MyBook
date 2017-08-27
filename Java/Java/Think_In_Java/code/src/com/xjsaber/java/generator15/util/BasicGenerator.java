package com.xjsaber.java.generator15.util;

public class BasicGenerator<T> implements Generator<T> {

    private Class<T> type;
    private BasicGenerator(Class<T> type){this.type = type;}
    @Override
    public T next() {
        // Assumes type is a public class
        try {
            return type.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static <T> Generator<T> create(Class<T> type){
        return new BasicGenerator<T>(type);
    }
}

package com.xjsaber.java.collection17;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class Maps {

    public static void printKeys(Map<Integer, String> map){
        System.out.println("Size: " + map.size());
        System.out.println("Keys: ");
        System.out.print(map.keySet());
    }

    public void test(Map<Integer, String> map){
        System.out.println(map.getClass().getSimpleName());
//        map.putAll(new (25));
//        map.putAll(new Cou);
    }

    public static void main(String[] args){
//        Map<String, String> map = new HashMap<>();
//        map.put("1", "1");
//        map.put("2", "2");
//        map.put("3", "3");

        Collection<String> collection = new ArrayList<>();
        collection.add("1");
        collection.add("2");
        collection.add("3");
        System.out.println(collection.contains("1"));
        System.out.println(collection.containsAll(new ArrayList<>()));
    }
}

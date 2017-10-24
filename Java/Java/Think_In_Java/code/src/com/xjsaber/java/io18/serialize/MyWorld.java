package com.xjsaber.java.io18.serialize;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xjsaber
 */
public class MyWorld {

    public static void main(String[] args) throws IOException, ClassNotFoundException{
        House house = new House();
        List<Animal> animalList = new ArrayList<>();
        animalList.add(new Animal("Bosco the dog", house));
        animalList.add(new Animal("Ralph the hamster", house));
        animalList.add(new Animal("Molly the cat", house));
        System.out.println("animals: " + animalList);
        ByteArrayOutputStream buf1 = new ByteArrayOutputStream();
        ObjectOutputStream o1 = new ObjectOutputStream(buf1);
        o1.writeObject(animalList);
        // Write a 2nd set
        o1.writeObject(animalList);
        // Write to a different stream
        ByteArrayOutputStream buf2 = new ByteArrayOutputStream();
        ObjectOutputStream o2 = new ObjectOutputStream(buf2);
        o2.writeObject(animalList);
        // Now get them back
        ObjectInputStream in1 = new ObjectInputStream(new ByteArrayInputStream(buf1.toByteArray()));
        ObjectInputStream in2 = new ObjectInputStream(new ByteArrayInputStream(buf2.toByteArray()));
        List animals1 = (List)in1.readObject();
        List animals2 = (List)in1.readObject();
        List animals3 = (List)in2.readObject();
        System.out.println("animal1: " + animals1);
        System.out.println("animal2: " + animals2);
        System.out.println("animal3: " + animals3);
    }
}

class House implements Serializable {}
class Animal implements Serializable {
    private String name;
    private House preferredHouse;
    Animal(String nm, House h){
        name = nm;
        preferredHouse = h;
    }
    @Override
    public String toString(){
        return name + "[" + super.toString() + "]." + preferredHouse + "\n";
    }
}
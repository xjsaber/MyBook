package com.xjsaber.java.thread.code.ch5.cancel;

import java.util.Random;

/**
 * @author xjsaber
 */
public class ArrayGenerator {

    public int[] generateArray(int size){
        int[] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++){
            array[i] = random.nextInt(10);
        }
        return array;
    }
}

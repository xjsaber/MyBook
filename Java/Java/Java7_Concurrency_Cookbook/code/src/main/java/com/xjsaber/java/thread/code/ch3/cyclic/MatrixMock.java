package com.xjsaber.java.thread.code.ch3.cyclic;

import java.util.Random;

/**
 * @author xjsaber
 */
public class MatrixMock {

    private int[][] data;

    public MatrixMock(int size, int length, int number){
        int counter = 0;
        data = new int[size][length];
        Random random = new Random();

        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                data[i][j] = random.nextInt(10);
                if (data[i][j] == number){
                    counter++;
                }
            }
        }

        System.out.printf("Mock: There are %d ocurrences of number in generated data. \n", counter, number);
    }
}

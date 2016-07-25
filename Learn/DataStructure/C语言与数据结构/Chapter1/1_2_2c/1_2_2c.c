//
// Created by xjsaber on 2016/7/23.
//
/* ======================================*/


#include <stdio.h>

/* 程序实例：1_2_2c.c*/

void main()
{
    struct score
    {
        int math;
        int english;
        int computer;
    };
    struct score chen;

    chen.math = 80;
    chen.english = 81;
    chen.computer = 82;

    printf("1: name math english computer \n");
    printf("2: Chen %d %d %d \n", chen.math, chen.english, chen.computer);
}
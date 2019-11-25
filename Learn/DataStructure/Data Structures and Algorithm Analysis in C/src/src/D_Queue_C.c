//
// Created by xjsaber on 2019/11/19.
//
#include <stdio.h>
#include <stdlib.h>
#include "../include/D_Queue_H.h"

struct QueueRecord
{
    int capacity;
    int front;
    int rear;
    int size;
    ElementType *array;
};

/* Return true if l is empty */
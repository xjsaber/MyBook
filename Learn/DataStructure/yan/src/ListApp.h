#pragma once
#define maxSize 100

typedef struct SqlList
{
    int data[maxSize];
    int length;
} SqlList;

SqlList createList(int length);

int findElem(SqlList l, int item);
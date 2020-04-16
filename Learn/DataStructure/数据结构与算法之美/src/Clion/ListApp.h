#pragma once
#define maxSize 100

struct SqlList
{
    int data[maxSize];
    int length;
};

SqlList createList(int length);

int findElem(SqlList l, int item);
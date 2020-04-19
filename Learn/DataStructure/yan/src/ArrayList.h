#pragma once
#define maxSize 50
#define ElemType int

typedef struct SqlList{
    ElemType data[maxSize];
    int length;
} SqlList;

typedef struct SeqList{
    ElemType *data;
    int length;
} SeqList;

SqlList createList(int length);

int findElem(SqlList l, int item);

bool insert(SqlList &l, int i, ElemType e);

bool remove();
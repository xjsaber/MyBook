#pragma once
#include "Base.h"

typedef struct SqlList{
    ElemType data[MAXSIZE];
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
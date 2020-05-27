#include <cstdlib>
#include "../Base.h"
#include "SqList.h"
//
// Created by xjsaber on 2020/5/21.
//

Status InitList(SqList &L)
{
    L.elem = new ElemType[MAXSIZE];
    if (!L.elem) exit(OVERFLOW);
    L.length = 0;
    return OK;
}

Status GetElem(SqList L, int i, ElemType &e)
{
    if (i<1 || i>L.length) return ERROR;
    e = L.elem[i-1];
    return OK;
}

int LocateElem(SqList L, ElemType e)
{
    for (int i = 0; i < L.length; i++){
        if (L.elem[i] == e){
            return i + 1;
        }
    }
    return 0;
}

Status ListInsert(SqList &L, int i, ElemType e)
{
    if (i<1 || i>L.length) return ERROR;
    if (L.length == MAXSIZE) return ERROR;
    for (int j = L.length - 1; j > i-1; j--)
    {
        L.elem[j+1] = L.elem[j];
    }
    L.elem[i-1] = e;
    L.length++;
    return OK;
}

Status ListDelete(SqList &L, int i)
{
    if (i<1 || i>L.length) return ERROR;
    if (L.length == 0) return ERROR;
    for (int j = i; j < L.length; j++){
        L.elem[j - 1] = L.elem[j];
    }
    L.length--;
    return OK;
}

void MergeList(SqList &LA, SqList LB)
{
    int m = LA.length;
    int n = LB.length;
    for (int i = 0; i < n; i++)
    {
        ElemType e;
        GetElem(LB, i, e);
        if (!LocateElem(LA, e))
        {
            ListInsert(LA, ++m, e);
        }
    }
}



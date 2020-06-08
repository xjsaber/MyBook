#include <cstdlib>
#include "../Base.h"
#include "SqList.h"
//
// Created by xjsaber on 2020/5/21.
//

void CreateList(SeqList &L,int length)
{
    for (int i = 0; i < length; i++) {
        L.data[i] = i;
    }
    L.length = length;
}

Status InitList(SeqList &L)
{
    L.data = new ElemType[MAXSIZE];
    if (!L.data) exit(OVERFLOW);
    L.length = 0;
    return OK;
}

Status GetElem(SeqList L, int i, ElemType &e)
{
    if (i<0 || i>L.length-1) return ERROR;
    e = L.data[i];
    return OK;
}

int LocateElem(SeqList L, ElemType e)
{
    for (int i = 0; i < L.length; i++){
        if (L.data[i] == e){
            return i;
        }
    }
    return -1;
}

Status ListInsert(SeqList &L, int i, ElemType e)
{
    if (i<1 || i>L.length) return ERROR;
    if (L.length == MAXSIZE) return ERROR;
    for (int j = L.length; j > i; j--)
    {
        L.data[j+1] = L.data[j];
    }
    L.data[i] = e;
    L.length++;
    return OK;
}

Status ListDelete(SeqList &L, int i)
{
    if (i<1 || i>L.length) return ERROR;
    if (L.length == 0) return ERROR;
    for (int j = i; j < L.length; j++){
        L.data[j - 1] = L.data[j];
    }
    L.length--;
    return OK;
}

void MergeList(SeqList &LA, SeqList LB)
{
    int m = LA.length;
    int n = LB.length;
    for (int i = 0; i < n; i++)
    {
        ElemType e;
        GetElem(LB, i, e);
        if (LocateElem(LA, e) == -1)
        {
            /**
             * 需要关注的是这里的m必须是m++，因为m基本等于LA的length，
             * 类似在LA的尾部追加节点数值
             */
            ListInsert(LA, m++, e);
        }
    }
}

void MergeList_Sq(SeqList LA, SeqList LB, SeqList &LC)
{
    //
    LC.length = LA.length + LB.length;
    LC.data = new ElemType[LC.length];
    ElemType *pa = LA.data;
    ElemType *pb = LB.data;
    ElemType *pc = LC.data;
    ElemType *pa_last = LA.data + LA.length - 1;
    ElemType *pb_last = LB.data + LB.length - 1;
    while ((pa <= pa_last) && (pb <= pb_last))
    {
        if (*pa <= *pb) *pc++ = *pa++;
        else *pc++ = *pb++;
    }
    while (pa <= pa_last) *pc++ = *pa++;
    while (pb <= pb_last) *pc++ = *pb++;
}


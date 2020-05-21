//
// Created by xjsaber on 2020/5/21.
//

#ifndef SRC_SQLIST_H
#define SRC_SQLIST_H

#include "../Base.h"

typedef struct
{
    ElemType *elem; //存储基地址
    int length; //当前长度
}SqList; //顺序表的结构类型韦SqList

Status InitList(SqList &L);

Status GetElem(SqList L, int i, ElemType &e);

int LocateElem(SqList, ElemType e);

Status ListInsert(SqList &L, int i, ElemType e);

Status ListDelete(SqList &L, int i);

#endif //SRC_SQLIST_H
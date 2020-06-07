//
// Created by xjsaber on 2020/5/21.
//

#ifndef SRC_SQLIST_H
#define SRC_SQLIST_H

#include "../Base.h"

void CreateList(SeqList &L,int length);

Status InitList(SeqList &L);

Status GetElem(SeqList L, int i, ElemType &e);

int LocateElem(SeqList, ElemType e);

Status ListInsert(SeqList &L, int i, ElemType e);

Status ListDelete(SeqList &L, int i);

/**
 * 合并顺序表
 * @param LA 顺序表A
 * @param LB 顺序表B
 */
void MergeList(SeqList &LA, SeqList LB);

void MergeList_Sq(SeqList LA, SeqList LB, SeqList &LC);

#endif //SRC_SQLIST_H
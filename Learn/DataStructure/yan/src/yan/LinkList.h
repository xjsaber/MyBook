#include "../Base.h"
//
// Created by xjsaber on 2020/5/21.
//

#ifndef SRC_LINKLIST_H
#define SRC_LINKLIST_H

Status InitList(LinkList &L);

Status GetElem(LinkList L, int i, ElemType &e);

LNode * LocalElem(LinkList L, ElemType e);

Status ListInsert(LinkList &L, int i, ElemType e);

Status ListDelete(LinkList &L, int i);

void CreateList_H(LinkList &L, int n);

void MergeList_L(LinkList &LA, LinkList &LB, LinkList &LC);

#endif //SRC_LINKLIST_H
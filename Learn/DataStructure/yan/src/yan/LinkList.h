#include "../Base.h"
//
// Created by xjsaber on 2020/5/21.
//

#ifndef SRC_LINKLIST_H
#define SRC_LINKLIST_H

typedef struct LNode
{
    ElemType data;
    struct LNode *next;
}LNode, *LinkList;

Status InitList(LinkList &L);

Status GetElem(LinkList L, int i, ElemType &e);

#endif //SRC_LINKLIST_H

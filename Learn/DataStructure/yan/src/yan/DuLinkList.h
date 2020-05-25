//
// Created by xjsaber on 2020/5/25.
//
#include "../Base.h"

#ifndef SRC_DULINKLIST_H
#define SRC_DULINKLIST_H

typedef struct DuLNode
{
    ElemType data;
    struct DuLNode *prior;
    struct DuLNode *next;
}DuLNode, *DuLinkList;

DuLNode *GetElem_DuL(DuLinkList &L, int i);

Status ListInsert_DuL(DuLinkList &L, int i, ElemType e);

Status ListDelete_DuL(DuLinkList &L, int i);

#endif //SRC_DULINKLIST_H

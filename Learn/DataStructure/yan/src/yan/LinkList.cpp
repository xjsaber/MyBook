#include "LinkList.h"
//
// Created by xjsaber on 2020/5/21.
//

Status InitList(LinkList &L)
{
    L = new LNode;
    L->next = nullptr;
    return OK;
}

Status GetElem(LinkList L, int i, ElemType &e)
{
    InitList(L);
    LNode *p = L ->next;
    int j = 1;
    while (p && j < i)
    {
        p = p->next;
        j++;
    }
    if (!p || j > i) return ERROR;
    e = p->data;
    return OK;
}
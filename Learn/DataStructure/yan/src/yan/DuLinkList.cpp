#include "DuLinkList.h"
//
// Created by xjsaber on 2020/5/25.
//
DuLNode *GetElem_DuL(DuLinkList &L, int i)
{
    DuLNode *p = L;
    for (int j = 0; j < i; j++)
    {
        p = p->next;
    }
    return p;
}

Status ListInsert_DuL(DuLinkList &L, int i, ElemType e)
{
    DuLNode *p;
    if (!(p = GetElem_DuL(L, i)))
    {
        return ERROR;
    }
    DuLNode *s = new DuLNode;
    s->data = e;
    s->prior = p->prior;
    //TODO 记忆
    p->prior->next = s;
    s->next = p;
    p->prior = s;
    return OK;
}

Status ListDelete_DuL(DuLinkList &L, int i)
{
    return OK;
}

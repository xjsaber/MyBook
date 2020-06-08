#include <iostream>
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

LNode * LocalElem(LinkList L, ElemType e)
{
    LNode *p = L ->next;
    while (p->next != nullptr)
    {
        if (p->data == e){
            break;
        }
        p = p->next;
    }
    return p;
}

Status ListInsert(LinkList &L, int i, ElemType e)
{
    int j = 0;
    LNode *p = L;
    for (j = 0; j < i-1; i++){
        p = p->next;
    }
    if (!p && j > i-1) return ERROR;
    auto *s = new LNode;
    s->data = e;
    s->next = p->next;
    p->next = s;
    return OK;
}

Status ListDelete(LinkList &L, int i)
{
    LNode *p = L;
    int j = 0;
    while ((p->next) && (j < i-1))
    {
        j++;
        p = p->next;
    }
    if (!(p->next) && j > i-1) return ERROR;
    LNode *q = p->next;
    p->next = q->next;
    delete q;
    return OK;
}

void CreateList_H(LinkList &L, int n)
{
    L = new LNode;
    L->next = nullptr;
    for (int i = 0; i < n; i++)
    {
        auto *p = new LNode;
        p->data = i;
        p->next = L->next;
        L->next = p;
    }
}

void CreateList_R(LinkList &L, int n)
{
    L = new LNode;
    L->next = nullptr;
    LNode* r = L;
    for (int i = 0; i < n; i++)
    {
        LNode *p = new LNode;
        std::cin >> p ->data;
        p->next = NULL;
        r->next = p;
        r = p;
    }
}

void MergeList_L(LinkList &LA, LinkList &LB, LinkList &LC)
{
    LNode *pa = LA->next;
    LNode *pb = LB->next;
    LC = LA;
    LNode *pc = LC;
    while (pa && pb)
    {
        if (pa->data <= pb->data)
        {
            pc->next = pb;
            pb = pb->next;
        } else {
            pc->next = pa;
            pa = pa->next;
        }
        pc = pc->next;
    }
    while (pa)
    {
        pc->next = pa;
        pa = pa->next;
        pc = pc->next;
    }
    while (pb)
    {
        pc->next = pb;
        pb = pb->next;
        pc = pc->next;
    }
}
//
// Created by xjsaber on 2020/6/8.
//

#include <iostream>
#include "LinkStack.h"

Status InitStack(LinkStack &S)
{
    S = nullptr;
    return OK;
}

Status Push(LinkStack &S, ElemType e)
{
    // S为栈顶指针
    // ERROR:p = S->next;
    //    while (p)
    //    {
    //        p = p->next;
    //    }
    // 不需要前往栈底
//    StackNode *n, *p;
//    n->data = e;
//    p = S->next;
//    while (p)
//    {
//        p = p->next;
//    }
//    n->next = nullptr;
//    p->next = n;
    auto *p = new StackNode;
    p->data = e;
    p->next = S; //将新结点插入栈顶
    S = p; //修改栈顶指针为p
    return OK;
}

Status Pop(LinkStack &S, ElemType &e)
{
    // S为栈顶指针
//    StackNode *n, *p;
//    n = S->next;
//    p = n->next;
//    while (p->next)
//    {
//        n = n->next;
//        p = p->next;
//    }
//    e = p->data;
//    n->next = nullptr;
    // 不需要前往栈底
    if (S == nullptr) return ERROR;
    e = S->data;
    auto *p = new StackNode;
    p = S;
    S = S->next;
    delete p;
    return OK;
}

ElemType GetTop(LinkStack S)
{
    if (S != nullptr)
        return S->data; //返回栈顶元素，指针不变
}

void TraverseList(LinkStack p)
{
    if (p == nullptr) return;
    else{
        std::cout << p->data << std::endl;
        TraverseList(p->next);
    }
}
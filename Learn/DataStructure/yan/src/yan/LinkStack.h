//
// Created by xjsaber on 2020/6/8.
//

#ifndef SRC_LINKSTACK_H
#define SRC_LINKSTACK_H

#include "../Base.h"

Status InitStack(LinkStack &S);

Status Push(LinkStack &S, SElemType e);

Status Pop(LinkStack &S, SElemType &e);

ElemType GetTop(LinkStack S);

void TraverseList(LinkStack p);

#endif //SRC_LINKSTACK_H

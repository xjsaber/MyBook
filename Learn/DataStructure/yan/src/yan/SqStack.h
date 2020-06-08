//
// Created by xjsaber on 2020/6/8.
//

#ifndef SRC_SQSTACK_H
#define SRC_SQSTACK_H


#include "../Base.h"

Status InitStack(SqStack &S);

Status Push(SqStack &S, SElemType e);

Status Pop(SqStack &S, SElemType &e);

SElemType GetTop(SqStack &S);


#endif //SRC_SQSTACK_H

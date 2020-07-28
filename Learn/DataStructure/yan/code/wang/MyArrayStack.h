//
// Created by xjsaber on 2020/7/27.
//

#ifndef SRC_MYARRAYSTACK_H
#define SRC_MYARRAYSTACK_H
#include "Base.h"

typedef struct {
    ElemType data[MAXSIZE];
    int top;
} MySqStack;

void InitStack(MySqStack &S);

bool StackEmpty(MySqStack S);

bool Push(MySqStack &S, ElemType e);

bool Pop(MySqStack &S, ElemType &e);

bool GetTop(MySqStack S, ElemType &e);

#endif //SRC_MYARRAYSTACK_H

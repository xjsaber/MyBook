//
// Created by xjsaber on 2020/6/8.
//

#include <cstdlib>
#include <xutility>
#include <iostream>
#include "SqStack.h"

Status InitStack(SqStack &S)
{
    S.base = new SElemType[MAXSIZE];
    if (!S.base) exit(OVERFLOW);
    S.top = S.base; // 初始为base，空栈
    S.stacksize = MAXSIZE;
    return OK;
}

Status Push(SqStack &S, SElemType e)
{
    // 栈满
    if (S.top-S.base == S.stacksize) return ERROR;
    *S.top = e;
    // ERROR: *S.top++;
    S.top++;
    return OK;
}

Status Pop(SqStack &S, SElemType &e)
{
    // 栈空
    if (S.top == S.base) return ERROR;
    // ERROR: e = *S.top;
    // ERROR: *S.top--;
    S.top--;
    e = *S.top;
    return OK;
}

SElemType GetTop(SqStack &S)
{
    // 栈空
    if (S.top == S.base) return ERROR;
    // 返回栈顶元素的值，栈顶指针不变
    // ERROR: SElemType e = *S.top;
    SElemType e = *(S.top-1);
    return e;
}
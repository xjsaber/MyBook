//
// Created by xjsaber on 2020/7/27.
//

#include "MyArrayStack.h"

void InitStack(MySqStack &S){
    S.top = -1;
}

bool StackEmpty(MySqStack S){
    return S.top == -1;
}

bool Push(MySqStack &S, ElemType e) {
    if (S.top == MAXSIZE - 1) return false;
    S.top++;
    S.data[S.top] = e;
    return true;
}

bool Pop(MySqStack &S, ElemType &e) {
    if (S.top == -1) return false;
    e = S.data[S.top];
    S.top--;
    return true;
}

bool GetTop(MySqStack S, ElemType &e) {
    if (S.top == -1) return false;
    e = S.data[S.top];
    return true;
}

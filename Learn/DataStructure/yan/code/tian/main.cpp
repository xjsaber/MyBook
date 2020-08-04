#include <iostream>

#include "MyArrayStack.h"

int main() {
    MySqStack mySqStack;
    InitStack(mySqStack);
    ElemType e0 = 0;
    ElemType e1 = 1;
    ElemType e2 = 2;
    ElemType e3 = 3;
    ElemType re1 = 0;
    ElemType re2 = 0;
    bool isEmpty = StackEmpty(mySqStack);
    std::cout << isEmpty << std::endl;
    Push(mySqStack, e1);
    Push(mySqStack, e2);
    Push(mySqStack, e3);
    Pop(mySqStack, re1);
    GetTop(mySqStack, re2);
    std::cout << re1 << std::endl;
    std::cout << re2 << std::endl;
    return 0;
}

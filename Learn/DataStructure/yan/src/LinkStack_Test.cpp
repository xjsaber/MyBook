#include <iostream>
#include "yan/LinkStack.h"

int main() {

    LinkStack S;
    InitStack(S);
    Push(S, 1);
    std::cout << "Push(S, 1)" << std::endl;
    Push(S, 3);
    std::cout << "Push(S, 3)" << std::endl;
    ElemType e = GetTop(S);
    std::cout << "GetTop1" << std::endl;
    std::cout << e << std::endl;
    Push(S, 2);
    std::cout << "Push(S, 2)" << std::endl;
    Pop(S, e);
    std::cout << "Pop" << std::endl;
    std::cout << e << std::endl;
    Push(S, 4);
    std::cout << "Push(S, 4)" << std::endl;
    e = GetTop(S);
    std::cout << "GetTop2" << std::endl;
    std::cout << e << std::endl;
    std::cout << "TraverseList" << std::endl;
    TraverseList(S);
    return 0;
}
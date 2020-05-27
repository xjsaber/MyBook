#include <iostream>
#include "yan/SqList.h"

int main() {
    SqList L;
    InitList(L);
    std::cout << L.length << std::endl;
    return 0;
}
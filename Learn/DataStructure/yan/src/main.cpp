#include <iostream>
#include "yan/SqList.h"

int main() {
    SeqList LA;
    SeqList LB;
    InitList(LA);
    InitList(LB);
    CreateList(LA, 4);
    CreateList(LB, 6);

    std::cout << "LA" << std::endl;
    for (int i = 0; i < LA.length; i++)
    {
        std::cout << LA.data[i] << "," << std::ends;
    }
    std::cout << "" << std::endl;
    std::cout << "LB" << std::endl;
    for (int i = 0; i < LB.length; i++)
    {
        std::cout << LB.data[i] << "," << std::ends;
    }
    std::cout << "" << std::endl;

    MergeList(LA, LB);
//    SeqList LC;
//    InitList(LC);
//    MergeList_Sq(LA, LB, LC);
    std::cout << "RESULT" << std::endl;
    for (int i = 0; i < LA.length; i++)
    {
        std::cout << LA.data[i] << "," << std::ends;
    }
    return 0;
}
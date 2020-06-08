#include <iostream>
#include "yan/LinkList.h"

int main() {

    LinkList LA;
    LinkList LB;
    InitList(LA);
    InitList(LB);
    CreateList_H(LA, 4);
    CreateList_H(LB, 6);

    LNode *pa = LA->next;
    LNode *pb = LB->next;

    std::cout << "LA" << std::endl;
    while (pa)
    {
        std::cout << pa->data << "," << std::ends;
        pa = pa->next;
    }
    std::cout << "" << std::endl;
    std::cout << "LB" << std::endl;
    while (pb)
    {
        std::cout << pb->data << "," << std::ends;
        pb = pb->next;
    }
    std::cout << "" << std::endl;

    LinkList LC;
    InitList(LC);
    MergeList_L(LA, LB, LC);

    LNode *pc = LC->next;
    std::cout << "RESULT" << std::endl;
    while (pc)
    {
        std::cout << pc->data << "," << std::ends;
        pc = pc->next;
    }
    return 0;
}
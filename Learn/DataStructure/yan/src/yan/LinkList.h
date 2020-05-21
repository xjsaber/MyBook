#include "../Base.h"
//
// Created by xjsaber on 2020/5/21.
//

#ifndef SRC_LINKLIST_H
#define SRC_LINKLIST_H

typedef struct LNode
{
    ElemType data;
    struct LNode *next;
}LNode, *LinkList;

#endif //SRC_LINKLIST_H

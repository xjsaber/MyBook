//
// Created by xjsaber on 2020/7/27.
//

#ifndef SRC_MYLINKEDSTACK_H
#define SRC_MYLINKEDSTACK_H
#include "../Base.h"

typedef struct LinkNode {
    ElemType data;
    struct LinkNode *next;
} *LinkedStack;

#endif //SRC_MYLINKEDSTACK_H

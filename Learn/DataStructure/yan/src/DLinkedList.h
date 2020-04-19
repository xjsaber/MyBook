//
// Created by xjsaber on 2020/4/19.
//

#ifndef SRC_DLINKEDLIST_H
#define SRC_DLINKEDLIST_H
#define ElemType int

typedef struct DNode {
    ElemType data;
    struct DNode *prior, *next;
}DNode, *DLinkedList;

#endif //SRC_DLINKEDLIST_H

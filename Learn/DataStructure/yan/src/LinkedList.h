//
// Created by xjsaber on 2020/4/19.
//

#ifndef SRC_LINKEDLIST_H
#define SRC_LINKEDLIST_H
#define ElemType int

typedef struct LNode{
    ElemType data;
    struct LNode *next;
}LNode, *LinkedList;

typedef struct LRNode{
    ElemType data;
    struct LNode *next;
};

LinkedList Link_HeadInsert(LinkedList &l);

LinkedList List_TailInsert(LinkedList &l);

LNode* GetElem(LinkedList l, int i);

#endif //SRC_LINKEDLIST_H

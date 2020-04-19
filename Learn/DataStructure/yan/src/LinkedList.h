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

LinkedList List_HeadInsert(LinkedList &l);

LinkedList List_TailInsert(LinkedList &l);

LNode* GetElem(LinkedList l, int i){
    int j = 1;
    LNode *p = l->next;
    if (i == 0)
        return l;
    if (i < 1)
        return NULL;
    while (p && j < i) {
        p = p->next;
        j++;
    }
    return p; //返回第i个结点的指针，若i大于表长返回NULL
}

#endif //SRC_LINKEDLIST_H

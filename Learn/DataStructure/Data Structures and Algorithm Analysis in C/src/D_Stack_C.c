//
// Created by xjsaber on 2019/11/19.
//
#include "D_Stack_H.h"
#include <stdio.h>
#include <stdlib.h>

#define NULL 0

struct Node
{
    ElementType Element;
    Position Next;
};

/* Return true if l is empty */

void
MakeEmpty(Stack s)
{
    if (s == NULL)
        Error("");
    else
        while (!IsEmpty(s)){
            Pop(s);
        }
}

Stack
MakeStack(void)
{
    Stack s;

    s = malloc(sizeof(struct Node));
    if (s == NULL) {
        // Out of space!!
        exit(0);
    }
    s->Next == NULL;
    MakeEmpty(s);
    return s;
}

int
IsEmpty(List l)
{
    return l->Next == NULL;
}

int
IsLast(Position p, List l)
{
    return p->Next == NULL;
}

Position
Find(ElementType x, List l)
{
    Position  p;

    p = l->Next;
    while (p != NULL && p->Element != x)
    {
        p = p->Next;
    }
    return p;
}

Position
FindPrevious(ElementType x, List l)
{
    Position p;

    p = l;
    while (p != NULL && p->Next->Element != x)
        p = p->Next;
    return p;
}

void
Delete(ElementType x, List l)
{
    Position p, tmpCell;
    p = FindPrevious(x, l);

    if (!IsLast(p, l))
    {
        tmpCell = p->Next;
        p->Next = tmpCell->Next;
        free(tmpCell);
    }
}

/* ERROR */
//void
//Insert(ElementType x, List l)
//{
//    Position tmpCell;
//    tmpCell = FindPrevious(x, l);
//
//    if (!IsLast(tmpCell, l)){
//        PtrToNode item = NULL;
//        item->Element = x;
//
//        tmpCell->
//    }
//}

void
Insert(ElementType x, List l, Position p)
{
    Position tmpCell;
    tmpCell = malloc(sizeof(struct Node));

    if (tmpCell == NULL){
        exit(0);
    }

    tmpCell->Element = x;
    tmpCell->Next = p->Next;
    p->Next = tmpCell;
}
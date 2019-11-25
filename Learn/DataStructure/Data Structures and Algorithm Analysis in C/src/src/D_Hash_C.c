//
// Created by xjsaber on 2019/11/19.
//
#include <stdio.h>
#include <stdlib.h>
#include "../include/D_Stack_H.h"

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
        ERROR("");
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
IsEmpty(Stack s)
{
    return s->Next == NULL;
}

int
IsLast(Position p, Stack l)
{
    return p->Next == NULL;
}

Position
Find(ElementType x, Stack l)
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
FindPrevious(ElementType x, Stack s)
{
    Position p;

    p = s;
    while (p != NULL && p->Next->Element != x)
        p = p->Next;
    return p;
}

void
Delete(ElementType x, Stack s)
{
    Position p, tmpCell;
    p = FindPrevious(x, s);

    if (!IsLast(p, s))
    {
        tmpCell = p->Next;
        p->Next = tmpCell->Next;
        free(tmpCell);
    }
}

void
Insert(ElementType x, Stack s, Position p)
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

//
// Created by xjsaber on 2019/11/19.
//
#include <stdio.h>
#include <stdlib.h>
#include "../include/D_List_H.h"

/* Return true if l is empty */

List
MakeEmpty()
{
	List list = (List)malloc(sizeof(List));
	list->Element = 0;
	list->Next = NULL;
    return list;
}

int
IsEmpty(List l)
{
    return l->Next == NULL;
}

int
IsLast(Position p, List l)
{
    while(l->Next != NULL){
        l = l->Next;
    }
    return p == l;
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
Insert(ElementType x, List list, Position p)
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

void
DeleteList(List list)
{
	Position p, tmp;
	p = list->Next;
	list->Next = NULL;

	while (p != NULL) {
		tmp = p->Next;
		free(p);
		p = tmp;
	}
}

Position Header(List list)
{
	Position p = list->Next;
	return p;
}

Position Last(List list)
{
	Position p = list->Next;
	while (p->Next != NULL)
	{
		p = p->Next;
	}
	return p;
}


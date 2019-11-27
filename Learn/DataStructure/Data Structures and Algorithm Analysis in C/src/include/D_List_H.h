//
// Created by xjsaber on 2019/11/19.
//
#ifndef DATA_STRUCTURES_AND_ALGORITHM_ANALYSIS_IN_C_D_LIST_H_H
#define DATA_STRUCTURES_AND_ALGORITHM_ANALYSIS_IN_C_D_LIST_H_H

#include "D_Base_H.h"

typedef PtrToNode List;

/* O(1) */
List MakeEmpty();
int IsEmpty(List l);
int IsLast(Position p, List l);
void Delete(ElementType x, List l);
void Insert(ElementType x, List list, Position p);

Position Find(ElementType x, List l);
Position FindPrevious(ElementType x, List l);
void DeleteList(List list);
Position Header(List list);
Position First(List list);
Position Advance(Position p);
ElementType Retrieve(Position p);

#endif //DATA_STRUCTURES_AND_ALGORITHM_ANALYSIS_IN_C_D_LIST_H_H

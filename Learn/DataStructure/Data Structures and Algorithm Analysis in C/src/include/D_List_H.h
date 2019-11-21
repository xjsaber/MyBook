//
// Created by xjsaber on 2019/11/19.
//

#ifndef DATA_STRUCTURES_AND_ALGORITHM_ANALYSIS_IN_C_D_LIST_H_H
#define DATA_STRUCTURES_AND_ALGORITHM_ANALYSIS_IN_C_D_LIST_H_H

struct Node;
typedef struct Node *PtrToNode;
typedef PtrToNode List;
typedef PtrToNode Position;
typedef int ElementType;

List MakeEmpty(List l);
int IsEmpty(List l);
int IsLast(Position p, List l);
Position Find(ElementType x, List l);

#endif //DATA_STRUCTURES_AND_ALGORITHM_ANALYSIS_IN_C_D_LIST_H_H

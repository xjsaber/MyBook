//
// Created by xjsaber on 2019/11/19.
//

#ifndef DATA_STRUCTURES_AND_ALGORITHM_ANALYSIS_IN_C_D_STACK_H_H
#define DATA_STRUCTURES_AND_ALGORITHM_ANALYSIS_IN_C_D_STACK_H_H

struct Node;
typedef struct Node *PtrToNode;
typedef PtrToNode Stack;

int IsEmpty(List l);
Stack CreateStack(void)
void DisposeStack(Stack s);
List MakeEmpty(List l);
void Push(ElementType x, Stack s);
ElementType Top(Stack s);
void Pop(Stack s);

#endif //DATA_STRUCTURES_AND_ALGORITHM_ANALYSIS_IN_C_D_LIST_H_H

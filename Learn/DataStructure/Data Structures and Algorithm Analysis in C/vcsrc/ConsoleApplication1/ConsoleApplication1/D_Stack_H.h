#pragma once

struct Node;
typedef struct Node *PtrToNode;
typedef PtrToNode Stack;
typedef PtrToNode Position;
typedef int ElementType;

int IsEmpty(Stack s);
Stack CreateStack(void);
void DisposeStack(Stack s);
void Push(ElementType x, Stack s);
ElementType Top(Stack s);
void MakeEmpty(Stack s);
void Pop(Stack s);

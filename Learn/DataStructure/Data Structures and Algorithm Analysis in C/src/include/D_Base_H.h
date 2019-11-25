//
// Created by xjsaber on 2019/11/25.
//

#ifndef SRC_D_BASE_H_H
#define SRC_D_BASE_H_H

typedef int ElementType;
struct Node;
typedef struct Node *PtrToNode;
typedef PtrToNode Position;

struct Node
{
    ElementType Element;
    Position Next;
};

#endif //SRC_D_BASE_H_H

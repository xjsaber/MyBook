//
// Created by xjsaber on 2020/8/4.
//

#ifndef TIAN_MYTREE_H
#define TIAN_MYTREE_H

#include "Base.h"

typedef struct BitNode{
    ElemType data;
    BitNode *left;
    BitNode *right;
}BitNode, *BiTree;

void preOrder(BiTree node);

void inOrder(BiTree node);

void postOrder(BiTree node);

#endif //WANG_MYTREE_H

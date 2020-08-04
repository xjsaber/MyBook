//
// Created by xjsaber on 2020/8/4.
//
#include <iostream>
#include "MyTree.h"

void preOrder(BiTree T)
{
    if (T != nullptr) {
        std::cout << T->data << std::endl;
        preOrder(T->left);
        preOrder(T->right);
    }
}

void inOrder(BiTree T)
{
    if (T != nullptr) {
        inOrder(T->left);
        std::cout << T->data << std::endl;
        inOrder(T->right);
    }
}

void postOrder(BiTree T)
{
    if (T != nullptr) {
        postOrder(T->left);
        postOrder(T->right);
        std::cout << T->data << std::endl;
    }
}
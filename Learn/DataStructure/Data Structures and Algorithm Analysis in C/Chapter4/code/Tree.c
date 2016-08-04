//
// Created by xjsaber on 2016/8/4.
//

#include "stdafx.h"
#include <ios>
typedef int ElementType;

#ifndef _Tree_H
#define _Tree_H

struct TreeNode;
typedef struct TreeNode *Position;
typedef struct TreeNode *SearchTree;

SearchTree MakeEmpty(SearchTree T);
Position Find(ElementType X, SearchTree T);
Position FindMin(SearchTree T);
Position FindMax(SearchTree T);
SearchTree Insert(ElementType X, SearchTree T);
SearchTree Delete(ElementType X, SearchTree T);
ElementType Retrieve(Position P);

#endif // _Tree_H

/* Place in the implementation file */

struct TreeNode
{
    ElementType Element;
    SearchTree Left;
    SearchTree Right;
};
//typedef struct TreeNode *SearchTree;
//typedef struct TreeNode *Position;

SearchTree MakeEmpty(SearchTree T)
{
    if (T != nullptr) {
        MakeEmpty(T->Left);
        MakeEmpty(T->Right);
        MakeEmpty(T);
    }
    return nullptr;
}

Position Find (ElementType X, SearchTree T)
{
    if (T == NULL)
    {
        return nullptr;
    }
    if ( X < T->Element)
    {
        T = T->Left;
        return Find(X, T);
    }
    if ( X > T->Element)
    {
        T = T->Right;
        return Find(X, T);
    }
    return T;
}

Position FindMin(SearchTree T)
{
    if (T == nullptr)
    {
        return nullptr;
    }
    if (T->Left == nullptr)
    {
        return T;
    }
    return FindMin(T->Left);
}

Position FindMax(SearchTree T)
{
    if (T == nullptr)
    {
        return nullptr;;
    }
    if (T->Right == nullptr)
    {
        return T;
    }
    return FindMax(T->Right);
}

SearchTree Insert(ElementType X, SearchTree T)
{
    if (T == nullptr)
    {
        /* create and return a one-node tree */
        T = SearchTree(malloc(sizeof(struct TreeNode)));
        if (T == nullptr)
        {
//			fatal_error("Out of space!!!");
            printf_s("Out of space!!!");
        }
        else
        {
            T->Element = X;
            T->Left = T->Right = NULL;
        }
    }
    else if (X < T->Element)
    {
        T->Left = Insert(X, T->Left);
    }
    else if (X > T->Element)
    {
        T->Right = Insert(X, T->Right);
    }
    return nullptr;
}
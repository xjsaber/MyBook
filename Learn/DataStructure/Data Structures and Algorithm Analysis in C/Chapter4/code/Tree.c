//
// Created by xjsaber on 2016/8/3.
//
typedef struct TreeNode *PtrToNode;
struct TreeNode
{
    ElementType Element;
    PtrToNode FirstChild;
    PtrToNode NextSibling;
};


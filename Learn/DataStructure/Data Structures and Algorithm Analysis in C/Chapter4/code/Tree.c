//
// Created by xjsaber on 2016/8/4.
//
#ifdef _Tree_H
struct TreeNode;
typedef struct TreeNode *Position;
typedef struct TreeNode *SearchTree;

SearchTree MakeEmpty(SearchTree T);
#endif _Tree_H

//
struct TreeNode
{
    ElementType Element;
    SearchTree Left;
    SearchTree Right;
};

SearchTree
MakeEmpty(SearchTree T)
{
    if (T != null){
        MakeEmpty( T->left );
        MakeEmpty( T->right );
        MakeEmpty( T );
    }
    return null;
}

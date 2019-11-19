#include <iostream>

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

int main() {
    std::cout << "Hello, World!" << std::endl;
    return 0;
}
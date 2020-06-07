#include "LinkedList.h"

#define maxSize 100

SqlList createList(int length) 
{
    struct SqlList l;

    LNode* lNode = malloc(
    typeof(LNode));
    for (int i = 0; i < length; i++) {
        l.data[i] = i;
    }
    l.length = length;
    return l;
}

int findElem(SqlList l, int item) 
{
    int index = -1;
    for (int i = 0; i < l.length; i++) {
        if (item == l.data[i]) {
            index = i + 1;
            break;
        }
    }
    return index;
}
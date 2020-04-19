#include "ArrayList.h"
#include <cstddef>
#include <iostream>

SqlList createList(int length) 
{
    SqlList l;

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

bool insert(SqlList &l, int position, int item){
    if (position < 0 && position > l.length) return false;
    for (int i = l.length - 1; i >= position; i--){
        int value = l.data[l.length];
        l.data[l.length + 1] = value;
    }
    l.data[position] = item;
    l.length++;
    return true;
}

bool remove(SqlList &l, int p, ElemType &e){
    if (p < 0 && p > l.length) return false;
    e = l.data[p - 1];
    for (int i = p - 1; i < l.length - 1; i++){
        l.data[p - 1] = l.data[p];
    }
    l.data[l.length - 1] = NULL;
    l.length--;
    return true;
}

bool empty(SqlList &l){
    return l.length == 0;
}

void printList(SqlList &l){
    int i = 0;
    while (i < l.length){
        std::cout << l.data;
        i++;
    }
}


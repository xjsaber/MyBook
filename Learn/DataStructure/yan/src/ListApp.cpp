#include "ListApp.h"
#include <stddef.h>

SqlList createList(int length) 
{
    struct SqlList l;

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

void insert(SqlList &l, int position, int item){
    if (position < 0 && position > l.length) return;
    for (int i = l.length - 1; i >= position; i--){
        int value = l.data[l.length];
        l.data[l.length + 1] = value;
    }
    l.data[position] = item;
    l.length++;
}

void remove(SqlList &l, int position){
    if (position < 0 && position > l.length) return;
    for (int i = position; i < l.length - 1; i++){
        l.data[position] = l.data[position + 1];
    }
    l.data[l.length - 1] = NULL;
    l.length++;
}

void remove(SqlList &l, int &item){
    if (position < 0 && position > l.length) return;
    for (int i = position; i < l.length - 1; i++){
        l.data[position] = l.data[position + 1];
    }
    l.data[l.length - 1] = NULL;
    l.length++;
}
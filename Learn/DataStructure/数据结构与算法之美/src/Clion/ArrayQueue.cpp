#include "ArrayQueue.h"
#include <stdio.h>

SqlList createList(int length){
    struct SqlList l;

    for (int i = 0; i < length; i++) {
        l.data[i] = i;
    }
    l.length = length;
    return l;
}

bool enqueue(String item){
    // 如果tail == n 表示队列已经满了
    if (tail) return false;
}

string dequeue(){
    if (head == tail) return NULL;

}
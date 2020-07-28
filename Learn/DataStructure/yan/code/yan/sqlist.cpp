//
// Created by xjsaber on 2017/3/1.
//
#include "sqlist.h"

Status SqLList_Class::InitList(SqList &L){
    L.elem
}

Status SqList_Class::LocateElem(SqList L, ElemType e){
    int index = 0;
    for (int i = 0; i < L.length; i++){
        if (e == L.elem[i]){
            index = i;
            break;
        }
    }
    return index;
}

Status SqList_Class::Insert(SqList &L, int i, ElemType e){
    int index;
    if (i < 1 || i > L.length + 1 || L.length){
        return ERROR;
    }
    for (index = L.length; index >= i; --index){
        L.elem[index+1] = L.elem[index];
        L.elem[i] = e;
        ++(L.length);
        return OK;
    }
}

//
// Created by xjsaber on 2020/4/19.
//
#include <cstdlib>
#include <cstdio>
#include "LinkedList.h"

LinkedList Link_HeadInsert(LinkedList &l){ //逆向建立单链表
    LNode *s; int x;
    l = (LinkedList)malloc(sizeof(LNode)); //创建头节点
    l->next = NULL; //初始为空链表
    scanf("%d", &x); //输入结点的值
    while (x != 9999){
        s = (LNode*)malloc(sizeof(LNode)); //创建新节点
        s->data = x;
        s->next = l->next;
        l->next = s;    //将新节点插入表中，L为头节点
        scanf("%d", &x);
    }
    return l;
}

LinkedList List_TailInsert(LinkedList &l){
    int x;
    l = (LinkedList)malloc(sizeof(LNode));
    LNode *s, *r = l;   // r为表尾指针
    scanf("%d", &x);    //输入结点的值
    while(x != 9999){
        s = (LNode *)malloc(sizeof(LNode));
        s->data = x;
        r->next = s;
        r = s;  //r 指向新的表尾节点
        scanf("%d", &x);
    }
    r->next = NULL;
    return l;
}



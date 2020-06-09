//
// Created by xjsaber on 2020/6/8.
//
#include "../Base.h"
#include "LinkQueue.h"

Status InitQueue(LinkQueue &Q)
{
    Q.front = Q.rear = new QNode;
    Q.front->next = nullptr;
    return OK;
}

int QueueLength(LinkQueue Q)
{
    int length = 0;
    while (Q.rear != Q.front)
    {
        Q.rear = Q.rear->next;
        length++;
    }
    return length;
}

Status EnQueue(LinkQueue &Q, QElemType e)
{
    auto *p = new QNode;
    p->data = e;
    p->next = nullptr;
    // 将新结点插入到队尾
    Q.rear->next = p;
    Q.rear = p;
    return OK;
}

Status DeQueue(LinkQueue &Q, QElemType &e)
{

}

QElemType GetHead(LinkQueue Q)
{

}

//
// Created by xjsaber on 2020/6/8.
//

#include <cstdlib>
#include "LinkQueue.h"

Status InitQueue(SqQueue &Q)
{
    Q.base = new QElemType[MAXSIZE];
    if (!Q.base) exit(OVERFLOW);
    // 头指针和尾指针置为0，队列为空
    Q.front = Q.rear = 0;
    return OK;
}

int QueueLength(SqQueue Q)
{
    return (Q.rear - Q.front + MAXQSIZE) / MAXQSIZE;
}

Status EnQueue(SqQueue &Q, QElemType e)
{
    if ((Q.rear + 1) % MAXQSIZE == Q.front)
        return ERROR;
    Q.base[Q.rear] = e;
    Q.rear = (Q.rear + 1) % MAXQSIZE;
    return OK;
}

Status DeQueue(SqQueue &Q, QElemType &e)
{
    if (Q.rear == Q.front )
        return ERROR;
    e = Q.base[Q.front];
    Q.front = (Q.front + 1) % MAXQSIZE;
    return OK;
}

QElemType GetHead(SqQueue Q)
{
    if (Q.rear != Q.front )
        return Q.base[Q.front];
}

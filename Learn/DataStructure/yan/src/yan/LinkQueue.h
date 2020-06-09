//
// Created by xjsaber on 2020/6/8.
//

#ifndef SRC_LINKQUEUE_H
#define SRC_LINKQUEUE_H

#include "../Base.h"

Status InitQueue(LinkQueue &Q);

int QueueLength(LinkQueue Q);

Status EnQueue(LinkQueue &Q, QElemType e);

Status DeQueue(LinkQueue &Q, QElemType &e);

QElemType GetHead(LinkQueue Q);


#endif //SRC_LINKQUEUE_H

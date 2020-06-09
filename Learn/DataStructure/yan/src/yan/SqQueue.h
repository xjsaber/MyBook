//
// Created by xjsaber on 2020/6/8.
//

#ifndef SRC_SQQUEUE_H
#define SRC_SQQUEUE_H

#include "../Base.h"

Status InitQueue(SqQueue &Q);

int QueueLength(SqQueue Q);

Status EnQueue(SqQueue &Q, QElemType e);

Status DeQueue(SqQueue &Q, QElemType &e);

QElemType GetHead(SqQueue Q);


#endif //SRC_SQQUEUE_H

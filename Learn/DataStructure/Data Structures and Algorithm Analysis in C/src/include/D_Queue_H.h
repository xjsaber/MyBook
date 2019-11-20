//
// Created by xjsaber on 2019/11/20.
//

#ifndef SRC_D_QUEUE_H_H
#define SRC_D_QUEUE_H_H

struct QueueRecord;
typedef struct QueueRecord *Queue;
typedef int ElementType;

int IsEmpty(Queue q);
int IsFull(Queue q);
Queue CreateQueue(int maxElements );
void DisposeQueue(Queue q);
void MakeEmpty(Queue q);
void Enqueue(ElementType x, Queue q);
ElementType front(Queue q);
void Dequeue(Queue q);
ElementType frontAndDeQueue(Queue q);

#endif //SRC_D_QUEUE_H_H

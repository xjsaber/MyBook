//
// Created by xjsaber on 2020/5/8.
//

#ifndef SRC_BASE_H
#define SRC_BASE_H

#define ElemType int
#define MAXSIZE 100

// 函数结果状态代码
#define OK 1
#define ERROR 0
#define OVERFLOW -2
// Status是函数返回值类型，其值是函数结果状态代码
typedef int Status ;

typedef struct SqlList{
    ElemType data[MAXSIZE];
    int length;
} SqlList;

typedef struct SeqList{
    ElemType *data;
    int length;
} SeqList;

typedef struct LNode
{
    ElemType data;
    struct LNode *next;
}LNode, *LinkList;

#endif //SRC_BASE_H
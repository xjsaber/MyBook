//
// Created by xjsaber on 2020/5/8.
//

#ifndef SRC_BASE_H
#define SRC_BASE_H

#define ElemType int
#define SElemType int
#define MAXSIZE 100

// 函数结果状态代码
#define OK 1
#define ERROR 0
#define OVERFLOW -2
// Status是函数返回值类型，其值是函数结果状态代码
typedef int Status ;

typedef struct {
    ElemType data[MAXSIZE];
    int length;
} SqlList;

typedef struct {
    ElemType *data;
    int length;
} SeqList;

typedef struct LNode
{
    ElemType data;
    struct LNode *next;
}LNode, *LinkList;

typedef struct
{
    SElemType *base; //栈底元素
    SElemType *top; //栈顶元素
    int stacksize; //栈可用的最大容量
}SqStack;

typedef struct StackNode
{
    ElemType data;
    struct StackNode *next;
}StackNode, *LinkStack;

#endif //SRC_BASE_H
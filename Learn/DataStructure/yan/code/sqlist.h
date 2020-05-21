//
// Created by xjsaber on 2017/3/1.
//

#ifndef C_LIST_H
#define C_LIST_H
#define MAXSIZE 100
#define OK 1
#define ERROR 0
#define OVERFLOW -1
#define LIST_INIT_SIZE 100 //线性表存储空间的初始分配量
#define LISTINCREMENT 10 //线性表存储空间的分配增量

typedef int ElemType;
typedef int Status;

typedef struct {
    ElemType * elem; //存储空间基址
    int length; //当前长度
//    int listsize; //当前分配的存储容量（以sizeof(ElemType)为单位）
}SqList;

class SqList_Class {
    private:

    public:

        Status InitList(SqList &L);

        Status DestroyList(SqList & L);

        Status ListEmpty(SqList L);

        Status ListLength(SqList L);

        Status GetElem(SqList L, int i, ElemType &e);

        Status LocateElem(SqList L, ElemType e);

        Status Insert(SqList &L, int i, ElemType e);
};

#endif //C_LIST_H

//
// Created by xjsaber on 2020/6/10.
//

#ifndef SRC_SORTUTILS_H
#define SRC_SORTUTILS_H

#define MAXSIZE 20

typedef int keyType;
typedef int InfoType;
typedef struct{
    keyType key;
    InfoType otherinfo;
}RedType;
typedef struct {
    RedType r[MAXSIZE + 1];
    int length;
} SqList;

void InsertSort(SqList &L);

#endif //SRC_SORTUTILS_H

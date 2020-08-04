//
// Created by xjsaber on 2020/7/27.
//

#ifndef SRC_MYARRAYSTACK_H
#define SRC_MYARRAYSTACK_H

#include "Base.h"

typedef struct {
    ElemType data[MAXSIZE]; //数据域
    int front, rear;    //队头和队尾指针
};

#endif //SRC_MYARRAYSTACK_H

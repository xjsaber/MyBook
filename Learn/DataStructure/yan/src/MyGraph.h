//
// Created by xjsaber on 2020/7/23.
//
#include "Base.h"

#ifndef SRC_MYGRAPH_H
#define SRC_MYGRAPH_H

typedef struct
{
    int no;	//顶点编号
    char info;	//顶点其他信息
}VertexType;	//顶点类型

typedef struct
{
    int edges[MAXSIZE][MAXSIZE];	//邻接矩阵定义，如果有权图，则在此句中将int改为float
    int n, e;	// 存放结点信息
    VertexType vex[MAXSIZE];  // 存放结点信息
}MGraph;	// 图的邻接矩阵类型

#endif //SRC_MYGRAPH_H

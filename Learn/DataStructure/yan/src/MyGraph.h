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

typedef struct ArcNode
{
    int adjvex; // 这边所指向的结点的位置
    struct ArcNode *nextarc; //指向下一条边的指针
    int info;   // 该边的相关信息
} ArcNode;

typedef struct
{
    char data; //顶点信息
    ArcNode *firstarc; //指向第一条边的指针
} VNode;

typedef struct
{
    VNode adjlist[MAXSIZE]; //邻接表
    int n, e;   //顶点数和边数
} AGraph;

#endif //SRC_MYGRAPH_H

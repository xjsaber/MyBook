//
// Created by xjsaber on 2019/11/19.
//

#ifndef DATA_STRUCTURES_AND_ALGORITHM_ANALYSIS_IN_C_D_HASH_H
#define DATA_STRUCTURES_AND_ALGORITHM_ANALYSIS_IN_C_D_HASH_H

#include "D_Hash_H.h"

struct ListNode;
struct HashTbl;
typedef struct HashTbl *HashTable;

HashTable InitializeTable(int tableSize);
void DestroyTable(HashTable t);
Position Find(ElementType key, HashTable t);
void Insert(ElementType key, HashTable t);
ElementType Retrieve(Position p);

struct ListNode
{
    ElementType element;
    Position next;
};

typedef Position list;

struct HashTbl
{
    int tableSize;
    List *theLists;
};

#endif //DATA_STRUCTURES_AND_ALGORITHM_ANALYSIS_IN_C_D_HASH_H

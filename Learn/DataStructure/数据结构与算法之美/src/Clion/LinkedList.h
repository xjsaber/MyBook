#pragma once

typedef struct LNode
{
    int data;
    struct LNode* next;
}LNode;

typedef struct DLNode
{
    int data;
    struct DLNode* next;
    struct DLNode* prev;
}DLNode;

LNode* createList(int length);

void insert(LNode* list, LNode* item);

void remove(LNode* list, LNode* item);

int findElem(LNode* list, LNode* item);
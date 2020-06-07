#pragma once
#include "Base.h"

SqlList createList(int length);

int findElem(SqlList l, int item);

bool insert(SqlList &l, int i, ElemType e);

bool remove();
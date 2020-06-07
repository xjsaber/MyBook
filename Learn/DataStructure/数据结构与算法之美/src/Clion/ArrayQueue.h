#pragma once

struct SqlList
{
    string data[maxSize];
    int length;
};

SqlList createList(int length);

bool enqueue(String item);

String dequeue();
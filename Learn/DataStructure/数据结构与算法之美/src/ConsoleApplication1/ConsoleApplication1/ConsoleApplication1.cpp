// ConsoleApplication1.cpp : 此文件包含 "main" 函数。程序执行将在此处开始并结束。
//

#include <iostream>

#define maxSize 100

typedef struct SqlList
{
    int data[maxSize];
    int length;
} SqlList;

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

SqlList createList(int length) 
{
    struct SqlList l;

    for (int i = 0; i < length; i++) {
        l.data[i] = i;
    }
    l.length = length;
    return l;
}

int findElem(SqlList l, int item) 
{
    int index = -1;
    for (int i = 0; i < l.length; i++) {
        if (item == l.data[i]) {
            // 序号是从1开始，i是从0开始
            index = i + 1;
            break;
        }
    }
    return index;
}

int main()
{
    int length = 50;
    SqlList l = createList(50);
    int index = findElem(l, 0);
    std::cout << index;
}

// 运行程序: Ctrl + F5 或调试 >“开始执行(不调试)”菜单
// 调试程序: F5 或调试 >“开始调试”菜单

// 入门使用技巧: 
//   1. 使用解决方案资源管理器窗口添加/管理文件
//   2. 使用团队资源管理器窗口连接到源代码管理
//   3. 使用输出窗口查看生成输出和其他消息
//   4. 使用错误列表窗口查看错误
//   5. 转到“项目”>“添加新项”以创建新的代码文件，或转到“项目”>“添加现有项”以将现有代码文件添加到项目
//   6. 将来，若要再次打开此项目，请转到“文件”>“打开”>“项目”并选择 .sln 文件

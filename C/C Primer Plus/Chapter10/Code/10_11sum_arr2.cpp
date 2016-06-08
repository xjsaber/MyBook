/* 10_11sum_arr2.c -- 数组元素之和 */
#include <stdio.h>
#define SIZE 10
int sump(int * start, int * end);
int main(void) {
    int marbles[SIZE] = {20, 10, 5, 39, 4, 16, 19, 26, 31, 20};
    long answer;

    answer = sump(marbles, marbles + SIZE);
    printf("The total number of marbles is %ld.\n", answer);

    return 0;
}

// 使用指针算法
int sump(int * start, int * end)
{
    int total = 0;

    while (start < end){
        total += *start; //把数组元素的值加起来
        start++;    //把指针指向下一个元素
    }
    return total;
}
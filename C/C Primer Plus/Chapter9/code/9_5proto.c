/* proto.c -- 使用函数原型 */
#include <stdio.h>

int iMax(int, int);
void starBar(void); /* 函数原型 */

int main(void)
{
    printf("The maximum of %d and %d is %d. \n", 3, 5, iMax(3, 5));
    printf("The maximum of %d and %d is %d. \n", 3, 5, iMax(3.0, 5.0));

    return 0;
}

int iMax(int n, int m)/* 定义函数*/
{
    return (n > m ? n : m);
}
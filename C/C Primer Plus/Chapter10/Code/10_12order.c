/* 10_12order.c -- 指针运算中的优先级 */
#include <stdio.h>
int data[2] = { 100, 200};
int moreData[2] = { 300, 400};
int main(void)
{
    int *p1, *p2, *p3;

    p1 = p2 = data;
    p3 = moreData;
    printf(" *p1 = %d, *p2 = %d, *p3 = %d\n", *p1, *p2, *p3);
    printf("*p1++ = %d, *++p2 = %d, (*p3)++ = %d\n", *p1++, *++p2, (*p3)++);
    printf(" *p1 = %d, *p2 = %d, *p3 = %d\n", *p1, *p2, *p3);
    //只有（*p3）++改变了数组元素的值，其他两个操作分别把p1和p2指向数组的下一个元素。
    return 0;
}
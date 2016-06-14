/* 10_14arf.c -- 处理数组的函数 */
#include <stdio.h>
#define SIZE 5
void show_array(const double array[], int n);
void mult_array(double array[], int n, double mult);
int main(void)
{
    double dip[SIZE] = {20.0, 17.66, 8.2, 15.3, 22.22 };

    printf("The original dip array:\n");
    show_array(dip, SIZE);
    mult_array(dip, SIZE, 2.5);
    printf("The dip array after calling mult_array():\n");
    show_array(dip, SIZE);

    return 0;
}

/* 显示数组的内容*/
void show_array(const double array[], int n)
{
    for (int i = 0; i < n; i++) {
        printf("%8.3f ", array[i]);
    }
    putchar('\n');
}

/* 把数组的每个元素都乘以相同的值 */
void mult_array(double array[], int n, double mult)
{
    for (int i = 0; i < n; i++) {
        array[i] *= mult;
    }
}
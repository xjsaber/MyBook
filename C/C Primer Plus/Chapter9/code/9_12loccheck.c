/* 9_12loccheck.c -- 查看变量被存储在何处 */
#include <stdio.h>
void mikado(int); /* 函数原型*/

int main(void)
{
    int pooh = 2, bah = 5; /* main()的局部变量*/

    printf("Int main(), pooh = %d and &pooh = %p\n", pooh, &pooh);
    printf("Int main(), bah = %d and &bah = %p\n", bah, &bah);
    mikado(pooh);

    return 0;
}

void mikado(int bah)/* 定义函数*/
{
    int pooh = 10; /* mikado()的局部变量*/

    printf("In mikado(), pooh = %d and &pooh = %p\n", pooh, &pooh);
    printf("In mikado(), bah = %d and &bah = %p\n", bah, &bah);
}
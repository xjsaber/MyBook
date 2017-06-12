//
// Created by xjsaber on 2017/6/11.
//
#include <stdio.h>
#define PAGES 959
int main(void)
{
    //与带整数字段宽度的转换说明的输出结果相同，默认没有任何修饰符的转换说明，就是这样的打印结果
    printf("*%d*\n", PAGES);
    //其对应的输出结果是2字段宽度
    printf("*%2d*\n", PAGES);
    printf("*%10d*\n", PAGES);
    printf("*%-10d*\n", PAGES);

    return 0;
}

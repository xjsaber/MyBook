//
// Created by xjsaber on 2017/6/11.
//
#include <stdio.h>
int main(void)
{
    int age; //变量
    float assets; //变量
    char pet[30]; //字符数组，用于存储字符串

    printf("Enter your age, assets, and favorite pet.\n");
    scanf("%d %f", &age, &assets);
    scanf("%s", pet);
    printf("%d $%.2f %s\n", age, assets, pet);
}

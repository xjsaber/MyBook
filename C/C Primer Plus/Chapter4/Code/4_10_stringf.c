//
// Created by xjsaber on 2017/6/11.
//
#include <stdio.h>
#define BLURB "Authentic imitation!"
int main(void)
{
    printf("[%2s]\n", BLURB);
    printf("[%24s]\n", BLURB);
    printf("[%24.5s]\n", BLURB);
    printf("[%-24.5s]\n", BLURB);
    // 第1个转换说明是%2s，但是字段被扩大为可容纳字符串中的所有字符。
    // 精度限制了代打印字符的格式。
    // .5告诉printf()只打印5个字符。
    return 0;
}


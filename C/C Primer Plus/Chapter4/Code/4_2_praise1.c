//
// Created by xjsaber on 2017/6/10.
//

#include <stdio.h>
#ifndef TEST_4_2_PRAISE1_H
#define TEST_4_2_PRAISE1_H
#define PRAISE "You are an extraordinary being."

#endif //TEST_4_2_PRAISE1_H

int main(void){
    char name[40];

    printf("What's your name?");
    scanf("%s", name);
    printf("Hello, %s.%s\n", name, PRAISE);

    return 0;
}



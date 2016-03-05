// Test.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#define NONBLANK 'a'	//负责把变量lastc初始化为一个任意的非空格字符

int main()
{
	int c, lastc;	//c记录当前输入字符的ASCII值，
					//lastc则记录着前一个输入字符的ASCII值。
	lastc = NONBLANK;
	while ((c = getchar()) != EOF) {
		if (c != ' ' || lastc != ' ') {
			putchar(c);
		}
		lastc = c;
	}

    return 0;
}


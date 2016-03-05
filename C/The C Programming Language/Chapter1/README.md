#第一章 导言
## 1.1 入门

## 1.2 变量与算术表达式

## 1.3 for语句

##1.4. 符号常量
define指令可以把符号名（或称为符号常量）定义为一个特定的字符串：#define 名字 替换文字

##1.5 字符输入/输出
输入/输出都是按照字符流的方式处理。

Each time it is called, <code>getchar</code> reads the next input character from a text stream and returns that as its value. 
The function <code>putchar</code> prints a character each time


###1.5.1 文件复制
伪代码:

	读一个字符
	while(该字符不是文件结束指示符)
		输出刚读入的字符
		读下一个字符
code:

	int c;
	c = getchar();
	while (c != EOF) {
		putchar(c);
		c = getchar();
	}

转化为：

	int c;
	while (c = getchar() != EOF)
	{
		putchar(c);
	}


###1.5.2 字符计数

###1.5.3 行计数

###1.5.4 单词计数

##1.6 数组

##1.7 函数

##1.8 参数——传值调用

##1.9 字符数组

##1.10 外部变量与作用域 
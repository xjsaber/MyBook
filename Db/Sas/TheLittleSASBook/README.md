# The Little SAS Book中文版 #

所有SAS关键词全部用大写字母。

## 第1章 SAS软件使用入门 ##

### 1.1 SAS语言 ###

SAS语句：每一条SAS语句都以分号结尾

1. 以星号（\*）开头，分号（\;）结尾
2. 以斜杠星号（\/\*），星号斜杆（\*\/）结尾

### 1.2 SAS数据集 ###

变量和观测

数据类型：两种数据类型（数值型和字符型）

缺失数据

SAS数据集大小

SAS数据集与变量命名规则

SAS数据集中存储的说明信息

### 1.3 DATA步和PROC步 ###

SAS程序由两个基本部分构成：DATA步和PROC步。典型的程序由DATA步起始创建SAS数据集，而后将数据传递给PROC步进行处理。

	DATA distance;
		Miles = 26.22;
		Kilometers = 1.61 * Miles;
	PROC PRINT DATA = distance;
	RUN;

DATA步读取、修改数据；PROC步分析数据、执行实用功能以及打印报表。

DATA步以DATA语句开始，DATA关键字后面紧跟着SAS的数据集名称。而SAS过程则以PROC语句开头，该语句由关键字PROC及其后的过程名（比如PRINT、SORT或者MEANS）组成。

|DATA步|PROC步|
|--|--|
|以DATA语句开发|以PROC语句开始|
|读取、修改数据|完成特定分析或者特定功能|
|创建数据集|产生结果或报表|

### 1.4 DATA步的内置循环 ###

DATA步逐行执行语句、逐条处理观测。

### 1.5 选择提交SAS程序的模式 ###






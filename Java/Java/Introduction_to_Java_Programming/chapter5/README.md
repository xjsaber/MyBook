#Java语言程序设计 基础篇 

#第5章 方法

##5.1 引言

##5.2 定义方法

	修饰符 返回值类型 方法名（参数列表）｛
	//方法体
	｝
##5.3 调用方法 ##
调用（call或invoke）

##5.4 void方法举例 ##

##5.5 参数的值传递 ##
在调用SWAP(第12行)前，num1为1而num2为2.在调用swap方法后，num1仍为1，num2仍为2.

## 5.6 模块化代码 ##
	
## 5.7 问题：将十进制数转换为十六进制数 ##	

## 5.8 重载方法 ##	

## 5.9 变量的作用域 ##	
变来那个的作用域（scope of a variable）是指变量可以在程序中引用的范围。在方法中定义的变量称为局部变量（local variable）。
	
## 5.10 Math数学类 ##
三角函数方法（trigonometric method）、指数函数方法（exponent method）和服务方法（service method）

### 5.10.1 三角函数方法 ###

### 5.10.2 指数函数方法 ###

### 5.10.3 取整方法 ###
	
	public static double ceil(double x)

	public static double floor(double x)

	public static double rint(double x)

	public static int round(float x)

	public static long round(double x)

### 5.10.4 min、max和abs方法 ###
重载min和max方法以返回两个数（int、long、float或double型）的最小值和最大值。

### 5.10.5 random方法 ###
	
	(int)(Math.random() * 10) ->返回0到9之间的一个随机整数
	a + Math.random() * b -> 返回一个在a到a+b之间但不包括a+b的随机数
## 5.11 举例：生成随机字符 ##

## 5.12 方法抽象和逐步求精 ##
方法抽象（method abstraction）是通过将方法的使用和它的实现分离来实现的。

### 4.8.3 问题：蒙特卡罗模拟 ###

## 4.9 关键字break和continue ##

## 4.10 （GUI）使用确认对话框控制循环 ##


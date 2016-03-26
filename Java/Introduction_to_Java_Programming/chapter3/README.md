#Java语言程序设计 基础篇 

#第3章 选择

##3.1 引言
选择语句用到用条件。条件就是布尔表达式。
##3.2 boolean数据类型
**表3-1 比较运算符**

|运算符 |名称 |举例 |结果|
| ----:|----:|-----:| -----:|
|< |小于 |radius < 0 |false|
|<= |小于等于 |radius <= 0 |false|
|> |小于等于 |radius > 0 |true|
|>= |大于等于 |radius >= 0 |true|
|== |等于 |radius == 0 |false|
|!= |不等于 |radius <= 0 |true|
##3.3 问题：一个简单的数学学习工具

## 3.4 if语句 ##

## 3.5 问题：猜生日 ##

## 3.6 双向if语句 ##
	if （布尔表达式）{
		布尔表达式为真时执行的语句（组）;
	}
	else {
		布尔表达式为假时执行的语句（组）;
	}

## 3.7 潜逃的if语句 ##
**ex1**

	if (score >= 90.0)
		grade = 'A';
	else
		if (score >= 80.0)
			grade = 'B';
		else
			if (score >= 70.0)
				grade = 'C';
			else
				if (score >= 60.0)
					grade = 'D';
				else
					grade = 'F';

=>	**这样更好**

	if (score >= 90.0)
		grade = 'A';
	else if (score >= 80.0)
		grade = 'B';
	else if (score >= 70.0)
		grade = 'C';
	else if (score >= 60.0)
		grade = 'D';
	else
		grade = 'F';


**ex2**

	if (number % 2 == 0)
		even = true;
	else
		even = false;

=> **这样更好**

	boolean even
	= number % 2 == 0;

## 3.8 选择语句中的常见错误 ##

## 3.9 问题：一个改进的数学学习工具 ##

## 3.10 问题：计算身体质量指数 ##

## 3.11 问题：计算税款 ##

## 3.12 逻辑运算符 ##

## 3.13 问题：判定闰年 ##

## 3.14 问题：彩票 ##

## 3.15 switch语句 ##

## 3.16 条件表达式 ##

## 3.17 格式化控制台输出 ##

## 3.18 运算符的优先级和结合方向 ##
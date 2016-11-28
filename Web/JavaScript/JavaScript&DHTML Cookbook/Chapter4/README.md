# 第4章 变量、函数和流程控制 #

## 4.0 引言 ##

## 4.1 创建JavaScript变量 ##

#### 问题 ####

#### 解答 ####

#### 讨论 ####

	var myVar;
	var myVar, counter, fred, i, j;
	var myVar, counter = 0, fred, i, j;
#### 请参照 ####


## 4.2 创建命名函数 ##

#### 问题 ####

#### 解答 ####

	if (typeof someVal == "number") {
		// OK，作为数值处理
	}

#### 讨论 ####

## 4.3 嵌套命名函数 ##

#### 问题 ####

#### 解答 ####

#### 讨论 ####

#### 请参照 ####

## 4.4 创建匿名函数 ##

#### 问题 ####

#### 解答 ####

#### 讨论 ####

#### 请参照 ####

## 4.5 延迟函数的调用 ##

#### 问题 ####

#### 解答 ####

	var timeoutID = setTimeout("myFunc()", 5000);
	clearTimeout(timeoutID); //取消定时器

#### 讨论 ####

#### 请参照 ####

## 4.6 条件分支 ##

#### 问题 ####

#### 解答 ####

#### 讨论 ####

#### 请参照 ####

## 4.7 漂亮地处理脚本错误 ##

#### 问题 ####

#### 解答 ####
`Math.random()`返回0到1之间的一个伪随机数字。

	var result = Math.floor(Math.random() * (n + 1));
这里的n是这个范围的上限。

	var result = Math.floor(Math.random() * (n + 1)) + m;
这里的m是范围的下限，而n是范围的上限。


#### 讨论 ####

## 4.8 提高脚本的性能 ##
#### 问题 ####

#### 解答 ####
* 不要使用eval()函数
* 不要使用with结构
* 最小化重复表达式的判断

#### 讨论 ####
	
## 2.9 创建Date对象 ##

#### 问题 ####

#### 解答 ####

	var myDate = new Date(yyyy, mm, dd, hh, mm, ss);
	var myDate = new Date(yyyy, mm, dd);
	var myDate = new Date("monthName dd, yyyy hh:mm:ss");
	var myDate = new Date("monthName dd, yyyy");
	var myDate = new Date(epochMilliseconds);

#### 讨论 ####

## 2.10 过去或将来日期的计算 ##
#### 问题 ####

#### 解答 ####

#### 讨论 ####

## 2.11 计算两个日期之间的天数 ##
#### 问题 ####


#### 解答 ####
把两个日期作为参数传递给这个函数`daysBetween()`，从而获得表示两个日期之间的天数和整数。
#### 讨论 ####

#### 请参照 ####


## 2.12 验证日期 ##
#### 问题 ####


#### 解答 ####

#### 讨论 ####

#### 请参照 ####
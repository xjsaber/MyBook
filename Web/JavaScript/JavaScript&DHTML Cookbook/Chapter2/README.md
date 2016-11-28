# 第2章 数字和日期 #

## 2.0 引言 ##

## 2.1 数字和字符串的互相转换 ##

#### 问题 ####

#### 解答 ####

	numValue.toString();
	new String(numValue);
	parseInt(numAsString, 10);
	parseFloat(numAsString);

#### 讨论 ####

## 2.2 校验数字的有效性 ##

#### 问题 ####

#### 解答 ####

	if (typeof someVal == "number") {
		// OK，作为数值处理
	}

#### 讨论 ####

## 2.3 校验数字的相等 ##

#### 问题 ####

#### 解答 ####

	if (typeof someVal == "number") {
		// OK，作为数值处理
	}

#### 讨论 ####

## 2.4 浮点小数的取整 ##

#### 问题 ####

#### 解答 ####

	Math.round(floatingPointValue);

#### 讨论 ####

## 2.5 为文本显示指定数字格式 ##

#### 问题 ####

#### 解答 ####
获取一个包含指定位数的小数的字符串`toFixed()`
获取一个包含指定位数的数字的字符串`toPrecision()`

	var num = 123.45;
	preciseNum = num.toPrecision(7); //preciseNum现在是123.4500
	preciseNum = num.toPrecision(4); //preciseNum现在是123.5
	preciseNum = num.toPrecision(3); //preciseNum现在是123

	preciseNum = num.toPrecision(2); //preciseNum现在是1.2e+2

#### 讨论 ####


## 2.6 十进制和十六进制之间的互相转换 ##

#### 问题 ####

#### 解答 ####
获取一个包含指定位数的小数的字符串`toFixed()`
获取一个包含指定位数的数字的字符串`toPrecision()`

	var decimalVal = parseInt(myHexNumberValue, 16);

#### 讨论 ####

## 2.7 生成伪随机数字 ##

#### 问题 ####

#### 解答 ####
`Math.random()`返回0到1之间的一个伪随机数字。

	var result = Math.floor(Math.random() * (n + 1));
这里的n是这个范围的上限。

	var result = Math.floor(Math.random() * (n + 1)) + m;
这里的m是范围的下限，而n是范围的上限。


#### 讨论 ####

## 2.8 三角函数的计算 ##
#### 问题 ####

#### 解答 ####

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
# 第5章 浏览器特性检测 #

## 5.0 引言 ##

## 5.1 检测浏览器的种类 ##

#### 问题 ####

#### 解答 ####

#### 讨论 ####

## 5.2 检测早期的浏览器版本 ##

#### 问题 ####

#### 解答 ####

#### 讨论 ####

## 5.3 检测 Internet Explorer 的版本 ##

#### 问题 ####

#### 解答 ####

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
	
## 5.9 检测对象属性和方法的支持 ##

#### 问题 ####

#### 解答 ####

	if (objectTest && objectPropertyTest) {
		// 可以使用该属性
	}
#### 讨论 ####

## 5.10 检测W3C DOM标准的支持 ##
#### 问题 ####

#### 解答 ####
implementation的`hasFeature()`方法

	var cssFile;
	if (document.implementation.hasFeature("CSS2", "2.0")) {
		cssFile = 'styles/corpStyle2.css';
	} else {
		cssFile = 'styles/corpStyle1.css';
	}
#### 讨论 ####

## 5.11 检测浏览器的书写语言 ##
#### 问题 ####

#### 解答 ####
navigator对象的属性
#### 讨论 ####

#### 请参照 ####

## 5.12 检测Cookie的是否可用 ##
#### 问题 ####

#### 解答 ####
	
	if (navigator.cookieEnabled) {
		// 在这里调用cookie的语句
	}
#### 讨论 ####

#### 请参照 ####

## 5.13 为特定浏览器或属性定义链接 ##
#### 问题 ####

#### 解答 ####
	

#### 讨论 ####

#### 请参照 ####

## 5.14 多个浏览器版本的检验 ##
#### 问题 ####

#### 解答 ####
	

#### 讨论 ####

#### 请参照 ####
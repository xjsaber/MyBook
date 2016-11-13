# 第5章 初探JavaScript #

## 5.1 准备使用JavaScript ##

## 5.2 使用语句 ##

## 5.3 定义和使用函数 ##

### 5.3.1 定义带参数的函数 ###

### 5.3.2 定义会返回结果的函数 ###
return

## 5.4 使用变量和类型 ##
var。
局部变量、全局变量

### 5.4.1 使用基本类型 ###
string、number和boolean

#### 1.字符串类型 ####
string类型，夹在一对双引号或单引号之间的一串字符表示。

#### 2.布尔类型 ####
boolean类型有两个值：true和false

#### 3.数值类型 ####
整数和浮点数（也称实数）都用number类型表示。

### 5.4.2 创建对象 ###
调用new Object()的方式创建了一个对象，然后将其赋给一个名未myData的变量。

#### 1.使用对象字面量 ####

#### 2.将函数用做方法 ####

#### 5.4.3 使用对象 ####

#### 1.读取和修改属性值 ####

#### 2.枚举对象属性 ####
for...in

#### 3.增删数据和方法 ####

#### 4.判断对象是否具有某个属性 ####
可以用in表达式判断对象是否具有某个属性

## 5.5 使用JavaScript ##

### 5.5.1 相等和等同运算符 ###

	== 判断值是否相等
	=== 判断值和类型是否都相同

### 5.5.2 显式类型转换 ###
字符串连接运算符（+）比加法运算符（也是+）优先级更高。

#### 1.将数值转换为字符串 ####

#### 2.将字符串转换为数值 ####
	
	var firstVal = "5";
	var secondVal = "5";
	var result = Number(firstVal) + Number(secondVal);

Number函数解析字符串值的方式狠严格，在这方面parseInt和parseFloat函数更为灵活，后面这两个函数还会忽略数字字符后面的非数字字符。

|函数|说明|
|---|---|
|Number(<str>)|通过分析指定字符串，生成一个整数或实数值|
|parseInt(<str>)|通过分析指定字符串，生成一个整数值|
|parseFloat(<str>)|通过分析指定字符串，生成一个整数或实数值|

## 5.6 使用数组 ##

	var myArray = new Array();
	myArray[0] = 100;
	myArray[1] = "Adam";
	myArray[2] = true;
调用 new Array()创建一个新的数组。

### 5.6.1 使用数组字面量 ###
	
	var myArray = [100, "Adam", true];

### 5.6.2 读取和修改数组内容 ###
押送读取指定索引位置的数组元素值，应使用一对方括号（[和]）并将索引值放在方括号间。

### 5.6.3 枚举数组内容 ###

### 5.6.4 使用内置的数组方法 ###

concat
join
pop
push
reverse
shift
slice
sort
unshift

## 5.7 处理错误 ##
try...catch

## 5.8 比较undefined和null值 ##
undefined 读取未赋值的变量或试图读取对象没有的属性时得到的就是undefined值。
null

### 5.8.1 检查变量或属性是否为undefined或null ###

像检查某属性是否为null或undefined，那么只要使用if语句和逻辑非运算符（!）即可。

### 5.8.2 区分null和undefined ###
想同等对待undefined值和null值，那么应该是用相等运算符（==），如果要区分null和undefined，则应使用等同运算符（===）。

## 5.9 常用的JavaScript工具 ##

### 5.9.1 使用JavaScript调试器 ###

### 5.9.2 使用JavaScript库 ###

## 5.10 小结 ##




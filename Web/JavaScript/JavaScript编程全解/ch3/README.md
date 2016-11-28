# 第3章 JavaScript的数据类型 #

## 3.1 数据类型的定义 ##

### 3.1.1 在数据类型方面与Java作比较 ###

动态数据类型与静态数据类型

基于类与基于原型

### 3.1.2 基本数据类型和引用类型 ###
基本数据类型变量直接保存有数值等类型的数据的值，而引用类型变量则保存有对象的引用。

## 3.2 内建数据类型该要 ##

### 3.3.5 字符串对象 ###
使用new运算符，来显式地生成一个字符串对象。

### 3.3.6避免昏庸字符串值和字符串对象 ###

### 3.3.7 调用String函数 ###

### 3.3.8 String类的功能 ###
String(value) 将参数value转换为字符串值类型
new String(value) 生成String的实例

### 3.3.9 非破坏性的方法 ###

## 3.4 数值型 ##

### 3.4.1 数值字面量 ###
数值的内部结构为64位的浮点小数。

### 3.4.2 数值型的运算 ###
对于数值可以进行+(加法)、-（减法）、*(乘法)、/（除法）四则运算。
### 3.4.3 有关浮点数的常见注意事项 ###

### 3.4.4 数值类（Number类） ###
	var nobj = new Number(1);
	typeof nobj;
	object
### 3.4.5 调用Number函数 ###
	var n1 = Number(1);
	typeof n1;
	number
### 3.4.6 Number类的功能 ###
Number([value]) 将参数value转换为数值类型
new Number([value]) 生成Number类实例
### 3.4.7 边界值与特殊数值 ###

### 3.4.8 NaN ###
对NaN进行任何运算，其结果都是NaN。

## 3.5 布尔型 ##

### 3.5.1 布尔值 ###

## 3.6 null型 ##
对null值进行typeof运算得到的结果也是“object”。

## 3.7 undefined型 ##
undefined型只能够取undefined这一个值。对undefined值进行typeof运算，其结果为“undefined”。

ECMAScript的第五版中undefined变为只读变量。

undefined值是一种有着非常高的潜在出错风险的语言特性，所以在

## 3.9 数据类型转换 ##

### 3.9.1 从字符串值转换为数值 ###

### 3.9.2 从数值转换为字符串值 ###

### 3.9.4 转换为布尔型 ###

* 数值0
* 数值NaN
* null值
* undefined值
* 字符串值"（空字符串值）

### 3.9.5 其他数据类型转换 ###

| --: | -- | -- |
| true | 1 | 'true' |
| false | 0 | 'false' |
| null值 | 0	 | 'null' |
| undefined值 | NaN | 'undefined' |

### 3.9.6 从Object类型转换位基本数据类型 ###

### 3.9.7 从基本数据类型转换为Object类型 ###

|--:|:--|
| 字符串型 | String对象 |
| 数值型 | Number对象 |
| 布尔型 | Boolean对象 |
| null | Error对象 |
| undefined型 | Error对象 |
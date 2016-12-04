# 第4章 对象与类 #

## 4.1 面向对象程序设计概念 ##

## 4.1.1 类 ##

## 4.1.2 对象 ##

## 4.1.3 识别类 ##

## 4.1.4 类之间的关系 ##

* 依赖（“uses-a”）
* 聚合（“has-a”）
* 继承（“is-a”）

表达式关系的UML符号
![表达式关系的UML符号](img/2016-12-03_18-38-40.jpg)

继承 “is-a”

## 4.2 使用预定义类 ##

### 4.2.1 对象与对象变量 ###

### 4.2.2 Java类库中的GregorianCalendar类 ###

### 4.2.3 更改器方法与访问器方法 ###

	GregorianCalendar now = new GregorianCalenda();
	int month = now.get(Calendar.MONTH);
	int weekday = now.get(Calendar.DAY_OF_WEEK);

	dealine.set(Calendar.YEAR, 2001);
	dealine.set(Calendar.MONTH, Ca;emdar.APRIL);
	dealine.set(Calendar.DAY_OF_MONTH, 15);

## 4.3 用户自定义类 ##
强类型语言

### 4.3.1 Employee类 ###
[Employee类](code/4_1_Employee.java)

### 4.3.2 多个源文件的使用 ###

### 4.3.3 剖析Emplyee类 ###

### 4.3.4 从构造器开始 ###

### 4.3.5 隐式参数与显式参数 ###

### 4.3.6 封装的优点 ###
不要编写返回引用可变对象的访问器方法，如果需要访问一个可变对象，则应该先对它进行克隆（clone）。

	public Date getHireDay()
	{
		return hireDay.clone();
	}

### 4.3.7 基于类的访问权限 ###

### 4.3.8 私有方法 ###

### 4.3.9 final实例域 ###

	final
## 4.4 静态域与静态方法 ##

### 4.4.1静态域 ###

### 4.4.2静态常量 ###

### 4.4.3静态方法 ###

### 4.4.4工作方法 ###

	NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
	NumberFormat percentFormatter = NumberFormat.getPercentInstance();
	double x = 0.1;
	System.out.println(currencyFormatter.format(x));
	System.out.println(percentFormatter.format(x));
* 无法命名构造器。
* 当使用构造器时，无法改变所构造的对象类型
### 4.4.5main方法 ###

## 4.5 方法参数 ##

## 4.6 对象构造  ##

### 4.6.1 子重载 ###

### 4.6.2 默认域初始化 ###

### 4.6.3 无参数的构造器 ###

### 4.6.4 显示域初始化 ###

### 4.6.5 参数名 ###

### 4.6.6 调用另一个构造器 ###
关键字this引用方法的隐式参数。

### 4.6.7 初始化快 ###

### 4.6.8 对象析构与finalize方法 ###
finalize方法

## 4.7 包 ##
建议将公司的因特网域名以逆序的形式作为包名
### 4.7.1 类的导入 ###

### 4.7.2 静态导入 ###

	import static java.lang.System.*;

### 4.7.3 将类放入包中 ###

### 4.7.4 包作用域 ###

## 4.8 类路径 ##
类存储在文件系统的子目录中。类的路径必须与包名匹配。

## 4.9 文档注释 ##
javadoc

### 4.9.1 注释的插入 ###

### 4.9.2 类注释 ###

### 4.9.3 方法注释 ###

* @param变量描述
* @return描述
* @throws类描述

### 4.9.4 域注释 ###

### 4.9.5 通用注释 ###

### 4.9.6 包与概述注释 ###

## 4.10 类设计技巧 ##

1. 一定要保证数据私有
2. 一定要对数据初始化
3. 不要在类中使用过多的基本类型
4. 不是所有的域都需要独立的域访问器和域更改器。
5. 将职责过多的类进行分解
6. 类名和方法名要能够体现它们的职责
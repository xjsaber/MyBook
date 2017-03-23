# 第12章 泛型程序设计 #

## 12.1 为什么要使用泛型程序设计 ##
泛型程序射击（Generic programming）意味着编写的代码可以被很多不同类型的对象所重用。

	ArrayList<String> files = new ArrayList<>();

#### 谁想成为泛型程序员？ ####

## 12.2 定义简单泛型类 ##

## 12.3 泛型方法 ##

	class ArragAlg{
		public static <T> getMiddle(T... a){
			return a[a.length / 2];
		}
	}
	
	String middle = ArrayAlg.<String>getMiddle("00", "zz","父子情");

## 12.4 类型变量的限定 ##

一个类型变量或通配符可以有多个限定，例如：

	T extends Comparable & Serializable

限定类型用“&”分隔，而逗号用来分隔类型变量

## 12.5 泛型代码和虚拟机 ##

虚拟机没有泛型类型对象——所有对象都属于普通类。无论何时定义一个泛型类型，都自动提供了一个相应的原始类型（raw type）。原始类型的名字就是删去类型参数后的泛型类型名。擦除（erased）类型变量，并替换为限定类型（无限定的变量用Object）

### 12.5.1 翻译泛型表达式 ###
当程序调用泛型方法时，如果擦除返回类型，编译器插入强制类型转换。

	Pair<Employee> buddies = ...;
	Employee buddy = buddies.getFirst();

* 对原始方法Pair.getFirst的调用
* 对返回的Object类型强制转换为Employee类型。

### 12.5.2 翻译泛型方法 ###

Java泛型转换：
* 虚拟机中没有泛型，只有普通的类和方法。
* 所有的类型参数都用它们的限定类型替换。
* 桥方法被合成来保持多态。
* 为保持类型安全性，必要时插入强制类型转换。

### 12.5.3 调用遗留代码 ###

## 12.6 约束与局限性 ##
大多数限制都是类擦除引起的。

### 12.6.1 不能用基本类型实例化类型参数 ###
不能用类型参数代替基本类型。因此，没有Pair<double>，只有Pair<Double>。当然，其原因时类型擦除。Pair类型含有Object类型，而Object不能存储double值。Java只有8中基本类型，当包装类型（wrapper type）不能接收替换时，可以使用独立的类和方法处理它们。

### 12.6.2 运行时类型查询只适用于原始类型 ###


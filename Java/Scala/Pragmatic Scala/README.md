# Pragmatic Scala #

## 引言 ##

Scala是编译成JVM字节码的最强大的编程语言之一。

## Scala的编程风格 ##

## Scala和其他编程语言 ##

Scala中基于Actor的并发模型就是受Erlang大行其道的并发模型启发。

Scala中的静态类型和类型推导（type inference）也是受到别的编程语言（如Haskell）的影响。

小试牛刀

## 第1章 探索 Scala ##

### 1.1 Scala的特性 ###

* 同时支持命令式和函数式风格；
* 纯面向对象；
* 强制合理的静态类型和类型推导；
* 简洁和富有表现力；
* 能和Java无缝地互操作；
* 基于精小的内核构建；
* 高度可伸缩，仅用少量代码就可以创建高性能的应用程序；
* 具有一个强大易用的并发模型。

### 1.2 以少胜多 ###



### 1.3 函数式编程 ###

### 1.4 小结 ###

函数式风格、易用的并发、集合的使用、酷炫的迭代器、不可变性编程、元组的使用。

## 第2章 体验Scala ##

### 2.1 使用RERL ###

REPL（read-eval-print loop）

### 2.2 命令行上的Scala ###



### 2.3 以独立脚本运行Scala代码 ###

#### 2.3.1 在类Unix系统上以独立脚本方式运行 ####

#### 2.3.2 在Windows上以独立脚本方式运行 ####

### 2.3.3 Scala的IDE支持 ###

### 2.4 编译Scala ###

### 2.5 小结 ###

## 第3章 从Java到Scala ##

### 3.1 Scala：简洁的Java ###

#### 3.1.1 Less Boilerplate 减少样板代码 ####

	//Java code
	public class Greetings {
		public static void main(String[] args) {
			for (int i = 1; i < 4; i++) {
				System.out.print(i + ",");
			}
			System.out.println("Scala Rocks!!!");
		}
	}
	//Scala code
	for (i <- 1 to 3) {
		print(s"$i,")
	}
	println("Scala Rocks!!!")

#### 3.1.2 更多便利特性 ####

val或var定义变量，使用val定义的变量是不可变的，即初始化后不能更改。那些使用var定义（不推荐使用）的变量是可变的，可以被改变任意次。

#### 3.1.3 Leaning Toward Functional Style 转向函数式风格 ####



### 3.2 Java基本类型对应的Scala类 ###

利用自动装箱（autoboxing）机制，可以将原始类型视为对象。

Scala将所有的类型都视为对象。

### 3.3 元祖和多重赋值 ###
	
元祖是不可变的对象序列，创建时使用逗号分隔。

### 3.4 变长参数 ###

#### 3.4.1 传递变长参数（Varargs） ####

#### 3.4.2 参数默认值 ####

#### 3.4.3 使用命名参数 ####

Scala的类型检查能够防止你向方法传入错误类型的参数。

## 细粒度的访问控制 ##

## 3.10 Scala和Java的差异 ##



## 3.10.1 赋值的结果 ##


## 3.11 默认访问修饰符 ##

Scala的访问修饰符（access modifier）和Java有如下不同点：

* 不指定任何访问修饰符，那么Java会默认为包内部可见，而Scala则默认公开；
* Java主张全有或全无，也就是说当前包的所有类可见或者对所有都不可见。而Scala对可见性的控制是细粒度。
* Java的protected是宽泛的，器作用域包括在任意包中的派生类和当前包中任意类。而Scala的protected与C++和C#的类似，只有派生类能够访问。然而，在Scala中protected还有相当自由灵活的用法。
* Java的封装是类级别的。在一个类的实例的方法中访问该类的任何对象的私有城院变量和方法。在Scala中是这样，然而，定制成能够在当亲实例的方法中访问，这样就和Rubuy比较像了。

### 3.11.1 定制访问修饰 ###

### 3.11.2 Scala的protected ###

### 3.11.3 细粒度的访问控制 ###

Scala在protected修饰符上的限制比Java更多

## 3.12 小结 ##

Scala提供了Java中不支持的特性：元祖、多重赋值、命名参数、默认值、隐式参数、多行字符串、字符串插值以及更加灵活的访问修饰符。


## 第4章 处理对象 ##

### Creating and Using Classes ###

### 创建实例 ###

	new StringBuilder()

Scala把空括号看作多余的。

	new StringBuilder

#### Creating Classes ####

	class Car(val year: Int) {
		private var milesDriven: Int = 0

		def miles: Int = milesDriven

		def drive(distance: Int): Unit = {
			milesDriven += Math.abs(distance)
		}
	}

#### Defining Fields，Methods，and Constructors ####

### Following the JavaBean Convention 遵循JavaBean惯例 ###

### Type Aliasing 类型别名 ###

### Extending a Class ###

### 扩展一个类 ###

### 参数化类型 ###


## 第5章 ##

## 第6章 ##

## 第7章 特质 ##

## 第12章 并行集合和惰性求值 ##


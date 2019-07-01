# Pragmatic Scala #

## 引言 ##

Scala是编译成JVM字节码的最强大的编程语言之一。

## Scala的编程风格 ##

## Scala和其他编程语言 ##

Scala中基于Actor的并发模型就是受Erlang大行其道的并发模型启发。

Scala中的静态类型和类型推导（type inference）也是受到别的编程语言（如Haskell）的影响。

小试牛刀

第一部分 小试牛刀

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


## 3.6 字符串和多行原始字符串 ##

Scala能将String转化为scala.runtime.RichString。新增一些有用的方法，如capatilize()、lines()和reverse()。

多行字符串放入`"""..."""`

	stripMargin()
方法将起始管道符号(|)前面的空白或者控制字符都去掉了。在不是任何行的起始位置以外的其他位置，管道符号都会被保留。

## 3.7 字符串插值 ##

	val message = s"A discount of $dusciount% has been applied"

## 3.8 合理的约定 ##

* 支持脚本
* return是可选的。
* 分号是可选的
* 类和方法默认就是公开的。
* Scala提供轻便的语法以创建JavaBeans。
* 括号和点号也是可选的。

scala.Predef对象以及它们相应的类和成员。

* java.lang
* scala
* scala.Predef

Predef对象包含了类型、隐式转换以及Scala中常用的一些方法。

## 3.10 Scala和Java的差异 ##


### 3.10.1 赋值的结果 ###


### 3.10.2 赋值的结果 ###



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


# 第4章 处理对象 #

## 4.1 Creating and Using Classes 4.1 创建并使用 ##

### 4.1.1 创建实例 ###

	new StringBuilder()

Scala把空括号看作多余的。

	new StringBuilder

### 4.1.2 Creating Classes 创建类  ###

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


## 4.7 创建枚举类 ##

枚举是一个扩展了Enumberation类的对象。使用关键字val定义了枚举所选中的实力。

	pageage finance.currencies

	object Currency extends Enumberation {
		type Currency = Value
		val CNY, GBP, INR, JPY. NOK, PLN, SEK, USD = Value
	}

	object UseCurrency extends App {
		Currency.values.foreach { currency => println(currency) }
	}

## 4.8 包对象 ##

Java的包中只含有接口、类、枚举和注解类型。

Scala更进一步，包中还可以有变量和函数。都放在一个称为包对象（package object）的特殊的单例对象中。



## 第5章 善用类型 ##

静态类型和类型推断

Any、Nothing和Option

### 5.1 类型推断 ###

#### 5.1.1 简单的类型推断  ####

	val greet: String = "Anoy!"

1. 要求将类型放在变量名之后，选择一个好的变量名比标准类型更加重要。
2. 类型信息是可选的

	val greet = "Anoy!"

#### 5.1.2 针对泛型和集合的类型推断 ####

### 5.2 基础类型 ###

Scala的Any类型是所有类型的超类型。

#### 5.2.1 Any类型 ####

Any类型可以作为任意类型对象的一个通用引用。Any是一个抽象类。`!=()、==()、asInstanceOf()、equals()、hashCode()、IsInstanceOf()和toString()`

Any类型的直接后裔是AnyVal和AnyRef类型。AnyVal是所有Scala中所有值类型（如Int、Double等）的基础类型，并映射到了Java的原始类型。AnyRef直接映射到Java的Object。

#### 5.2.2 关于Nothing ####

`Nothing`是一切类型的子类型。

#### 5.2.3 Option类型 ####

返回空集合，而不是`null`引用。通过调用返回的Option[T]上的getOrElse()方法，可以主动表明自己的意图，即防止结果不存在，也就是None

#### 5.2.4 Either类型 ####

当接受到一个Either类型的值时，可以使用模式匹配来提取其中的值。

	def displayResult(result: Either[String, Double]): Unit = {
		println(s"Raw: $result")
		result match {
			case Right(value) => println(s"result $value")
			case Left(err) => println(s"Error: $err")
		}
	}


### 5.3 返回值类型推断 ###

自动推断取决于你如何定义函数。

只有当使用等号（=）将方法的声明和方法的主体部分分开时，Scala的返回值类型推断才回生效。否则，该方法将会返回一个Unit，等效于Java中Void。

	def function1 {Math.sqrt(4)} Unit
	def function2 = {Math.sqrt(4)} Double
	def function3 = Math.sqrt(4) Double
	def function4:  Double = {Math.sqrt(4)} Double

function4指定返回类型

### 5.4 参数化类型的形变 ###

#### 5.4.1 斜变和逆变 ####

#### 5.4.2 支持斜变 ####

#### 5.4.3 支持逆变 ####

### 5.5 隐式类型转换 ###

#### 5.5.1 隐式函数 ####

DSL——一种流式语法（fluent syntax）

	2 days ago
	5 days from_now

### 5.6 值类 ###

第二部分 深入Scala

## 第6章 函数值和闭包 ##

## 第7章 特质 ##

## 第8章 集合 ##

## 第9章 模式匹配和正则表达式 ##

## 第10章 处理异常 ##

## 第11章 递归 ##

## 第12章 并行集合和惰性求值 ##

## 第13章 使用Actor编程 ##

## 第14章 和Java进行互操作 ##

## 第15章 使用Scala创建应用程序  ##

## 第16章 单元测试 ##


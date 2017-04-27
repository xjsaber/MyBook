# Scala  #

## Scala 基础语法 ##

Scala 与 Java 的最大区别是：Scala 语句末尾的分号 ; 是可选的。
* 对象 - 对象有属性和行为。
* 类 - 类是对象的抽象，而对象是类的具体实例。
* 方法 - 方法描述的基本的行为，一个类可以包含多个方法。
* 字段 - 每个对象都有它唯一的实例变量集合，即字段。对象的属性通过给字段赋值来创建。
Scala 基本语法需要注意以下几点：
1. 区分大小写，Scala是大小写敏感的
2. 类名，对于所有的类名的第一个字母要大写。如果需要使用几个单词来构成一个类的名称，每个单词的第一个字母要大写。示例：class MyFirstScalaClass
3. 方法名称 - 所有的方法名称的第一个字母用小写。如果若干单词被用于构成方法的名称，则每个单词的第一个字母应大写。示例：def myMethodName()
4. 程序文件名 - 程序文件的名称应该与对象名称完全匹配。保存文件时，应该保存它使用的对象名称（记住Scala是区分大小写），并追加".scala"为文件扩展名。 （如果文件名和对象名称不匹配，程序将无法编译）。示例: 假设"HelloWorld"是对象的名称。那么该文件应保存为'HelloWorld.scala"
5. def main(args: Array[String]) - Scala程序从main()方法开始处理，这是每一个Scala程序的强制程序入口部分。
标识符
Scala 可以使用两种形式的标志符，字符数字和符号。
字符数字使用字母或是下划线开头，后面可以接字母或是数字，符号"$"在 Scala 中也看作为字母。然而以"$"开头的标识符为保留的 Scala 编译器产生的标志符使用，应用程序应该避免使用"$"开始的标识符，以免造成冲突。
Scala 的命名规则采用和 Java 类似的 camel 命名规则，首字符小写，比如 toString。类名的首字符还是使用大写。此外也应该避免使用以下划线结尾的标志符以避免冲突。符号标志符包含一个或多个符号，如+，:，? 等，比如:
+ ++ ::: < ?> :->



## Scala 注释 ##

/* 这是一个 Scala 程序
* 这是一行注释
* 这里演示了多行注释
*/
// 输出 Hello World
// 这是一个单行注释
空行和空格
一行中只有空格或者带有注释，Scala 会认为其是空行，会忽略它。标记可以被空格或者注释来分割。
Scala 包
定义包
Scala 使用 package 关键字定义包，在Scala将代码定义到某个包中有两种方式：
1. 和 Java 一样，在文件的头定义包名，这种方法就后续所有代码都放在该包中
package com.xjsaber
class HelloWorld
package com.runoob {
class HelloWorld 
}
2. 可以在一个文件中定义多个包。
使用 import 关键字引用包。
引用
Scala 使用 import 关键字引用包。
import java.awt.Color // 引入Color
import java.awt._ // 引入包内所有成员
def handler(evt: event.ActionEvent) { 
// java.awt.event.ActionEvent
... // 因为引入了java.awt，所以可以省去前面的部分
}
import语句可以出现在任何地方，而不是只能在文件顶部。import的效果从开始延伸到语句块的结束。这可以大幅减少名称冲突的可能性。
如果想要引入包中的几个成员，可以使用selector（选取器）：
import java.awt.{Color, Font}
// 重命名成员
import java.util.{HashMap => JavaHashMap}
// 隐藏成员
import java.util.{HashMap => _, _} // 引入了util包的所有成员，但是HashMap被隐藏了

## Scala 数据类型 ##

Scala 与 Java有着相同的数据类型

### 整型字面量 ###
整型字面量用于 Int 类型，如果表示 Long，可以在数字后面添加 L 或者小写 l 作为后缀。

### 浮点型字面量 ###
如果浮点数后面有f或者F后缀时，表示这是一个Float类型，否则就是一个Double类型的。

### 符号字面量 ###
符号字面量被写成： '<标识符> ，这里 <标识符> 可以是任何字母或数字的标识（注意：不能以数字开头）。这种字面量被映射成预定义类scala.Symbol的实例。

### 字符字面量  ###
在scala中字符类型表示为半角单引号(')中的字符

### 字符串字面量 ###
字符串表示方法是在双引号中(") 包含一系列字符

### 多行字符串的表示方法 ###
多行字符串用三个双引号来表示分隔符，格式为：""" ... """。

	val foo = """菜鸟教程
	www.runoob.com
	www.w3cschool.cc
	www.runnoob.com
	以上三个地址都能访问"""

### Null 值 ###
空值是 scala.Null 类型。

Scala.Null和scala.Nothing是用统一的方式处理Scala面向对象类型系统的某些"边界情况"的特殊类型。

Null类是null引用对象的类型，它是每个引用类（继承自AnyRef的类）的子类。Null不兼容值类型。

### Scala 转义字符 ###

|转义字符|Unicode|描述|
|--|--|--|
|\b|\u0008|退格(BS) ，将当前位置移到前一列
|\t|\u0009|水平制表(HT) （跳到下一个TAB位置)|
|\n|\u000c|换行(LF) ，将当前位置移到下一行开头|
|\f|\u000c|换页(FF)，将当前位置移到下页开头|  
|\r|\u000d|回车(CR) ，将当前位置移到本行开头|
|\"|\u0022|代表一个双引号(")字符| 
|\'|\u0027|代表一个单引号（'）字符| 
|\\|\u005c|代表一个反斜线字符 '\' | 

0 到 255 间的 Unicode 字符可以用一个八进制转义序列来表示，即反斜线‟\‟后跟 最多三个八进制。
在字符或字符串中，反斜线和后面的字符序列不能构成一个合法的转义序列将会导致 编译错误。

## Scala 变量 ##

### 变量声明 ###
一、变量： 在程序运行过程中其值可能发生改变的量叫做变量。如：时间，年龄。
二、常量 在程序运行过程中其值不会发生变化的量叫做常量。如：数值 3，字符'A'。
在 Scala 中，使用关键词 "var" 声明变量，使用关键词 "val" 声明常量。
var myVar : String = "Foo"
var myVar : String = "Too"
声明常量实例如下：
val myVal : String = "Foo"
变量类型声明

## Scala 访问修饰符 ##
Scala 访问修饰符基本和Java的一样，分别有：private，protected，public。如果没有指定访问修饰符符，默认情况下，Scala对象的访问级别都是 public。Scala 中的 private 限定符，比 Java 更严格，在嵌套类情况下，外层类甚至不能访问被嵌套类的私有成员。

### 私有(Private)成员 ###
用private关键字修饰，带有此标记的成员仅在包含了成员定义的类或对象内部可见，同样的规则还适用内部类。

### 保护(Protected)成员 ###
在 scala 中，对保护（Protected）成员的访问比 java 更严格一些。因为它只允许保护成员在定义了该成员的的类的子类中被访问。而在java中，用protected关键字修饰的成员，除了定义了该成员的类的子类可以访问，同一个包里的其他类也可以进行访问。 

### 公共(Public)成员 ###
Scala中，如果没有指定任何的修饰符，则默认为 public。这样的成员在任何地方都可以被访问。 

### 作用域保护 ###
Scala中，访问修饰符可以通过使用限定词强调。格式为:

	private[x] 
	或 	
	protected[x]

这里的x指代某个所属的包、类或单例对象。如果写成private[x],读作"这个成员除了对[…]中的类或[…]中的包中的类及它们的伴生对像可见外，对其它所有类都是private。

## Scala 运算符 ##

## Scala IF...ELSE 语句 ##

## Scala 循环 ##
函数是一组一起执行一个任务的语句。 您可以把代码划分到不同的函数中。如何划分代码到不同的函数中是由您来决定的，但在逻辑上，划分通常是根据每个函数执行一个特定的任务来进行的。

### 函数声明 ###

	def functionName ([参数列表]) : [return type]

如果你不写等于号和方法主体，那么方法会被隐式声明为"抽象(abstract)"，包含它的类型于是也是一个抽象类型。

### 函数定义 ###
方法定义由一个def 关键字开始，紧接着是可选的参数列表，一个冒号"：" 和方法的返回类型，一个等于号"="，最后是方法的主体。

	def functionName ([参数列表]) : [return type] = {
	   function body
	   return [expr]
	}
	以上代码中 return type 可以是任意合法的 Scala 数据类型。参数列表中的参数可以使用逗号分隔。

### 函数调用 ###
Scala 提供了多种不同的函数调用方式：

	// 标准格式
	functionName( 参数列表 )
	// 实例的对象来调用，我们可以使用类似java的格式 (使用 . 号)
	[instance.]functionName( 参数列表 )

[函数传名调用(Call-by-Name)](function.md)
[指定函数参数名](function.md)
[函数 - 可变参数](function.md)
[递归函数](function.md)
[默认参数值](function.md)
[高阶函数](function.md)
[内嵌函数](function.md)
[匿名函数](function.md)
[偏应用函数](function.md)
[函数柯里化(Function Currying)](function.md) 

## Scala 闭包 ##

闭包是一个函数，返回值依赖于声明在函数外部的一个或多个变量。闭包通常来讲可以简单的认为是可以访问一个函数里面局部变量的另外一个函数。

	val multiplier = (i:Int) => i * 10 
	val multiplier = (i:Int) => i * factor
	var factor = 3 
	val multiplier = (i:Int) => i * factor 
	
这样定义的函数变量 multiplier 成为一个"闭包"，因为它引用到函数外面定义的变量，定义这个函数的过程是将这个自由变量捕获而构成一个封闭的函数。
object Test { 
	def main(args: Array[String]) { 
		println( "muliplier(1) value = " + multiplier(1) ) 
		println( "muliplier(2) value = " + multiplier(2) ) 
	} 
	var factor = 3 
	val multiplier = (i:Int) => i * factor 
}

## Scala 字符串 ##

var greeting = "Hello World!";
或
var greeting:String = "Hello World!";
字符串长度
var palindrome = "www.runoob.com";
var len = palindrome.length();
字符串连接
String 类中使用 concat() 方法来连接两个字符串：
string1.concat(string2);
同样你也可以使用加号(+)来连接；
创建格式化字符串
String 类中你可以使用 printf() 方法来格式化字符串并输出，String format() 方法可以返回 String 对象而不是 PrintStream 对象。
var fs = printf("浮点型变量为 " +
"%f, 整型变量为 %d, 字符串为 " +
" %s", floatVar, intVar, stringVar)

## Scala Collection ##

Scala 集合分为可变的和不可变的集合。

可变集合可以在适当的地方被更新或扩展。这意味着你可以修改，添加，移除一个集合的元素。
而不可变集合类，相比之下，永远不会改变。不过，你仍然可以模拟添加，移除或更新操作。但是这些操作将在每一种情况下都返回一个新的集合，同时使原来的集合不发生改变。

[collection](collection.md)

## Scala Trait(特征) ##
Scala Trait(特征) 相当于 Java 的接口，与接口不同的是，它还可以定义属性和方法的实现。

一般情况下Scala的类只能够继承单一父类，但是如果是 Trait(特征) 的话就可以继承多个，从结果来看就是实现了多重继承。
Trait(特征) 定义的方式与类类似，但它使用的关键字是 trait。

### 特征构造顺序 ###
特征也可以有构造器，由字段的初始化和其他特征体中的语句构成。这些语句在任何混入该特征的对象在构造是都会被执行。 
构造器的执行顺序： 

* 调用超类的构造器；
* 特征构造器在超类构造器之后、类构造器之前执行；
* 特质由左到右被构造；
* 每个特征当中，父特质先被构造；
* 如果多个特征共有一个父特质，父特质不会被重复构造
* 所有特征被构造完毕，子类被构造。

构造器的顺序是类的线性化的反向。线性化是描述某个类型的所有超类型的一种技术规格。 

## Scala 模式匹配 ##

一个模式匹配包含了一系列备选项，每个都开始于关键字 case。每个备选项都包含了一个模式及一到多个表达式。箭头符号 => 隔开了模式和表达式。

match 对应 Java 里的 switch，但是写在选择器表达式之后。即： 选择器 match {备选项}。
match 表达式通过以代码编写的先后次序尝试每个模式来完成计算，只要发现有一个匹配的case，剩下的case不会继续匹配。 

## Scala 提取器(Extractor) ##

提取器是从传递给它的对象中提取出构造该对象的参数。
Scala 提取器是一个带有unapply方法的对象。unapply方法算是apply方法的反向操作：unapply接受一个对象，然后从对象中提取值，提取的值通常是用来构造该对象的值。

apply 和 unapply 方法。通过 apply 方法我们无需使用 new 操作就可以创建对象。所以你可以通过语句 Test("Zara", "gmail.com") 来构造一个字符串 "Zara@gmail.com"。unapply方法算是apply方法的反向操作：unapply接受一个对象，然后从对象中提取值，提取的值通常是用来构造该对象的值。实例中我们使用 Unapply 方法从对象中提取用户名和邮件地址的后缀。

### 提取器使用模式匹配 ###

在我们实例化一个类的时，可以带上0个或者多个的参数，编译器在实例化的时会调用 apply 方法。我们可以在类和对象中都定义 apply 方法。就像我们之前提到过的，unapply 用于提取我们指定查找的值，它与 apply 的操作相反。 当我们在提取器对象中使用 match 语句是，unapply 将自动执行。

## Scala 正则表达式 ##

通过 scala.util.matching 包种的 Regex 类来支持正则表达式。

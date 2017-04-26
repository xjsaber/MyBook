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
Scala 数据类型

## Scala 变量 ##

变量声明
一、变量： 在程序运行过程中其值可能发生改变的量叫做变量。如：时间，年龄。
二、常量 在程序运行过程中其值不会发生变化的量叫做常量。如：数值 3，字符'A'。
在 Scala 中，使用关键词 "var" 声明变量，使用关键词 "val" 声明常量。
var myVar : String = "Foo"
var myVar : String = "Too"
声明常量实例如下：
val myVal : String = "Foo"
变量类型声明

Scala 闭包
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


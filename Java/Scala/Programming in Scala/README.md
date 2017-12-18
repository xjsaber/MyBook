# Scala编程完整中文版 #

## 第1章 可伸展的语言 ##

### 1.1 与你一同成长的语言 ###

#### 增加新的类型 ####

#### 增加新的控制结构 ####

### 1.2 是什么让Scala具有可扩展性 ###

#### Scala是面向对象的 ####

### 1.3 为什么选择Scala？ ###

#### Scala是兼容的 ####

#### Scala是简洁的 ####

#### Scala是高级的 ####

#### Scala是静态类型的 ####

### 1.4 Scala的根源 ###

### 1.5 小结 ###

## 第2章 Scala入门初探 ##

### 2.1 第一步 学习使用Scala解释器 ###

* 自动产生的或用户定义的名称说明计算的值（res0，表示结果0）；
* 冒号（：）及表达式的类型（Int）；
* 等号（=）；
* 表达式经计算得到的结果（3）

### 2.2 第二步 变量定义 ###

val和var。val类似于Java里的final变量。一旦初始化了，val就不能再被赋值。相反，var如同Java里面的非final变量，可以在它的生命周期中被多次赋值。

### 2.3 第三步 函数定义 ###

	def max(x: Int, y: Int): Int = {
		if (x > y)x
		else y
	}

### 2.4 第四步 编写Scala脚本 ###

	Scala脚本的命令行参数保存在名为args的Scala数组中。

### 2.5 第五步 用while做循环：用if做判断 ###

	var i = 0
	while (i < args.length) {
		println(args(i))
		i += 1
	}

必须把while或if的布尔表达式放在括号里。

### 2.6 第六步 用foreach和for做枚举 ###

### 2.7 小结 ###

## 第3章 Scala入门再探 ##

### 3.1 第七步 使用类型参数化数组（Array） ###

Scala里使用new实例化对象（或者叫类实例）。

### 3.2 第8步 使用列表（List） ###

### 3.3 第九步 使用元组（Tuple） ###

#### 访问元祖的元素 ####

### 3.4 第十步 使用集（set） ###

## 第4章 类和对象 ##

### 4.1 类、字段和方法 ###

	class ChecksumAccmulator {
		// 此处为类定义
	}
创建ChecksumAccumulator对象：

	new CheckSumAccumulator

### 4.2 分号推断 ###

### 4.3 Singleton对象 ###

### 4.4 Scala程序 ###

### 4.5 Application特质 ###

scala.Application

### 4.6 小结 ###


## 第5章 基本类型和操作 ##

### 5.1 基本类型 ###

### 5.2 字面量 ###

### 5.3 操作符和方法 ###

Scala为基本类型提供了丰富的操作符集。

#### 整数字面量 ####

类型Int、Long、Short和Byte的整数字面量有三种

### 5.4 数字运算 ###

### 5.5 关系和逻辑操作 ###

大于（>）、小于（<）、大于等于（>=）和小于等于（<=），可以用来比较数类型并产生Boolean结果。还可以使用一元操作符！（unary_!方法）改变Boolean值。

逻辑方法、逻辑与（&&）和逻辑或（||）,以中缀方式调用Boolean操作数并产生Boolean结果。

### 5.6 位操作符 ###

* 1 & 2，对1（0001）和2（0010）的每一位进行运算，产生0（0000）
* 1 | 2 3（0011）

Scala整数类型提供了三个位移方法，分别是左移（<<），右移（>>）和无符号右移（>>>）。

### 5.7 对象相等性 ###

1 == 2

1 != 2

#### Scala的==与Java的有何差别 ####

Java里==既可以比较原始类型也可以比较引用类型。

### 5.8 操作符的优先级和关联性 ###

|操作符优先级|
|--|
|* / %|
|+ -|
|:|
|= !|
|<>|
|&|
|^|
||
|(所有字母)|
|(所有赋值操作符)|

### 5.9 富包装器 ###

implicit conversion

一些富操作

|代码|结果|
|--|--|
|0 max 5|5|
|0 min 5|0|
|-2.7 abs|2.7|
|-2.7 round|-3L|
|1.5 isInfinity|false|
|(1.0/0) isInfinity|true|
|4 to 6|5|
|"bob" capitalize|"Bob"|
|"robert" drop 2|"bert"|

富包装类

|基本类型|富包装|
|--|--|
|Byte|scala.runtime.RichByte|
|Short|scala.runtime.RichShort|
|Int|scala.runtime.RichInt|
|Long|scala.runtime.RichLong|
|Char|scala.runtime.RichChar|
|String|scala.runtime.RichString|
|Float|scala.runtime.RichFloat|
|Double|scala.runtime.RichDouble|
|Boolean|scala.runtime.RichBoolean|

### 5.10 小结 ###


## 第6章 函数式对象 ##

### 6.1 类Rational的规格说明书 ###

有理数（rational number）是一种可以表达为比率n/d的数字，这里的n和d是数字，其中d不能为零。n被称作分子（numberator），d被称作分母（denominator）。

### 6.2 创建Rational ###

	class Rational(n: Int, d: Int)

#### 不可变对象的权衡 ####

1. 不可变对象常常比可变对象更易理清头绪，因为它们没有会随着时间变化的复杂的状态空间。
2. 可以自由地传递不可变对象，但对于可变对象来说，传递给其他代码之前，需要先建造个以防万一的副本
3. 一旦不可变对象完成构造之后，就不会有线程因为并发访问而破坏对象内部状态，因为根本没有线程可以改变不可变对象的状态。
4. 不可变对象让哈希表键值更安全。

不可变对象唯一的缺点就是有时需要复制很大的对象表而可变对象的更新可以在原址发生。有些情况下这会变得难以快速完成而课呢功能产生性能瓶颈。

Java类具有可以带参数的构造器，而Scala类可以直接带参数。

### 6.3 重新实现toString方法 ###

	clas Rational(n: Int, d: Int) {
		ovverride def toString = n + "/" + d
	}

### 6.4 检查先决条件 ###

面向对象编程的优点之一就是它允许你把数据封装在对象之内以确保数据在整个生命周期中的有效性。必须确保对象创建时数据的有效性。

	class Rational(n: Int, d: Int) {
		require(d != 0)
		override def toString = n + "/" + d
	}

require 方法带一个布尔型参数。如果传入的值为真，require将正常返回。反之，require将抛出IlllegalArgumentException阻止对象被构造。

### 6.5 添加字段 ###

	class Rational(n: Int, d: Int) {
		require(d != 0)
		overide def toString = n + "/" + d
		def add(that: Rational): Rational = 
			new Rational(n * that.d + that.n * d, d * that.d)
	}

### 6.6 自指向 ###

关键字this指向当前执行方法被调用的对象实例，或者如果使用在构造器里的话，就是正被构建的对象实例。

	def max(that: Rational) = 
		if (this.lessThan(that)) that else this

第一个this可写可不写，写成(lessThan(that))也是一样；但第二个this标识当测试为假的时候的方法结果；如果你省略它，就什么都返回不了了。

### 6.7 辅助构造器 ###

Scala里主构造器之外的构造器被称为辅助构造器（auxiliary constructor）。

### 6.8 私有字段和方法 ###

	class Rational (n: Int, d: Int) {
		require(d != 0)
		private val g = gcd(n.abs, d.abs)
		...
	}
	private def gcd(a: Int, b: Int): Int = 
		if (b == 0) a else gcd(b, a % b)

### 6.9 定义操作符 ###

	x + y => x.add(y) => x add y

### 6.10 Scala的标识符 ###

Thread.\`yield`()

### 6.11 方法重载 ###

### 6.12 隐式转换 ###

	val r = new Rational(2, 3)
	r: Rational = 2/3

### 6.13 一番告诫 ###

### 6.14 小结 ###

## 第7章 内建控制结构 ##

### 7.1 If表达式 ###

	var filename = "default.txt"
	if (!args.isEmpty)
		filename = args(0)

### 7.2 while循环 ###

while，do-while

	def gcdLoop(x: Long, y: Long): Long = {
		var a = x
		var b = y
		while (a != 0) {
			val temp = a
			a = b % a
			b = temp
		}
	}

### 7.3 For表达式 ###

#### 枚举集合类 ####

	val filesHere = (new java.io.File(".")).listFiles
	for (file <- filesHere)
		println(file)

#### 过滤 ####

#### 嵌套枚举 ####


### 7.4 使用try表达式处理异常 ###

### 7.6 不再使用break和continu ###

	int i = 0; 
	boolean foundIt = false;
	while ( i < args.length) {
		if (args[i].startsWith("-")){
			i = i + 1;
			continue;
		}
		if (args[i].endsWith(".scala")){
			foundIt = true;
			break;
		}
		i = i + 1;
	}

----

	var i = 0
	var foundIt = false
	while (i < args.length && !foundIt) {
		if (!args(i).startsWith("-")){
			if (args(i).endsWith(".scala"))
				foundIt = true
		}
	}

----

	def searchFrom(i: Int): Int = 
		if (i >= args.length) -1
		else if (args(i).startsWith("-").searchFrom(i + 1)
		else if (args(i).endsWith(".scala")) i
		else searchFrom(i + 1)
	var i = searchFrom(0)

### 7.7 变量范围 ###

	val a = 1
	val a = 2 //编译不过

----

	val a = 1
	{
		val a = 2 // 编译通过
	}

### 7.8 重构指令式风格的代码 ###

	// 以序列形式返回一行乘法表
	def makeRowSeq(row: Int) = 
		for (col <- 1 to 10) yield {
			val prod = (row * col).toString
			val padding = " " * (4 - prod.length)
			padding + prod
		}
	// 以字符串形式返回一行乘法表
	def makeRow(row: Int) = makeRowSeq(row).mkString
	// 以字符串形式返回乘法表，每行记录占一行字符串
	def multiTable() = {
		val tableSeq = // 行记录字符串的序列
			for (row <- 1 to 10)
			yield makeRow(row)
		tableSeq.mkString("\n")
	}
### 7.9 小结 ###


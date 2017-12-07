# Go圣经 #

## 前言 ##

## 第1章 入门 ##

### 1.1. Hello，World ###

Go语言的代码通过包（package）组织，包类似于其他语言里的库（libraries）或者模块（modules）。一个包由位于单个目录下的一个或多个.go源代码文件组成，目录定义包的作用。

每个源文件都以一条`package`声明语句开始，这个例子里就是package main，表示该文件属于哪个包，紧跟着一系列导入（import）的包，折后是存储在这个文件里的程序语句。

main包定义了一个独立可执行的程序，而不是一个库。在`main`里`main`函数也很特殊，它是整个程序执行的入口。

`import`声明必须跟在文件的`package`声明之后。则是组成程序的函数、变量、常量、类型的声明语句（分别由关键字func，var，const，type定义）。

一个函数䣌声明由`func`关键字、函数名、参数列表、返回值列表以及包含在大括号里的函数体组成。

### 1.2. 命令行参数 ###

os包以跨平台的方式，提供了一些与操作系统交互的函数和变量。程序的命令行参数可从os包的Args变量获取；os包外部使用os.Args访问该变量。

## 第2章 程序结构 ##

### 2.1 命名 ###

Go语言中的函数名、变量名、常量名、类型名、语句标号和包名等所有的命名，都遵循一个简单的命名规则：一个名字必须以一个字母（Unicode字母）或下划线开头，后面可以跟任意数量的字母、数字或下划线。大写字母和小写字母是不同的：heapSort和Heapsort是两个不同的名字。

Go语言中类似if和switch的关键字有25个：关键字不能用于自定义名字，只能在特定语法结构中使用。

	s := "" //只能用在函数内部，而不能用于包变量
	var s string //依赖于字符串的默认初始化零值机制，被初始化为""
	var s = "" //用的极少，除非同时声明多个变量
	var s string = "" 显式地标明变量的类型，当变量类型与初值类型相同时，类型冗余，但如果两者类型不同，变量类型就必须了。

### 1.3 查找重复的行 ###



### 2.2 声明 ###

* var 变量
* const 常量
* type 类型
* func 函数

### 2.3 变量 ###

var声明语句可以创建一个特定类型的变量，然后给变量附加一个名字，并且设置变量的初始值。

	var 变量名字 类型 = 表达式

#### 2.3.1 简短变量声明 ####

#### 2.3.2 指针 ####

一个变量对应一个保存了变量对应类型值的内存空间。普通比那辆在声明语句创建时被绑定到一个变量名，比如叫x的变量。

	x := 1
	p := &x	// p, of type * int, points to x
	fmt.Println(*p) // "1"
	*p = 2	// equivalent to x =2
	fmt.Println(x) // "2"

### 2.4 赋值 ###

使用赋值语句可以更新一个变量的值，最简单的赋值语句是将要被赋值的变量放在=的左边，新值的表达式放在=的右边。

	x = 1 // 命名变量的赋值
	*p = true // 通过指针间接赋值
	person.name = "bob" // 结构体字段赋值
	count[x] = count[x] * scale // 数组、slice或map的元素赋值

----

	count[x] *= scale

#### 2.4.1 元祖赋值 ####

元祖赋值是另一种形式的赋值语句，它允许同时更新多个变量的值。

	x, y = y, x
	a[i], a[j] = a[j], a[i]

	f, err = os.Open("foo.txt") // function call return s two values

#### 2.4.2 可赋值性 ####

赋值语句是显式的赋值形式，但是程序中没有很多地方会发生隐式的赋值行为。

### 2.5 类型 ###

### 2.6 包和文件 ###

### 2.7 作用域 ###

一个声明语句将程序中的实体和一个名字关联。


## 第3章 基础数据类型 ##

Go语言数据类型分为四类：基础类型、符合类型、引用类型和接口类型。

### 3.1 整型 ###

Go语言的数值类型包括几种不同大小的整形数、浮点数和复数。

### 3.2 浮点数 ###

Go语言提供了两种精度的浮点数，float32和float64。

常量math.MaxFloat32标识float32能标识的最大数值，大约是3.4e38;对应的math.MaxFloat64常量大约是1.8e308。

### 3.3 复数 ###

complex64和complex128分别对应float32和float64

	var x complex128 = complex(1, 2) // 1+2i
	var y complex128 = complex(3, 4) // 1+2i
	fmt.Println(x*y) // "(-5+10i)"
	fmt.Println(real(x*y)) // "-5"
	fmt.Println(imag(x*y)) // "10"

### 3.4 布尔型 ###

### 3.5 字符型 ###

一个字符串是一个不可改变的字节序列。字符串可以包含任意的数据，包括byte值0。

	s := "hello, world"
	fmt.Println(len(s))
	fmt.Println(s[0], s[7])
	c := s[len(s)] // pansic: index out of range
	fmt.Println(s[0:5]) // "hello"
	fmt.Println(s[:5]) // "hello"
	fmt.Println(s[7:]) // "world"
	fmt.Println(s[:]) // "hello,world"
	fmt.Println("goodbye" + s[5:]) // "goodbye, world"

字符串的值是不可变的

	s[0] = 'L' // compile error: cannot assign to s[0]

#### 3.5.1 字符串面值 ####

	\a 响铃
	\b 退格
	\f 换页
	\n 换行
	\r 回车
	\t 制表符
	\v 垂直制表符
	\' 单引号
	\" 双引号
	\\ 反斜杠

#### 3.5.2. Unicode ####

#### 3.5.3. UTF-8 ####

![img/3_5.png](img/3_5.png)

字符串包含13个字节，以UTF8形式编码，但是只对应9个Unicode字符：

	import "unicode/utf8"
	s := "Hello, 世界"
	fmt.Println(len(s)) // "13"
	fmt.Println(utf8.RuneCountInString(s)) // "9"

----

	for i, r := range "Hello, 世界" {
		fmt.Printf("%d\t%q\t%d\n", i, r, r)
	}

	n := 0
	for _, _ = range s {
		n++
	}



### 3.6 常量 ###

常量表达式的值在编译期计算，而不是在运行期。每种常量的潜在类型都是基础类型：boolean、string或数字。

	const pi = 3.14159 //

和变量声明一样，可以批量的声明多个常量；

	const (
		e = 
		pi
	)

常量间的所有算术运算、逻辑运算和比较运算的结果也是常量，对常量的类型转换操作以下函数调用都是返回常量结果：len、cap、real、imag、complex和unsafe.Sizeof()

	const IPv4Len = 4
	
	// parseIPv4 parses an IPv4 address (d.d.d.d).
	func parseIPv4(s string) IP {
		var p [IPv4Len]byte
		// ...
	}

#### 3.6.1. iota常量生成器 ####

	type Weekday int
	
	const (
		Sunday Weekday = iota
		Monday 
		Tuesday
		Wednesday
		Thursday
		Friday
		Saturday
	)

## 第5章 函数 ##


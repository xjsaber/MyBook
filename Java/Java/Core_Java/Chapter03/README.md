# 第3章 Java的基本程序设计结构 #

## 3.1 一个简单的Java应用程序 ##
**Java对大小写敏感**
关键字public称为访问修饰符（access modifier），它用于控制程序的其他部分对这段代码的访问级别。

## 3.2 注释 ##

1. 


## 3.3 数据类型 ##
强类型语言

### 3.3.1 整型 ###
整型用于表示没有小数部分的数值，它允许是负数。Java提供了4种整型。
*int* *short* *long* *byte*

### 3.3.2 浮点类型 ###

### 3.3.3 char类型 ###
char类型用于表示单个字符。通常用来表示字符常量。

### 3.3.4 Unicode和chat类型 ###

### 3.3.5 boolean类型 ###


## 3.4 变量 ##
在Java中，每一个变量属于一种类型（type）。在声明变量时，变量所属的类型位于变量名之前。

### 3.4.1 变量初始化 ###

### 3.4.2常量 ###
利用关键字final指示常量。
关键字final表示这个变量只能被赋值一次。一旦被赋值之后，就不能够再更改了。习惯上，常量名使用全大写。

## 3.5 运算符 ##

### 3.5.1 自增运算符与自减运算符 ###

### 3.5.2 关系运算符与boolean运算符 ###

### 3.5.3 位运算符 ###
">>"和"<<"运算符将二进制位进行右移或左移操作。

### 3.5.4 数学函数与常量 ###

    Math.sqrt(x)
	Math.sin
	Math.cos
	Math.tan
	Math.atan
	Math.atan2
	Math.pow(x, a);

### 3.5.5 数值类型之间的转换 ###
数值类型之间的合法转换
![数值类型之间的合法转换](img/2016-12-03_12-05-55.jpg)

* 如果两个操作数中有一个是double类型，另一个操作数就回转换位double类型。
* 否则，如果其中一个操作数是float类型，另一个操作数就回转换为float类型。
* 否则，如果其中一个操作数是long类型，另一个操作数就回转换为long类型。
* 否则，两个啊从左数都将被转换位int类型。

### 3.5.6 强制类型转换 ###
强制类型转换（cast）

	double x = 9.997;
	int nx = (int) x;
### 3.5.7 括号与运算符级别 ###
&("and") |("or") ^("xor") ~("not")

### 3.5.9 枚举类型 ###
	
	enum Size {SMAILL, MEDIUM, LARGE, EXTRA_LARGE};

## 3.6 字符串 ##
Java字符串就是Unicode字符序列。

### 3.6.1 子串 ###
String类的substring方法可以从一个较大的字符串提取一个子串。

	String greeting = "Hello";
	String s = greeting.substring(0, 3); //"Hel"

### 3.6.2 拼接 ###

### 3.6.3 不可变字符串 ###
String类对象称为不可变字符串
### 3.6.4 检测字符串是否相等 ###

    s.equals(t)
千万不要使用==运算符测试字符串的相等性

### 3.6.5 空串与Null串 ###
空串""是长度为0的字符串。
	if(str.length() == 0)
	或
	if(str.equals(""))

### 3.6.6 代码点与代码单元 ###
Java字符串由char序列组成。

s.charAt(n)将返回位置n的代码单元
	String greeting = "Hello";
	char first = greeting.charAt(0); // first is 'H'
	char last = greeting.charAt(4); // last is 'o'

### 3.6.7 String API ###

* char charAt(int index)
* int codePointAt(int index)
* int offsetByCodePoints(int startIndex, int cpCount)
* int compareTo(String other)
* boolean endsWith(String suffix)
* boolean equals(Object other)
* boolean equalsIgnoreCase(String other) 忽略大小写
* int indexOf(String str)
* int indexOf(String str, int fromIndex)
* int indexOf(int cp)
* int indexOf(int cp, int fromIndex)
* int lastIndexOf(String str)
* int lastIndexOf(String str, int fromIndex)
* int lastIndexOf(int cp)
* int lastIndexOf(int cp, int fromIndex)
* int length()
* int codePointCount(int startIndex, int endIndex)
* String replace(CharSequence oldString, CharSequence newString)
* boolean startsWith(String prefix)
* String substring(int beginIndex)
* String substring(int beginIndex, int endIndex)
* String toLowerCase()
* String toUpperCase()
* String trim()

### 3.6.8 阅读联机API文档 ###

### 3.6.9 构建字符串 ###

	StringBuilder sb = new StringBuilder();
	//每次需要添加一部分内容时，就调用append方法
	sb.append(ch); //appends a single character
	sb.append(str); //appends a string
	//
	String completedString = builder.toString();

Java.langStringBuilder 5.0

* StringBuilder() 构造一个空的字符串构建器
* int length() 返回构建起或缓冲器中的代码单元数量。
* StringBuilder append(String str)
* StringBuilder append(char c)
* StringBuilder appendCodePoint(int cp)
* void setCharAt(int i, char c)
* StringBuilder insert(int offset, String str)
* StringBuilder insert(int offset, Char c)
* StringBuilder delete(int startIndex, int endIndex)
* String toString()

## 3.7 输入输出 ##

### 3.7.1 读取输入 ###

* 标准输出流 System.out.println
* 标准输入流 System.in 要想通过控制台进行输入，首先需要构造一个Scanner对象，并与“标准输入流”System.in关联。`Scanner in = new Scanner(System.in)`

Java.util.Scanner5.0

* Scanner(InputStream in)
* String nextLine()
* String next()
* itn nextInt()
* double nextDouble
* boolean hasNext()
* boolean hasNextInt()
* boolean hasNextDouble()

Java.lang.System1.0

* static Console console() 

Java.io.Console 6

* static char[] readPassword(String prompt, Object...args)
* static String readLine(String prompt, Object...args)

### 3.7.2 格式化输出 ###
double x = 10000.0 / 3.0
System.out.print(x);


| 标志        | 目的           | 举例  |
| ------------- |:-------------:| -----:|
| 0      | 数字前面补0 | 003333.33 |
| (      | 将负数括在括号内      | (3333.33) |
| . | 添加分组分隔符      |  3,333.33   |
| #(对于f格式) | 包含小数点      |  3,333.   |
| #(对于x或0格式) | 添加前缀0x或0 |  3,333   |
| $ | 给定被格式化的参数索引。例如.%1$d, %1$x将以十进制和十六进制格式化打印第1个参数      |  159 9F  |
| < | 格式化前面说明的数值。例如，%d%<x以十进制和十六进制打印同一个数值      |  159 9F  |

可以用静态的String.format方法创建一个格式化的字符串，而不打印输出:String message = String.format("Hello, %s.Next year，you'll be %d", name, age);

	String.format("Hello, %s. Next year, you'll be %d", name ,age);

### 3.7.3 文件输入与输出 ###

	Scanner in = new Scanner(Paths.get("myfile.txt"));
## 3.8 控制流程 ##

### 3.8.1 块作用域 ###
块（即复合语句）是指由一对大括号刮起来的若干条简单的Java语句。块确定了变量的作用域。
### 3.8.2 条件语句 ###

### 3.8.3 循环 ###

### 3.8.4 确定循环 ###

### 3.8.5 多重选择：switch语句 ###

### 3.8.6 中断控制流程语句 ###
break, continue

## 3.9 大数值 ##
BigInteger和BigDecimal

	BigInteger c = a.add(b); // c = a + b
	BigInteger d = c.multiply(b.add(BigInteger.valueOf(2))); // d = c * (b + 2)

## 3.10 数组 ##

	int[] a = new int[100];

### 3.10.1 for each 循环 ###
	
	for (int element : a)
		System.out.println(element);

	System.out.println(Arrays.toString(a));
	[2,3,5,7,11,13]
### 3.10.2 数组初始化以及匿名数组 ###

	int[] smallPrimes = { 2, 3, 5, 7, 11, 13};
	smallPrimes = new int[] {17, 19, 23, 29, 31, 37};
### 3.10.3 数组拷贝 ###

	int[] luckNumbers = smallPrimes;
	luckyNumbers[5] = 12; //now smallPrimes[5] is also 12

	int[] copiedLucksNumbers = Arrays.copeOf(luckyNumbers, luckyNumbers.length);

### 3.10.4 命令行参数 ###
### 3.10.5 数组排序 ###

### 3.10.6 多维数组 ###
	
	double[][] balances;

	for (int j = 0; j < balances[0].length; j++)
		balances[0][j] = 10000;

	for (double[] row : a)
		for (double value : row)
			do...

### 3.10.7 不规则数组 ###



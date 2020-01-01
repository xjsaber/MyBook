# Java核心技术 卷1 #

目录

# 第1章 Java程序设计概述 #

## 1.1 Java程序设计平台 ##

## 1.2 Java“白皮书”的关键术语 ##

1）简单性 
2）面向对象
3）网络技能（Network-Savvy）
4）健壮性
5）安全性
6）体系结构中立
7）可移植性
8）解释性
9）高性能
10）多线程
11）动态性

### 1.2.1 简单性 ###

### 1.2.2 面向对象 ###

### 1.2.3 网络技能 ###

### 1.2.4 健壮性 ###

### 1.2.5 安全性 ###

### 1.2.6 体系结构中立 ###

### 1.2.7 可移植性 ###

### 1.2.8 解释性 ###

### 1.2.9 高性能 ###

### 1.2.10 多线程 ###

### 1.2.11 动态性 ###

## 1.3 Java applet与Internet ##

## 1.4 Java发展简史 ##

## 1.5 关于Java的常见误解 ##

# 第2章 Java程序设计环境 #

## 2.1 安装Java开发工具箱 ##

### 2.1.1 下载JDK ###

### 2.1.2 设置JDK ###

### 2.1.3 安装库源文件和文档 ###

## 2.4 使用集成开发环境 ##

## 2.5 使用集成开发环境 ##

## 2.6 建立并运行applet ##

# 第3章 Java的基本程序设计结构 #

## 3.1 一个简单的Java应用程序 ##

**Java对大小写敏感**

关键字public称为访问修饰符（access modifier），它用于控制程序的其他部分对这段代码的访问级别。


## 3.2 注释 ##

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






# 第4章 对象与类 #

## 4.1 面向对象程序设计概念 ##

OOP

算法 + 数据结构 = 程序(Algorithms + Data Structures = Programs, Prentice Hall, 197)。在Wirth命名的书中，算法是第一位，数据结构是第二位的，明确地表述了程序员的工作作方式。首先要确定如何操作数据， 然后再决定如何组织数据，以便于数据操作。 而 OOP 却调换了这个次序，将数据放在第1位，然后再考虑操作数据的算法。

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

# 第5章 继承 #

## 5.1 类、超类和子类 ##
	
	class Manager extends Employee
	{

	}

### 5.1.1 集成层次 ###

### 5.1.2 多态 ###

## 第6章 接口与内部类 ##

## 第7章 异常、断言和日志 ##

# 第8章 泛型程序设计 #

泛型正是我们需要的程序设计手段。使用泛型机制编写的程序代码要比那些杂乱地使用Object 变量，然后再进行强制类型转换的代码具有更好的安全性和可读性。

## 8.1 为什么要使用泛型程序设计 ##

泛型程序设计（Generic programming) 意味着编写的代码可以被很多不同类型的对象所重用。

### 8.1.1 类型参数的好处 ###

编译器还知道 ArrayList<String> 中 add 方法有一个类型为 String 的参数。 这将比使用Object 类型的参数安全一些。现在， 编译器可以进行检査，避免插人错误类型的对象。

类型参数的魅力在于：使得程序具有更好的可读性和安全性

### 8.1.2 谁想成为泛型程序员 ###

## 8.2 定义简单的泛型类 ##



## 第8章 事件处理 ##

## 第9章 Swing用户界面组件 ##

## 第10章 部署应用程序和applet ##

## 第11章 异常、断言、日志和调试 ##

## 第12章 泛型程序设计 ##

## 第13章 集合 ##

## 第14章 多线程 ##
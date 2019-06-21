# 第2章 Ruby.new #
## 2.1 Ruby是一门面向对象语言 Ruby is an Object-Oriented Language ##

	song = Song.new("Ruby!!")
每个对象有一个唯一的对象标识符（object identtifier,缩写为object ID）其次，可以定义一些**实例变量**（instance variables）。
在这里点号（“.”）之前的东西被称为接收者（receiver），点号后面的名字是被调用的方法。
Ps：在Ruby里，确定绝对值的能力内建在数字中——处理细节在内部实现中。`number = number.abs`
## 2.2 Ruby的一些基本知识 Some Basic Ruby##
1. Ruby注释以一个#字符开始，在行尾结束。
2. 缩进编排以两个字符为宜。
3. 方法（method）用关键字def来定义，后面跟着方法名称和在括号中的方法参数，用关键字end结束这个程序体。
4. 不必声明变量，赋值给他时，他就存在了。

创建字符串对象有多种途径，最常见的是使用字符串字面量（literals），即一组单引号或多引号之间的字符序列。
两种形式，Ruby对字符串所做处理的多寡有所不同。Ruby对单引号串处理的很少，对双引号字符串有更多的处理（首先，它寻找以反斜线开始的序列，并用二进制替换它们。其中最常见的是\n，它会被回车换行符替换掉。当一个包含回车换行符的字符串输出时，\n会强制换行。）
## 2.3 数组和散列表 ##
数组(arrays)和散列表（hashes）是被索引的收集（indexed collections）。
使用数组字面量（array literal）——即方括号之间放一组元素——可以创建和初始化

## 2.4 控制结构 ##
如果if或while语句的程序体只是一个表达式，Ruby的语句修饰符（statement modifiers）是一种有用的快捷方式。

	if radiation > 3000
	  puts "Danger, Will Robinson"
	end
用语句修饰符重新编写了同样这个例子

	puts "Danger, While Robinason" if radiation > 3000
同样地，下面是while循环语句

	square = 2
	while square < 1000
	  square = square*square
	end
用语句修饰符，这个语句变得更简洁了。

	square = 2
	square = square * square while square < 1000

## 2.5 正则表达式 ##
字符类（character classes）如\s，它匹配空白字符（空格符、制表符、回车换行符等等）；\d匹配任何数字；还有\w，它匹配会出现在一个词内的任何字符。一个点（.）匹配几乎任意字符。

## 2.6 Block和迭代器 Blcks and Iterators ##
	def call_block
	  puts "Start of method"
	  yield
	  yield
	  puts "End of method"
	end

	call_block {puts "In the block"}

在Ruby库中大量使用了block来实现迭代器：迭代器是从某种收集（collection）如数组中连续返回元素的方法。

	animals = %w( ant bee cat dog elk) #创建一个数组
	animals.each(|animal| puts animal) #迭代它的内容
许多内建于C和Java等语言的循环结构在Ruby中只是方法调用，这些方法会零次或多次地调用相关联的block。
	
	['cat', 'dog', 'horse'].each{|name| print name, " "}
	times { print '*' }
	3.upto(6) {|i| print i }
	('a'..'e').each {|char| print char }
	result:cat dog horse *****3456abcde
上面的代码要求对象5五次调用block；然后要求对象3调用一个block，并传入一个连续的值，直到这个值到达6为止。然后对a到e的字符区间（range），使用each方法调用block。

## 2.7 读/写文件 ##
* puts输出它的参数，并在每个参数后面添加回车换行符。
* print输出它的参数，但没有添加回车换行符。
* printf在一个格式化字符串的控制下打印出它的参数
* gets


## 2.8 更高更远 Onward and Upward ##

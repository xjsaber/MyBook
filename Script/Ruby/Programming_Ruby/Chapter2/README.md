# 第2章 Ruby.new #
## 2.1 Ruby是一门面向对象语言 ##

## 2.2 Ruby的一些基本知识 ##

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

## 2.6 Block和迭代器 ##
	def call_block
	  puts "Start of method"
	  yield
	  yield
	  puts "End of method"
	end

	call_block {puts "In the block"}

## 2.7 读/写文件 ##


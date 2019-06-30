# 第6章 关于方法的更多细节  More about Methods #

## 6.1 定义一个方法 Deining a Method ##
使用关键字def来定义一个方法，方法名必须以一个小写字母开头，表示查询的方法名通常以?结尾，例如`instance_of?`“危险的”或者会修改接收者对象（receive）的方法，可以用!结尾。例如，String提供了chop和chop!方法。第一个方法返回一个修改后的字符串：而第二个则就地修改对象。可以被赋值的方法以一个等号（=）结尾。只有?、!和=这几个字符能够作为方法名的后缀。

已经为新方法指定了一个名字，可能还需要声明某些参数（parameter,形参）。它们就是括号中列出的局部变量。

### 6.1.1 可变长度的参数列表 Variable-Length Argument Lists ###
传入可变个数的参数（argument）、或者想用一个行参（parameter）接收多个参数，在“普通”的参数名前放置一个星号（*）即可。
	
	def varargs（arg1, *rest）
		"Got #{arg1} and #{rest.join(', ')}"
	end
	varargs("one")
	varargs("one", "two")
	varargs("one", "two", "three")	
	#第二个形参的前缀为星号，因此所有剩余的参数都被转入到一个新的Array中，然后赋值给第二个形参。
### 6.1.2 方法和Block Methods and Blocks ###
当调用一个方法时，可以用一个block与之相关联。通常，你可以使用yield
从内部方法调用block。

	def take_block(p1)
		if block_given?
			yield(p1)
		else
			p1
		end
	end
	take_block("no block");	-> "no block"
	take_block("no block"){|s|s.sub(/no /, '')} -> "block"
## 6.2 调用方法 Calling a Method ##
制定接收者、方法的名称、可选的参数及block，来调用一个方法。



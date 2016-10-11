# 深入理解JavaScript系列 #

[（1）：编写高质量JavaScript代码的基本要点](1.编写高质量JavaScript代码的基本要点)

##  1.编写高质量JavaScript代码的基本要点 ##
书写可维护的代码(Writing Maintainable Code )

最小全局变量(Minimizing Globals)

### switch模式(switch Pattern) ###
这个简单的例子中所遵循的风格约定如下：

* 每个case和switch对齐（花括号缩进规则除外）
* 每个case中代码缩进
* 每个case以break清除结束
* 避免贯穿（故意忽略break）。如果你非常确信贯穿是最好的方法，务必记录此情况，因为对于有些阅读人而言，它们可能看起来是错误的。
* 以default结束switch：确保总有健全的结果，即使无情况匹配。

### 避免隐式类型转换(Avoiding Implied Typecasting ) ###
为避免引起混乱的隐含类型转换，在你比较值和表达式类型的时候始终使用===和!==操作符。

### 避免(Avoiding) eval() ###


### parseInt()下的数值转换(Number Conversions with parseInt()) ###

### 编码规范(Coding Conventions) ###

### 缩进(Indentation) ###

### 花括号{}(Curly Braces) ###
最好总是使用花括号，即时只一行代码

### 左花括号的位置(Opening Brace Location) ###
开发人员对于左大括号的位置有着不同的偏好——在同一行（推荐）或是下一行。
	
	if (true) {
	   alert("It's TRUE!");
	}

	// 警告： 意外的返回值
	function func() {
	   return
	  // 下面代码不执行
	   {
	      name : "Batman"
	   }
	}
	// 警告： 意外的返回值
	function func() {
	   return undefined;
	  // 下面代码不执行
	   {
	      name : "Batman"
	   }
	}
### 空格 ###
* for循环分号分开后的的部分：如for (var i = 0; i < 10; i += 1) {...}
* for循环中初始化的多变量(i和max)：for (var i = 0, max = 10; i < max; i += 1) {...}
* 分隔数组项的逗号的后面：var a = [1, 2, 3];
* 对象属性逗号的后面以及分隔属性名和属性值的冒号的后面：var o = {a: 1, b: 2};
* 限定函数参数：myFunc(a, b, c)
* 函数声明的花括号的前面：function myFunc() {}
* 匿名函数表达式function的后面：var myFunc = function () {};
使用空格分开所有的操作符和操作对象是另一个不错的使用，这意味着在+, -, *, =, <, >, <=, >=, ===, !==, &&, ||, +=等前后都需要空格。

* 函数、if-else语句、循环、对象字面量的左花括号的前面({)
* else或while之间的右花括号(})
### 分隔单词(Separating Words) ###
对于构造函数，可以使用大驼峰式命名法(upper camel case)，如MyConstructor()。对于函数和方法名称，你可以使用小驼峰式命名法(lower camel case)，像是myFunction(), calculateArea()和getFirstName()。
### 其它命名形式(Other Naming Patterns) ###
* 全部单词大写的规范来命名这个程序生命周期中都不会改变的变量。
* 全局变量名字全部大写。

* 使用一个下划线前缀来表示一个私有属性或方法。
### 注释(Writing Comments) ###
阅读者需要的是（不要读太多的东西）仅注释和函数属性名来理解你的代码。
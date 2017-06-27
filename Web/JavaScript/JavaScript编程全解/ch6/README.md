# 第6章 函数与闭包 #

## 6.1 函数声明语句与匿名函数表达式 ##

## 6.2 函数调用的分类 ##

|名称|说明|
|--|--|
|方法调用|通过接收方对象函数进行调用（包括apply与call）|
|构造函数调用|通过new表达式对函数进行调用|
|函数调用|以上两种方式之外的函数调用|

#### 函数声明语句的后置 ####
	
	function doit() {
		fn();
		function fn() { print("called"); };
	}

## 6.3 参数与局部变量 ##

### 6.3.1 arguments对象 ###

使用arguments对象来访问呢实参。

	function fn() {
		print(arguments.length);
		print(arguments[0], arguments[1], arguments[2]);
	}

没有相对应的形参的实参也可以通过arguments访问。由于能够通过arguments.length获知实参的数量，因此可以写出所谓的可变长参数函数。

### 6.3.2 递归函数 ###

递归函数是一种在函数内对自身进行调用的函数。避免使用递归。

## 6.4 作用域 ##

作用域指的是名称（变量名与函数名）的有效范围。在JavaScript中有以下两种作用域。

	* 全局作用域
	* 函数作用域

	var x = 1; 
	function f() { 
		console.log('x =' + x); 
		var x = 2; 
		console.log('x =' + x) 
	};
	x = undefined;
	x = 2;
	局部变量x的作用域是整个函数f内部。

### 6.4.1 浏览器与作用域 ###

各个窗口（标签）、框架（包括iframe）都有其各自的全局作用域。在窗口之间是无法访问各自全局作用域中的名称的，但父辈与框架之间可以相互访问。

### 6.4.2 块级作用域 ###

在JavaScript（ECMAScript）中不存在块级作用域的概念。

	function f() {
		var i = 1;
		for (var i = 0; i < 10; i++) {
			//省略
		}
		此时变量i的值为10
	}

### 6.4.3 let与块级作用域 ###
	
通过let声明进行声明的变量具有块级作用域。除了作用域之外，它的其他方面与通过var进行声明的变量没有区别。

	// 名称的查找
	function f1() {
		let x= 1;
		{
			console.log(x); //输出1，将对代码块由内至外进行名称查找。
		}
	}

	let x = 1 ;
	{
		console.log(x);
	}

### 6.4.4 嵌套函数与作用域 ###
	
	function f1() {
		var x = 1; //函数f1的局部变量
		function f2() {
			var y = 2; //函数f2的局部变量
			console.log(x); //对函数f1的局部变量进行访问
			console.log(y); //对函数f2的局部变量进行访问
		}
		// 嵌套函数的声明
		function f3() {
			console.log(y); //如果不存在全局变量y，则会发生ReferenceError
		}
		// 嵌套函数的调用
		f2();
		f3();
	}

### 6.4.5 变量隐藏 ###

变量隐藏，通过作用域较小对策变量（或函数），来隐藏作用域较大的同名变量（或函数）。

	var n = 1; //全局变量
	function f() { //局部变量隐藏了n
		var n = 2;
		print(n);
	}

## 6.5 函数是一种对象 ##

函数也是一种对象。从内部结构来看，它继承于Function对象。可以像下面这样通过constructor属性验证。
	
	function f() {}
	f.constructor;

将匿名函数赋值给某个变量，与将Function对象的引用赋值给某个变量，在本质上是相同的，仅仅是表达方式的不同而已。

#### 函数名与调试的难易度 ####


## 6.6 Function类 ##

Function类是用于Function对象的类。

|函数或是构造函数|说明|
|--|--|
|Function(p0, p1, ..., body)|通过参数p0, p1, ... 与函数体body（字符串）来生成Function实例|
|new Function(p0, p1, ..., body)|通过参数p0, p1, ... 与函数体body（字符串）来生成Function实例|

|属性名|说明|
|--|--|
|prototype|用于原型链|
|length|值为1|

|属性名|说明|
|--|--|
|apply(thisArg, argArray)|将argArray的所有元素作为参数对函数调用。函数内的this引用的是thisArg对象|
|bind(thisArg[,arg0 , arg1, ...])||
|apply(thisArg, argArray)||
|apply(thisArg, argArray)||
|apply(thisArg, argArray)||
|apply(thisArg, argArray)||

|属性名|说明|
|---|----|
|apply(thisArg,argArray)|姜argArray的所有元素作为参数对函数调用。函数内的this引用的是thisArg对象|

#### Function类的继承 ####

JavaScript的函数是Function对象的对象实例，即JavaScript中的函数的原型对象是Function.prototype。

	function fn() {}
	fn.constructor === Function; //构造函数
	fn._proto_ === Function.prototype; //原型函数

	Function === Function.constructor;
	Function._proto_ == Function.prototype;

## 6.7 嵌套函数声明与闭包 ##
	
### 6.7.1 对闭包的初步认识 ###

	closure	
	function f() {
		var cnt = 0;
		return function() { return ++cnt;}
	}
	从表面上来看，闭包是一种具有状态的函数。或者也可以将闭包的特征理解为其相关的局部变量在函数调用结束之后将会继续存在。

### 6.7.2 闭包的原理 ###

**嵌套的函数声明**

	function f() {
		function g() {
			print('g is called');
		}
		g();
	}

	闭包的前提条件是需要在函数声明的内部声明另一个函数（即嵌套的函数声明）。

**嵌套函数与作用域**

	function f() {
		var n = 123;
		function g() {
			print('n is ' + n);
			print('g is called');
		}
		g();
	}

在内层进行声明的函数g可以访问外层的函数f的局部变量。函数内的变量名的查找是按照先Call对象的属性再全局对象的属性这样的顺序进行的。对于嵌套声明的函数，内部的函数将会首先查找被调用时所生成的Call对象的属性，之后再查找外部函数的Call对象的属性。这一机制被称为**作用域链**。

**嵌套函数的返回**

	function f() {
		var n = 123;
		function g() {
			print('n is ' + n);
			print('g is called');
		}
		return g; //再内部返回已被声明的函数（未对函数进行调用）
	}
由于return g语句，函数f将会返回一个Function对象（的引用）。调用函数f的结果是一个Function对象。这时，虽然会生成与函数f相对应的Call对象(Call-f对象)（并在离开函数f后被销毁），但由于不会调用g，所以此时还不会生成与之对应的Call对象。

**闭包**

将函数f的返回值赋值给另一个变量。

### 6.7.3 闭包中需要注意的地方 ###

函数g与函数gg保持了各自含有局部变量n的执行环境。由于声明函数g时的n值与声明函数gg时的n值是不同的，因此闭包g与闭包gg貌似将有表示各自不同的n值。但实际上两者将会表示相同的值。这是因为两者引用了同一个Call对象（Call-f对象）。

### 6.7.4 防范命名空间的污染 ###

在JavaScript中，在最外层的代码（函数之外）所写的名称（变量名与函数名）具有全局作用域，即所谓的全局变量与全局函数。

不应该只是一味地减少全局变量的使用，而应该形成一种尽可能避免使用较广的作用域的意识。

* 避免使用全局变量

* 通过闭包实现信息隐藏
JavaScript语言并没有提供可用于信息隐藏的语法功能，不过灵活运用闭包之后，就能使得名称无法从外部被访问。

## 6.8 回调函数设计模式 ##

### 6.8.1 回调函数与控制反转 ###

回调函数是程序设计的一种方法。在传递了可能会进行调用的函数或对象之后，在需要时再分别对进行调用。由于调用方与被调用方的依赖关系与通常相反，所以也称为控制反转（IoC，Inversion of Control）。

### 6.8.2 JavaScript与回调函数 ###

#### 回调函数 ####


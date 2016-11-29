# 第6章 函数与闭包 #

## 6.1 函数声明语句与匿名函数表达式 ##

## 6.2 函数调用的分类 ##

#### 函数声明语句的后置 ####
	
	function doit() {
		fn();
		function fn() { print("called"); };
	}

## 6.3 参数与局部变量 ##

### 6.3.1 arguments对象 ###
使用arguments对象来访问呢实参。
### 6.3.2 递归函数 ###
避免使用递归

## 6.4 作用域 ##

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


### 6.4.2 块级作用域 ###
在JavaScript中不存在块级作用域的概念。

### 6.4.3 let与块级作用域 ###

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

## 6.5 函数是一种对象 ##

#### 函数名与调试的难易度 ####


## 6.6 Function类 ##

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
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


### 3.5.1 布尔值 ###
布尔类（Boolean类）是一种布尔型的包装类型。其地位以及使用方法和String类以及Number类相同。

### 3.5.2 布尔类（Boolean类） ###

### 3.5.3 Boolean类的功能 ###



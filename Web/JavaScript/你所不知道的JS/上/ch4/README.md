# 第4章 提升 #

任何声明在某个作用域内的变量，都将附属于这个作用域。

## 4.1 先有鸡还是先有蛋 ##

	a = 2;
	var a;
	console.log(a);
输出结果是2

	console.log(a);
	var a = 2;
输出结果是undefined

## 4.2 编译器再度来袭 ##

当你看到 var a = 2; 时，可能会认为这是一个声明。但 JavaScript 实际上会将其看成两个声明： var a; 和 a = 2; 。第一个定义声明是在编译阶段进行的。第二个赋值声明会被留在原地等待执行阶段。

第一个代码片段：

	var a;
	a = 2;
	console.log(a);
其中第一部分是编译，而第二部分是执行。

第二个代码片段：
	
	var a;
	console.log(a);
	a = 2;

这个过程就好像变量和函数声明从它们在代码中出现的位置被“移动”
到了最上面。这个过程就叫作提升。

先有蛋（声明）后有鸡（赋值）。

只有声明本身会被提升，而赋值或其他运行逻辑会留在 原地 。如果提升改变了代码执行的顺序，会造成非常严重的破坏。

	foo();

	function foo() {
		console.log(a); //undefined
		var a = 2;
	}
foo函数的声明（这个例子还包括实际函数的隐含值）被提升了，因为第一行中的调用可以正常执行。

每个作用域都会进行提升操作。

	function foo() {
		var a;
		console.log( a ); // undefined
		a = 2;
	}
	foo();

可以看到，函数声明会被提升，但是函数表达式却不会被提升。

	foo(); // 不是 ReferenceError, 而是 TypeError!
	var foo = function bar() {
	// ...
	};

这段程序中的变量标识符 foo() 被提升并分配给所在作用域（在这里是全局作用域），因此foo() 不会导致 ReferenceError 。但是 foo 此时并没有赋值（如果它是一个函数声明而不是函数表达式，那么就会赋值）。 foo() 由于对 undefined 值进行函数调用而导致非法操作，因此抛出 TypeError 异常。

## 4.3 函数优先 ##

函数声明和变量声明都会被提升。但是一个值得注意的细节（这个细节可以出现在有多个“重复”声明的代码中）是函数会首先被提升，然后才是变量。

	foo(); // 1
	var foo;
	function foo() {
		console.log( 1 );
	}
	foo = function() {
		console.log( 2 );
	};

会输出 1 而不是 2 ！这个代码片段会被引擎理解为如下形式：

	function foo() {
		console.log( 1 );
	}
	foo(); // 1
	foo = function() {
		console.log( 2 );
	};

var foo 尽管出现在 function foo()... 的声明之前，但它是重复的声明（因此被忽略了），因为函数声明会被提升到普通变量之前。

重复的 var 声明会被忽略掉，但出现在后面的函数声明还是可以覆盖前面的。

	foo(); // 3
	function foo() {
		console.log( 1 );
	}
	var foo = function() {
		console.log( 2 );
	};
	function foo() {
		console.log( 3 );
	}

说明了在同一个作用域中进行重复定义是非常糟糕的，而且经常会导致各种奇怪的问题。

	foo(); // "b"
	var a = true;
	if (a) {
		function foo() { console.log("a"); }
	}
	else {
		function foo() { console.log("b"); }
	}

## 4.4 小结 ##

我们习惯将 var a = 2; 看作一个声明，而实际上 JavaScript 引擎并不这么认为。它将 var a 和 a = 2 当作两个单独的声明，第一个是编译阶段的任务，而第二个则是执行阶段的任务。

这意味着无论作用域中的声明出现在什么地方，都将在代码本身被执行前首先进行处理。可以将这个过程形象地想象成所有的声明（变量和函数）都会被“移动”到各自作用域的最顶端，这个过程被称为提升。

声明本身会被提升，而包括函数表达式的赋值在内的赋值操作并不会提升。
要注意避免重复声明，特别是当普通的 var 声明和函数声明混合在一起的时候，否则会引起很多危险的问题！
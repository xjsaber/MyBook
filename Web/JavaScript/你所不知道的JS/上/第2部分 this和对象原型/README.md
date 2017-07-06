你不知道的JavaScript

第二部分 this和对象原型

序

# 第1章 关于this #

this关键字是JavaScript中最复杂的机制之一。

## 1.1 为什么要用this ##

为什么要使用`this`

	function identify() {
		return this.name.toUppserCase();
	}
	function speak() {
		var greeting = "Hello, I'm " + identify.call(this);
		console.log(greeting);
	}
	var me = {
		name: "Kyle"
	};
	var you = {
		name: "Reader"
	};
	identify.call(me); //KYLE
	identify.call(you); //READER

	speak.call(me); //Hello,我是KYLE
	speak.call(you); //Hello,我是READER

## 1.2　误解 ##

### 1.2.1 指向自身 ###

既然函数看作一个对象（JavaScript 中的所有函数都是对象），那就可以在调用函数时存储状态（属性的值）。

### 1.2.2 它的作用域 ###

this 指向函数的作用域。

## 1.3 this到底是什么 ##

this 是在运行时进行绑定的，并不是在编写时绑定，它的上下文取决于函数调用时的各种条件。 this 的绑定和函数声明的位置没有任何关系，只取决于函数的调用方式。

## 1.4 小结 ##

this 实际上是在函数被调用时发生的绑定，它指向什么完全取决于函数在哪里被调用。

# 第2章 this全面解析 #

## 2.1 调用位置 ##

在理解 this 的绑定过程之前，首先要理解调用位置：调用位置就是函数在代码中被调用的位置（而不是声明的位置）。

	function baz() {
		// 当前调用栈是：baz
		// 因此，当前调用位置是全局作用域
		console.log( "baz" );
		bar(); // <-- bar 的调用位置
	}
	function bar() {
		// 当前调用栈是 baz -> bar
		// 因此，当前调用位置在 baz 中
		console.log( "bar" );
		foo(); // <-- foo 的调用位置
	}	

	function foo() {
		// 当前调用栈是 baz -> bar -> foo
		// 因此，当前调用位置在 bar 中
		console.log( "foo" );
	}
	baz(); // <-- baz 的调用位置

## 2.2 绑定规则 ##

查看真正的调用位置

1. 调用栈想象成一个函数调用链
2. 运行代码时，调试器会在那个位置暂停，同时会展示当前位置的函数
调用列表，这就是你的调用栈。因此，如果你想要分析 this 的绑定，使用开发者工具得到调用栈，然后找到栈中第二个元素，这就是真正的调用位置

### 2.2.1 默认绑定 ###

独立函数调用

	function foo() {
		console.log(this.a);
	}
	var a = 2;
	foo(); //2

1. 声明在全局作用域中的变量（比如 var a = 2 ）就是全局对象的一个同名属性。
2. 当调用 foo() 时， this.a 被解析成了全局变量 a 。为什么？因为在本例中，函数调用时应用了 this 的默认绑定，因此 this 指向全局对象。
3. 这里应用了默认绑定呢？可以通过分析调用位置来看看 foo() 是如何调用的。在代码中， foo() 是直接使用不带任何修饰的函数引用进行调用的，因此只能使用默认绑定，无法应用其他规则。

如果使用严格模式（strict mode），那么全局对象将无法使用默认绑定，因此 this 会绑定到 undefined：

	function foo() {
		"use strict";

		console.log(this.a);
	}
	var a = 2;
	foo; //TypeError: this is undefined
虽然 this 的绑定规则完全取决于调用位置，但是只有 foo() 运行在非 strict mode 下时，默认绑定才能绑定到全局对象；严格模式下与 foo()
的调用位置无关。

	function foo() {
		console.log(this.a);
	}
	var a = 2;
	(function() {
		"use strict";
		
		foo	();
	})();

### 2.2.2 隐式绑定 ###

需要考虑的规则是调用位置是否有上下文对象，或者说是否被某个对象拥有或者包含。

	function foo() {
		console.log( this.a );
	}
	var obj = {
		a: 2,
		foo: foo
	};
	obj.foo(); // 2
1. 需要注意的是 foo() 的声明方式，及其之后是如何被当作引用属性添加到 obj 中的。但是无论是直接在 obj 中定义还是先定义再添加为引用属性，这个函数严格来说都不属于obj 对象。
2. 调用位置会使用 obj 上下文来引用函数，因此你可以说函数被调用时 obj 对象“拥有”或者“包含”它。
3. 无论你如何称呼这个模式，当 foo() 被调用时，它的落脚点确实指向 obj 对象。当函数引用有上下文对象时，隐式绑定规则会把函数调用中的 this 绑定到这个上下文对象。因为调用 foo() 时 this 被绑定到 obj ，因此 this.a 和 obj.a 是一样的。
4. 对象属性引用链中只有最顶层或者说最后一层会影响调用位置。

	function foo() {
		console.log( this.a );
	}
	var obj2 = {
		a: 42,
		foo: foo
	};
	var obj1 = {
		a: 2,
		obj2: obj2
	}
	obj1.obj2.foo(); //42

**隐式丢失**

一个最常见的 this 绑定问题就是被隐式绑定的函数会丢失绑定对象，也就是说它会应用默认绑定，从而把 this 绑定到全局对象或者 undefined 上，取决于是否是严格模式。

	function foo() {
		console.log( this.a );
	}
	var obj = {
		a: 2,
		foo: foo
	};
	var bar = obj.foo; //
	var a = "oops, global";
	bar();
虽然 bar 是 obj.foo 的一个引用，但是实际上，它引用的是 foo 函数本身，因此此时的bar() 其实是一个不带任何修饰的函数调用，因此应用了默认绑定。

	function foo() {
		console.log( this.a );
	}
	function doFoo(fn) {
		//fn其实引用的是foo
		fn(); // <-- 调用位置！
	}
	var obj = {
		a: 2,
		foo: foo
	};
	var a = "oops, global';
	doFoo(obj.foo); 

回调函数丢失 this 绑定是非常常见的。

调用回调函数的函数可能会修改 this 。在一些流行的JavaScript 库中事件处理器常会把回调函数的 this 强制绑定到触发事件的 DOM 元素上。

### 2.2.3 显式绑定 ###

在分析隐式绑定时，我们必须在一个对象内部包含一个指向函数的属性，并通过这个属性间接引用函数，从而把 this 间接（隐式）绑定到这个对象上。

可以使用函数的 call(..) 和 apply(..) 方法。

它们的第一个参数是一个对象，它们会把这个对象绑定到this ，接着在调用函数时指定这个 this 。因为你可以直接指定 this 的绑定对象，因此我
们称之为显式绑定。

	function foo() {
		console.log(this.a);
	}
	var obj = {
		a:2
	};
	foo.call(obj); // 2
通过foo.call(..) ,我们可以在调用 foo 时强制把它的 this 绑定到 obj 上。

如果你传入了一个原始值（字符串类型、布尔类型或者数字类型）来当作 this 的绑定对象，这个原始值会被转换成它的对象形式（也就是 new String(..) 、 new Boolean(..) 或者 new Number(..) ）。这通常被称为“装箱”。

**1. 硬绑定**

	function foo() {
		console.log( this. a);
	}
	var obj = {
		a:2
	};
	var bar = function() {
		foo.call(obj);
	}
	bbar(); // 2
	setTimeout(bar, 100); //2

	// 硬绑定的 bar 不可能再修改它的 this
	bar.call(window); // 2

1.创建了函数bar()，并在它的内部手动调用了foo.call(obj)，因此强制把foo的this绑定到了obj。无论之后如何调用函数bar，它总会手动在obj上调用foo。（这种绑定是一种显式的强制绑定，因此我们称之为硬绑定）。 

### 2.2.4 new绑定 ###

在传统的面向类的语言中，“构造函数”是类中的一些特殊方法，使用 new 初始化类时会调用类中的构造函数。

	something = new MyClass(); 

JavaScript 也有一个 new 操作符，使用方法看起来也和那些面向类的语言一样，绝大多数开发者都认为 JavaScript 中 new 的机制也和那些语言一样。然而，JavaScript 中 new 的机制实际上和面向类的语言完全不同。

1. 重新定义一下 JavaScript 中的“构造函数”。在 JavaScript 中，构造函数只是一些使用 new 操作符时被调用的函数。它们并不会属于某个类，也不会实例化一个类。实际上，它们甚至都不能说是一种特殊的函数类型，它们只是被 new 操作符调用的普通函数而已。
2. 包括内置对象函数（比如 Number(..) ，详情请查看第 3 章）在内的所有函数都可以用 new 来调用，这种函数调用被称为构造函数调用。这里有一个重要但是非常细微的区别：实际上并不存在所谓的“构造函数”，只有对于函数的“构造调用”。

使用 new 来调用函数，或者说发生构造函数调用时，会自动执行下面的操作。

1. 创建（或者说构造）一个全新的对象。
2. 这个新对象会被执行 [[ 原型 ]] 连接。
3. 这个新对象会绑定到函数调用的 this 。
4. 如果函数没有返回其他对象，那么 new 表达式中的函数调用会自动返回这个新对象。

	function foo(a) {
		this.a = a;
	}
	var bar = new foo(2);
	console.log( bar.2 ); //2
使用new来调用foo(..)时，，构造一个新对象并把它绑定到foo（..）调用中的this上。new是最后一种可以影响函数调用时this绑定行为的方法，我们称之为new绑定。

## 2.3 优先级 ##

默认绑定的优先级是四条规则中最低的。

	function foo() {
		console.log( this.a );
	}
	var obj1 = {
		a: 2,
		foo: foo
	};
	var obj2 = {
		a: 3,
		foo: foo
	};
	obj1.foo(); //2
	obj2.foo(); //3
	obj1.foo.call(obj2); //3
	obj2.foo.call(obj1); //2
显式绑定优先级更高，也就是说在判断时应当先考虑是否可以应用显式绑定。

	function foo(something) {
		this.a = something;
	}
	var obj1 = {
		foo: foo
	};
	var obj2 = {};
	obj1.foo( 2 );
	console.log( obj1.a ); // 2
	obj1.foo.call( obj2, 3 );
	console.log( obj2.a ); // 3
	var bar = new obj1.foo( 4 );
	console.log( obj1.a ); // 2
	console.log( bar.a ); // 4
可以看到 new 绑定比隐式绑定优先级高。

new 和 call / apply 无法一起使用，因此无法通过 new foo.call(obj1) 来直接进行测试。但是我们可以使用硬绑定来测试它俩的优先级。

ES5 中内置的 Function.prototype.bind(..) 更加复杂。

	if (!Function.prototype.bind) {
		Function.prototype.bind = function(oThis) {
			if (typeof this !== "function") {
				// 与 ECMAScript 5 最接近的
				// 内部 IsCallable 函数
				throw new TypeError(
				"Function.prototype.bind - what is trying " +
				"to be bound is not callable"
				);
			}
			var aArgs = Array.prototype.slice.call(arguments, 1);
			fToBind = this,
			fNop = function(){},
			fBound = function() {
				return fToBind.apply(
				(this instanceof fNOP && oThis ? this : oThis),
				aArgs.concat(Array.prototype.slice.call(arguments))
				)
			}
			fNOP.prototype = this.prototype;
			fBound.prototype = new fNOP();

			return fBound;
		}
	}

# 第3章 对象 #



# 第4章 混合对象“类” #



# 第5章 原型 #




第6章 行为委托
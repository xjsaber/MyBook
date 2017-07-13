第1部分 类型和语法

# 第1章 类型 #

## 1.1 类型 ##

## 1.2 内置类型 ##

* 空置(null)
* 未定义(undefined)
* 布尔值(boolean)
* 数字(number)
* 字符串(string)
* 对象(object)
* 符号(symbol，ES6新增)

	typeof undefined === "undefined"; //true
	typeof true === "boolean"; //true
	typeof 42 === "number"; //true
	typeof "42" === "string"; //true
	typeof { life: 42} === "object"; //true
	// ES6中新加入的类型
	typeof Symbol() === "symbol"; //true

## 1.3 值和类型 ##

JavaScript 中的变量是没有类型的，只有值才有。变量可以随时持有任何类型的值。

语言引擎不要求变量总是持有与其初始值同类型的值。一个变量可以现在被赋值为字符串类型值，随后又被赋值为数字类型值。

### 1.3.1  undefined 和 undeclared ###

变量在未持有值的时候为 undefined 。

	var a;
	typeof a; // "undefined"
	var b = 42;
	var c;
	// later

	b = c;
	typeof b; // "undefined"
	typeof c; // "undefined"

### 1.3.2　 typeof Undeclared ###

## 1.4 小结 ##

JavaScript 有 七 种 内 置 类 型： null 、 undefined 、 boolean 、 number 、 string 、 object 和 symbol ，可以使用 typeof 运算符来查看。 

变量没有类型，但它们持有的值有类型。类型定义了值的行为特征。

通过 typeof 的安全防范机制（阻止报错）来检查 undeclared 变量，有时是个不错的办法。

# 第2章 值 #

数组（ array ）、字符串（ string ）和数字（ number ）

## 2.1 数组 ##

在 JavaScript 中，数组可以容纳任何类型的值，可以是字符串、
数字、对象（ object ），甚至是其他数组（多维数组就是通过这种方式来实现的）

	var a = [ 1, "2", [3] ];

	a.length; // 3
	a[0] === 1; // true
	a[2][0] === 3; // true

对数组声明后即可向其中加入值，不需要预先设定大小

	var a = [ ];
	a.length; // 0
	a[0] = 1;
	a[1] = "2";
	a[2] = [ 3 ];
	a.length; // 3

在创建“稀疏”数组（sparse array，即含有空白或空缺单元的数组）时要特别注意:

	var a = [ ];
	a[0] = 1;
	// 此处没有设置a[1]单元
	a[2] = [ 3 ];
	a[1]; // undefined
	a.length; // 3

其中的“空白单元”（empty slot）可能会导致出人意料的结
果。 a[1] 的值为 undefined ，但这与将其显式赋值为 undefined （ a[1] = undefined ）还是有所区别。

数组通过数字进行索引，但有趣的是它们也是对象，所以也可以包含字符串键值和属性。

	var a = [ ];
	a[0] = 1;
	a["foobar"] = 2;
	a.length; // 1
	a["foobar"]; // 2
	a.foobar; // 2

如果字符串键值能够被强制类型转换为十进制数字的话，它就会被当作数字索引来处理。

	var a = [ ];
	a["13"] = 42;
	a.length; // 14

在数组中加入字符串键值 / 属性并不是一个好主意。建议使用对象来存放键值 / 属性值，用数组来存放数字索引值。

#### 类数组 ####

工具函数 slice(..) 经常被用于这类转换：

	function foo() {
		var arr = Array.prototype.slice.call( arguments );
		arr.push( "bam" );
		console.log( arr );
	}
	foo( "bar", "baz" ); // ["bar","baz","bam"]

## 2.2 字符串 ##

字符串经常被当成字符数组。

	var a = "foo";
	var b = ["f","o","o"];

字符串和数组的确很相似，它们都是类数组，都有 length 属性以及 indexOf(..) （从 ES5 开始数组支持此方法）和 concat(..) 方法

	a.length; // 3
	b.length; // 3
	a.indexOf( "o" ); // 1
	b.indexOf( "o" ); // 1
	var c = a.concat( "bar" ); // "foobar"
	var d = b.concat( ["b","a","r"] ); // ["f","o","o","b","a","r"]
	a === c; // false
	b === d; // false
	a; // "foo"
	b; // ["f","o","o"]

这并不意味着它们都是“字符数组”

	a[1] = "O";
	b[1] = "O";

	a; // "foo"
	b; // ["f","O","o"]

JavaScript 中字符串是不可变的，而数组是可变的。并且 a[1] 在 JavaScript 中并非总是合法语法

字符串不可变是指字符串的成员函数不会改变其原始值，而是创建并返回一个新的字符串。而数组的成员函数都是在其原始值上进行操作。

	c = a.toUpperCase();
	a === c; // false
	a; // "foo"
	c; // "FOO"

	b.push( "!" );
	b; // ["f","O","o","!"]

## 2.3 数字 ##

JavaScript 只有一种数值类型： number （数字），包括“整数”和带小数的十进制数。

JavaScript 中的“整数”就是没有小数的十进制数。

JavaScript 中的数字类型是基于 IEEE 754 标准来实现的，该标准通常也被称为“浮点数”。JavaScript 使用的是“双精度”格式（即 64 位二进制）。

### 2.3.1　数字的语法 ###

JavaScript 中的数字常量一般用十进制表示。例如：
	
	var a = 42;
	var b = 42.3;

数字前面的 0 可以省略：
	
	var a = 0.42;
	var b = .42;

小数点后小数部分最后面的 0 也可以省略：

	var a = 42.0;
	var b = 42.;

默认情况下大部分数字都以十进制显示，小数部分最后面的 0 被省略，如：

	var a = 42.300;
	var b = 42.0;
	a; // 42.3
	b; // 42

特别大和特别小的数字默认用指数格式显示，与 toExponential() 函数的输出结果相同。

	var a = 5E10;
	a; // 50000000000
	a.toExponential(); // "5e+10"

	var b = a * a;
	b; // 2.5e+21

	var c = 1 / a;
	c; // 2e-11

由于数字值可以使用 Number 对象进行封装，因此数字值可以调用 Number.prototype 中的方法。

	var a = 42.59;

	a.toFixed( 0 ); // "43"
	a.toFixed( 1 ); // "42.6"
	a.toFixed( 2 ); // "42.59"
	a.toFixed( 3 ); // "42.590"
	a.toFixed( 4 ); // "42.5900"

toPrecision(..) 方法用来指定有效数位的显示位数:

	var a = 42.59;

	a.toPrecision( 1 ); // "4e+1"
	a.toPrecision( 2 ); // "43"
	a.toPrecision( 3 ); // "42.6"
	a.toPrecision( 4 ); // "42.59"
	a.toPrecision( 5 ); // "42.590"
	a.toPrecision( 6 ); // "42.5900"

上面的方法不仅适用于数字变量，也适用于数字常量。不过对于 . 运算符需要给予特别注意，因为它是一个有效的数字字符，会被优先识别为数字常量的一部分，然后才是对象属性访问运算符。

	// 无效语法：
	42.toFixed( 3 ); // SyntaxError

	// 下面的语法都有效：
	(42).toFixed( 3 ); // "42.000"
	0.42.toFixed( 3 ); // "0.420"
	42..toFixed( 3 ); // "42.000"

	
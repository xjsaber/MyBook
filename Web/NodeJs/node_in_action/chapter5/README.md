# chapter2 JavaScript概览 #

## JavaScript基础 ##

### 类型 ###

* 基本类型包括number、 boolean、 string、 null以及undefined。
* 复杂类型包括array、 function以及object。

### 类型的困惑 ###
	
	var a = 'woot';
	var b = new String('woot');
	a + b; // 'woot woot'
对这两个变量使用typeof和instanceof操作符
	
	typeof a; // 'string'
	typeof b; // 'object'
	a instanceof String; // false
	b instanceof String; // true
并且使用==操作符绑定时两者相等，而使用===操作符判定时并不相同:

	a == b; //true
	a === b; //false
	
typeof不会把null识别为类型为null

### THIS、FUNCTION#CALL以及FUNCTION#APPLY ###
	function a() {
		window == this;
	}
this的值是全局对象，使用.call和.apply方法改变this的值。

### 函数的参数数量 ###
参数数量，该属性指明函数声明时可接收的参数数量。

	var a = function (a, b, c);
	a.length == 3; //true

### 闭包 ###
在某个作用域钟定义的变量只能在该作用域或其内部作用域（该作用域钟定义的作用域）钟才能访问到：
	
	var a = 5;
	function woot() {
		a == 5; //true
		var a == 6;
		function test(){
			a == 6; //true
		}
		test();
	}
	woot();

JavaScript中没有class关键字。类只能通过函数来定义：
	
	function Animal() {}
要给所有Animal的实例定义函数，可以通过prototype属性来完成：

	Animal.prototype.eat = function (food) {
		//eat method
	}
在prototype的函数内部，this并非像普通函数那样指向global对象，而是指向通过该类创建的实例对象：
	
	function Animal (name) {
		this.name = name;
	}
	Animal.prototype.getName() {
		return this.name;
	};
	var animal = new Animal('duck');
	a.getNam() == 'duck'; //true

### 继承 ###
JavaScript有基于原型的继承的特点。通常，可以通过以下方式来模拟类继承。

定义一个要继承自Animal的构造器：

	function Ferret () {};
要定义继承链，首先创建一个Animal对象，然后将其赋值给Ferret.prototype。

	//实现继承
	Ferret.prototype = new Animal();
随后，可以为子类定义属性和方法：

	//为所有ferrets实例定义type属性
	Ferret.prototype.type = 'domestic';
还可以通过prototype来重写和调用父类函数：

	Ferret.prototype.eat = function(food) {
		Animal.prototype.eat.call(this. food);
		// ferret特有的逻辑写在这里
	}

### TRY {} CATCH {} ###
当函数抛出错误时，代码就停止执行了：

	function (){
		throw new Error('h1');
		console.log('h1'); //这里永远不会被执行到
	}
若使用try/catch则可以进行错误处理，并让代码继续执行下去
	
	function() {
		var a = 5;
		try {
			a();	
		} catch(e) {
			e instanceof Error; // true
		}

		console.log('you got here!');
	}

## V8中的JavaScript ##

### OBJECT#KEYS ###
要想获取下述对象的键（a和c）：
	
	var a = {a : 'b', c: 'd'};
通常会使用如下迭代的方式：
	
	for (var i in a) { }
通过对键进行迭代，可以将它们收集到一个数组中。不过，如果采用如下方式对Object.prototype进行过扩展:
	
	Object.prototype.c = 'd';
为了避免在迭代过程中把c也获取到，就需要使用hasOwnProperty来进行检查：

	for (var i in a) {
		if (a.hasOwnProperty(i)) {}
	}
在V8种，要获取对象上所有的自有键，还有更简单的方法：
	
	var a = { a: 'b', c: 'd'};
	Object.keys(a); // ['a', 'c']

### ARRAY#ISARRAY ###
对数组使用typeof操作符会返回object。
Array.isArray对数组返回true，对其他值则返回false：
	
	Array.isArray(new Array); //true
	Array.isArray([]); //true
	Array.isArray(null) //false
	Array.isArray(arguments) // false

### 数组方法 ###
要遍历数组，可以使用forEach(类似jQuery的$.each):
	
	//会打印出1,2,3
	[1, 2, 3].forEach(function (v)) {
		console.log(v);
	});
要过滤数组元素，可以使用filter(类似jQuery的$.grep):
	
	//会返回[1,2]
	[1, 2, 3].forEach(function (v)) {
		return v < 3;
	});
要改变数组中的每个元素的值，可以使用map(类似jQuery的$.map):
	
	//会返回[10,20,30]
	[5, 10, 15].forEach(function (v)) {
		return v * 3;
	});
V8还提供了一些不太常用的方法，如reduce、 reduceRight以及lastIndexOf。

### 字符串方法 ###
要移除字符串首末的空格，可以使用

	' hello '.trim(); // 'hello'
### JSON ###
V8提供了JSON.stringify和JSON.parse方法来对JSON数据进行解码和编码。

	var obj = JSON.parse('{"a": "b"}');
	obj.a == 'b'; //true

### FUNCTION#BIND ###
.bind(类似jQuery的$.proxy)允许改变对this的引用：

	function a (){
		this.hello == 'world'; //true
	}
	var b = a.bind({hello: 'world'});
	b();

### FUNCTION#NAME ###
V8还选择支持非标准的函数属性名

	var a = function woot() {};
	a.name == 'woot'; // true
当有错误抛出时，V8会显示一个堆栈追踪的信息，会告诉你时哪个函数调用导致了错误的发生：
	
	> var woot = function() {throw new Error();};
	> woot()
	Error
		at [object Context]:1:32
在上述例子中，V8无法为函数引用指派名字。然而，如果对函数进行命名，v8就能在显示堆栈信息时将名字显示出来：

为函数命名有助于调试，因此，推荐始终对函数进行命名。

### _PROTO_(继承) ###
_proto_使得定义继承链变得更加容易：

	function Animal() {}
	function Ferret() {}
	Ferret.prototype._proto_ = Animal.prototype;
可免去如下的工作：

* 像上一章节介绍的，借助中间构造器。
* 借助OOP的工具类库。无须再引入第三方模块来进行基于原型继承的声明。

### 存取器 ###
可以调用方法来定义属性，访问属性就使用_defineGetter_、设置属性就使用_defineSetter_。

## 小结 ##
V8威武
# 第9章 DOM #

## 9.1 DOM的定义 ##

DOM是一种API，其作用为在程序中使用HTML文档以及XML文档。在DOM中，HTML文档与XML文档会以树形对象集合的形式被使用。这一树形结构称为DOM树。

DOM树中的一个个对象被称为节点。节点之间形成了树形结构，树中的某个节点可能会另外一个节点。根据引用关系，分别有父节点、子节点、兄弟节点、祖先节点、子孙节点等类型。

DOM可以分为Level1~3这几层。

### 9.1.1 DOM Level 1 ###

DOM Level 1是由Core与HTML这两个模块组成的。

**DOM Level 1 的模块一览**

|模块|说明|
|--|--|
|Core|对包括HTML在内的基本DOM操作提供支持|
|HTML|对一些专用于HTML文档的方法提供支持|

**DOM Level 1 Core **

|方法名|说明|
|--|--|
|getElementsByTagName|根据指定的标签名来获取元素|
|createElement|创建新元素|
|appendChild|插入元素|

### 9.1.2 DOM Level 2 ###

DOM Level 2 所包含的模块一览
|模块|说明
|--|--|
|Core|Level 1 Core的扩展|
|HTML|Level 1 HTML的扩展|
|Views|对与文档显示状态相关的功能提供支持|
|Events|对捕获、冒泡、取消等事件系统提供支持|
|Styles|对于样式表相关的功能提供支持|
|Traversal and Range|对DOM树的遍历以及范围的指定提供支持|

### 9.1.3 DOM Level 3 ###

|模块|说明|
|--|--|
|Core|Level 2 Core的扩展|
|Load and Save|对文档内容的读取与写入提供支持|
|Validation|对文档内容合法性的验证提供支持|
|XPath|对XPath相关的功能提供支持|
|Events|Level 2 Events的扩展。对键盘事件提供了支持|

#### DOM Level 0 ####



### 9.1.4 DOM的表达方式 ###

	接口名.方法名()
	接口明.属性名

## 9.2 DOM的基础 ##

### 9.2.1 标签、元素、节点 ###

#### 标签 ####

标签是一种用于标记的字符串，其作用为对文档的结构进行指定。

#### 元素、节点 ####

元素和节点之间略有一些继承关系，其中节点是父类概念。节点具有nodeType这一属性，如果其值为ELEMENT_NODE(1)，该节点则是一个元素。

### 9.2.2 DOM操作 ###

JavaScript的作用是使网页能够执行某些功能。为了实现这些功能，必须对DOM进行操作。通过选择某个DOM元素并改写其属性，或创建一个新的DOM元素，就能够给予用户视觉反馈，以实现交互功能。选择、创建、更改与删除。

### 9.2.3 Document对象 ###

Document对象是DOM树结构中的根节点。

## 9.3 节点的选择 ##

### 9.3.1 通过ID检索 ###

Document.getElementById()方法是一种最为常见的手段。

	var element = document.getElementById('foo');

ID在DOM树中必须是唯一的。在DOM中并没有对存在多个相同的ID的情况做出规定。不过，大部分的浏览器都采用了返回第一个找到的元素的方式。

	<div id="foo">first</div>
	<div id="foo">second</div>
	<script>
		var element = document.getElementById('foo');
		alert (element.innerHTML); // => 大部分浏览器都会返回first。不过这并不是一种绝对标准
	</script>
### 9.3.2 通过标签名检索 ###

通过下面这样的方式，使用Element.getElementsByTagName()方法来取得具有该标签名的所有节点。标签名还可以使用'*'作为通配符。可以通过'*'来获取所有元素。

	var spanElements = document.getElementsByTagName('span');	// 仅获取span元素
	var spanElements = document.getElementsByTagName('*');	// 获取所有的元素
Document.getElementById()是只存在于Document对象中的方法，而Element.getElementsByTagName()则是同时存在于Document对象与Element对象这两者中的方法。在执行某个Element对象的getElementsByTagName()方法时，该Element对象的子孙节点中具有指定标签名的元素也将被获取。

#### Live对象的特征 ####

getElementsByTagName()所能取得的对象是一个NodeList对象，而不是单纯Node对象的数组。而NodeList对象的一大特征就是它是一个Live对象。

### 9.3.3 通过名称检索 ###

通过HTMLDocument.getElementsByName()方法，可以将name属性的值作为限定条件来获取属性。

不过因为只能在form标签或input标签等标签使用name属性，所以与getElementById()相比，它的使用频率较低。

### 9.3.4 通过类名检索 ###

通过使用HTMLElement.getElementsByClassName()方法，就可以获取指定类名的元素。

其中的类名可以指定多个值。如果想要指定多个类名，则需要使用空白符作为分隔字符串。也就是类似于“classA classB”的形式。这时，会取得classA与classB这两个类名所制定的元素。

### 9.3.5 父节点、子节点、兄弟节点 ###

**一些用于引用相关结点的属性**

|属性名|能够获取的节点|
|--|--|
|parentNode|父节点|
|childNodes|子节点列表|
|firstChild|第一个子节点|
|lastChild|最后一个子节点|
|nextSibling|下一个兄弟节点|
|previousSibling|上一个兄弟节点|

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


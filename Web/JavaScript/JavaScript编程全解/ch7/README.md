# 第7章 数据处理 #

## 7.1 数组 ##

数组是一种有序元素的集合。在JavaScript中，数组的长度是可变的。

数组只不过是继承了JavaScript的对象的一些性质。

### 7.1.1 JavaScript的数组 ###

### 7.1.2 数组元素的访问 ###

### 7.1.3 数组的长度 ###

### 7.1.4 数组元素的枚举 ###

** 数组长度的上限 **

在ECMAScript中，JavaScript的数组长度的上限是2的32次方。

ECMAScript第5版有多个这种类型的内部循环方法。最具代表性的forEach方法，参数e：元素值，i下标值，a数组对象

	arr.forEach(function(e, i, a) {
		print(i, e);
	})

### 7.1.5 数组元素的枚举 ###

由于任意内容都可以被指定为数组的元素，因此数组本身自然也可以成为另一数组的元素。

### 7.1.6 数组是一种对象 ###

数组是一种对象。从内部来看，它是Array对象（Array类）的对象实例。因此，可以通过new表达式来调用Array的构造函数来生成数组。

### 7.1.7 Array类 ###

Array类的函数是一种作为数组对象使用的类。

Array类的函数以及构造函数调用

|函数或是构造函数|说明|
|Array([item0, item1,...])|将参数作为元素以生成数组实例|
|new Array([item0, item1,...])|将参数作为元素以生成数组实例|
|Array(len)|将参数作为元素以生成数组实例|
|new Array(len)|将参数作为元素以生成数组实例|

Array类的属性

|属性名|说明|
|--|--|
|prototype|用于原型链|
|length|值为1|
|isArray|如果参数arg是一个数组实例则为真|

Array.prototype对象的属性

|属性名|说明|
|--|--|
|constructor|对Array类对象的一个引用|
|concat|对Array类对象的一个引用|
|every|对Array类对象的一个引用|
|filter|对Array类对象的一个引用|
|forEach|对Array类对象的一个引用|
|indexOf|对Array类对象的一个引用|
|join|对Array类对象的一个引用|
|lastIndexOf|对Array类对象的一个引用|
|map|对Array类对象的一个引用|
|pop|对Array类对象的一个引用|
|push|对Array类对象的一个引用|
|reduce|对Array类对象的一个引用|
|reduceRight|对Array类对象的一个引用|
|reverse|对Array类对象的一个引用|
|reverse()|对Array类对象的一个引用|
|shift|对Array类对象的一个引用|
|slice|对Array类对象的一个引用|
|some|对Array类对象的一个引用|
|sort|对Array类对象的一个引用|
|slice|对Array类对象的一个引用|
|some|对Array类对象的一个引用|
|sort|对Array类对象的一个引用|
|splice|对Array类对象的一个引用|
|toLocaleString|对Array类对象的一个引用|
|toSource()|对Array类对象的一个引用|
|toString()|对Array类对象的一个引用|
|unshift|对Array类对象的一个引用|

7.4 Array类的实例属性

|属性名|说明|
|--|--|
|0以上的整数值|数组元素|
|length|数组的长度|

### 7.1.8 数组对象的意义 ###

### 7.1.9 数组的习惯用法 ###

### 7.1.10 数组的内部实现 ###

### 7.1.11 数组风格的对象 ###

从使用的角度来看，属性名是数值的对象与数组差别很小。

* 对象具有length属性
* 对象具有数值属性

### 7.1.12 迭代器 ###

在JavaScript中Iterator类这样的一个自定义增强的功能。可以通过构造函数调用或Iterator函数的调用来生成一个对象实例。

	var arr = ['zero', 'one', 'two']; 
	var it = new Iterator(arr, true); // it = Iterator(arr, ture);

在迭代器对象中含有一个next方法。next方法能够从（对象）元素的集合中返回下一个所需的元素。
	
	it.next();
	"0"

对于已经存在的对象或数组来说，使用Iterator其实并没有太大的作用。什但在使用自定义迭代器时，可以发挥更大的作用。


### 7.1.13 生成器 ###

生成器（Generator）也是JavaScript自定义的增强功能，其作用是帮助执行循环处理。生成器与通常的函数的不同之处在于是否在内存进行了yield调用。一个函数如果在内部进行了yield调用，它就是一个隐式的生成器。此外需要注意的是，在JavaScript中，yield是一个保留字。

	function factorial_printer(max) {
		var cur = 1;
		for (var n = 1; n <= max; n++) {
			cur *= n;
			print('cur = ' + cur);
		}
	}

调用函数factorial_generator之后将会返回一个对象。这个对象称为迭代生成器。调用迭代生成器的next方法的话，就可以执行1次生成器中的循环。

可以将生成器直观地理解为一种由于yield而处于停止状态的函数。可以在其外部通过next方法使循环过程继续进行。

### 7.1.14 数组的内包 ###

数组的内包是一种在通过生成器生成数组时的功能。

## 7.2 JSON ##

JSON是JavaScript Object Notation的缩写，是一种基于JavaScript的字面量表达方式的数据格式类型。

JSON能够通过4种基本数据类型以及2种结构化数据类型来表示。

4种基本数据类型是指字符串值型、数值型、布尔型以及null型。结构化数据类型是指对象与数组这两种。

表7.5 JSON的标准

|数据类型|书写示例|注意点|
|--|--|--|
|字符串值|"foobar"|不能使用单引号。字符串的默认编码为UTF-8|
|数值|123.4|只支持10进制书写方式|
|布尔值|true或是false||
|null值|null||
|对象|{"x": 1, "v": "foo"}|属性名只能使用字符串的方式表示而不能使用{x: 1}这样的字面量形式|
|数组|{1, 2, "foo"}|数组中的元素可以被指定为任意类型的值|

### 7.2.1 JSON字符串 ###

** JSON字符串的分析 **

在原生JSON出现之前，可以通过eval函数的方式将JSON字符串转换为JavaScript对象。传递给eval函数的字符串将被看作是JavaScript代码并被执行（以进行求值）。

### 7.2.2 JSON对象 ###

JSON对象是一种用于原生JSON分析的对象，无法对其进行构造函数调用。

7.6 JSON对象的属性

|属性名|说明|
|--|--|
|parse(text[, reviver])|对参数text这一JSON字符串进行分析之后返回一个JavaScript对象。reviver将会对每个属性调用回调函数，并将返回值赋为属性值|
|stringify(value[, replacer[, space]])|将参数value转换为JSON字符串。replacer将会对每个属性调用回调函数，并将返回值赋为属性值。space则是输出时的一个缩进字符串|

## 7.3 日期处理 ##

Date类是一种用于日期处理的类。

	var dt = new Date(); //如果使用不含参数的构造函数调用，则会生成一个包含了当前时刻的Date实例
	print(dt);

GMT标准的1970年1月1日0时0分。

7.7 日期数据的表示形式

|名称|主要用途|
|--|--|
|epoch值|保存与数据库中的值。各种转换的根底。用于计算经过的时间|
|Date类|JavaScript代码的内部形式。用于月份的处理、星期的处理，或星期几的判断|
|字符串|用于向用户显示日期（包括农历等），或作为用户输入值的形式，以及网络传输时的形式|
|年月日等数值|用于向用户显示日期，或作为用户输入值的形式|

epoch值是一种纯数值。

Date类的作用就是将这些日期处理中的复杂情况隐藏起来，


## 7.4 正则表达式 ##

### 7.4.1 正则表达式的定义 ###

regular expression

### 7.4.2 块级作用域 ###

* 模式
* 输入字符串
* 匹配

查找规则被称为模式（pattern）。用于查找模式的对象字符串被称为输入字符串。在输入字符串中寻找与模式相一致的字符串的过程称为匹配（match）。

### 7.4.3 正则表达式的语法 ###
	
** 模式字符串的组成元素 **

* "book"
* "books"
* "buy a book"
* "notebook"

7.12 JavaScript正则表达式的元字符

|元字符|说明|
|--|--|
|.|任意1个字符|
|\s|空白字符|
|\S|非空白字符|
|\w|可以构成单词的字符|
|\W|不能构成单词的字符|
|\d|数字|
|\D|非数字|
|\b|单词的边界|
|\B|不是单词的边界|
|^|行首|
|$|行末|
|X?|字符X重复出现0次或1次|
|X??|字符X重复出现0次或1次（非贪心法）|
|X*|字符X重复出现0次或更多次|
|X*?|字符X重复出现0次或更多次（非贪心法）|
|X+|字符X重复出现1次或更多次|
|X+?|字符X重复出现1次或更多次（非贪心法）|
|$|行末|
|$|行末|
|$|行末|
|$|行末|

如果不希望让元字符存在的字符被解释为元字符，则需要通过反斜杠字符对其进行转义。例如，如果不希望让模式对点字符（.）进行匹配，则需要对其写为\.的形式。

如果在模式中要匹配反斜杠字符，则需要将其写成\\

### 7.4.4 嵌套函数与作用域 ###
	
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


# 第10章 事件 #

## 10.1 事件驱动程序设计 ##

DOM Level 2中定义了标准的事件模型。

## 10.2 事件处理程序/事件侦听器的设定 ##

* 指定为HTML元素的属性（事件处理程序）
* 指定为DOM元素的属性（事件处理程序）
* 通过EventTarget.addEventListener()进行值定（事件侦听器）

### 10.2.1 指定为HTML元素的属性 ###

|事件处理程序名|触发的时机|
|onclick|鼠标点击操作|
|ondbclick|鼠标双击操作|
|onmousedown|按下了鼠标按键|
|onmouseup|放开了鼠标按键|
|onclick|鼠标点击操作|
|onclick|鼠标点击操作|
|onclick|鼠标点击操作|
|onclick|鼠标点击操作|
|onfocus|input元素获得了焦点|
|onselect|文本被选取|
|onsubmit|按下了表单的提交按钮|
|onrest|按下了表单的重置按钮|
|onload|载入完成|
|onunload|文档的载入被撤销（例如页面跳转等情况时）|
|onabort|图像的读取被中断|
|onerror|图像读取过程中发生错误|
|onresize|窗口尺寸发生改变|

如果事件处理车改内需返回了一个false值，则会取消该事件的默认行为。

### 10.2.2 指定为DOM元素的属性 ###

事件处理车改内需可以被指定为节点的属性。

	btn.onclick = sayFoo(); //这种方式指定的是函数执行后的返回值，是错误的
	btn.onclick = "sayFoo()"; //以字符串的形式指定该函数也是无效的
	btn.onclick = sayFoo(); //将函数指定为了事件处理程序，而能够正常运行。

与通过HTML标签标签的属性设定时不同，这里必须全部必须使用小写字母书写。

而在设定为了属性之后，HTML标签属性的内容将会被覆写。因此，如果希望通过JavaScript代码在HTML标签属性所指定的内容之后再追加新的处理操作，仅采用这种指定DOM元素的方法是很难实现的。

### 10.2.3 通过EventTarget.addEventListener()进行指定 ###

	var btn = document.getElementById('foo');
	btn.addEventListener('click', function (e) {

	})

#### 注册事件侦听器 ####

### 10.2.4 事件处理程序/事件侦听器内的this引用 ###



## 10.3 事件的触发 ##

## 10.4 事件的传播 ##

在浏览器中显示HTML文档时，HTML的元素将会嵌套显示。

	<html>
		<body>
			<div id="foo">
				<button id="bar">sample</button>
			</div>
		</body>
	</html>
事件的处理将会分别经过捕获阶段、目标阶段、事件冒泡阶段

### 10.4.1 捕获阶段 ###

在捕获阶段中，事件将会从Window对象开始向下遍历DOM树来传播。如果注册了事件侦听器，则会在捕获阶段执行相应的处理。

### 10.4.2 目标阶段 ###

在这一阶段中，被事件目标注册的事件侦听器将会被执行。如果一个事件处理程序被指定为了HTML的标签属性，或被指定为了对象的属性，则会在这一阶段中被执行。

### 10.4.3 事件冒泡阶段 ###

在这一阶段中，被事件目标注册的事件侦听器将会被执行。如果一个事件处理程序被指定为了HTML的标签属性，或被指定为了对象的属性，则会在这一阶段中被执行。

### 10.4.4 取消 ###
	
#### 取消传播 ####

通过在事件侦听器内执行Event.stopPropagation()方法就能取消传播。不过stopPropagation()方法只能取消执行在之后的侦听器目标中注册的事件侦听器，而在当前的侦听器目标中设定的其他事件侦听器仍然会被执行。

#### 取消默认事件 ####

event.preventDefault()方法来取消浏览器中默认是实现的处理操作。

stopPropagation()方法与preventDefault()方法不仅能够被用于事件冒泡阶段，在其他阶段中也能够使用这些方法。

## 10.5 事件所具有的元素 ##

#### Event接口的属性一览 ####
|属性|说明|
|--|--|
|type|事件属性的名称。|
|target|事件属性的名称。|
|currentTarget|事件属性的名称。|
|eventPhase|事件属性的名称。|
|bubbles|事件属性的名称。|
|cancelable|事件属性的名称。|

#### Event接口的方法一览 ####
|方法|说明|
|--|--|
|stopPropagation()|用于中止事件传播的方法|
|preventDefault()|用于中止默认行为的方法|
|stoplmmediatePropagation()|用于中止其他事件侦听器的方法。在DOM Level3中被引入|

#### Event接口的方法一览 ####

## 10.6 标准事件 ##

### 10.6.1 DOM Level 2 中所定义的事件 ###

* HTMLEvent
* MouseEvent
* UIEvent
* MutationEvent

“冒泡”表示事件传播过程中是否会在DOM树种经过冒泡阶段，而“默认”则表示是否含有可以通过preventDefault()方法取消的默认行为。


**HTMLEvent一览**

|事件类型|冒泡|默认|触发的时机|
|--|--|--|--|
|load|X|X|文档载入完成后|
|unload|X|X|文档的载入被撤销（例如页面跳转等情况时）|
|abort|冒泡|默认|触发的时机|
|error|冒泡|默认|触发的时机|
|select|冒泡|默认|触发的时机|
|change|冒泡|默认|触发的时机|
|submit|冒泡|默认|触发的时机|
|reset|冒泡|默认|触发的时机|
|focus|冒泡|默认|触发的时机|
|blur|冒泡|默认|触发的时机|
|resize|冒泡|默认|触发的时机|
|scroll|冒泡|默认|触发的时机|

**MutationEvent一览**

|事件类型|冒泡|默认|触发的时机|
|--|--|--|--|
|DOMSubtreeModified|O|X|文档内容发生了更改|
|DOMNodeInserted|O|X|添加了子节点（在添加后被触发）|
|DOMNodeRemoved|O|X|删除了子节点（在删除前被触发）|
|DOMNodeRemoveFromDocument|X|X|向文档中添加了节点（在添加后被触发）|
|DOMNodeRemoveFromDocument|X|X|从文档中删除了节点（在删除前被触发）|
|DOMNodeAttrModified|O|X|节点中有属性发生了更改|
|DOMCharacterDataModified|O|X|节点中的文字数据发生了更改|

### 10.6.2 DOM Level 3 中所定义的事件 ###

* UIEvent
* KeyboardEvent
* FocusEvent
* CompositionEvent
* MouseEvent
* MutationEvent(不推荐使用)
* WheelEvent
* MutationNameEvent(不推荐使用)
* TextEvent

## 10.7 自定义事件 ##

除了标准中所定义的事件，还能够定义并触发自定义的事件。

createEvent()方法来创建一个事件对象，并通过目标节点的dispatch()方法来分发这一事件对象。可以通过createEvent()方法来创建一个事件对象，并通过目标节点的dispatch()方法来分发这一事件对象。

	var event = document.createEvent('Events');
	event.initEvent('myevent', true, true);
	var target = document.getElementById('foo');
	target.addEventListener('myevent', function() {
		alert('My event is fired.');
	}, false);
	target.dispatchEvent(event);

initEvent()方法的第1个参数用于指定事件类型。
在使用dispatch()方法时需要注意的是，该方法是一个同步执行的方法。它并不会以队列的形式依次逐一执行，而会立即通过相应的事件侦听器开始执行。在执行完之后，dispatchEvent()方法将会返回相应的事件侦听器的返回值。

通过setTimeout()方法，能够实现dispatch()的异步执行。

	window.setTimeout(function () {
		target.dispatchEvent(event);
	}, 10);

dispatchEvent()是同步执行的，也可以通过显式地调用事件侦听器来实现相同的功能。通过事件触发的形式可以更容易地添加新的处理操作。相比使用具有了回调函数的自定义函数，通过在DOM标准中定义的addEventListener()方法来添加新的回调函数的方式通用性更强，而且还能够与其他模块共同使用。


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


# 第1章 富有表现力的JavaScript #

## 1.1 JavaScript的灵活性 ##

## 1.2 弱类型语言 ##

## 1.3 函数式一等对象 ##

code1:

	(function(){
	
	})();

	(function(foo, bar){
		alert(foo * bar);
	})(10, 2);

	var baz;

	(function() {
		var foo = 10;
		var bar = 2;
		baz = function(){
			return foo * bar;
		};
	})();
	baz();
	变量foo和bar定义在匿名函数中，因为函数baz定义在这个闭包中，所以它能访问这两个变量，即使是在该闭包执行结束后。

## 1.4 对象的易变性 ##
在JavaScript中，一切都是对象。

## 1.5 继承 ##
JavaScript使用的式基于对象的（原型式（protoypal））继承，它可以用来模仿基于类的（类式（classical））继承。

## 1.6 JavaScript中的设计模式 ##



## 1.7 小结 ##
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

第2章 this全面解析

第3章 对象

第4章 混合对象“类”

第5章 原型

第6章 行为委托
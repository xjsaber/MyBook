# Chapter4 Node中的JavaScript #

## global对象 ##
在浏览器中，全局对象指的就是window对象。在window对象伤定义的任何内容都可以被全局访问到。ps.setTimeout其实就是window.setTimeout，document就是window.document。

* global: 和window一样，任何global对象伤的属性都可以被全局访问到。

* process: 所有全局执行上下文中的内容都在process对象中。在浏览器中，只有一个window对象，在Node中，也只有一个process对象。举例来说，在浏览器中窗口的名字是window.name，类似的，在Node中进程的名字是process.title。

## 实用的全局对象 ##
process.nextTick函数可以将一个函数的执行时间规划到下一个事件循环中：
	
	console.log(1);
	process.nextTick(function() {
		console.log(3);
	});
	console.log(2);
把他想象成是setTimeout(fn, 1)或者“通过异步的方法在最近的将来调用该函数”，输出结果为“1，2，3”。

## 模块系统 ##
JavaScript原生态是一个全局的世界。所有如setTimeout、document等这样在浏览器端实用的API，都是全局定义的。

Node摒弃了采用定义一堆全局变量（或者跑很多可能根本就不会用到的代码）的方式，转而引入了一个简单但强大无比的模块系统，该模块系统有三个核心的全局对象：require、module和exports。

## 绝对和相对模块 ##
绝对模块是指Node通过在其内部node_modules查找到的模块，或者Node内置的如fs这样的模块。如当你安装好了colors模块，其路径就变成了./node_modules/colors。可以直接通过名字来require这个模块，无须添加路径名：`require('colors')`

相对模块将require指向一个相对工作目录中的JavaScript文件。模块可以实用模块系统的功能来提供更加简洁独立的API以及抽象。在同一目录中创建名为module_a.js，需要在require参数前加上./

## 暴露API ##
要让模块暴露一个API成为require调用的返回值，就要依靠module和exports这两个全局变量。

在默认情况下，每个模块都会暴露出一个空对象。如果你想要在该对象上添加属性，那么简单地实用exports即可：

exports其实就是对module.exports的引用，其在默认情况下是一个对象。要是在该对象上逐个添加属性无法满足需求，还可以彻底重写module.exports。

## 事件 ##
Node.js中的基础API之一就是EventEmitter。

	var EventEmitter = process.EventEmitter
		, MyClass = function (){};

	MyClass.prototype._proto_ = EventEmitter.prototype;

	var a = new MyClass;
	a.on('某一事件', function(){
		//做些什么
	});
事件是Node非阻塞设计的重要体现。Node通常不会直接返回数据（因为这样可能会在等待某个资源的时候发生线程阻塞），而是采用分发事件来传递数据的方式。

## 小结 ##
了解在浏览器端和Node.js中书写JavaScript的主要区别。
了解Node添加的但在语言标准中没有的JavaScript中常用API，如定时器、事件、二进制数据以及模块。
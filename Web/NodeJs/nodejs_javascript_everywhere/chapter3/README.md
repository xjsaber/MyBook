# chapter3 阻塞与非阻塞IO #


## 能力越强，责任就越大 ##

## 阻塞 ##

	//PHP
	print("Hello");
	sleep(5);
	print("World");
	//node
	console.log("Hello");
	setTimeout (function () {
		console.log("World");
	}, 5000);
上述两段代码不仅仅时语义的区别（Node.js使用了回调函数），两者区别集中体现在阻塞和非阻塞的区别伤。在第一个例子中，PHP sleep()阻塞了线程的执行。当程序进入会睡眠时，就什么事情都不做了。

而Node.js使用了事件轮询，因此这里setTimeout是非阻塞的。换句话说，如果在setTimeout厚再加入console.log语句的划，该语句会被立刻执行;
	
	console.log('Hello');
	setTimeout(function () {
		console.log('World')
	}, 5000);
	console.log('Bye');
采用了事件轮询意味着，从本质上来说，Node会先注册事件，随后不停地询问内核这些事件是否已经分发。当事件分发时，对应的回调函数就会被触发，然后继续执行下去。如果没有事件触发，则继续执行其他代码，直到有新事件时，再去执行对应的回调函数。

文件描述符是抽象的句柄，存有对打开的文件、socket、管道等的引用。本质上来说，当Node接收到从浏览器发来的HTTMP请求时，底层的TCP连结会分配一个文件描述符。随后，如果客户端向服务器发送数据，Node就会收到该文件描述符上的通知，然后触发JavaScript的回调函数。

### 单线程的世界 ###
Node是单线程的

	var start = Date.now();

	setTimeout(function () {
		console.log(Date.now() - start);

		for (var i = 0; i < 100000000; i++) {}
	}, 1000);

	setTimeout(function () {
		console.log(Date.now() - start);
	}, 2000);

### 错误处理 ###

### 堆栈追踪 ###

### 小结 ###
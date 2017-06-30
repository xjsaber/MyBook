# 第8章 客户端JavaScript的重要性 #

## 8.1.1 Web应用程序的发展 ##

#### Web应用程序的功能 ####

* 拖拽操作（Drag and drop）
* 异步读取
* 键盘快捷键（键盘访问）
* 动画效果

### 8.1.2 JavaScript的性能提升 ###

### 8.1.3 JavaScript的作用 ###

## 8.2 HTML与JavaScript ##

### 8.2.1 网页显示过程中的外部流程 ###

* 分析HTML
* 构造DOM树
* 载入外部JavaScript文件以及CSS文件
* 载入图像文件等外部资源
* JavaScript在分析后开始执行
* 全部完成

### 8.2.2 JavaScript的表述方式及其执行流程 ###

1. \<script>标签
2. DOMContentLoaded
3. 读取外部JavaScript文件
4. 动态载入
5. onload

#### onload ####



#### DOMContentLoaded ####

对于onload方法，执行JavaScript时可能需要一定的等待时间，可以使用DOMContentLoaded来解决。DOMContentLoaded是在完成HTML解析后发生的事件。

	document.addEventListener('DOMContentLoaded', function () {
		alert('hello');
	}, false);

#### 动态载入 ####

	var script = document.createElement('script');
	script.src = 'other-javascript.js';
	document.getElementsByTagName('head')[0].appendChild(script);

在使用这种方法执行JavaScript时，JavaScript文件在下载过程中并不会阻断其他的操作。如果直接在页面内书写script元素，则在下载该JavaScript文件的过程中，其他图像文件或CSS文件的下载将被阻断。

## 8.3 运行环境与开发环境 ##

### 8.3.1 运行环境 ###

### 8.3.2 运行环境 ###

## 8.4 调试 ##

### 8.4.1 alert ###

### 8.4.2 console ###

#### 虚拟console对象 ####

#### 显示消息以及对象 ####

* console.log()
* console.warm()
* console.debug()
* console.info()
* console.error()

debug()方法，则可以知道输出的内容是在JavaScript文件中的哪一行。

#### 分析对象并显示 ####

console.dir()方法可以完整输出作为参数接收到的对象，并将其一目了然地显示出来。console.dirxml()方法可以将DOM元素以HTML的形式显示。

#### 显示栈追踪 ####

使用console.trace()方法就可以显示该函数的调用者，并可以据此了解具体是哪一个对象的哪一个事件触发了该函数。

#### 测量时间、次数与性能 ####

通过console.time()方法与console.timeEnd()方法测量这两个方法之间经过的时间。其参数分别为每次计时的名称。具有相同名称的方法将会配对，其间经过的时间则会输出。

#### 使用断言 ####

console.assert()的功能是仅在指定条件为false时输出日志。

### 8.4.3 onerror ###

### 8.4.4 Firebug，Web Inspector（Developer Tools），Opera Dragonfly ###

* HTML/CSS的内容确认与编辑
* JavaScript性能分析工具
* JavaScript控制台
* 网络监控
* JavaScript调试工具

#### JavaScript调试工具 ####

	debugger

#### JavaScript性能分析工具时的一些注意点 ####

#### 网络监控 ####


## 8.5 跨浏览器支持 ##

### 8.5.1 应当提供支持的浏览器 ###

### 8.5.2 实现方法 ###

#### 根据用户代理来判断 ####


#### 根据功能是否被支持来判断 ####



#### 通过用户代理判断与通过功能测试判断 ####

## 8.6 Window对象 ##

Window对象是一个全局对象。Window对象即对应于正被浏览器显示的窗口的一种对象。

Window对象是JavaScript所能操作的最高层的对象。

* navigator
* location
* history
* screen
* frames
* document
* parent, top, self

### 8.6.1 Navigator 对象 ###

Window对象的navigator属性是一个Navigator对象.

### 8.6.2 Location 对象 ###

Window对象的location属性是一个Location对象。Location对象包含了与当前显示的URL相关的一些信息。

####  href 属性 ####

|属性名|说明|示例|
|--|--|
|protocol|协议|http:|
|host, hostname|主机名|example.com|
|port|端口|8080|
|pathname|路径|/foo|
|search|查询参数|?q=bar|
|hash|哈希令牌|#baz|

####  assign()方法 ####

可以使用assign()方法从当前页面跳转至另一页面。这与设置href属性的值的效果是一样的。

	location.assign('http://foobar.example.com');

####  replace()方法 ####



####  reload()方法 ####



### 8.6.3 History对象 ###

Window对象的history属性是一个History对象。back()方法与forward()方法实现浏览器历史纪录中的后退与前进。

go()方法

### 8.6.4 Screen对象 ###

Window对象的screen属性是一个Screen对象。

Screen对象包含了画面的大小与发色数等信息。通过这些值，能够针对大尺寸的显示器和小尺寸屏幕分别显示不同的画面。

### 8.6.5 对Window对象的引用 ###

#### window属性 ####

可以用过window属性获取对Window对象的引用。window属性所指向的Window对象在JavaScript中的一个全局对象。也就是说，在客户端JavaScript中所操作的所有函数与对象都是window属性所引用的这个对象的属性。

#### frames属性 ####

当窗口中含有多个框架时，frams属性中将会含有这些框架的引用。如果没有框架，frames属性中则是一个空的数组。可以通过<frameset>标签、<frame>标签，或<iframe>标签来生成一个框架。

框架本身也是一种Window对象，因此额可以以

	window.frames[1].frame[2]

#### self属性 ####

可以通过self属性对Window对象自身引用。self属性与window属性一样，引用了一个Window对象。

#### parent属性 ####

可以通过parnt属性从子框架处取得其父框架的引用。如果不存在父框架，parent属性则是对当前Window对象自身的一个引用。

#### top属性 ####

如果要在框架层层前套的情况下获取最上层的框架引用，可以使用top属性。如果自身就是最上层的框架，top属性则是对当前Window对象自身的一个引用。

### 8.6.6 Document对象 ###

Window对象的document属性是一个Document对象。

对Cookie的操作是Document对象中不属于DOM范畴的一种功能。HTML5的Web Storage或Indexed Database，浏览器自身能够保存一定的数据，但在没有实现这些功能之前Cookie是唯一一种可以保存数据的方式。

可以通过Document对象的cookie对Cookie进行读写操作。


# Chapter5 命令行工具（CLI）以及FS API：首个Node应用 #

处理进程（stdio）的stdin以及stdout相关的API，还有那些与文件系统（fs）相关的API。

## 需求 ##
* 程序需要在命令行运行。这就意味着程序要么通过node命令来执行，要么直接执行，然后通过终端提供交互给用户进行输入、输出。
* 程序启动后，需要显示当前目录下列表。
* 选择某个文件时，程序需要显示该文件内容。
* 选择一个目录时，程序需要显示该目录下的信息。
* 运行结束后程序退出。

根据上述需求，可以将此项目细分到如下几个步骤：

1. 创建模块
2. 决定采用同步的fs还是异步的fs。
3. 理解什么是流（Stream）
4. 实现输入输出
5. 重构
6. 使用fs进行文件交互
7. 完成

## 编写首个Node程序 ##

### 创建模块 ###
将该目录命名为file-explorer，定义package.json文件，既可以方便地对NPM中注册的模块依赖进行管理，将来也能对模块进行发布。

需要创建一个简单的package.json文件:

	# package.json
	{
		"name": "file-explorer"
	  , "version": "0.0.1"
	  ,	"description": "A command-file file explorer!"
	}

### 同步还是异步 ###
从声明依赖关系开始，由于stdio API是全局process对象的一部分，所以，我们的程序唯一的依赖就是fs模块：
	
	# index.js
	/**
	 * Module dependencies.
	 */
	var fs = require('fs');
获取当前目录的文件列表。
fs模块是唯一一个同时提供同步和异步API的模块。

### 理解什么事流（stream） ###
console.log会输出到控制台。事实上，console.log内部做了这些事情：它在指定的字符串后加上\n（换行）字符，并将其写到stdout流中。
`console.log("Hello world");` 和 `process.stdout.write("Hello world");`

process全局对象中包含了三个流对象，分别对应三个UNIX标准流:

	- **stdin**：标准输入
	- **stdout**：标准输出
	- **stderr**：标准错误
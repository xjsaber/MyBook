# 第3章 初探HTML #

## 3.1 使用元素 ##
代码清单3-1 HTML元素示例

	I like <code> 

### 3.1.1 了解本章用到的元素 ###
* a	生成超链接
* body 生成HTML文档的内容
* button 生成用以提交表单的按钮
* code 表示计算机代码片段
* DOCTYPE 表示HTML文档的开始
* head 表示HTML文档的头部区域
* hr 表示主题的改变
* html 表示文档的HTML部分
* input 表示用户输入的数据
* label 生成另一元素的说明标签
* p 表示段落
* style 定义CSS样式
* tabel 表示用表格组织的数据
* td 表示表格单元格
* textarea 生成表头单元格
* title 表示HTML文档的标题
* tr 表示表格行

### 3.1.2 使用空元素 ###
元素的开始和结束标签之间并非一定要有内容。没有内容的元素称为空元素。

代码清单3-2 空HTML元素

	I like <code></code> apples and oranges
### 3.1.3 使用自闭合标签 ###
空元素可以更简洁地只用一个标签标识。

代码清单3-3 只用一个标签表示空元素
	
	I like <code/> apples and oranges.

### 3.1.4 使用虚元素 ###
有些元素只能使用一个标签标识，在其中放置任何内容都不符合HTML规范。这类元素称为虚元素（void element）

label和tag都是标签的意思。

## 3.2 使用元素属性 ##
元素可以用属性（attribute）进行配置。

代码清单3-6 使用元素属性

	I like <a href="/apples.html">apples</a> and oranges.
	属性只能用在开始标签或单个标签上，不能用于结束标签。

### 3.2.1 一个元素应用多个属性 ###
一个元素可以应用多个属性，这些属性间以一个或多个空格分隔即可。

### 3.2.2 使用布尔属性 ###
这种属性不需要设定一个值，只消将属性名添加到元素中即可。

代码清单3-8 布尔属性
	
	Enter your name:<input disabled>
	布尔属性是disabled，元素中只添加了该属性的名称。

代码清单3-9 为布尔属性指定空字符串值

	Enter your name:<input disabled="">
	Enter your name:<input disabled="disabled">

### 3.2.3 使用自定义属性 ###
用户可自定义属性，这种属性必须以data-开头

代码清单3-10 为元素应用自定义属性
	
	Enter your name: <input> disabled="true" data-creator="adam" data-purpose="collection"</input>
自定义属性是对HTML4中“浏览器应当忽略不认识的属性”这种广泛应用的技巧的正式规定。在这类属性名称之前添加前缀data-是为了避免与HTML的未来版本中可能增加的**属性名冲突**。
## 3.3 创建HTML文档 ##
在HTML5中更加强调将内容与呈现形式分开，正式因为认识到HTML内容并不总是被显示给用户看。
### 3.3.1 外层结构 ###
HTML文档的外层结构由两个元素确定：DOCTYPE和html

代码清单3-11 HTML文档的外层结构
	<!DOCTYPE HTML>
	<html>
		<!--elements go here -->
	</html>

### 3.3.2 元数据 ###
HTML文档的元数据部分可以用来向浏览器提供文档的一些信息。元数据包含在head元素内部。

代码清单3-12 在HTML文档中添加head元素

	<!DOCTYPE HTML>
	<html>
		<head>
			<!-- metadata goes here -->
			<title>Example</title>
		</head>
	</html>

### 3.3.3 内容 ###

代码清单3-13 在HTML文档中添加body元素
	<DOCTYPE HTML>
	<html>
		<head>
			<!-- metadata goes here -->
			<title>Example</title>
		</head>
		<body>
			<!--content and elements go here-->
			I like <code>apples</code> and oranges.
		</body>
	</html>
body元素告诉浏览器该向用户显示文档的哪个部分。

### 3.3.4 父元素、子元素、后代元素和兄弟元素 ###
HTML文档中元素之间有明确的关系。包含另一个元素的元素是被包含元素的*父元素*。

### 3.3.5 了解元素类型 ###
HTML5规范将元素分为三大类：元数据元素（metadata element）、流元素（flow element）和短语元素(phrasing element)。
元数据元素用来构建HTML文档的基本结构，以及就如何处理文档向浏览器提供信息和指示。

## 3.4 使用HTML实体 ##

表3-3 常用HTML实体

|字符|实体名称|实体编号|
| -- |:------:| -----:|
|<|&lt|$#60|
|>|&gt|$#62|
|&|&amp|$#30|

## 3.5 HTML5全局属性 ##
每种元素都能规定自己的属性，这种属性称为局部属性（local attribute）
全局属性（global attribute），用来配置所有元素的共有的行为。

### 3.5.1 accesskey属性 ###
使用accesskey属性可以设定有一个或几个用来选择页面上的元素的**快捷键**。

代码清单3-14 使用accesskey属性
	
	Name:<input type="text" name="name" accesskey="n" />
	Password:<input type="password" name="password" accesskey="p" />
	<input type="submit" value="Log In" accesskey="s"/>

### 3.5.2 class属性 ###
class属性用来将元素归类。

代码清单3-16 定义依靠类起作用的样式
	
	<a class="class1 class2" href=""></a>
	<a class="class1 otherclass" href=""></a>

代码清单3-17 在脚本中使用class属性
	
	var elems = document.getElementsByClassName("otherclass");
	for (i = 0; i < elems.length; i++){
		var x = elems[i];
		x.style.border = "thin solid black";
		x.style.backgroundColor = "white";
		x.style.color = "black";
	}

### 3.5.3 contenteditable属性 ###
contenteditable是HTML5种新增加的属性，其用途是让用户能够修改页面上的内容。

代码清单3-18 使用contenteditable属性
	
	<p contenteditable="true">It is raining right now</p>

### 3.5.4 contentmenu属性 ###
contextmenu属性用来为元素设定快捷菜单。这种菜单会在受到触发的时候弹出来。

### 3.5.5 dir属性 ###
dir属性用来规定元素中文字的方向。其有效值为两个：ltr（用于从左到右的文字）和rtl（用于从右到左的文字）。

代码清单3-19 使用dir属性
	
	<p dir="rtl">This is right-to-left</p>
	<p dir="ltr">This is left-to-right</p>

### 3.5.6 draggable属性 ###
dragable属性是HTML5支持拖放操作的方式之一，用来表示元素是否可以被拖放。
### 3.5.7 dropzone属性 ###
dropzone属性是HTML5支持拖放操作的的方式之一，与上述dragable属性搭配使用。
### 3.5.8 hidden属性 ###
hidden是个布尔属性，表示相关元素当前务虚关注。浏览器对它的处理方式是隐藏相关元素。

### 3.5.9 id属性 ###
id属性用来给元素分配一个唯一的标识符。
为了根据id属性值应用样式，需要在定义样式时使用一个#号开头后接id属性值的选择器（selector）。

### 3.5.10 lang属性 ###
lang属性用于说明元素内容使用的语言。

	<p lang="en">Hello - how are you?</p>
lang属性值必须使用有效的ISO语言代码

### 3.5.11 spellcheck属性 ###
spellcheck属性用来表明浏览是否应该对元素的内容进行拼写检查。

代码清单3-23 使用spellcheck属性

	<textarea spellcheck="true">This is some mispelled text</textarea>
### 3.5.12 style属性 ###
style属性用来直接在元素身上定义CSS样式

### 3.5.13 tabindex属性 ###
HTML页面上的键盘焦点可以通过按Tab键在各元素之间切换。用tabindex属性可以改变默认的转移顺序。

代码清单3-25 使用tabindex属性
	
	<label>Name: <input type="text" name="name" tabindex="1"/></label>

### 3.5.14 title属性 ###
title属性提供了元素的额外信息。

	<a title="Apress Publishing"

## 3.6 有用的HTML工具 ##
跟HTML打交道时我认为有帮助的工具有两种，第一重是一款优秀的HTML编辑软件。另一种工具是大多数浏览器菜单中都有的“查看源代码。”

## 3.7 小结 ##

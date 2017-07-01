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

### 9.3.6 XPath ###

### 9.3.7 Selector API ###

## 9.5 节点的内容更改 ##

Node.replaceChild()方法替换节点

	var newNode = document.createElement('div');
	var oldNode = document.getElementById('foo');
	var parentNode = oldNode.parentNode;
	
## 9.6 节点删除 ##

Node.removeChild()方法来删除节点

## 9.7 函数是一种对象 ##

### 9.7.1 innerHTML ###

innerHTML属性并不是一种在DOM标准中被定义的功能，而是一种在HTML5中被定义的属性。

	var elem = document.getElementById('foo');
	elem.innerHTML = '<div>..</div>';

## 9.8 DOM操作的性能 ##

画面的重新绘制这一步需要花费开销的，所以应当尽可能避免重新绘制画面。

当需要新增10个div的元素时，最好先div相加，然后进行画面绘制。而不是加一个div，画面绘制一次。


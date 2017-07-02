# 第11章 客户端JavaScript实践 #

## 11.1 样式 ##

#### 11.1.1 样式的变更方法 ####

* 通过className属性更改class名
* 通过classList属性更改class名
* 更改style属性
* 直接更改样式表

#### 11.1.2 位置的设定 ####

** position 属性**

* static
* fixed
* absolute
* relative

设定了static以外的值，能够设置top、bottom、left、right属性。

** static 属性**

position属性的默认值。

** fixed 属性**

如果将position属性指定为fixed，则将会以浏览器窗口为基准来确定元素的相对位置。由于是相对于浏览器窗口的位置，因此即使对页面进行了滚动操作，元素在画面上的位置也不会发生改变。也就是说元素将始终保持在同一位置。

** absolute **

** relative **

如果将position属性指定为了relative，则会在根据HTML中所写的标签进行配置的基础上，对元素的相对位置进行设置。

#### 11.1.3 位置 ####

** 屏幕坐标 **

screenX和screenY属性来获取屏幕坐标。屏幕坐标是一种以计算机显示器的左上角为原点的坐标系。

** 窗口坐标 **

通过clientX和clientY属性来获取窗口坐标。窗口坐标是一种以浏览器的显示范围的左上角为原点的坐标系。这一坐标系与文档、元素的滚动情况无关，其坐标系仅由内容的显示位置决定。

** 文档坐标 **

pageX和pageY来获取元素在文档中的位置。文档坐标是一种以文档页面的左上角为原点的坐标系。与窗口坐标不同，其坐标值与显示位置无关，是由元素在整个文档中的位置决定的。

** 在特定元素内的相对坐标 **

layerX与layerY，或offsetX与offsetY属性，可以获取触发了事件的元素内的相对坐标。

Element.getBoundingClientRect()虽然不能直接取得相对坐标，但也能实现类似的功能。

	function onclick(event) {
		var x = event.clientX; //窗口坐标系中鼠标指针的x坐标
		var y = event.clientY;
		var r = event.target.getBoundingClientRect();
		x -= r.left;
		y -= r.top;	
	}

#### 11.1.4 动画 ####

position:absolute

## 11.2 Ajax ##

AJAX是Asynchronous JavaScript + XML的简称。实际含义为“不发生页面跳转、异步载入内容并改写页面内容的技术”。

### 11.2.1 异步处理的优点 ###

AJAX的关键在于它是以异步的方式执行的。

### 11.2.2 XMLHttpRequest ###



### 11.2.3 基本的处理流程 ###

** XMLHttpRequest对象的创建 **

	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				alert(xhr.responseText);
			}
		}
	}
	xhr.open('GET', 'http://example.com/something');
	xhr.setRequestHeader('If-Modified-Since', 'Thu 01 Jun 1970 00:00:00 GMT');
	xhr.send(null);

readyState的含义

|readyState|含义|
|--|--|
|0|open()尚未被调用|
|1|send()尚未被调用|
|2|服务器尚未返回响应|
|3|正在接收来自服务器的响应|
|4|完成了对来自服务器的响应的接收|

在responseText中包含了服务器响应的字符串形式。而对于XML的情况，响应会以DOM对象的形式包含于reponseXML之中。

传递给open()的参数是HTTP请求类型及通信目标服务器的URL。

如果传递了false值至第3个参数，XMLHttpRequest就会执行同步通信。该参数的默认值为true，也就是将执行异步通信。如果是同步通信的话，自然在此期间无法执行其他操作。对于用户的可操作性来说，执行同步通信几乎不具有任何优点。

setRequestHeader()用于请求头部的设置。在通信的目标服务器会自动发送对应的Cookie，所以并不需要显式地设置。当然也可以显式地设定一个不同的值。

实际相服务器发送请求的是send()。如果是POST请求类型，则会将参数所收到的数据发送至服务器。如果是GET请求类型或HEAD请求类型等不需要发送数据的HTTP请求类型，则参数为null。

### 11.2.4 同步通信 ###

XMLHttpRequest。如果要执行同步通信，则不必对onreadystatechange事件处理程序进行设定。在执行了send()之后该处理将会进入待机状态，只要在send()之后继续书写对响应的处理操作即可。

	var xhr = new XMLHttpRequest();
	xhr.open('GET', 'http://example.com/something', false);
	xhr.setRequestHeader('IF-Modified-Since', 'Thu, 01...');
	xhr.send(null);
	if (xhr.status === 200) {
		alert(xhr.responseText);
	}

在进行同步通信时，代码顺序和实际的通信过程是对应的。不过在实际操作中通常不会使用这种方式。

在客户端JavaScript中最重要的是如何减少用户的等待时间，尽可能向用户提供流畅的使用体验。

### 11.2.5 超时 ###

在进行同步通信时，如果通信过程很费事，处理操作则会在send()处等待，其他的处理将会无法进行。这时，应当在长时间无法取得响应的情况下取消该请求。这种情况称为请求超时。

XMLHttpRequest基本上采用的都是异步通信。所以无论通信需要话费多少时间，都不会影响用户操作。

如果要取消请求，其实只需要执行abort()方法即可。而通过setTimeout()方法可以在一定时间后执行abort()方法，从而实现超时功能。另外还有一个clearTimeout()方法，如果在一定时间内收到了返回的响应，该方法就将会取消abort()的执行。

	var xhr = new XMLHttpRequest();
	var timerId = window.setTimeout(function() {
		xhr.abort();
	}, 5000); //5秒后将会超时
	
	xhr.onreadystatechange = function() {
		if (request.readyState === 4) {
			// 取消超时处理
			window.clearTimeout(timeId);
		}	
	}
### 11.2.6 响应 ###

** 通用类型的响应 **

通过responseText属性来引用一个XMLHttpRequest响应。即使目标的Content type不是text/plain，也能够以该属性进行设定。这时所使用的是响应的body部分的内容。

	var xhr = XMLHttpRequest();
	// ...
	var dom = document.getElementById('foo');
	foo.innerHTML = xhr.reponseText;

** XML类型的响应 **

从XMLHttpRequest这名称能够看出，以XML的形式接收XMLHttpRequest的响应。在接收时，最好使用reponseXML属性而不是reponseText属性。

	var xhr = XMLHttpRequest();
	var xml = xhr.reponseXML();
	
	// 家庭xml的内部是这样的内容
	//<result>
	//	<apiversion>1.0</apiversion>
	//	<value>foo</value>
	//</result>
	alert(xml.getElementsByTagName('value')[0].firstChild.nodeValue); // => foo

** JSON 形式的响应 **

reponseText属性的内容转化为JSON，需要使用JSON.parse()方法

	var xhr = XMLHttpRequest();
	var json = JSON.parse(xhr.responseText);

	// 假定json的内容是这样的内容
	// {
	//		"apiversion": 1.0,
	//		"value": "foo"
	//	}
	alert(json.value); // => foo

在浏览器对JSON.parse实现之前，是通过eval来对responseText求值的。如果JSON字符串的内容不正确，有可能导致页面的数据的损坏或被错误地改写。

### 11.2.7 跨源限制 ###

所以跨源限制指的是，对源不同的通信进行限制。而这里的源指的是由URL的协议（http:或https:等）、主机名、端口号。在Web领域，为了确保安全性，只有同源的通信才能被允许进行，这称为同源策略。

对于XMLHttpRequest来说，同源策略的含义是，一个XMLHttpReqeust对象只能发送至一个特定的服务器，即提供了使用该XMLHttpRequest对象的文档的下载的那个服务器。不过，只要让服务器转发该请求，就能够将请求发送至不同域的服务器。

### 11.2.8 跨源通信 ###

跨源通信指的是在不同的源之间收发请求。一般情况下是无法通过XMLHttpRequest向不同的源发送请求的。既可以通过服务器转发或Flash来实现跨源请求的发送，也可以通过JS实现跨源通信。

* JSONP
* iframe攻击
* window.postMessage()
* XMLHttpRequest level 2

### 11.2.9 JSONP ###

虽然无法通过XMLHttpRequest进行跨源通信，但是可以将script标签的src属性指定为其他域中的JavaScript文件并将其载入。如果在此处动态地创建script标签的话就能实现对其他域中的数据动态读取。

不过，如果仅仅是取得了数据，还无法在客户端使用。因此产生了JSONP这一概念。

JSONP是JSON with Padding的简称。这里的Padding指的是向JSON数据中添加函数名。

	callback({
		"foo": "This is foo",
		"bar": "This is bar",
		"baz": "This is baz",
	});

当客户端对其求值时，如果在客户端侧对该函数进行了定义，就能够执行这个函数。即JSONP的思想是不但能从服务器处获取数据，而且还能将这些数据作为函数的参数使用。

	<script>
	function foo (json) {
		// 使用json数据进行一些操作
	}

	function loadData() {
		var elem = document.createElement('script');
		elem.src = 'http://api.example.com/som-data&callback=foo';
		document.getElementByTagName('head')[0].append(elem);
	}
	</script>

JSONP存在的一个问题是它无法在POST请求类型中使用。这时只能够动态创建script标签并读取数据，而无法从客户端发出数据。如果想让客户端以POST的形式发送跨源数据，则必须通过其他手段。

### 11.2.10 iframe攻击(iframe back) ###

通过iframe来进行跨源通信的原理稍微有些复杂。

父页面与孙iframe必须是相同的域。由于存在同源策略。因此无法对不同域的iframe内的DOM元素进行操作，也无法在一个iframe中对其父节点的DOM元素进行操作。

* 父页面（my.example.com）
* 子iframe（other.example.com）
* 孙iframe（my.example.com）

** API请求 **

首先，在my.example.com的页面中将会指定一个other.example.com中的html来创建一个iframe。URL中包含了哈希片段，哈希片段被指定为了进行API调用时所需的数据。

** 响应 **

在other.example.com的页面，将会通过使用XMLHttpRequest之类的方式吊用other.example.com中的功能并获取数据。

** 回调函数 **

由于孙iframe与父页面都是my.example.com中的页面，因此可以在孙iframe中执行父页面中的函数。这时，孙iframe的onload将会调用父页面的函数，从而实现callback的调用。

优点：安全性比较高，IE也能够正常工作

缺点：跨源通信比较复杂

### 11.2.11 window.postMessage ###

### 11.2.12 XMLHttpRequest Level 2 ###

XMLHttpRequest无法用于跨源通信，但这只是对于Level 1而言的。在XMLHttpRequest Level 2中，新增了一些功能以实现对跨源通信的支持。不过，要进行跨源通信就必须得到服务器端的许可。所以必须在响应中包含Access-Control-Allow-Origin这一HTTP头部，以指定可以访问的源。如果Access-Control-Allow-Origin这一头部的指被指定为了"*"的话，则表示允许来自任意源的访问。

在通过XMLHttpRequest进行跨源通信时，默认为不发送Cookie。如果要发送Cookie，则必须将withCredentials属性设置为true。

	var xhr = new XMLHttpRequest();
	xhr.open('GET', 'http://other.example.com', true);
	xhr.withCredentials = true; 设定为将会发送Cookie
	xhr.onreadystatechange = function() {
		// 进行一些操作
	};

#### 11.2.13 跨源通信的安全问题 ####

通过跨源通信在不同的域中获取数据的做法已经被认为是理所应当的了，得到了广泛的使用。然后，由同源策略的指定而不难想到，在不同的域之间进行通信是存在安全风险的。

## 11.3 表单 ##

表单最大的不足就是在submit时会发生页面跳转，而AJAX却是一种不进行页面跳转，直接改写页面的内容技术。表单在submit时一定会发生页面跳转，因此在这一点上它是与AJAX的理念相悖的。

得益于AJAX，出线了不使用表单就能与服务器进行数据收发的方式。通过使用JavaScript来发挥表单功能的方法，这不仅仅是为了向服务器发送数据，其目的是实现更为用户友好的表单功能。

### 11.3.1 表单元素 ###

HTMLFormElement继承于HTMLElement的接口。

HTMLFormElement的属性

|属性名|说明|
|--|--|
|elements|form内的input元素一览|
|length|form内的input元素的数量|
|name|form的名称。JavaScript可通过它引用表单|
|acceptCharset|form所支持的字符集|
|action|form的action元素|
|enctype|form的content type|
|method|在发送数据时所用的HTTP类型|
|target|action结果的写入的目标|
|submit()|发送数据|
|reset()|将form还原至初始化状态|

在向服务器POST数据时，可以通过acceptCharset、action、enctype与method来设定元信息。

elements是对表单内的控件的引用。各个表单控件将按照它们在HTML中的书写顺序被引用。

	<body>
		<form>
			<input type="text">
			<input type="password">
			<input type="email">
		</form>
		<script>
			var email = document.forms[0].elements[2].value;
			alert(email);
		</script>
	</body>

submit()方法被执行后的效果与被按下submit键的效果一样，都将会向服务器发送数据。不过，与按下submit键时不同的是，submit事件不会被触发，于是，onsubmit事件处理程序也不会被执行。reset()也是。

### 11.3.2 表单控件 ###

在表单中用于接收输入信息的元素称为表单控件。

** 表单控件的通用属性 **

|属性|说明|
|--|--|
|form|该控件所属的表单元素|
|disabled|控件是否被禁用|
|name|控件的名称|
|type|控件的类型|
|value|控件的值|
|focus()|使控件获得焦点（*1）|
|blur()|使控件失去焦点（*1）|

通过以JavaScript调用focus()方法与blur()方法，可以使特定的元素获得或失去焦点。与submit()和reset()方法不同的是，在执行这些方法的时候将会分别触发focus事件与blur事件。

### 11.3.3 内容验证 ###

**内容验证的必要性**

1. 在客户端完成了检查工作，在与服务器通信过程中的事件损失将会消除。与将数据发送
2. 避免明显有误的数据，从而减轻了服务器的处理量。

**进行内容验证的时机**

有多次进行内容验证的时机。其中之一自然是在按下submit键时验证。在按下了submit键之后，检查整个form内所有的元素的值，如果发现了不合法的数据，则返回false，并取消数据的发送。

### 11.3.4 可用于验证的事件 ###

**submit**

仅通过submit事件的事件处理程序就能够取消表单的发送操作。

**focus、blur**

input元素在获取了焦点时将会触发focus事件。而当其失去焦点时，则将触发blur事件。

可以认为元素在触发了blur事件时相应的输入已经完成，所以在这时验证内容也是一种妥当的选择。不过在这种情况下，焦点已经转移至了下一个输入元素，所以应当怎样提供反馈以增强用户体验是一个问题。

**change**

在input元素的值发生变化时该事件将被触发。虽然文本框也对这一事件提供了支持，不过他主要还是被用于复选框或单选框等用于选择值择的input元素之中。

对于文本框来说，change事件仅会在文本框失去焦点，且其value属性的值与具有焦点时不同的情况下，才会被触发。因此，在输入过程中该事件不会被触发。change事件本应该是有字符输入而改变了value时被触发，而在文本框中无法实现这一效果，故而无法对其使用。

**keydown、keyup、keypress**

keydown、keyup与keypress事件将在发生键盘输入时被触发。当有键被按下时将触发keydown，当释放按下的键时将触发keyup，当有键被按下且输入了字符将触发keypress。不过，与键盘相关的事件的执行方式会根据浏览器以及系统平台的不同而有所差异，所以很难对其进行处理。

**input**

当input元素发生了输入行为时将会触发input事件。对于文本框来说，每输入1个字符都会触发该事件。而这正是之前期望通过change事件所获得的效果。

input事件与keypress事件等由键盘输入触发的事件不同，只有在值发生变化时才会被触发。也就是说，即使通过方向键改变了光标插入标记的位置，也不会触发input事件。

### 11.3.5 使用表单而不产生页面跳转的方法 ###

form的target属性

|值|显示结果的位置|
|--|--|
|_blank|新建窗口|
|_self|当前框架（窗口）|
|_parent|父框架|
|_top|解除框架的分割并在整个窗口中显示|
|框架名、窗口名|所指定的任意框架（窗口）|

为了防止页面跳转的发生，可以将target指定为当前窗口之外的窗口。不过打开新窗口的做法会有些碍眼，甚至可能会被浏览器的弹窗锁定功能所拦截。应该将target指定为空的iframe，并将该iframe的宽度和高度都设为0，使其不显示。
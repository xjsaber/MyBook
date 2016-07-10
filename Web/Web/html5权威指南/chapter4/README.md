# 第4章 初探CSS #
CSS（层叠样式表）用来规定HTML文档的呈现形式（外观和格式编排）。

## 4.1 定义和应用样式 ##
CSS样式由一条或多条以分号隔开的样式声明组成。每条声明包含着一个CSS属性和该属性的值，二者以冒号分隔。

代码清单4-1 一条简单的CSS样式
	
	background-color: grey; color:white
	background-color 属性
	grey 值
	background-color: grey 声明

### 4.1.1 了解本章所用的CSS属性 ###
| 属性        | 说明|
| ------------- |:-------------:|
| background-color | 设置元素的背景颜色|
| border    	   | 设定围绕元素的边框|
| color | 设置元素的前景颜色|
| font-size| 设置元素文字的字号|
| height| 设置元素高度 |
| padding| 设定元素内容与边框之间的间距|
| text-decoration| 设置元素文字的装饰效果，如本章用的下划线|
| width| 设置元素的宽度|

### 4.1.2 使用元素内嵌样式 ###


### 4.1.3 使用文档内嵌样式 ###
用style元素（而不是style属性）定义文档内嵌样式，通过CSS选择器指示浏览器应用样式。

代码清单4-3 使用style元素
	
	<style type="text/css">
		a{
			background-color: grey;
			color:white;
		}
	</style>

![4-3 定义在style元素之内的样式剖析](img/4-3.jpg)

代码清单4-4 使用style元素内定义多条样式
	
	<style type="text/css">
		a{
			background-color: grey;
			color:white;
		}
		a{
			border: thin black solid;
			padding: 10px;
		}
	</style>

![4-3 定义在style元素之内的样式剖析](img/4-3.jpg)

4.1.4 使用外部样式表

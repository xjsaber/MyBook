# Chapter3 定位元素 #

## 3.1 理解盒模型 ##

边框（border）
内边框（padding）
外边框（margin）

### 3.1.1 盒子边框 ###

### 3.1.3 盒子外边框 ###
中和外边距和内边距
推荐大家把下面这条规则作为样式表的第一条规则：

	* {margin:0; padding:0;}

### 3.1.4 叠加外边距 ###

	/*为简明起见，省略了字体声明*/
	p { height:50px; border:1px solid #000; backgroundcolor:#fff;  margin-top:50px;
	margin-bottom:30px;}

### 3.1.5 外边距的单位 ###

## 3.2 盒子有多大 ##

1. 没有宽度的盒子
如果不设置块级元素的 width 属性，那么这个属性的默认值是 auto ，结果会让元素的宽度扩展到与父元素同宽。

2. 有宽度的盒子

# chapter4 字体和文本 #

## 4.1 字体 ##
字体相关的6个属性

* font-family
* font-size
* font-style
* font-weight
* font-variant
* font(简写属性)

### 4.1.1 字体族 ###

### 4.1.2 字体大小 ###
font-size是可以继承的，

1.绝对字体大小
像素、派卡（pica）或英寸

2.相对字体大小
使用百分比、em或rem（根元素的字体大小）

3.关于rem单位
rem（root em， 根em）

### 4.1.3 字体样式 ###
italic、oblique、normal

### 4.1.4 字体粗细 ###
100、200....900，或者lighter、normal、bold和bolder。

### 4.1.5 字体变化 ###
small-caps、normal

### 4.1.6 简写字体属性 ###
font属性是一个简写形式。

规则一：必须声明font-size和font-family的值

规则二：所有值必须按如下顺序声明。
1. font-weight、font-style、font-variant不分先后；
2. 然后是font-size；
3. 最后是font-family。

## 4.2 文本属性 ##
* text-indent
* letter-spacing
* word-spacing
* text-decoration
* text-align
* line-height
* text-transform
* vertical-align

### 4.2.1 文本缩进 ###
text-indent属性设定行内盒子相对于包含元素的起点。磨人情况下，这个起点就是包含元素的左上角。给text-indent设定正值，文本向右移，得到的是段落首行缩进效果。

### 4.2.2 字符间距 ###

	p {letter-spacing:.2em;}

### 4.2.3 单词间距 ###

	p {word-spacing:.2em;}
单词间距与字符间距

### 4.2.4 文本装饰 ###
underline、overline、line-through、blink、none。

### 4.2.5 文本对齐 ###
left、right、center、justify（双端对齐）

### 4.2.6 行高 ###

	p {line-height: 1.5;}

### 4.2.7 文本转换 ###
none、uppercase、lowercase、capitalize。

capitalize值会将每个词的首字母转换为大写。

### 4.2.8 垂直对其 ###
任意长度值以及sub、super、top、middle、bottom等

## 4.3 Web字体大揭秘 ##


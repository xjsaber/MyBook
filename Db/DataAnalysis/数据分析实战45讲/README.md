# 数据分析实战45讲 #

开篇词

## 开篇词 | 你为什么需要数据分析能力？ ##

## 01 | 数据分析全景图及修炼指南 ##

## 02 | 学习数据挖掘的最佳路径是什么？ ##

## 03 | Python基础语法：开始你的Python之旅 ##

### 安装及IDE环境 ###

### Python基础语法 ###

#### 输入与输出 ####

	name = input("What's your name?")
	sum = 100 + 100
	print('hello,%s' % name)
	print('sum = %d' % sum)

#### 判断语句：if … else … ####

	if score >= 90:
	    print('Excellent')
	else: 
	    if score < 60:
	        print('Fail')
	    else:
	        print('Good Job')

#### 循环语句：for … in ####

	sum = 0
	for number in range(11):
	    sum = sum + number
	print sum

#### 循环语句: while ####

	sum = 0
	number = 1
	while number < 11:
	       sum = sum + number
	       number = number + 1
	print sum

#### 数据类型：列表、元组、字典、集合 ####

**列表：[list]**

	lists = ['a','b','c']
	lists.append('d')
	print lists
	print len(lists)
	lists.insert(0,'mm')
	lists.pop()
	print lists

**元组：(tuple)**

	tuples = ('tupleA','tupleB')
	print tuples[0]

元祖tuple和list非常相似，但tuple一旦初始化就不能修改，可以像访问数组一样访问。

**字典：{dictionary}**

**集合：set**

	s = set(['a', 'b', 'c'])
	s.add('d')
	s.remove('b')
	print s
	print 'c' in s

**注释：#**

**引用模块/包：import**

	# 导入一个模块import model_name
	# 导入多个模块import module_name1,module_name2
	# 导入包中指定模块 from package_name import moudule_name
	# 导入包中所有模块 from package_name import 

**函数：def**

	def addone(score):
		return score + 1
	print(addone(99))

### 总结 ###

## 04 | Python科学计算：用NumPy快速处理数据 ##


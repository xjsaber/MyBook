# TensorFlow 实战Google深度学习框架 #

## 第1章 深度学习简介 ##

### 1.1 人工智能、机器学习与深度学习 ###

### 1.2 深度学习的发展历程 ###

### 1.3 深度学习的应用 ###

#### 1.3.1 计算机视觉 ####

#### 1.3.2 语音识别 ####

#### 1.3.3 自然语言处理 ####

#### 1.3.4 人机博弈 ####

### 1.4 深度学习工具介绍和对比 ###

## 第2章 TensorFlow环境搭建 ##

### 2.1 TensorFlow的主要依赖包 ###

#### 2.1.1 Protocol Buffer ####

#### 2.1.2 Bazel ####

自动化构建工具。

### 2.2 TensorFlow安装 ###

#### 2.2.1 使用Docker安装 ####

#### 2.2.2 使用pip安装 ####

#### 2.2.3 从源代码表编译安装 ####

### 2.3 TensorFlow测试样例 ###

### 小结 ###

Protocol Buffer和Bazel

Protocol Buffer是一个结构数据序列化的工具，在TensorFlow中大部分数据结构都是通过Protocol Buffer的形式存储的。Bazel是一个谷歌开源的编译工具。

## 第3章TensorFlow入门 ##

### 3.1 TensorFlow计算模型——计算图 ###

#### 3.1.1 计算图的概念 ####

Tensor张量，多维数组。

Flow体现了计算模型，张量之间通过计算相互转化的过程。

#### 3.1.2 计算图的使用 ####

	import tensorflow as tf

	a = tf.constant([1.0, 2.0], name="a")
	b = tf.constant([2.0, 3.0], name="b")
	
	result = a + b

采用“import tensorflow as tf”的形式来载入TensorFlow，使用“tf”来代替“tensorflow”作为模块名称。

### 3.2 TensorFlow数据模型——张量 ###

张量是TensorFlow管理的数据的形式。

#### 3.2.1 张量的概念 ####

#### 3.2.2 张量的使用 ####

### 3.3 TensorFlow运行模型——会话 ###

TensorFlow中使用会话的模式一般有两种，第一种模式需要明确调用会话生成函数和关闭会话函数。

	# 创建一个会话
	session = tf.Session()
	# 使用创建好的会话来得到关心的运算的结果。
	session.run(...)
	# 关闭会话使得本次运行中使用到的资源可以被释放
	session.close()

	
	# 创建一个会话，并通过Python中的上下文管理器来管理这个会话。
	with tf.Session() as session:
		# 使用创建好的会话来得到关心的运算的结果。
		session.run(...)
	# 当上下文退出时会话关闭和资源释放来自动完成了。

通过ConfigProto可以配置类似并行的线程数、GPU分配策略、运算超时时间等参数。

allow_soft_placement布尔型的参数，当它为True的时候，在以下任意一个条件成立的时候，GPU上运算可以放到CPU上进行：

1.运算无法在GPU上执行。
2.没有GPU资源。
3.运算输入包含对CPU计算结果的引用。


log_device_placement日志


### 3.4 TensorFlow实现神经网络 ###

#### 3.4.1 TensorFlow游乐场及神经网络简介 ####

#### 3.4.2 前向传播算法简介 ####





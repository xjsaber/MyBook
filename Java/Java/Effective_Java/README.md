# Effective Java（第3版） #

## 第1章 引言 ##

## 第2章 创建和销毁对象 ##

何时以及如何创建对象，何时以及如何避免创建

第1条

第3章 对于所有对象都通用的方法

# 第4章 类和接口 #

## 第18条：复合优先于继承 ##



第5章 泛型

第6章 枚举和注解

第7章 方法

# 第9章 通用程序设计 #

## 第58条：for-each循环优先于传统的for循环 ##

for-each循环，通过完全隐藏迭代器或者索引变量。

有三种情况无法使用for-each循环：

* 结构过滤——如果需要遍历集合，并删除选定的元素，就需要使用显式的迭代器，以便可以调用它的remove方法。（removeIf）
* 转换——如果需要遍历列表或者数组
* 平行迭代

# 第10章 异常 #

## 第69条：只针对异常的情形才使用异常 ##

* 因为异常机制的设计初衷是用于不正常的情形，所以几乎没有JVM实现试图对他们进行优化，使他们与显式的测试一样快速
* 把代码放在try-catch块中反而阻止了现代JVM实现本来可能要执行的某些特定优化。
* 对数组进行便利的标准模式并不会导致冗余的检查。有些现代的JVM实现会将他们优化掉。

异常应该只用于异常的模式下：他们永远不应该用于正常的控制流。

	for(Iterator<Foo> i = collection.iterator(); i.hasNext();) {
		Foo foo = i.next();
		...
	}
	try {
		Iterator<Foo> i = collection.iterator();
		while(true) {
			Foo foo = i.next();
			...
		}
	} catch (NoSuchElementException e) {

	}

## 第70条：对可恢复的情况使用受检异常，对编程错误使用运行时异常 ##

Java程序设计语言提供了三种可抛出结构（throwable）：受检异常（checked exception）、运行时异常（run-time exception）和错误（error）。

第10章 并发

第11章 序列化
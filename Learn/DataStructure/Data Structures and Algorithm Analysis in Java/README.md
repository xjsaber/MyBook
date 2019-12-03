# 数据结构与算法分析 Java语言 #

第一部分 基础知识

# 第1章 导论 #

## 1.1 本书讨论内容 ##

## 1.2 数学知识复习 ##

## 1.3 递归简论 ##

## 1.4 实现泛型构件pre-Java5 ##

## 1.5 利用Java5泛型特性实现泛型构件 ##

# 第4章 树 #

二叉查找树

* 看到树时如合用于实现几个流行的操作系统中的问及那系统的。
* 看到树如合能够用来计算算术表达式的值。
* 指出如合利用树支持以O（log N）平均时间进行的各种搜索操作，以及如合细化以得到最坏情况时间界O（log N）。我们还将讨论当数据被存放在磁盘上时如合来实现这些操作。
* 讨论并使用TreeSet类和TreeMap类

## 4.1 预备知识 ##

树（tree）有几种方式定义。定义树的一种自然的方式是递归的方式。一棵树是一些节点的集合。这个集合可以是空集；若不是空集，则树由称作根（root）的节点r以及0个或多个非空的（子）树

### 4.1.1 树的实现 ###

	class TreeNode
	{
		Object element;
		TreeNode firstChild;
		TreeNode nextSibling;
	}

firstChild（第一个儿子）的链
nextSibling（下一兄弟）的链

### 4.1.2 树的遍历及应用 ###

递归方法listAll。

	private void listAll(int depth){
		printName(depth);
		if (isDirecotry())
			for each file c in this directory(for each child)
				c.listAll(depth + 1);
	}
	public void listAll() {
		listAll(0);
	}


先序遍历、后序遍历。

## 4.2 二叉树 ##

二叉树（binary tree）是一棵树，其中每个节点都不能有多于两个的儿子。




## 4.7 B树 ##
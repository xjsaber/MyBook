# 第17章 容器深入研究 #

## 17.1 完整的容器分类法 ##

![img/]()

* Queue接口 及其实现PriorityQueue和各种风格的BlockingQueue
* ConcurrentMap接口及其实现ConcurrentHashMap，用于多线程机制的
* CopyOnWriteArrayList和CopyOnWriteArraySet，用于多线程机制
* 在Collections类中的多个便利方法。

## 17.2 填充容器 ##

Collections类也有一些使用的static方法，其中包括fill()。此fill()方法也是只复制同一个对象引用来填充整个容器的，并且只针对List对象。

用对单个对象的引用来填充Collection的方式，第一种是使用Collections.nCopies()创建传递给构造器的List，填充的是ArrayList。

### 17.2.1 一种Generator解决方案 ###

所有的Collection子类型都有一个接收另一个Collection对象的构造器，用所接收Collection对象中的元素来填充新的容器。

使用Generator在容器中放置所需数量的对象，然后产生的容器可以传递给任何Collection的构造器，这个构造器会把其中的数据复制到自身上。addAll()方法是所有Collection子类型的一部分，用来组装现有的Collection。

泛型便利方法可以减少在使用类时所必需的类型检查数量。

### 17.2.2 Map生成器 ###

### 17.2.3 使用Abstract类 ###

## 17.5 List的功能方法 ##

add()、get()和iterator()

## 17.13 Java 1.0/1.1 的容器 ##

17.1

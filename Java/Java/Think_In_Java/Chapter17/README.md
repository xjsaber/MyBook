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

## 17.3 Collection的功能方法 ##

|--|--|
|boolean add(T)|确保|
|boolean addAll(Collection<? extends T>)|
|void clear()||
|boolean contains(T)||

## 17.5 List的功能方法 ##

add()、get()和iterator()

## 17.7 队列 ##

除了并发应用，Queue在Java SE5中仅有的两个实现是LinkdedList和PriorityQueue，它们的差异在排序行为而不是性能。

除了优先级队列，Queue将精确地按照元素被置于Queue中的顺序产生它们。

### 17.7.1 优先级队列 ###

to-do列表，该列表中每个对象都包含一个字符串和一个主要的以及次要的优先级值。该列表的排序顺序也是通过实现Comparable而进行控制。

//TODO 练习11

### 17.7.2 双向队列 ###

双向队列（双端队列）就像是一个队列，但是可以在任何一端添加或移除元素。在LinkedList中包含支持双向队列的方法，但是在Java标准类库中没有任何显式的用于双向队列的接口。

不太可能在两端

## 17.8 理解Map ##

映射表（也称为关联数组）的基本意思是它维护的是键-值（对）关联。

标准的Java类库中包含了Map的几种基本实现，包括：HashMap，TreeMap，LinkedHashMap，WeakHashMap,ConcurrentHashMap,IdentityHashMap。

但是行为特性各不相同，表现在效率、键值对的保存及呈现次序、对象的保存周期、映射表如何在多线程程序中工作和判定“键”等价的策略等方面。

关联数组的基本方法是put()和get()

//TODO 练习12

//TODO 练习13

### 17.8.1 性能 ###

当在get()中使用线性搜索时，执行速度会相当地慢，而执行速度会相当的慢，而这正是HashMap提高速度的地方。HashMap使用了特殊的值，称作*散列码*，来取代对键的缓慢搜索。

散列码是“相对唯一”的、用以代表对象的int值，它是通过将该对象的某些信息进行转换而生成的。hashCode()是根类Object中的方法，因此所有Java对象都能产生散列码。

HashMap就是使用对象的hashCode()进行快速查询，能够显著提高性能。

想进一步提高查询速度，并且令新的Map只针对特定类型，避免与Object之间的类型转换操作使用数组代替溢出桶，这有两个好处：第一，可以针对磁盘存储方式做优化；第二，在创建和回收单独的记录时，能节约很多时间。

|--|--|
|HashMap|Map基于散列表的实现|
|LinkedHashMap|类似于HashMap，但是迭代遍历它时，取得“键值对”的顺序是其插入次序，或者是最近最少使用（LRU）的次序。只比HashMap慢一点；而在迭代访问时反而更快，因为它使用链表维护内部次序|
|TreeMap|基于红黑树的实现。查看“键”或“键值对”时，它们会被排序（次序由Comparable或Comparator决定）。TreeMap的特点在于，所得到的结果是经过排序的。TreeMap是唯一的带有subMap()方法的Map，它可以返回一个子树|
|WeakHashMap|弱健（weak key）映射，允许释放映射所指向的对象；这是为解决某类特殊问题而设计的。如果映射之外没有引用指向某个“键”，则此“键”可以被垃圾收集器回收|
|ConcurrentHashMap|一种线程安全的Map，它不涉及同步加锁。|
|IdentityHashMap|使用==代替equals()对“键”进行比较的散列映射。|

任何键都必须具有一个equals()方法；如果键被用于散列Map，那么它必须还具有恰当的hashCode()方法；如果键被用于TreeMap，那么它还必须实现Comparable。

### 17.8.2 SortedMap ###

### 17.8.3 LinkedHashMap ###

LinkedHashMap散列化所有的元素，但是在遍历键值对时

## 17.9 散列与散列码 ##

### 17.9.2 为速度而散列 ###

散列的价值在于速度：散列使得查询得以快速进行。由于瓶颈位于键的查询速度，因此解决方案之一就是保持键的排序状态，然后使用Collections.binarySearch()进行查询。

查询一个值的过程首先就是计算散列码，然后使用散列码查询数组。如果能够保证没有冲突（如果值的数量是固定的，那么就有可能），那可就有了一个*完美的散列函数*，但是这种情况只是特例。通常，冲突由*外部链接*处理：数组并不直接保存值，而是保存值的list。然后对list中的值使用equals()方法进行线性的查询。这部分的查询自然会比较慢，但是，如果散列函数好的话，数组的每个位置就只有较少的值。因此，不是查询整个list，而是快速地跳转到数组的某个位置，只对很少的元素进行比较。这便是HashMap会如此快的原因。

## 17.13 Java 1.0/1.1的容器 ##

### 17.13.1 Vector和Enumeration ###

### 17.13.2 Hashtable ###

### 17.13.3 Stack ###

### 17.13.4 BitSet ###

## 17.14 总结 ##

必须对散列操作有足够的了解，从而能够编写自己的hashCode()方法
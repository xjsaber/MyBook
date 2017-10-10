# 第13章 集合 #

## 13.1 集合接口 ##
Vector、Stack、Hashtable、BitSet与Enumeration接口

### 13.1.1 将集合的接口与实现分离 ###
inerface implementation

队列通常有两种实现方式：一种是使用循环数组；另一种是使用链表

里氏替换原则
	
### 13.1.2 将集合的接口与实现分离 ###

#### 1.迭代器 ####

Iterator通过凡湖调用next方法，可以逐个访问集合中的每个元素。但是，如果到达了集合的末尾，next方法将抛出一个NoSuchElementException。因此，需要在调用next之前调用hasNext方法。

如果想要查看所有元素，需要和hasNext()结合起来，Java SE5.0起，这个循环可以采用一种更优雅的缩写方式。用“for each”循环
	
	for (String element : c){
		do something with element
	}

## 13.2 具体的集合 ##

除了以Map结尾的类之外，其他类都实现了Collection接口。而以Map结尾的类实现了Map接口。

|集合类型|描述|
|--|--|
|ArrayList|一种可以动态增长和缩减的索引序列|
|LinkedList|一种可以在任何位置进行高效地插入和删除操作的有序序列|
|ArrayDeque|一种用循环数组实现的双端队列|
|HashSet|一种没有重复元素的无序集合|
|TreeSet|一种有序集|
|EnumSet|一种可以动态增长和缩减的索引序列|
|LinkedHashSet|一种可以动态增长和缩减的索引序列|
|PriorityQueue|一种可以动态增长和缩减的索引序列|
|HashMap|一种可以动态增长和缩减的索引序列|
|TreeMap|一种可以动态增长和缩减的索引序列|
|EnumMap|一种可以动态增长和缩减的索引序列|
|LinkedHashMap|一种可以动态增长和缩减的索引序列|
|WeakHashMap|一种可以动态增长和缩减的索引序列|
|IdentityHashMap|一种可以动态增长和缩减的索引序列|

### 13.2.1 链表 ###

链表（linked list）。尽管数组在连续的存储位置上存放对象引用，但链表却将每个对象存放在独立的结点中。每个结点还存放着序列中下一个结点的引用。

在Java程序设计语言中，所有链表实际都是双向链接的（doubly linked）——即每个结点还存放着指向前驱结点的引用。

链表是一个有序集合（ordered collection），每个对象的位置十分重要。LinkedList.add方法将对象添加到链表的尾部。

**java.util.List<E> 1.2**

* ListIterator<E> listIterator() 返回一个列表迭代器，以便用来访问列表中的元素
* ListIterator<E> listIterator(int index) 返回一个列表迭代器，以便用来访问列表中的元素，这个元素是第一次调用next返回的给定索引的元素。
* void add(int i, E element) 在给定位置添加一个元素
* void addAll(int i, Collection<? extends E> elements) 将某个集合中的所有元素添加到给定位置
* E remove(int i) 删除给定位置的元素并返回这个元素。
* E get(int i) 获取给定位置的元素
* E set(int i, E element) 用新元素取代给定位置的元素，并返回原来那个元素。
* int indexOf(Object element) 返回与指定元素相等的元素在列表中第一次出现的位置，如果没有这样的元素将返回-1。
* int lastIndexOf(Object element) 返回与指定元素相等的元素在列表中最后一次出现的位置，如果没有这样的元素将返回-1.

**java.util.ListIterator<E> 1.2**

**java.util.LinkedList<E> 1.2**

### 13.2.2 数组列表 ###

List接口用于描述一个有序集合，并且集合中每个元素的位置十分重要。有两种访问元素的协议：一种是用迭代器，另一种是用get和set方法随机地访问每个元素。

ArrayList类，实现了List接口，封装了一个动态再分配的对象数组。

### 13.2.3 散列集 ###

链表和数组可以按照人们的意愿排列元素的次序。

散列表为每个对象计算一个整数，称为散列码（hash code）。散列码是由于对象的实例域产生一个整数。更准确地说，具有不同数据域的对象将产生不同的散列码。

在Java中，散列表用链表数组实现。每个列表被称为桶（bucket）。要想查找表中对象的位置。就要先计算它的散列码，然后与桶的总数取余，所得到的结果就是保存这个元素的桶的索引。

散列表可以用于实现几个重要的数据结构。其中最简单的是set类型。set是没有重复元素的元素集合。set的add方法首先在集中查找要添加的对象。如果不存在，就将这个对象添加进去。

**java.util.HashSet<E> 1.2**

* HashSet() 构造一个空散列表。
* HashSet(Collection<? extends E> elements) 构造一个散列表，并将集合中的所有元素添加到这个散列集中。
* HashSet(int initialCapacity) 构造一个空的具有指定容量（桶数）的散列集
* HashSet(int initialCapacity, float loadFactor) 构造一个具有指定容量和装填因子（一个0.0~1.0之间的数值，确定散列表填充的百分比，当大于这个百分比时，散列表进行再散列）的空散列集。

**java.lang.Object 1.0**

* int hashCode() 返回这个对象的散列码。散列码可以是任何整数，包括正数或负数。equals和hashCode的定义必须兼容，即如果x.equals(y)为true，x.hashCode()必须等于y.hashCode()。

### 13.2.4 树集 ###

树集是一个有序集合（sorted collection）。可以以任意顺序将元素插入到集合中。在对集合进行遍历时，每个值将自动地按照排序后的顺序呈现。

TreeSet类名，排序是用树结构完成的（当前实现使用的是红黑树（red-black tree））每次将一个元素添加到树中时，都被放置在正确的排序位置上。因此迭代器总是以排好序的顺序访问每个元素。

将一个元素添加到树中要比添加啊到散列表中慢，但是，与将元素添加到数组或链表的正确位置上相比还是快很多的。如果树中包含n个元素，查找新元素的正确位置平均需要log<sup>2</sup>n次比较。

因此，将一个元素添加到TreeSet中要比添加到HashSet中慢。

**java.util.TreeSet<E>1.2**

* TreeSet() 构造一个空树集
* TreeSet(Collection<? extends E> elements) 构造一个树集，并将集合中的所有元素添加到树集中

### 13.2.5 对象的比较 ###

	public interface Comparable<T> {
		int compareTo(T other);
	}

如果a与b相等，调用a.compareTo(b)一定返回0；如果排序后a位于b之前，则返回负值；如果a位于b之后，则返回正值。

> 只有整数在一个足够小的范围内，才可以使用这个技巧。如果x是一个较大的正整数，y是一个较大的负整数，x-y有可能会溢出。

使用Compareable接口定义排序有其局限性。对于一个给定的类，只能够实现这个接口一次。可以通过将Comparator对象传递给TreeSet构造器来告诉树集使用不同的比较方法。

### 13.2.7 优先级队列 ###

优先级队列（priority queue）中的元素可以按照任意的顺序插入，却总是按照排序的顺序进行检索。也就是说，无论何时调用remove方法，总会获得当前优先级队列中最小的元素。

优先级队列使用一个优雅且高效的数据结构，称为堆(heap).堆是一个可以自我调整的二叉树，对树执行添加（add）和删除（remore）操作，可以让最小的元素移动到根，而不必花费时间对元素进行排序。

**java.util.PriorityQueue 5.0**

* PriorityQueue()
* PriorityQueue(int initialCapacity) 构造一个用于存放Comparable对象的优先级队列
* PriorityQueue(int initalCapacity, Comparator<? super E> c) 构造一个优先级队列，并用指定的比较器对元素进行排序。

### 13.2.8 映射表 ###

集是一个集合，也可以快速地查找现有的元素。

### 13.2.9 专用集与映射表类 ###

## 13.3 集合框架 ##
线程可以有如下6种状态：
* New(新创建)
* Runnable(可运行)
* Blocked(被阻塞)
* Waiting(等待)
* Timed waiting(计时等待)
* Terminated(被终止)

要确定一个线程的当前状态，可调用getState方法。

### 14.3.1新创建线程 ###
当用new操作符创建一个新线程时，如new Thread(r),该线程还没有开始运行。这意味着它的状态是new。
### 14.3.2可运行线程 ###
一旦调用start方法，线程处于runnable状态。一个可运行的线程可能正在运行也可能没有运行，着取决于操作系统给线程提供运行的时间。
### 14.3.3 被阻塞线程和等待线程 ###
当线程处于被阻塞火等待状态时，它暂时不活动。它不运行任何代码且消耗最小的资源。


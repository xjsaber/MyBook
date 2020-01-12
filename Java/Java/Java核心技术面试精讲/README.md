# Java核心技术面试精讲 #

# 开篇词 #

## 开篇词|以面试题为切入点，有效提升你的Java内容 ##

更要求对底层源代码层面的掌握，并对分布式、安全、性能等领域能力有进一步的要求。

# 模块一 Java基础 #

## 第1讲 | 谈谈你对Java平台的理解？ ##

### 典型回答 ###

1. 书写一次，到处运行
2. 垃圾收集

我们开发的Java的源代码

1. 通过Javac编译成为字节码（bytecode）
2. 在运行时，通过Java虚拟机（JVM）内嵌的解释器将字节码转换成为最终的机器码

但是常见的JVM（Oracle JDK提供的Hotspot JVM，都提供了JIT(Just-In-Time)编译器，也就是通常所说的动态编译器）

### 考点分析 ###

### 知识扩展 ###

1. Java 语言特性，包括泛型、Lambda 等语言特性；基础类库，包括集合、IO/NIO、网络、并发、安全等基础类库。
2. JVM 的一些基础概念和机制，比如 Java 的类加载机制，常用版本 JDK（如 JDK 8）内嵌的 Class-Loader，例如Bootstrap、Application和Extension Class-loader；
3. 类加载大致过程：加载、验证、链接、初始化；自定义Class-Loader等。还有垃圾收集的基本原理，最常见的垃圾收集器，如 SerialGC、Parallel GC、 CMS、 G1 等，对于适用于什么样的工作负载最好也心里有数。
4. 当然还有 JDK 包含哪些工具或者 Java 领域内其他工具等，如编译器、运行时环境、安全工具、诊断和监控工具等。

Java分为编译器和运行时：

1. Javac的编译，编译Java源码生成“.class”文件里面实际是字节码，而不是可以直接执行的机器码。Java通过字节码和Java虚拟机（JVM）这种跨平台的抽象，屏蔽了操作系统和硬件的细节（一次编译，到处执行的基础）
2. 在运行时，JVM会通过类加载器（Class-Loader）加载字节码，解释或者编译执行。主流Java版本中，如JDK8实际是解释和编译混合的一种模式，即所谓的混合模式（-Xmixed）。

Java虚拟机启动时，可以指定不同的参数对运行模式进行选择

1. 指定“-Xint”，告诉JVM只进行解释执行，不对代码进行编译
2. 指定“-Xcomp”，告诉JVM关闭解释器，不要进行解释执行
3. AOT（Ahead-of-Time Compilation），直接将字节码编译成机器代码，避免了JIT预热等各方面的开销

#### 小结 ####

提纲挈领地构建一个整体的印象

1. Java语言特性
2. 核心类库与常用第三方类库
3. Java虚拟机基本原理和相关工具

### 一课一练 ###

谈谈你对Java平台的理解？

## 第2讲 | Exception和Errpr有什么区别？ ##

### 典型回答 ###

Exception和Error都是继承了Throwable类，在Java中只有Throwable类型的实例才可以被抛出（throw）或者捕获（catch），它是异常处理机制的基本组成类型。

* Error 是指在正常情况下，不大可能出现的情况，绝大部分的 Error 都会导致程序（比如 JVM 自身）处于非正常的、不可恢复状态。既然是非正常情况，所以不便于也不需要捕获，常见的比如 OutOfMemoryError 之类，都是 Error 的子类。
* Exception 又分为可检查（checked）异常和不检查（unchecked）异常
	* 可检查异常在源代码里必须显式地进行捕获处理，这是编译期检查的一部分。前面我介绍的不可查的 Error，是 Throwable 不是 Exception。
	* 不检查异常就是所谓的运行时异常，类似 NullPointerException、ArrayIndexOutOfBoundsException 之类，通常是可以编码避免的逻辑错误，具体根据需要来判断是否需要捕获，并不会在编译期强制要求。

### 考点分析 ### 

1. 理解Throwable、Exception、Error的设计和分类
	* NoClassDefFoundError和ClassNotFoundException有什么区别。
		* NoClassDefFoundError，class未定义
		* ClassNotFoundException，ClassLoader中没找到这个class或者method
2. 理解Java语言中操作Throwable的元素和实践

## 第8讲 | 对比Vector、ArrayList、LinkedList有何区别？ ##

对比 Vector、ArrayList、LinkedList 有何区别？

我：
1. Vector：是Java早期的数组类型，线程安全，但性能损失大，用的是全局锁，sync
2. ArrayList：字面意思，根据数组来的List，读取O(1)，增加O(n)，如果需要扩容则损失很大
3. LinkedList：根据链表来的List，读取O(n)，增加O

### 典型回答 ###

实现集合框架中的List（有序集合）

1. Vector是Java早期提供的线程安全的动态数组，
2. ArrayList 动态数组，非线程安全，也是可以根据需要调整容量，不过两者的调整逻辑有所区别，Vector 在扩容时会提高 1 倍，而 ArrayList 则是增加 50%。
3. LinkedList 双向链表，不需要像上面两种那样调整容量，也不是线程安全的。

### 考点分析 ###

* Vector和ArrayList作为动态数组，其内部元素以数组形式顺序存储的，适合随机访问的场合。除了尾部插入和删除元素，往往性能会相对较差，比如我们在中间位置插入一个元素，需要移动后续所有元素。
* 而 LinkedList 进行节点插入、删除却要高效得多，但是随机访问性能则要比动态数组慢。

考察 Java 集合框架，很多方面需要掌握：

* Java 集合框架的设计结构，至少要有一个整体印象。
* Java 提供的主要容器（集合和 Map）类型，了解或掌握对应的数据结构、算法，思考具体技术选择。
* 将问题扩展到性能、并发等领域。
* 集合框架的演进与发展。

数据结构和算法是基本功

典型排序算法为例

* 内部排序，至少掌握基础算法如归并排序、交换排序（冒泡、快排）、选择排序、插入排序等。
* 外部排序，掌握利用内存和外部存储处理超大数据集，至少要理解过程和思路。

考察算法不仅仅是如何简单实现，面试官往往会刨根问底，

* 比如哪些是排序是不稳定的呢（快排、堆排），或者思考稳定意味着什么；
* 对不同数据集，各种排序的最好或最差情况；
* 从某个角度如何进一步优化（比如空间占用，假设业务场景需要最小辅助空间，这个角度堆排序就比归并优异）等，从简单的了解

学习相关书籍

* 算法导论
* 编程珠玑
* [教程](https://www.coursera.org/learn/algorithms-part1)
* Leetcode见仁见智

### 知识扩展 ###

虽然通常概念上我们也会把 Map 作为集合框架的一部分，但是它本身并不是真正的集合（Collection）。

![675536edf1563b11ab7ead0def1215c7.png](img/675536edf1563b11ab7ead0def1215c7.png)

我们可以看到 Java 的集合框架，Collection 接口是所有集合的根，然后扩展开提供了三大类集合，分别是：

* List，也就是我们前面介绍最多的有序集合，它提供了方便的访问、插入、删除等操作。
* Set，Set 是不允许重复元素的，这是和 List 最明显的区别，也就是不存在两个对象 equals 返回 true。我们在日常开发中有很多需要保证元素唯一性的场合。
* Queue/Deque，则是 Java 提供的标准队列结构的实现，除了集合的基本功能，它还支持类似先入先出（FIFO， First-in-First-Out）或者后入先出（LIFO，Last-In-First-Out）等特定行为。这里不包括 BlockingQueue，因为通常是并发编程场合，所以被放置在并发包里。

每种集合的通用逻辑，都被抽象到相应的抽象类之中，比如 AbstractList 就集中了各种 List 操作的通用部分。这些集合不是完全孤立的，比如，LinkedList 本身，既是 List，也是 Deque 哦。

需要对各种具体集合实现，至少了解基本特征和典型使用场景，以 Set 的几个实现为例：

* TreeSet 支持自然顺序访问，但是添加、删除、包含等操作要相对低效（log(n) 时间）。
* HashSet 则是利用哈希算法，理想情况下，如果哈希散列正常，可以提供常数时间的添加、删除、包含等操作，但是它不保证有序。
* LinkedHashSet，内部构建了一个记录插入顺序的双向链表，因此提供了按照插入顺序遍历的能力，与此同时，也保证了常数时间的添加、删除、包含等操作，这些操作性能略低于 HashSet，因为需要维护链表的开销。
* 在遍历元素时，HashSet 性能受自身容量影响，所以初始化时，除非有必要，不然不要将其背后的 HashMap 容量设置过大。而对于 LinkedHashSet，由于其内部链表提供的方便，遍历性能只和元素多少有关系。

在Collections工具类中，提供了一系列的synchronized方法，比如

	static <T> List<T> synchronizedList(List<T> list)

可以利用类似方法来实现基本的线程安全集合

	List list = Collections.synchronizedList(new ArrayList());

它的实现，基本就是将每个基本方法，比如 get、set、add 之类，都通过 synchronizd 添加基本的同步支持，非常简单粗暴，但也非常实用。注意这些方法创建的线程安全集合，都符合迭代时 fail-fast 行为，当发生意外的并发修改时，尽早抛出 ConcurrentModificationException 异常，以避免不可预计的行为。

另外一个经常会被考察到的问题，就是理解 Java 提供的默认排序算法，具体是什么排序方式以及设计思路等。

1. 因为需要区分是 Arrays.sort() 还是 Collections.sort() （底层是调用 Arrays.sort()）；什么数据类型；多大的数据集（太小的数据集，复杂排序是没必要的，Java 会直接进行二分插入排序）等。

对于原始数据类型，目前使用的是所谓双轴快速排序（Dual-Pivot QuickSort），是一种改进的快速排序算法，早期版本是相对传统的快速排序，你可以阅读源码。

而对于对象数据类型，目前则是使用TimSort，思想上也是一种归并和二分插入排序（binarySort）结合的优化排序算法。TimSort 并不是 Java 的独创，简单说它的思路是查找数据集中已经排好序的分区（这里叫 run），然后合并这些分区来达到排序的目的。

Java 8 引入了并行排序算法（直接使用 parallelSort 方法），这是为了充分利用现代多核处理器的计算能力，底层实现基于 fork-join 框架（专栏后面会对 fork-join 进行相对详细的介绍），当处理的数据集比较小的时候，差距不明显，甚至还表现差一点；但是，当数据集增长到数万或百万以上时，提高就非常大了，具体还是取决于处理器和系统环境。

排序算法仍然在不断改进，最近双轴快速排序实现的作者提交了一个更进一步的改进，历时多年的研究，目前正在审核和验证阶段。根据作者的性能测试对比，相比于基于归并排序的实现，新改进可以提高随机数据排序速度提高 10%～20%，甚至在其他特征的数据集上也有几倍的提高

我们知道 Java 已经支持所谓的可变参数（varargs），但是官方类库还是提供了一系列特定参数长度的方法，看起来似乎非常不优雅，为什么呢？这其实是为了最优的性能，JVM 在处理变长参数的时候会有明显的额外开销，如果你需要实现性能敏感的 API，也可以进行参考。

1. 从Verctor、ArrayList、LinkedList开始，逐步分析其设计实现区别、适合的应用场景等
2. 进一步对集合框架进行了简单的归纳
3. 介绍了集合框架从基础算法到 API 设计实现的各种改进

### 一课一练 ###

需要实现一个云计算任务调度系统，希望可以保证 VIP 客户的任务被优先处理，你可以利用哪些数据结构或者标准的集合类型呢？更进一步讲，类似场景大多是基于什么数据结构呢？

## 第9讲 | 对比Hashtable、HashMap、TreeMap有什么不同？ ##

Hashtable、HashMap、TreeMap 都是最常见的一些 Map 实现，是以**键值对**的形式存储和操作数据的容器类型。

* Hashtable，早期 Java 类库提供的一个哈希表实现，同步，不支持null键和值，性能开销，很少被推荐使用
* HashMap，不同步，支持null键和值等。通常情况下，HashMap 进行 put 或者 get 操作，可以达到常数时间的性能，所以它是绝大部分利用键值对存取场景的首选。 
* TreeMap，则是基于红黑树的一种提供顺序访问的 Map，它的 get、put、remove 之类操作都是 O（log(n)）的时间复杂度，具体顺序可以由指定的 Comparator 来决定，或者根据键的自然顺序来判断。

## 考点分析 ##

尤其是在 Java 8 里，HashMap 本身发生了非常大的变化。

* 理解 Map 相关类似整体结构，尤其是有序数据结构的一些要点。
* 从源码去分析 HashMap 的设计和实现要点，理解容量、负载因子等，为什么需要这些参数，如何影响 Map 的性能，实践中如何取舍等。
* 理解树化改造的相关原理和改进原因。
* 并发相关问题，如HashMap在并发环境可能出现无限循环占用 CPU、size 不准确等诡异的问题。具体可以参考[这里](https://mailinator.blogspot.com/2009/06/beautiful-race-condition.html)。

### 知识扩展 ###

**1. Map整体结构**

1. Map 虽然通常被包括在 Java 集合框架里，但是其本身并不是狭义上的集合类型（Collection）。

![266cfaab2573c9777b1157816784727c](img/266cfaab2573c9777b1157816784727c.png)

Hashtable 比较特别，作为类似 Vector、Stack 的早期集合相关类型，它是扩展了 Dictionary 类的，类结构上与 HashMap 之类明显不同。HashMap 等其他 Map 实现则是都扩展了 AbstractMap，里面包含了通用方法抽象。

HashMap 的性能表现非常依赖于哈希码的有效性，请务必掌握 hashCode 和 equals 的一些基本约定。

* equals 相等，hashCode 一定要相等。
* 重写了 hashCode 也要重写 equals。
* hashCode 需要保持一致性，状态改变返回的哈希值仍然要一致。
* equals 的对称、反射、传递等特性。

虽然 LinkedHashMap 和 TreeMap 都可以保证某种顺序，但二者还是非常不同的。

* LinkedHashMap 通常提供的是遍历顺序符合插入顺序，它的实现是通过为条目（键值对）维护一个双向链表。注意，通过特定构造函数，我们可以创建反映访问顺序的实例，所谓的 put、get、compute 等，都算作“访问”。
	* 适用于特定应用场景，例如，构建一个空间占用敏感的资源池，希望可以自动将最不常被访问的对象释放掉，可以利用LinkedHashMap提供的机制来实现。
* 对于TreeMap，它的整体顺序是由键的顺序关系决定，通过Comparator或Comparable（自然顺序）来决定。

**2. HashMap源码分析**

* HashMap 内部实现基本点分析。
* 容量（capacity）和负载系数（load factor）。
* 树划。

1. HashMap内部的结构，可以看作是数组（Node<K, V>[] table）和链表结合组成的复合结构，数组可以分为一个个桶（bucket），通过哈希值决定了键值对在这个数组的寻址
2. 哈希值相同的键值对，则以链表形式存储。如果链表大小超过阈值`（TREEIFY_THRESHOLD, 8）`。

不考虑极端情况（容量理论最大极限由MAXIMUM_CAPACITY指定，数值为1<<30，也就是2的30次方），可以归纳为：

* 门限值等于（负载因子）* （容量），如果构建HashMap的时候没有指定它们，那么就是依据相应的默认常量值。
* 门限通常是以倍数进行调整 （newThr = oldThr << 1），我前面提到，根据 putVal 中的逻辑，当元素个数超过门限大小时，则调整 Map 大小。
* 扩容后，需要将老的数组中的元素重新放置到新的数组，这是扩容的一个主要开销来源。

**3. 容量、负载因子和树化**

容量和负载系数决定了可用的桶的数量，空桶太多会浪费空间，如果使用的太满则会严重影响操作的性能。极端情况下，假设只有一个桶，那么它就退化成了链表，完全不能提供所谓常数时间存的性能。

如果能够制导HashMap曜存取的键值对数量，可以考虑预先设置合适的容量大小。具体数值可以根据扩容发生的条件来做简单预估。

	负载因子 * 容量 > 元素数量

预先设置的容量需要满足，大于“预估元素数量/负载因子”，同时它是2的幂数。

而对于负载因子，

* 如果没有特别需求，不要轻易进行更改，因为 JDK 自身的默认负载因子是非常符合通用场景的需求的。
* 如果确实需要调整，建议不要设置超过 0.75 的数值，因为会显著增加冲突，降低 HashMap 的性能。
* 如果使用太小的负载因子，按照上面的公式，预设容量值也进行调整，否则可能会导致更加频繁的扩容，增加无谓的开销，本身访问性能也会受影响。

提到了树化改造，对应逻辑主要在 putVal 和 treeifyBin 中，当bin数量大于TREEIFY_THRESHOLD时：

* 如果容量小于MIN_TREEIFY_CAPACITY，只会进行简单的扩容。
* 如果容量大于MIN_TREEIFY_CAPACITY，则会进行树化改造。

因为在元素放置过程中，如果一个对象哈希冲突，都被放置到同一个桶里，则会形成一个链表，链表查询是线性的，会严重影响存取的性能。恶意代码就可以利用这些数据大量与服务器端交互，导致服务器端 CPU 大量占用，这就构成了哈希碰撞拒绝服务攻击。

### 一课一练 ###

解决哈希冲突有哪些典型方法呢？

## 第10讲 | 如何保证集合是线程安全的？ConcurrentHashMap如何实现高效地线程安全 ##

如何保证容器是线程安全的？ConcurrentHashMap 如何实现高效地线程安全？

### 典型回答 ###

Java 提供了不同层面的线程安全支持。在传统集合框架内部，除了 Hashtable 等同步容器，还提供了所谓的同步包装器（Synchronized Wrapper），我们可以调用 Collections 工具类提供的包装方法，来获取一个同步的包装容器（如 Collections.synchronizedMap），但是它们都是利用非常粗粒度的同步方式，在高并发情况下，性能比较低下。

更加普遍的选择是利用并发包提供的线程安全容器类，它提供了：

* 各种并发容器，比如 ConcurrentHashMap、CopyOnWriteArrayList。
* 各种线程安全队列（Queue/Deque），如 ArrayBlockingQueue、SynchronousQueue。
* 各种有序容器的线程安全版本等。

具体保证线程安全的方式，包括有从简单的 synchronize 方式，到基于更加精细化的，比如基于分离锁实现的 ConcurrentHashMap 等并发实现等。具体选择要看开发的场景需求，总体来说，并发包内提供的容器通用场景，远优于早期的简单同步实现。

### 考点分析 ###

安全和并发

* 理解基本的线程安全工具。
* 理解传统集合框架并发编程中 Map 存在的问题，清楚简单同步方式的不足。
* 梳理并发包内，尤其是 ConcurrentHashMap 采取了哪些方法来提高并发表现。
* 最好能够掌握 ConcurrentHashMap 自身的演进，目前的很多分析资料还是基于其早期版本。

### 知识扩展 ###

#### 1. 为什么需要ConcurrentHashMap? ####

Hashtable本身比较低效，因为它的实现基本就是将put、get、size等各种方法加上“synchronized”。导致了所有并发操作都要竞争同一把锁，一个线程在进行同步操作时，其他线程智能等待，大大降低了并发操作的效率。

同步包装器只是利用输入Map构造了另一个同步版本，所有操作虽然不再声明成为synchronized方法，但是还是利用“this”作为互斥的mutext，没有真正意义上的改进。

Hashtable 或者同步包装版本，都只是适合在非高度并发的场景下。

#### 2.ConcurrentHashMap 分析 ####

ConcurrentHashMap 的设计实现其实一直在演化，比如在 Java 8 中就发生了非常大的变化（Java 7 其实也有不少更新），所以，我这里将比较分析结构、实现机制等方面，对比不同版本的主要区别。

早期ConcurrentHashMap，其实现是基于：

TODO

* 

### 一课一练 ###

在产品代码中，有没有典型的场景需要使用类似 ConcurrentHashMap 这样的并发容器呢？

## 第11讲 | Java提供了哪些IO方式？NIO如何实现多路复用 ##

Java 提供了哪些 IO 方式？ NIO 如何实现多路复用？

### 典型回答 ###

1. IO，传统的java.io包，基于流模型实现
	* 好处是代码比较简单、直观
	* 缺点是IO效率和扩展性存在局限，容易成为性能瓶颈
	* java.net下面提供的部分网络API，比如Socket、ServerSocket、HttpURLConnection也归类到同步阻塞IO类库（网络通信同样是IO行为） 
2. NIO，Java1.4（java.nio包），提供了Channel、Selector、Buffer等新的抽象，可以构建多路复用的、同步非阻塞IO程序，同时提供了更接近操作系统底层的高性能数据操作方式。
3. NIO2——Java7异步非阻塞，NIO2，引入了异步非阻塞IO方式。异步IO操作基于事件和回调机制（应用操作直接返回，而不会阻塞，当后台处理完成，操作系统会通知相应线程进行后续工作。）

### 考点分析 ###

* 基础 API 功能与设计， InputStream/OutputStream 和 Reader/Writer 的关系和区别。
* NIO、NIO  2 的基本组成。
* 给定场景，分别用不同模型实现，分析 BIO、NIO 等模式的设计和实现原理。
* NIO 提供的高性能数据操作方式是基于什么原理，如何使用？
* 或者，从开发者的角度来看，你觉得 NIO 自身实现存在哪些问题？有什么改进的想法吗？

### 知识扩展 ###

* 区分同步或异步（synchronous/asynchronous）
* 区分阻塞与非阻塞（blocking/non-blocking），在进行阻塞操作时，当前线程会处于阻塞状态，无法从事其他任务，只有当条件就绪才能继续，比如 ServerSocket 新连接建立完毕，或数据读取、写入操作完成；而非阻塞则是不管 IO 操作是否结束，直接返回，相应操作在后台继续处理。

* IO不仅仅是对文件的操作，网络编程中，比如Socket通信，都是典型的IO操作目标。
* 输入流、输出流（InputStream/OutputStream）是用于读取或写入字节的，比如操作图片文件。
* 而Reader/Writer则是用于操作字符，增加了字符编解码等功能，适用于类似从文件中读取或者写入文本信息

### 一课一练 ###

NIO 多路复用的局限性是什么呢

## 第13 | 谈谈接口和抽象类有什么区别 ##

谈谈接口和抽象类有什么区别？

### 典型回答 ###

接口和抽象类是Java面向对象设计的两个基础机制。

interface

* 接口是对行为的抽象，它是抽象方法的集合，利用接口可以达到 API 定义和实现分离的目的。
* 接口，不能实例化；不能包含任何非常量成员，任何 field 都是隐含着 public static final 的意义；同时，没有非静态方法实现，也就是说要么是抽象方法，要么是静态方法。Java 标准类库中，定义了非常多的接口，比如 java.util.List。

abstract

* 抽象类是不能实例化的类，用 abstract 关键字修饰 class，其目的主要是代码重用。
* 除了不能实例化，形式上和一般的 Java 类并没有太大区别，可以有一个或者多个抽象方法，也可以没有抽象方法。抽象类大多用于抽取相关 Java 类的共用方法实现或者是共同成员变量，然后通过继承的方式达到代码复用的目的。Java 标准库中，比如 collection 框架，很多通用部分就被抽取成为抽象类，例如 java.util.AbstractList。

Java类实现interface使用了implements关键词，继承abstract class则是使用extends关键词，参考Java标准库中的ArrayList。

### 考点分析 ###

* 对于 Java 的基本元素的语法是否理解准确。能否定义出语法基本正确的接口、抽象类或者相关继承实现，涉及重载（Overload）、重写（Override）更是有各种不同的题目。
* 在软件设计开发中妥善地使用接口和抽象类。你至少知道典型应用场景，掌握基础类库重要接口的使用；掌握设计方法，能够在 review 代码的时候看出明显的不利于未来维护的设计。
* 掌握 Java 语言特性演进。现在非常多的框架已经是基于 Java 8，并逐渐支持更新版本，掌握相关语法，理解设计目的是很有必要的。

### 知识扩展 ###



#### 面向对象设计 ####

封装、继承、多态

* 封装：隐藏事务内部的实现细节，以便提高安全性和简化编程。封装提供了合理的边界，避免外部调用者接触到内部的细节。
* 继承：是代码复用的基础机制。但要注意，继承可以看作是非常紧耦合的一种关系，父类代码修改，子类行为也会变动。在实践中，过度滥用继承，可能会起到反效果。
* 多态：重写（override）和重载（overload）、向上转型。
	* 重写是父子类中相同名字和参数的方法，不同的实现
	* 重载则是相同名字的方法，但是不同的参数 

S.O.L.I.D原则

* 单一职责，类或者对象最好是单一职责。
* 开关原则，设计要对扩展开发，对修改关闭。
* 里式替换，进行继承关系抽象时，凡是可以用父类或者基类的地方，都可以用子类替换。
* 接口分离，通过拆分功能单一的多个接口，将行为进行解耦。
* 依赖反转，实体应该依赖于抽象而不是实现。也就是说高层次模块，不应该依赖于低层次模块，而是应该基于抽象。实践这一原则是保证产品代码之间适当耦合度的法宝。

#### OOP原则实践中的取舍 ####

实践中我们还是要按照得失利弊进行选择，而不是一味得遵循原则。

	var list = new ArrayList();

#### OOP原则在面试题中的分析 ####

	public class VIPCenter {
	  void serviceVIP(T extend User user>) {
	     if (user instanceof SlumDogVIP) {
	        // 穷X VIP，活动抢的那种
	        // do somthing
	      } else if(user instanceof RealVIP) {
	        // do somthing
	      }
	      // ...
	  }

	public class VIPCenter {
	   private Map<User.TYPE, ServiceProvider> providers;
	   void serviceVIP(T extend User user） {
	      providers.get(user.getType()).service(user);
	   }
	 }
	 interface ServiceProvider{
	   void service(T extend User user) ;
	 }
	 class SlumDogVIPServiceProvider implements ServiceProvider{
	   void service(T extend User user){
	     // do somthing
	   }
	 }
	 class RealVIPServiceProvider implements ServiceProvider{
	   void service(T extend User user) {
	     // do something
	   }
	 } 


### 一课一练 ###

思考一下自己的产品代码，有没有什么地方违反了基本设计原则？那些一改就崩的代码，是否遵循了开关原则？

# 模块5 Java应用开发扩展 #

## 第36讲 |  ##

## 第37讲 | 谈谈Spring Bean的生命周期和作用域 ##

谈谈Spring Bean的生命周期和作用域

### 典型回答 ###

SpringBean生命周期比较复杂，可以分位创建和销毁两个过程。

1. 创建Bean会经过一系列的步骤
	* 实例化Bean对象
	* 设置Bean属性
	* 如果我们通过各种Aware接口声明了依赖关系，则会注入Bean对容器基础设施层面的依赖，具体包括BeanNameAware、BeanFactoryAware和ApplicationContextAware，分别会注入Bean ID、Bean Factory或者ApplicationContext。
	* 调用BeanPostProcessor的前置初始化方法
	* 如果实现了InitializingBean接口，则会调用afterPropertiesSet方法
	* 调用Bean自身定义的init方法。
	* 调用BeanPostProcessor的后置初始化方法postProcessAfterInitialization。
	* 创建过程完毕
2. Spring Bean的销毁过程会依次调用DisposableBean的destory方法和Bean自身定制的destory方法。SpringBean有五个作用域，其中最基础的有下面两种：
	* Singleton，Spring的默认作用域，为每个IOC容器创建唯一的一个Bean实例。
	* Prototype，针对每个getBean请求，容器都会单独创建一个Bean实例。
	* 从Bean的特点来看，Prototype适合有状态的Bean，而Singleton则适合无状态的情况。（使用Prototype作用域需要经过仔细思考，毕竟频繁创建和销毁Bean是有明显开销的）
如果是Web容器，则支持另外三种作用域：
	* Request
	* Session
	* GlobalSession
### 考点分析 ###

Bean 的生命周期是完全被容器所管理的，从属性设置到各种依赖关系，都是容器负责注入，并进行各个阶段其他事宜的处理，Spring 容器为应用开发者定义了清晰的生命周期沟通界面。

### 知识扩展 ###

1. 从一个常见的Spring面试题开始

### 一课一练 ###

介绍一下 Spring 声明式事务的实现机制，可以考虑将具体过程画图。

## 第38讲 | 对比Java标准NIO类库，你知道Netty是如何实现更高性能的吗？ ##

对比Java标准NIO类库，你知道Netty是如何实现更高性能的吗？

### 典型回答 ###

* 更加优雅的Reactor模式实现、灵活的线程模型、利用EventLoop等创新性的机制，可以非常高效地管理成百上千的Channel。
* 充分利用了Java的Zero-Copy机制，并且从多种角度，“斤斤计较”般的降低内存分配和回收的开销。例如，使用池化的Direct Buffer等技术，在提高IO性能的同时，减少了对象的创建和销毁；利用反射等技术直接操纵SelectionKey，使用数组而不是Java容器等。
* 使用更多本地代码。例如，直接利用JNI调用Open SSL等方式，获得比Java内建SSL引擎更好的性能。
* 在通信协议、序列化等其他角度的优化。

### 考点分析 ###

兼顾全局，但需要个别重点进行深入，最好是进行源码层面的深入阅读和实验。可参考 Norman 在 [Devoxx](https://speakerdeck.com/normanmaurer/writing-highly-performant-network-frameworks-on-the-jvm-a-love-hate-relationship) 上的分享（其中的很多技巧对于实现极致性能的 API 有一定借鉴意义，但在一般的业务开发中要谨慎采用）

### 知识扩展 ###

Netty，是一个异步，基于事件Client/Server的网络框架，目标是提供一种简单、快速构建网络应用的方式，同时保证高吞吐量、低延时、高可靠性。

Netty > java.nio + java.net!

* 从网络协议的角度，Netty除了支持传输层的UDP、TCP、SCTP协议，也支持HTTP(s)、WebSocket等多种应用层协议，它并不是单一协议的API
* 在应用中，需要将数据从Java对象转换成为各种应用协议的数据格式，或者进行反向的转换，Netty为此提供了一系列扩展和的编解码框架，与应用开发场景无缝衔接，并且性能良好
* 扩展了Java NIO Buffer，提供了自己的ByteBuf实现，并且深度支持Direct Buffer等技术，甚至hack了Java内部对Direct Buffer的分配和销毁等。同时，Netty也提供了更加完善的Scatter/Gather机制实现。

Netty 的能力范围大大超过了 Java 核心类库中的 NIO 等 API，可以说它是一个从应用视角出发的产物。

![97f1f65e7277681a9e6da818832c8342.png](img/97f1f65e7277681a9e6da818832c8342.png)

* ServerBootstrap：服务器端程序的入口
* Channel，作为一个基于NIO的扩展框架，Channel和Selector等概念仍然是Netty的基础组件，但是针对应用开发具体需求，提供了相对易用的抽象。
* EventLoop，Netty处理事件的核心机制。例子中使用了EventLoopGroup，我们在NIO中通常要做的几件事情，如注册感兴趣的时间、调度相应的Handler等，都是EventLoop负责
* ChannelFuture，实现异步IO的基础之一，保证了同一个Channel操作的调用顺序。Netty扩展了Java标准的Future，提供了针对自己场景的特有Future定义
* ChannelHandler，放置业务逻辑的主要地方
* CHannelPipeline，ChannelHandler链条的容器，每个Channel在创建后，自动被分配一个ChannelPipeline。通过ServerBootstrap注册了ChannelInitializer，并且实现了initChannel方法，而在该方法中则承担了向ChannelPipeline安装其他Handler的任务

### 考点 ###

Reactor模型和Netty线程模型
Pipelining、EventLoop等部分的设计实现细节。
Netty的内存管理机制、引用计数等特别手段
对比Java标准NIO API,例如，Java NIO早期版本中的Epoll空转问题，以及Netty的解决方式等

### 一课一练 ###

Netty的线程模型

## 第39讲 |  ##

# 周末福利 #

## 周末福利 | 谈谈我对Java学习和面试的看法 ##

能够做到将自己“掌握”的东西，准确地表达出来。

了解 - 掌握 - 精通，这是我们对事物掌握的一个循序渐进的过程。从自己觉得似乎懂了，到能够说明白，再到能够自然地运用它，甚至触类旁通，这是不断提高的过程。

从学习技巧的角度，每个人都有自己的习惯

* 动手实践是必要一步，如果连上手操作都不肯，你会发现自己的理解很难有深度。
* 在交流的过程中你会发现，很多似是而非的理解，竟然在试图组织语言的时候，突然就想明白了，而且别人的观点也验证了自己的判断。技术领域尤其如此，把自己的理解整理成文字，输出、交流是个非常好的提高方法，甚至我认为这是技术工作者成长的必经之路。

### 再来聊聊针对技术底层，我们是否有必要去阅读源代码？ ###

* 阅读代码的时间其实大大超过写代码的时间，这意味着阅读、总结能力，会直接影响我们的工作效率
* 参考别人的架构、实现，分析其历史上掉过的坑，具体阅读时可以从其修正过的问题等角度入手。
* 快速定位问题往往需要黑盒结合白盒能力，对内部一无所知，可能就没有思路。往往只有深入源代码层面进行定制或者自研，才能实现。我认为这也是软件工程师地位不断提高的原因之一。

### 关于阅读源代码和理解底层 ###

* 带着问题和明确目的去阅读
* 一定要有输出，至少要写下来，整理心得，交流、验证、提高。

软件开发中需要分清表象、行为（behavior），还是约定（specification）。喜欢源代码、底层是好的，但是一定要区分其到底是实现细节，还是规范的承诺，因为如果我们的程序依赖于表现，很有可能带来未来维护的问题。

需要慎重决定对内部的依赖。

### 面试时看中候选人的哪些素质和能力 ###

* 技术素养好，能够进行深度思考，而不是跳脱地夸夸其谈。
	* 最擅长的领域进行思考
	* 看出业务中平凡事情背后的工程意义
	* 良好的技术素养和工作热情
	* 配合一定的经验 
* 职业精神，是否表现出认真对待每一个任务。
	* 能够主动地从不清晰中找出清晰，切实地解决问题，是非常重要的能力 
* 是否 hands-on，是否主动。

## 周末福利 | 一份Java工程师必读书单 ##

* Java基础
	* Java编程思想：不仅仅介绍 Java 编程的基础知识点，也会思考编程中的各种选择与判断，包括穿插设计模式的使用，作者从理论到实践意义从不同的角度进行探讨，构建稳固的 Java 编程知识体系。（忽略过时内容，适当补充Java新技术的学习）
	* Java核心技术：完全没有Java编程基础。
	* Effective Java：第三版，涵盖了Java7和Java9的新特性。边学习边回顾，在吸收书中的经验时，多去想想自己在实际应用中是如何处理的。
* 并发和虚拟机
	* Java并发编程实战：内容全部建立在理论之上，先讲清道理再谈实践，可以真正让你知其然也知其所以然。这本书更加侧重并发编程中有哪些问题，如何来深刻地理解和定义问题，如何利用可靠的手段指导工程实践，并没有过分纠结于并发类库的源码层面。
	* 深入理解Java虚拟机：国内最好的JVM书籍之一
* 性能优化
	* Java性能优化权威指南：尽量体会其思路和原理，更加侧重于 Linux 等主流开放平台
* 如开源软件和互联网架构相关的图书 
	* Spring实战
	* Netty实战
	* Cloud Native Java：这部分的学习，不要盲目追新，最好是关注于分布式设计中的问题和解决思路，做到触类旁通，并且注重书籍之外的学习渠道
* 了解到分布式设计中的问题和解决的思路 
	* 大型分布式网站架构设计与实践
	* 深入分布式缓存：从原理到实践
* 读者推荐 
	* 设计模式之蝉
	* Java8 实战
	* java解惑
	* java_my_life

# 结束语 #

## 结束语 | 技术没有终点 ##

回想最初我在专栏内容设计时，侧重于Java语言和虚拟机的基础领域，因为这些内容在飞速变化的世界中更加具备长久价值。

1. 构建一个广泛的知识体系，但终归是要克制住诱惑，将某个领域做到精深
2. 技术人永远不要羞于表达自己的观点
3. 注重实践和项目推动，确保结果输出，仅仅把专栏看作是一个参照物，找到自己的技术道路

讲到这里，专栏的结束并不代表你将止步于此，而是应该通过前面的学习，把专栏的结束当作新的开始，不管过去你是否从中掌握了新的知识或是提升了视野，希望你能以一种全新的状态重新出发，继续勇攀技术的高峰。
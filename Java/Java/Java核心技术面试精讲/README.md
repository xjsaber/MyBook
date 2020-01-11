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

## 第11讲 | 谈谈接口和抽象类有什么区别 ##

谈谈接口和抽象类有什么区别？

### 典型回答 ###



### 考点分析 ###

### 知识扩展 ###

#### 面向对象设计 ####

封装、继承、多态



* 单一职责
* 开关原则
* 里式替换
* 接口分离
* 依赖反转

#### OOP原则实践中的取舍 ####

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
* 如果实现了InitializingBean
* 调用Bean自身定义的init方法。
* 调用BeanPost

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

爱因斯坦曾经说过，“如果你不能把它简单地解释出来，那说明你还没有很好地理解它”。了解 - 掌握 - 精通，这是我们对事物掌握的一个循序渐进的过程。从自己觉得似乎懂了，到能够说明白，再到能够自然地运用它，甚至触类旁通，这是不断提高的过程。

从学习技巧的角度，每个人都有自己的习惯

* 动手

### 关于阅读源代码和理解底层 ###

带着问题和明确目的去阅读

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

## 结束语 ##

回想最初我在专栏内容设计时，侧重于Java语言和虚拟机的基础领域，因为这些内容在飞速变化的世界中更加具备长久价值。

1. 构建一个广泛的知识体系，但终归是要克制住诱惑，将某个领域做到精深
2. 技术人永远不要羞于表达自己的观点
3. 注重实践和项目推动，确保结果输出，仅仅把专栏看作是一个参照物，找到自己的技术道路

讲到这里，专栏的结束并不代表你将止步于此，而是应该通过前面的学习，把专栏的结束当作新的开始，不管过去你是否从中掌握了新的知识或是提升了视野，希望你能以一种全新的状态重新出发，继续勇攀技术的高峰。
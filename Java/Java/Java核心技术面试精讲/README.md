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

### 知识扩展 ###

## 第11讲 | Java提供了哪些IO方式？NIO如何实现多路复用 ##

Java 提供了哪些 IO 方式？ NIO 如何实现多路复用？

### 典型回答 ###

IO
NIO
NIO2——Java7异步非阻塞

### 考点分析 ###

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

## 周末福利 | 一份Java工程师必读书单 ##

* Java编程思想
* Effective Java
* Java并发编程实战
* 深入理解Java虚拟机
* Java性能优化权威指南
* Spring实战
* Netty实战
* Cloud Native Java
* 大型分布式网站架构设计与实践
* 深入分布式缓存：从原理到实践
* 设计模式之蝉

# 结束语 #

## 结束语 ##

构建一个广泛的知识体系，但终归是要克制住诱惑，将某个领域做到精深

技术人永远不要羞于表达自己的观点

注重实践和项目推动，确保结果输出，仅仅把专栏看作是一个参照物，找到自己的技术道路
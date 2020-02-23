# Netty源码剖析与实战 #

第一章：初识Netty：背景、现状与趋势

## 01 | 课程介绍 ##

* 网络知识：《TCP/IP详解》、《图解TCP/IP》、《Wireshark网络分析就这么简单》
* Java 网络编程：《Java 网络编程》、《Java TCP/IP Socket编程》
* Netty 相关:《Netty权威指南》《Netty实战》（译自《Netty in action》: Norman Maurer）《Netty进阶之路：跟着案例学Netty》 

## 02 | 内容综述 ##

## 03 | 揭开Netty面纱 ##

* 本质：网络应用程序框架
* 实现：异步、事件驱动
* 特性：高性能、可维护、快速开发
* 用途：开发服务器和客户端

## 04 | 为什么舍近求远：不直接使用JDK NIO ##

* 支持常用应用层协议；
* 解决传输问题：粘包、半包现象；
* 支持流量整形；
* 完善的断连、Idle等异常处理等。

* 解决部分BUG
	* 解决 epoll bug：异常唤醒空转导致CPU 100%【lnx 2.4】
	* IP_TOS参数（IP包的优先级和QoS选项）使用时抛出异常
* 更好的API
	* Netty's ByteBuf
	* Netty's FastThreadLocal 
* 隔离变化、屏蔽细节
	* 隔离JDK NIO的实现变化：nio->nio2(aio)->...
	* 屏蔽JDK NIO的实现细节  

## 05 | 为什么孤注一掷：独选Netty？ ##

## 06 | Netty的前尘往事 ##

## 07 | Netty的现状与趋势 ##

![netty_project](img/netty_project.png)

## 一 介绍Netty是什么 ##

## 08 | Netty怎么切换三种I/O模式 ##

* 什么是经典的三种I/O模式
* Netty对三种I/O模式的支持
* 为什么Netty仅支持NIO了？
* 为什么Netty有多种NIO实现？
* NIO一定优于BIO么？
* 源码解读Netty怎么切换I/O模式？

### 什么是经典的三种I/O模式 ###

BIO（JDK1.4之前），NIO（JDK1.4），AIO（JDK1.7）

阻塞与非阻塞：阻塞：没有数据传过来时，读会阻塞直到有数据；缓冲区满时，写操作也会阻塞。非阻塞遇到这些情况，都是直接返回。

同步与异步：数据就绪后需要自己去读是同步，数据就绪直接读好再回调给程序是异步。

* BIO（阻塞I/O）
* NIO（非阻塞I/O）
* AIO（异步I/O）

NIO

* COMMON
	* NioEventLoopGroup
	* NioEventLoop
	* NioServerSocketChannel
	* NioSocketChannel 
* Linux
	* EpollEventLoopGroup
	* EpollEventLoop
	* EpollServerSocketChannel
	* EpollSocketChannel
* macOS/BSD
	* KQueueEventLoopGroup
	* KQueueEventLoop
	* KQueueServerSocketChannel
	* KQueueSocketChannel

### 为什么Netty仅支持NIO了 ###

* 不支持阻塞I/O
连接数高的情况下：阻塞->耗资源、效率低
* AIO被去除

### 为什么Netty有多种NIO实现？ ###

通用的NIO实现（Common）在Linux使用epoll

* Netty暴露了更多的可控参数：
	* JDK的NIO默认实现是水平触发
	* Netty是边缘触发（默认）和水平触发可切换
* Netty实现的垃圾回收更少、性能更好  

### NIO一定优于BIO么 ###

### 解读Netty怎么切换I/O模式的？ ###

* EventLoopGroup切换开发模式
* 切换channel：对应的IO模式

---

* 原理是什么？
* 为什么服务器开发并不需要切换客户端对应Socket？

## 09 | 源码剖析：Netty对I/O模式的支持 ##

原理是什么？

泛型+反射+工厂

* NioEventLoop：run 死循环监听处理事件
* NioServerSocketChannel：接受新连接

## 10 | Netty如何支持三种Reactor ##

* 什么是Reactor及三种版本
* 如何在Netty中使用Reactor模式
* 解析Netty对Reactor模式支持的常见疑问

|BIO|NIO|AIO|
|--|--|--|
|Thread-Per-Connection|Reactor|Proactor|

Reactor是一种开发模式，模式的核心流程：

注册感兴趣的事件->扫描是否有感兴趣的事件发生->事件发生后做出相应的处理。

## 11 | 源码剖析：Netty对Reactor的支持 ##

解析Netty对Reactor模式支持的常见疑问

* Netty如何支持主从Reactor模式的？
* 为什么说Netty的main reactor大多并不能用到一个线程组，只能线程组里面的一个？
* Netty对Channel分配NIO event loop的规则是什么
* 通用模式的NIO实现多路复用器是如何跨平台的

### Netty如何支持主从Reactor模式的？ ###

1. bossGroup ——MainReactor：bossGroup绑定socketChannel
	
		public void channelRead(ChannelHandlerContext ctx, Object msg) {
	    	// SocketChannel
	        final Channel child = (Channel) msg;
			...
		}
	
2. workerGroup——SubReActor：workerGroup绑定socketChannel
    
		public void channelRead(ChannelHandlerContext ctx, Object msg) {
	    	// SocketChannel
	        final Channel child = (Channel) msg;
			...
		}

3. execute

		ch.eventLoop().execute(new Runnable() {
	        @Override
	        public void run() {
	            pipeline.addLast(new ServerBootstrapAcceptor(
	                    ch, currentChildGroup, currentChildHandler, currentChildOptions, currentChildAttrs));
	        }
	    });

### 为什么说Netty的main reactor大多并不能用到一个线程组，只能线程组里面的一个？ ###

MainReactor需要绑定`ChannelFuture bind(InetAddress inetHost, int inetPort)`，一般只使用一次

### Netty对Channel分配NIO event loop的规则是什么 ###

* 2的幂次方
* 普通的

### 通用模式的NIO实现多路复用器是如何跨平台的 ###

	SelectorProvider.provider()
	...
	provider = sun.nio.ch.DefaultSelectorProvider.create();

对应不同的平台进行不同的验证

## 12 | TCP粘包/半包Netty全搞定 ##

* 什么是粘包和半包
* 为什么TCP应用中会出现粘包和半包现象？
* 解决粘包和半包问题的几种常用方法
* Netty对三种常用封帧方式的支持
* 解读Netty处理粘包、半包的源码

### 什么是粘包和半包 ###

### 为什么TCP应用中会出现粘包和半包现象？ ###

粘包的主要原因：

* 发送方每次写入数据 < 套接字缓冲区大小
* 接收方读取套接字缓冲区数据不够及时

半包的主要原因：

* 发送方每次写入数据 > 套接字缓冲区大小
* 发送的数据大于协议的MTU（Maximum Transmission Unit，最大传输单元），必须拆包；TCP/IP协议分层，每层大小都有控制

收发的角度：

一个发送可能被多次接受，多个发送可能被一次接收

传输的角度：

一个发送可能占用多个传输包，多个发送可能公用一个传输包

根本原因：

* TCP是流式协议，消息无边界。
* UDP又界限，无此类问题。

### 解决粘包和半包问题的几种常用方法 ###

|方式|比较|寻找消息边界方式|优点|缺点|推荐度|
|--|--|--|--|--|--|
|TCP连接改成短链接，一个请求一个短链接||建立连接到释放连接之间的信息即为传输信息|简单|效率低下|不推荐|
|封装成帧(Framing)|固定长度|满足固定长度即可|简单|空间浪费|不推荐|
|封装成帧(Framing)|分隔符|分隔符之间|简单不浪费，简单|内容本身出现分隔符时需转义，需要扫描内容|推荐|
|封装成帧(Framing)|固定长度字段存个内容的长度信息|先解析固定长度的字段获取长度，然后读取后续内容|精确定位用户数据，内容也不用转义|长度理论上有限制，需提前预知可能的最大长度而定义长度占用字节数|推荐+|
|封装成帧(Framing)|其他方式|每种都不同，例如JSON可以看{}是否应已经成对|||衡量实际场景，很多是对现有协议的支持|

### Netty对三种常用封帧方式的支持 ###

|方式\支持|解码|编码|
|--|--|--|
|封装成帧|固定长度|FixedLengthFrameDecoder|简单|
|封装成帧|分隔符|FixedLengthFrameDecoder|简单|
|封装成帧|固定长度字段存个内容的长度信息|LengthFieldBasedFrameDecoder|LengthFieldPrepender|

### 解读Netty处理粘包、半包的源码 ###

见ch13

## 13 | 源码剖析：Netty对处理粘包/半包的支持 ##

源码解析：

* 解码核心工作流程？
* 解码中两种数据积累器（Cumulator）的区别？
* 三种解码器的常用额外控制参数有哪些？

### 解码核心工作流程？ ###

ByteToMessageDecoder

 	first = cumulation == null;
    // 如果是第一笔数据直接赋值，不是的话追加
    cumulation = cumulator.cumulate(ctx.alloc(),
            first ? Unpooled.EMPTY_BUFFER : cumulation, (ByteBuf) msg);
    callDecode(ctx, cumulation, out);
	...
	decodeRemovalReentryProtection
	...
	decode(ctx, in, out);

### 解码中两种数据积累器（Cumulator）的区别？ ###

cumulator

* 默认方式：内存复制
* 逻辑视图：组合：非内存复制

### 三种解码器的常用额外控制参数有哪些？ ###

* lengthFieldOffset：往后移动1位
* lengthFieldLength：实际内容的长度
* lengthAdjustment：0（多加内容的字段）
* initialBytesToStrip：0（= do not strip header）截断长度

## 14 | 常用的“二次”编解码方式 ##

* 为什么需要“二次”解码
* 常用的“二次”编解码方式
* 选择编解码方式的要点
* Protobuf简介与使用
* 源码解读：Netty对二次编解码的支持

### 为什么需要“二次”解码 ###

解决半包和粘包问题的常用三种解码器叫一次解码器

* 一次解码器：ByteToMessageDecoder
	* io.netty.buffer.ByteBuf(原始数据流) -> io.netty.buffer.ByteBuf(用户数据)
* 二次解码器：MessageToMessageDecoder<T>
	* io.netty.buffer.ByteBuf(用户数据) ->  Java Object 

可以合并一次解码但会出现如下问题：

* 没有分层，不够清晰；
* 耦合性高，不容易置换方案；

### 常用的“二次”编解码方式 ###

* java序列化
* Marshaling
* XML
* JSON
* MessagePack
* Protobuf
* 其他

### 选择编解码方式的要点 ###

* 空间：编解码占用空间：需要比较不同的数据大小情况
* 时间：编解码速度：需要比较不同的数据大小情况
* 是否追求可读性
* 多语言的支持

### Protobuf简介与使用 ###

* 相比较XML和JSON，Protobuf更小、更快、更便捷
* Protobuf是跨语言的，并且自带了一个编译器（protoc），只需要用它进行编译，可以自动生成java、python、C++等代码，不需要再写其他代码

## 15 | 源码剖析：Netty对常用编解码的支持 ##

* netty-codec
	* bytes：将netty的ByteBuf转化为JDK的ByteBuf
	* compression
	* json
	* marshalling
	* protobuf
	* jdk的serialization
		* ObjectDecoder
		* ObjectEncoder
			* CompactObjectOutputStream：想比较JDK的序列化而言信息要少很多
	* string
	* xml  

### protobuf ###

    p.addLast(new ProtobufVarint32FrameDecoder()); //一次解码得到Protobuf
    p.addLast(new ProtobufDecoder(WorldClockProtocol.LocalTimes.getDefaultInstance())); //二次解码得到用户数据
    p.addLast(new ProtobufVarint32LengthFieldPrepender());// 最短接收到的信息粘包和半包问题
    p.addLast(new ProtobufEncoder()); //业务逻辑的处理得到结果经过处理得到字节数组
	p.addLast(new WorldClockClientHandler()); //业务逻辑的处理得到结果

#### ProtobufVarint32FrameDecoder ####

Varint = 长度字段的长度可变：值越小的数字使用越小的字节数，节约空间

	readRawVarint32

* parseFrom
* mergeFrom

#### ProtobufVarint32LengthFieldPrepender ####

Protobuf（Protobuf）Varint（长度可变）Prepender（长度在前面）

#### ProtobufEncoder ####

处理序列化

## 16 | keepalive与idle监测 ##

* 为什么需要keepalive？
* 怎么设计keepalive？以TCP keepalive为例
* 为什么还需要应用层keepalive？
* idle监测是什么？
* 如何在Netty中开启TCP keepalive和Idle监测

### 为什么需要keepalive ###

* 需要keepalive的场景：
	* 对端异常“崩溃”
	* 对端在，但是处理不过来
	* 对端在，但是不可达
* 不做keepalive的后果：
	* 连接已坏，但是还浪费资源维持，下次直接用会直接报错

### 怎么设计keepalive ###

TCP keepalive核心参数：

* net.ipv4.tcp_keepalive_time = 7200
* net.ipv4.tcp_keepalive_intvl = 75
* net.ipv4.tcp_keealive_probes = 9

当启用（默认关闭）keepalive时，TCP在连接没有数据通过的7200秒后发送keepalive消息，当探测没有确认时，按75秒的重试频率重发，一直发9个探测包都没有确认，就认定连接失败。

### 为什么还需要应用层keepalive？ ###

分层、分层、分层；不推荐修改系统参数

* 协议分层，各层关注点不同：传输层关注是否“通”，应用层关注是否可服务？
* TCP层的keepalive默认关闭，且经过路由等中转设备keepavlive包可能会被丢弃。
* TCP层的keepalive时间太长：默认>2小时，虽然可改，但属于系统参数，改动影响所有应用

HTTP属于应用层协议，HTTP Keep-Alive指的是对长连接和短链接的选择：

* Connection：Keep-Alive长连接（HTTP/1.1默认长连接，不需要带这个header）
* Connection：Close短连接

### idle监测是什么 ###

Idle检测，只是负责诊断，诊断后，做出不同的行为，决定Idle检测的最终用途：

* 发送keepalive：一般用来配合keepalive，减少keepalive消息。Keepalive设计演进：V1定时keepalive消息 -> V2空闲检测+判定为Idle时才发送keepalive。
	* V1：keepalive消息与服务器正常消息交换完全不关联，定时就发送；
	* V2：有其他数据传输的时候，不发送keepalive，无数据传输超过一定时间，判定为Idle，再发keepalive
* 直接关闭连接：
	* 快速释放损坏的、恶意的、很久不用的连接，让系统时刻保持最好的状态。
	* 简单粗暴，客户端可能需要重连。
	* 结合起来使用。按需keepalive，保证不会空闲，如果空闲，关闭连接     

### 如何在Netty中开启TCP keepalive和Idle监测 ###

开启keepalive：

* Server端开启TCP keepalive

		bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
		bootstrap.childOption(NioChannelOption.of(StandardSocketOption.SO_KEEPALIVE), true)

TODO

开启不同的Idle Check：

	ch.pipeline().addLast("idleCheckHandler", new IdleStateHandler(0, 20, 0, TimeUnit.SCEONDS))

### 源码剖析： Netty对keepalive与idle监测的支持 ###

* 设置TCP keepalive怎么生效
* 两种设置keepaive的方式有什么区别？
* Idle检测类包（io.netty.handler.timeout）的功能浏览
* 读Idle检测的原理
* 写Idle检测原理和参数obseveOutput用途？

## 18 | Netty的那些“锁”事 ##

* 分析同步问题的核心三要素
* 锁的分类
* Netty玩转锁的五个关键点：
	* 在意锁的对象和范围->减少粒度
	* 注意锁的对象本身大小->减少空间占用
	* 注意锁的速度->提高速度
	* 不同场景选择不同的并发类->因需而变
	* 衡量好锁的价值->能不用则不用 

### 分析同步问题的核心三要素 ###

* 原子性
* 可见性
* 有序性

### 锁的分类 ###

* 对竞争的态度：乐观锁（java.util.concurrent包中的原子类）与悲观锁（Synchronized）
* 等待锁的人是否公平而言：公平锁new ReentrantLock(true)与非公平锁new ReentrantLock()
* 是否可以共享：共享锁与独享锁：ReadWriteLock，其读锁是共享锁，其写锁是独享锁

### 在意锁的对象和范围->减少粒度 ###

Synchronized method -> Synchronized block

### 在意锁的对象大小->减少空间占用 ###

Atomic long VS long

* Atomic long是一个对象，包含对象头（object header）以用来保存hashcode、lock等信息，32位系统占用8字节；64位系统占16字节，所以在64位系统情况下：
	* volatile long = 8 bytes
	* AtomicLong = 8 bytes(valatile long) + 16 bytes（对象头） + 8 bytes(引用) = 32 bytes 

Atomic* objects -> Volatile primary type + Static Atomic * FieldUpdater

### 注意锁的速度->提高并发性 ###

* 高并发时：java.util.concurrent.atomic.AtomicLong -> java.util.concurrent.atomic.LongAdder
* 根据不同情况，选择不同的并发包：JDK < 1.8

### 不同场景选择不同的并发包->因需而变 ###

1. 关闭和等待关闭事件执行器（Event Executor）

	Object.wait/notify->CountDownLatch

Object.wait/notify需要把方法放到监视器里，不然会报错。
2. Nio Event loop中负责村基础task的Queue
Jdk's LinkedBlockingQueue(MPMC)->jctools'MPSC，Nio Event loop中负责存储task的Queue

### 衡量好锁的价值->能不用则不用 ###

局部串行：Channel的I/O请求处理Pipeline是串行
整体并行：多个串行化的线程（NioEventLoop）

Netty应用场景：局部串行 +整体并行>一个队列+多个线程模式

避免用锁：用ThreadLocal来避免资源争用，例如Netty轻量级的线程池实现io.netty.util.Recycle#threadLocal

### 精选留言 ###

#### Q ####
pipeline、eventloop和channel之间的关系
#### A ####
* pipeline：工厂的流水线
* eventloop: 操作流水线工序的实际干活的工人
* channel: 多套流水线，一个channel配一套流水线（pipeline）。工人（eventloop）共享。

## 19 | Netty如何玩转内存使用 ##


## 20 | 源码解析：Netty对堆外内存和内存池的支持 ##

# 第三章 Netty源码：从“线”（请求处理）的角度剖析 #

## 21 | Netty代码编译与总览 ##

## 22 | 源码剖析：启动服务 ##




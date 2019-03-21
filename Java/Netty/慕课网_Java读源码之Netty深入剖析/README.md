# 慕课网 Java读源码之Netty深入剖析 笔记 #

## 2.1 一个简单的socket例子 ##

## 2.2 Netty对于socket的抽象 ##

![2019-03-21_9-54-13.png](img/2019-03-21_9-54-13.png)

线程：监听连接

在监听连接中的一个线程中：监听信息

![2019-03-21_10-09-29.png](img/2019-03-21_10-09-29.png)

## 2.3 Netty 组建简单介绍 ##

### Netty基本组件 ###

* NioEventLoop -> Thread

run方法

监听客户端链接，处理客户端读写

* Channel -> Socket

ServerSocketChannel 对应的底层Nio模型的SocketChannel（父类就是一个channel）

对简单的连接进行封装

# TODO 了解的还不够精细，需要加大力度

* ByteBuf -> IO Byte

* Pipeline -> 逻辑链（logic chain）

每个channel都会有个pipeline，最终把逻辑的链路加到对应的channel中间

ChannelHandler -> 逻辑处理块（logic）

对逻辑的动态增加、更改。加入到ChannelHandler中数据流每次读写都会被ChannelHandler处理

NioEventLoop 启用了两种线程

1. 监听客户端连接
2. 监听客户端的读写

## ch3 Netty服务端启动 ##

### 3.1 服务端启动demo ###


### 3.2 服务端Channel的创建 ###

#### 两个问题 #### 

1. 服务端的socket在哪里初始化？
2. 在哪里accept连接

### Netty服务端启动 ###

* 创建服务端Channel
* 初始化服务端Channel
* 注册selector
* 端口绑定

### 创建服务端Channel ###

	bind()[用户代码入口]
	
		initAndRegister()[初始化并注册]
	
			反射创建服务单Channel
	
				newSocket()[通过jdk来创建底层jdk channel]

bind方法->doBind方法->initAndRegister方法->channelFactory.newChannel()->.channel->ReflectiveChannelFactory

### 反射创建服务端Channel ###

	newSocket()[通过jdk来创建底层jdk channel]
	
	* NioServerSocketChannel->newSocket->java.nio.channel.ServerSocketChannel->provider.openServerSocketChannel()->SelectorProvider.provider()

	NioServerSocketChannelConfig()[tcp参数配置类]
	
	* ServerSocket配置的抽象

	AbstractNioChannel()

		configureBlocking(false)[阻塞模式]

		AbstractChannel()[创建id，unsafe，pipeline]

		* 对channel的抽象
		 id = newId();
		 unsafe = newUnsafe();
		 pipeline = newChannelPipeline();

TODO 表示服务端channel创建的流程

### 初始化服务端Channel ###

### 注册selector ###

	AbstractChannel.register(channel) [入口]
		this.eventLoop = eventLoop[绑定线程]
		resgiter0()[实际注册]
			doRegister()[调用jdk底层注册]
			invokeHandlerAddedIfNeeded()
			fireChannelRegistered()[传播事件]


### 端口绑定 ###

	AbstractUnsafe.bind() [入口]
		doBind()
			javaChannel().bind()[jdk底层绑定]
		pipeline.fireChannelActive()[传播事件]
			HeadContext.readIfIsAutoRead()

## 第4章 NioEventLoop ##

三个问题

* 默认情况下，Netty服务端起多少线程？何时启动？
* Netty是如何解决JDK空轮训bug的？
* Netty如何保证异步串行无锁化？

* NioEventLoop创建
* NioEventLoop启动


NioEventLoop创建

	new NioEventLoopGroup()[线程，默认2*cpu]
		new ThreadPerTaskExecutor()[线程创建器]
		for(){newChild()}[构造NioEventLoop]
		chooserFactory.newChooser()[线程选择器]

### 创建NioEventLoop线程 ###

* 保存线程执行器ThreadPerTaskExecutor
* 创建一个MpscQueue
* 创建一个selector

newchild()

chooserFactory.newChooser()

chooser.next()

NioEvenentLoop[] 0 1 2 ... N
当n+1个链接进来的时候会循环从0开始绑定

	isPowerOfTwo()[判断是否是2的幂，如2、4、8、16]
		PowerOfTwoEventExecutorChooser[优化]
			index++ &(length-1)
		GenericEventExecutorChhoser[普通]
			abs(index++ % length)
# 慕课网 Java读源码之Netty深入剖析 笔记 #

## 2.3 Netty 组建简单介绍 ##

### Netty基本组件 ###

Channel -> Socket

ByteBuf -> IO Byte

Pipeline -> 逻辑链

ChannelHandler -> 逻辑处理块

NioEventLoop 启用了两种线程

1. 监听客户端连接
2. 监听客户端的读写

## ch3 Netty服务端启动 ##

### 3.1 服务端启动demo ###


### 3.2 服务端Channel的创建 ###

* 创建服务端Channel
* 初始化服务端Channel
* 注册selector
* 端口绑定

创建服务端CHannel

bind()[用户代码入口]

initAndRegister()[初始化并注册]

反射创建服务单Channel

newSocket()[通过jdk来创建底层jdk channel]

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
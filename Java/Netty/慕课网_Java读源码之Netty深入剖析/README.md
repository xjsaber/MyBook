# 慕课网 Java读源码之Netty深入剖析 笔记 #

# ch1 Netty深入剖析 # 

Dubbo、RocketMQ、Spart、Elasticsearch、Cassandra、Flink、Netty-SocketIO、Spring5、Play、Grpc

* 异步事件驱动框架，用于快速开发高性能服务端和客户端
* 封装了JDK底层BIO和NIO模型，提供高度可用的API
* 自带编解码器解决拆包粘包问题，用户只用关心业务逻辑
* 精心设计的ractor线程模型支持高并发海量连接
* 自带各种协议栈让你处理任何一种通用协议都不用亲自动手

## Netty是什么？ ##

## 技术储备 ##

# ch2 # 

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

## 3.3 服务端Channel的初始化 ##

### 初始化服务端Channel ###

* bind()[用户代码入口]

	* initAndRegister()[初始化并注册]

		* newChannel()[创建服务端channel]

			* init()[初始化服务端channel]

				* set ChannelOptions，ChannelAttrs
				
				* set ChildlOptions，ChildAttrs

				* config handler[配置服务端pipeline]

				* add ServerBootstrapAcceptor[添加连接器]

####  set ChannelOptions，ChannelAttrs ####

ServerBootStrap

绑定用户自定义属性，把option全部拿出来，最后绑定到channel上

	final Map<ChannelOption<?>, Object> options = options0();

    synchronized (options) {
        setChannelOptions(channel, options, logger);
    }

	final Map<AttributeKey<?>, Object> attrs = attrs0();
    synchronized (attrs) {
        for (Entry<AttributeKey<?>, Object> e: attrs.entrySet()) {
            @SuppressWarnings("unchecked")
            AttributeKey<Object> key = (AttributeKey<Object>) e.getKey();
            channel.attr(key).set(e.getValue());
        }
    }

####  set ChildlOptions，ChildAttrs ####

    synchronized (childOptions) {
        currentChildOptions = childOptions.entrySet().toArray(newOptionArray(0));
    }
    synchronized (childAttrs) {
        currentChildAttrs = childAttrs.entrySet().toArray(newAttrArray(0));
    }

#### config handler[配置服务端pipeline] ####

	final ChannelPipeline pipeline = ch.pipeline();
	ChannelHandler handler = config.handler();
	if (handler != null) {
	    pipeline.addLast(handler);
	}

#### add ServerBootstrapAcceptor[添加连接器] ####

	ch.eventLoop().execute(new Runnable() {
                    @Override
                    public void run() {
                        pipeline.addLast(new ServerBootstrapAcceptor(
                                ch, currentChildGroup, currentChildHandler, currentChildOptions, currentChildAttrs));
                    }
                });

## 3.4 注册selector ##

* AbstractChannel.register(channel)[入口]
	* this.eventLoop = eventLoop [绑定线程]
	* resgiter0()[实际注册]
		* doRegister()[调用jdk底层注册]
		* invokeHandlerAddedIfNeeded()
		* fireChannelRegistered()[传播事件]

### doRegister()[调用jdk底层注册] ###

	this.selectionKey = this.javaChannel().register(this.eventLoop().unwrappedSelector(), 0, this);

使用jdk底层的channel注册方式，将服务端的channel注册到selector上

## 3.5 服务端口的绑定 ##

* AbstractUnsafe.bind()[入口]
	* doBind()
		* javaChannel().bind() [jdk底层绑定]
	* pipeline.fireChannelActive()[传播事件]
		* HeadContext.readIfIsAutoRead() 

### doBind ###

	boolean wasActive = isActive();
	try {
	    doBind(localAddress);
	} catch (Throwable t) {
	    safeSetFailure(promise, t);
	    closeIfClosed();
	    return;
	}
	
	// TODO	在你绑定之前不是active，在你绑定之后是active的
	if (!wasActive && isActive()) {
	    invokeLater(new Runnable() {
	        @Override
	        public void run() {
	            pipeline.fireChannelActive();
	        }
	    });
	}

### boBeginRead ###

	// Channel.read() or ChannelHandlerContext.read() was called
    final SelectionKey selectionKey = this.selectionKey;
    if (!selectionKey.isValid()) {
        return;
    }

    readPending = true;

    final int interestOps = selectionKey.interestOps();
    if ((interestOps & readInterestOp) == 0) {
        selectionKey.interestOps(interestOps | readInterestOp);
    }

端口绑定->触发active事件->最终调用read事件->我可以读了（即新的连接）

## 3.6 服务启动总结 ##

newChannel() -> init() -> register() -> doBind()

* newChannel —— 调用jdk底层的api生成jdk的channel，生成一些组件（pipeline等）
* init —— 初始化，为服务端channel添加一个连接处理器
* register —— 注册selector，将jdk底层的channel注册到事件轮询器
* doBind —— 重新向selector注册readInterestOp，可以接受新的连接

# ch4 NioEventLoop #

## 4.1 NioEventLoop概述 ##

三个问题

* 默认情况下，Netty服务端起多少线程？何时启动？
* Netty是如何解决JDK空轮训bug的？
* Netty如何保证异步串行无锁化？

* NioEventLoop创建
* NioEventLoop启动
* NioEventLoop执行逻辑

### NioEventLoop创建 ###

* new NioEventLoopGroup()[线程，默认2*cpu]
	* new ThreadPerTaskExecutor()[线程创建器]
	* for(){newChild()}[构造NioEventLoop]
	* chooserFactory.newChooser()[线程选择器]

#### new ThreadPerTaskExecutor()[线程创建器] ####

	if (executor == null) {
	    executor = new ThreadPerTaskExecutor(newDefaultThreadFactory());
	}

#### for(){newChild()}[构造NioEventLoop] ####

	for (int i = 0; i < nThreads; i ++) {
	    boolean success = false;
	    try {
	        children[i] = newChild(executor, args);
	        success = true;
	    } catch (Exception e) {
	        // TODO: Think about if this is a good exception type
	        throw new IllegalStateException("failed to create a child event loop", e);
	    } finally {
	        if (!success) {
	            for (int j = 0; j < i; j ++) {
	                children[j].shutdownGracefully();
	            }
	
	            for (int j = 0; j < i; j ++) {
	                EventExecutor e = children[j];
	                try {
	                    while (!e.isTerminated()) {
	                        e.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
	                    }
	                } catch (InterruptedException interrupted) {
	                    // Let the caller handle the interruption.
	                    Thread.currentThread().interrupt();
	                    break;
	                }
	            }
	        }
	    }
	}

#### chooserFactory.newChooser()[线程选择器] ####

	chooser = chooserFactory.newChooser(children);

## 4-3 ThreadPerTaskThread ##

ThreadPerTaskExecutor

* 每次执行任务都会创建一个线程实体
* NioEventLoop线程命名规则nioEventLoop-1-xx

ThreadPerTaskExecutor(newDefaultThreadFactory)

newThread -> new FastThreadLocalThread

#TODO 深入理解下

## 4.4 创建NioEventLoop线程 ##

### newchild() ###

* 保存线程执行器ThreadPerTaskExecutor
* 创建一个MpscQueue
* 创建一个selector

newTaskQueue -> newMpscQueue

### 4.5 创建线程选择器 ###

chooserFactory.newChooser()

chooser.next() 

NioEvenentLoop[] 0 1 2 ... N
当n+1个链接进来的时候会循环从0开始绑定

* isPowerOfTwo()[判断是否是2的幂，如2、4、8、16]
	* PowerOfTwoEventExecutorChooser[优化]
		* index++ &(length-1)
	* GenericEventExecutorChhoser[普通]
		* abs(index++ % length)

#### PowerOfTowEventExecutorChooser ####

ids.getAndIncrement() & executors.length - 1

# TODO

				idx 111010
						 &
	executor.length-1 1111
			 result   1010

二进制的性能比取模来要高效得多

## 4.6 NioEventLoop启动触发器 ##

* 服务端启动绑定端口
* 新连接接入通过chooser绑定一个NioEventLoop

### NioEventLoop启动 ###

* bind() -> execute(task)[入口]
	* startThread() -> doStartThread()[创建线程]
		* ThreadPerTaskExecutor.execute()
			* thread = Thread.currentThread()
			* NioEventLoop.run[启动]  

#### bind() -> execute(task)[入口] ####

	// This method is invoked before channelRegistered() is triggered.  Give user handlers a chance to set up
	// the pipeline in its channelRegistered() implementation.
	channel.eventLoop().execute(new Runnable() {
	    @Override
	    public void run() {
	        if (regFuture.isSuccess()) {
	            channel.bind(localAddress, promise).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
	        } else {
	            promise.setFailure(regFuture.cause());
	        }
	    }
	});

Runable即是一个task，具体做的就是绑定端口的事情

#### startThread() -> doStartThread()[创建线程] ####
	
	// 判断是否是在本线程里面，这里的话，线程还没创建，自然返回的是不等false
	boolean inEventLoop = inEventLoop();
	if (inEventLoop) {
	    addTask(task);
	} else {
		// ThreadPerTaskExecutor 创建一个线程并启用
	    startThread();
	    addTask(task);
	    if (isShutdown() && removeTask(task)) {
	        reject();
	    }
	}

netty服务端启动的过程中，主线程最终会调用一个bind的方法，而这个bind的方法最终会把实际绑定的流程封装成一个task，调用服务端channel的一个方executor的方法去具体的执行。Netty会判断调用executor的方法不是Nio方法，调用doStartThread方法创建线程

## 4.7 NioEventLoop执行 ##

	SingleThreadEventExecutor.this.run()

	case SelectStrategy.CONTINUE:
        continue;
    case SelectStrategy.SELECT:
        select(wakenUp.getAndSet(false));

轮询IO事件-》处理IO事件

ioRatio默认是50

轮询IO事件，处理IO事件是1比1

### NioEventLoop.run() ###

* run() -> for(;;)
	* select()[检查是否有io事件]
	* processSelectedKeys()[处理io事件]
	* runAllTasks()[处理异步任务队列]

## 4.8 检测IO事件 ##

### select()方法执行逻辑 ###

* deadline以及任务穿插逻辑处理
* 阻塞式select
* 避免jdk空轮训的bug

#### deadline以及任务穿插逻辑处理 ####

#### 阻塞式select ####

    if (selectedKeys != 0 || oldWakenUp || wakenUp.get() || hasTasks() || hasScheduledTasks()) {
        // - Selected something, select!=0 表明轮训到
        // - waken up by user, or
        // - the task queue has a pending task.
        // - a scheduled task is ready for processing
        break;
    }

#### 避免jdk空轮训的bug ####

已经执行过一次

	long time = System.nanoTime();

减掉超轮训时间

    if (time - TimeUnit.MILLISECONDS.toNanos(timeoutMillis) >= currentTimeNanos) {
	// time - currentTimeNanos > TimeUnit.MILLSECONDS.toNanos(timeoutMillis) 当前时间减去
        // timeoutMillis elapsed without anything selected.
        selectCnt = 1;
    }


# TODO rebuildSelector


## 4.9 处理IO事件 ##

### processSelectedKey()执行逻辑 ###

* selected keySet优化
* processSelectedKeysOptimized()

	provider = selectorProvider;
	selector = openSelector();

	SelectedSelectionKeySet => SelectionKey[] keysA, int keysASize = size

	selectedKeys

	final AbstractNioChannel.NioUnsafe unsafe = ch.unsafe(); 一个unsafe跟一个channel绑定

#TODO 听不懂……继续深入

# ch5 Netty新连接的接入 #

## 5.1 Netty新连接接入处理逻辑 ##

1. 检测新连接
2. 创建NioSocketChannel
3. 分配线程及注册selector
4. 向selector注册读事件

### 两个问题 ###

* Netty是在哪里检测有新连接接入的？
* 新连接是怎样注册到NioEventLoop线程的？

## 5.2 检测新连接 ##

服务端启动的时候会绑定boss进程，selector io事件

* processSelectedKey(key, channel)[入口]
	* NioMessageUnsafe.read()
		* doReadMessages() [while循环]
			* javaChannel().accept()   


#### NioMessageUnsafe.read() ###

readyOps & (SelectionKey.OP_READ | SelectionKey.OP_ACCEPT)) != 0 || readyOps == 0

会进入 unsafe.read() —— AbstractNioMessageChannel$NioMessageUnsafe

#### doReadMessages() [while循环] ####

	do {
	    int localRead = doReadMessages(readBuf);
	    if (localRead == 0) {
	        break;
	    }
	    if (localRead < 0) {
	        closed = true;
	        break;
	    }
	
	    allocHandle.incMessagesRead(localRead);
	} while (allocHandle.continueReading());

#### javaChannel().accept() ####

服务端启动的jdk channel

TODO 生成jdk的channel然后包装成netty自身的channel

## 5.3 NioSocketChannel的创建 ##

* processSelectedKey(key, channel)[入口]
	* NioMessageUnsafe.read()
		* doReadMessages()[while循环]
			* javaChannel().accept()

### 创建NioSocketChannel ### 

* new NioSocketChannel(parent, ch)[入口] 
	* AbstractNioByteChannel(p, ch, op_read)
		* configureBlocking(false) & save op
		* create id, unsafe, pipeline
	* new NioSocketChannelConfig()
		* setTcoNoDelay(true) 禁止Nagle算法

#### new NioSocketChannel(parent, ch)[入口] ####

这里的ch是客户端的channel

直接使用new关键词来创建，而服务端channel使用反射的方法来创建

#### AbstractNioByteChannel(p, ch, op_read) ####

channel的read事件，后续如果有事件读写，那么请告诉我


#### create id, unsafe, pipeline ####



## 5.4 Channel的分类 ##

## 5.5 新连接NioEventLoop的分配和 ##

## 5.6 NioSocketChannel读事件的注册 ##

## 5.7 新连接接入总结 ##

# 第6章 # 



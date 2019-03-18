# Netty 入门与实战：仿写微信IM即时通讯系统 #

## ch2 Netty是什么？ ##

### NIO编程 ###

#### 线程资源受限 ####

NIO编程模型，新来一个连接不再创建一个新的线程，而是可以把这条连接直接绑定到某个固定的线程，然后这条连接所有的读写都由这个线程来负责。

#### 线程切换效率低下 ####

**IO读写以字节为单位**

	 Set<SelectionKey> set = serverSelector.selectedKeys();

### Netty编程 ###

1. `boss`对应`IOServer.java`中的接受新连接线程，主要负责创建新连接
2. `worker`对应`IOClient.java`中的负责读取数据的线程，主要用于读取数据以及业务逻辑处理

## ch3 Netty环境配置 ##

略

## ch4 服务端启动流程 ##

### 自动绑定递增端口 ###


### 服务端启动其他方法 ###

#### handler()方法 ####

childHandler() 指定处理新连接数据的读写处理逻辑，handler()用于指定再服务端启动过程中的一些逻辑。

#### attr()方法 ####

attr()方法可以给服务端的 channel，也就是NioServerSocketChannel指定一些自定义属性，然后我们可以通过channel.attr()取出这个属性。

#### childAttr()方法 ####

	serverBootstrap.childAttr(AttributeKey.newInstance('clientKey', 'clientValue')

#### childOption()方法 ####

	serverBootstrap
		.childOption(ChannelOption.SO_KEEPALIVE, true)
		.childOption(ChannelOption.TCP_NODELAY, true)

childOption()可以给每条连接设置一些TCP底层相关的属性。

* `ChannelOption.SO_KEEPALIVE`表示是否开启TCP底层心跳机制，true为开启
* `ChannelOption.TCP_NODELAY`表示是否开始Nagle算法，true表示关闭，false表示开启，通俗地说，如果要求高实时性，有数据发送时就马上发送，就关闭，如果需要减少发送次数减少网络交互，就开启

#### option()方法 ####

除了给每个连接设置这一系列属性之外，还可以给服务端channel设置一些属性，最常见就是so_backlog

	serverBootstrap.option(ChannelOption_SO_BACkLOG, 1024)

### 总结 ###

* 本文中，首先学习了Netty服务端启动的流程，一句话来说就是：创建一个引导类，然后给他指定线程模型，IO模型，连接读写处理逻辑，绑定端口之后，服务端就启动起来了。
* 然后学习到bind方法是异步的，通过这个异步机制来实现端口递增绑定。
* 讨论了Netty服务端启动额外的参数，主要包括给服务端Channel或者客户端Channel设置属性值，设置底层TCP参数。

## 客户端启动流程 ##

### 客户端启动Demo ###

对于客户端的启动来说，和服务端的启动类似，依然需要线程模型、IO模型、以及IO业务处理逻辑三大参数。

### 失败重连 ###


### 客户端启动其他方法 ###

#### attr()方法 ####

	bootstrap.attr(AttributeKey.newInstant"clientName"), "nettyClient");

`attr()`方法可以给客户端Channel，也即是说`NioSocketChannel`绑定自定义属性，然后通过`channel.attr()`取出这个属性。

#### option()方法 ####

* ChannelOption.CONNECT_TIMEOUT_MILLIS 表示连接的超时时间，超过这个时间还是建立不上的话则代表连接失败
* CHannelOption.SO_KEEPALIVE 表示是否开启TCP底层心跳机制，true为开启
* ChannelOption.TCP_NODELAY 表示是否开始Nagle算法，true表示关闭，false表示开启，通俗地说，如果要求高实时性，有数据发送就马上发送，就设置true关闭，如果需要减少发送次数，减少网络交互，就设置false开启。

### 总结 ###

* 创建一个引导类，然后给他指定线程模型，IO模型，连接读写处理逻辑，连接上特定主机和端口，客户端就启动起来了。
* `connect`方法是异步的，通过这个异步回调机制来实现指数退避重连逻辑。
* 客户端`Channel`绑定自定义属性值，设置底层TCP参数。

### 思考题 ###

与服务端启动相比，客户端启动的引导类少了哪些方法，为什么不需要这些方法？欢迎留言讨论

## ch6 实战：客户端与服务端双向通信 ##

### 客户端发数据到服务端 ###

### 服务端读取客户端数据 ###

### 服务端回数据给客户端 ###

### 总结 ###

1. 通过给逻辑处理链 `pipeline` 添加逻辑处理器，来编写数据的读写逻辑
2. 客户端连接成功之后会回调到逻辑处理器的` channelActive()` 方法，而不管是服务端还是客户端，收到数据后都会调用 `channelRead` 方法。
3. 写数据调用 `writeAndFlush` 方法，客户端与服务端交互的二进制数据载体为 `ByteBuf`，`ByteBuf` 通过连接的内存管理器创建，字节数据填充到 `ByteBuf` 之后才能写到对端。

## ch7 数据传输载体ByteBuf介绍 ##

### ByteBuf结构 ###

1. ByteBuf是一个字节容器，容器里面的数据分为三个部分。
2. 以上三段内容是被两个指针给划分出来的，从左到右，依次是读指针（readerIndex）、写指针（writerIndex），然后还有一个变量capacity，表示ByteBuf底层内存的总容量
3. 从ByteBuf从

废弃字节、可读字节和可写字节


### 容量API ###

	capacity()
	maxCapacity()
	readableBytes()与isReadable()
	writableBytes()、isWritable()与maxWritableBytes()

### 读写指针相关的API ###

	readerIndex()与readerIndex(int)

前者表示返回当前的读指针readerIndex，后者表示设置读指针。

	writeIndex()与writeIndex(int)
	markReaderIndex()与resetReaderIndex()
	
前者表示把当前的读指针保存起来，后者表示把当前的读指针恢复到之前保存的值。

### 读写API ###

writeBytes(byte[] src)

多次释放

不释放造成内存泄漏

### 实战 ###

### 总结 ###

1. Netty对二进制数据的抽象ByteBuf的结构，本质上它的原理就是，引用了一段内存，这段内存可以是队内也可以是堆外，然后用引用计数来控制这段内存是否需要被释放，使用读写指针来控制对ByteBuf的读写，可以理解为是外观模式的一种使用。
2. 基于读写指针和容量、最大可扩充容量，衍生出一系列的读写方法，要注意read/write与get/set区别。
3. 多个ByteBuf可以引用同一段内存，通过引用计数来控制内存的释放，遵循谁retain()谁release()的原则。

## ch8 客户端与服务端通信协议编解码 ##

### 什么是服务端与客户端的通信协议 ###

1. 首先，客户端把一个Java对象按照通信协议转换成二进制数据包。
2. 然后通过网络，把这段二进制数据包发送到服务端，数据的传输过程由TCP/IP协议负责数据的传输，与我们的应用层无关。
3. 服务端受到数据之后，按照协议取出二进制数据包中的相应字段，包装成Java对象，交给应用逻辑处理。
4. 服务端处理完之后，如果需要吐出响应给客户端，那么按照相同的流程进行。


### 通信协议的设计 ###

### 通信协议的实现 ###

#### Java对象 ####


### 总结 ###

1. 通信协议是为了服务端与客户端交互，双方协商出来的满足一定规则的二进制数据格式。
2. 介绍了一种通用的通信协议的设计，包括魔数、版本号、序列化算法标识、指令、数据长度、数据几个字段，该协议能够满足绝大多数的通信场景。
3. Java对象以及序列化，目的就是实现Java对象与二进制数据的互转。
4. 依照设计的协议以及ByteBuf的API实现了通信协议，这个过程称为编解码过程。

## ch9 实战：实现客户端登录 ##

### 登录流程 ###

1. 客户端会构建一个登录请求对象，然后通过编码把请求对象编码为ByteBuf，写到服务端
2. 服务端接受到ByteBuf之后，首先通过解码把ByteBuf解码为登录请求响应，然后进行校验
3. 服务端校验通过之后，构造一个登录响应对象，然后经过编码，然后再写回到客户端
4. 客户端接收到服务端之后，解码ByteBuf，拿到登录响应响应，判断是否登录成功

## ch10 实战：实现客户端与服务端收发消息 ##

## ch11 pipeline与channelHandler ##

客户端写在ClientHandler，服务端写在ServerHandler。

### pipeline与channelHandler的构成 ###

在Netty整个框架里面，一条连接对应着一个Channel，这条Channel所有的处理逻辑都在一个叫做`ChannelPipeline`的对象里面，`ChannelPipeline`是一个双向链表结构，他和Channel之间是一对一的关系。

### channelHandler的分类 ###

1. 第一个子接口是ChannelInboundHandler是处理读数据的逻辑。
2. 第二个子接口是ChannelOutboundHandler是处理写数据的逻辑。

### ChannelInboundHandler的事件传播 ###

在服务端的pipeline添加三个`ChannelInboundHandler`。

每个inBoundHandler都继承自`ChannelInboundHandlerAdapter`，然后实现了channelRead()方法。

在channelRead()方法里面，打印当前handler的信息，然后调用弗雷的`channelRead()`方法，而这里父类的`channelRead()`方法会自动调用到下一个inBoundhandler的`channelRead()`方法，并且会把当前inBoundHandler里处理完毕的对象 传递到下一个inBoundHandler。

addLast()方法来为pipeline添加inBoundHandler，A->B->C

### ChannelOutboundHandler的事件传播 ###



### 总结 ###

1. 引出pipeline和channelHandler的概念
2. channelHandler分为inBound和outBound两种类型的接口，分别是处理数据读和数据写的逻辑，可与tcp协议栈联系起来。
3. 两种类型的handler均有相应的默认实现，默认会把事件传递到下一个，这里的传递时间其实说
4. inBoundHandler的执行顺序与实际添加的孙婿相同，而outBoundHandler则相反。

## ch12 实战：构建客户端与服务端pipeline ##

## ch13 实战：拆包粘包理论与解决方案 ##

## ch14 channelHandler的生命周期 ##

## 小册总结 ##

### 1. Netty是什么？ ###

### 2. 服务端和客户端启动 ###

### 3. ByteBuf ###

## 小册读者总结 ##

## 扩展：进阶学习Netty的方向与资料 ##

### 1. 官网与github ###

### 2. 源码解析博客 ###


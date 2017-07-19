Netty实战

# 第1章 Netty——异步和事件驱动 #

网络编程、多线程处理和并发

## 1.1 Java网络编程 ##

### 1.1.1 Java NIO ###

本地套接字库很早地提供了非阻塞调用

* 可以使用setsockopt()方法配置套接字，以便读/写调用在没有数据的时候立即返回，也就是说，如果是一个阻塞调用应该已经被阻塞了。
* 可以使用操作系统的事件通知API注册一组非阻塞套接字，以确定它们中是否有任何的套接字已经有数据可供读写。

### 1.1.2 选择器 ###

class java.nio.channels.Selector是Java的非阻塞I/O实现的关键。

与阻塞I/O模型相比，这种模型提供了更好的资源管理：

* 使用较小的线程便可以处理许多连接，因此也减少了内存管理和上下文切换所带来开销；
* 当没有I/O操作需要处理的时候，线程也可以被用于其他任务。

## 2.2 Netty 客户端/服务器概览 ##

## 2.3 编写Echo服务器 ##

* channelRead() ——对于每个传入的消息都要调用；
* channelReacComplete() ——通知ChannelInboundHandler最后一次对channelRead()的调用是当前批量读取中的最后一条消息；
* exceptionCaught()——在读取操作期间，有异常抛出时会调用；

## 2.4 编写Echo客户端 ##

### 2.4.2 引导客户端 ###


# 第3章 Netty的组件和设计 #

通过两个不同的但却又密切相关的视角来探讨Netty：类库的视角以及框架的视角。对于使用Netty编写高效的、可重用的可维护的代码。

Netty解决了两个相应的关注领域，大致标记为技术的和体系结构的。

1. 基于Java NIO的异步的和事件驱动的实现，保证了高负载下应用程序性能的最大化和可伸缩性。
2. Netty包含了一组设计模式，将应用程序逻辑从网络层解耦，简化了开发过程，同时也最大限度地提高了可测试性、模块化以及代码的可重用性。

## 3.1 Channel、EventLoop和ChannelFuture ##

* Channel——Socket；
* EventLoop——控制流、多线程处理、并发；
* ChannelFuture——异步通知。

### 3.1.1 Channel接口 ###

Netty的Channel接口所提供的API，大大地降低了直接使用Socket类的复杂性。此外，Channel也是拥有许多预定义的、专门化实现的广泛类层次结构的根。

* EmbeddedChannel;
* LocalServerChannel;
* NioDatagramChannel;
* NioSctpChannel;
* NioSocketChannel;

### 3.1.2 EventLoop接口 ###

EventLoop定义了Netty的核心抽象，用于处理连接的声明周期中所发生的事件。

在高层次上说明了Channel、EventLoop、Thread以及EventLoopGroup之间的关系。

* 一个EventLoopGroup包含一个或者多个EventLoop；
* 一个EventLoop在它的生命周期内之和一个Thread绑定；
* 所有由EventLoop处理的I/O事件都将在它专有的Thread上被处理；
* 一个Channel在它的生命周期内只注册于一个EventLoop；
* 一个EventLoop可能会被分配给一个或多个Channel。

### 3.1.3 ChannelFuture接口 ###

Netty中所有的I/O操作都是异步的。

## 3.2 ChannelHandler 和 ChannelPipline ##

### 3.2.2 ChannelPipelin接口 ###

ChannelPipeline 提供了 ChannelHandler 链的容器，并定义了用于在该链上传播入站和出战事件流的API。当Channel被创建时，它会被自动地分配到它专属的ChannelPipeline。

* 一个ChannelInitializer的实现被注册到了ServerBootstrap中；
* 当ChannelInitializer.initChannel()方法被调用时，ChannelInitializer将在ChannelPipline中安装一组自定义的ChannelHandler;
* ChannelInitializer将它自己从ChannelPipeline中移除。

事件流经ChannelPipeline是ChannelHandler的工作，它们是在应用程序的初始化或者引导阶段被安装的。

### 3.2.3 更加地深入地了解ChannelHandler ###

有许多不同类型的ChannelHandler，各自的功能主要取决于它们的超类。Netty以适配器类的形式提供了大量默认的ChannelHandler实现，其皆在简化应用程序处理逻辑的开发过程。

ChannelPipeline中的每个ChannelHandler将负责把事件转发到链中的下一个ChannelHandler。

* ChannelHandlerAdapter
* ChannelInboundHandlerAdapter
* ChannelOutboundHandlerAdapter
* ChannelDuplexHandler

ChannelHandler的子类型：编码器、解码器和SimpleChannelInboundHandler<T>——ChannelInboundHandlerAdapter的一个子类。

### 3.2.4 编码器和解码器 ###

当你通过Netty发送或者接收一个消息的时候，就将会发生一次数据转换。入站消息会被解码；从字节转换为另一种格式（通常是Java对象）。如果是出站消息，则会发生相反的转换：将从它的当前格式编码为字节。

对应于特定的需要，Netty为编码器和解码器提供了不同类型的抽象类。这些基类的名称将类似于ByteToMessageDecoder或MessageToByteEncoder。

Netty提供的编码器/解码器适配器类都山脊线了ChannelOutboundHandler或者ChannleInboundHandler接口，对于入站数据来说，channelRead方法/事件已经被重写了。对于每个从入站Channel读取的消息，这个方法都将会被调用。随后，它将调用由预置解码器所提供的decode()方法，并将已解码的字节转发给ChannelPipeline中的下一个ChannelInboundHandler。

出站消息的模式是相反反响的：编码器将消息转换为字节，并将它们转发给下一个ChannelOutBoundHandler。

### 3.2.5 抽象类 SimpleChannelInboundHandler ###

利用一个ChannelHandler来接受解码消息，并对数据应用业务逻辑要创建一个这样的ChannelHandler，只需要扩展基类SimpleChannelInboundHandler<T>，其中T是你要处理的消息的Java类型。在这个ChannelHandler中，需要重写基类的一个或者多个方法，并且获取一个到ChannelHandlerContext的引用，这个引用将作为输入参数传递给ChannerlHandler的所有方法。

## 3.3 引导 ##

Netty的引导类为应用车改内需的网络层配置提供了容器，这涉及将一个进程绑定到某个指定的端口，或者将一个进程连接到另一个运行在某个指定主机的指定端口上的进程。

*面向连接的协议，“连接”这个术语仅适用于面向连接的协议，如TCP，其保证了两个连接端点之间消息的有序传递。*

有两种类型的引导：一种用于客户端（简单地称为Bootstrap），而另一种（ServerBootstrap）用于服务器。

**3.1 比较Bootstrap类**

|类别|Bootstrap|ServerBootstrap|
|--|--|--|
|网络编程中的作用|连接到远程主机和端口|绑定到一个本地端口|
|EventLoopGroup的数目|1|2|

# 第4章 传输 #

流经网络的数据总是具有相同的类型：字节。

## 4.1 案例研究 ##

### 4.1.1 不通过Netty使用OIO和NIO ###

### 4.1.2 通过Netty使用OIO和NIO ###

### 4.1.3 非阻塞的Netty版本 ###

## 4.2 传输API ##

传输API的核心是interface Channel，他被用于所有的I/O操作。

每个Channel都将会被分配一个ChannelPipeline和ChannelConfig。

ChannelPipeline持有所有将应用于入站和出站数据以及事件的ChannelHandler实例，这些ChannelHandler实现了应用程序在用于处理状态变化以及数据处理的逻辑。

* 将数据从一个格式转换为另一种格式；
* 提供异常的通知
* 提供Channel变为活动的或者非活动的通知
* 提供当Channel注册到EventLoop或者从EventLoop注销时的通知；
* 提供有关用户自定义事件的通知。

ChannelPipeline实现了一种常见的设计模式——拦截过滤器（Intercepting Filter）。UNIX管道是另一个熟悉的例子：多个命令被链接在一起，其中一个命令的输出端将连接到命令行中下一个命名的输入端。

Channel的方法

|方法名|描述|
|--|--|
|eventLoop|返回分配给Channel的EventLoop|
|pipeline|返回分配给Channel的ChannelPipeline|
|isActive|如果Channel桑出活动的，则返回true。活动的意义可能依赖于底层的传输。例如，一个Socket传输一旦连接到了远程节点便是活动的，而一个Datagram传输一旦被打开便是活动的|
|localAddress|返回本地的SocketAddress|
|remoteAddress|返回远程的SocketAddress|
|write|将数据写到远程节点。这个数据将被传递给ChannelPipeline，并且排队直到它被冲刷|
|flush|将之前已写的数据冲刷到底层传输，如一个Socket|
|writeAndFlush|一个简便的方法，等同于调用write()并借着调用flush()|

Netty的Channel实现是线程安全的，因此可以存储一个到Channel的引用，并且每当需要向远程结点写数据时，都可以使用它，即使当时许多线程都在使用它。

## 4.3 内置的传输 ##

Netty内置了一些可开箱即用的传输，因为并不是它们所有的传输都支持每一种协议，所以必须选择一个和应用车改内需所使用的协议相容的传输。

**4.2 Netty所提供的传输**

|名称|包|描述|
|--|--|
|NIO|io.netty.channel.socket.nio|使用java.nio.channels包作为基础——基于选择器的方式|
|Epoll|io.netty.channel.epoll|由JNI驱动的epoll()和非阻塞IO。这个传输支持只有在Linux上可用的多种特性，如SO_REUSEPORT，比NIO传输更快，而且是完全非阻塞|
|OIO|io.netty.channel.socket.oio|使用java.net包作为基础——使用阻塞流|
|Local|io.netty.channel.local|可以在VM内部通过管道进行通信的本地传输|
|Embedded|io.netty.channel.embedded|Embedded传输，允许使用ChannelHandler而又不需要一个真正的基于网络的传输

### 4.3.1 NIO——非阻塞I/O ###

NIO提供了一个所有I/O操作的全异步的实现。它利用了自NIO子系统被引入JDK1.4时便可用的基于选择器的API。

可以在Channel的状态发生变化时得到通知：

1. 新的Channel已被接收并且就绪
2. Channel连接已经完成
3. Channel有已经就绪的可供读取的数据
4. Channel可用于写数据

**4-3 选择操作的位模式**

|名称|描述|
|--|--|
|OP_ACCEPT|请求在接受新连接并创建Channel时获得通知|
|OP_CONNECT|请求建立一个连接时获得通知|
|OP_READ|请求当数据已经就绪，可以从Channel中读取获得通知|
|OP_WRITE|请求当可以向Channel中写更多的数据时获得通知。这处理了套接字缓冲区被完全填满时的情况，这种情况通常发生在数据的发送速度比远程节点可处理的速度更快的时候|

### 4.3.2 Epoll——用于Linux的本地非阻塞传输 ###

Netty的NIO传输基于Java提供的异步/非阻塞网络编程的通用抽象。

epoll——一个高度可扩展的I/O事件通知特性。这个API自Linux内核版本2.5.44（2002）被引入，提供了比旧的POSIX select和poll系统调用更好的性能，同时现在也是Linux上非阻塞网络编程的事实标准。

### 4.3.3 OIO——旧的阻塞I/O ###

Netty的OIO传输实现代表了一种折中：可以通过常规的传输API使用，但是由于它是建立在java.net包的阻塞实现之上的，所以它不是异步的。

### 4.3.4 用于JVM内部通信的Local传输 ###

Netty提供了一个Local，用于在同一个JVM中运行的客户端和服务器程序之间的异步通信。

























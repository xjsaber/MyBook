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
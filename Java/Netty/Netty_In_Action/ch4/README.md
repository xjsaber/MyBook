# 第四章：Transports（传输）  #

## 4.1 案例研究：切换传输方式 ##

### 4.1.1 使用Java的I/O和NIO ###

1. 一个Netty程序开始于Bootstrap类，Bootstrap类是Netty提供的一个可以通过简单配置来设置或"引导"程序的一个很重要的类。Netty中设计了Handlers来处理特定的"event"和设置Netty中的事件，从而来处理多个协议和数据。事件可以描述成一个非常通用的方法，因为你可以自定义一个
handler,用来将Object转成byte[]或将byte[]转成Object；也可以定义个handler处理抛出的异常。
2. 一个实现ChannelInboundHandler的类，ChannelInboundHandler是用来接收消息，当有消息过来时，你可以决定如何处理。当程序
需要返回消息时可以在ChannelInboundHandler里write/flush数据。可以认为应用程序的业务逻辑都是在ChannelInboundHandler中来处理的，业务罗的生命周期在ChannelInboundHandler中。
3. Netty连接客户端端或绑定服务器需要知道如何发送或接收消息，这是通过不同类型的handlers来做的，多个Handlers是怎么配置的？Netty提供了ChannelInitializer类用来配置Handlers。ChannelInitializer是通过ChannelPipeline来添加ChannelHandler的，如发送和接收消息，这些Handlers将确定发的是什么消息。ChannelInitializer自身也是一个ChannelHandler，在添加完其他的handlers之后会自动从ChannelPipeline中删除自己。
4. 所有的Netty程序都是基于ChannelPipeline。ChannelPipeline和EventLoop和EventLoopGroup密切相关，因为它们三个都和事件处理相关，所以这就是为什么它们处理IO的工作由EventLoop管理的原因。
5. Netty中所有的IO操作都是异步执行的，例如你连接一个主机默认是异步完成的；写入/发送消息也是同样是异步。也就是说操作不会直接执行，
而是会等一会执行，因为你不知道返回的操作结果是成功还是失败，但是需要有检查是否成功的方法或者是注册监听来通知；Netty使用Futures和
ChannelFutures来达到这种目的。Future注册一个监听，当操作成功或失败时会通知。ChannelFuture封装的是一个操作的相关信息，操作被执行时会立刻返回ChannelFuture。

### 4.1.2 Netty 中使用I/O 和NIO ###

### 4.1.3 Netty中实现异步支持 ###

## 4.2 Transport API ##

传输API的核心是Channel接口，它用于所有出站的操作。传输一般有特定的配置设置，只作用于传输，没有其他的实现。ChannelPipeline容纳了使用的ChannelHandler实例，这些ChannelHandler将处理通道传递的“入站”和“出站”数据。ChannelHandler的实现允许你改变数据状态和传输数据，

现在我们可以使用ChannelHandler做下面一些事情
* 传输数据时，将数据从一种格式转换到另一种格式
* 异常通知
* Channel变为有效或无效时获得通知
* Channel被注册或从EventLoop中注销时获得通知
* 通知用户特定事件

## 3.3 什么是Bootstrap？为什么使用它？ ##

“引导”是Netty中配置程序的过程，当你需要连接客户端或服务器绑定指定端口时需要使用bootstrap。

“引导”有两种类型，
1. 一种是用于客户端的Bootstrap(也适用于DatagramChannel) 
2. 一种是用于服务端的ServerBootstrap。

不管程序使用哪种协议，无论是创建一个客户端还是服务器都需要使用“引导”。两种bootsstraps之间有一些相似之处，其实他们有很多相似之处，也有一些不同。Bootstrap和ServerBootstrap之间的差异：

* Bootstrap用来连接远程主机，有1个EventLoopGroup
* ServerBootstrap用来绑定本地端口，有2个EventLoopGroup

1. “ServerBootstrap”监听在服务器监听一个端口轮询客户端的“Bootstrap”或DatagramChannel是否连接服务器。通常需要调用“Bootstrap”类的connect()方法，但是也可以先调用bind()再调用connect()进行连接，之后使用的Channel包含在bind()返回的
ChannelFuture中。
2. 客户端bootstraps/applications使用一个单例EventLoopGroup，而ServerBootstrap使用2个EventLoopGroup(实际上使用的是相同的实例)，它可能不是显而易见的，但是它是个好的方案。一ServerBootstrap可以认为有2个channels组，第一组包含一个单例ServerChannel，代表持有一个绑定了本地端口的socket；第二组包含所有的Channel，代表服务器已接受了的连接。

## 3.4 Channel Handlers and Data Flow（通道处理和数据流） ##

Handlers自身依赖于ChannelPipeline来决定它们执行的顺序，因此不可能通过ChannelPipeline定义处理程序的某些方面,反过来不可能定义也不可能通过ChannelHandler定义ChannelPipeline的某些方面。

如果你使用Netty应用将至少有一个ChannelHandler参与，换句话说，ChannelHandler对很多事情是关键的。那么ChannelHandler究竟是什么？给ChannelHandler一个定义不容易，我们可以理解为ChannelHandler是一段执行业务逻辑处理数据的代码，它们来来往往的通过ChannelPipeline。实际上，ChannelHandler是定义一个handler
的父接口，ChannelInboundHandler和ChannelOutboundHandler都实现ChannelHandler接口

Netty中有两个方向的数据流，上图显示的入站(ChannelInboundHandler)和出站(ChannelOutboundHandler)之间有一个明显的区别：若数据是从用户应用程序到远程主机则是“出站(outbound)”，相反若数据时从远程主机到用户应用程序则是“入站(inbound)”。

为了使数据从一端到达另一端，一个或多个ChannelHandler将以某种方式操作数据。这些ChannelHandler会在程序的“引导”阶段被添加
ChannelPipeline中，并且被添加的顺序将决定处理数据的顺序。ChannelPipeline的作用我们可以理解为用来管理ChannelHandler的一个容器，每个ChannelHandler处理各自的数据(例如入站数据只能由ChannelInboundHandler处理)，处理完成后将转换的数据放到ChannelPipeline中交给下一个ChannelHandler继续处理，直到最后一个ChannelHandler处理完成。

## 3.5 编码器、解码器和业务逻辑：细看Handlers ##

每个handler负责转发时间到ChannelPipeline的下一个handler。在*Adapter类(和子类)中是自动完成的，因此我们只需要在感兴趣的*Adapter中重写方法。

Netty有一下适配器：

* ChannelHandlerAdapter
* ChannelInboundHandlerAdapter
* ChannelOutboundHandlerAdapter

### 3.5.1 Encoders(编码器)，decoders（解码器） ###

发送或接收消息后，Netty必须将消息数据从一种形式转化为另一种。接收消息后，需要将消息从字节码转成Java对象(由某种解码器解码)；发送
消息前，需要将Java对象转成字节(由某些类型的编码器进行编码)。这种转换一般发生在网络程序中，因为网络上只能传输字节数据。

### 3.5.2 业务逻辑（Domain logic） ###

也许最常见的是应用程序处理接收到消息后进行解码，然后供相关业务逻辑模块使用。所以应用程序只需要扩展SimpleChannelInboundHandler<I>，也就是我们自定义一个继承SimpleChannelInboundHandler<I>的handler类，其中<I>是handler可以处理的消息类型。通过重写父类的方法可以获得一个ChannelHandlerContext的引用，它们接受一ChannelHandlerContext的参数，你可以在class中当一个属性存
储。

处理程序关注的主要方法是“channelRead0(ChannelHandlerContext ctx, I msg)”，每当Netty调用这个方法，对象“I”是消息，这里使用了Java的泛
型设计，程序就能处理I。如何处理消息完全取决于程序的需要。在处理消息时有一点需要注意的，在Netty中事件处理IO一般有很多线程，程序中尽量
不要阻塞IO线程，因为阻塞会降低程序的性能。

必须不阻塞IO线程意味着在ChannelHandler中使用阻塞操作会有问题。幸运的是Netty提供了解决方案，我们可以在添加ChannelHandler到
ChannelPipeline中时指定一个EventExecutorGroup，EventExecutorGroup会获得一个EventExecutor，EventExecutor将执行ChannelHandler的所有方
法。EventExecutor将使用不同的线程来执行和释放EventLoop。
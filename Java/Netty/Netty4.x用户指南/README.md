# Netty 4.x User Guide 读书笔记 #

## Introuction ##

原文地址
[https://waylau.gitbooks.io/netty-4-user-guide/](https://waylau.gitbooks.io/netty-4-user-guide/)

扩展阅读

[https://github.com/waylau/essential-netty-in-action]()
[https://github.com/waylau/apache-mina-2.x-user-guide]()

## Perface 前言 ##

### The Problem问题 ###

有时候一个通用的协议或他的实现并没有很好的满足需求。比如我们无法使用一个通用的 HTTP 服务器来处理大文件、电子邮件以及近实时消息，比如金融信息和多人游戏数据。我们需要一个高度优化的协议来处理一些特殊的场景。

### The Solution解决 ###

Netty是一个提供 asynchronous event-driven（异步事件驱动）的网络应用框架。Netty是一个NIO客户端服务器框架，大大简化了网络程序的开发过程比如TCP和UDP的socket服务的开发。

Netty 是一个精心设计的框架，它从许多协议的实现中吸收了很多的经验比如 FTP、SMTP、HTTP、许多二进制和基于文本的传统协议。

## Getting Started 开始 ##

### Before Getting Started 开始之前 ###

### Writing a Discard Server 写个抛弃服务器 ###

世上最简单的协议不是'Hello, World!' 而是 DISCARD(抛弃服务)。这个协议将会抛弃任何收到的数据，而不响应。

为了实现 DISCARD 协议，你只需忽略所有收到的数据。让我们从 handler （处理器）的实现开始，handler 是由 Netty 生成用来处理 I/O 事件的。

### Looking into the Received Data 查看收到的数据 ###

修改discard server的channelRead()方法
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
	      ctx.write(msg); // (1)
	      ctx.flush(); // (2)
	}
如果你再一次运行 telnet 命令，你将会看到服务端打印出了他所接收到的消息。

### Writing an Echo Server 写个应答服务器 ###

和 discard server 唯一不同的是把在此之前我们实现的 channelRead() 方法，返回所有的数据替代打印接收数据到控制台上的逻辑。

	@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ctx.write(msg); // (1)
        ctx.flush(); // (2)
    }

如果你再一次运行 telnet 命令，你会看到服务端会发回一个你已经发送的消息。

### Writing a Time Server 写个时间服务器 ###

在这个部分被实现的协议是 TIME 协议。和之前的例子不同的是在不接受任何请求时他会发送一个含32位的整数的消息，并且一旦消息发送就会立即关闭连接。

因为我们将会忽略任何接收到的数据，而只是在连接被创建发送一个消息，所以这次我们不能使用 channelRead() 方法了，代替他的是，我们需要覆盖 channelActive() 方法。

### Writing a Time Client 写个时间客户端 ###

不像 DISCARD 和 ECHO 的服务端，对于 TIME 协议我们需要一个客户端,因为人们不能把一个32位的二进制数据翻译成一个日期或者日历。

在 Netty 中,编写服务端和客户端最大的并且唯一不同的使用了不同的[BootStrap](http://netty.io/4.0/api/io/netty/bootstrap/Bootstrap.html) 和 [Channel](http://netty.io/4.0/api/io/netty/channel/Channel.html)的实现。

[TimeClient](code/src/java/com/xjsaber/netty/TimeClient.java)

### Dealing with a Stream-based Transport 处理一个基于流的传输 ###

**One Small Caveat of Socket Buffer 关于 Socket Buffer的一个小警告**

基于流的传输比如 TCP/IP, 接收到数据是存在 socket 接收的 buffer 中。不幸的是，基于流的传输并不是一个数据包队列，而是一个字节队列。意味着，即使你发送了2个独立的数据包，操作系统也不会作为2个消息处理而仅仅是作为一连串的字节而言。因此这是不能保证你远程写入的数据就会准确地读取。

因此，一个接收方不管他是客户端还是服务端，都应该把接收到的数据整理成一个或者多个更有意思并且能够让程序的业务逻辑更好理解的数据。

**The First Solution 办法一**

回到 TIME 客户端例子。同样也有类似的问题。一个32位整型是非常小的数据，他并不见得会被经常拆分到到不同的数据段内。然而，问题是他确实可能会被拆分到不同的数据段内，并且拆分的可能性会随着通信量的增加而增加。

最简单的方案是构造一个内部的可积累的缓冲，直到4个字节全部接收到了内部缓冲。

**The Second Solution 方法二**



http://netty.io/4.0/api/io/netty/channel/ChannelInboundHandler.html


### Speaking in POJO instead of ByteBuf 用POJO代替ByteBuf ###

### Summary 总结 ###


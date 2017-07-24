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
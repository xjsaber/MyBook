# 第二章：第一个Netty程序  #

## 2.1 设置开发环境 ##

## 2.2 Netty客户端和服务器概述 ##

### 1.2.1 Callbacks(回调) ###
类似js回调。

[1.2.1回调 code]()

使用回调技术可以从“caller”线程移动到期他的线程执行；但也不会保证fetcherCallback的每个方法都会北执行。

### 1.2.2 Futures ###

Futures 是一个抽象的概念，它表示一个值，该值可能在某一点变得可用。一个 Future 要么获得计算完的结果，要么获得计算失败后的异常。Java 在 java.util.concurrent 包中附带了 Future 接
口，它使用 Executor 异步执行。

有时候使用 Future 感觉很丑陋，因为你需要间隔检查 Future 是否已完成，而使用回调会直接收到返回通知。

[1.2.2Futures code]()

## 2.3 编写一个应答服务器 ##

### 2.3.1 启动服务器 ###

1. 创建 ServerBootstrap 实例来引导绑定和启动服务器
2. 创建 NioEventLoopGroup 对象来处理事件，如接受新连接、接收数据、写数据等等
3. 指定 InetSocketAddress，服务器监听此端口
4. 设置 childHandler 执行所有的连接请求
5. 都设置完毕了，最后调用 ServerBootstrap.bind() 方法来绑定服务器

### 2.3.2 实现服务器业务逻辑 ###

Netty使用futures和回调概念,他的设计允许你处理不同的事件类型。

### 2.3.3 捕获异常 ###

重写ChannelHandler的exceptionCaught方法可以捕获服务器的异常，比如客户端连接服务器后强制关闭，服务器会抛出"客户端主机强制关闭错
误"，通过重写exceptionCaught方法就可以处理异常，比如发生异常后关闭ChannelHandlerContext。

## 2.4 编写应答程序的客户端 ##

服务器写好了，现在来写一个客户端连接服务器。应答程序的客户端包括以下几步：

1. 连接服务器
2. 写数据到服务器
3. 等待接受服务器返回相同的数据
4. 关闭连接

### 2.4.1 引导客户端 ###

创建启动一个客户端包含下面几步：
1. 创建Bootstrap对象用来引导启动客户端
2. 创建EventLoopGroup对象并设置到Bootstrap中，EventLoopGroup可以理解为是一个线程池，这个线程池用来处理连接、接受数据、发送数据
3. 创建InetSocketAddress并设置到Bootstrap中，InetSocketAddress是指定连接的服务器地址
4. 添加一个ChannelHandler，客户端成功连接服务器后就会被执行
5. 调用Bootstrap.connect()来连接服务器
6. 最后关闭EventLoopGroup来释放资源

### 2.4.2 实现客户端的业务逻辑 ###

1. channelActive()：客户端连接服务器后被调用
2. channelRead0()：从服务器接收到数据后调用
3. exceptionCaught()：发生异常时被调用

## 2.5 编译和运行echo(应答)程序客户端和服务器 ##

## 2.6 总结 ##
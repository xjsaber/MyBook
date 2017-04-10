# 第三章：Netty核心概念  #

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

* 创建 ServerBootstrap 实例来引导绑定和启动服务器
* 创建 NioEventLoopGroup 对象来处理事件，如接受新连接、接收数据、写数据等等
* 指定 InetSocketAddress，服务器监听此端口
* 设置 childHandler 执行所有的连接请求
* 都设置完毕了，最后调用 ServerBootstrap.bind() 方法来绑定服务器

### 2.3.2 实现服务器业务逻辑 ###

Netty使用futures和回调概念



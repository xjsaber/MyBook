# 第一章：Netty 介绍  #

Netty 是一个 NIO client-server(客户端服务器)框架，使用 Netty 可以快速开发网络应用，例如服务器和客户端协议。Netty 提供了一种新的方式来使开发网络应用程序，这种新的方式使得它很容易使用和有很强的扩展性。Netty 的内部实现时很复杂的，但是 Netty 提供了简单易用的 api 从网络处理代码中解耦业务逻辑。Netty 是完全基
于 NIO 实现的，所以整个 Netty 都是异步的。

## 1.1  为什么使用 Netty ？ ##
Netty 提供了高层次的抽象来简化 TCP 和 UDP 服务器的编程，但是你仍然可以使用底层地 API。

1.1.1 

1.1.2

## 1.2 异步设计 ##

### 1.2.1 Callbacks(回调) ###
类似js回调。

[1.2.1回调 code]()

使用回调技术可以从“caller”线程移动到期他的线程执行；但也不会保证fetcherCallback的每个方法都会北执行。

### 1.2.2 Futures ###

Futures 是一个抽象的概念，它表示一个值，该值可能在某一点变得可用。一个 Future 要么获得计算完的结果，要么获得计算失败后的异常。Java 在 java.util.concurrent 包中附带了 Future 接
口，它使用 Executor 异步执行。

有时候使用 Future 感觉很丑陋，因为你需要间隔检查 Future 是否已完成，而使用回调会直接收到返回通知。

[1.2.2Futures code]()
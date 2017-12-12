# Netty 权威指南 #

### 基础篇 走进Java Nio ###

## 第1章 Java的I/O演进之路 ##

## 第2章 Java的I/O演进之路 ##

## 第3章 Netty入门应用 ##

### 3.1 Netty开发环境的搭建 ###

#### 3.1.1 下载Netty的软件包 ####

#### 3.1.2 搭建Netty应用工程 ####

### 3.2 Netty服务端开发 ###

### 3.3 Netty客户端开发 ###

### 3.4 运行和调试 ###

#### 3.4.1 服务端和客户端的运行 ####

#### 3.4.2 打包和部署 ####

### 3.5 总结 ###



## 第4章 TCP粘包/拆包问题的解决之道 ##

### 4.1 TCP粘包/拆包 ###

TCP是个“流”协议，所谓流，就是没有界限的一串数据。

#### 4.1.1 TCP 粘包/拆包问题说明 ####

#### 4.1.2 TCP 粘包/拆包发生的原因 ####

#### 4.1.3 粘包问题的解决策略 ####

1. 消息定长
2. 在包尾增加回车换行符进行分割
3. 将消息分为消息头和消息体，消息头中包含表示消息总长度（或消息体长度）的字段，通常设计思路为消息头的第一个字段使用int32来表示消息的总长度；
4. 更复杂的应用层协议

### 4.2 未考虑TCP粘包导致功能异常案例 ###

#### 4.2.1 TimeServer的改造 ####

#### 4.2.2 TimeClient的改造 ####

#### 4.2.3 运行结果 ####

### 4.3 利用LineBasedFrameDecoder解决TCP粘包问题 ###

#### 4.3.1 支持TCP粘包的TimeServer ####



#### 4.3.2 支持TCP粘包的TimeClient ####

中级篇 Netty编解码开发指南

## 第6章 编解码技术 ##

### 6.1.1 无法跨语言 ###

### 6.1.2 序列化后的码流太大 ###

* 是否支持跨语言，支持的语言种类是否丰富；
* 编码后的码流大小；
* 编解码的性能；
* 类库是否小乔，API使用是否方便；
* 使用者需要手工开发的工作量和难度

### 6.1.3 序列化性能太低 ###

无论是序列化后的码流大小，还是序列化的性能， JDK默认的序列化机制表现得都很差。因此，不会选择Java序列化作为远程跨节点调用的编解码框架。

## 6.2 业界主流的编解码框架 ##

### 6.2.1 Google的Protobuf介绍 ###

* 结构化数据存储格式（XML、JSON等）；
* 高效的编解码性能；
* 语言无关、平台无关、扩展性好；
* 官方支持Java、C++和Python三种语言。

### 6.2.2 Facebook的Thrift介绍 ###

Thrift可以作为高性能的通信中间件使用，它支持数据（对象）序列化和多种类型的RPC服务。Thrift适用于静态的数据交换，需要先确定好它的数据结构，当诗句结构发生变化时，必须重新编辑IDL文件，生成代码和编译。

1. 语言系统以及IDL编译器：负责由用户给定的IDL文件生成相应语言的接口代码；
2. TRrotocol：RPC的协议层，可以选择多种不同的对象序列化方式，如JSON和Binary；
3. TTransport：RPC的传输层，同样可以选择不同的传输层实现，如socket、NIO、MemoryBuffer等；
4. TProcessor：作为协议层和用户提供的服务实现之间的纽带，负责调用
5. TServer：聚合TProtocol、TTransport和TProcessor等对象。

### 6.2.3 JBoss Marshalling介绍 ###

相比于传统的Java序列化机制，它的优点如下：

* 可插拔的类解析器
* 可插拔的对象替换技术
* 可插拔的预定义类缓存表
* 无须实现java.io.Serializable接口，即可实现Java序列化；
* 通过缓存技术提升对象的序列化性能

## 6.3 总结 ##

## 第7章 MessagePack编解码 ##

### 7.1 MessagePack介绍 ###

#### 7.1.1 MessagePack多语言支持 ####

#### 7.1.2 MessagePack Java API介绍 ####

#### 7.1.3 MessagePack开发包下载 ####

### 7.2 MessagePack 编码器和解码器开发 ###

编解码框架可以方便地集成第三方序列化框架，Netty预集成了几种常用的编解码框架。

#### 7.2.1 MessagePack 编码器开发 ####

MsgpackEncoder继承MessageToByteEncoder，负责Object类型的POJO对象编码为byte数组，然后写入到ByteBuf中。

#### 7.2.2 MessagePack 解码器开发 ####

编解码器开发之后，以Netty原生的Echo程序为例，进行测试。对Echo进行简单改造，传输的对象由字符串修改为POJO对象，利用MessagePack对POJO对象进行序列化。

#### 7.2.3 功能测试 ####

## 第8章 Goole Protobuf 编解码 ##

### 8.1 Protobuf的入门 ###

#### 8.1.1 Protobuf开发环境搭建 ####

#### 8.1.2 Protobuf编解码开发 ####

#### 8.1.3 运行Protobuf例程 ####

### 8.2 Netty的Protobuf服务端开发 ###

#### 8.2.1 Protobuf版本的图书订购服务端开发 ####



## 第9章 JBoss Marsshalling 编解码 ##

## 第11章 WebSocket协议开发 ##

### 11.1 HTTP协议的弊端 ###

1. HTTP协议为半双工协议。
2. HTTP消息冗长而繁琐
3. 针对服务器推送的黑客攻击

### 11.2 WebSocket入门 ###

* 单一的TCP连接，采用全双工模式通信；
* 对代理、防火墙和路由器透明；
* 无头部信息、Cookie和身份验证；
* 无安全开销；
* 通过“ping/pong”帧保持链路激活；
* 服务器可以主动传递消息给客户都按，不再需要客户端轮询。

#### 11.2.1 WebSocket背景 ####

取代轮询和Comet技术

#### 11.2.2 WebSocket连接建立 ####

为了建立一个WebSocket连接，客户端浏览器首先要向服务器发起一个HTTP请求，这个请求和通常的HTTP请求不同，包含了一些附加头信息，其中附加头信息“Upgrade WebSocket”表明这事一个申请协议升级的HTTP请求。

#### 11.2.3 WebSocket生命周期 ####

握手成功之后，服务器和客户端可以通过“messages”的方式进行通信，一个消息由一个或者多个帧组成。WebSocket的消息不一定对应一个特定网络层的帧，

#### 11.2.4 WebSocket连接关闭 ####

为关闭WebSocket连接，客户端和服务端需要通过一个安全的方法关闭底层TDCP连接以及TLS会话。如果合适，丢弃任何肯呢个已经接收的字节；必要时（比如受到攻击），可以通过任何可用的手段关闭连接。

底层的TCP连接，在正常情况下，应该首先由服务器关闭。再异常情况下（例如再一个合理的时间周期后没有接收到服务器

### 11.3 Netty WebSocket协议开发 ###

#### 11.3.1 WebSocket服务端功能介绍 ####

支持WebSocket的浏览器通过WebSocket协议发送请求消息给服务端，服务端对请求消息进行判断，如果是合法的WebSocket请求，则获取请求消息体（文本）。

#### 11.3.2 WebSocket服务端开发 ####

服务端对握手

* HttpServerCodec，将请求和应答消息编码或者解码为HTTP消息
* HttpObjectAggregator，将HTTP消息的多个部分组合成一条完整的HTTP消息
* ChunkedWriteHandler，来向客户端发送HTML5文件，主要用于支持浏览器和服务端进行WebSocket通信
* 最后增加WebSocket服务端handler

WebSocketServerHandler

1. 第一次握手请求消息由HTTP协议承载，是一个HTTP消息，执行handleHttpRequest方法处理WebSocket握手请求。
2. 对握手请求	请求消息进行判断，如果消息头没有包含Upgrade字段或者它的值不是websocket，则返回HTTP400响应。
3. 握手请求简单校验通过之后，开始构造握手工厂，创建握手处理类WebSocketServerHandler，通过它构造握手响应消息返回给客户端，同时将WebSocket的编码和解码类动态添加到ChannelPipeline中，用于WebSocket消息的编解码。
4. 添加WebSocketEncoder和WebSocketDecoder之后，服务端就可以自动对WebSocket消息进行编解码（后面的业务handler可以直接对WebSocket对象进行操作）
5. handler业务可以直接对WebSocket对象进行操作，客户端通过问嗯框提交请求消息给服务端，WebSocketServerHandler接收到的是已经解码后的WebSocketFrame消息。
6. 对WebSocket请求消息进行处理，首先对控制帧进行判断，如果是关闭链路的控制消息，就调用WebSocketServerHandler的close关闭链路；如果是ping，则返回pong消息返回
7. 从TextWebSocketFrame中获取请求字符串，对它处理后通过构造新的TextWebSocketFram消息返回给客户端，由于握手应答时动态添加了TextWebSocketFrame的编码类。

#### 11.3.3 运行WebSocket服务端 ####

### 11.4 总结 ###
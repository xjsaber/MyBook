# 第2章 NIO入门 #

## 2.1 传统的BIO编程 ##

### 2.1.3 同步阻塞式I/O创建的TimeClient源码分析 ###

BIO主要的问题在于每当有一个新的客户端请求接入时，服务器必须创建一个新的线程处理新接入的客户端链路，一个线程只能处理一个客户端连接。再高性能服务器应用领域，往往需要面向成千上万个客户端的并发连接。这种模型。

为了改进一线程一连接模型，演进了一种通过线程池或者消息队列是新建1个或者多个线程处理N个客户端的模型，由于它的底层通信机制依然使用同步阻塞I/O,所以称伪“伪异步”。

## 2.2 伪异步I/O编程 ##

### 2.2.1 伪异步I/O模型图 ###

当有新的客户端接入的时候，将客户端的Socket封装成一个Task(该任务实现java.lang.Runnable接口)投递到后端的线程池中进行处理，JDK的线程池维护一个消息队列和N个活跃线程对消息队列中的任务进行处理。由于线程池可以设置消息队列的大小和最大线程数。

### 2.2.2 伪异步式I/O创建的TimeServer源码分析 ###

伪异步I/O通信框架采用了线程池实现，因此避免了为每个请求都创建一个独立线程造成的线程资源耗尽问题。但是


### 2.2.3 伪异步I/O弊端分析 ###

InputStream

OutputStream

## 2.3 NIO编程 ##

### 2.3.1 NIO类库简介 ###

#### 1.缓冲区Buffer ####
Buffer是一个对象，它包含一些要写入或者要读出的苏话剧。在NIO类库中加入Buffer对象，体现了新库与原I/O的一个重要区别。在面向流的I/O中，可以将数据直接写入或者将数据直接读到Stream对象中。

* ByteBuffer: 字节缓存区
* CharBuffer: 字符缓冲区
* ShortBuffer
* IntBuffer
* LongBuffer
* FloatBuffer
* DoubleBuffer

#### 2.通道Channel ####
网络数据通过Channel读取和写入。通道与流的不同之处在于通道是双向的，流只是在一个方向上移动（一个流必须是inputStream或者OutputStream的子类），而且通道可以用于读、写或者同时用于读写。

Channel是全双工的。在UNIX网络编程模型中，底层操作系统的通道都是全双工的，同时支持读写操作。

Channel可以分为两大类：分别是用于网络读写的SelectableChannel和用于文件操作的FileChannel。ServerSocketChannel和SocketChannel都是Selectable

#### 3.多路复用器Selector ####
Java NIO编程的基础。

Selector会不断地轮询注册在其上地Channel，如果某个Channel上面有新地TCP连接接入、读和写事件，这个Channel就处于就绪状态，会呗Selector轮询出来，然后通过SelectionKey可以获取就绪Channel地集合，进行后续地I/O操作。

一个多路复用器Selector可以同事轮询多个Channel，由于JDK使用了epoll()代替传统地select实现，所以它并没有最大连接句柄1024/2048地限制。这也就意味着只需要一个线程负责Selector地轮询，就可以接入成千上万地客户端。

### 2.3.2 NIO服务端序列图 ###

步骤一：打开ServerSocketChannel，用于监听客户端的连接，它是所有客户端连接的父管道。

	ServerSocketChannel acceptorSvr = ServerSocketChannel.open();

步骤二：绑定监听端口，设置连接为非阻塞模式。
	
	acceptorSvr.socket().bind(new InetSocketAddress(InetAddress.getByName("IP"), port));
	acceptorSvr.configureBlocking(false);

步骤三：创建Reactor线程，创建多路复用器并启动线程。

	Selector selector = Selector.open();
	New Thread(new ReactorTask()).start();

步骤四：将ServerSocketChannel注册到Reactor线程的多路复用器Selector上，监听ACCEPT事件。

	SekectionKey key = acceptorSvr.register(selector, SelectionKey.OP_ACCEPT, ioHandler);

步骤五：多路复用器在线程run方法的无限循环体内轮询准备就绪的Key。

	int num = selector.select();
	Set selectedKeys = selector.selectedKeys();
	Iterator it = selectedKeys.iterator();
	while (it.hasNext()) {
		SelectionKey key = (SelectionKey)it.next();
		// ... deal with I/O event ...
	}

步骤六：多路复用器监听到有新的客户端接入，处理新的接入请求，完成TCP三次握手，建立物理链路。
	
	SocketChannel channel = svrChannel.accept();
步骤七：设置客户端链路为非阻塞模式。

	channel.configureBlock(flase);
	channel.socket().setReuseAddress(true);
	...
步骤八：将新接入的客户端连接注册到Reactor线程的多路复用器上，监听读操作，用来读取客户端发送的网络信息。

	SelectionKey key = socketChannel.register(selector, SelectionKey.OP_READ, ioHandler);

步骤九：异步读取客户端请求消息到缓冲区

	int readNumber = channel.read(receivedBuffer);
步骤十：对ByteBuffer进行解编码，如果有半包消息指针rest，继续读取后续的报文，将解码成功的消息封装成Task，投递到业务线程池中，进行业务逻辑编排。

	Object message = null;
	whilte (buffer.hasRemain()) {
		byteBuffer.mark();
		object message = decode(byteBuffer);
		if (message == null) {
			byteBuffer.rest();
			break;
		}
		messageList.add(message);
	}
	if (!byteBuffer.hasRemain()){
		byteBuffer.clear();
	}
	else 
		byteBuffer.compact();
	if (messageList != null && !messageList.isEmpty()){
		for (Object message : messageList) {
			handletTask(message);
		}
	}
步骤十一：将POJO对象encode成ByteBuffer，调用SocketChannel的异步write接口，将消息异步发送给客户端

	socketChannel.write(buffer);

PS：如果发送区TCP缓冲区满，会导致写半包，此时，需要注册监听写操作位，循环写，直到整包消息写入TCP缓冲区。
### 2.3.3 NIO创建的TimeServer源码分析 ###

### 2.3.4 NIO客户端序列图 ###

## 2.5 4种I/O的对比 ##

#### 1.异步非阻塞I/O ####

#### 2.多路复用器Selector ####

#### 3.伪异步I/O ####

## 2.6 选择Netty的理由 ##

### 2.6.1 不选择Java原生NIO编程的原因 ###

### 2.6.2 为什么选择Netty ###

## 2.7 总结 ##






# Netty 入门与实战：仿写微信IM即时通讯系统 #

# ch1 仿微信IM系统简介 #

## 单聊流程 ##

1. 如上图，A 要和 B 聊天，首先 A 和 B 需要与服务器建立连接，然后进行一次登录流程，服务端保存用户标识和 TCP 连接的映射关系
2. A 发消息给 B，首先需要将带有 B 标识的消息数据包发送到服务器，然后服务器从消息数据包中拿到 B 的标识，找到对应的 B 的连接，将消息发送给 B
3. 任意一方发消息给对方，如果对方不在线，需要将消息缓存，对方上线之后再发送

我们把客户端与服务端之间相互通信的数据包称为**指令数据包**，指令数据包分为指令和数据，每一种指令对应客户端或者服务端的一种操作，数据部分对应的是指令处理需要的数据。

## 单聊的指令 ##

### 指令列表 ###

|指令内容|客户端|服务端|
|--|--|--|
|登录请求|发送|接收|
|登录响应|接收|发送|
|客户端发消息|发送|接收|
|服务端发消息|接收|发送|
|登出请求|发送|接收|
|登出响应|接收|发送|

## 群聊流程 ##

	Map<聊天室ID，List<用户标识>>

1. A，B，C 依然会经历登录流程，服务端保存用户标识对应的 TCP 连接
2. A 发起群聊的时候，将 A，B，C 的标识发送至服务端，服务端拿到之后建立一个群聊 ID，然后把这个 ID 与 A，B，C 的标识绑定
3. 群聊里面任意一方在群里聊天的时候，将群聊 ID 发送至服务端，服务端拿到群聊 ID 之后，取出对应的用户标识，遍历用户标识对应的 TCP 连接，就可以将消息发送至每一个群聊成员

#### 指令列表 ####

|指令内容|客户端|服务端|
|--|--|--|
|创建群聊请求|发送|接收|
|群聊创建成功通知|接收|发送|
|加入群聊请求|发送|接收|
|群聊加入通知|接收|发送|
|发送群聊消息|发送|接收|
|接收群聊消息|接收|发送|
|退出群聊请求|发送|接收|
|退出群聊通知|接收|发送|

## Netty ##

### 客户端使用 Netty 的程序逻辑结构 ###

1. 首先，客户端会解析控制台指令，比如发送消息或者建立群聊等指令
2. 然后，客户端会基于控制台的输入创建一个指令对象，用户告诉服务端具体要干什么事情
3. TCP 通信需要的数据格式为二进制，因此，接下来通过自定义二进制协议将指令对象封装成二进制，这一步称为协议的编码
4. 对于收到服务端的数据，首先需要截取出一段完整的二进制数据包（拆包粘包相关的内容后续小节会讲解）
5. 将此二进制数据包解析成指令对象，比如收到消息
6. 将指令对象送到对应的逻辑处理器来处理

# ch2 Netty是什么？ #

## IO编程 ##

1. 线程资源受限：线程是操作系统中非常宝贵的资源，同一时刻有大量的线程处于阻塞状态是非常严重的资源浪费，操作系统耗不起
2. 线程切换效率低下：单机 CPU 核数固定，线程爆炸之后操作系统频繁进行线程切换，应用性能急剧下降。
3. 除了以上两个问题，IO 编程中，我们看到数据读写是以字节流为单位。

## NIO编程 ##

#### 线程资源受限 ####

NIO编程模型，新来一个连接不再创建一个新的线程，而是可以把这条连接直接绑定到某个固定的线程，然后这条连接所有的读写都由这个线程来负责。

#### 线程切换效率低下 ####

### IO读写面向流 ###

**IO读写以字节为单位**

	 Set<SelectionKey> set = serverSelector.selectedKeys();

1. NIO模型中通常有两个县城，每个线程绑定一个轮询器selector，在我们这个例子中serverSelector负责轮询是否有新的连接，clientSelector负责轮询连接是否有数据可读
2. 服务端监测到新的连接之后，不再创建一个新的线程，而是直接将新连接绑定到clientSelector上，这样就不用 IO 模型中 1w 个 while 循环在死等，参见(1)
3. clientSelector被一个 while 死循环包裹着，如果在某一时刻有多条连接有数据可读，那么通过 clientSelector.select(1)方法可以轮询出来，进而批量处理，参见(2)
4. 数据的读写面向Buffer，参见（3）

## Netty编程 ##

1. `boss`对应`IOServer.java`中的接受新连接线程，主要负责创建新连接
2. `worker`对应`IOClient.java`中的负责读取数据的线程，主要用于读取数据以及业务逻辑处理

# ch3 Netty环境配置 #

略

# ch4 服务端启动流程 #

	public class NettyServer {
	    public static void main(String[] args) {
	        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
	        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
	
	        ServerBootstrap serverBootstrap = new ServerBootstrap();
	        serverBootstrap
	                .group(bossGroup, workerGroup)
	                .channel(NioServerSocketChannel.class)
	                .childHandler(new ChannelInitializer<NioSocketChannel>() {
	                    protected void initChannel(NioSocketChannel ch) {
	                    }
	                });
	
	        serverBootstrap.bind(8000);
	    }
	}

1. 创建两个NioEventLoopGroup，传统IO编程模型的两大线程组，`bossGroup`表示监听端口，accept新连接的线程组，`workerGroup`表示处理每一条连接的数据读写的线程组。
2. 创建一个引导类ServerBootstrap，这个类将引导我们进行服务端的启动工作，直接new出来。
3. .group(bossGroup, workerGroup)给引导类配置两大线程组，这个引导类的线程模型也就定型了。
4. 指定服务端的IO模型为*NIO*，.channel(NioServerSocketChannel.class)来指定IO模型，如果你想指定 IO 模型为 BIO，那么这里配置上OioServerSocketChannel.class类型即可，因为Netty的优势就在于NIO。
5. childHandler()方法，给这个引导类创建一个`ChannelInitializer`，定义后续每条连接的数据读写，业务处理逻辑。

ChannelInitializer这个类中，我们注意到有一个泛型参数NioSocketChannel，这个类呢，就是 Netty 对 NIO 类型的连接的抽象，而我们前面NioServerSocketChannel也是对 NIO 类型的连接的抽象，NioServerSocketChannel和NioSocketChannel的概念可以和 BIO 编程模型中的ServerSocket以及Socket两个概念对应上

BioServerSocketChannel和NioSocketChannel的概念可以和BIO编程模型种的ServerSocket以及Socket两个概念对应上

要启动一个Netty服务端，必须要指定三类属性，分别是线程模型、IO 模型、连接读写处理逻辑，有了这三者，之后在调用bind(8000)。public class NettyServer {
	    public static void main(String[] args) {
	        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
	        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
	
	        ServerBootstrap serverBootstrap = new ServerBootstrap();
	        serverBootstrap
	                .group(bossGroup, workerGroup)
	                .channel(NioServerSocketChannel.class)
	                .childHandler(new ChannelInitializer<NioSocketChannel>() {
	                    protected void initChannel(NioSocketChannel ch) {
	                    }
	                });
	
	        serverBootstrap.bind(8000);
	    }
	}## 自动绑定递增端口 ##

`serverBootstrap.bind(8000);`这个方法施一步方法，调用之后立即返回的，返回值是一个`ChannelFuture`，给这个ChannelFuture添加一个监听器`GenericFutureListener`，然后再`GenericFutureListener`和`operationComplete`方法里面，可以监听端口是否绑定成功

	serverBootstrap.bind(8000).addListener(new GenericFutureListener<Future<? super Void>>() {
	    public void operationComplete(Future<? super Void> future) {
	        if (future.isSuccess()) {
	            System.out.println("端口绑定成功!");
	        } else {
	            System.err.println("端口绑定失败!");
	        }
	    }
	});

	bind(serverBootstrap, 1000)


## 服务端启动其他方法 ##

#### handler()方法 ####

	serverBootstrap.handler(new ChannelInitializer<NioServerSocketChannel>() {
	    protected void initChannel(NioServerSocketChannel ch) {
	        System.out.println("服务端启动中");
	    }
	})

`handler()`方法呢，可以和`childHandler()`方法对应起来，`childHandler()`用于指定处理新连接数据的读写处理逻辑，`handler()`用于指定在服务端启动过程中的一些逻辑

#### attr()方法 ####

	serverBootstrap.attr(AttributeKey.newInstance("serverName"), "nettyServer")

attr()方法可以给服务端的 channel，也就是NioServerSocketChannel指定一些自定义属性，然后我们可以通过channel.attr()取出这个属性。

#### childAttr()方法 ####

	serverBootstrap.childAttr(AttributeKey.newInstance('clientKey', 'clientValue')

`childAttr`给每一条连接指定自定义属性，然后后续可以通过channel.attr()取出该属性。

#### childOption()方法 ####

	serverBootstrap
		.childOption(ChannelOption.SO_KEEPALIVE, true)
		.childOption(ChannelOption.TCP_NODELAY, true)

`childOption()`可以给每条连接设置一些TCP底层相关的属性。

* `ChannelOption.SO_KEEPALIVE`表示是否开启TCP底层心跳机制，true为开启
* `ChannelOption.TCP_NODELAY`表示是否开始Nagle算法，true表示关闭，false表示开启，如果要求高实时性，有数据发送时就马上发送，就关闭，如果需要减少发送次数减少网络交互，就开启

#### option()方法 ####

除了给每个连接设置这一系列属性之外，还可以给服务端channel设置一些属性，最常见就是so_backlog

	serverBootstrap.option(ChannelOption_SO_BACkLOG, 1024)

表示系统用于临时存放已完成三次握手的请求的队列的最大长度，如果连接建立频繁，服务器处理创建新连接较慢，可以适当调大这个参数。

### 总结 ###

* 本文中，首先学习了Netty服务端启动的流程，一句话来说就是：创建一个引导类，然后给他指定线程模型，IO模型，连接读写处理逻辑，绑定端口之后，服务端就启动起来了。
* 然后学习到bind方法是异步的，通过这个异步机制来实现端口递增绑定。
* 讨论了Netty服务端启动额外的参数，主要包括给服务端Channel或者客户端Channel设置属性值，设置底层TCP参数。

# ch5 客户端启动流程 #

## 客户端启动Demo ##

对于客户端的启动来说，和服务端的启动类似，依然需要线程模型、IO模型、以及IO业务处理逻辑三大参数。

客户端启动的引导类是 Bootstrap，负责启动客户端以及连接服务端，服务端的启动的时候，这个辅导类是 ServerBootstrap，引导类创建完成之后。

	public class NettyClient {
	    public static void main(String[] args) {
	        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
	        
	        Bootstrap bootstrap = new Bootstrap();
	        bootstrap
	                // 1.指定线程模型
	                .group(workerGroup)
	                // 2.指定 IO 类型为 NIO
	                .channel(NioSocketChannel.class)
	                // 3.IO 处理逻辑
	                .handler(new ChannelInitializer<SocketChannel>() {
	                    @Override
	                    public void initChannel(SocketChannel ch) {
	                    }
	                });
	        // 4.建立连接
	        bootstrap.connect("juejin.im", 80).addListener(future -> {
	            if (future.isSuccess()) {
	                System.out.println("连接成功!");
	            } else {
	                System.err.println("连接失败!");
	            }
	
	        });
	    }
	}

客户端启动的引导类是`Bootstrap`，负责启动客户端以及连接服务端，服务端启动的时候辅导类是`ServerBootstrap`

1. 创建客户端引导类`Bootstrap`,
2. 指定io类型为nio
3. io处理逻辑
4. 建立连接

————————

1. 首先，与服务端的启动一样，我们需要给它指定线程模型，驱动着连接的数据读写，这个线程的概念可以和 `IOClient.java` 创建的线程联系起来
2. 然后，我们指定 IO 模型为 `NioSocketChannel`，表示 IO 模型为 NIO，当然，你可以可以设置 IO 模型为 `OioSocketChannel`，但是通常不会这么做，因为 Netty 的优势在于 NIO
3. 接着，给引导类指定一个 `handler`，这里主要就是定义连接的业务处理逻辑
4. 配置完线程模型、IO 模型、业务处理逻辑之后，调用 `connect` 方法进行连接，可以看到 `connect` 方法有两个参数，第一个参数可以填写 IP 或者域名，第二个参数填写的是端口号，*由于 connect 方法返回的是一个 `Future`，也就是说这个方是异步的*，我们通过 `addListener` 方法可以监听到连接是否成功，进而打印出连接信息

## 失败重连 ##

尝试重新连接，重新连接的逻辑写在连接失败的逻辑块里

	bootstrap.connect("juejin.im", 80).addListener(future -> {
	    if (future.isSuccess()) {
	        System.out.println("连接成功!");
	    } else {
	        System.err.println("连接失败!");
	        // 重新连接
	    }
	});

把建立连接的逻辑先抽取出来，然后在重连失败的时候，递归调用自身

	private static void connect(Bootstrap bootstrap, String host, int port) {
	    bootstrap.connect(host, port).addListener(future -> {
	        if (future.isSuccess()) {
	            System.out.println("连接成功!");
	        } else {
	            System.err.println("连接失败，开始重连");
	            connect(bootstrap, host, port);
	        }
	    });
	}



	connect(bootstrap, "juejin.im", 80, MAX_RETRY);
	
	private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
	    bootstrap.connect(host, port).addListener(future -> {
	        if (future.isSuccess()) {
	            System.out.println("连接成功!");
	        } else if (retry == 0) {
	            System.err.println("重试次数已用完，放弃连接！");
	        } else {
	            // 第几次重连
	            int order = (MAX_RETRY - retry) + 1;
	            // 本次重连的间隔
	            int delay = 1 << order;
	            System.err.println(new Date() + ": 连接失败，第" + order + "次重连……");
	            bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit
	                    .SECONDS);
	        }
	    });
	}

1. 如果连接成功则打印连接成功的消息
2. 如果连接失败但是重试次数已经用完，放弃连接
3. 如果连接失败但是重试次数仍然没有用完，则计算下一次重连间隔 `delay`，然后定期重连

我们定时任务是调用 `bootstrap.config().group().schedule()`, 其中 bootstrap.config() 这个方法返回的是 BootstrapConfig，他是对 Bootstrap 配置参数的抽象，然后 bootstrap.config().group() 返回的就是我们在一开始的时候配置的线程模型 workerGroup，调 workerGroup 的 schedule 方法即可实现定时任务逻辑。

## 客户端启动其他方法 ##

#### attr()方法 ####

	bootstrap.attr(AttributeKey.newInstant"clientName"), "nettyClient");

`attr()`方法可以给客户端Channel，也即是说`NioSocketChannel`绑定自定义属性，然后通过`channel.attr()`取出这个属性，比如，上面的代码我们指定我们客户端Channel的一个`clientName`属性，属性值为`nettyClient`，`NioSocketChannel`维护一个map，后续再`NioSocketChannel`通过参数传来传去的时候，通过`NioSocketChannel`来取出设置的属性。

#### option()方法 ####
	
	Bootstrap
	        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
	        .option(ChannelOption.SO_KEEPALIVE, true)
	        .option(ChannelOption.TCP_NODELAY, true)

`option()`方法可以给连接设置一些TCP底层相关的属性。

* ChannelOption.CONNECT_TIMEOUT_MILLIS 表示连接的超时时间，超过这个时间还是建立不上的话则代表连接失败
* CHannelOption.SO_KEEPALIVE 表示是否开启TCP底层心跳机制，true为开启
* ChannelOption.TCP_NODELAY 表示是否开始Nagle算法，true表示关闭，false表示开启，通俗地说，如果要求高实时性，有数据发送就马上发送，就设置true关闭，如果需要减少发送次数，减少网络交互，就设置false开启。

### 总结 ###

* 创建一个引导类，然后给他指定线程模型，IO模型，连接读写处理逻辑，连接上特定主机和端口，客户端就启动起来了。
* `connect`方法是异步的，通过这个异步回调机制来实现指数退避重连逻辑。
* 客户端`Channel`绑定自定义属性值，设置底层TCP参数。

### 思考题 ###

与服务端启动相比，客户端启动的引导类少了哪些方法，为什么不需要这些方法？欢迎留言讨论

# ch6 实战：客户端与服务端双向通信 #

## 客户端发数据到服务端 ##

	public class FirstClientHandler extends ChannelInboundHandlerAdapter {
	    @Override
	    public void channelActive(ChannelHandlerContext ctx) {
	        System.out.println(new Date() + ": 客户端写出数据");
	
	        // 1. 获取数据
	        ByteBuf buffer = getByteBuf(ctx);
	
	        // 2. 写数据
	        ctx.channel().writeAndFlush(buffer);
	    }
	
	    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
	        // 1. 获取二进制抽象 ByteBuf
	        ByteBuf buffer = ctx.alloc().buffer();
	        
	        // 2. 准备数据，指定字符串的字符集为 utf-8
	        byte[] bytes = "你好，闪电侠!".getBytes(Charset.forName("utf-8"));
	
	        // 3. 填充数据到 ByteBuf
	        buffer.writeBytes(bytes);
	
	        return buffer;
	    }
	}

客户端相关的数据读写逻辑是通过 `Bootstrap` 的 `handler()` 方法指定，我们在 `initChannel()` 方法里面给客户端添加一个逻辑处理器，这个处理器的作用就是负责向服务端写数据。

1. `ch.pipeline()`返回的是和这条连接相关的逻辑处理链，采用了责任链模式。
2. 然后再调用addLast()方法添加一个逻辑处理器，这个逻辑处理器为的就是再客户端建立成功之后，向服务端写数据。

## 服务端读取客户端数据 ##



## 服务端回数据给客户端 ##

服务端向客户端写数据逻辑与客户端侧的写数据逻辑一样，先创建一个 ByteBuf，然后填充二进制数据，最后调用 writeAndFlush() 方法写出去。

	public class FirstServerHandler extends ChannelInboundHandlerAdapter {
	
	    @Override
	    public void channelRead(ChannelHandlerContext ctx, Object msg) {
	        // ... 收数据逻辑省略
	
	        // 回复数据到客户端
	        System.out.println(new Date() + ": 服务端写出数据");
	        ByteBuf out = getByteBuf(ctx);
	        ctx.channel().writeAndFlush(out);
	    }
	
	    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
	        byte[] bytes = "你好，欢迎关注我的微信公众号，《闪电侠的博客》!".getBytes(Charset.forName("utf-8"));
	
	        ByteBuf buffer = ctx.alloc().buffer();
	
	        buffer.writeBytes(bytes);
	
	        return buffer;
	    }
	}



## 总结 ##

1. 通过给逻辑处理链 `pipeline` 添加逻辑处理器，来编写数据的读写逻辑
2. 客户端连接成功之后会回调到逻辑处理器的` channelActive()` 方法，而不管是服务端还是客户端，收到数据后都会调用 `channelRead` 方法。
3. 写数据调用 `writeAndFlush` 方法，客户端与服务端交互的二进制数据载体为 `ByteBuf`，`ByteBuf` 通过连接的内存管理器创建，字节数据填充到 `ByteBuf` 之后才能写到对端。

## 思考题 ##

如何实现新连接接入的时候，服务端主动向客户端推送消息，客户端回复服务端消息？欢迎留言讨论。

## ch7 数据传输载体ByteBuf介绍 ##

### ByteBuf结构 ###

1. ByteBuf是一个字节容器，容器里面的数据分为三个部分。
2. 以上三段内容是被两个指针给划分出来的，从左到右，依次是读指针（readerIndex）、写指针（writerIndex），然后还有一个变量capacity，表示ByteBuf底层内存的总容量
3. 从ByteBuf中每读取一个字节，readIndex自增1，ByteBuf 里面总共有 writerIndex-readerIndex 个字节可读, 由此可以推论出当 readerIndex 与 writerIndex 相等的时候，ByteBuf 不可读
4. 写数据是从 writerIndex 指向的部分开始写，每写一个字节，writerIndex 自增1，直到增到 capacity，这个时候，表示 ByteBuf 已经不可写了
5. ByteBuf 里面其实还有一个参数 maxCapacity，当向 ByteBuf 写数据的时候，如果容量不足，那么这个时候可以进行扩容，直到 capacity 扩容到 maxCapacity，超过 maxCapacity 就会报错

废弃字节、可读字节和可写字节

### 容量API ###

* capacity()

表示ByteBuf底层占用了多少字节的内存（包括丢弃的字节、可读字节、可写字节），不同的底层实现机制有不同的计算方式。

* maxCapacity()

表示ByteBuf底层最大能够占用多少字节的内存，当向ByteBuf种写数据的时候，如果发现容量不足，则进行扩容，直到扩容到maxCapacity，超过这个数，就抛异常

* readableBytes()与isReadable()

readableBytes()表示ByteBuf当前可读的字节数，它的值等于writeIndex-readerIndex，如果两者相等，则不可读，isReadable()方法返回false

* writableBytes()、isWritable()与maxWritableBytes()

writeableBytes()表示ByteBuf当前可写的字节数，它的值等于capacity-writeIndex，如果两者相等，则表示不可写，isWritable()方法false，但是这个时候，并不代表不能往ByteBuf重写数据了，如果发现往ByteBuf重写数据写不进去的话，Netty会自动扩容ByteBuf，直到扩容到底层的内存大小为maxCapacity，而maxWritableBytes()就表示可写的最大字节数，它的值等于maxCapacity-writeIndex

### 读写指针相关的API ###

	readerIndex()与readerIndex(int)

前者表示返回当前的读指针readerIndex，后者表示设置读指针。

	writeIndex()与writeIndex(int)

前者表示返回当前的写指针writeIndex，后者表示设置写指针。

	markReaderIndex()与resetReaderIndex()
	
前者表示把当前的读指针保存起来，后者表示把当前的读指针恢复到之前保存的值。

	// 代码片段1
	int readerIndex = buffer.readerIndex();
	// .. 其他操作
	buffer.readerIndex(readerIndex);
	
	
	// 代码片段二
	buffer.markReaderIndex();
	// .. 其他操作
	buffer.resetReaderIndex();



	markWriterIndex() 与 resetWriterIndex()

### 读写API ###

关于ByteBuf的读写都可以看作从指针开始的地方开始读写数据

	writeBytes(byte[] src)与buffer.readBytes(byte[] dst)

writeBytes()表示把字节数组src里面的数据全部写到ByteBuf，而readBytes()指的是把ByteBuf里面的数据全部读取到dst，这里dst字节数组的大小通常等于readableBytes()，而src字节数组大小的长度通常小于等于writableBytes()

	wrtieByte(byte b)与buffer.readByte()

writeByte()表示往ByteBuf中写一个字节，而buffer.readByte()表示从ByteBuf中读取一个字节，类似的API还有writeBoolean()、writeChar()、writeShort()、writeInt()、writeLong()、writeFloat()、writeDouble()与readBoolean()、readChar()、readShort()、readInt()、readLong()、readFloat()、readDouble()

读写 API 类似的 API 还有 getBytes、getByte() 与 setBytes()、setByte() 系列，唯一的区别就是 get/set 不会改变读写指针，而 read/write 会改变读写指针。

	release()与retain()

由于Netty使用了堆外内存，而堆外内存是不被jvm直接管理的，也就是说申请到的内存无法被垃圾回收器直接回收，所以需要我们手动回收。有点类似于c语言里面，申请到的内存必须手工释放，否则会造成内存泄漏。

Netty的ByteBuf是通过引用技术的方式来管理的，如果一个 ByteBuf 没有地方被引用到，需要回收底层内存。

* retain 加一
* release 减一

减完之后如果发现引用计数为0，则直接回收 ByteBuf 底层的内存。

	slice()、duplicate()、copy()

1. slice()方法从原始ByteBuf中截取一段，这段数据是从readerIndex到writeIndex，同时，返回的新的ByteBuf的最大容量maxCapacity为原始ByteBuf的readableBytes()
2. duplicate() 方法把整个 ByteBuf 都截取出来，包括所有的数据，指针信息
3. slice()方法与duplicate()方法的相同点是：底层内存以及引用次数与原始的ByteBuf共享，也就是说经过slice()或者duplicate()方法的相同点是：
4. slice()方法与duplicate()不同点就是：slice()只截取从readerIndex到writerIndex之间的数据，它返回的ByteBuf的最大容量被限制到原始ByteBuf的readableBytes()
5. slice() 方法与 duplicate() 方法不会拷贝数据，它们只是通过改变读写指针来改变读写的行为，而最后一个方法 copy() 会直接从原始的 ByteBuf 中拷贝所有的信息，包括读写指针以及底层对应的数据，因此，往 copy() 返回的 ByteBuf 中写数据不会影响到原始的 ByteBuf。
6. slice() 和 duplicate() 不会改变 ByteBuf 的引用计数，所以原始的 ByteBuf 调用 release() 之后发现引用计数为零，就开始释放内存，调用这两个方法返回的 ByteBuf 也会被释放，这个时候如果再对它们进行读写，就会报错。因此，我们可以通过调用一次 retain() 方法 来增加引用，表示它们对应的底层的内存多了一次引用，引用计数为2，在释放内存的时候，需要调用两次 release() 方法，将引用计数降到零，才会释放内存。
7. 这三个方法均维护着自己的读写指针，与原始的 ByteBuf 的读写指针无关，相互之间不受影响。

	retainedSlice()与retainedDuplicate()

相信读者应该已经猜到这两个 API 的作用了，它们的作用是在截取内存片段的同时，增加内存的引用计数，分别与下面两段代码等价

	// retainedSlice 等价于
	slice().retain();
	
	// retainedDuplicate() 等价于
	duplicate().retain()

使用到 slice 和 duplicate 方法的时候，千万要理清内存共享，引用计数共享，读写指针不共享几个概念

**1. 多次释放**

	Buffer buffer = xxx;
	doWith(buffer);
	// 一次释放
	buffer.release();
	
	
	public void doWith(Bytebuf buffer) {
	// ...    
	    
	// 没有增加引用计数
	Buffer slice = buffer.slice();
	
	foo(slice);
	
	}
	
	
	public void foo(ByteBuf buffer) {
	    // read from buffer
	    
	    // 重复释放
	    buffer.release();
	}

这里的 *doWith* 有的时候是用户自定义的方法，有的时候是 Netty 的回调方法，比如 *channelRead()* 等等

**2. 不释放造成内存泄漏**

	Buffer buffer = xxx;
	doWith(buffer);
	// 引用计数为2，调用 release 方法之后，引用计数为1，无法释放内存 
	buffer.release();
	
	
	public void doWith(Bytebuf buffer) {
	// ...    
	    
	// 增加引用计数
	Buffer slice = buffer.retainedSlice();
	
	foo(slice);
	
	// 没有调用 release
	
	}
	
	
	public void foo(ByteBuf buffer) {
	    // read from buffer
	}

在一个函数体里面，只要增加了引用计数（包括 `ByteBuf` 的创建和手动调用 `retain()` 方法），就必须调用 `release()` 方法

### 实战 ###

	public class ByteBufTest {
	    public static void main(String[] args) {
	        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(9, 100);
	
	        print("allocate ByteBuf(9, 100)", buffer);
	
	        // write 方法改变写指针，写完之后写指针未到 capacity 的时候，buffer 仍然可写
	        buffer.writeBytes(new byte[]{1, 2, 3, 4});
	        print("writeBytes(1,2,3,4)", buffer);
	
	        // write 方法改变写指针，写完之后写指针未到 capacity 的时候，buffer 仍然可写, 写完 int 类型之后，写指针增加4
	        buffer.writeInt(12);
	        print("writeInt(12)", buffer);
	
	        // write 方法改变写指针, 写完之后写指针等于 capacity 的时候，buffer 不可写
	        buffer.writeBytes(new byte[]{5});
	        print("writeBytes(5)", buffer);
	
	        // write 方法改变写指针，写的时候发现 buffer 不可写则开始扩容，扩容之后 capacity 随即改变
	        buffer.writeBytes(new byte[]{6});
	        print("writeBytes(6)", buffer);
	
	        // get 方法不改变读写指针
	        System.out.println("getByte(3) return: " + buffer.getByte(3));
	        System.out.println("getShort(3) return: " + buffer.getShort(3));
	        System.out.println("getInt(3) return: " + buffer.getInt(3));
	        print("getByte()", buffer);
	
	
	        // set 方法不改变读写指针
	        buffer.setByte(buffer.readableBytes() + 1, 0);
	        print("setByte()", buffer);
	
	        // read 方法改变读指针
	        byte[] dst = new byte[buffer.readableBytes()];
	        buffer.readBytes(dst);
	        print("readBytes(" + dst.length + ")", buffer);
	
	    }
	
	    private static void print(String action, ByteBuf buffer) {
	        System.out.println("after ===========" + action + "============");
	        System.out.println("capacity(): " + buffer.capacity());
	        System.out.println("maxCapacity(): " + buffer.maxCapacity());
	        System.out.println("readerIndex(): " + buffer.readerIndex());
	        System.out.println("readableBytes(): " + buffer.readableBytes());
	        System.out.println("isReadable(): " + buffer.isReadable());
	        System.out.println("writerIndex(): " + buffer.writerIndex());
	        System.out.println("writableBytes(): " + buffer.writableBytes());
	        System.out.println("isWritable(): " + buffer.isWritable());
	        System.out.println("maxWritableBytes(): " + buffer.maxWritableBytes());
	        System.out.println();
	    }
	}


### 总结 ###

1. Netty对二进制数据的抽象ByteBuf的结构，本质上它的原理就是，引用了一段内存，这段内存可以是堆内也可以是堆外，然后用引用计数来控制这段内存是否需要被释放，使用读写指针来控制对ByteBuf的读写，可以理解为是外观模式的一种使用。
2. 基于读写指针和容量、最大可扩充容量，衍生出一系列的读写方法，要注意read/write与get/set区别。
3. 多个ByteBuf可以引用同一段内存，通过引用计数来控制内存的释放，遵循谁retain()谁release()的原则。
4. 具体的例子说明ByteBuf的实际使用

## 思考 ##

slice 方法可能用在什么场景？欢迎留言讨论。

## ch8 客户端与服务端通信协议编解码 ##

### 什么是服务端与客户端的通信协议 ###

1. 首先，客户端把一个Java对象按照通信协议转换成二进制数据包。
2. 然后通过网络，把这段二进制数据包发送到服务端，数据的传输过程由TCP/IP协议负责数据的传输，与我们的应用层无关。
3. 服务端受到数据之后，按照协议取出二进制数据包中的相应字段，包装成Java对象，交给应用逻辑处理。
4. 服务端处理完之后，如果需要吐出响应给客户端，那么按照相同的流程进行。

### 通信协议的设计 ###

![1653028b36ee5d81.jpg](img/1653028b36ee5d81.jpg)

1. 魔数，固定的几个字节（通常是4个），首先取出前面四个字节进行比对，能够在第一时间识别出这个数据包并非遵循自定义协议，也就是无效数据包
2. 版本号
3. 序列化算法表示如何把Java对象转换二进制数据以及二进制数据如何转换回Java对象，比如Java自带的序列化，json，hessian等序列化方式
4. 字段表示指令，服务端或者客户端每收到一种指令都会又相应的处理逻辑，这里，我们用一个字节来表示，最高支持256种指令。
5. 数据部分长度，占四个字节
6. 数据内容，每一种指令对应的数据是不一样的。

### 通信协议的实现 ###

把 Java 对象根据协议封装成二进制数据包的过程成为编码，而把从二进制数据包中解析出 Java 对象的过程成为解码。

#### Java对象 ####

	@Data
	public abstract class Packet {
	    /**
	     * 协议版本
	     */
	    private Byte version = 1;
	
	    /**
	    * 指令
	    * /
	    public abstract Byte getCommand();
	}

以上是通信过程中 Java 对象的抽象类，可以看到，我们定义了一个版本号（默认值为 1 ）以及一个获取指令的抽象方法，所有的指令数据包都必须实现这个方法，这样我们就可以知道某种指令的含义。

	public interface Command {
	
	    Byte LOGIN_REQUEST = 1;
	}
	
	@Data
	public class LoginRequestPacket extends Packet {
	    private Integer userId;
	
	    private String username;
	
	    private String password;
	
	    @Override
	    public Byte getCommand() {
	        
	        return LOGIN_REQUEST;
	    }
	}

登录请求数据包继承自 `Packet`，然后定义了三个字段，分别是用户 ID，用户名，密码，这里最为重要的就是覆盖了父类的 `getCommand()` 方法，值为常量 `LOGIN_REQUEST`。

#### 序列化 ####

	public interface Serializer {
	
	    /**
	     * 序列化算法
	     */
	    byte getSerializerAlgorithm();
	    
	    /**
	     * java 对象转换成二进制
	     */
	    byte[] serialize(Object object);
	
	    /**
	     * 二进制转换成 java 对象
	     */
	    <T> T deserialize(Class<T> clazz, byte[] bytes);
	}

序列化接口有三个方法，`getSerializerAlgorithm()` 获取具体的序列化算法标识，`serialize()` 将 Java 对象转换成字节数组，`deserialize()` 将字节数组转换成某种类型的 Java 对象

	public interface SerializerAlgorithm {
	    /**
	     * json 序列化标识
	     */
	    byte JSON = 1;
	}
	
	
	public class JSONSerializer implements Serializer {
	   
	    @Override
	    public byte getSerializerAlgorithm() {
	        
	        return SerializerAlgorithm.JSON;
	    } 
	
	    @Override
	    public byte[] serialize(Object object) {
	        
	        return JSON.toJSONBytes(object);
	    }
	
	    @Override
	    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
	        
	        return JSON.parseObject(bytes, clazz);
	    }
	}

序列化算法的类型以及默认序列化算法

	public interface Serializer {
	    /**
	     * json 序列化
	     */
	    byte JSON_SERIALIZER = 1;
	
	    Serializer DEFAULT = new JSONSerializer();
	
	    // ...
	}

我们就实现了序列化相关的逻辑，如果想要实现其他序列化算法的话，只需要继承一下 `Serializer`，然后定义一下序列化算法的标识，再覆盖一下两个方法即可。

	PacketCodeC.java

	private static final int MAGIC_NUMBER = 0x12345678;
	
	public ByteBuf encode(Packet packet) {
	    // 1. 创建 ByteBuf 对象
	    ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
	    // 2. 序列化 Java 对象
	    byte[] bytes = Serializer.DEFAULT.serialize(packet);
	
	    // 3. 实际编码过程
	    byteBuf.writeInt(MAGIC_NUMBER);
	    byteBuf.writeByte(packet.getVersion());
	    byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
	    byteBuf.writeByte(packet.getCommand());
	    byteBuf.writeInt(bytes.length);
	    byteBuf.writeBytes(bytes);
	
	    return byteBuf;
	}

1. 创建一个ByteBuf，调用Netty的ByteBuf分配器来创建，`ioBuffer()`方法来返回适配io读写相关的内存，尽可能创建一个直接内存，直接内存可以理解为不受jvm堆管理的内存空间，写到IO缓存区的效果更高。
2. 将Java对象序列化成二进制数据包
3. 对照本小节开头协议的设计以及`ByteBuf`的API，逐个往`ByteBuf`写入字段，即实现了编码过程。

Netty会将此ByteBuf写到另外一端，另外一端拿到的也是一个ByteBuf对象，基于这个ByteBuf对象，就可以反解出在对端创建的Java对象，这个过程称作为解码。

	PacketCodeC.java
	
	public Packet decode(ByteBuf byteBuf) {
	    // 跳过 magic number
	    byteBuf.skipBytes(4);
	
	    // 跳过版本号
	    byteBuf.skipBytes(1);
	
	    // 序列化算法标识
	    byte serializeAlgorithm = byteBuf.readByte();
	
	    // 指令
	    byte command = byteBuf.readByte();
	
	    // 数据包长度
	    int length = byteBuf.readInt();
	
	    byte[] bytes = new byte[length];
	    byteBuf.readBytes(bytes);
	
	    Class<? extends Packet> requestType = getRequestType(command);
	    Serializer serializer = getSerializer(serializeAlgorithm);
	
	    if (requestType != null && serializer != null) {
	        return serializer.deserialize(requestType, bytes);
	    }
	
	    return null;
	}

解码流程

1. 假定`decode`方法传递进来的`ByteBuf`已经是合法的，即首四个字节是定义的魔数`0x12345678`，调用`skipBytes`跳过这四个字节。
2. 通常在没有遇到协议升级的时候，这个字段暂时不处理
3. 调用`ByteBuf`的API分别拿到序列化算法标识、指令、数据包的长度。
4. 根据拿到的数据包的长度取出数据，通过指令拿到该数据包对应的Java对象的类型，根据序列化算法标识拿到序列化对象，将字节数组转换为Java对象。

### 总结 ###

1. 通信协议是为了服务端与客户端交互，双方协商出来的满足一定规则的二进制数据格式。
2. 介绍了一种通用的通信协议的设计，包括魔数、版本号、序列化算法标识、指令、数据长度、数据几个字段，该协议能够满足绝大多数的通信场景。
3. Java对象以及序列化，目的就是实现Java对象与二进制数据的互转。
4. 依照设计的协议以及ByteBuf的API实现了通信协议，这个过程称为编解码过程。

### 思考题 ###

1. 除了json序列化之外，还有哪些序列化方式？如何实现
2. 序列化和编码都是把 Java 对象封装成二进制数据的过程，这两者有什么区别和联系？

# ch9 实战：实现客户端登录 #

## 登录流程 ##

![1653028b36ee5d81](img/1653028b36ee5d81.jpg)

1. 客户端会构建一个登录请求对象，然后通过编码把请求对象编码为ByteBuf，写到服务端
2. 服务端接受到ByteBuf之后，首先通过解码把ByteBuf解码为登录请求响应，然后进行校验
3. 服务端校验通过之后，构造一个登录响应对象，然后经过编码，然后再写回到客户端
4. 客户端接收到服务端之后，解码ByteBuf，拿到登录响应响应，判断是否登录成功

## 逻辑处理器 ##

	public class ClientHandler extends ChannelInboundHandlerAdapter {
	}

	bootstrap.handler(new ChannelInitializer<SocketChannel>() {
	            @Override
	            public void initChannel(SocketChannel ch) {
	                ch.pipeline().addLast(new ClientHandler());
	            }
	        });

在客户端侧，Netty 中 IO 事件相关的回调就能够回调到我们的 `ClientHandler`。

## 客户端发送登录请求 ##

## 服务端发送登录相应 ##

#### 服务端处理登录响应 ####

	LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
	loginResponsePacket.setVersion(packet.getVersion());
	if (valid(loginRequestPacket)) {
	    loginResponsePacket.setSuccess(true);
	} else {
	    loginResponsePacket.setReason("账号密码校验失败");
	    loginResponsePacket.setSuccess(false);
	}
	// 编码
	ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginResponsePacket);
	ctx.channel().writeAndFlush(responseByteBuf);

服务端逻辑处理器 `ServerHandler` 的 `channelRead()` 方法里，我们构造一个登录响应包 `LoginResponsePacket`，然后在校验成功和失败的时候分别设置标志位，接下来，调用编码器把 Java 对象编码成 `ByteBuf`，调用 `writeAndFlush()` 写到客户端，至此，服务端的登录逻辑编写完成

#### 客户端处理登录响应 ####

客户端接收服务端数据的处理逻辑也是在 `ClientHandler` 的 `channelRead()` 方法

	public void channelRead(ChannelHandlerContext ctx, Object msg) {
	    ByteBuf byteBuf = (ByteBuf) msg;
	
	    Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);
	
	    if (packet instanceof LoginResponsePacket) {
	        LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;
	
	        if (loginResponsePacket.isSuccess()) {
	            System.out.println(new Date() + ": 客户端登录成功");
	        } else {
	            System.out.println(new Date() + ": 客户端登录失败，原因：" + loginResponsePacket.getReason());
	        }
	    }
	}

客户端拿到数据之后，调用 `PacketCodeC` 进行解码操作。

## 总结 ##

梳理了一下客户端登录的基本流程，然后结合上一小节的编解码逻辑，我们使用 Netty 实现了完整的客户端登录流程。

## 思考 ##

客户端登录成功或者失败之后，如果把成功或者失败的标识绑定在客户端的连接上？服务端又是如何高效避免客户端重新登录？ 欢迎留言讨论。

# ch10 实战：实现客户端与服务端收发消息 #

## 收发消息对象 ##

## 判断客户都安是否登陆成功 ##

## 控制台输入消息并发送 ##

## 服务端收发消息处理 ##

## 客户端收消息处理 ##

## 控制台输出 ##

## 总结 ##

## 思考 ##

# ch11 pipeline与channelHandler #

客户端写在`ClientHandler`，服务端写在`ServerHandler`。

Netty中的pipeline和channelHandler通过责任链设计模式组织代码逻辑，并且能够支持逻辑的动态添加和删除。

每次发指令数据包都要手动调用编码器编码成 `ByteBuf`，把他们放在一个类中进行模块化处理，使用链式解决。

## pipeline与channelHandler的构成 ##

在Netty整个框架里面，一条连接对应着一个Channel，这条Channel所有的处理逻辑都在一个叫做`ChannelPipeline`的对象里面，`ChannelPipeline`是一个双向链表结构，他和Channel之间是一对一的关系。

`ChannelPipeline`里面每个节点都是一个`ChannelHandlerContext`对象，这个对象能够拿到Channel相关的所有上下文信息，然后这个对象包着一个重要的对象，那就是逻辑处理器`ChannelHandler`

## channelHandler的分类 ##

1. 第一个子接口是`ChannelInboundHandler`是处理读数据的逻辑。最重要的方法是`channelRead()`。将 `ChannelInboundHandler` 的逻辑处理过程与 TCP 的七层协议的解析联系起来，收到的数据一层层从物理层上升到我们的应用层。
2. 第二个子接口是`ChannelOutboundHandler`是处理写数据的逻辑。最重要的方法是`write()`。将 `ChannelOutBoundHandler` 的逻辑处理过程与 TCP 的七层协议的解析联系起来，收到的数据一层层从物理层上升到我们的应用层。

对应的默认实现，`ChannelInboundHandlerAdapter`和`ChanneloutBoundHandlerAdapter`，分别实现了两大接口的所有功能，默认情况会把读写事件传播到下一个handler。

### ChannelInboundHandler的事件传播 ###

在服务端的pipeline添加三个`ChannelInboundHandler`。

每个inBoundHandler都继承自`ChannelInboundHandlerAdapter`，然后实现了channelRead()方法。

在channelRead()方法里面，打印当前handler的信息，然后调用弗雷的`channelRead()`方法，而这里父类的`channelRead()`方法会自动调用到下一个inBoundhandler的`channelRead()`方法，并且会把当前inBoundHandler里处理完毕的对象 传递到下一个inBoundHandler。

addLast()方法来为pipeline添加inBoundHandler，A->B->C

### ChannelOutboundHandler的事件传播 ###

ChanneloutBoundHandler -> write()

两种类型的handler在一个双向链表离，但是这两类handler分工是不一一样的，inBoundHandler的事件通常只会传播到下一个inBoundHandler，outBoundHandler的事件通常只会传播到下一个outBoundHandler，两者相互不受干扰。

可以使用这几种特殊的 channelHandler 来改造我们的客户端和服务端逻辑，解决掉 if else 泛滥的问题

### 总结 ###

1. 引出pipeline和channelHandler的概念 #TODO
2. channelHandler分为inBound和outBound两种类型的接口，分别是处理数据读和数据写的逻辑，可与tcp协议栈联系起来。
3. 两种类型的handler均有相应的默认实现，默认会把事件传递到下一个，这里的传递时间其实说
4. inBoundHandler的执行顺序与实际添加的孙婿相同，而outBoundHandler则相反。

# ch12 实战：构建客户端与服务端pipeline #

## ChannelInboundHandlerAdapter 与 ChannelOutboundHandlerAdapter ##

1. `ChannelInboundHandlerAdapter`,这个适配器主要实现其接口 `ChannelInboundHandler`的所有方法

## ByteToMessageDecoder ##

1. 把二进制转换成一个Java对象。

## SimpleChannelInboundHandler ##

## 构建客户端与服务端pipeline ##

### 总结 ###

## ch13 实战：拆包粘包理论与解决方案 ##

## ch14 channelHandler的生命周期 ##

## 小册总结 ##

### 1. Netty是什么？ ###

### 2. 服务端和客户端启动 ###

### 3. ByteBuf ###

## 小册读者总结 ##

## 扩展：进阶学习Netty的方向与资料 ##

### 1. 官网与github ###

### 2. 源码解析博客 ###


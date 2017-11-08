# Java 网络工程 #

## ch1 基本网络概念 ##

### 网络 ###

网络中的每台机器称为一个节点（node）。

每个网络节点都有地址（address），这是用于唯一标识节点的一个字节序列。

所有现代计算机网络都是包交换（分组交换）网络：流经网络的数据分割成小块，称为包（packet，也称分组），每个包都单独加以处理。每个包都包含了由谁发送和将发往何处的信息。将数组分成单独的带有地址的包，器最大的

协议（protocol）是定义计算机如何通信的一组明确的规则：包括地址格式、数据如何分包等。针对网络通信的不同方面，定义有很多不同的协议。

### 网络的分层 ###

当Web浏览器向Web服务器发送获取网页的请求时，浏览器实际上只与本地客户机的传输层对话。传输层将请求分解为TCP片，想数据添加序列号和校验和，然后将请求传递给本地网际层。网际层根据本地网络所需的大小将各TCP片分成IP数据报，并传递到主机网络层以便通过线缆传输数据。

### 主机网络层 ###

### 网际层 ###

网络的下一层就是网际层（internet layer）。网络层协议定义了数据位和字节如何组织为更大的分组，称为包，还定义了寻址机制。

网际协议（IP）是世界上使用最广泛的网络层协议。

### 传输层 ###

传输层（transport layer）负责确保各包以发送的顺序接收，并保证没有数据丢失和破坏。如果丢包，传输层会请求发送方重传这个包。为实现这个目标，IP网络会给每个数据报添加一个附加首部，其中包含有更多信息。这一层上主要有两个协议。

1. 第一个是传输控制协议（Transmission Control Protocol，TCP），这是开销很高的协议，支持对丢失或破坏的数据进行重传，并按照发送时的顺序进行传送。
2. 第二个协议是用户数据报协议（User Datagram Protocol，UDP），它允许接受仿检测被破坏的包，但不保证这些包以正确的顺序传送（或者包有可能根本未传送）。
3. UDP通常比TCP快。TCP称为可靠的（reliable）协议；UDP是不可靠的（unreliable）协议。


### 应用层 ###

向用户传送数据的层称为应用层（application layer）。它下面的三层共同定义了数据如何从一台计算机传输到另一台计算机。应用层确定了数据传输后的操作。

### IP、TCP和UDP ###

1. IP设计为允许任意两点之间有多个路由，可以绕过被破坏的路由器实现数据包的路由。
2. IP必须是开放，与平台无关
3. 将TCP置于IP之上，使连接的两端能够确认接收的IP包，以及请求重传丢失或被破坏的包。此外，TCP允许接收端的包按发送时的顺序重新组合在一起。



### IP地址和域名 ###


## ch2 流 ##

Java的I/O建立于流（Stream）之上。输入流读取数据；输出流写入数据。

过滤器（filter）流可以串联到输入流或输出流上。读/写数据是，过滤器可以修改数据（例如，通过加密或压缩），或者只是提供额外的方法，将读/写的数据转换为其他格式。

阅读器（reader）和书写器（writer）可以串联到输入流和输出流上，允许程序读/写文本（即字符）而不是字节。

流是同步的。当程序（确切地讲是线程）请求一个流读/写一段数据时，在做任何其他操作前，它要等待所读/写的数据。Java还支持使用通道和缓冲区的非阻塞I/O。

### 输出流 ###

Java的基本输出流雷是java.io.OutputStream:

	public abstract class OutputStream

基本方法

	public abstract void write(int b) throws IOException
	public void write(byte[] data) throws IOException
	public void write(byte[] data, int offset, int length) throws IOException
	public void flush() throws IOException
	public void close() throws IOException

OutputStream的基本方法是write(int b)，这个方法接受一个0到225之间的整数作为参数，讲对应的字节写入到输出流中。

### 输入流 ###

Java的基本输入类是java.io.InputStream:

	public abstract class InputStream

基本方法

	public abstract void read() throws IOException
	public void read(byte[] input) throws IOException
	public void read(byte[] input, int offset, int length) throws IOException
	public long skip(long n) throws IOException
	public int available() throws IOException
	public void close() throws IOException

* FileInputStream从文件中读取数据
* TelnetInputStream从网络连接中读取数据
* ByteArrayInputStream从字节数组中读取数据

InputStream的基本方法是没有参数的read()。这个方法从输入流的源中读取1字节数据，作为一个0到255的int返回。流的结束通过返回-1来表示。read()。流的结束通过返回-1来表示。read()方法会等待并阻塞其后任何代码的执行，直到有1个字节的数据可供读取。

输入和输出可能很慢，所以如果程序再做其他重要的工作，要尽量将I/O放在单独的线程中。

	int bytesRead = 0;
	int bytesToRead = 1024;
	byte[] input = new byte[bytesToRead];
	while (bytesRead < bytesToRead) {
		bytesRead += in.read(input, bytesRead, bytesToRead - bytesRead)
	}

所有3个read()方法都用返回-1表示流的结束。如果流已经结束，而又没有读取的数据，多字节read()方法会返回这些数据，直到缓冲区清空。其后任何一个read()方法调用会返回-1。-1永远不会放进数据中。数组中只包含实际的数据。

前面的代码段中存在一个bug，因为它没有考虑所有1024字节可能永远不会到达的情况。

	int bytesRead = 0;
	int bytesToRead = 1024;
	byte[] input = new byte[bytesToRead];
	while (bytesRead < bytesToRead) {
		int result = in.read(input, bytesRead, bytesToRead - bytesRead);
		if (result == -1) break; //流结束
		bytesRead += result;
	}

使用available()方法来确定不阻塞的情况下有多少字节可以读取。它会返回可以读取的最小字节数。

	int bytesAvailable = in.available();
	byte[] input = new byte[bytesAvailable];
	int bytesRead = in.read(input, 0, bytesAvailable);
	// 立即继续执行程序的其他部分...

在流的最后，available()会返回0。一般来说，read(byte[] input, int offset, int length)在流结束时返回-1；但如果length是0，那么它不会注意流的结束，而是返回0。

在少数情况洗啊，希望跳过数据不进行读取。skip()方法会完成这项任务。

### 标记和重置 ###

InputStream类还有3个不太常用的方法，允许程序备份和重新读取已经读取的数据

	public void mark(int readAheadLimit)
	public void reset() throws IOException
	public boolean markSupported()

为了重新读取数据，mark()方法标记流的当前位置。在以后某个时刻，可以用reset()方法把流重置到之前标记的位置。接下来的读取操作会返回从标记位置开始的数据。

标记和重置通常通过将标记位置之后的所有字节存储在一个内部缓冲区中来实现。

### 过滤器流 ###

InputStream和OutputStream是相当原始的类。

## ch3 线程 ##

1.重用进程
2.轻量级的线程来处理连接，通过使用线程池而不是为了每个连接生成新线程，服务器每分钟就可以用不到100个线程来处理数千个短连接。

## 运行线程 ##

线程如果以小写字母t大头（thread），这就表示虚拟机中的一个单独、独立的执行路径。如果以大写字母T大头（Thread），则是java.lang.Thread类的一个实例。

### 派生Thread ###

[code1](code/c1/DigestThread.java)

### 实现Runnable接口 ###

[code2](code/c2/DigestRunnable.java)

## 从线程返回信息 ##

[code3](code/c3/ReturnDigestUserInterface.java)

### 竞态条件 ###

[code3](code/c3/ReturnDigestUserInterface.java)

### 轮询 ###

code4

### 回调 ###

code5

### Future、Callable和Executor ###

code6

## 同步 ##

### 同步块 ###

### 同步方法 ###

### 同步的代替方法 ###

## 死锁 ##

## 线程调度 ##

### 优先级 ###

### 抢占 ###

### 阻塞 ###

### 放弃 ###

### 休眠 ###

### 连接线程 ###

### 等待一个对象 ###

### 结束 ###

## 线程池和Executor ##

java.util.concurrent钟的Executors类，可以非常容易地建立线程池。

## ch4 Internet地址 ##

### InetAddress类 ###

java.net.InetAddress类是Java对IP地址（包括IPv4和IPv6）的高层表示。Socket、ServerSocket、URL、DatagramSocket、DatagramPacket等。

### 创建新的InetAddress对象 ###

第一个InetAddress.getByAddress()工厂方法用一个IP地址（而没有主机名）创建一个InetAddress对象。第二个InetAddress.getAddress()方法使用一个IP地址和一个主机名创建InetAddress对象。

### 缓存 ###


## ch5 Internet地址 ##

### InetAddress类 ###
java.net.InetAddress类是Java对IP地址（包括IPv4和IPv6）的高层表示。Socket、ServerSocket、URL、DatagramSocket、DatagramPacket等。

#### 创建新的InetAddress对象 ####
第一个InetAddress.getByAddress()工厂方法用一个IP地址（而没有主机名）创建一个InetAddress对象。第二个InetAddress.getAddress()方法使用一个IP地址和一个主机名创建InetAddress对象。

#### 缓存 ####

## ch6 Http ##

超文本传输协议（Hypertext Transfer Protocol, HTTP）是一个标准，定义了Web客户端如何与服务器对话，以及数据如何从服务器传回客户端。

### HTTP协议 ###
HTTP使Web浏览器和Web服务器之间通信的标准协议。HTTP指定客户端与服务器如何建立连接、客户端如何从服务器请求数据，服务器如何响应请求，以及最后如何关闭连接。

HTTP连接使用TCP/IP来传输数据。对于从客户端导服务器的每一个请求，都有4个步骤：

HTTP1.0

1. 默认情况下，客户端在端口80打开与服务器的一个TCP连接，URL中还可以指定其他端口。
2. 客户端向服务器发送信息，请求指定路径上的资源。这个请求包括一个首部，可选地（取决于请求的性质）还可以有一个空行，后面是这个请求的数据。
3. 服务器向客户端发送响应。响应以响应码开头，后面是包含元数据的首部、一个空行以及所请求的文档或错误消息。
4. 服务器关闭连接。

HTTP1.1 可以用过一个TCP连接连续发送多个请求和响应。第1步和第4步之间，第2步和第3步可以反复多次。

### Keep-Alive ###
客户可以在HTTP请求首部中包括一个Connection字段

## Cookie ##

要在浏览器设置一个cookie，服务器会在HTTP首部中包含一个Set-Cookie首部行。

	HTTP/1.1 200 OK
	Content-type: text/html
	Set-Cookie: cart=ATVPDKIKXoDER

如果浏览器再向同一个服务器做出第二个请求，它会在HTTP请求首部行中的Cookie行发回这个cookie。

	GET /index.html HTTP/1.1
	Host: www.example.org
	Cookie: cart=ATVPDKIKXoDER
	Accept: text/html

只要服务器不重用cookie，这会使它在多个（否则无状态的）HTTP连接伤跟踪各个用户和会话。

### CookieManager ###
java5 包括一个抽象类java.net.CookieHandler，它定义了存储和获取cookie的一个API，但不包括这个抽象类的实现。

java.net.CookieManager

	CookieManager manager = new CookieManager();
	CookieHandler.setDefault(manager);

* CookiePolicy.ACCEPT_ALL接收所有cookie。
* CookiePolicy.ACCEPT_NONE不接受任何cookie。
* CookiePolicy.ACCEPT_ORIGINAL_SERVER只接受第一方cookie。

	CookieManager manager = new CookieManager();
	manager.setCookiePolicy(CookiePolicy.ACCEPT_ORIGINAL_SERVER);
	CookieHandler.setDefault(manager);
	//它只接受与你对话的服务器发送的cookie，而不接受Internet上其他服务器发送的cookie。

更细粒度的控制，接受某些已知域的cookie，而不接受其他域的cookie，可以自行实现CookiePolicy接口，并覆盖shouldAccept()方法:

	public boolean shouldAccept(URI uri, HttpCookie cookie)

[code](c1.java)

### CookieStore ###

	CookieStore store = manager.getCookieStore();

CookieStore类允许增加、删除和列出cookie，使能控制在正常HTTP请求和响应流之外的cookie：

	public void add(URI uri, HttpCookie cookie)
	public void List<HttpCookie> get(URI uri)
	public void List<HttpCookie> getCookies()
	public void List<URI> getURIs()
	public boolean remove(URI uri, HttpCookie cookie)
	public boolean removeAll()


## ch7 URLConnection ##

## ch8 客户端Socket ##

### 使用Socket ###

* 连接远程机器

* 发送数据

* 接收数据

* 关闭连接

* 绑定端口

* 监听入站数据。

* 在绑定端口上接收来自远程机器的连接

## ch9 服务器Socket ##

服务器Socket在服务器上运行，监听入站TCP连接。每个服务器Socket监听服务器机器上的一个特定端口。

### 使用SeverSocket ###
ServerSocket类包含了使用Java编写服务器所需的全部内容。其中包括创建新ServerSocket对象的构造函数、在指定端口监听连接的方法、配置各个服务器Socket选项的方法，以及其他一些常见的方法。
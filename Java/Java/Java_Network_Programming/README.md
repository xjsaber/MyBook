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

IPv4网络中的每台计算机都由一个4字节的数字标识。一般写为点分四段（dotted quad）格式，如199.1.32.90，这4个数中，每个数都是一个无符号字节，范围从0到255.IPv4网络中的每台计算机都有唯一的4字节地址。当数据通过网络传输时，包的首部会包括要发往的机器地址（目的地址）和发送这个包的机器地址（源地址）。

沿路的路由器通过检查目的的地址来选择发送数据包的最佳路由。包括源地址是为了让接收方直到要向谁回复。

当Java程序访问网络时，他们需要同时处理数字地址和相应的主机名。这些方法由java.net.InetAddress类提供。

### 端口 ###

FTP与Web业务流分开，通过端口（port）实现的。

### Internet ###

### Internet 地址分块 ###

ISP分配IPv4地址块。

如果前缀是216.254.85，那么本地网络可以使用从216.254.85.0到216.254.85.255的地址。由于这个块固定了前24位，所以称为/24。/23指定了前25位，而留出9位表示总共2<sup>9</sup>。不过，所有块中最低地址用于标识网络本身，最高地址是这个网络的一个广播地址，所以比原先预想的要少两个地址。

### 网络地址转换 ###

网络地址转换（Network Address Translation，NAT）。基于NAT的网络中，大多数节点只有不可路由的本地地址。

路由器会监视出站和入站连接，调整IP包中的地址。对于出站的包，它将源地址改为路由器的外部地址。对于入站的包，它将目的地址改为一个本地地址。

### 防火墙 ###

过滤通常是基于网络地址和端口。

### 代理服务器 ###

代理服务器（proxy server）与防火墙有关。这个机器可以请求本地代理服务器的Web页面，而不是直接请求远程Web服务器的Web页面。然后代理服务器会请求Web服务器的页面，将响应转发给最初发出请求的机器。

防火墙一般工作于传输层或网际层，而代理服务器通常工作于应用层。

代理服务器可以用来实现本地缓存（local caching）。当请求Web服务器的文件时，代理服务器首先查看此文件是否已在缓存中。如果文件在缓存中，那么代理服务器将提供缓存中的文件，而不是Internet上的文件。如果这个文件不在缓存中，那么代理服务器将获取此文件，转发给请求方，并将它存储在缓存中，供下次请求使用。

代理服务器最大的问题在于无法应对所有协议。通常已有的协议如HTTP、FTP和SMTP允许通过，而更新的协议如BitTorrent则不允许通过通过。

### 客户/服务器模型 ###

服务器和客户端

### Internet标准 ###

#### IETF RFC ####

#### W3C推荐 ####

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

1.重用进程，而不是创建新的进程。
2.轻量级的线程来处理连接，通过使用线程池而不是为了每个连接生成新线程，服务器每分钟就可以用不到100个线程来处理数千个短连接。

### 运行线程 ###

线程如果以小写字母t大头（thread），这就表示虚拟机中的一个单独、独立的执行路径。如果以大写字母T大头（Thread），则是java.lang.Thread类的一个实例。

在虚拟机中执行的线程与虚拟机构造的Thread对象之间存在一种一一对象的关系。

	Thread t = new Thread();
	t.start();

### 派生Thread ###

由于run()方法的签名是固定的，所以无法向其传递参数或从中返回值。因此，需要其他方法向线程传递信息和从中获得信息。传递信息最简单的方法是向构造函数传递参数。

[code1](code/c1/DigestThread.java)

### 实现Runnable接口 ###

避免覆盖标准的Thread方法

1. 不要派生Thread类。
2. 希望线程完成的任务编写位Runnable接口的一个实例。

	public void run()

任何实现这个接口的类都必须提供这个方法，除了这个方法之外，可以自由地创建任何其他方法（可以使用选择的任何方法名），而绝不会无意地妨碍线程的行为。

还允许将线程的任务放在其他的类的子类中，如Applet或HTTPServlet。要启动执行Runnable任务的一个线程，可以把这个Runnable对象传入Thread构造函数。

	Thread t = new Thread(myRunnableObject);
	t.start();

[code2](code/c2/DigestRunnable.java)

## 从线程返回信息 ##

run()方法和start()方法不返回任何值。

[code3](code/c3/ReturnDigestUserInterface.java)

### 竞态条件 ###

[code3](code/c3/ReturnDigestUserInterface.java)

### 轮询 ###

让获取方法返回一个标志值（或者可能抛出一个异常），直到设置了结果字段位置。然后主线程定期询问获取方法，查看是否返回了标志之外的值。

code4

这个解决方案不能保证一定能工作。在有些虚拟机上，主线程会占用所有可用的时间，而没有给具体的工作线程留出任何时间。主线程太忙于检查工作的完成情况，以至于没有时间来具体完成任务。

### 回调 ###

线程告诉主线程它何时结束。这是通用调用主类（即启动这个线程的类）中的一个方法来做的。这被称为回调（callback），因为线程在完成时反过来调用其创建者。因为线程在完成时反过来调用其创建者。

当线程的run()方法接近结束时，要做的最后一件事情就是基于结果调用主线程中的一个已知方法。不是由主程序询问

code5

### Future、Callable和Executor ###

不再直接创建一个线程，而是创建一个ExecutorService，会根据需要位你创建线程。可以向ExecutorService提供Callable任务，对于每个Callable任务，会分别得到一个Future。之后可以向Future请求得到任务的结果。

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

java.util.concurrent中的Executors类，可以非常容易地建立线程池。只需要将各个任务作为一个Runnable对象提交给这个线程池，就会得到一个Future对象，可以用来检查任务的进度。

## ch4 Internet地址 ##

连接到Internet的设备称为节点（node）。计算机节点称为主机（host）。每个节点或主机都由至少一个唯一的数来标识，这称为Internet地址或IP地址。

IPv4地址一般写为四个无符号字节，每字节范围从0到255，最高字节在前。
IPv6地址通常写为冒号分隔的8个区块，每个区块是4个十六进制数字。

一个名会映射到多个IP地址，DNS服务器负责随机选择一台机器来响应各个请求。负载分摊到多个系统上。

每台连接到Internet的计算机都应当能访问一个称为域名服务器（domain name server）的机器，通常是一个运行特殊DNS软件的UNIX主机，这种软件了解不同主机名和IP地址之间的映射。大多数域名服务器只知道其本地网络上主机的地址，以及其他网站中一些域名服务器的地址。如果客户端请求本地地域之外一个机器的地址，本地域名服务器就会询问远程位置的域名服务器，再将答案转发给请求者。

大多数情况下，使用主机名，而让DNS处理向IP地址的转换。只要能连接到一个域名服务器，就不需要担心在你的机器、本地域名服务器和Internet其余部分之间传递主机名和地址的有关细节。

### InetAddress类 ###

java.net.InetAddress类是Java对IP地址（包括IPv4和IPv6）的高层表示。Socket、ServerSocket、URL、DatagramSocket、DatagramPacket等。

### 创建新的InetAddress对象 ###

InetAddress类没有公共构造函数。实际上，InetAddress有一些静态工厂方法，可以连接到DNS服务器来解析主机名。最常用的是InetAddress.getByName()。

	InetAddress address = InetAddress.getByName("www.baidu.com");

第一个InetAddress.getByAddress()工厂方法用一个IP地址（而没有主机名）创建一个InetAddress对象。第二个InetAddress.getAddress()方法使用一个IP地址和一个主机名创建InetAddress对象。

如果知道一个数字地址，可以由这个地址创建一个InetAddress对象，而不必使用InetAddress.getByAddress()与DNS交互。这个方法可以为不存在或者无法解析的主机创建地址：

	public static InetAddress getByAddress(byte[] addr) throws UnknownHostException
	public static InetAddress getByAddress(String hostname, byte[] addr) throws UnknowHostException

第一个InetAddress.getByAddress()工厂方法用一个IP地址（而没有主机名）创建一个InetAddress对象。第二个InetAddress.getByAddress()方法使用IP地址和一个主机名创建InetAddress对象。

### 缓存 ###

由于DNS查找的开销可能相当大（如果请求需要经过多个中间服务器，或者尝试解析一个不可达的主机，这大约需要几秒的时间），所以InetAddress类会缓存查找的结果。

Java对于不成功的DNS查询只缓存10秒。

这些时间可以用系统属性networkaddress.cache.ttl和networkaddress.cache.negative.ttl来控制。其中第一个属性networkaddress.cache.ttl指定了成功的DNS查找结果在Java缓存中保留的时间（秒数），networkaddress.cache.negative.ttl指定了不成功的查找结果缓存的时间（秒数）。在这些时限内，再次尝试查找相同的主机会返回相同的值。-1解释位“永不过期”。

### 按IP地址查找 ###

调用getByName()并提供一个IP地址串作为参数时，会为所请求的IP地址创建一个InetAddress对象，而不检查DNS。

由包含IP地址的字符串来创建InetAddress对象时，这个对象的主机名初始设置为这个字符串。只有当请求主机名是（显式地通过getHostName()请求），才会真正完成主机名的DNS查找。

### 安全性问题 ###

调用getByName()并提供一个IP地址串作为参数时，会位所请求的IP地址创建一个InetAddress对象，而不检查DNS。在默认安全管理器控制下的不可信applet只允许获得它的初始主机（其代码基）的IP地址，这可能是本地主机。

InetAddress.getByName()方法、InetAddress.getAllByName()方法、InetAddress.getLocalHost()方法。

不可信代码可以由字符串形式的IP地址构造InetAddress对象，但不会为这样的地址完成DNS查询。

要测试一个主机能够解析，所用的特定SecurityManager方法是checkConnect():

	public void checkConnect(String hostname, int port)

当port参数位-1时，这个方法检查能够调用DNS解析制定的hostname。（如果port参数大于-1，这个方法检查是否允许在指定端口对指定主机建立连接）。host参数可以是主机名，也可以是点分呢四段IP地址，或者还可以是十六进制IPv6地址。

### 获取方法 ###

InetAddress包含4个获取方法，可以将主机名作为字符串返回，将IP地址返回位字符串和字节数组：

	public String getHostName()
	public String getCanonicalHostName()
	public byte[] getAddress()
	public String getHostAddress()

没有对应的setHostName()和setAddress()方法。

getHostName()方法返回一个String，其中包含主机的名字，以及这个InetAddress对象表示IP地址。如果这台机器没有主机名或者安全管理器阻止确定主机名，就会返回点分四段格式的数字IP地址。

	InetAddress machine = InetAddress.getLocalHost();
	String localhost = machine.getHostName();

希望知道一台机器的IP地址，可以使用getAddress()方法，会以网络字节顺序将IP地址作为一个字节数组返回。最高字节（即地址的点分四段形式中的第一字节）是数组的第一字节，即数组的元素0.如果要考虑到IPv6地址，不要对这个数组的长度做任何假定。

	InetAddress me = InetAddress.getLocalHost();
	byte[] address = me.getAddress();

返回的字节是无符号的。

### 地址类型 ###

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
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

### 获取方法 ###

NetworkInterface对象

#### public Enumeration getInetAddresses() ####

一个网络接口可以绑定多个IP地址。getInetAddresses()方法返回一个java.util.Enumeration，其中对于与这个接口绑定的每一个IP地址都包含一个InetAddress。

	NetworkInterface eth0 = NetworkInterrface.getByName("eth0");
	Enumberation addresses = eth0.getInetAddresses();
	while (addresses.hasMoreElements()) {
		System.out.println(addresses.nextElement());
	} 

#### public String getName() ####

getName()方法返回某个特定NetworkInterface对象的名

#### public String getDisplayName() ####

getDisplayName()方法声称可以返回特定NetworkInterface的一个更友好的名字。

### 一些有用的程序 ###



#### SpamCheck ####

负载应当分布到多个服务器上，最好是位于世界各地的不同服务器。如果DNS查询成功（更准确地讲，如果它返回地址127.0.0.2），那么主机就是一个已知的垃圾邮件发送者。如果查找失败，也就是说，它抛出一个UnknowHostException，说明这个地址不是一个垃圾邮件发送者。

### 处理Web服务器日志文件 ###

Web服务器日志会跟踪记录访问Web网站的主机。默认情况下，日志会报告连接服务器的网站的IP地址。

## ch5 URL和URI ##

URL类是Java程序再网络中定位和获取数据的最简单的方法。

### URI ###

统一资源标识符（Uniform Resource Udentifier， URI）是采用一种特定语法标识一个资源的字符串。

资源是由URI标识的内容。URI则是标识一个资源的字符串。

URI的语法由一个模式和一个模式特定部分组成，模式和模式特定部分用一个冒号分隔，如下所示：
	
	模式：模式特定部分

模式特定部分的语法取决于所用的模式。当前的模式包括：

	data 链接中直接包含的Base64编码数据
	file 本地磁盘上的文件
	ftp FTP服务器
	http 使用超文本传输协议的国际互联网服务器
	maito 电子邮件地址
	magnet 可以通过对等网络
	telnet 与基于Telnet的服务的连接
	urn 统一资源名（Uniform Resource Name，URN）

### URLs ###

### 相对URL ###

### URL类 ###

java.net.URL类是对统一资源定位符（如http://www.lolcats.com/）的抽象。它扩展了java.lang.Object，是一个final类，不能对其派生子类。

它不依赖于继承来配置不同类型URL的实例，而使用策略（strategy）设计模式。协议处理器就是策略，URL类构成上下文，通过它来选择不同的策略。

URL是不可变的。构造一个URL对象后，其字段不再改变。这有一个副作用：可以保证它们是“线程安全”的。

### 创建新的URL ###

	public URL(String url) throws MalformedURLException
	public URL(String protocol, String hostname, String file) throws MalformedURLException
	public URL(String protocol, String host, int port, String file) throws MalformedURLException
	public URL(URL base, String relative) throws MalformedURLException

#### 从字符串构造URL ####

最简单的URL构造函数只接受一个字符串形式的绝对URL作为唯一的参数：

	public URL(String url) throws MalformedURLException

与所有构造函数一样，这个函数只能在new操作符后调用，另外同样类似于所有其他URL构造函数，可能回抛出MalformedURLException异常。

	try {
		URL u = new URL("http://www.audubon.org/");
	} catch (MalformedURLException ex) {
		System.err.println(ex);
	}

#### 由组成部分构造URL ####

通过指定协议、主机名和文件来构建一个URL：

	public URL(String protocol, String hostname, String file) throws MalformedURLException

这个构造函数讲端口设置为-1，所以会使用改协议的默认端口。file参数应当以斜线开头、包括路径、文件名和可选的片段标识符。

与所有URI构造函数一样，可能会抛出MalformedURLexception异常。

	try {
		URL u = new URL("http", "www.eff.org", "/blueribbon.html#intro");
	} catch (MalformedURLException ex) {
		throw new RuntimeException("shouldn't happen; all VMS recogninze http");
	}

#### 构造相对URL ####

构造函数根据相对URL和基础URL构建一个绝对URL

	public URL(URL base, String relative) throws MalformedURLException

	try {
		URL u1 = new URL("http://www.ibiblio.org/javafaq/index.html");
		URL u1 = new URL(u1, "mailinglists.html");
	} catch(MalformedURLException ex) {
		System.err.println(ex);
	}
讲文件名从u1的路径中去除，追加新文件名mailinglists.html得到u2。如果希望循环处理位于同一个目录下的一组文件，这个构造函数特别有用。可以为第一个文件创建一个URL，然后使用这个初始URL，通过替代文件名来创建其他文件的URL对象。

### 其他URL对象 ###

Java类库中的其他一些方法也返回URL对象。

在applet中，getDocumentBase()会返回包含这个applet的页面的URL，getCodeBase()会返回applet。

class文件的URL。java.io.File类有一个toURL()方法，它返回与指定文件匹配的file URL。

Java file URL通常不能与用于Web浏览器和其他程序使用的URL互换，甚至不能与不同平台上运行的Java程序互换。

类加载器不仅用于加载器，也能加载资源，如图片和音频文件。静态方法ClassLoader.getSystemResource(String name)返回一个URL，通过它可以读取一个资源。ClassLoader.getSystemResources(String name)方法返回一个Enumeration，其中包含一个URL列表，通过这些URL可以读取指定的资源。最后，实例方法getResource(String name)会在所引用类加载器使用的路径中搜索指定资源的URL。这些方法返回的URl可能是file URL、HTTP URL或其他模式。

### 从URL获取数据 ###

	public InputStream openStream() throws IOException
	public URLConnection openConnection() throws IOException
	public URLConnection openConnection(Proxy proxy) throws IOException
	public Object getContent() throws IOException
	public Object getCOntent(Class[] classes) throws IOException

最基本是最常用的是openStream()，它会返回一个InputStream，可以从这个流读取数据。如果需要更多地控制下载过程，应当调用openConnection()，这会提供一个可以配置的URLConnection，再由它得到一个InputStream。

#### public final InputStream openStream() throws IOException ####

openStream()方法连接到URL所引用的资源，再客户端和服务器之间完成必要的握手，返回一个InputStream，可以由此读取数据。从这个InputStream获得的数据是URL引用的原始内容（即未经解释的内容）：如果读取ASCII文本文件则为ASCII；如果读取HTML文件则为原始HTML（不包括任何HTTP首部或者与协议有关的任何其他信息），如果读取图像文件则为二进制图片数据等。

	try {
		URL u = new URL("http://www.lolcats.com");
		InputStream in = u.openStream();
		int c;
		while ((c= in.read()) != -1) Systemout.write(c);
		in.close();
	} catch (IOException ex) {
		System.err.println(ex);
	}

MalformedURLException是IOException的子类。

在Java6及之前版本中，使用了释放模式：在try块外声明变量，并将它设置为null，然后在finally块中，如果流变量非null，则讲它关闭。

	InputStream in = null;
	try {
		URL u = new URL("http://www.lolcats.com");
		in = u.openStream();
		int c;
		while ((c = in.read()) != -1) System.out.write(c);
	} catch (IOException ex) {
		System.err.println(ex);
	} finally {
		try {
			if (in != null) {
				in.close();
			}
		} catch(IOException ex) {
			// 忽略
		}
	}

Java7，使用一个嵌套的try-with-resources语句：

	try {
		URL u = new URL("http://www.lolcats.com");
		try (InputStream in = u.openStream()){
			int c;
			while ((c = in.read()) != -1) System.out.write(c);
		}
	} catch (IOException ex) {
		System.err.println(ex);
	}

#### public URLConnection openConnection() throws IOException ####

oepnConnection()方法为指定的URL打开一个socket，并返回一个URLCOnnect对象。URLConnection表示一个网络资源的打开的连接。如果调用失败，则openConnection()会抛出一个IOException异常。

#### public final Object getContent() throws IOException ####

getContent()方法是下载URL引用数据的第三种方法。getContent()方法获取URL引用的数据尝试由它建立某种类型的对象。

	URL u = new URL("http://www.baidu.com");
	Object o = u.getContent();
	// 将Object强制转换为适当的类型
	// 处理这个Object...

getContent()的做法是，在从服务器获取的数据首部中查找Content-type字段。如果服务器没有使用MIME首部，或者发送了一个不熟悉的Content-type，getContent()会返回某种InputStream，可以通过它读取数据。如果无法获取这个对象，就会抛出一个IOException异常。

#### public final Object getContent(Class[] classes) throws IOException ####

URL的内容处理器提供一个资源的不同视图。getContent()方法的这个重载版本允许选择希望内容作为哪个类返回。这个方法尝试以第一种可用的格式返回URI的呃内容。

### 分解URL ###

URL由以下5部分组成：

* 模式，也称为协议。
* 授权机构
* 路径
* 片段标识符，也称为段或ref。
* 查询字符串

授权机构可以进一步划分为用户信息、主机和端口。

9个公共方法提供了URL这些部分的只读访问：getFile()、getHost()、getPort()、getProtocol()、getRef()、getQuery()、getPath()、getUserInfo()和getAuthority()。

#### public String getProtocol() ####

getProtocol()方法返回一个String。其中包含URL的模式（如“http”、“https”或“file”）。

	URL u = new URL("https://xkcd.com/727/");
	System.out.println(u.getProtocol());

#### public String getHost() ####

getHost()方法返回一个String，其中包含URL的主机名。

	URL u = new URL("https://xkcd.com/727/");
	System.out.println(u.getHost());

#### public int getPort() ####

getPort()犯法将URI中指定的端口号作为一个int返回。如果URL中没有指定端口，getPort()返回-1，表示这个URL没有显式指定端口，将使用改协议的默认端口。

#### public int getDefaultPort() ####

URL中没有指定端口时，getDefaultPort()方法返回这个URL的协议所使用的默认端口。如果没有为这个协议定义默认端口，getDefaultPort()将返回-1。

#### public String getFile() ####

getFile()方法返回一个String，其中包含URL的路径部分；Java不会把URL分解为单独的路径和文件部分。从主机名后的第一个斜线（/）一直到开始片段标识符的#号之前的字符，都被认为是文件部分。

	URL page = this.getDocumentBase();
	System.out.println("This page's path is " + page.getFile());
如果URL没有文件部分，Java会把文件设置为空串。

#### public String getPath() ####

getPath()方法几乎是getFile()的同义词。

getPath()和getFile()都返回完整的路径和文件名。唯一的区别是getFile()还返回查询字符串，而getPath()不返回这一部分。

#### public String getRef() ####

getRef()方法返回URL的片段标识符部分。如果URL没有片段标识符，则这个方法返回null。

#### public String getQuery() ####

getQuery()方法返回URL的查询字符串。如果URL没有查询字符串，则这个方法返回null。

	URL u = new URL("http://www.ibiblio.org/nywc/compositions.phtml?catagory=Piano");
	System.out.println("The query string of " + u + " is " + u.getQuery());

#### public String getUserInfo() ####

有些URL包括用户名，或者口令信息。

#### public String getAuthority() ####

### 相等性和比较 ###

URL类包含通常的equals()和hashCode()方法。当且仅当两个URL都指向相同主机、端口和路径上的相同资源，而且有相同的片段标识符和查询字符串，才认为着两个URL是相等的。

URL上的equals()可能是一个阻塞的I/O操作，应当避免将URL存储在依赖equals()的数据结构中，如java.util.HashMap。更好的选择是java.net.URI，可以在必要时将URI和URL来回切换。

URL没有实现Comparable。

URL类还有一个sameFile()方法，可以检查两个URL是否指向相同的资源：

	public boolean sameFile(URL other)

#### 比较 ####

URL有3个方法可以将一个实例转换为另外一种形式，分别是toString()、toExternalForm()和toURI()。

java.net.URL有一个toString()方法。toString()生成的String总是绝对URL。显式调用toString()并不常见。显示（打印）语句会隐式调用toString()。除了显示（打印）语句以外，使用toExternalForm()更合适：

	public String toExternalForm()

toExternalForm()方法将一个URL对象转换为一个字符串，可以在HTML链接或Web浏览器的打开URL对话框中使用。

toExternalForm()方法返回表示这个URL的一个人可读的String。它等同于toString()。事实上，toString()所做的就是返回toExternalForm()。

最后，toURI()方法将URL对象转换为对应的URI对象。

	public URI toURI() throws URISyntaxException

### URI类 ###

URI是对URL的抽象，不仅包括统一资源定位符（Uniform Resource Locators，URL），还包括统一资源名（Uniform Resource Names，URN）。

在Java中，URI用java.net.URI类表示。这个类与java.net.URL类的区别现在3个重要的方面：

* URI类完全有关于资源的标识和URI的解析。它没有提供方法来获取URI所标识资源的表示。
* 相比URL类，URI类与相关的规范更一致。
* URI对象可以表示相对URI。URL类在存储URI之前会将其绝对化。

URL对象是对应网络获取的应用层协议的一个表示，而URI对象纯粹用于解析和处理字符串。URI类没有网络获取功能。尽管URL类有一些字符串解析方法，如getFile()和getRef()。

#### 构造一个URI ####

URI从字符串构造。可以把整个URI通过一个字符串传入构造函数，也可以分部分传入：

	public URI(String uri) throws URISyntaxException
	public URI(String scheme, String schemeSpecificPart, String fragment) throws URISyntaxException
	public URI(String scheme, String host, String path, String fragment) throws URISyntaxException
	public URI(String scheme, String authority, String path, String query, String fragment) throws URISyntaxException
	public URI(String scheme, String userInfo, String host, int port, String path, String query, String fragment) throws URISyntaxException

与URL类不同，URI类不依赖于底层协议处理器。只要是URI语法上正确，Java就不需要为了创建URI对象而理解其协议。

1. 构造函数根据任何满足条件的字符串创建一个新的URI对象。

	URI voice = new URI("tel:111231");
	URI web = new URI("http://www.baidu.com");
	URI book = new URI("urn:isbn:123");

2. 构造函数需要一个模式特定的部分，主要用于非层次URI。模式（scheme）是URI的协议，如http、urn、tel等。
3. 构造函数用于层次URI，如http和ftp URL。主机和路径（用/分隔）共同构成这个URI的模式特定部分。
4. 添加了一个查询字符串部分
5. 前面两个构造函数调用的主层次URI构造函数。

### URI的各部分 ###

URI引用包括最多三个部分：模式、模式特定部分和片段标识符。一般格式为：

	模式：模式特定部分：片段

解码后的部分：

	public String getScheme()
	public String getSchemeSpecificPart()
	public String getRaawSchemeSpecificPart()
	public String getFragment()
	public String getRawFragment()

### 解析相对URI ###

### URLEncoder ###

要对字符串完成URL编码，需要将这个字符串和字符集名传入URLEncoder.encode()方法。
	
	String encoded = URLEncoder.encode("This*string*has*asterisks", "UTF-8");

URLEncoder.encode()返回输入字符串的一个副本，不过有一些调整。

### 缓存 ###

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

请求头
	GET /index.html HTTP/1.1
	User-Agent: Mozilla/5.0(Machintosh; Intel Mac OS X 10.8; rv: 20.0) 浏览器版本
	Gecko/20100101 Firefox/20.0
	Host: en.wikipedia.org 指定服务器多地名，允许Web服务器区分来自相同IP的不同名的主机
	Accept-Language: en-US, en;q=0.5 处理哪些数据类型
	Accept-Encoding: gzip, deflate
	Accept: text/html,application/xhtml-xml,application/xml;q=0.9,*/*;q=0.8
响应头
	HTTP/1.1 200 OK 服务器使用协议（HTTP/1.1）响应码
	Date: Sun, 21 Apr 2013 15:12:46 GMT 请求的日期（采用服务器的时间帧）
	Server: Apache 服务器软件（Apache）
	Connection: close 承若服务器结束时发送时关闭链接
	Content-Type: text/html;charset=ISO-8859-1 MIME媒体类型
	Content-length:115 传输文档的长度

HTTP1.1 可以用过一个TCP连接连续发送多个请求和响应。第1步和第4步之间，第2步和第3步可以反复多次。

* text/* 表示人可读的文字。
* image/* 表示图片。
* model/* 表示3D模型，如VRML文件。

### Keep-Alive ###

HTTP1.0会为每个请求打开一个新连接。实际上，一个典型Web会话中打开和关闭所有连接所花费的时间远远大于实际传输数据的时间，特别是有很多小文档的会话。

在HTTP1.1和以后版本中，服务器不必在发送响应后就关闭连接，可以保持连接打开，在同一个socket上等待来自客户端的新请求。可以在一个TCP连接上连续发送多个请求和响应。

客户可以在HTTP请求首部中包括一个Connection字段，指定值为Keep-Alive，指示它希望重用一个socket：

	Connection: Keep-Alive

* 设置http.keepAlive为“true”或“false”，启用/禁用HTTP keep-Alive(默认是启用的)。
* 设置http.maxConnections 默认为5。
* 设置http.keepAlive.remainingData为true，使Java在丢弃连接之后完成清理
* 设置sun.net.http.errorstream.enableBuffering为true
* 设置sun.net.http.errorstream.bufferSize为缓冲错误流使用的字节数。默认为4096字节。
* 设置Set sun.net.http.errorstream.timeout为读错误流超时前的毫秒数。默认为300毫秒。

## Http方法 ##

与HTTP服务器的通信遵循一种请求-响应模式：先是一个无状态的请求，后面是一个无状态的响应。每个HTTP请求包括两个或三个部分：

* 起始行，包含HTTP方法和要执行这个方法的资源的路径。
* 一个包含名-值字段的首部，可以提供元信息
* 一个请求主体，包含资源的一个表示（只针对POST和PUT）

4个HTTP方法

* GET
* POST
* PUT
* DELETE

不完成提交的所有安全操作应当使用GET而不是POST。只有真正提交的操作才应当使用POST。

HEAD方法，相当于GET，它只返回资源的首部，而不返回具体的数据。这个方法常用与检查文件的修改日期，查看本地缓存中存储的文件副本是否仍然有效。

OPTIONS和TRACE

## 请求主体 ##

GET方法获取URL锁标识的资源的一个表示。用GET从服务器获取的资源的具体位置由路径和查询字符串的不同部分指定。

POST和PUT要更为复杂。

1. 一个起始行，包括方法、路径和查询字符串，以及HTTP版本。
2. 一个HTTP首部。
3. 一个空行（两个连续的回车/换行对）。
4. 主体。

	POST /cgi-bin/register.pl HTTP 1.0
	Date: Sun, 27 Apr 2013 12:32:36
	Host: www.cafeaulait.org
	Content-type: application/x-www-form-urlencoded
	Content-length: 54

	username=Elliotte+Harold&email=elharo%40ibiblio.org

application/x-www-form-unlencoded数据

* 一个Content-length手段，指定主体中有多少字节。
* 一个Content-type字段，指定类型的MIME媒体类型。

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

#### 用Telnet研究协议 ####

#### 用Socket从服务器读取 ####

#### 用Socket写入服务器 ####

#### 半关闭Socket ####

close()方法同时关闭Socket输入和输出。shutdownInput()和shutdownOutput()方法可以只关闭连接的一半（输入或者输出）。

	public void shutdownInput() throws IOException
	public void shutdownOutput() throws IOException

### 构造和连接Socket ###

java.net.Socket类是Java完成客户端TCP操作的基础类。

#### 基本构造函数 ####

每个Socket构造函数指定要连接的主机和端口。主机可以指定为InetAddress或String。远程端口指定为1到65 535之间的int值

	public Socket(String host, int port) throws UnknownHostException, IOException
	public Socket(InetAddress host, int port) throws IOException

构造函数连接socket（也就是说，在构造函数返回之前，会与远程主机建立一个活动的网络连接）。

#### 选择从哪个本地接口连接 ####

	public Socket(String host, int port, InetAddress interface, int localPort) throws IOException, UnknownHostException
	public Socket(InetAddress host, int port, InetAddress interface, int localPort)

网络接口可以是物理接口，也可以是虚拟接口（一个有多个IP地址的多宿主主机）。如果为localPort参数传入0，Java会随机选择1024到65535之间的一个可用端口。

#### 构造但不连接 ####

	public Socket()

可以以后再为某个connect()方法传入一个SocketAddress来建立连接。

	try {
		Socket socket = new Socket();
		// 填入socket选项
		SocketAddress address = new InetSocketAddress("www.baidu.com")
		Socket.connect(address);
		// 使用socket...
	} catch(IOException ex) {
		System.err.println(ex);
	}

#### Socket地址 ####

SocketAddress类表示一个连接断电。

#### 代理服务器 ####

最后一个构造函数创建一个未连接的Socket，它通过一个指定的代理服务器连接：

	public Socket(Proxy proxy)

#### 获取Socket的消息 ####

* 远程地址 public InetAddress getInetAddress()
* 远程端口 public int getPort()
* 本地地址 public InetAddress getLocalAddress()
* 本地端口 public int getLocalPort()

#### 关闭还是连接 ####

如果socket关闭，isCloesd()方法会返回true，否则返回false。

	if (socket.isClosed()){

	} else {
		
	}

如果Socket从一开始从未连接，isClosed()也返回false。

#### toString() ####

### 设置Socket选项 ###

* TCP_NODELAY
* SO_BINDADDR
* SO_TIMEOUT
* SO_LINGER
* SO_SNDBUF
* SO_RECBUF
* SO_KEEPALIVE
* OOBINLINE
* IP_TOS

#### TCP_NODELAY ####

	public void setTcpNoDelay(boolean on) throws SocketException
	public boolean getTcpNoDelay() throws SocketException

#### SO_LINGER ####

SO_LINGER选项指定了Socket关闭时如何处理尚未发送的数据报。默认情况下

	public void setSoLinger(boolean on, int seconds) throws SocketException
	public int getSolinger() throws SocketException



#### SO_TIMEOUT ####

	public void setSoTimeout(int milliseconds) throws SocketException
	public int getSoTimeout() throws SocketException

1. 尝试从Socket读取数据时，read()调用会阻塞尽可能长的时间来得到足够的字节。设置SO_TIMEOUT可以确保这个次调用阻塞的时间不会超过某个固定的毫秒数。
2. 当时间到期就会抛出一个InterruptedIOException异常。
3. Socket仍然是连接的，虽然read()调用失败，但可以再次尝试读取该Socket。下次调用可能会成功。

	if (s.getSoTimeout() == 0) s.setSoTimeout(180000);

1. SocketException
2. IllegalArgumentException

#### SO_RCVBUF和SO_SNDBUF ####

TCP使用缓冲区提升网络性能。较大的缓冲区会提升快速连接的性能，而较慢的拨号连接利用较小的缓冲区有更好的表现。

#### SO_KEEPALIVE ####

如果打开了SO_KEEPALIVE，客户端偶尔会通过一个空闲连接发送一个数据包）一般两小时一次，以确保服务器未崩溃。如果服务器没能响应这个包，客户端会持续尝试11分钟多的时间，直到接收到响应为止。如果服务器没能响应这个包，客户端就关闭socket。

如果没有SO_KEEPALIVE，不活动的客户端可能会永远存下去，而不会注意到服务器已经崩溃。

#### OOBINLINE ####

TCP包括一个可以发送单字节带外（Out Of Band，OOB）“紧急”数据的特性。这个数据会立即发送

## ch9 服务器Socket ##

服务器Socket在服务器上运行，监听入站TCP连接。每个服务器Socket监听服务器机器上的一个特定端口。

### 使用SeverSocket ###

ServerSocket类包含了使用Java编写服务器所需的全部内容。其中包括创建新ServerSocket对象的构造函数、在指定端口监听连接的方法、配置各个服务器Socket选项的方法，以及其他一些常见的方法。

在Java中，服务器程序的基本生命周期：

1. 使用一个ServerSocket()构造函数在一个特定端口创建一个新的ServerSocket。
2. ServerSocket使用其accept()方法监听这个端口的入站连接。accept()会一直阻塞，直到一个客户端尝试建立连接。
3. 根据服务器的类型，会调用Socket的getInputStream()方法或getOutputStream()方法，或者这两个方法都调用，以获得与客户端通信的输入和输出流。
4. 服务器和客户端根据已协商的协议交互，直到要关闭连接。
5. 服务器或客户端（或二者）关闭连接。
6. 服务器返回到步骤2，等待下一次连接。

创建一个监听端口13的服务器Socket：

	ServerSocket server = new ServerSocket(13);

接下来，接受一个连接：

	Socket connection = server.accept();

accept()调用会阻塞。

	OutputStream out = connection.getOutputStream();
	Writer writer = new OutputStreamWriter(writer, "ASCII");

	Date now = new Date();
	out.write(now.toString() + "\r\n");

	out.flush();
	connection.close();

#### 提供二进制数据 ####

发送二进制的非文本数据并不难，只需要使用一个写byte数组的OutputStream，而不是写String的Writer。

#### 多线程服务器 ####

Daytime和time都是非常快的协议。

#### 用Socket写入服务器 ####

与很多协议不同，echo没有指定锁步行为，即客户端发送一个请求，然后在发送更多数据之前会等待的服务器响应。

#### 关闭服务器Socket ####

## 第10章 安全Socket ##

### 保护通信 ###

* javax.net.ssl 定义Java安全网络通信API的抽象类。
* javax.net 替代构造函数创建安全Socket的抽象Socket工厂类。
* java.security.cert 处理SSL所需公开密钥证书的类。
* com.sun.net.ssl Sun的JSSE参考实现加密算法和协议的具体类。

### 创建安全客户端Socket ###

javax.net.ssl.SSLSocketFactory使用其createSocket()方法得到一个Socket对象。SSLSocketFactory是一个遵循抽象共产设计模式的抽象类。要通过调用静态SSLSocketFactory.getDefault()方法得到一个实例：
	SocketFactory factory = SSLSocketFactory.getDefault();
	Socket socket = factory.createSocket("login.ibiblio.org", 7000);

* public abstract Socket createSocket(String host, int port) throws IOException, UnknowHostException
* public abstract Socket createSocket(InetAddress host, int port) throws IOException
* public abstract Socket createSocket(String host, int port, InetAddress interface, int localPort) throws IOException, UnknowHostException
* public abstract Socket createSocket(InetAddress host, int port, InetAddress interface, int localPort) throws IOException, UnknowHostException
* public abstract Socket createSocket(Socket proxy, String host, int port, boolean autoClose) throws IOException

### 选择密码组 ###

JSSE的不同实现支持认真和加密算法的不同组合。

SSLSocketFactory中的getSupportedCipherSuites()方法可以指出给定Socket上可用的算法组合。

	public abstract String[] getSupportedCipherSuites()

* TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256

每个名中的算法分为4个部分：协议、密钥交换算法。

默认情况下，JDK1.7实现启用了所有加密认证密码组。

### 事件处理器 ###



## 第11章 非阻塞I/O ##

#### 一个示例客户端 ####

#### 一个示例服务器 ####

### 缓冲区 ###

除了boolean外，Java的所有基本数据类型都有特定的Buffer子类：ByteBuffer、CharBuffer、ShortBuffer、IntBuffer、LongBuffer、FloatBuffer和DoubleBuffer。每个子类中的方法都有相应类型的返回值和参数列表

位置（position）

	public final int position()
	public final Buffer position(int newPosition)

容量（capacity）

	public final int capacity()

限度（limit）

	public final int limit()
	public final Buffer limit(int newLimit)

标记（mark）

	public final Buffer mark()
	public final Buffer reset()

（position）

位置（position）

#### 创建缓冲区 ####

IntBuffer、ByteBuffer、CharBuffer还是其他类型

**分配**



**直接分配**

**包装**

#### 填充和排空 ####
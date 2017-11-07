# Java 网络工程 #

## ch1 基本网络概念 ##

## ch2 流 ##

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
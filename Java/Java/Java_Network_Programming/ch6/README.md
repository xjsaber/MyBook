# ch6 Http #
超文本传输协议（Hypertext Transfer Protocol, HTTP）是一个标准，定义了Web客户端如何与服务器对话，以及数据如何从服务器传回客户端。

## HTTP协议 ##
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

# 第2章：一个简单的Servlet容器 #

### javax.servlet.Servlet接口 ###

Servlet编程是通过javax.servlet和javax.servlet.http这两个包的类和接口来实现的。其中一个至关重要的就是javax.servlet.Servlet接口了

Servlet接口有五个方法，其用法如下：

	public void init(ServletConfig config) throws ServletException
	public void service(ServletRequest request, ServletResponse response) throws ServletException, java.io.IOException
	public void destroy()
	public ServletConfig getServletConfig()
	public java.lang.String getServletInfo()

在Servlet的五个方法中，init，service和destory是servlet的生命周期方法。

#### init ####

在servlet类已经初始化之后，init方法将会被servlet容器所调用。servlet容器只调用一次，以此表明servlet已经加载进服务中。init方法必须在servlet可以接受任何请求之前成功运行完毕。一个 servlet 程序员可以通过覆盖这个方法来写那些仅仅只要运行一次的初始化代码，例如加载数据库驱动，值初始化等等。在其他情况下，这个方法通常是留空的。

#### service ####

servlet容器为servlet请求调用它的service方法。servlet容器传递一个javax.servlet.ServletRequest对象和javax.servlet.ServletResponse对象。ServletRequest
对象包括客户端的 HTTP 请求信息，而 ServletResponse 对象封装 servlet 的响应。在 servlet的生命周期中，service 方法将会给调用多次。

#### destroy ####

当中服务中移除一个servlet实例的时候，servlet容器调用destroy方法。这通常发生在
servlet 容器正在被关闭或者 servlet 容器需要一些空闲内存的时候。仅仅在所有 servlet 线程
的 service 方法已经退出或者超时淘汰的时候，这个方法才被调用。在 servlet 容器已经调用完
destroy 方法之后，在同一个 servlet 里边将不会再调用 service 方法。destroy 方法提供了一个机会来清理任何已经被占用的资源，例如内存，文件句柄和线程，并确保任何持久化状态和servlet 的内存当前状态是同步的。

### 应用程序1 ###

一个全功能的 servlet 容器会为 servlet 的每个 HTTP 请求做下面一些工作。

* 当第一次调用 servlet 的时候，加载该 servlet 类并调用 servlet 的 init 方法(仅仅一次)。
* 对每次请求，构造一个 javax.servlet.ServletRequest 实例和一个javax.servlet.ServletResponse 实例。
* 调用 servlet 的 service 方法，同时传递 ServletRequest 和 ServletResponse 对象。
* 当 servlet 类被关闭的时候，调用 servlet 的 destroy 方法并卸载 servlet 类。

* 等待 HTTP 请求。
* 构造一个 ServletRequest 对象和一个 ServletResponse 对象。
* 假如该请求需要一个静态资源的话，调用 StaticResourceProcessor 实例的 process 方法，同时传递 ServletRequest 和 ServletResponse 对象。
* 假如该请求需要一个 servlet 的话，加载 servlet 类并调用 servlet 的 service 方法，同时传递 ServletRequest 和 ServletResponse 对象。

* HttpServer1
* Request
* Response
* StaticResourceProcessor
* ServletProcessor1
* Constants

### HttpServer1 ### 

### Request类 ###

servlet 的 service 方法从 servlet 容器中接收一个 javax.servlet.ServletRequest 实例 和一个 javax.servlet.ServletResponse 实例。这就是说对于每一个 HTTP 请求，servlet 容器必须构造一个 ServletRequest 对象和一个 ServletResponse 对象并把它们传递给正在服务的servlet 的 service 方法。

### Response类 ###


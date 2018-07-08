# 第3章 创建第一个Servlet #

## 3.1 创建Servlet类 ##

Servlet用于接收的响应终端用户的请求。

Servlet是一个运行在Web服务器中的Java小程序。Servlet将会接收和响应来自Web客户端请求，使用HTTP（超文本传输协议）进行通信。

Servlet是所有Web应用程序的核心类，是唯一的既可以直接处理和响应用户请求，也可以将处理工作委托给应用中的其他部分的类。

### 3.1.1 选择要继承的Servlet类 ###

Servlet继承自`javax.servlet.GenericServlet`。

GenericServlet是一个不依赖于具体协议的Servlet。

### 3.1.2 使用初始化方法和销毁方法 ###

init方法在Servlet构件完成之后，但在响应第一个请求之前被调用。

destory在Servlet不再接受请求之后立即调用。

## 3.2 配置可部署的Servlet ##

### 3.2.1 向描述符中添加Servlet ###

servlet

servlet-mapping

### 3.2.2 将Servlet映射到URL ###

### 3.2.3 运行和调试Servlet ###

## 3.3 了解doGet、doPost和其他方法 ##

### 3.3.1 在service方法执行的过程中 ###



### 3.3.2 使用HttpServletRequest ###

HttpServletRequest接口是对ServletRequest的扩展，它将提供关于收到请求的额外的与HTTP协议相关的信息。

#### 1.获取请求参数 ####

查询参数（也称为URI参数）、以application/x-www-form-unlencoded或multipart/form-data编码的请求正文。

#### 2.确定与请求内容相关的信息 ####

application/x-www-form-urlencoded、application/json、text/plain或application/zip

getContentLength和getContentLengthLong返回了请求的正文的长
度，以字节为单位

#### 3.读取请求的内容 ####

方法 `getInputStream` 将返回一个`javax.servlet.ServletInputStream`，而方法`getReader`将返回一个`java.io.BufferedReader`，都可以用于读取请求的内容。如果请求内容基于字符编码的，例如UTF-8或ISO-8859-1文本，那么使用`BufferedReader`，如果请求数据是二进制格式的，那么手机用`ServletInputStream`。

在同一个请求上如果同时使用这两个方法`BufferedReader`、`ServletInputStream`，在调用其中一个之后，再调用另一个将会触发IllegalStateExceptption异常。

#### 4.获取请求特有的数据，例如URL、URI和头 ####

* getRequestURL
* getRequestURI
* getServletPath
* getHeader
* getHeaderNames
* getIntHeader
* getDateHeader


5.会话和Cookies

### 3.3.3 使用HttpServletResponse ###

#### 1.编写响应正文 ####

getOutputStream将返回一个javax.servlet.ServletOutputStream
getWriter将返回一个java.io.PrintWriter

#### 2.设置头和其他响应属性 ####

* setStatus：设置HTTP响应状态码
* getStatus：判断当前响应的状态
* sendError：设置状态码
* sendRedirect：将客户端重定向至另一个URL

## 3.4 使用参数和接受表单提交 ##




## 3.5 使用初始化参数配置应用程序 ##

通过上下文初始化参数（简称初始化参数）和Servlet初始化参数进行配置

### 3.5.1 使用上下文初始化参数 ###

在web.xml文件中使用<context-param>标签声明上下文初始化参数
	
	web.xml
	<context-param>
        <param-name>settingOne</param-name>
        <param-value>foo</param-value>
    </context-param>
    <context-param>
        <param-name>settingTwo</param-name>
        <param-value>bar</param-value>
    </context-param>
	ContextParameterServlet.java
	ServletContext c = this.getServletContext();
	c.getInitParameter("settingOne")；
	c.getInitParameter("settingTwo")；

应用程序中的所有Servlet都将共享这些初始化参数，XML通常是定义上下文初始化参数的最好方式。

### 3.5.2 使用Servlet初始化参数 ###

@WebServlet



## 3.6 通过表单上传文件 ##

Servlet3.0 multipart配置选项，并为HttpServletRequest添加了getPart和getParts方法。

### 3.6.1 介绍客户支持项目 ###

### 3.6.2 配置Servlet支持文件上传 ###

Ticket类、Attachment类和TickServlet类。

	@MultipartConfig(
	        fileSizeThreshold = 5_242_880, //5MB
	        maxFileSize = 20_971_520L, //20MB
	        maxRequestSize = 41_943_040L //40MB
	)
注解@MultipartConfig将告诉Web容器为该Servlet提供文件上传支持。
location、fileSizeThreshold将告诉Web容器文件必须达到多大才能写入临时目录中。maxFileSize和maxRequestSize

## 3.7 编写多线程安全的应用程序 ##

### 3.7.1 理解请求、线程和方法执行 ###

在JavaEE世界里，Web容器通常会包含某种类型的线程池，它们被称为连接池或执行池。

Tomcat中最大的线程池大小默认是200。

线程池有个可以配置的大小属性。

Ps：JavaEE6中的Servlet3.0规范添加了异步请求上下文的概念。实际上，当Servlet处理请求时，调用ServletRequest的startAsync方法，返回一个包含了请求对象的javax.servlet.AsyncContext对象。然后Servlet将从service方法返回，不需要对请求作出响应，所使用的线程也被返回到线程池中。

ch9 异步上下文
ch10 长轮询（long polling） 

### 3.7.2 保护共享资源 ###

Servlet中的静态变量和实例变量都可以被多个线程同时访问。

在编写Servlet方法时，最需要记住的一件事，是永远不要在静态或实例变量中存储请求或响应对象。


	private volatile int TICKET_ID_SEQUENCE = 1;

	synchronized(this){
		id = this.TICKET_ID_SEQUENCE++;
		this.ticketDatabase.put(id, ticket);
	}

在该代码块中完成了两个操作：将TICKET_ID_SEQUENCE变量自增1并将修改后的值赋给id，将变量ticket插入到哈希map中。这两个变量都是Servlet的实例变量，这意味着多个线程可以同事访问它们。将这些操作添加到同步代码块中，可以保证其他线程都无法同时执行这两行代码。

Warming：在编写Servlet方法时，永远不要在静态或实例变量中存储请求或响应对象。

## 3.8 小结 ##

`GenericServlet` 和 `HttpServlet抽象类`，还有`HttpServletRequest` 和 `HttpServletResponse` 接口









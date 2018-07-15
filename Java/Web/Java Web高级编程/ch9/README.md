# 第9章 使用过滤器改进应用程序 #

## 9.1 了解过滤器的目的 ##

过滤器是可以拦截访问资源的请求、资源的响应或者同时拦截两者的应用组件，它们将以某种方式作用于这些请求或响应。过滤器可以检测和修改请求和响应，他们甚至可以拒绝、重定向或转发请求。

### 9.1.1 日志过滤器 ###




### 9.1.2 验证过滤器 ###

如果只有授权用户才可以访问应用程序，通常可以检查每个请求的信息，保证用户已经“登入”。

过滤器可以通过将验证和授权操作集中到一个位置（拦截所有安全的请求）的方式使工作变得更加简单

### 9.1.3 压缩和加密过滤器 ###



### 9.1.4 错误处理过滤器 ###



## 9.2 创建、声明和映射过滤器 ##

### 9.2.1 了解过滤器链 ###

接受进入的请求并将他传递到下一个过滤器，直至所有匹配的过滤器都处理完成，最终再将它传入Servlet中。

过滤器链的这种工作方式非常像栈（确实，一系列方法的执行都将运行在Java栈上）。当请求进入时，它首先将进入第一个过滤器，该过滤器将被添加到栈中。当过滤器链继续执行时，下一个过滤器将被添加到栈中。一直到请求进入Servlet中，它是被添加到栈中的最后一个元素。当请求完成并且Servlet的service方法也返回时，Servlet将从栈中移除，然后控制权被返回到最后一个过滤器。当它的doFilter方法返回时，该过滤器将从栈中移除，控制权也将返回之前的过滤器中。一直到控制权返回到第一个过滤器中。当它的doFilter方法返回时，栈已经是空的，请求处理也就完成了。

### 9.2.2 映射到URL模式和Servlet名称 ###

过滤器可以被映射到URL模式。

### 9.2.3 映射到不同的请求派发器类型 ###

* 普通请求——来自客户端，包含了容器中特定Web应用程序的目标URL。
* 转发请求 requestDispatcher的forward方法或者使用<jsp:forward>标签时将触发这些请求。
* 包含请求 <jsp:include>或者调用RequestDispatcher的include方法时，将会产生一个不同的、与原始请求相关的内部包含请求（）
* 错误资源请求——访问处理HTTP错误
* 异步请求——AsyncContext派发的请求

### 9.2.4 使用部署描述符 ###

	<filter>
		<filter-name>myFilter</filter-name>
		<filter-class>myFilter</filter-class>
	</filter>

### 9.2.5 使用注解 ###

@javax.servlet.annotation.WebFilter中包含了取代部署描述符所有选项的特性。

### 9.2.6 使用编程式配置 ###
可以再ServletContext中以编程的方式配置过滤器。不使用部署描述符和注解，调用ServletContext的方法注册和映射过滤器即可。因为必须要再ServletContext结束启动之前完成，所以通常需要再ServletContextListener的contextInitialized方法中实现。

## 9.3 过滤器排序 ##

### 9.3.1 URL模式映射和Servlet名称映射 ###

### 9.3.2 演示过滤器顺序 ###

### 9.3.3 使用过滤器处理异步请求 ###


## 4.4 结合使用Servlet和JSP ##

### 4.4.1 配置部署描述符中的JSP属性 ###

	<jsp-config>
		<jsp-property-group>
			<url-pattern>*.jsp</url-pattern>
			<url-pattern>*.jspf</url-pattern>
			<page-encoding>UFT-8</page-encoding>
			<scripting-invalid>false</script-invalid>
			<include-prelude>/WEB-INF/jsp/base.jspf</include-prelude>
			<trim-directive-whitespaces>true</trim-directive-whitespaces>
			<default-content-type>text/html</default-content-type>
		</jsp-property-group>
	</jsp-config>
#### 1.了解JSP属性组 ####

#### 2.使用JSP属性 ####

### 4.4.2 将Servlet中的请求转发给JSP ###
结合使用Servlet和JSP时的一种典型模式就是，由Servlet接收请求，实现业务逻辑处理以及必须的数据，创建可以由JSP轻松处理的数据模型，最终将请求转发给JSP。

#### 1.使用请求派发器 ####
引入了HttpServletRequest的一个新特性，通过方法getRequestDispatcher可以获得一个javax.servlet.RequestDispatcher，可用于处理针对指定路径下的内部转发和包含。通过该对象，可以将当前请求转发给调用forward方法的JSP。在调用forward之后，Servlet代码将无法再操作响应对象。
#### 2.设计表示层 ####

#### 3.测试修改后的客户支持应用程序 ####

## 4.5 关于JSP文档（JSPX）的注意事项 ##

* 所有的语法使用的都是jsp标签。指令、声明、脚本和表达式现在都是XML标签了，但使用jsp命名空间前缀。唯一的例外时标签库指令，它变成了根文档标签的特性。
* 现在无法区分JSP注释和XML注释了。所有的注释都是XML注释（当然，在声明和脚本仍然可以使用Java注释）。

## 4.6 小结 ##
# 第4章 使用JSP显示页面内容 #

## 4.1 使用<br/>代替output.println("<br/>") ##

### 4.1.1 使用JSP的原因 ###

JSP

### 4.1.2 JSP在运行时的处理 ###

JSP实际上只是一个精心设计的Servlet。在运行时，JSP代码将由JSP编译器进行转换，它将解析出JSP代码的所有特性，并将它们转换成Java代码。由JSP创建得到的Java类都将实现Servlet。然后，该Java代码将与普通Java代码一样经历相通的生命周期。



## 4.2 创建第一个JSP ##



### 4.2.1 了解文件结构 ###

### 4.2.2 指令、声明、脚本和表达式 ###

<%@ 这是一个指令 %>
<%! 这是一个声明 %>
<% 这是一个脚本 %>
<%= 这是一个表达式 %>

#### 1. 使用指令 ####

#### 2. 使用声明 ####

#### 3. 使用脚本 ####

#### 4. 使用表达式 ####

#### 5. 结合使用所有技术 ####



### 4.2.3 注释代码 ###

* XML注释
* 传统的Java行注释
* 传统的Java块注释
* JSP注释

之前3钟的注释内容都将出现在JSP Servlet java文件中，最后一种注释类型不会。

### 4.2.4 在JSP中导入类 ###

	<%@ page import="java.util.Map" %>
	<%@ page import="java.util.List" %>
	<%@ page import="java.io.IOException" %>

对于不产生输出的JSP标记、指令、声明和脚本，将会在客户端输出一行空白。所以，如果在变量声明和脚本呢之前有许多导入类的page指令，那么将会在输出中显示出数行空白。为了解决这个问题，需要将一个标记的尾部与另一个标记的头部链接在一起。
	
	<%@ page import="java.util.Map" %><%@ page import="java.util.List" %><%@ page import="java.io.IOException" %>
	
### 4.2.5 使用指令 ###

#### 1.修改页面属性 ####

**pageEncoding**

指定JSP所使用的字符编码，等同于HttpServletResponse中的setCharacterEncoding方法。可以在page指令中使用contentType="text/html" page

**session**

**isELIgnored**

**buffer和autoFlush**

特性buffer指定了JSP缓存的大小，或者为“none”（不会缓存任何输出），而autoFlush则表示是否在它达到大小限制之后自动刷新缓存。

“8kb”和真。

**errorPage**

错误页

isErrorPage

**isThreadSafe**

默认为真该特性表示当前的JSP可以安全地同时处理多个请求。如果修改为假，容器将把请求逐个发送到该JSP。*永远不要修改这个这个设置，记住“如果你的JSP不是线程安全的，那么它就是错误的”*

extends

指定了当前JSP Servlet的父类。使用了这个特性的JSP将无法从一个Web容器迁移到另一个容器。

其他特性

#### 2.包含其他JSP ####

在这个JSP文件中包含其他JSP是很简单的，但需要记住一些有趣的规则和选项。第一个可用于包含其他JSP的指令是include指令。

	<@ include file="path"/to/some/file.jsp" %>
特性file将为容器提供需要包含的JSP文件的路径。

在JSP被转换成Java之前，编译器将使用被包含JSP文件的内容替换include指令。在此之后，合并后的JSP文件将被转换成Java代码并编译。

TODO 证明

#### 3.包含标签库 ####

taglib指令引用该标签库即可。

	<%@ taglib url="http://java.sun.com/jsp/jstl/core" prefix="c" %>

prefix定义了引用库中标签时使用的别名

### 4.2.6 使用<jsp>标签 ###

所有JSP都支持一种以XMLNS为前缀的特殊jsp标签。

	<jsp:forward page="/some/other/page.jsp" />

这不是重定向，客户端浏览器无法看到这个变化

<jsp:useBean> 声明一个JavaBean

<jsp:getProperty> 从使用<jsp:useBean>声明的bean中获取属性值（通过getter方法）

<jsp:setProperty> 将用于设置该实例的属性（使用setter方法） 

## 4.3 在JSP中使用Java（以及不鼓励使用Java的原因） ##

### 4.3.1 使用JSP中隐式的变量 ###

#### 1.request和response ####

变量request是HttpServleRequest的一个实例，而response则是HttpServletResponse的一个实例。

#### 2.session ####

该变量是HttpSession的一个实例。page指令，它有个session特性，并且默认值为真，默认在所有的JSP中都可以使用session变量。为假时，JSP中就没有session变量的定义。

#### 3.out ####

变量out是JspWriter的一个实例，在所有的JSP中都可以使用。如同通过调用HttpServletResponse的getWriter方法获得Writer一样。

#### 4.application ####

它是ServletContext接口的一个实例。提供了对Web应用的程序配置的访问，包括上下文初始化参数。

#### 5.config ####

变量config是ServletConfig接口的一个实例。不同于application变量。

#### 6.pageContext ####

是PageContext类的一个实例，它提供了获取请求特性和会话特性值、访问请求和响应。

#### 7.page ####

它是java.lang.Object的一个变量，它代表了JSP Servlet对象的this变量。可以将它强制转换为Servlet对象，并使用Servlet接口中已定义的方法。它还实现了javax.servlet.jsp.JspPage（继承了Servlet）和javax.servlet.jsp.HttpJspPage(继承了JspPage)，所以也可以将对象强制转换为这两个接口，并使用其中定义的方法。

#### 8.exception ####

在page指令中可以通过将它的isErrorPage特性设置为真，表示该JSP的目的是用于处理错误。只有这样菜能够在JSP中使用exception变量

#### 9.使用隐式变量 ####

TODO 下面的没看，之后回看

### 4.3.2 不应该在JSP中使用Java的原因 ###

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
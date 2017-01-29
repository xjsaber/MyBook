# 第17章 创建RESTful和SOAP Web服务 #

## 17.1 了解Web服务 ##

### 17.1.1 最初的SOAP ###
SOAP源自于简单对象访问协议（Simple Object Access Protocol），他是一种使用XML消息形式结构化数据再机器与机器
### 17.1.2 RESTful Web提供了一种更简单的方式 ###


## 17.2 在SpringMVC中配置RESTful Web服务 ##

### 5.2.1 了解会话cookie ###
HTTP cookie。cookie是一种必要的通信机制，可以通过Set-Cookie响应头在服务器和浏览器之间传递任意的数据，并存储在用户计算机中，然后在通过请求头Cookie从浏览器返回到服务器中。
### 5.2.2 URL中的会话ID ###

<%@ 这是一个指令 %>
<%! 这是一个声明 %>
<% 这是一个脚本 %>
<%= 这是一个表达式 %>

#### 1. 使用指令 ####

#### 2. 使用声明 ####

#### 3. 使用脚本 ####

#### 4. 使用表达式 ####

#### 5. 结合使用所有技术 ####

### 5.2.3 会话的漏洞 ###

#### 1.复制并粘贴错误 ####

#### 2.会话固定 ####

### 4.2.4 在JSP中导入类 ###

### 4.2.5 使用指令 ###

#### 1.修改页面属性 ####

isELIgnored

buffer和autoFlush
“8kb”和真。

errorPage

isErrorPage

extends

其他特性

#### 2.包含其他JSP ####
在这个JSP文件中包含其他JSP是很简单的，但需要记住一些有趣的规则和选项。第一个可用于包含其他JSP的指令是include指令。

	<@ include file="path"/to/some/file.jsp" %>
特性file将为容器提供需要包含的JSP文件的路径。

#### 3.包含标签库 ####
taglib指令引用该标签库即可。

<%@ taglib url="http://java.sun.com/jsp/jstl/core" prefix="c" %>

### 4.2.6 使用<jsp>标签 ###
所有JSP都支持一种以XMLNS为前缀的特殊jsp标签。

	<jsp:forward page="/some/other/page.jsp" />
这不是重定向，客户端浏览器无法看到这个变化

<jsp:useBean> 声明一个JavaBean

<jsp:getProperty> 从使用<jsp:useBean>声明的bean中获取属性值（通过getter方法）

<jsp:setProperty> 将用于设置该实例的属性（使用setter方法） 

## 5.3 在会话中存储数据 ##

### 5.3.1 在部署描述符汇总配置会话 ###

#### 1.request和response ####
变量request是HttpServleRequest的一个实例，而response则是HttpServletResponse的一个实例。

#### 2.session ####
该变量是HttpSession的一个实例。page指令，它有个session特性，并且默认值为真，默认在所有的JSP中都可以使用session变量。为假时，JSP中就没有session变量的定义。

#### 3.out ####
变量out是JspWriter的一个实例，在所有的JSP中都可以使用。如同通过调用
#### 4.application ####
它是ServletContext接口的一个实例。
#### 5.config ####
变量config是ServletConfig接口的一个实例。不同于application变量。
#### 6.pageContext ####
是PageContext类的一个实例，它提供了获取请求特性和会话特性值、访问请求和响应。
#### 7.page ####
它是java.lang.Object的一个变量，它代表了JSP Servlet对象的this变量。可以将它强制转换为Servlet对象，并使用Servlet接口中已定义的方法。它还实现了javax.servlet.jsp.JspPage（继承了Servlet）和javax.servlet.jsp.HttpJspPage(继承了JspPage)，所以也可以将对象强制转换为这两个接口，并使用其中定义的方法。
#### 8.exception ####
在page指令中可以通过将它的isErrorPage特性设置为真，表示该JSP的目的是用于处理错误。只有这样菜能够在JSP中使用exception变量
#### 9.使用隐式变量 ####

### 5.3.2 存储和获取数据 ###

#### 1.在Servlet中使用会话 ####

#### 2.在JSP中使用会话 ####

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
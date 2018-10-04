# 第14章 使用服务和仓库支持控制器 #

## 14.1 了解模型-视图-控制器模式与控制器-服务-仓库模式 ##

Spring MVC控制器，可以使用它替换Servlet。

* 验证
* 警告
* 数据持久化

视图 发送命令到控制器 控制器
控制器 读取或操作数据 模型
模型 将数据发送到控制器 控制器
控制器 将模型发送到视图 视图

### 14.1.1 识别程序逻辑的不同类型 ###


### 14.1.2 使用仓库提供持久化逻辑 ###

在控制器-服务-仓库模式中，仓库是最低的一层，它负责所有的持久化逻辑，将数据保存到数据存储中并从数据存储中读取已保存的数据。使用@Repository注解标记出仓库，表示它的语义目的。

启用了组件扫描之后，@Repository类所属的Spring应用上下文将自动实例化、注入和管理这些仓库。（通常，每个仓库负责一种持久化和实体）

### 14.1.3 使用服务提供业务逻辑 ###

服务是仓库之上的下一层。服务封装了应用程序的业务逻辑，它将使用其他服务和仓库，但不能使用更高层应用程序层的资源，例如控制器。服务被标记上了@Service注解，使它们可以自动实例化和依赖注入，另外它

### 14.1.4 使用控制器提供用户界面逻辑 ###

控制器是控制器-服务-仓库模式中食物链的顶层。

在MVC模式中，服务和仓库被认为是控制器（不是@Controller）的一部分。@Controller依赖的@Service、@Service依赖的@Repository与所有居于这些组件之间的缓存层将@Controller——可以是一个WebGUI或者Web服务API——将模型的必要部分传递给视图用于渲染。该视图可以是一个JSP（对于WebGUI），或者JSON或XML渲染引擎。

## 14.2 使用根应用上下文代替Web应用上下文 ##

### 14.2.1 在多用户界面中重用根应用上下文 ###
通常根据不同的用户界面讲共享相同的服务。通过这种方式，业务逻辑将在所有的用户之间保持不变。为了实现这一点，不应该在Web应用上下文中管理服务和仓库，而是应该在根应用上下文中，是所有Web应用上下文的父亲。所有这些服务和仓库都将被控制各种不同用户界面的Web应用上下文所继承。如果希望使用为Web应用程序编写的服务和仓库编写桌面应用程序，那么可以使用相同的根应用上下文配置，然后使用不同的机制启动。

组件扫描基于两个准则进行工作：包扫描和类过滤。

### 13.2.2 将业务逻辑从控制器移动到服务 ###


#### 1.配置视图名称转换 ####
可以在需要地时候创建自己地RequestToViewNameTranslator,但是默认地DefaultRequestToViewNameTranslator通常是足够地。它将去除Web应用上下文URL和URL结尾的任何文件扩展名。
#### 2.使用@ModelAttribute ####
注解@ModelAttribute将告示Spring返回的User应该被添加到模型的特性健currentUser上。

## 13.2.3 返回响应实体 ##

### 1.配置消息转换器 ###
当服务器收到包含了请求正文的POST或PUT请求时，该正文通常被称为HTTP实体或请求实体，但也可以被称为消息。该消息可能是任意一种格式，它们必须被转换成控制器方法可以处理的某种类型的Java对象。这将根据请求的Content-Type头实现。

ByteArrayHttpMessageConverter、StringHttpMessageConverter、FormHttpMessageConverter和SourceHttpMessageConverter是所有Spring会自动配置的转换器。按照它们通常出现的顺序进行配置。**顺序非常重要。**因为有得转换器有更宽的MIME类型和Java类型范围，这可能会屏蔽我们希望使用的转换器。

配置类中注入的org.springframework.oxm.Marshaller、org.springframework.oxm.Unmarshaller和ObjectMapper

为了配置这些bean并在应用程序中共享，在RootContextConfiguration中创建。因为org.springframework.oxm.jaxb.Jaxb2Marshaller既是Marshaller也是Unmarshaller。，所以它将同时负责两个任务。`

### 2.配置内容协商 ###
内容协商是客户端向服务器传达一个优先使用的响应内容类型的列表（按照优先级顺序），然后服务器将从其中选择一个合适的内容类型（如果没有合适的就是用默认类型）的过程。

分析清楚客户端希望使用的数据格式。如果请求包含了一个请求实体，那么它也会有该实体的Content-Type，但这并非必须是客户端希望返回的响应格式（尽管通常是这样）。然后还有Accpt头，用于表示客户端希望接收的响应格式。

1. 寻找请求URL上的文件扩展名。如果它包含了文件扩展名（例如.html、.xml、.json等），那么它将根据该扩展名决定被请求的格式。如果它未包含文件扩展名，或者如果文件扩展名不能被识别，那么它将继续执行下一步。
2. 接下来Spring将寻找名为format的请求参数。如果它存在，那么它将使用被请求的格式（html、xml、json等）。如果format参数不存在或不被识别，它将继续执行下一步。
3. Spring将使用Accept头确定希望返回的响应格式。

### 3.使用@ResponseBody ###
使用@ReonseBody，它将触发已配置的内容协商策略。

Jaxb2Marshaller可以将User对象转换成它的XML表示，这是因为User类上填加了@javax.xml.bind.annotation.XmlRootElement注解。
## 13.3 使用表单对象简化开发 ##

### 13.3.1 在模型中添加表单对象 ###
将表单对象和业务对象分隔开。

### 13.3.2 使用Spring Framework <form>标签 ###

	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

* <form:errors>等同于一个<span>，它将被关联到自动表单对象验证
* <form:label>表示字段的标签文本，它等同于<label>
* <form:hidden>等同于<input type="hidden">
* <form:input>等同于<input type="text">
* 
* <form:textarea>等同于<textarea>
* <form:checkbox>等同于<input type="checkbox">，它可以支持多种类型的属性，例如boolean、Boolean和数字类型
* <form:checkboxes>等同于<input type="checkbox">，它可以支持多种类型的属性，例如boolean、Boolean和数字类型
* <form:radiobutton>等同于<input type="radio">。通常，需要将两个或多个该标签绑定到相同的路径（表单对象属性），Spring将根据属性值自动选择正确的标签。
* <form:checkboxes>等同于<form:checkboxes>一样，<form:radiobuttons>等同于<from:radiobuttons>。它们有着相同的items、itemValue和itemLabel特性，用于帮助生成单选按钮。<form:radiobuttons>和<form:checkboxes>都非常有利于枚举派生出字段。
* <form:select>等同于一个<select>下拉列表或多选框。它可以与<form:option>和<form:options>一起使用。它将根据下拉列表被绑定到的路径的值自动选择正确的选项。
* <form:option>将被嵌套在一个<form:select>中，等同于<option>
* <form:options>如同<form:checkboxes>和<form:radiobuttons>一样，有items、itemValue和itemLabel特性

### 13.3.3 获得被提交的表单数据 ###
   
## 13.4 更新客户支持应用开发程序 ##

### 13.4.1 启用Multipart支持 ###
Configuration类已经被移除，该类是一个ServletContextListener，它以编程的发过誓配置了LoggingFilter和AuthenticationFilter。Bootstrap类都是对过滤器（之前在Configurator中）进行配置的。

### 13.4.2 将Servlet转换成Spring MVC控制器 ###

### 13.4.3 创建自定义下载视图 ###

## 13.5 小结 ##



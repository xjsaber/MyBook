# 第13章 使用控制器替代Servlet #

## 13.1 了解@RequestMapping ##

@RequestMapping注解将请求映射到控制器中的方法。

### 13.1.1 使用@RequestMapping特性索性缩小请求匹配的范围 ###

@RequestMapping注解将把请求被映射到的方法缩小到特定的方法上。

#### 1.URL限制 ####

控制器方法URL映射到通过DispatcherServlet、控制器映射（如果可用）或者方法映射进行构建，如果未指定的话，所有这些映射都将通过前斜线进行分隔。

#### 2.HTTP请求方法限制 ####

	@RequestMapping(value="add", method=RequestMethod.GET)

GET

	@RequestMapping(value="add", method=RequestMethod.POST)

POST

#### 3. 请求参数限制 ####

#### 4. 请求头限制 ####

	@RequestMapping(value="user", headers={"X-Client", "content-type=text/*"})

#### 5. 内容类型限制 ####



### 13.1.2 指定控制器方法参数 ###

#### 1.标准Servlet类型 ####

* HttpServletRequest 用于使用请求属性
* HttpServletResponse 用于操作响应
* HttpSession 用于操作HTTP会话对象
* InputStream或者Reader用于读取请求正文，但不能同时使用两者。在完成对它的处理之后不应该关闭该对象。
* OutputStream或者Writer用于编写响应正文，但不能同时使用两者。在完成对它的处理之后不应该关闭该对象。
* org.springframework.web.context.request.WebRequest 用于请求属性和HTTP会话对象的操作。

#### 2.注解请求属性 ####

required=false(可选参数)
defaultValue=""（默认值为空白）不填的话，默认值为null

通过在Map<String, String>或者org.springframework.util.MultiValueMap<String, String>类型的单个参数上标注@RequestPram，也可以获得Map中的所有请求参数值。

@RequestHeader的工作方式与@RequestParam一致，它提供了对请求头的值的访问。它指定了一个必须的（默认）或者可选的请求头，用作相应方法的参数值。


#### 3.输入绑定表单对象 ####

Spring Framework允许指定一个表单对象（也称为命令对象）作为控制器方法的参数。表单对象是含有设置和读取方法的简单POJO。他们不必事先实现任何特殊接口，也不需要使用任何特殊的注解对控制器方法参水进行标记，Spring将把它识别为一个表单对象。

Spring将在类中寻找方法名为set开头的方法。然后它将使用参数名称把请求参数映射到表单对象属性。Spring也可以自动验证表单对象的细节，避免在控制器方法中内嵌验证逻辑。

#### 4.请求正文转换和实体 ####


#### 5.Multipart请求数据 ####

请求的Content-Type为multipart/form-data，并且它包含了被提交的每个表单字段的一部分。请求的每个部分都由指定的边界分隔开，它们都有一个值为form-data的Content-Disposition和匹配表单输入名称的名字。

#### 6.模型类型 ####

控制器方法可以有单个类型为Map<String, Ojbect>、org.springframework.ui.ModelMap或org.springframework.ui.Model的非标注参数。这些类型之一的方法参数代表了Spring传入到视图中用于渲染的模型，并且可以在方法的执行时向其中添加任意的特性。

### 13.1.3 为控制器方法选择有效的返回类型 ###

方法参数通常与请求内容相关，返回类型通常与响应相关。

#### 1.模型类型 ####

控制器方法可以返回一个Map<String, Object>、ModelMap或Model。Spring可以将该返回类型识别为模型，并使用已配置的org.springframework.web.servlet.view.DefaultRequestToViewNameTranslator（默认为org.springframework.web.servlet.view.DefaultRequestToViewNameTranslator）自动确定视图。

#### 2.视图类型 ####

控制器方法可以返回许多不同的视图类型。
接口org.springframework.web.servlet.View（或者任意实现了View的类）表示方法将返回一个显式的视图对象。

#### 3.响应正文实体 ####

正如请求正文可以包含HTTP实体（请求实体）一样，响应正文也可以包含HTTP实体（响应实体）。控制器方法可以返回HttpEntity<?>或者org.springframework.http.ResponseEntity<?>，Spring将把实体中的正文对象转换为正确的响应内容（基于协商的内容类型，使用适合的HTTP消息转换器进行转换）。

HttpEntity允许设置响应正文和各种不同的头。ResponseEntity继承了HttpEntity，并添加了设置响应状态代码的能力，响应状态代码的类型应为org.springframework.http.HttpStatus。

@ReponseBody（正文对象）、@ResponseStatus（状态码，如果不使用@ReponseStatus的话，默认值为200 OK）

#### 4.任意返回类型 ####

控制器方法可以返回任何其他对象。Spring将假设该对象是模型中的一个特性。它将使用返回类型类名称的驼峰命名法版本作为模型特性名称，除非该方法注解了@ModelAttribute，此时Spring将使用注解中指定的名称作为特性名。

#### 5.异步类型 ####

控制器方法可以返回java.util.concurrent.Callable<?>或org.springframework.web.context.request.async.DeferredResult<?>。

## 13.2 使用SpringFramework的模型和视图模式 ##

Spring Framework的MVC架构名称的来源是因为它依赖于模型-视图-控制器（MVC）设计模式。

控制器将操作模型的数据（用户感兴趣的信息），并将模型传递给视图，而视图将以某种有用的方式对模型进行渲染。用户只会知道与视图进行交互，但在执行某种操作时不回知道它在与控制器交互。Servlet可被认为是一个控制器，在请求到达时将代表用户执行操作。Servlet将操作HttpServletRequest特性形式的模型，然后将模型传递给视图JSP用于渲染。

Spring将再继续执行两个步骤，将模型从请求中完全分离开（Map<String, Object>、ModelMap或Model），并提供可以通过无限多种方式实现的高级View接口。

* InternalResouceView和JstlView分别实现传统的JSP和JSTL增强JSP视图。它们负责将模型特性转换成请求特性，并将请求转发到正确的JSP。
* FreeMarketView（支持FreeMarker模板引擎）
* VelocityView（支持Apache Velocity模板引擎）
* TilesView（支持Apache Tiles模板引擎）

需要将模型转换为JSON或XML——通常用于RESTful Web服务和支持Ajax的请求终端——Spring提供了MappingJackson2JsonView和MarshallingView（支持JSON和XML）。

* 当控制器方法返回一个View、或者ModelAndView（将View的实现传入到ModelAndView构造器中）的实现时，Spring将直接使用该View，并且不需要额外的逻辑用于判断如何向客户端展示模型。
* 如果控制器方法返回了一个字符串视图名称或者使用字符串视图名称构造的ModelAndView，Spring必须使用已配置的org.springframework.web.servlet.ViewResolver将视图名称解析成一个真正的视图。
* 如果方法返回的是模型或者模型特性，Spring首先必须使用已配置的RequesstToViewNameTranslator隐式地将请求转换成视图名称，然后使用ViewResolver解析已命名地视图。
* 当控制器方法返回地是响应实体ResponseEntity或者HttpEntity时，Spring将使用内容协商决定将实体展示到哪个视图中。

### 13.2.1 使用显式的视图和视图名称 ###

Model-View-Controller项目将采用编程的方式配置和启动：使用WebApplicationInitializer启动Spring Framework，它配置了两个@Configuration类：RootContextConfiguration和ServletContextConfiguration。

#### 1.使用重定向视图 ####

显式返回的最常见视图就是org.springframework.web.servlet.view.RedirectView，它用于将客户端请求发送到一个不同的URL上。如果URL以某种协议（http：//、https://等）或者网络前缀（//）开头，它将被认为式一个绝对URL。如果

	@RequestMapping("/")
	public View home(Map<String, Object> Model)
	{
		model.put("dashboardUrl", "dashboard");
		return new RedirectView("/{dashboardUrl}", true);
	}

#### 2.配置视图解析 ####

创建一个视图解析器（在派发器servlet应用上下文中实例化一个框架bean），此时，应该使用InternalResourceViewResolver，将把视图名称转换成JSP文件名。

	InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	resolver.setViewClass(JstlView.class);
	resolver.setPrefix("/WEB-INF/jsp/view/");
	resolver.setSuffix(".jsp");

视图解析器将使用前缀/WEB-INF/jsp/view加上视图名称再加上.jsp的方式构造JSP文件名。

要将bean命名为viewResolver，因此必须将@Bean方法命名为viewResolver。

#### 3.创建JSP视图 ####

JSP视图通过JstlView显式出了模型的内容。

### 13.2.2 使用同一个代码库的多个用户界面 ###

RequestToViewnameTranslator bean和ViewResolver。

#### 1.配置视图名称转换 ####

可以在需要地时候创建自己地RequestToViewNameTranslator,但是默认地DefaultRequestToViewNameTranslator通常是足够地。它将去除Web应用上下文URL和URL结尾的任何文件扩展名。

可以自定义DefaultRequestToViewNameTranslator如何使用各种不同的配置方法对名称转换，但在这里的默认的配置就足够了。必须将该bean命名为viewNameTranslator，所以也可以将@Bean方法命名为viewNameTranslator

#### 2.使用@ModelAttribute ####

注解@ModelAttribute将告示Spring返回的User应该被添加到模型的特性健currentUser上。

## 13.2.3 返回响应实体 ##

获得请求实体并返回响应实体通常是RESTful Web服务或其他自动化任务的保留任务。在大多数情况下，只有在JavaScript应用程序发出AjaxGET或者POST请求时浏览器才回被牵涉进来。

### 1.配置消息转换器 ###

当服务器收到包含了请求正文的POST或PUT请求时，该正文通常被称为HTTP实体或请求实体，但也可以被称为消息。该消息可能是任意一种格式，它们必须被转换成控制器方法可以处理的某种类型的Java对象。这将根据请求的Content-Type头实现。

org.springframework.http.converter.FromHttpMessageConverter负责将x-www-from-urlencoded消息转换成控制器方法可以处理表单对象。

消息转换器的工作方式有两种：将进入的消息转换成Java对象，或者将Java对象转换成发出的消息。根据已识别的MIME内容类型和目标Java类型的简单原则进行操作。每个转换器都可以支持一种或者多种MIME和Java类型，并且可以将这些MIME类型的消息转换成这些类型的Java对象，并再转换回来。

如果未手动配置任何消息转换器的话，Spring Framework将自动创建特定的消息转换器。在许多情况下，这个自动产生的配置是足够的。处于演示和额外配置的目的，Model-View-Controller将手动配置一个消息转换器。为了实现这一点，ServletContextConfiguration类必须继承WebMvcConfigurerAdapter，并重写configuremessageConverters方法。

	public class ServletContextConfiguration extends WebMvcConfigurerAdapter {
		
	}

ByteArrayHttpMessageConverter、StringHttpMessageConverter、FormHttpMessageConverter和SourceHttpMessageConverter是所有Spring会自动配置的转换器。按照它们通常出现的顺序进行配置。**顺序非常重要。**因为有得转换器有更宽的MIME类型和Java类型范围，这可能会屏蔽我们希望使用的转换器。

通常不会将MarshallingHttpmessage
Converter添加到消息转换器列表中，但此时添加了它，用于支持XML实体的转换。MappingJackson2HttpMessageConverter通常会自动创建。将使用默认的无配置的com.fasterxml.jacson.databind.ObjectMapper创建，并且只支持application/json MIME内容

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

	@RequestMapping(value="/user/{userId}", method=RequestMedhod.GET)
	@ResponseBody
	public User getUser(@PathVariable("userId") long userId)

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



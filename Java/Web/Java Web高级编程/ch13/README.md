# 第13章 使用控制器替代Servlet #

## 13.1 了解@RequestMapping ##
@RequestMapping注解将请求映射到控制器中的方法。
### 13.1.1 使用@RequestMapping特性索性缩小请求匹配的范围 ###
@RequestMapping注解将把请求被映射到的方法缩小到特定的方法上。

#### 1.URL限制 ####
控制器方法URL映射到通过DispatcherServlet、控制器映射（如果可用）或者方法映射进行构建，如果未指定的话，所有这些映射都将通过前斜线进行分隔。

#### 2.HTTP请求方法限制 ####

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

* 当控制器方法返回一个View、或者ModelAndView（将View的实现传入到ModelAndView构造器中）的实现时，Spring将直接使用该View，并且不需要额外的逻辑用于判断如何向客户端展示模型。
* 如果控制器方法返回了一个字符串视图名称或者使用字符串视图名称构造的ModelAndView，Spring必须使用已配置的org.springframework.web.servlet.ViewResolver将视图名称解析成一个真正的视图。
* 如果方法返回的是模型或者模型特性，Spring首先必须使用已配置的RequesstToViewNameTranslator隐式地将请求转换成视图名称，然后使用ViewResolver解析已命名地视图。
* 当控制器方法返回地是响应实体ResponseEntity或者HttpEntity时，Spring将使用内容协商决定将实体展示到哪个视图中。

### 13.2.1 使用显式的视图和视图名称 ###
Model-View-Controller项目将采用编程的方式配置和启动：使用WebApplicationInitializer启动Spring Framework，它配置了两个@Configuration类：RootContextConfiguration和ServletContextConfiguration。

#### 1.使用重定向视图 ####
显式返回的最常见视图就是org.springframework.web.servlet.view.RedirectView，它用于将客户端请求发送到一个不同的URL上。如果URL以某种协议（http：//、https://等）或者网络前缀（//）开头，它将被认为式一个绝对URL。如果

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
#### 2.使用@ModelAttribute ####
注解@ModelAttribute将告示Spring返回的User应该被添加到模型的特性健currentUser上。

## 13.2.3 返回响应实体 ##

### 1.配置消息转换器 ###
当服务器收到包含了请求正文的POST或PUT请求时，该正文通常被称为HTTP实体或请求实体，但也可以被称为消息。该消息可能是任意一种格式，它们必须被转换成控制器方法可以处理的某种类型的Java对象。这将根据请求的Content-Type头实现。

## 13.3 使用表单对象简化开发 ##

## 13.4 更新客户支持应用开发程序 ##

### 13.4.1 启用Multipart支持 ###


### 12.5.1 创建XML配置 ###

    <mvc:annotation-driven />

    <bean name="greetingServiceImpl" class="com.wrox.GreetingServiceImpl" />

    <bean name="helloController" class="com.wrox.HelloController">
        <property name="greetingService" ref="greetingServiceImpl" />
    </bean>

将告诉Spring实例化GreetingServiceImpl和HelloCOntroller，并将greetingServiceImpl bean注入到helloController bean的greetingService属性中。

元素`<mvc:annotation-driven />`将指示Spring使用请求映射到控制器方法上。使用`<mvc:annotation-driven />`元素实际上会在幕后创建出特定的bean。

### 12.5.2 创建混合配置 ###
混合配置的核心时组件扫描和注解配置的概念。通过使用组件扫描，Spring将扫描通过特定注解指定的包查找类。所有标注了@org.springframework.stereotype.Component的类（在这些包中），都将变成由Spring管理的bean，这意味着Spring将实例化他们并注入他们的依赖。

任何标注了@Component的注解都将变成组件注解，标注了@Controller、@Repository和@Service的类也将成为Spring管理的bean。

@org.springframework.beans.factoryannotation.Autowired。可以为任何私有、保护和公开字段或者接受一个或多个参数的公开设置方法标注@Autowired。@Autowired声明了S-pring应该都在实例化应该在实例化之后注入的依赖，并且它也可以用于标注构造器。

Spring无法为依赖找到匹配的bean，它将抛出并记录一个异常，然后启动失败；如果它为依赖找到多个匹配的bean，它也将抛出并记录一个异常，然后启动失败，可以使用@org.springframework.beans.factory.annotation.Qualifier或@org.springframework.context.annotation.Primary注解避免第二个问题。@Qualifier可以指定应该使用的额bean的名字，也可以使用@Primary标记一个组件标注的bean，表示在出现多个符合依赖的候选bean时应该优先使用它。

## 12.6 使用bean definition profile ##

### 12.6.1 了解profile的工作远离 ###

### 12.6.2 考虑反模式和安全问题 ###

## 12.7 小结 ##
Spring Framework，bean，应用上下文和派发器Servlet，讲解了依赖注入（DI）和反转控制（IoC）、面向切面编程、事务管理、发布-订阅应用程序消息和Spring的MVC框架。
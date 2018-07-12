# 第12章 介绍Spring Framework #

## 12.1 Spring Framework简介 ##

### 12.1.1 反转控制和依赖注入 ###

反转控制（IoC）和依赖注入（DI）。

IoC是一个软件设计模式：组装器将在运行时而不是编译时绑定对象。

一段程序代码（Spring Framework的一个类）可以声明它依赖于另一块程序代码（一个接口），然后组装器可以在运行时注入它依赖的实例（通常并不总是单例）。

### 12.1.2 面向切面编程 ###

因为Spring Framework负责处理实例化和依赖注入，通过封装注入依赖的实例，使用其他行为对方法调用进行装饰。

### 12.1.3 数据访问和事务管理 ###

对Java Persistent APJ（JPA）和对象关系映射的广泛支持。

Spring Data，在关系数据库以及MongoDB和Redis这样的NoSQL数据库持久化变成了一个简单的任务。

### 12.1.4 应用程序消息 ###

Spring Framework提供了一个松耦合的消息系统，它使用的是发布-订阅模式：系统中的组件通过订阅消息，声明它对该消息感兴趣，然后这些消息的生产者将会发布该消息，而无须关心谁对消息感兴趣。使用Spring Framework时，一个由Spring管理的bean可以通过实现一个通用接口订阅特定的消息类型，其他由Spring管理的对象可以发布这些消息到Spring Framework中，然后由Spring Framework将消息发送到已订阅的bean中。该系统也可以被扩展和配置为跨应用程序的群几种发布消息。

### 12.1.5 Web应用程序和模型-视图-控制器模式 ###

提供了一个模型-视图-控制器（MVC）模式框架，简化创建交互式Web应用程序的过程。

隐藏Servlet、HttpServletRequest\HttpServletResponse以及JSP转发，Spring将处理这些任务。

模型将以Map<String, Object>的形式从控制器传递到视图，控制器返回的视图或视图名称将使Spring把模型转发到合适的JSP视图。请求和URL路径参数将被自动转换为原始或复杂的控制器方法参数。

## 12.2 使用SpringFramework的原因 ##

### 12.2.1 逻辑代码分组 ###

一个处理用户配置的Servlet可以有数十个方法，每个含有不同路由的逻辑的方法都将添加到doGet和doPost的方法中。

使用Spring的Web MVC框架时，控制器类的行为非常像使用方法级别映射的Servlet。每个方法都可以拥有一个指向特定URL、请求方法、参数存在性、头的值、内容类型和/或期望相应类型的唯一映射。

### 12.2.2 使用同一个代码库的多个用户界面 ###

## 12.3 了解应用上下文 ##

Spring Framework容器以一个或多个应用上下文的形式存在，由org.springframework.context.ApplicationContext接口表示。

一个应用上下文管理着一组bean、执行业务逻辑的Java对象、执行任务、持久化和获取持久化数据、响应HTTP请求等。由Spring管理的bean可以自动进行依赖注入、消息通知、定时方法执行、bean验证和执行其他关键的Spring服务。

一个Spring应用程序至少需要一个应用上下文，并且有时这就是所有的要求。不过，也可以使用由多个应用上下文组成的层次结构。在这样的层次结构中，任何由Spring管理的bean都可以访问相同的应用上下文、父亲的父亲应用上下文中的bean。

ApplicationContext
* ConfigurableApplicationContext使可配置的，而基本的ApplicationContext是只读的。
* org.springframework.web.context.WebApplicationContext和ConfigurableWebApplicationContext接口被设计用于Servlet容器中运行的JavaEE Web应用程序，它们提供了对底层ServletContext和ServletConfig（如果可以的话）的访问
* 具体类ClassPathXmlApplicationContext和FileSystemXmlApplicationContext被设计用于在独立运行的应用程序中从XML文件加载Spring配置，而XmlWebApplicationContext被设计用于在JavaEE Web应用程序中实现相同的目标。
* 如果需要使用Java而不是使用XML以编程方式对Spring进行配置，那么AnnotationConfigApplication和AnnotationConfigWebApplicationContext类分别可用于独立运行的应用程序和JavaEE Web应用程序中

每个DispatcherServlet实例都有自己的应用上下文，其中包含了对Web应用程序的ServletContext和它自己的ServletConfig的引用。整个Web应用程序的全局应用上下文是所有DispatcherServlet应用上下文的父亲，它将通过org.springframework.web.context.ContextLoaderListener创建。

## 12.4 启动Spring Framework ##

### 12.4.1 使用部署描述符启动Spring(XML启动) ###

传统的Spring Framework应用程序总是使用JavaEE部署描述符启动Spring。至少，在配置文件中创建DispatcherServlet的一个实例，所以以contextConfigLocation

1. 创建DispatcherServlet的一个实例。
2. 以contextConfigLocation启动参数的形式为它提供配置文件。
3. 指示Spring在启动时加载它

ContextLoaderListener将在Web应用程序启动时被初始化（因为它实现了ServletContextListener，所以它将在所有的Servlet之前初始化），然后从contextCOnfigLocation上下文初始化参数指定的
rootContext.xml文件中加载根应用上下文，并启动根应用上下文。

### 12.4.2 在初始化器中使用编程的方式启动Spring（Java启动） ###

监听器的contextInitizlized方法可能在其他监听器之后调用。

JavaEE6中天加了一个新的接口ServletContainerInitializer。实现了ServletContainerInitializer。实现了ServletCOntainerInitializer接口的类将在应用程序开始启动时，并在所有监听器启动之前调用它们的onStartup方法。 

**派生器Servlet映射**

不要将DispatcherServlet映射到URL模式/*。在多数情况下，URL模式必须以星号开头或结尾，但在映射到应用程序根时，只使用前斜线/，不使用星号，就足以使Servlet响应应用程序的所有URL，并且Servlet容器的JSP机制仍然可以处理JSP请求。

如果计划将DispatcherServlet映射到应用程序根，当任何一个Servlet被映射到应用程序根时（不使用星号）。更具体的URL模式总是会覆盖它。

XML

	<servlet-mapping>
		<servlet-name>default</servlet-name>
		<url-pattern>/resource/*</url-pattern>
		<url-pattern>*.css</url-pattern>
		<url-pattern>*.js</url-pattern>
	</servlet-mapping>

Java

	servletContext.getServletRegistration("default").addMapping("/resources/*", "*.css", "*.js", "*.png", "*.git");

## 12.5 配置Sprng Framework ##

### 12.5.1 创建XML配置 ###

    <mvc:annotation-driven />

    <bean name="greetingServiceImpl" class="com.wrox.GreetingServiceImpl" />

    <bean name="helloController" class="com.wrox.HelloController">
        <property name="greetingService" ref="greetingServiceImpl" />
    </bean>

将告诉Spring实例化GreetingServiceImpl和HelloController，并将greetingServiceImpl bean注入到helloController bean的greetingService属性中。

元素`<mvc:annotation-driven />`将指示Spring使用@RequestMapping、@RequestBody、@RequestParam、@ResponseBody请求映射到控制器方法上。使用`<mvc:annotation-driven />`元素实际上会在幕后创建出特定的bean。

	<servlet-mapping>
		<serlvet-name>default</serlvet-name>
		<url-pattern>/resource/*</url-pattern>
	</servlet-mapping>

	<servlet>
		<servlet-name>springDispatcher</servlet-name>
	</servlet>

### 12.5.2 创建混合配置 ###

混合配置的核心时组件扫描和注解配置的概念。

通过使用组件扫描，Spring将扫描通过特定注解指定的包查找类。所有标注了@org.springframework.stereotype.Component的类（在这些包中），都将变成由Spring管理的bean，这意味着Spring将实例化他们并注入他们的依赖。

任何标注了@Component的注解都将变成组件注解，标注了@Controller、@Repository和@Service的类也将成为Spring管理的bean。

@org.springframework.beans.factoryannotation.Autowired。可以为任何私有、保护和公开字段或者接受一个或多个参数的公开设置方法标注@Autowired。@Autowired声明了Spring应该都在实例化应该在实例化之后注入的依赖，并且它也可以用于标注构造器。（通常由Spring管理的bean必须有无参构造器，但对于只含有一个标注了@Autowire的构造器的类，Spring将使用该构造器并注入所有的构造器参数）

Spring无法为依赖找到匹配的bean，它将抛出并记录一个异常，然后启动失败；如果它为依赖找到多个匹配的bean，它也将抛出并记录一个异常，然后启动失败，可以使用@org.springframework.beans.factory.annotation.Qualifier或@org.springframework.context.annotation.Primary注解避免第二个问题。@Qualifier可以指定应该使用的额bean的名字，也可以使用@Primary标记一个组件标注的bean，表示在出现多个符合依赖的候选bean时应该优先使用它。

有许多接口都继承了Aware，下面时最流行的一些接口：

* ApplicationEventPublisherAware 用于获得发布应用程序事件的bean。
* BeanFactroyAware 用于获得BeanFactory，通过它可以手动获得或创建bean。
* EnvironmentAware 用于获得Environment对象，通过它可以从属性源中获得属性。
* MessageSourceAware 用于获得国际化消息源。
* ServletSontextAware 用于获得JavaEE Web应用环境中的ServletContext。
* ServletConfigAware 用于获得DispatcherServlet Web应用上下文管理的bean ServletConfig。

通常在bean的所有依赖后，在它作为依赖被注入其他bean之前，可以在该bean上执行某种初始化操作。只需要使用org.springframework.beans.factory.InitializingBean接口。

### 12.5.3 使用@Configuration配置Spring ###

* XML配置难于调试。
* 不能对XML配置进行单元测试。

Spring Framework的纯Java配置可以通过编程的方式配置Spring容器。

AnnotationConfigWebApplicationContext启动编程式Spring配置。使用该类时，通过register方法注册配置类即可。这些配置类（必须标注上@org.springframework.context.annotation.Configuration，也必须有默认构造器）将通过标注了@Bean的无参方法注册bean。

@Configuration注解是标注了@Component的元数据注解，意味着@Configuration类可以使用@Autowired或@Inject进行依赖注入；可以实现Aware接口、InitializingBean或DisposableBean中的任意一个；也可以使用@PostConstruct和@PreDestory方法。如果@Configuration需要直接访问框架bean或者在另一个@Configuration类中创建bean。

如果需要在注入自己的依赖之后，但在Spring调用它的@Bean之前，初始化两个或多个被依赖的bean。

@ComponentScan

@EnableAspectAutoProxy

@EnableAsync

@EnableCache

@EnableLoadTimeWwaving

@EnableScheduling

@EnableSpringConfigured

@EnableTransactionManagement

@EnableWebMvc


## 12.6 使用bean definition profile ##

* 在一个多层次的应用环境中，需要让一些bean运行在一个层次中，而让另外一些bean运行在另一层中。

### 12.6.1 了解profile的工作远离 ###

Spring bean definition profile有两个组件：声明和激活。可以在XML配置文件中使用<beans>元素声明profile，或者在@Configutation类或@Components上使用@org.springfraework.context.anotation.Profile注解，或者同时使用这两种方式。

任意的<beans>中都可以包含profile特性，表示它的bean

### 12.6.2 考虑反模式和安全问题 ###

* 有没有更简单的方法可以实现相同的目标呢？

* 你的profile中存在的bean类型都有哪些？

* 使用bean definition profile的安全意义是什么？



## 12.7 小结 ##

Spring Framework，bean，应用上下文和派发器Servlet，讲解了依赖注入（DI）和反转控制（IoC）、面向切面编程、事务管理、发布-订阅应用程序消息和Spring的MVC框架。
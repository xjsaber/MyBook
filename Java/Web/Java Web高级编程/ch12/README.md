# 第12章 介绍Spring Framework #

## 12.1 Spring Framework简介 ##

### 12.1.1 反转控制和依赖注入 ###
反转控制（IoC）和依赖注入（DI）。一段程序代码（Spring Framework的一个类）可以声明它依赖于另一块程序代码（一个接口），然后组装器可以在运行时注入它依赖的实例（通常并不总是单例）。
### 12.1.2 面向切面编程 ###
负责实例化和依赖注入，通过封装注入依赖的实例，使用其他行为对方法调用进行装饰。
### 12.1.3 数据访问和事务管理 ###
对Java Persistent APJ（JPA）和对象关系映射的广泛支持。
### 12.1.4 应用程序消息 ###
Spring Framework提供了一个松耦合的消息系统，它使用的是发布-订阅模式：系统中的组件通过订阅消息，声明它对该消息感兴趣，然后这些消息的生产者将会发布该消息，而无须关心谁对消息感兴趣。使用Spring Framework时，一个由Spring管理的bean可以通过实现一个通用接口订阅特定的消息类型，其他由Spring管理的对象可以发布这些消息到Spring Framework中，然后由Spring Framework将消息发送到已订阅的bean中。该系统也可以被扩展和配置为跨应用程序的群几种发布消息。

### 12.1.5 Web应用程序和模型-视图-控制器模式 ###
提供了一个模型-视图-控制器（MVC）模式框架，简化创建交互式Web应用程序的过程。

模型将以Map<String, Object>的形式从控制器传递到视图，控制器返回的视图或视图名称将使Spring把模型转发到合适的JSP视图。
## 12.2 使用SpringFramework的原因 ##

### 12.2.1 逻辑代码分组 ###

### 12.2.2 使用同一个代码库的多个用户界面 ###
 

## 12.3 了解应用上下文 ##

一个应用上下文管理着一组bean、执行业务逻辑的Java对象、执行任务、持久化和获取持久化数据、响应HTTP请求等。由Spring管理的bean可以自动进行依赖注入、消息通知、定时方法执行、bean验证和执行其他关键的Spring服务。

一个Spring应用程序至少需要一个应用上下文。

ApplicationContext
* ConfigurableApplicationContext使可配置的，而基本的ApplicationContext是只读的。
* org.springframework.web.context.WebApplicationContext和ConfigurableWebApplicationContext接口被设计用于Servlet容器中运行的JavaEE Web应用程序，它们提供了对底层ServletContext和ServletConfig（如果可以的话）的访问
* 具体类ClassPathXmlApplicationContext和FileSystemXmlApplicationContext被设计用于在独立运行的应用程序中从XML文件加载Spring配置，而XmlWebApplicationContext被设计用于在JavaEE Web应用程序中实现相同的目标。
* 如果需要使用Java而不是使用XML以编程方式对Spring进行配置，那么AnnotationConfigApplication和AnnotationConfigWebApplicationContext类分别可用于独立运行的应用程序和JavaEE Web应用程序中

每个DispatcherServlet实例都有自己的应用上下文，其中包含了对Web应用程序的Servlet
## 12.4 启动Spring Framework ##

### 12.4.1 使用部署描述符启动Spring(XML启动) ###
传统的Spring Framework应用程序总是使用JavaEE部署描述符启动Spring。

1. 创建DispatcherServlet的一个实例。
2. 以contextConfigLocation启动参数的形式为它提供配置文件。
3. 指示Spring在启动时加载它

ContextLoaderListener将在Web应用程序启动时被初始化（因为它实现了ServletContextListener，所以它将在所有的Servlet之前初始化），然后从contextCOnfigLocation上下文初始化参数指定的
rootContext.xml文件中加载根应用上下文，并启动根应用上下文。

### 12.4.2 在初始化器中使用编程的方式启动Spring（Java启动） ###
监听器的contextInitizlized方法可能在其他监听器之后调用。JavaEE6中天加了一个新的接口ServletContainerInitializer。实现了ServletContainerInitializer。实现了ServletCOntainerInitializer接口的类将在应用程序开始启动时，并在所有监听器启动之前调用它们的onStartup方法。 

## 12.5 配置Sprng Framework ##

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

有许多接口都继承了Aware，下面时最流行的一些接口：

* ApplicationEventPublisherAware 用于获得发布应用程序事件的bean。
* BeanFactroyAware 用于获得BeanFactory，通过它可以手动获得或创建bean。
* EnvironmentAware 用于获得Environment对象，通过它可以手动或创建bean。
* MessageSourceAware 用于获得国际化消息源。
* ServletSontextAware 用于获得JavaEE Web应用环境中的ServletContext。
* ServletConfigAware 用于获得DispatcherServlet Web应用上下文管理的bean ServletConfig。

### 12.5.3 使用@Configuration配置Spring ###



## 12.6 使用bean definition profile ##

### 12.6.1 了解profile的工作远离 ###
Spring bean definition profile有两个组件：声明和激活。可以在XML配置文件中使用<beans>元素声明profile，或者在@Configutation类或@Components上使用@org.springfraework.context.anotation.Profile注解，火炬噢这同时使用
### 12.6.2 考虑反模式和安全问题 ###

## 12.7 小结 ##
Spring Framework，bean，应用上下文和派发器Servlet，讲解了依赖注入（DI）和反转控制（IoC）、面向切面编程、事务管理、发布-订阅应用程序消息和Spring的MVC框架。
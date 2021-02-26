# Spring实战（第5版） #

Part1. FOUNDATIONAL SPRING

# CH1 Spring起步

## 1.1 什么是Spring？

​	Spring的核心是提供了一个容器（container），通常称为Spring应用上下文（Spring application context），它们会创建和管理应用组件。这些组件也可以称为bean，会在Spring应用上下文中装配在一起，从而形成一个完整的应用程序。

	* 将bean装配在一起的行为是通过一种基于依赖注入（dependency injection，DI）的模式实现的。
	* 组件不会再去创建它所依赖的组件并管理它们的生命周期，使用依赖注入的应用依赖于单独的实体（容器）来创建和维护所有的组件，并将其注入到需要它们的bean中。通常，这是通过构造器参数和属性访问方法来实现的

XML方式和Java方式

1. 通过xml文件

   ```xml
   <bean id="inventorService" class="com.example.InventoryService" />
   <bean id="productService" class="com.example.ProductService" >
   	<constructor-arg ref="inventorService"/>
   </bean>
   ```

2. @Configuration注解会告知Spring这是一个配置类，会为Spring应用上下文提供bean。这个配置类的方法使用@Bean注解进行了标注，表明这些方法所返回的对象会以bean的形式添加到Spring的应用上下文中（默认情况下，这些bean所对应的bean ID与定义它们的方法名称是相同的）。

   ```java
   @Configuration
   public class ServiceConfiguration {
   	@Bean
   	public InventorService inventorSerivce() {
   		return new InventorSerivce();
   	}
   	@Bean
   	public ProductService productService() {
   		return new ProductService(inventorSerivce());
   	}
   }
   ```



在Spring技术中，自动配置起源于所谓的自动装配（autowiring）和组件扫描（component scanning）。

## 1.2 初始化Spring应用 ##

Spring Initializr

### 1.2.1 检查Spring项目的结构

END

### 1.2.2 检查Spring项目的结构 ###

1. 应用的源码放到了“src/main/java”中，测试代码放到了“src/test/java”中，而非Java的资源放到了“src/main/resources”
2. 需要注意以下几点
   * mvnw和mvnw.cmd：这是Maven包装器（wrapper）脚本。借助这些脚本，即便你的机器上没有安装Maven，也可以构建项目。
   * pom.xml：这是Maven构建规范
   * TacoCloudApplication.java：这是Spring Boot主类，它会启动该项目
   * application.properties：这个文件起初是空的，但是它为我们提供了指定配置属性的地方
   * static：在这个文件夹下，你可以存放任意为浏览器提供服务的静态内容（图片、样式表、JavaScript等），该文件夹初始为空
   * templates：这个文件夹中存放用来渲染内容到浏览器的模板文件
   * TacoCloudApplicationTests.java：这是一个简单的测试类，它能确保Spring应用上下文可以成功加载

**探索构建规范**

在pom.xml文件中，需要注意：

1. `<packaging>`:`jar` 或者 `war`
2. `<parent>`:`spring-boot-starter-parent作为其父POM<version>子元素`
3. `<dependencies>`元素下声明了3个依赖

**引导应用**

* @SpringBootApplication注解明确表明这是一个Spring Boot应用，@SpringBootApplication是一个组合注解，它组合了3个其他的注解
  * @SpringBootConfiguration：将该类声明为配置类。
  * @EnableAutoConfiguration：启用Spring Boot的自动配置。
  * @ComponentScan：启用组件扫描。这样我们能够通过像@Component、@Controller、@Service这样的注解声明其他类，Spring会自动发现它们并将它们注册为Spring应用上下文中的组件。

main()方法。这是JAR文件执行的时候要运行的方法。在大多数情况下，这个方法都是样板代码，我们编写的每个Spring Boot应用都会有一个类似或完全相同的方法（类名不同则另当别论）

* 这个main()方法会调用SpringApplication中静态的run()方法，后者会真正执行应用的引导过程，也就是创建Spring的应用上下文。
* 在传递给run()的两个参数中，一个是配置类，另一个是命令行参数。尽管传递给run()的配置类不一定要和引导类相同，但这是最便利和最典型的做法。

**测试应用**

* 带有@RunWith(SpringRunner.class)注解
* @RunWith是JUnit的注解，它会提供一个测试运行器（runner）来指导JUnit如何运行测试

**测试运行器的其他名称**

END

## 1.3 编写Spring应用

### 1.3.1 处理Web请求

​	Spring自带了一个强大的Web框架，名为Spring MVC。Spring MVC的核心是控制器（controller）的理念。控制器是处理请求并以某种方式进行信息响应的类。在面向浏览器的应用中，控制器会填充可选的数据模型并将请求传递给一个视图，以便于生成返回给浏览器的HTML。

@Controller并没有做太多的事情。它的主要目的是让组件扫描将这个类识别为一个组件。@Controller有着类似的目的（包括@Component、@Service和@Repository）

**为何使用Thymeleaf**

模板名称是由逻辑视图名派生而来的，再加上“/templates/”前缀和“.html”后缀。最终形成的模板路径将是“/templates/home.html”。

### 1.3.2 定义视图

END

### 1.3.3 测试控制器

HomeControllerTest没有使用@SpringBootTest标记，而是添加了@WebMvcTest注解。

这是Spring Boot所提供的一个特殊测试注解，它会让这个测试在Spring MVC应用的上下文中执行。

@WebMvcTest同样会为测试Spring MVC应用提供Spring环境的支持

### 1.3.4 构建和运行应用

END

### 1.3.5 了解Spring Boot DevTools

DevTools为Spring开发人员提供了一些便利的开发期工具，其中包括：

* 代码变更后应用会自动重启；
* 当面向浏览器的资源（如模板、JavaScript、样式表）等发生变化时，会自动刷新浏览器；
* 自动禁用模板缓存；
* 如果使用H2数据库的话，内置了H2控制台。

**应用自动重启**

DevTools会监控变更，当它看到有变化的时候，将会自动重启应用。

更准确地说，当DevTools运行的时候，应用程序会被加载到Java虚拟机（Java virtual Machine，JVM）两个独立的类加载器中。

1. 其中一个类加载器会加载你的Java代码、属性文件以及项目中“src/main/”路径下几乎所有的内容。这些条目很可能会经常发生变化。
2. 另外一个类加载器会加载依赖的库，这些库不太可能经常发生变化。

当探测到变更的时候，DevTools只会重新加载包含项目代码的类加载器，并重启Spring的应用上下文，在这个过程中另外一个类加载器和JVM会原封不动。

**浏览器自动刷新和禁用模板缓存**

DevTools通过禁用所有模板缓存解决了这个问题。DevTools在运行的时候，它会和你的应用程序一起，同时自动启动一个LiveReload服务器。LiveReload服务器本身并没有太大的用处。但是，当它与LiveReload浏览器插件结合起来的时候，就能够在模板、图片、样式表、JavaScript等（实际上，几乎涵盖为浏览器提供服务的所有内容）发生变化的时候自动刷新浏览器。

**内置的H2控制台**

END

### 1.3.6 回顾一下

* 使用Spring Initializr创建初始的项目结构；
* 编写控制器类处理针对主页的请求
* 定义了一个视图模板来渲染主页；
* 编写了一个简单的测试类来验证工作符合预期。

要理解Spring到底做了些什么，我们首先来看一下构建规范：

1. 在pom.xml文件中，我们声明了对Web和Thymeleaf starter的依赖。这两项依赖会传递引入大量其他的依赖，包括：
   * Spring的MVC框架；
   * 嵌入式的Tomcat；
   * Thymeleaf和Thymeleaf布局方言；
2. 还引入了Spring Boot的自动配置库。当应用启动的时候，Spring Boot的自动配置将会探测到这些库，并自动完成如下功能：
   * 在Spring应用上下文中配置bean以启用Spring MVC；
   * 在Spring应用上下文中配置嵌入式的Tomcat服务器；
   * 配置Thymeleaf视图解析器，以便于使用Thymeleaf模板渲染Spring MVC视图。

## 1.4 俯瞰Spring风景线

### 1.4.1 Spring核心框架

Spring核心框架是Spring领域中一切的基础。它提供了核心容器和依赖注入框架。

* 其中有一项是Spring MVC，也就是Spring的Web框架。Spring MVC还能用来创建RESTAPI，以生成非HTML的输出。
* Spring核心框架还提供了一些对数据持久化的基础支持，尤其是基于模板的JDBC支持——JdbcTemplate
* 在最新版本的Spring中，还添加了对反应式（reactive）风格编程的支持，其中包括名为Spring WebFlux的新反应式Web框架，这个框架大量借鉴了Spring MVC。

### 1.4.2 Spring Boot

除了starter依赖和自动配置，Spring Boot还提供了大量其他有用的特性：

* Actuator能够洞察应用运行时的内部工作状况，包括指标、线程dump信息、应用的健康状况以及应用可用的环境属性；
* 灵活的环境属性规范；
* 在核心框架的测试辅助功能之上提供了对测试的额外支持。
* Spring Boot还提供了一个基于Groovy脚本的编程模型，称为Spring Boot命令行接口（Command-LineInterface，CLI）。使用Spring Boot CLI，我们可以将整个应用程序编写为Groovy脚本的集合，并通过命令行运行它们。

### 1.4.3 Spring Data

END

### 1.4.4 Spring Security

END

### 1.4.5 Spring Integration and Spring Batch ###

* Spring Integration解决了实时集成问题。在实时集成中，数据在可用时马上就会得到处理。
* 相反，Spring Batch解决的则是批处理集成的问题，在此过程中，数据可以收集一段时间，直到某个触发器（可能是一个时间触发器）发出信号，表示该处理批量数据了才会对数据进行批处理

### 1.4.6 Spring Cloud ###

要更全面地研究Spring Cloud，我建议阅读John Carnell的SpringMicroservices in Action一书（Manning，2017）。

## 1.5 小结

* pring旨在简化开发人员所面临的挑战，比如创建Web应用程序、处理数据库、保护应用程序以及实现微服务。
* Spring Boot构建在Spring之上，通过简化依赖管理、自动配置和运行时洞察，使Spring更加易用。
* Spring应用程序可以使用Spring Initializr进行初始化。Spring Initializr是基于Web的应用，并且为大多数Java开发环境提供了原生支持。
* 在Spring应用上下文中，组件（通常称为bean）既可以使用Java或XML显式声明，也可以通过组件扫描发现，还可以使用Spring Boot自动配置功能实现自动化配置。

# CH2 开发Web应用 #

## 2.1 展现信息 ##

tacos

### 2.1.1 构建领域类 ###

应用的领域指的是它所要解决的主题范围：也就是会影响到对应用理解的理念和概念。

*使用了名为Lombok的库*

### 2.1.2 创建控制器类 ###

1. 首先是@Slf4j，这是Lombok所提供的注解，在运行时，它会在这个类中自动生成一个SLF4J（Simple Logging Facadefor Java）Logger。
2. DesignTacoController用到的下一个注解是@Controller。这个注解会将这个类识别为控制器，并且将其作为组件扫描的候选者，所以Spring会发现它并自动创建一个DesignTacoController实例
3. DesignTacoController还带有@RequestMapping注解。当@RequestMapping注解用到类级别的时候，它能够指定该控制器所处理的请求类型

**处理GET请求**

@GetMappping是一个相对较新的注解，是在Spring 4.3引入的。

```
@GetMapping = @RequestMapping(method=RequestMethod.GET)
```



**请求映射注解**

|Annotation|Description|
|--|--|
|@RequestMapping|通用的请求处理|
|@GetMapping|Handles HTTP GET requests|
|@PostMapping|Handles HTTP POST requests|
|@PutMapping|Handles HTTP PUT requests|
|@DeleteMapping|Handles HTTP DELETE requests|
|@PathchMapping|Handles HTTP PATCH requests|

通常，我喜欢只在类级别上使用@RequestMapping，以便于指定基本路径。在每个处理器方法上，我会使用更具体的@GetMapping、@PostMapping等注解。

### 2.1.3 设计视图 ###

在运行时，Spring Boot的自动配置功能会发现Thymeleaf在类路径中，因此会为Spring MVC创建支撑Thymeleaf视图的bean。

	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-thymeleaf</artifactId>
	</dependency>

## 2.2 处理表单提交 ##

END

## 2.3 校验表单输入

Validation API提供了一些可以添加到领域对象上的注解，以便于声明校验规则。

Spring支持Java的Bean校验API（BeanValidation API，也被称为JSR-303）。借助Spring Boot，要在项目中添加校验库，我们甚至不需要做任何特殊的操作，这是因为Validation API以及ValidationAPI的Hibernate实现将会作为Spring Boot web starter的传递性依赖自动添加到项目中。

要在Spring MVC中应用校验：

* 在要被校验的类上声明校验规则
* 在控制器方法中声明要进行校验：具体来讲，也就是DesignTacoController的processDesign()方法和OrderController的processOrder()方法。
* 修改表单视图以展现校验错误。

Validation API提供了一些可以添加到领域对象上的注解，以便于声明校验规则。

### 2.3.1 声明校验规则 ###

想要确保name属性不能为空或null，可以使用@NotNull和@Size注解来声明这些校验规则。

所有的校验注解都包含了一个message属性，该属性定义了当输入的信息不满足声明的校验规则时要给用户展现的消息。

### 2.3.2 在表单绑定的时候执行校验 ###



### 2.3.3 展现校验错误 ###

2.4 Working with view controllers

## 2.4 使用视图控制器

# ch3 Working with data #

# ch4 Securing Spring #

# ch5 Working with configuration properies #

Part2. INTEGERATED SPRING

# ch6 Createing REST services #

# ch7 Consuming REST #

# ch8 Sending messages asynchronously #

# ch9 Integrating Spring #

Part3 REACTIVE SPRING

# ch10 Introducing Reactor #

# ch11 Developing reactive APIs #

# ch12 Persisting data reactively #

Part4 CLOUD-NATIVE SPRING

# ch13 Discovering services #

# ch14 Managing configuration #

# ch15 Handling failure and latency #

Part5 DEPLOYED SPRING

# ch16 Working with Spring Boot Actuator #

# ch17 Administering Spring #

# ch18 Monitoring Spring with JMX #

# ch19 Deloying Spring #



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

服务是仓库之上的下一层。服务封装了应用程序的业务逻辑，它将使用其他服务和仓库，但不能使用更高层应用程序层的资源，例如控制器。服务被标记上了@Service注解，使它们可以自动实例化和依赖注入，另外它还有些优点。

服务实现特定的接口，依赖于它的资源针对接口进行编程。

	@Service 服务提供业务
	@Component 自定义元注解

### 14.1.4 使用控制器提供用户界面逻辑 ###

控制器是控制器-服务-仓库模式中食物链的顶层。

在MVC模式中，服务和仓库被认为是控制器（不是@Controller）的一部分。@Controller依赖的@Service、@Service依赖的@Repository与所有居于这些组件之间的缓存层将@Controller——可以是一个WebGUI或者Web服务API——将模型的必要部分传递给视图用于渲染。该视图可以是一个JSP（对于WebGUI），或者JSON或XML渲染引擎。

## 14.2 使用根应用上下文代替Web应用上下文 ##

在Seqrvlet容器之外，ServletContextConfiguration没有投任何用处。配置的内容反映了这一点，该类中配置的所有bean都与接受、处理和响应HTTP请求有点关系。

### 14.2.1 在多用户界面中重用根应用上下文 ###

通常根据不同的用户界面讲共享相同的服务。通过这种方式，业务逻辑将在所有的用户之间保持不变。为了实现这一点，不应该在Web应用上下文中管理服务和仓库，而是应该在根应用上下文中，是所有Web应用上下文的父亲。所有这些服务和仓库都将被控制各种不同用户界面的Web应用上下文所继承。如果希望使用为Web应用程序编写的服务和仓库编写桌面应用程序，那么可以使用相同的根应用上下文配置，然后使用不同的机制启动。

组件扫描基于两个准则进行工作：包扫描和类过滤。

对于Spring在基本包中找到的每个类，都将引用已配置的过滤器。过滤器分为包含过滤器和排除过滤器。如果这个类出发了任意一个包含过滤器，并且未触发任何排除过滤器，那么它将编程Springbean。这意味着可以被改造、主语、初始化，并执行任何应用在Spring管理bean上的操作。

### 14.2.2 将业务逻辑从控制器移动到服务 ###

#### 1.配置视图名称转换 ####

可以在需要地时候创建自己地RequestToViewNameTranslator,但是默认地DefaultRequestToViewNameTranslator通常是足够地。它将去除Web应用上下文URL和URL结尾的任何文件扩展名。

#### 2.使用@ModelAttribute ####

注解@ModelAttribute将告示Spring返回的User应该被添加到模型的特性健currentUser上。

## 14.2.3 使用仓库存储数据 ##

## 14.3 使用异步和计划执行改进服务 ##

### 14.3.1 了解执行器和调度器 ###

* 接口java.util.concurrent.Executor定义了一个可以执行简单Runnable的执行器
* Spring的org.springframework.core.task.TaskExecutor继承了该接口
* Spring还提供了org.springframework.scheduling.TaskScheduler接口，指定几个方法用于调度任务，并在将来的某个时间点运行一次或多次。

将表单对象和业务对象分隔开。

### 14.3.2 配置调度器和异步支持 ###

为了在@Async方法上启用异步方法执行，需要在@Configuration类上注解@EnableAsync。为了在@Scheduled方法上启用计划方法执行，需要使用@EnableSceduling注解。

@EnableAsync注解中的proxyTargetClass特性将告诉Spring使用CGLIB库而不是使用Java接口代理创建含有异步或计划方法的代理类。通过这种方法，可以在自己的bean上创建接口位指定和计划方法。如果将该特性设置为假，那么只有接口指定的方法可以通过计划或异步的方式执行。

新的@Bean方法将把调度器暴露为任何其他bean都可以使用的bean。方法getAsyncExecutor（在AsyncConfigure中指定）将告诉Spring为异步方法执行使用相同的执行器。

方法getAsyncExecutor和configureTasks都将调用taskScheduler。当Spring调用@Bean方法时是否会实例化第三个TaskScheduler。Spring将代理所有对@Bean方法的调用，所以他们永远不会被调用多次。第一次调用@Bean方法的结果将被缓存，并在所有将来的调用中使用。这将允许配置中的多个方法使用其他的@Bean方法。

### 14.3.3 创建和使用@Async ###
   
Spring Framework通过以代理的方式

### 14.3.4 创建和使用@ScheduleId方法 ###

## 14.4 使用WebSocket实现逻辑层分离 ##

* InMemoryUserRepository实现了UserRepository，将所有用户都保存在内存中
* TemporaryAuthenticationService实现了AuthenticationService，它将使用UserRepository接口。
* AuthenticationController
* InMemoryTicketRepository、DefaultTickService使用的是TicketRepository接口，而TicketController现在使用的是TicketService接口。
* java.secutriy.Principal接口接口。

### 14.4.1 在Spring应用上下文中添加由容器管理的对象 ###

DefauleSessionRegistry。

SessionListener被实例化并由Servlet容器而不是Spring Framework所管理。

1. 使用字节码和面向切面编程。如果希望将他们转换为Spring bean并自动实例化的话，唯一需要做的是使用@org.springframework.breans.factory.annotation.Configurable注解标志非Spring管理的bean的类。
2. 通过编程的方式，在运行时向Spring应用上下文添加一个现有对象，必须要做的第一件事是：从SessionListener移除@WebListener注解（因为添加了该注解的监听器被调用顺序是不可预测）。

Configuration类已经被移除，该类是一个ServletContextListener，它以编程的发过誓配置了LoggingFilter和AuthenticationFilter。Bootstrap类都是对过滤器（之前在Configurator中）进行配置的。

### 14.4.2 将Servlet转换成Spring MVC控制器 ###

WebSocket服务器终端也是容器管理的对象。



### 14.4.3 记住：WebSocket知识业务逻辑的另一个界面 ###



## 14.5 小结 ##




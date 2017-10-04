# 第3章 高级装配 #

## 3.1 环境与profile ##
	
	@Bean(destoryMethod="shutdown")
	public DataSource dataSource(){
		return new EmbeddedDatabaseBuilder()
			.addScript("classpath:schema.sql")
			.addScript("classpath:test-data.sql")
			.build();
	}

### 3.1.1 配置profile bean ###

#### 通知（Advice） ####

* 前置通知（Before）：在目标方法被调用之前调用通知功能；
* 后置通知（After）：在目标方法完成之后调用通知，此时不会关心方法的输出是什么；
* 返回通知（After-returning）：在目标方法成功执行之后调用通知；
* 异常通知（After-throwing）：在目标方法抛出异常后调用通知；
* 环绕通知（Around）：通知包裹了被通知的方法，在被通知的方法调用之前和调用之后执行自定义的行为。

**连接点**
连接点是在应用执行过程中能够插入切面的一个点。这个点可以是调用方法时、抛出异常时、甚至修改一个字段时。切面代码可以利用这些点插入到应用的正常流程之中，并添加新的行为。

**切点**
切点的定义会匹配通知所要织入的一个或多个连接点。

**切面**
通知和切点的结合。通知和切点共同定义了切面的全部内容——他是什么，在何时和何处完成其功能。

**引入**
引入允许我们向现有的类添加新方法或属性。

**织入**
织入是把切面应用到目标对象并创建新的代理对象的过程。

在目标对象的生命周期里有多个点可以进行织入：
* 编译期：切面在目标编译时被织入。AspectJ
* 类加载期：切面在目标i类加载到JVM时被织入。这种方式需要特殊的类加载器（ClassLoader），它可以在目标类被引入应用之前加强该目标类的字节码。
* 运行期：切面在应用运行的某个时刻被织入。Spring Aop

### 3.1.2 激活profile ###

* 基于代理的经典SpringAOP；
* 纯POJO切面
* @AspectJ注解驱动的切面；
* 注入式AspectJ切面（适用于Spring各版本）

前三种都是Spring AOP实现的变体，Spring AOP构建在动态代理基础之上，因此，Spring对AOP的支持局限于方法拦截。

#### Spring通知是Java编写的 ####
Spring所创建的通知都是用标准的Java类编写的，定义通知所应用的切点通常会使用注解或在Spring配置文件里采用XML来编写的。

AspectJ

#### Spring在运行时通知对象 ####
通过在代理类中包裹切面，Srping在运行期把切面织入到Spring管理的bean中。代理类封装了目标类，并拦截被通知方法的调用，再把调用转发给真正的目标bean。当代理拦截到方法调用时，在调用目标bean方法之前，会执行切面逻辑。

知道应用需要被代理的bean时，Spring才创建代理对象。如果使用的是ApplicationContext的话，在ApplicationContext从BeanFactory中加载所有bean的时候，Spring才会创建被代理的对象。因为Spring运行时才创建代理对象，所以我们不需要特殊的编译器来织入Spring AOP的切面。

#### Spring只支持方法级别的连接点 ####
Spring基于动态代理，所以Spring只支持方法连接点。如果需要方法拦截之外的连接点拦截功能，需要AspectJ和JBoss。


## 4.2 通过切点来选择连接点 ##

Spring借助AspectJ的切点表达式语言来定义Spring切面

|AspectJ指示器|描述|
|--|--|
|arg()|限制连接点匹配参数为指定类型的执行方法|
|@args()|限制连接点匹配参数由指定注解标注的执行方法|
|execution()|用于匹配是连接点的执行方法|
|this()|限制连接点匹配AOP代理的bean引用为指定类型的类|
|target()|限制连接点匹配目标对象为指定类型类|
|within()|限制连接点匹配目标对象为指定类型类|
|@within()|限制连接点匹配指定注解所标注的类型（当使用Spring AOP时，方法定义在由指定的注解所标注的类里）|
|annotation|限定匹配带有指定注解的连接点|

### 4.2.1 编写切点 ###

	exection(* concert.Performance.perform(..))
execution()指示器选择Performance的perform()方法。方法表示以“*”号开始，表明了我们不关心方法返回值的类型。然后，我们指定了全限定类名和方法名。对于方法参数列表，我们使用两个点号（..）表明切点要选择任意的perform()方法，无论该方法的入参是什么。

	and && or || not !

### 4.2.2 在切点中选择bean ###

Spring引入了一个新的bean()指示器，它允许我们在切点表达式中使用bean的ID来标识bean。bean()使用beanID或bean名称作为参数来限制切入点只匹配特定的bean。

## 4.3 使用注解创建切面 ##

因为缓存是一种面向切面的行为，所以cache命名空间会与Spring的aop命名空间结合起来使用，用来声明缓存所应用的切点在哪里。

//TODO 以后了解，先放放

### 4.3.1 定义切面 ###

Spring使用AspectJ注解来声明通知方法

|注解|通知|
|--|--|
|@After|通知方法会在目标方法返回或抛出异常后调用|
|@AfterReturning|通知方法会在目标方法返回后调用|
|@AfterThrowing|通知方法会在目标方法抛出异常后调用|
|@Around|通知方法会将目标方法封装起来|
|@Before|通知方法会在目标方法调用之前执行|

@Pointcut注解能够在一个@AspectJ切面内定义可重用的切点。为@Pointcut注解设置的值是一个切点表达式。

### 4.3.2 创建环绕通知 ###
环绕通知是最为强大的通知类型。它能够让你所编写的逻辑将被通知的目标方式完全包装起来。（前置和后置）

ProceedingJoinPoint，

### 4.3.3 处理通知中的参数 ###

### 4.3.4 通过注解引入新功能 ###

如果除了实现这些接口，代理能暴露新的接口，切面所通知的bean看起来像是实现了新的接口。

@DeclarePattents

* value 指定哪种类型的bean要引入该接口。 + 表示Performance的所有子类型，而不是Performance本身。
* defaultImple 属性指定为引入功能提供实现的类。
* @DeclareParents 注解所标注的静态属性指明了要引入的接口

切面需要声明为一个bean
	<bean class="com.xjsaber.spring.aop.EncoreableIntroducer" />

## 4.4 在XML中声明切面 ##

### 4.4.2 声明环绕通知 ###

### 4.4.3 为通知传递参数 ###

### 4.4.4 声明前置和后置通知 ###

## 4.5 注入AspectJ切面 ##

//TODO 没细看，有时间回来看

当使用AspectJ后，Spring需要通过aspectOf()工厂方法获得切面的引用，然后像<bean>元素规定的那样在该对象上执行依赖注入。

## 4.6 小结 ##

SpringAOP

AspectJ



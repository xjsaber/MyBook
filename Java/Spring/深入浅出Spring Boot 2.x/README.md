# 深入浅出Spring Boot 2.x #

# 第1章 Spring Boot来临 #

## 1.1 Spring的历史 ##

# 第3章 全注解下的Spring IoC #

Spring Boot并不建议使用XML，而是通过注解的描述生成的对象。

* 通过描述管理Bean，包括发布和获取Bean；
* 通过描述完成Bean之间的依赖关系

## 3.1 IoC容器简介 ##

BeanFactory

## 3.2 装配你的Bean ##

### 3.2.1 通过扫描装配你的Bean ###

## 3.3 依赖注入 ##

@Autowired，根据属性的类型（by type）找到对应的Bean进行注入。

### 3.3.1 注解@Autowired ###

注入的机制是根据类型（by type）。

#### getBean ####

根据类型（by type）
根据名字（by name）

### 3.3.2 消除歧义性——@Primary和@Quelifier ###



## 3.4 生命周期 ##



## 3.5 使用属性文件 ##

	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-configuration-processor</artifactId>
		<optional>true</optional>
	</dependency>

通过@Value注解，使用${...}占位符读取配置在属性文件的内容。

* value可以配置多个配置文件。使用classpath前缀，意味着去类文件路径下找到属性文件；
* ignoreResourceNotFound的默认值为false，也就是没有找到属性文件就会报错，否则不会报错

## 3.6 条件装配Bean ##

@Conditional注解，并且配置了类DatabaseConditional，那么这个类就必须实现Condition接口。对于Condition接口要求实现matches方法。

#### matches方法 ####

1. 读取其上下文
2. 判定是否已经配置了对应的数据库信息
3. 返回true，则会装配数据库连接池的Bean，否则是不装配

## 3.7 Bean的作用域 ##

* isSingleton
* isPrototype

Web容器：

1. 页面（page）
2. 请求（request）
3. 会话（session）
4. 应用（application）

|作用域类型|使用范围|作用域描述|
|--|--|--|
|**singleton**|所有Spring应用|默认值，IoC容器只存在单例|
|**prototype**|所有Spring应用|每当从IoC容器中取出一个Bean，则创建一个新的Bean|
|**session**|Spring Web 应用|HTTP会话|
|**application**|Spring Web 应用|Web工程生命周期|
|request|Spring Web 应用|Web工程单次请求（request）|
|globalSession|Spring Web 应用|在一个全新的HTTP Session中，一个Bean定义对应一个实例。实践中基本不使用|

## 3.8 使用@Profile ##

使用Profile机制在开发环境、测试环境、准生产环境和生产环境中进行切换。

	application-{profile}.properties

## 3.9 引入XML配置Bean ##

## 3.10 使用Spring EL ##


# 第4章 开始约定编程——Spring AOP #

4.1 约定编程

4.1.1 约定

4.1.2 ProxyBean的实现

4.1.3 总结

4.2 AOP的概念

4.2.1 为什么使用AOP

4.2.2 AOP术语和流程

## 4.3 AOP开发详解 ##

@AspectJ

### 4.3.1 确定连接点 ###

任何AOP编程

1. 在什么地方需要AOP，需要确定连接点

4.3.2 开发切面

4.3.3 切点定义

4.3.4 测试AOP

4.3.5 环绕通知

4.3.6 引入

4.3.7 通知获取参数

4.3.8 织入

4.4 多个切面

# 第5章 访问数据库 #

## 5.1 配置数据源 ##

在依赖于Spring Boot的spring-boot-starter-data-jpa后，它就会默认为你配置数据源。

### 5.1.1 启动默认数据源 ###

	<dependency>
		<groupId>org.springframework.boot</groupId>
		<aftifactId>spring-boot-starter-data-jpa</aftifactId>
	</dependency>
	<dependency>
		<groupId>com.h2databaset</groupId>
		<aftifactId>h2</aftifactId>
		<scope>runtime</scope>
	</dependency>

引入JPA的依赖，对JPA来说，在SpringBoot中是依赖Hibernate去实现的。

### 5.1.2 配置自定义数据源 ###

	<dependency>
		<groupId>mysql</groupId>
		<aftifactId>mysql-connector-java</aftifactId>
	</dependency>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<aftifactId>mysql-connector-java</aftifactId>	
	</dependency>

## 5.2 使用JdbcTemplate操作数据源 ##

5.3 使用JPA（Hibernate）操作数据

## 5.4 整合MyBatis框架 ##

### 5.4.1 Mybatis简介 ###



# 第6章 聊聊数据库事务处理 #

# 第7章 使用性能利器——Redis #

在默认的情况下，spring-boot-starter-data-redis（版本2.x）会依赖Lettuce的Redis客户端驱动，而在一般的项目中，会使用Jedis，所以在代码中使用了<exclusions>元素将其依赖排除了。

## 7.1 spring-data-redis项目简介 ##

### 7.1.1 spring-data-redis项目的设计 ###

* Lettuce
* Jedis

Spring提供了一个RedisConnectionFactory接口，通过生成一个RedisConnection接口对象，而RedisConnection接口对象是对Redis底层接口的封装。

### 7.1.2 RedisTemplate ###

RedisTemplate

1. 自动从RedisConnectionFactory工厂中获取连接
2. 执行对应的Redis命令
3. 关闭Redis的连接

Redis是一种基于字符串存储的NoSQL，而Java是基于对象的语言，对象是无法存储到Redis中的，不过Java提供了序列化机制，只要类实现了java.io.Serializable接口，就代表类的对象能够进行序列化，通过将类对象进行序列化就能够得到二进制字符串。

Spring提供了RedisSerializer接口

1. serialize，能把那些序列化的对象转化为二进制字符串；
2. deserialize，能够通过反序列化把二进制字符串转换为Java对象。

|属性|描述|备注|
|--|--|--|
|defaultSerializer|默认序列化器|如果没有设置，则使用JdkSerializationRedisSerializer|
|keySerializer|Redis键序列化器|如果没有设置，则使用默认序列化器|
|valueSerializer|Redis值序列化器|如果没有设置，则使用JdkSerializationRedisSerializer|
|hashKeySerializer|Redis散列结构field序列化器|如果没有设置，则使用默认序列化器|
|hashValueSerializer|Redis散列结构value序列化器|如果没有设置，则使用默认序列化器|
|stringSerializer|字符串序列化器|如果没有设置，则使用JdkSerializationRedisSerializer|

### 7.1.3 Spring对Redis数据类型操作的封装 ###

### 7.1.4 SessionCallback和RedisCallback接口 ###

让RedisTemplate进行回调，通过他们在同一条连接下进行多个redis指令

* SessionCallback 较高的封装
* RedisCallback 较底层

7.2 在SpringBoot中配置和使用Redis

# 第8章 文档数据库——MongoDB #

# 第9章 初识Spring MVC #

展示给用户的视图(View)、控制器返回的数据模型（Model）、定位视图的视图解析器（ViewResolver）和处理器适配器（HandlerAdapter）

## 9.1 Spring MVC框架的设计 ##

## 9.2 Spring MVC流程 ##

## 9.3 定制Spring MVC的初始化 ##



## 9.4 Spring MVC实例 ##

### 9.4.1 开发控制器 ###

当方法被标注后，也可以定义部分URL，这样就能让请求的URL找到对应的路径。配置了扫描路径之后，Spring MVC扫描机制就可以将其扫描，并且装载为HandlerMapping，以备后面使用。
 
### 9.4.2 视图和视图渲染 ###



# 第10章 深入Spring MVC开发 #

## 10.1 处理器映射 ##

1. 启动阶段就会将注解`@RequestMapping`所配置的内容保存到处理器映射（HandlerMapping）机制中去
2. 等待请求的到来
3. 通过拦截请求信息和HandlerMapping进行匹配
4. 找到对应的处理器（它包含控制器的逻辑）
5. 将处理器及其拦截器保存到HandlerExecutionChain对象中
6. 返回给DispatcherServlet
7. DispathcerServlet就可以运行他们


## 10.2 获取控制器参数 ##

## 10.3 自定义参数转换规则 ##

## 10.4 数据验证 ##

1. 支持JSR-303注解验证，SpringBoot会引入关于Hibernate Validator机制来支持JSR-303验证规范。
2. 业务复杂，所以需要自定义验证规则。



## 10.5 数据模型 ##

## 10.6 视图和视图解析器 ##

## 10.7 文件上传 ##

## 10.8 拦截器 ##

## 10.9 国际化 ##

## 10.10 Spring MVC拾遗 ##

# 第11章 构建REST风格网站 #

# 第12章 安全——Spring Security #

# 第13章 学点Spring其他的技术 #

# 第14章 Spring5新框架——WebFlux #

# 第15章 实践一下——抢购商品 #

# 第16章 部署、测试和监控 #

# 第17章 分布式开发——Spring Cloud #


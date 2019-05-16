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

4.3 AOP开发详解

4.3.1 确定连接点

4.3.2 开发切面

4.3.3 切点定义

4.3.4 测试AOP

4.3.5 环绕通知

4.3.6 引入

4.3.7 通知获取参数

4.3.8 织入

4.4 多个切面

# 第5章 访问数据库 #

5.1 配置数据源

5.1.1 启动默认数据源

5.1.2 配置自定义数据源

5.2 使用JdbcTemplate操作数据源

5.3 使用JPA（Hibernate）操作数据

## 5.4 整合MyBatis框架 ##

### 5.4.1 Mybatis简介 ###



# 第6章 聊聊数据库事务处理 #

# 第7章 使用性能利器——Redis #

# 第8章 文档数据库——MongoDB #

# 第9章 初识Spring MVC #

# 第10章 深入Spring MVC开发 #
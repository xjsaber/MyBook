# 小马哥讲Spring核心编程思想 #

## 01 | 课程介绍 ##

## 02 | 内容综述 ##

1. Spring核心特性
	* 框架总览
		* 特性总览
		* 版本特性
		* 模块化设计
		* 技术整合
			* Java语言特性运用
			* JDK API实践
			* Java EE API整合   
		* 编程模型 
			* 面向对象编程OOP——契约接口
			* 面向切面编程
				* 动态代理
				* 字节码提升 
			* 面向元数据
				* 配置元数据
				* 注解
				* 属性配置
			* 面向模块编程
				* Maven Artifacts
				* Java 9 Automatic Module
				* Spring @Enable* 注解
			* 面向函数编程
				* Lambda
				* Reactive（异步非阻塞）
2. IoC容器：IoC是Bean的管理容器
	* 重新认识IoC
	* Spring IoC容器
	* 依赖查找
	* 依赖注入
	* 依赖来源
	* Spring IoC容器生命周期    
3. Bean：Bean是IoC的数据来源
	* Bean实例
	* Bean作用域：Bean的作用域
	* Bean生命周期：Bean的生命周期
4. 元信息：配置数据
	* 注解
	* 配置元信息：配置数据
	* 外部化属性
5. 基础设施：工具
	* 资源管理
	* 类型转换
	* 数据绑定
	* 校验
	* 国际化
	* 事件
	* 泛型处理

## 03 | 课前准备：学习三件套（工具、代码与大脑） ##

Spring Framwork总览

1. 课前准备
2. Spring特性总览
3. Spring版本特性
4. Spring模块化设计
5. Spring对Java语言特性运用
6. Spring对JDK API实践
7. Spring对Java EE API整合
8. Spring编程模型
9. Spring核心价值
10. 面试题精选

### 心态 ###

戒骄戒躁、谨慎豁达、如履薄冰

### 方法 ###

* 基础：夯实基础，了解动态
* 思考：保持怀疑，验证一切
* 分析：不拘小节，观其大意
* 实践：思辨结合，学以致用

### 工具 ###

* JDK：Oracle JDK 8
* Spring Framwork：5.2.2.RELEASE
* IDE：IntelliJ IDEA 2019（Community）
* Maven：3.2+

## 04 | 特性总览：核心特性、数据存储、Web技术、框架整合与测试 ##

### 核心特性（Core） ###

* Ioc容器（IoC Container）
* Spring事件（Events）：基于Java的标准事件
* 资源管理（Resources）：基于Java的资源管理
* 国际化（i18n）
* 校验（Validation）
* 数据绑定（Data Binding）
* 类型转换（Type Conversion）
* Spring表达式（Spring Express Language）
* 面向切面编程（AOP）

### 数据存储（Data Access） ###

* JDBC
* 事务抽象（Transactions）:EJB
* DAO支持（DAO Support）
* O/R映射（O/R Mapping）：JPA
* XML编列（XML Marshalling）

### Web技术（Web） ###

* Web Servlet技术栈（1-5）
	* Spring MVC
	* WebSocket
	* SockJS（不多讨论）
* Web Reactive技术栈（5）
	* Spring WebFlux
	* WebClient（RestTemplate或HttpClient同步）异步
	* WebSocket   

### 技术整合（Integration） ###

* 远程调用（Remoting）：同步模式
* Java消息服务（JMS）：异步模式
* Java连接架构（JCA）：JavaEE的架构
* Java管理扩展（JMX）：
* Java邮件客户端（Email）
* 本地任务（Tasks）：单机版
* 本地调度（Scheduling）：单机版
* 缓存抽象（Caching）
* Spring测试（Testing）

### 测试（Testing） ###

* 模拟对象（Mock Objects）：单元测试
* TestContext框架（TestContext Framwork）：集成测试
* Spring MVC测试（Spring MVC Test）
* Web测试客户端（WebTestClient）

## 05 | Spring版本特性：Spring各个版本引入了哪些新特性 ##

|Spring Framwork版本|Java标准版|Java企业版|
|--|--|--|
|1.x|1.3+（动态代理）|J2EE 1.3+|
|2.x|1.4.2+|J2EE 1.3+|
|3.x|5+（注解和枚举）|J2EE 1.4和JavaEE 5|
|4.x|6+|Java EE 6和7|
|5.x|8+（Lambda语法）|Java EE 7|

## 06 | Spring模块化设计：Spring功能特性如何在不同模块中组织？ ##

* spring-aop：面向切面编程
* spring-aspects：spring对aspects的支持
* spring-beans：与spring-context一起是ioc的实例
* spring-context-indexer：
* spring-context-support
* spring-context
* spring-core
* spring-expression
* spring-instrument
* spring-jcl
* spring-jdbc
* spring-jms
* spring-messaging
* spring-orm
* spring-oxm
* spring-text
* spring-tx
* spring-web
* spring-webflux
* spring-webmvc
* spring-websocket


## 07 | Java语言特性运用：各种Java语法特性是怎么被Spring各种版本巧妙运用的？ ##

## 08 | JDK API实践：Spring怎样取舍Java I/O ##

* < Java5
	* 反射（Reflection）
	* Java Beans
	* 动态代理（Dynamic Proxy）
* Java5
* Java6
* Java7 
	* NIO 2
	* Fork/Join框架
	* invokedynamic字节码（动态语言） 
* Java8
	* Stream API  
	* CompletableFuture
	* Annotation on Java Types
	* Data and Time API
	* 可重复
	* JavaScript运行时
* 运行时 

## 09 | Java EE API整合：为什么Spring要与“笨重”的Java EE共舞？ ##

## 10 | Spring编程模型：Spring实现了哪些编程模型？ ##

# 第二部：重新认识IoC #

1. IoC发展简介
2. IoC主要实现侧录呃
3. IoC容器的职责
4. IoC容器的实现
5. 传统IoC容器实现
6. 轻量级IoC容器
7. 依赖查找 VS. 依赖注入
8. 构造器注入 VS. Setter 注入
9. 面试题精选

## 13 | IoC发展简介：你可能对IoC有些误会 ##

相对传统架构会比较复杂，解耦一些相关设定

### IoC发展简介 ###

好莱坞原则->控制反转

2004年，提出了自己对IoC以及DI的理解《Inversion of Control Containers and the Dependency Injection pattern》

## 14 | IoC主要实现策略 ##

维基百科

Implementation techniques

* Using a contextualized lookup：Java Beans -> beancontext
* Using template method design pattern
* Using strategy design pattern

## 20 | 构造器注入 VS. Setter注入：为什么Spring官方文档的解读会与作者的初心出现偏差？ ##

## 21 | 面试题精选 ##

**什么是IoC?**

简单地说，IoC是反转控制，类似于好莱坞原则，主要有依赖查找和依赖注入实现。——JavaBean是IoC容器的实现，Servlet也是IoC容器的实现

IoC是推的模式

**依赖查找和依赖注入的区别？**

以来查找是主动或手动的依赖查找方式，通常需要依赖容器或标准实现（标准的API，显现的调用API方式）。而依赖注入则是手动或自动依赖绑定的方式，无需依赖特定的容器和API

**Spring作为IoC容器有什么优势？**

* 典型的IoC管理，依赖查找和依赖注入
* AOP抽象
* 事务抽象
* 事件机制
* SPI扩展(SpringBoot 自动注入)
* 强大的第三方整合（Spring Data，关系型数据库）
* 易测试性（模拟对象）
* 更好的面向对象

# 第三章：Spring IoC容器概述 #

1. Spring IoC依赖查找
2. Spring IoC依赖注入
3. Spring IoC依赖来源
4. Spring IoC配置元数据
5. Spring IoC配置元信息
6. Spring IoC容器
7. Spring应用上下文
8. 使用Spring IoC容器
9. Spring IoC容器生命周期
10. 面试题精选

## 22 | Spring IoC依赖查找：依赖注入还不够吗？依赖查找存在的价值几何？ ##

### Spring IoC依赖查找 ###

* 根据Bean名称查找
	* 实时查找
	* 延迟查找
* 根据Bean类型查找
	* 单个Bean对象   




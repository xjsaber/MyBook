# SpringBoot #

## 1-1 课程导学 ##

原因

1. 12
2. 213
3. 与时俱进的技术信仰

课程特色

Spring Boot是如何基于Spring Framework逐步走向自动装配？
SpringApplication是怎样掌握Spring应用生命周期？
Spring Boot外部化配置与SPring Environment抽象之间是什么关系？
Spring Web MVC向Spring Reactive WebFlux过渡的真实价值和意义？

场景分析掌握技术选型，系统学习拒绝浅尝辙止，重视规范了解发展趋势，源码解读理解设计思想，实战演练巩固学习成果。

技术储备要求

* 语言：Java8 Lambda
* 框架：Spring Framework基础较熟练
* 运用：Spring Boot基础

## 1-2 为什么说Spring Boot2.0 易学？ ##

组建自动装配：规约大于配置，专注核心业务
外部化配置：一次构建、按需调配，到处运行
嵌入式容器：内置让其、无需部署、独立运行
SpringBootStarter：简化依赖、按需装配、自我包含
Production-Ready：一站式运维、生态无缝整合

#### SpringBoot 难精通 ####

组建自动装配：模式注解、@Enable模块、条件装配、加载机制
外部话配置：Environment抽象、生命周期、破坏性变更
嵌入式容器：Servlet Web容器、Reactive Web容器
SpringBootStarter：依赖管理、装配条件、装配顺序
Production-Ready：健康检查、数据指标、@Endpoint管控

### SpringBoot与JavaEE规范 ###

Web： Servlet（JSR-315、JSR-340）
SQL： JDBK（JSR-221）
数据校验：Bean Validation（JSR303、JSR-349）
缓存：Java Caching （JSR-107）
WebSockets：Java API for WebSocket（JSR-356）
WebServices：JAX-WS（JSR-224）
Java管理：JMX（JSR 3）
消息：JMS(JSR-914)

## 1-3 系统总览 ##

### 课程介绍 ###

课程内容

* 核心特性
* Web应用
* 数据相关
* 功能扩展
* 运维管理
* 课程总结

## 1-4 核心特性介绍 ##

### Spring Boot 三大特性 ###

* 组件自动装配：Web MVC、Web Flux、JDBC等
* 嵌入式Web容器：Tomcat、Jetty以及Undertow
* 生产装备特性：指标、健康检查、外部化配置等

## 1-5 核心特性之组件自动装配 ##

* 激活：@EnableAutoConfiguration
* 配置：/META_INFO/spring.factories
* 实现：XXXAutoCOnfiguration

### 嵌入式Web容器 ###

* Web Servlet： Tomcat、Jetty和Undertow
* Web Reactive: Netty Web Server

### 生产准备特性 ###

* 指标：/actuator/metrics
* 健康检查：/autuator/health
* 外部化配置：/actuator/configprops

## 1-6 Web应用介绍 ##

传统Servlet应用

* Servlet组件：Servlet、Filter、Listener
* Servlet注册：Servlet注解、Spring Bean、RegistrationBean
* 异步非阻塞：异步Servlet、非阻塞Servlet

## 1-7 传统Servlet应用 ##

	spring-boot-starter-web

### Servlet组件 ###

* Servlet
	* 实现
		* @WebSerlvet
		* HttpServlet
		* 注册
	* URL映射
		* `@WebServlet(urlPatterns = "/my/servlet")`
		*  
	* 注册
		* `@ServletComponentScan(basePackages = "com.xjsaber.diveinsprintboot.web.servlet") `
* Filter
* Listener

### Servlet注册 ###

#### Servlet 注解 ####

* @ServletComponentScan +
	* @WebServlet 
	* @WebFilter
	* @WebListener

#### Spring Bean ####

* @Bean +
	* Servlet
	* Filter
	* Listener
	* 
### 异步非阻塞 ### 

## 1-8 异步非阻塞Servlet代码示例 ##

### 异步Servlet ###

* javax.servlet.ServletRequest#startAsync()
* javax.servlet.AsyncContext

### 非阻塞Servlet ###

* javax.servlet.ServletInputStream#setReadListener
	* javax.servlet.ReadListener
* javax.servlet.ServletOutputStream#setWriteListener
	* javax.servlet.WriteListener  

	TODO 还有问题

## 1-9 Spring Web MVC应用介绍 ##

### Spring Web MVC应用 ###

* Web MVC视图：模板引擎、内容协商、异常处理等
* Web MVC REST：资源服务、资源跨域、服务发现等
* Web MVC核心：核心架构、处理流程、核心组件

### Web MVC视图 ###

* ViewResulver
* View

#### 模板引擎 ####

* Thymeleaf
* Freemarker
* JSP

#### 内容协商 ####

* ContentNegotiationConfigurer
* ContentNegotiationStrategy
* ContentNegotiatingViewResolver

#### 异常处理 ####

* @ExceptionHandler
* HandlerExceptionResolver
	* ExceptionHandlerExceptionResovler
* BasicErrorController（Spring Boot） 

### Web MVC REST ###

#### 资源服务 ####

* @RequestMapping
	* @GetMapping
* @ResponseBody
* @RequestBody

#### 资源跨域 ####

* `CrossOrigin` SpringFramework
* `WebMVCCOnfigurer#addCorsMappings`
* 传统解决方案
	* IFrame
	* JSONP 

#### 服务发现 ####

* HATESOS

### Web MVC核心 ###

核心架构

处理流程

核心组建

* DispatcherServlet
* HandletMapping
* HandlerAdapter
* ViewResolver

第2章

## 2-1 自动装配 ##

* Spring Framework手动装配
* SpringBoot自动装配
* 课堂总结

Spring Framework手动装配

Spring模式注解装配

* 定义：一种用于声明在应用中扮演“组件”角色的注解
* 举例：@Compontent、@Service、@Configuration等
* 装配：<content:component-scan>或@ComponentScan

@Component作为一种由Spring容器托管的通用模式组件，任何被@Component标准的组件均为组件扫描的候选对象。

### 自定义模式注解 ###

#### 派生性 ####

* Component
	* @Reository
		* FirstLevelRepository  

#### 层次性 ####

* Component
	* @Reository
		* FirstLevelRepository  
			*  SecondLevelRepository


类似类，并不是真正的派生和层次性

## 2-4 @Enable 模块装配两种方式 ##

### Spring @Enable 模块装配 ###

* 定义：具备相同领域的功能组件集合，组合所形成的一个独立的单元
* 举例：@EnableWebMVC、@EnableAutoConfiguration等
* 实现：注解方式、编程方式

### 实现方式 ###

#### 注解驱动方式 @EnableHelloWorld ####


#### 接口编程方式 @EnableServer ####


## 2-5 Spring 条件装配 ##

* 定义： Bean装配的前置判断
* 举例

|Spring注解|场景说明|起始版本|
|--|--|--|
|Profile|配制化条件装配|3.1|
|Coditional|编程条件装配|4.0|

实现方式

配置方式-`@Profile`

计算服务，整数求和sum

编程方式-`@Conditional`

## 2-6 自定义条件装配 ##

### 基于配置方式实现 -@Profile ###

计算服务，多整数求和 sum

@Profile("Java7"):for循环
@Profile("Java8"):Lambda

### 基于编程方式实现 - @ConditionalOnSystemProperty ###

condition 包名

Java 系统属性 条件判断

getPropertyCondition

## 2-7 基于编程方式实现条件装配 ##

## 2-8 SpringBoot自动装配 ##

Condition

Spring Boot自动装配

* 定义：基于约定大于配置的原则，实现Spring组件自动装配的目的。
* 装配：模式注解、@Enable模块、条件装配、工厂加载机制
* 实现：激活自动装配、实现自动装配、配置自动装配实现



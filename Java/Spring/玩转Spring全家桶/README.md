# 玩转Spring全家桶 #

# 第一章：初识Spring #

## 01 | Spring课程介绍 ##

## 02 | 一起认识Spring家族的主要成员 ##

## 03 | 跟着Spring了解技术趋势 ##

## 04 | 编写你的第一个Spring程序 ##

# 第二章：JDBC必知必会 #

## 05 | 如何配置单数据源 ##

### SpringBoot的配置演示 ###

* 引入对应数据库驱动——H2
* 引入JDBC依赖——spring-boot-starter-jdbc
* 获取DataSource Bean，打印信息
* 也可通过/acturator/beans查看Bean

### 直接配置所需的Bean ###

**数据源相关**

* DataSource

**事务相关（可选）**

* PlatformTransactionManager
* TransactionTemplate

**操作相关（可选）**

* JdbcTemplate

### SpringBoot做了哪些配置 ###

**DataSourceAutoConfiguration**

* 配置DataSource

**DataSourceTransactionManagerAutoConfiguration**

* 配置DataSourceTransactionManager

**JdebcTemplateAutoConfiguration**

* 配置JdbcTemplate

符合条件时才进行配置 

### 数据源相关配置属性 ###

#### 通用 ####

* spring.datasource.url=jdbc:mysql://localhost/test
* spring.datasource.username=jdbc:mysql://localhost/test
* spring.datasource.password=jdbc:mysql://localhost/test
* spring.datasource.driver-class-name=jdbc:mysql://localhost/test(可选)

#### 初始化内嵌数据库 ####

* spring.datasource.initialization-mode=embedded|always|nerver
* spring.datasource.schema与spring.datasource.data确定初始化SQL文件
* spring.datasource.platform=hsqldb|h2|oracle|mysql|postgresql

## 06 | 如何配置多数据源 ##

### 配置多数据源的注意事项 ###

* 不同数据源的配置要分开
* 关注每次使用的数据源
	* 有多个DataSrouce时系统如何判断
	* 对应的设施（事务、ORM等）如何选择DataSource 

### SpringBoot中的多数据源配置 ###

* 手工配置两组DataSource及相关内容
* 与SpringBoot协同工作（二选一）
	* 配置@Primary类型的Bean
	* 排除Spring Boot的自动配置
		* DataSourceAutoConfiguration
		* DataSourceTransactionManageAutoConfiguration
		*   

## 07 | 那些好用的连接池：HikariCP ##

SpringBoot1.x Tomcat的DataSource
SpringBoot2.x HikariCP

### HikariCP为什么快 ###

1. 字节码级别优化（很多方法通过JavaAssit生成）
2. 大量小改进
	* 用FastStatementList代替ArrayList
	* 无锁集合ConcurrentBag（借鉴Dotnet的ConcurrentBag）
	* 代理类的优化（比如，用invokestatic代替了invokevirtual）
	
### 在SpringBoot中的配置 ###

Spring Boot 2.x

* 默认使用HikariCP
* 配置spring.datasource.hikari.*配置

### 常用的HikariCP配置参数 ###

常用配置

* spring.datasource.hikari.maximumPoolSize=10
* spring.datasource.hikari.minimumIdle=10
* spring.datasource.hikari.idleTimeout=600000
* spring.datasource.hikari.connectionTimeout=30000
* spring.datasource.hikari.maxLifetime=1800000

## 08 | 那些好用的连接池：Alibaba Druid ##

* 经过阿里巴巴各大系统的考研，值得信赖
* 实用的功能
	* 详细的监控
	* ExceptionSorter，针对主流数据库的返回码都有支持
	* SQL防注入
	* 内置加密配置
	* 众多扩展点，方便进行定制 

### 数据源配置 ###

* 直接配置DruidDataSource
* 通过druid-spring-boot-starter
	* spring.datasource.druid.* 

1. Filter配置
2. 密码加密
3. SQL防注入

### Druid Filter ###

* 用于定制连接池操作的各个环节
* 可以继承FilterEventAdapter以便方便实现Filter
* 修改META-INF/druid-filter.properties增加Filter配置

### 选择连接时的考量点 ###

* 可靠性
* 性能
* 功能
* 可运维性
* 可扩展性
* 其他

## 09 | 如何通过Spring JDBC访问数据库 ##

### spring-jdbc ###

* core，JdbcTemplate等相关核心接口和类
* datasource，数据源相关的辅助类
* object，将基本的JDBC操作封装成对象
* support，错误码等其他辅助工具

### 常用的Bean注解 ###

#### 通过注解定义Bean ####

* @Component
* @Repository
* @Serivce
* @Controller
	* RestController

### 简单的JDBC操作 ###

#### JdbcTemplate ####

* query
* queryForObject
* queryForList
* update
* execute

### SQL批处理 ###

#### JdbcTemplate ####

* batchUpdate
	* BatchPreparedStatementSetter 

#### NamedParameterJdbcTemplate ####
  
* batchUpdate
	* SqlParameterSourceUtils.createBatch 

## 10 | 什么是Spring的事务抽象（上） ##

## 11 | 什么是Spring的事务抽象（下） ##

## 13 | 了解Spring的JDBC异常抽象 ##

# 第四章：NoSQL实践 #

## 24 | 通过Docker辅助开发 ##

### Docker常用命令 ###

#### 镜像相关 ####

* docker pull <image>
* docker search <image>

#### 容器相关 ####

* docker run
* docker start/stop<容器名>
* docker ps<容器名>
* docker logs<容器名>

dokcer run的常用选项

### 通过Docker启动MongoDB ###

#### 官方指引 ####

#### 获取镜像 ####

#### 运行MongoDB镜像 ####

25 | 在Spring中访问MongoDB

26 | 在Spring中访问Redis


27 | 在Spring中访问Redis

# 第五章：数据访问进阶 #

## 31 | Project Reactor介绍（上） ##

Project Reactor

## 32 | Project Reactor介绍（下） ##

### 一些核心的概念 ###

#### Operators - Publisher / Subscriber ####

* Nothing Happens Until You subscribe()
* Flux[0..N] - onNext()、onComplete()、onError()
* Mono[0..1] - onNext()、onComplete()、onError()

#### Backpressure ####

* Subscription
* onRequest()、onCancel()、onDispose()

#### 线程调度Schedulers ####

* immediate() / single() / newSingle()
* elastic()【缓存，60秒之后会被回收】 / parallel() 【固定线程池】/ newParallel()

#### 错误处理 ####

* onError / onErrorReturn / onErrorResume
* doOnError / doFinally


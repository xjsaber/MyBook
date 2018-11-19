# Java企业级电商项目架构演进之路 * #

## ch1 导学 ##

tomcat集群 + redis集群

### Tomcat集群 ###

* Nginx负载均衡策略解析
* Nginx负载均衡及实战
* Tomcat+Nginx集群环境搭建

* Redis + Cookie + Jackson + Filter原生解决集群Session共享问题
* Spring Session零侵入解决session共享

### Redis——基础强化 ###

* Redis环境搭建
* Redis常用命令实战
* Redis数据结构解析

* Jedis源码解析
* Jedis API的封装

### Redis——分布式 ###

* Redis分布式环境搭建
* Consistent hashing分布式算法原理讲解

Consistent hashing——一致性算法

* ShardedJedisPool连接池编写实战
* Redis分布式锁实战
* Redis Session框架

### 单点登录 ###

* Redis构建Session服务器
* Redis+Cookie+Jackson+Filter实战
* SessionExpireFilter构建Session时间重置过滤器
* Spring Session源码解析
* Spring Session实现单点登录

### 定时关单 ###

* Spring Schedule Cron表达式
* Spring Schedule实现定时关单
* Spring Schedule + Redis分布式锁实战
* Spring Schedule + Redisson分布式锁实战
* 分布式任务调度

### 实用工具封装 ###

* Jedis及ShardedJedis客户端连接封装及使用
* Cookie封装及使用 
* Jackson源码解析 
* Jackson实现JSON多泛型序列化及反序列化

### 项目代码重构 ###

* Guava Cache迁移Redis分布式缓存
* SpringMVC拦截器实现管理员权限统一校验
* SpringMVC全局异常控制
* SpringMVC RESTful实现商品搜索及浏览

### 开发技巧实操 ###

* Lombok原理及使用
* Java Decompile
* Redis Desktop Manager
* Multi-Process Debug
* iTerm联动操作多窗口命令行

## 1-3 一起回顾 ##

问答区

### 思维Level提升 ###

# ch2 Lombok #

## 2-1 Lombok快速入门 ##

### Lombok介绍 ###

[Lombok官网](https://projectlombok.org)

通过简单注解来精简代码达到消除冗长代码的目的

### Lombok优点 ###

IDE必须要支持Lombok，否则IDE会报错

### Lombok原理 ###

* JSR 269 Pluggable Annotation Processing API
* javac从Java6开始支持“JSR 269 API”规范
* 只要程序实现了该API，就能在javac运行的时候得到调用
* Lombok实现了“JSR 269 API”，在编译时，javac编译源码的具体流程如下：Source File -> Parse -> AST -> (Lombok Annotation Processor -> AST -> Lombok Annotation Handler) -> Modified AST -> Analyze and Generate -> Byte Code

### Lombok引入项目 ###

<scope>provided</scorp> 表示编译的时候不会打进war包

### Lombok实战Coding  ###

* @Data 类上
* @Getter 自动生成get方法
* @Getter(AccessLevel.PROTECTED) 生成的get方法，默认是protected
* @Setter 自动生成set方法
* @Setter(AccessLevel.PROTECTED) 同get
* @NoArgsConstructor 无参构造器
* @AllArgsContructor
* @ToString 
* @ToString(exclude= "column") 排除哪些属性
* @EqualsAndHashCode equals和hashcode方法自动重写
* @EqualsAndHashCode(exclude= "column") 
* @Slf4j （课程使用logback日志框架，调用小写的log）
* @Log4j （使用log4j日志框架）
* @ToString(exclude="column")
* @ToString(exclude={"column1","column2"})
* @ToString(of="column")
* @ToString(of={"column1","column2"})
* EqualsAndHashCode同上

* @Data包含了
	* @Getter 
	* @Setter
	* @ToString
	* @EqualsAndHashCode

### 反编译大法 ###

Java Decompiler

* JD-GUI
* JD-Eclipse
* JD-InetlliJ

### Lombok验证 ###

Lombok实际使用需要注意的点

* 在类需要序列化，反序列化时详细控制字段时，例如：Jackson json序列化
* 使用@Slf4j还是@Log4j看项目使用的日志框架，教程中使用Logback，所以使用@Slf4j
* 选择适合的地方使用Lombok，例如POJO是个好地方，POJO很单纯...

## 2-2 Lombok实战 ##

## 2-3 Lombok总结 ##

* Lombok介绍及优点
* Lombok原理
* Lombok引入项目
* IDEA/Eclipse安装Lombok插件
* Lombok实战Coding
* 反编译大法
* Lombok验证
* Lombok工作中需注意的点

# ch3 Maven环境隔离 #

## 3-1 Maven快速入门 ##

* 实际的项目环境
* 隔离环境之间各种配置存在的差异
* Maven环境隔离解决的实际问题
* Maven环境隔离配置及原理

### 实际的项目环境 ###

* 本地开发环境（Local）
* 开发环境（Dev）
* 测试环境（Beta/QA）
* 线上环境（Prod）

### Maven环境隔离解决的实际问题 ###

* pom.xml中增加profiles节点

	<profiles>
		<profile>
			<id></id>
			<activation>
				<activeByDefault>true</activeByDefault>
			</activation>
			<properties>
				<deploy.type>dev</deploy.type>
			</properties>
		</profile>
	</profile>

* pom.xml中build节点增加

	<resources>
		<resource>
			<directory>src/main/resources.${deploy.type}</directory>
			<excludes>
				<exclude>*.jsp</exclude>
			</excludes>
		</resource>
		<resource>
			<directory>src/main/ressources</directory>
		</resource>
	</resources>


# ch4 #

# ch5 #

# ch6 #

# ch7 #
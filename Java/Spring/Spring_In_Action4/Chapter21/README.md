# 第21章 借助SpringBoot简化Spring开发 #

## 21.1 Spring Boot简化 ##

### 21.1.1 添加 Starter依赖 ###

可以通过SpringBoot的web和JDBC Starter取代了几个更加粗粒度的依赖。

### 21.1.2 自动配置 ###

SpringBoot的Starter减少了构建中依赖列表的长度，而SpringBoot的自动配置功能则削减了Spring配置的数量。

### 21.1.3 Spring Boot CLI ###

SpringBootCLI充分利用了SpringBootStarter和自动配置的魔力。

### 21.1.4 Actuator ###

* 管理端点
* 合理的异常处理以及默认的“/error”映射端点；
* 获取因公也能够信息的“/info”端点
* 当启用Spring Security时，会有一个审计事件框架。

## 21.2 使用SpringBoot构建应用 ##

### 21.2.1 处理请求 ###

	compile("org.springframework.boot:spring-boot-starter-web")
	//因为SpringBoot parent项目指定了Web Starter依赖的版本。因此在项目的build.gradle混合pom.xml文件中没有必要再显示指定版本信息。

### 21.2.2 创建视图 ###

将Thymeleaf添加到项目的构建中。

	compile("org.thymeleaf:thymeleaf-spring4")
需要记住的是，只要我们将Thymeleaf添加到项目的类路径下，就启用了SpringBoot的自动配置。当应用运行时，SpringBoot将会探测到类路径中的Thymeleaf，然后会自动配置视图解析器、模板解析器以及模板引擎。

### 21.2.3 添加静态内容 ###

* /META-INF/resources/style.css
* /resources/style.css
* /static/style.css
* /public/style.css

### 21.2.4 持久化数据 ###

compile("org.springframework.boot:spring-boot-starter-jdbc")
compile("com.h2database:h2")

### 21.2.5 尝试运行 ###

	@ComponentScan 组建搜索

## 21.3 组合使用Groovy与SpringBootCLI ##

### 21.3.1 编写Groovy控制器 ###

## 21.4 通过Actuator获取了解应用内部状况 ##

Spring Boot Actuator所完成的主要功能就是为基于SpringBoot的应用添加多个有用的管理端点。

## 21.5 小结 ##


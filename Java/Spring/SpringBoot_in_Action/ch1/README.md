# Chapter1 Bootstarting Spring #

发现 Spring in action类似，决定先放放，看下一章

## 1.1 Spring rebooted ##

### 1.1.1 Taking a fresh look at Spring ###

* A project structure, complete with a Maven or Gradle build file including
required dependencies. At the very least, you’ll need Spring  MVC and the Serv-
let  API expressed as dependencies.
* A web.xml file (or a  WebApplicationInitializer implementation) that declares
Spring’s  DispatcherServlet .
* A Spring configuration that enables Spring  MVC .
* A controller class that will respond to  HTTP requests with “Hello World”.
* A web application server, such as Tomcat, to deploy the application to.

## 1.1.2 Examining Spring Boot essentials ##

* 自动配置
* 起步依赖
* 命令行界面
* Actuator

1. 自动配置
在任何Spring应用程序的源代码里，你都会找到Java配置或XML配置（抑或两者皆有），它们为应用程序开启了特定的特性的功能。

2. 起步依赖

3. 命令行界面
快速开发Spring应用程序，Spring Boot CLI利用了起步依赖和自动配置。
4. Actuator
Actuator提供在运行时检视应用程序内部情况的能力

1.1.3 Spring Boot不是什么？

1.2 SpringBoot入门

1.2.1 安装Spring Boot CLI
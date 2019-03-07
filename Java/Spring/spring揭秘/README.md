# Spring揭秘 #

## 第1章 Spring框架的由来 ##

### 1.1 Spring之崛起 ###

### 1.2 Spring框架概述 ###

### 1.3 Spring大观园 ###

### 1.4 小结 ###

## 第2章 IoC的基本概念 ##

### 2.1 我们的理念是：让别人为你服务 ###

2.2 手语，呼喊，还是心有灵犀

2.3 IoC的附加值

2.4 小结

第3章 掌管大局的Ioc Service Provider

## 第4章 Spring的IoC容器之BeanFactory ##

* BeanFactory。基础类型IoC容器，默认采用延迟初始化策略（lazy-load）。只有当客户端对象需要访问容器中的某个受管对象的时候，才对
该受管对象进行初始化以及依赖注入操作。
* ApplicationContext。 ApplicationContext 在 BeanFactory 的基础上构建，是相对比较高级的容器实现，除了拥有 BeanFactory 的所有支持， ApplicationContext 还提供了其他高级特性。

BeanFactory就是生产Bean的工厂。

### 4.1 拥有BeanFactory之后的生活 ###

### 4.2 BeanFactory的对象注册与依赖绑定方式 ###

BeanFactory 作为一个IoC Service Provider

#### 4.2.1 直接编码方式 ####



#### 4.2.2 外部配置文件方式 ####

#### 4.2.3 注解方式 ####

### 4.3 BeanFactory的XML之旅 ###

#### 4.3.1 <beans>和<bean> ####

**1. <beans>之唯我独尊**

* default-lazy-init：其值可以指定为 true 或者 false ，默认值为 false 。用来标志是否对所有的 <bean> 进行延迟初始化。
* default-autowire：可以取值为 no 、 byName 、 byType 、 constructor 以及 autodetect 。默认值为 no ，如果使用自动绑定的话，用来标志全体bean使用哪一种默认绑定方式。
* default-dependency-check：可以取值 none 、 objects 、 simple 以及 all ，默认值为 none ，即不做依赖检查。
* default-init-method：如果所管辖的 <bean> 按照某种规则，都有同样名称的初始化方法的话，可以在这里统一指定这个初始化方法名，而不用在每一个 <bean> 上都重复单独指定。
* default-destory-method：与 default-init-method 相对应，如果所管辖的bean有按照某种规则使用了相同名称的对象销毁方法，可以通过这个属性统一指定。

**2.<decription>、<import>和<alias>**

* <description>

描述性

* <import>

通常情况下，可以根据模块功能或者层次关系，将配置信息分门别类地放到多个配置文件中。在想加载主要配置文件，并将主要配置文件所依赖的配置文件同时加载时，可以在这个主要的配置文件中通过 <import> 元素对其所依赖的配置文件进行引用。

如果A.xml中的<bean>定义可能依赖B.xml中的某些 <bean> 定义，那么就可以在A.xml中使用 <import> 将B.xml引入到A.xml，以类似于<import resource="B.xml"/> 的形式。

* <alias>

可以通过 <alias> 为某些 <bean> 起一些“外号”（别名），通常情况下是为了减少输入。

#### 4.3.2 孤孤单单一个人 ####

* id属性

通过 id 属性来指定当前注册对象的beanName是什么。通过 id 指定 beanName 为 djNewsListener 。

* class属性

每个注册到容器的对象都需要通过 <bean> 元素的 class 属性指定其类型

#### 4.3.3 Help Me，Help You ####

1. 构造方法注入的XML之道







#IoC容器基本原理

##2.2.1 IoC容器的概念
IoC容器就是具有依赖注入功能的容器，IoC容器负责实例化、定位、配置应用程序中的对象及建立这些对象间的依赖。应用程序无需直接在代码中new相关的对象，应用程序由IoC容器进行组装。在Spring中BeanFactory是IoC容器的实际代表者。

Spring IoC容器如何知道哪些是它管理的对象呢？这就需要配置文件，Spring IoC容器通过读取配置文件中的配置元数据，通过元数据对应用中的各个对象进行实例化及装配。一般使用基于xml配置文件进行配置元数据，而且Spring与配置文件完全解耦的，可以使用其他任何可能的方式进行配置元数据，比如注解、基于java文件的、基于属性文件的配置都可以。

##2.2.2 Bean的概念
由IoC容器管理的那些组成你应用程序的对象我们就叫它Bean， Bean就是由Spring容器初始化、装配及管理的对象。

##2.2.3 Hello World
### 一、配置环境 ###
Spring 依赖：本书使用spring-framework-3.0.5.RELEASE
spring-framework-3.0.5.RELEASE-with-docs.zip表示此压缩包带有文档的；
spring-framework-3.0.5.RELEASE-dependencies.zip表示此压缩包中是spring的依赖jar包，所以需要什么依赖从这里找就好了；
下载地址：http://www.springsource.org/download
### 二、开始Spring Hello World之旅 ###
1、准备需要的jar包

2、创建标准Java工程

3、

### 2.2.4 详解IoC容器 ###
在Spring Ioc容器的代表就是org.springframework.beans包中的BeanFactory接口，BeanFactory接口提供了IoC容器最基本功能；而org.springframework.context包下的ApplicationContext接口扩展了BeanFactory，还提供了与Spring AOP集成、国际化处理、事件传播及提供不同层次的context实现 (如针对web应用的WebApplicationContext)。简单说， BeanFactory提供了IoC容器最基本功能，而 ApplicationContext 则增加了更多支持企业级功能支持。ApplicationContext完全继承BeanFactory，因而BeanFactory所具有的语义也适用于ApplicationContext。

容器实现一览：

**XmlBeanFactory**：BeanFactory实现，提供基本的IoC容器功能，可以从classpath或文件系统等获取资源；

	（1）File file = new File("fileSystemConfig.xml");
	Resource resource = new FileSystemResource(file);
	BeanFactory beanFactory = new XmlBeanFactory(resource);

    （2）Resource resource = new ClassPathResource("classpath.xml");                 
    BeanFactory beanFactory = new XmlBeanFactory(resource);

**ClassPathXmlApplicationContext**：ApplicationContext实现，从classpath获取配置文件；

    BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath.xml");

**FileSystemXmlApplicationContext**：ApplicationContext实现，从文件系统获取配置文件。
         
	BeanFactory beanFactory = new FileSystemXmlApplicationContext("fileSystemConfig.xml");

ApplicationContext接口获取Bean方法简介：

* Object getBean(String name) 根据名称返回一个Bean，客户端需要自己进行类型转换；
* T getBean(String name, Class<T> requiredType) 根据名称和指定的类型返回一个Bean，客户端无需自己进行类型转换，如果类型转换失败，容器抛出异常；
* T getBean(Class<T> requiredType) 根据指定的类型返回一个Bean，客户端无需自己进行类型转换，如果没有或有多于一个Bean存在容器将抛出异常；
* Map<String, T> getBeansOfType(Class<T> type) 根据指定的类型返回一个键值为名字和值为Bean对象的 Map，如果没有Bean对象存在则返回空的Map。

让我们来看下IoC容器到底是如何工作。在此我们以xml配置方式来分析一下：

1. 准备配置文件：就像前边Hello World配置文件一样，在配置文件中声明Bean定义也就是为Bean配置元数据。

2. 由IoC容器进行解析元数据： IoC容器的Bean Reader读取并解析配置文件，根据定义生成BeanDefinition配置元数据对象，IoC容器根据BeanDefinition进行实例化、配置及组装Bean。

3. 实例化IoC容器：由客户端实例化容器，获取需要的Bean。

### **2.2.5  小结** ###
除了测试程序的代码外，也就是程序入口，所有代码都没有出现Spring任何组件，而且所有我们写的代码没有实现框架拥有的接口，因而能非常容易的替换掉Spring，是不是非入侵。

客户端代码完全面向接口编程，无需知道实现类，可以通过修改配置文件来更换接口实现，客户端代码不需要任何修改。是不是低耦合。

如果在开发初期没有真正的实现，我们可以模拟一个实现来测试，不耦合代码，是不是很方便测试。
Bean之间几乎没有依赖关系，很容易重用。
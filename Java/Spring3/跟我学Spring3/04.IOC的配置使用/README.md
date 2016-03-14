# 2.3IoC的配置使用 #

## 2.3.1 XML配置的结构 ##

一般配置如下：

	<beans>  
    	<import resource=”resource1.xml”/>  
    	<bean id=”bean1”class=””></bean>  
    	<bean id=”bean2”class=””></bean>  
	<bean name=”bean2”class=””></bean>  
    	<alias alias="bean3" name="bean2"/>  
    	<import resource=”resource2.xml”/>  
	</beans> 

1. <bean>标签主要用来进行Bean定义；
2. alias用于定义Bean别名的；
3. import用于导入其他配置文件的Bean定义，这是为了加载多个配置文件，当然也可以把这些配置文件构造为一个数组（new String[] {“config1.xml”, config2.xml}）传给ApplicationContext实现进行加载多个配置文件，那一个更适合由用户决定；这两种方式都是通过调用Bean Definition Reader 读取Bean定义，内部实现没有任何区别。<import>标签可以放在<beans>下的任何位置，没有顺序关系。

## 2.3.2 Bean的配置 ##
Spring IoC容器目的就是管理Bean，这些Bean将根据配置文件中的Bean定义进行创建，而Bean定义在容器内部由BeanDefinition对象表示，该定义主要包含以下信息：
* 全限定类名（FQN）：用于定义Bean的实现类;
* Bean行为定义：这些定义了Bean在容器中的行为；包括作用域（单例、原型创建）、是否惰性初始化及生命周期等；
* Bean创建方式定义：说明是通过构造器还是工厂方法创建Bean；
* Bean之间关系定义：即对其他bean的引用，也就是依赖关系定义，这些引用bean也可以称之为同事bean 或依赖bean，也就是依赖注入。

Bean定义只有“全限定类名”在当使用构造器或静态工厂方法进行实例化bean时是必须的，其他都是可选的定义。难道Spring只能通过配置方式来创建Bean吗？回答当然不是，某些SingletonBeanRegistry接口实现类实现也允许将那些非BeanFactory创建的、已有的用户对象注册到容器中，这些对象必须是共享的，比如使用DefaultListableBeanFactory 的registerSingleton() 方法。不过建议采用元数据定义。

## 2.3.3 Bean的命名 ##
每个Bean可以有一个或多个id（或称之为标识符或名字），在这里我们把第一个id称为“标识符”，其余id叫做“别名”；这些id在IoC容器中必须唯一。如何为Bean指定id呢，有以下几种方式:

一、  不指定id，只配置必须的全限定类名，由IoC容器为其生成一个标识，客户端必须通过接口“T getBean(Class<T> requiredType)”获取Bean；

	<bean class=” cn.javass.spring.chapter2.helloworld.HelloImpl”/>（1）              
测试代码片段如下：

	@Test  
	public void test1() {  
	BeanFactory beanFactory =  
	   new ClassPathXmlApplicationContext("chapter2/namingbean1.xml");  
	    //根据类型获取bean  
	    HelloApi helloApi = beanFactory.getBean(HelloApi.class);  
	    helloApi.sayHello();  
	}

二、指定id，必须在Ioc容器中唯一；

	<bean id=” bean” class=” cn.javass.spring.chapter2.helloworld.HelloImpl”/>    （2）  

测试代码片段如下：

	@Test  
	public void test2() {  
	BeanFactory beanFactory =  
	new ClassPathXmlApplicationContext("chapter2/namingbean2.xml");  
	//根据id获取bean  
	    HelloApi bean = beanFactory.getBean("bean", HelloApi.class);  
	    bean.sayHello();  
	}

三、指定name，这样name就是“标识符”，必须在Ioc容器中唯一：

	<bean name=” bean” class=” cn.javass.spring.chapter2.helloworld.HelloImpl”/> （3）

测试代码片段如下：

	@Test  
	public void test3() {  
	    BeanFactory beanFactory =  
	new ClassPathXmlApplicationContext("chapter2/namingbean3.xml");  
	    //根据name获取bean  
	HelloApi bean = beanFactory.getBean("bean", HelloApi.class);  
	bean.sayHello();  
	}  

四、指定id和name，id就是标识符，而name就是别名，必须在Ioc容器中唯一：

	<bean id=”bean1”name=”alias1”  
	class=” cn.javass.spring.chapter2.helloworld.HelloImpl”/>  
	<!-- 如果id和name一样，IoC容器能检测到，并消除冲突 -->  
	<bean id="bean3" name="bean3" class="cn.javass.spring.chapter2.helloworld.HelloImpl"/>              （4） 

	@Test  
	public void test4() {  
	BeanFactory beanFactory =  
	new ClassPathXmlApplicationContext("chapter2/namingbean4.xml");  
	    //根据id获取bean  
	    HelloApi bean1 = beanFactory.getBean("bean1", HelloApi.class);  
	    bean1.sayHello();  
	    //根据别名获取bean  
	    HelloApi bean2 = beanFactory.getBean("alias1", HelloApi.class);  
	    bean2.sayHello();  
	    //根据id获取bean  
	    HelloApi bean3 = beanFactory.getBean("bean3", HelloApi.class);  
	    bean3.sayHello();  
	    String[] bean3Alias = beanFactory.getAliases("bean3");  
	    //因此别名不能和id一样，如果一样则由IoC容器负责消除冲突  
	    Assert.assertEquals(0, bean3Alias.length);  
	}

五、指定多个name，多个name用“，”、“；”、“ ”分割，第一个被用作标识符，其他的（alias1、alias2、alias3）是别名，所有标识符也必须在Ioc容器中唯一:

	<bean name=” bean1;alias11,alias12;alias13 alias14”  
	      class=” cn.javass.spring.chapter2.helloworld.HelloImpl”/>     
	<!-- 当指定id时，name指定的标识符全部为别名 -->  
	<bean id="bean2" name="alias21;alias22"  
	class="cn.javass.spring.chapter2.helloworld.HelloImpl"/>              （5）      

测试代码片段如下：

	@Test  
	public void test5() {  
	BeanFactory beanFactory =  
	new ClassPathXmlApplicationContext("chapter2/namingbean5.xml");  
	    //1根据id获取bean  
	    HelloApi bean1 = beanFactory.getBean("bean1", HelloApi.class);  
	    bean1.sayHello();  
	    //2根据别名获取bean  
	    HelloApi alias11 = beanFactory.getBean("alias11", HelloApi.class);  
	    alias11.sayHello();  
	    //3验证确实是四个别名         
	    String[] bean1Alias = beanFactory.getAliases("bean1");  
	    System.out.println("=======namingbean5.xml bean1 别名========");  
	    for(String alias : bean1Alias) {  
	        System.out.println(alias);  
	    }  
	    Assert.assertEquals(4, bean1Alias.length);  
	    //根据id获取bean  
	    HelloApi bean2 = beanFactory.getBean("bean2", HelloApi.class);  
	    bean2.sayHello();  
	    //2根据别名获取bean  
	    HelloApi alias21 = beanFactory.getBean("alias21", HelloApi.class);  
	    alias21.sayHello();  
	    //验证确实是两个别名  
	    String[] bean2Alias = beanFactory.getAliases("bean2");  
	    System.out.println("=======namingbean5.xml bean2 别名========");  
	    for(String alias : bean2Alias) {  
	        System.out.println(alias);  
	    }     
	    Assert.assertEquals(2, bean2Alias.length);     
	}       


六、使用<alias>标签指定别名，别名也必须在IoC容器中唯一：

	<bean name="bean" class="cn.javass.spring.chapter2.helloworld.HelloImpl"/>  
	<alias alias="alias1" name="bean"/>  
	<alias alias="alias2" name="bean"/>                                  （6）

 测试代码片段如下：

	@Test  
	public void test6() {  
	BeanFactory beanFactory =  
	new ClassPathXmlApplicationContext("chapter2/namingbean6.xml");  
	    //根据id获取bean  
	    HelloApi bean = beanFactory.getBean("bean", HelloApi.class);  
	   bean.sayHello();  
	    //根据别名获取bean  
	    HelloApi alias1 = beanFactory.getBean("alias1", HelloApi.class);  
	    alias1.sayHello();  
	    HelloApi alias2 = beanFactory.getBean("alias2", HelloApi.class);  
	    alias2.sayHello();  
	    String[] beanAlias = beanFactory.getAliases("bean");  
	    System.out.println("=======namingbean6.xml bean 别名========");  
	    for(String alias : beanAlias) {  
	        System.out.println(alias);  
	   }  
	   System.out.println("=======namingbean6.xml bean 别名========");  
	    Assert.assertEquals(2, beanAlias.length);  
	 }

定义来看，name或id如果指定它们中的一个时都作为“标识符”，那为什么还要有id和name同时存在呢？这是因为当使用基于XML的配置元数据时，在XML中id是一个真正的XML id属性，因此当其他的定义来引用这个id时就体现出id的好处了，可以利用XML解析器来验证引用的这个id是否存在，从而更早的发现是否引用了一个不存在的bean，而使用name，则可能要在真正使用bean时才能发现引用一个不存在的bean。  

* Bean命名约定：Bean的命名遵循XML命名规范，但最好符合Java命名规范，由“字母、数字、下划线组成“，而且应该养成一个良好的命名习惯， 比如采用“驼峰式”，即第一个单词首字母开始，从第二个单词开始首字母大写开始，这样可以增加可读性。

### 2.3.4  实例化Bean ###
Spring IoC容器如何实例化Bean呢？传统应用程序可以通过new和反射方式进行实例化Bean。而Spring IoC容器则需要根据Bean定义里的配置元数据使用反射机制来创建Bean。在Spring IoC容器中根据Bean定义创建Bean主要有以下几种方式：

一、使用构造器实例化Bean：这是最简单的方式，Spring IoC容器即能使用默认空构造器也能使用有参数构造器两种方式创建Bean，如以下方式指定要创建的Bean类型：
 
使用空构造器进行定义，使用此种方式，class属性指定的类必须有空构造器

	<bean name="bean1" class="cn.javass.spring.chapter2.HelloImpl2"/>  

使用有参数构造器进行定义，使用此中方式，可以使用< constructor-arg >标签指定构造器参数值，其中index表示位置，value表示常量值，也可以指定引用，指定引用使用ref来引用另一个Bean定义，后边会详细介绍

	<bean name="bean2" class="cn.javass.spring.chapter2.HelloImpl2">  
	<!-- 指定构造器参数 -->  
	     <constructor-arg index="0" value="Hello Spring!"/>  
	</bean>  

二、使用静态工厂方式实例化Bean，使用这种方式除了指定必须的class属性，还要指定factory-method属性来指定实例化Bean的方法，而且使用静态工厂方法也允许指定方法参数，spring IoC容器将调用此属性指定的方法来获取Bean，配置如下所示：

（1）先来看看静态工厂类代码吧HelloApiStaticFactory：

	public class HelloApiStaticFactory {  
	    //工厂方法  
	       public static HelloApi newInstance(String message) {  
	              //返回需要的Bean实例  
	           return new HelloImpl2(message);  
	}  
	}  

（2）静态工厂写完了，让我们在配置文件(resources/chapter2/instantiatingBean.xml)配置Bean定义：

	<!-- 使用静态工厂方法 -->  
	<bean id="bean3" class="cn.javass.spring.chapter2.HelloApiStaticFactory" factory-method="newInstance">  
	     <constructor-arg index="0" value="Hello Spring!"/>  
	</bean>  
（3）配置完了，写段测试代码来测试一下吧，InstantiatingBeanTest：

	@Test  
	public void testInstantiatingBeanByStaticFactory() {  
	       //使用静态工厂方法  
	       BeanFactory beanFactory =  
	new ClassPathXmlApplicationContext("chaper2/instantiatingBean.xml");  
	       HelloApi bean3 = beanFactory.getBean("bean3", HelloApi.class);  
	       bean3.sayHello();  
	}  

三、使用实例工厂方法实例化Bean，使用这种方式不能指定class属性，此时必须使用factory-bean属性来指定工厂Bean，factory-method属性指定实例化Bean的方法，而且使用实例工厂方法允许指定方法参数，方式和使用构造器方式一样，配置如下：

（1）实例工厂类代码（HelloApiInstanceFactory.java）如下：

	package cn.javass.spring.chapter2;  
	public class HelloApiInstanceFactory {  
	public HelloApi newInstance(String message) {  
	          return new HelloImpl2(message);  
	   }  
	}  
   
（2）让我们在配置文件(resources/chapter2/instantiatingBean.xml)配置Bean定义：

	<!—1、定义实例工厂Bean -->  
	<bean id="beanInstanceFactory"  
	class="cn.javass.spring.chapter2.HelloApiInstanceFactory"/>  
	<!—2、使用实例工厂Bean创建Bean -->  
	<bean id="bean4"  
	factory-bean="beanInstanceFactory"  
	     factory-method="newInstance">  
	 <constructor-arg index="0" value="Hello Spring!"></constructor-arg>  
	</bean>  

（3）测试代码InstantiatingBeanTest：

	@Test  
	public void testInstantiatingBeanByInstanceFactory() {  
	//使用实例工厂方法  
	       BeanFactory beanFactory =  
	new ClassPathXmlApplicationContext("chapter2/instantiatingBean.xml");  
	       HelloApi bean4 = beanFactory.getBean("bean4", HelloApi.class);  
	       bean4.sayHello();  
	}  

这三种方式只是配置不一样，从获取方式看完全一样，没有任何不同。Spring IoC帮你创建Bean，我们只管使用就可以了，是不是很简单。

### 2.3.5  小结 ###
到此我们已经讲完了Spring IoC基础部分，包括IoC容器概念，如何实例化容器，Bean配置、命名及实例化，Bean获取等等。不知大家是否注意到到目前为止，我们只能通过简单的实例化Bean，没有涉及Bean之间关系。接下来一章让我们进入配置Bean之间关系章节，也就是依赖注入
# 3.3 更多DI的知识 #

## 3.3.1 延迟初始化Bean ##
延迟初始化也叫做惰性初始化，指不提前初始化Bean，而是只有在真正使用时才创建及初始化Bean。
配置方式很简单只需在<bean>标签上指定 “lazy-init” 属性值为“true”即可延迟初始化Bean。

Spring容器会在创建容器时提前初始化“singleton”作用域的Bean，“singleton”就是单例的意思即整个容器每个Bean只有一个实例，后边会详细介绍。Spring容器预先初始化Bean通常能帮助我们提前发现配置错误，所以如果没有什么情况建议开启，除非有某个Bean可能需要加载很大资源，而且很可能在整个应用程序生命周期中很可能使用不到，可以设置为延迟初始化。

延迟初始化的Bean通常会在第一次使用时被初始化；或者在被非延迟初始化Bean作为依赖对象注入时在会随着初始化该Bean时被初始化，因为在这时使用了延迟初始化Bean。

容器管理初始化Bean消除了编程实现延迟初始化，完全由容器控制，只需在需要延迟初始化的Bean定义上配置即可，比编程方式更简单，而且是无侵入代码的。
具体配置如下：

	<bean id="helloApi"  
	class="cn.javass.spring.chapter2.helloworld.HelloImpl"  
	lazy-init="true"/>  

## 3.3.2  使用depends-on ##

depends-on是指指定Bean初始化及销毁时的顺序，使用depends-on属性指定的Bean要先初始化完毕后才初始化当前Bean，由于只有“singleton”Bean能被Spring管理销毁，所以当指定的Bean都是“singleton”时，使用depends-on属性指定的Bean要在指定的Bean之后销毁。

配置方式如下：

	<bean id="helloApi" class="cn.javass.spring.chapter2.helloworld.HelloImpl"/>  
	<bean id="decorator"  
	    	class="cn.javass.spring.chapter3.bean.HelloApiDecorator"  
	    depends-on="helloApi">  
	    <property name="helloApi"><ref bean="helloApi"/></property>  
	</bean> 

“decorator”指定了“depends-on”属性为“helloApi”，所以在“decorator”Bean初始化之前要先初始化“helloApi”，而在销毁“helloApi”之前先要销毁“decorator”，大家注意一下销毁顺序，与文档上的不符。

“depends-on”属性可以指定多个Bean，若指定多个Bean可以用“;”、“,”、空格分割。

那“depends-on”有什么好处呢？主要是给出明确的初始化及销毁顺序，比如要初始化“decorator”时要确保“helloApi”Bean的资源准备好了，否则使用“decorator”时会看不到准备的资源；而在销毁时要先在“decorator”Bean的把对“helloApi”资源的引用释放掉才能销毁“helloApi”，否则可能销毁 “helloApi”时而“decorator”还保持着资源访问，造成资源不能释放或释放错误。

让我们看个例子吧，在平常开发中我们可能需要访问文件系统，而文件打开、关闭是必须配对的，不能打开后不关闭，从而造成其他程序不能访问该文件。让我们来看具体配置吧：

1）准备测试类：

ResourceBean从配置文件中配置文件位置，然后定义初始化方法init中打开指定的文件，然后获取文件流；最后定义销毁方法destroy用于在应用程序关闭时调用该方法关闭掉文件流。
 
DependentBean中会注入ResourceBean，并从ResourceBean中获取文件流写入内容；定义初始化方法init用来定义一些初始化操作并向文件中输出文件头信息；最后定义销毁方法用于在关闭应用程序时想文件中输出文件尾信息。

具体代码如下：

	package cn.javass.spring.chapter3.bean;  
	import java.io.File;  
	import java.io.FileNotFoundException;  
	import java.io.FileOutputStream;  
	import java.io.IOException;  
	public class ResourceBean {  
	    private FileOutputStream fos;     
	    private File file;  
	    //初始化方法  
	    public void init() {  
	        System.out.println("ResourceBean:========初始化");  
	        //加载资源,在此只是演示  
	        System.out.println("ResourceBean:========加载资源，执行一些预操作");  
	        try {  
	            this.fos = new FileOutputStream(file);  
	        } catch (FileNotFoundException e) {  
	            e.printStackTrace();  
	        }  
	    }  
	    //销毁资源方法  
	    public void destroy() {  
	        System.out.println("ResourceBean:========销毁");  
	        //释放资源  
	        System.out.println("ResourceBean:========释放资源，执行一些清理操作");  
	        try {  
	            fos.close();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	    }  
	    public FileOutputStream getFos() {  
	        return fos;  
	    }  
	    public void setFile(File file) {  
	        this.file = file;  
	    }  
	}  

	package cn.javass.spring.chapter3.bean;  
	import java.io.IOException;  
	public class DependentBean {  
	    ResourceBean resourceBean;     
	    public void write(String ss) throws IOException {  
	        System.out.println("DependentBean:=======写资源");  
	        resourceBean.getFos().write(ss.getBytes());  
	    }  
	    //初始化方法  
	    public void init() throws IOException {  
	        System.out.println("DependentBean:=======初始化");  
	resourceBean.getFos().write("DependentBean:=======初始化=====".getBytes());  
	    }  
	    //销毁方法  
	    public void destroy() throws IOException {  
	        System.out.println("DependentBean:=======销毁");  
	        //在销毁之前需要往文件中写销毁内容  
	        resourceBean.getFos().write("DependentBean:=======销毁=====".getBytes());  
	    }  
	     
	    public void setResourceBean(ResourceBean resourceBean) {  
	        this.resourceBean = resourceBean;  
	    }  
	} 

2）类定义好了，让我们来进行Bean定义吧，具体配置文件如下：
	
	<bean id="resourceBean"  
    class="cn.javass.spring.chapter3.bean.ResourceBean"  
	    init-method="init" destroy-method="destroy">  
	    <property name="file" value="D:/test.txt"/>  
	</bean>  
	<bean id="dependentBean"  
	    class="cn.javass.spring.chapter3.bean.DependentBean"  
	    init-method="init" destroy-method="destroy" depends-on="resourceBean">  
	    <property name="resourceBean" ref="resourceBean"/>  
	</bean> 

<property name="file" value="D:/test.txt"/>配置：Spring容器能自动把字符串转换为java.io.File。
init-method="init" ：指定初始化方法，在构造器注入和setter注入完毕后执行。
destroy-method="destroy"：指定销毁方法，只有“singleton”作用域能销毁，“prototype”作用域的一定不能，其他作用域不一定能；后边再介绍。
在此配置中，resourceBean初始化在dependentBean之前被初始化，resourceBean销毁会在dependentBean销毁之后执行。  

3）配置完毕，测试一下吧：

	package cn.javass.spring.chapter3;  
	import java.io.IOException;  
	import org.junit.Test;  
	import org.springframework.context.support.ClassPathXmlApplicationContext;  
	import cn.javass.spring.chapter3.bean.DependentBean;  
	public class MoreDependencyInjectTest {  
	    @Test  
	    public void testDependOn() throws IOException {  
	        ClassPathXmlApplicationContext context =  
	new ClassPathXmlApplicationContext("chapter3/depends-on.xml");  
	        //一点要注册销毁回调，否则我们定义的销毁方法不执行  
	        context.registerShutdownHook();  
	        DependentBean dependentBean =  
	context.getBean("dependentBean", DependentBean.class);  
	        dependentBean.write("aaa");  
	    }  
	}  

## 3.3.3  自动装配 ##
自动装配就是指由Spring来自动地注入依赖对象，无需人工参与。

目前Spring3.0支持“no”、“byName ”、“byType”、“constructor”四种自动装配，默认是“no”指不支持自动装配的，其中Spring3.0已不推荐使用之前版本的“autodetect”自动装配，推荐使用Java 5+支持的（@Autowired）注解方式代替；如果想支持“autodetect”自动装配，请将schema改为“spring-beans-2.5.xsd”或去掉。

自动装配的好处是减少构造器注入和setter注入配置，减少配置文件的长度。自动装配通过配置<bean>标签的“autowire”属性来改变自动装配方式。接下来让我们挨着看下配置的含义。

一、default：表示使用默认的自动装配，默认的自动装配需要在<beans>标签中使用default-autowire属性指定，其支持“no”、“byName ”、“byType”、“constructor”四种自动装配，如果需要覆盖默认自动装配，请继续往下看；
 
二、no：意思是不支持自动装配，必须明确指定依赖。
 
三、byName：通过设置Bean定义属性autowire="byName"，意思是根据名字进行自动装配，只能用于setter注入。比如我们有方法“setHelloApi”，则“byName”方式Spring容器将查找名字为helloApi的Bean并注入，如果找不到指定的Bean，将什么也不注入。

例如如下Bean定义配置：

	<bean id="helloApi" class="cn.javass.spring.chapter2.helloworld.HelloImpl"/>  
	<bean id="bean" class="cn.javass.spring.chapter3.bean.HelloApiDecorator"  
	     autowire="byName"/>  

测试代码如下：

	package cn.javass.spring.chapter3;  
	import java.io.IOException;  
	import org.junit.Test;  
	import org.springframework.context.support.ClassPathXmlApplicationContext;  
	import cn.javass.spring.chapter2.helloworld.HelloApi;  
	public class AutowireBeanTest {  
	   @Test  
	    public void testAutowireByName() throws IOException {  
	ClassPathXmlApplicationContext context =  
	new ClassPathXmlApplicationContext("chapter3/autowire-byName.xml");  
	        HelloApi helloApi = context.getBean("bean", HelloApi.class);  
	        helloApi.sayHello();  
	    }  
	}  

是不是不要配置<property>了，如果一个bean有很多setter注入，通过“byName”方式是不是能减少很多<property>配置。此处注意了，在根据名字注入时，将把当前Bean自己排除在外：比如“hello”Bean类定义了“setHello”方法，则hello是不能注入到“setHello”的。

四、“byType”：通过设置Bean定义属性autowire="byType"，意思是指根据类型注入，用于setter注入，比如如果指定自动装配方式为“byType”，而“setHelloApi”方法需要注入HelloApi类型数据，则Spring容器将查找HelloApi类型数据，如果找到一个则注入该Bean，如果找不到将什么也不注入，如果找到多个Bean将优先注入<bean>标签“primary”属性为true的Bean，否则抛出异常来表明有个多个Bean发现但不知道使用哪个。让我们用例子来讲解一下这几种情况吧。

1）根据类型只找到一个Bean，此处注意了，在根据类型注入时，将把当前Bean自己排除在外，即如下配置中helloApi和bean都是HelloApi接口的实现，而“bean”通过类型进行注入“HelloApi”类型数据时自己是排除在外的，配置如下（具体测试请参考AutowireBeanTest.testAutowireByType1方法）：

	<bean class="cn.javass.spring.chapter2.helloworld.HelloImpl"/>  
<bean id="bean" class="cn.javass.spring.chapter3.bean.HelloApiDecorator"  
     autowire="byType"/>  

2）根据类型找到多个Bean时，对于集合类型（如List、Set）将注入所有匹配的候选者，而对于其他类型遇到这种情况可能需要使用“autowire-candidate”属性为false来让指定的Bean放弃作为自动装配的候选者，或使用“primary”属性为true来指定某个Bean为首选Bean：
2.1）通过设置Bean定义的“autowire-candidate”属性为false来把指定Bean后自动装配候选者中移除：

	<bean class="cn.javass.spring.chapter2.helloworld.HelloImpl"/>  
	<!-- 从自动装配候选者中去除 -->  
	<bean class="cn.javass.spring.chapter2.helloworld.HelloImpl"  
	autowire-candidate="false"/>  
	<bean id="bean1" class="cn.javass.spring.chapter3.bean.HelloApiDecorator"  
	     autowire="byType"/>  

2.2）通过设置Bean定义的“primary”属性为true来把指定自动装配时候选者中首选Bean：

	<bean class="cn.javass.spring.chapter2.helloworld.HelloImpl"/>  
	<!-- 自动装配候选者中的首选Bean-->  
	<bean class="cn.javass.spring.chapter2.helloworld.HelloImpl" primary="true"/>  
	<bean id="bean" class="cn.javass.spring.chapter3.bean.HelloApiDecorator"  autowire="byType"/>
具体测试请参考AutowireBeanTest类的testAutowireByType***方法。

五、“constructor”：通过设置Bean定义属性autowire="constructor"，功能和“byType”功能一样，根据类型注入构造器参数，只是用于构造器注入方式，直接看例子吧：

	<bean class="cn.javass.spring.chapter2.helloworld.HelloImpl"/>  
	<!-- 自动装配候选者中的首选Bean-->  
	<bean class="cn.javass.spring.chapter2.helloworld.HelloImpl" primary="true"/>  
	<bean id="bean"  
     class="cn.javass.spring.chapter3.bean.HelloApiDecorator"  
     autowire="constructor"/>

测试代码如下： 

	@Test  
	public void testAutowireByConstructor() throws IOException {  
	ClassPathXmlApplicationContext context =  
	 new ClassPathXmlApplicationContext("chapter3/autowire-byConstructor.xml");  
	HelloApi helloApi = context.getBean("bean", HelloApi.class);  
	helloApi.sayHello();  
	}     

六、autodetect：自动检测是使用“constructor”还是“byType”自动装配方式，已不推荐使用。如果Bean有空构造器那么将采用“byType”自动装配方式，否则使用“constructor”自动装配方式。此处要把3.0的xsd替换为2.5的xsd，否则会报错。

	<?xml version="1.0" encoding="UTF-8"?>  
	<beans  xmlns="http://www.springframework.org/schema/beans"  
	        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  
	        xmlns:context="http://www.springframework.org/schema/context"  
	        xsi:schemaLocation="  
	           http://www.springframework.org/schema/beans  
	           http://www.springframework.org/schema/beans/spring-beans-2.5.xsd  
	           http://www.springframework.org/schema/context  
	           http://www.springframework.org/schema/context/spring-context-2.5.xsd">  
	<bean class="cn.javass.spring.chapter2.helloworld.HelloImpl"/>  
	  <!-- 自动装配候选者中的首选Bean-->  
	  <bean class="cn.javass.spring.chapter2.helloworld.HelloImpl" primary="true"/>  
	  <bean id="bean"  
	        class="cn.javass.spring.chapter3.bean.HelloApiDecorator"  
	        autowire="autodetect"/>  
	</beans>  

可以采用在“<beans>”标签中通过“default-autowire”属性指定全局的自动装配方式，即如果default-autowire=”byName”，将对所有Bean进行根据名字进行自动装配。
不是所有类型都能自动装配：
不能自动装配的数据类型：Object、基本数据类型（Date、CharSequence、Number、URI、URL、Class、int）等；
通过“<beans>”标签default-autowire-candidates属性指定的匹配模式，不匹配的将不能作为自动装配的候选者，例如指定“*Service，*Dao”，将只把匹配这些模式的Bean作为候选者，而不匹配的不会作为候选者；
通过将“<bean>”标签的autowire-candidate属性可被设为false，从而该Bean将不会作为依赖注入的候选者。
数组、集合、字典类型的根据类型自动装配和普通类型的自动装配是有区别的：
数组类型、集合（Set、Collection、List）接口类型：将根据泛型获取匹配的所有候选者并注入到数组或集合中，如“List<HelloApi> list”将选择所有的HelloApi类型Bean并注入到list中，而对于集合的具体类型将只选择一个候选者，“如 ArrayList<HelloApi> list”将选择一个类型为ArrayList的Bean注入，而不是选择所有的HelloApi类型Bean进行注入；
字典（Map）接口类型：同样根据泛型信息注入，键必须为String类型的Bean名字，值根据泛型信息获取，如“Map<String, HelloApi> map” 将选择所有的HelloApi类型Bean并注入到map中，而对于具体字典类型如“HashMap<String, HelloApi> map”将只选择类型为HashMap的Bean注入，而不是选择所有的HelloApi类型Bean进行注入。
 
自动装配我们已经介绍完了，自动装配能带给我们什么好处呢？首先，自动装配确实减少了配置文件的量；其次， “byType”自动装配能在相应的Bean更改了字段类型时自动更新，即修改Bean类不需要修改配置，确实简单了。
 
自动装配也是有缺点的，最重要的缺点就是没有了配置，在查找注入错误时非常麻烦，还有比如基本类型没法完成自动装配，所以可能经常发生一些莫名其妙的错误，在此我推荐大家不要使用该方式，最好是指定明确的注入方式，或者采用最新的Java5+注解注入方式。所以大家在使用自动装配时应该考虑自己负责项目的复杂度来进行衡量是否选择自动装配方式。
自动装配注入方式能和配置注入方式一同工作吗？当然可以，大家只需记住配置注入的数据会覆盖自动装配注入的数据。
大家是否注意到对于采用自动装配方式时如果没找到合适的的Bean时什么也不做，这样在程序中总会莫名其妙的发生一些空指针异常，而且是在程序运行期间才能发现，有没有办法能在提前发现这些错误呢？接下来就让我来看下依赖检查吧。
 
3.3.4  依赖检查
上一节介绍的自动装配，很可能发生没有匹配的Bean进行自动装配，如果此种情况发生，只有在程序运行过程中发生了空指针异常才能发现错误，如果能提前发现该多好啊，这就是依赖检查的作用。
 
依赖检查：用于检查Bean定义的属性都注入数据了，不管是自动装配的还是配置方式注入的都能检查，如果没有注入数据将报错，从而提前发现注入错误，只检查具有setter方法的属性。
Spring3+也不推荐配置方式依赖检查了，建议采用Java5+ @Required注解方式，测试时请将XML schema降低为2.5版本的，和自动装配中“autodetect”配置方式的xsd一样。

	<?xml version="1.0" encoding="UTF-8"?>  
	<beans  xmlns="http://www.springframework.org/schema/beans"  
	        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  
	        xsi:schemaLocation="  
	           http://www.springframework.org/schema/beans  
	           http://www.springframework.org/schema/beans/spring-beans-2.5.xsd  
	</beans>

依赖检查有none、simple、object、all四种方式，接下来让我们详细介绍一下：
 
一、none：默认方式，表示不检查；
 
二、objects：检查除基本类型外的依赖对象，配置方式为：dependency-check="objects"，此处我们为HelloApiDecorator添加一个String类型属性“message”，来测试如果有简单数据类型的属性为null，也不报错；

	<bean id="helloApi" class="cn.javass.spring.chapter2.helloworld.HelloImpl"/>  
	<!-- 注意我们没有注入helloApi，所以测试时会报错 -->  
	<bean id="bean"  
	     class="cn.javass.spring.chapter3.bean.HelloApiDecorator"  
	     dependency-check="objects">  
	<property name="message" value="Haha"/>  
	</bean> 

注意由于我们没有注入bean需要的依赖“helloApi”，所以应该抛出异常UnsatisfiedDependencyException，表示没有发现满足的依赖：
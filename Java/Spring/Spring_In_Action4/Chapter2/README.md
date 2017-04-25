# 第2章 装配Bean #

## 2.1 Spring配置的可选方案 ##

* 在XML中进行显式配置
* 在Java中进行显式配置
* 在XML中进行显式配置

PS：尽可能地使用自动配置的机制。显式配置越少越好，当你必须要显式配置bean的时候，推荐使用类型安全并且比XML更加强大的JavaConfig。

## 2.2 自动化装配bean ##
Spring从两个角度来实现自动化装配：
* 组件扫描（component scanning）：Spring会自动发现应用上下文中所创建的bean。
* 自动装配（autowiring）：Spring自动满足bean之间的依赖

组件扫描和自动装配组合在一起就能发挥出强大的威力，它们能够将你的显式配置降低到最低。

### 2.2.1 创建可被发现的bean ###
@Component 注解表明该类会作为组件类，并告知Spring要为这个类创建bean。没有必要显式配置SgtPeppers bean，因为这个类使用了@Component注解。

@ComponentScan默认会扫描与配置类相同的包

### 2.2.2 为组件扫描的bean命名 ###


### 2.2.3 设置组件扫描的基础包 ###
`@ComponentScan`设置任何属性。

### 2.2.4 通过微bean添加注解实现自动装配 ###
自动装配就是让Spring自动满足bean依赖的一种方法，在满足依赖的过程中，会在Spring应用上下文寻找匹配某个bean需求的其他bean。为了声明要进行自动装配，我们可以借助Spring的`@Autowired`注解。

@Autowired注解不仅能够用在构造器上，还能用在属性的Setter方法上。
	@Autowired
	public void setCompactDisc(CompactDisc cd){
		this.cd = cd;
	}

@Inject注解来源于Java依赖注入规范

### 2.2.5 验证自动装配 ###

## 2.3 通过Java代码代码装配bean ##
在进行显示装配的时候，有两种可选方案：Java和XML。
通常会将JavaConfig放到单独的包中，使他与其他的应用程序逻辑分离开来。
### 2.3.1 创建配置类 ###
创建JavaConfig类的关键在于为其添加@Configiration@Configuration注解表明这个类是配置类。

### 2.3.2 声明简单的bean ###
要在JavaConfig中声明bean，需要编写一个方法，这个方法会创建所需类型的实例，然后给这个方法添加@Bean注解。
	
	@Bean
	public CompactDisc sgtPeppers(){
		return new SgtPeppers();
	}
@Bean注解会告诉Spring这个方法将会返回一个对象，该对象要注册为Spring应用上下文的bean。方法体中包含了最终产生的bean实例的逻辑。

### 2.3.3 借助JavaScript实现注入 ###
Spring中的bean都是单例的，没有必要为第二个CDPlayer bean创建完全相同的SgtPeppers实例。

带有@Bean注解的方法可以采用任何必要的Java功能来产生bean实例。构造器和Setter方法只是@Bean方法的两个简单样例。

## 2.4 通过XML装配bean ##


## 2.6 小结 ##
Spring框架的核心是Spring容器。容器负责管理应用中组件的生命周期，它会创建这些组件并保证它们的依赖能够得到满足，这样的话，组件才能完成预定的任务。

我们看到了Spring中装配bean的三种主要方式：自动化配置、基于Java的显示配置以及基于XML的显示配置。

建议尽可能使用自动化配置，以避免显示配置所带来的维护成本，但是，需要显式配置Spring的话，应该优先选择基于Java的配置，它比基于XML的配置更加强大、类型安全并且易于重构。




# 第2章 装配Bean #

## 2.2 自动化装配bean ##
Spring从两个角度来实现自动化装配：
* 组件扫描（component scanning）：Spring会自动发现应用上下文中所创建的bean。
* 自动装配（autowiring）：Spring自动满足bean之间的依赖

### 2.2.1 创建可被发现的bean ###

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

### 2.2.5 验证自动装配 ###

## 2.3 通过Java代码代码装配bean ##
在进行显示装配的时候，有两种可选方案：Java和XML。


### 2.3.1 创建配置类 ###
创建JavaConfig类的关键在于为其添加@Configiration

### 2.3.2 声明简单的bean ###
要在JavaConfig中声明bean，需要编写一个方法，这个方法会创建所需类型的实例，然后给这个方法添加@Bean注解。
	
	@Bean
	public CompactDisc sgtPeppers(){
		return new SgtPeppers();
	}
@Bean注解会告诉Spring这个方法将会返回一个对象，该对象要注册为Spring应用上下文的bean。方法体中包含了最终产生的bean实例的逻辑。

### 2.3.3 借助JavaScript实现注入 ###

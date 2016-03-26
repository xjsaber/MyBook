#第2章 初识Struts2

##2.1 声明性架构

###2.1.1 两种配置

####1.配置架构

####2.声明应用程序的架构

###2.1.2 声明架构的两种方式
通过基于XML的配置文件或者通过Java注解

1.基于MXL的声明性架构

2.基于Java注解的声明性架构
注解：允许在Java源代码文件中直接添加元数据。Java注解的一个更高级的用途是可以从Java类中读取元数据并且能够使用这些信息。
	
	@Results({
    	@Result(name="input", value="/RegistrationSuccess.jsp")
    	@Result(value="/RegistrationSuccess.jsp")
	})
	public class Login implements Action{
    	public String execute(){
        	//Business logic for login
    	}
	}

3.应该使用哪种方式

2.1.3 只能默认值
struts-default.xml中声明了很多这样的组件

##2.2 简单的HelloWorld示例


###2.2.1 部署示例应用程序
部署应用程序，需要一个Servle容器。

###2.2.2 搜索HelloWorld应用程序


##2.3 使用注解的HelloWorld

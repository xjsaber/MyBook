# 第17章 创建RESTful和SOAP Web服务 #

spring-aop

## 17.1 了解Web服务 ##

### 17.1.1 最初的SOAP ###

SOAP源自于简单对象访问协议（Simple Object Access Protocol），他是一种使用XML消息形式结构化数据再机器与机器之间进行交互协议。

### 17.1.2 RESTful Web提供了一种更简单的方式 ###


## 17.2 在SpringMVC中配置RESTful Web服务 ##

### 17.2.1 使用原型注解分离控制器 ###

@Controller注解的目的有两个，作为一个@Component，它负责将控制器标记位受Spring管理的bean（适用于实例化和依赖注入）。

负责让Spring上下文在该类中搜索@RequestMapping。一个标记了@RequestMapping的bean，如果并未标记@Controller的话，那么它也无法对请求作出响应。因此，如同Web控制器一样，REST控制器也必须标记上@Controller

标注一个自定义注解

	@Target({ElementType.TYPE})
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@Controller
	public @interface WebController
	{
		String value() defaule "";
	}

	@Target({ElementType.TYPE})
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@Controller
	public @interface RestEndpoint
	{
		String value() defaule "";
	}

@WebController表示该控制器将用于处理传统的Web请求，而RestEndpoint表示了一个用于处理RESTful Web服务请求的终端。在这里，唯一的value特性的目的与其他原型注解中的value特性相同：提供了一种指定bean名称的方式，该名称将覆盖默认的bean名称模式。

### 17.2.2 创建单独的Web和REST应用上下文 ###

ServletContextConfiguration类在使用组件扫描查找@Controller时，去除目的的二义性，将重命名位WebServletContextConfiguration。

	@Configuration
	@EnableWebMvc
	@ComponentScan(basePackages = "xxxx",
				useDefaultFilters = false,
				includeFilters = @ComponentScan.Filter(WebController.class))
	public class WebServletContextConfiguration extends WebMvcConfigurerAdapter{}

## 17.3 测试Web服务终端 ##

## 17.4 使用Spring Web Service ##


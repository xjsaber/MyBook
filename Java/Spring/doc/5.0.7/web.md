# Web on Servlet Stack #

## ch1 Spring Web Mvc ##

### 1.1 Introduction ###

spring-webflux WebFlux

### 1.2 DispatcherServlet ###

central Servlet

using Java configuration or in web.xml

#### 1.2.1. Context Hierarchy ####


#### 1.2.2. Special Bean Types ####

特殊Bean类型



#### 1.2.3 Web Mvc Config ####



#### 1.2.4 Servlet Config ####

DispatcherServlet

In a Servlet 3.0+ environment, you have the option of configuring the Servlet container programmatically as an alternative or in combination with a web.xml file. Below is an example of registering a DispatcherServlet。

#### 1.2.5 Processing #### 

### 1.3 Filters ###

#### 1.3.1 HTTP PUT Form ####


### 1.4 Annotated Controllers ###

@Controller和@RestController

	@GetMapping("/index")
	public String handler(Model model){
	    model.addAttribute("message", "Hello World!");
	    return "index";
	}

#### 1.4.1 Declaration ####

Controller 定义在Servlet的WebApplicationContext

	@Configuration
	@ComponentScan("org.example.web")
	public class WebConfig {
	
	    // ...
	}



	<?xml version="1.0" encoding="UTF-8"?>
	<beans xmlns="http://www.springframework.org/schema/beans"
	    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	    xmlns:p="http://www.springframework.org/schema/p"
	    xmlns:context="http://www.springframework.org/schema/context"
	    xsi:schemaLocation="
	        http://www.springframework.org/schema/beans
	        http://www.springframework.org/schema/beans/spring-beans.xsd
	        http://www.springframework.org/schema/context
	        http://www.springframework.org/schema/context/spring-context.xsd">
	
	    <context:component-scan base-package="org.example.web"/>
	
	    <!-- ... -->
	
	</beans>

@RestController 相当于 @Controller和@ResponseBody结合

**AOP proxies**

In some cases a controller may need to be decorated with an AOP proxy at runtime。

@Transactional 

#### 1.4.2 Request Mapping ####

@RequestMapping 默认接受所有HTTP

* @GetMapping
* @PostMapping
* @PutMapping
* @DeleteMapping
* @PatchMapping

The above are Custom Annotations that are provided out of the box because arguably most controller methods should be mapped to a specific HTTP method vs using @RequestMapping which by default matches to all HTTP methods. 

**URI patterns**

* ? matches one character
* * matches zero or more characters within a path segment
* ** match zero or more path segments

**Custom Annotations**

@GetMapping, @PostMapping, @PutMapping, @DeleteMapping, and @PatchMapping。

Spring MVC also supports custom request mapping attributes with custom request matching logic. This is a more advanced option that requires sub-classing `RequestMappingHandlerMapping` and overriding the `getCustomMethodCondition` method where you can check the custom attribute and return your own `RequestCondition`.

**Explicit Registrations**

Handler methods can be registered programmatically which can be used for dynamic registrations, or for advanced cases such as different instances of the same handler under different URLs.

	@Configuration
	public class MyConfig {
	
	    @Autowired
	    public void setHandlerMapping(RequestMappingHandlerMapping mapping, UserHandler handler) ①
	            throws NoSuchMethodException {
	
	        RequestMappingInfo info = RequestMappingInfo
	                .paths("/user/{id}").methods(RequestMethod.GET).build(); ②
	
	        Method method = UserHandler.class.getMethod("getUser", Long.class); ③
	
	        mapping.registerMapping(info, handler, method); ④
	    }
	}

1. Inject target handler(s) and the handler mapping for controllers.
2. Prepare the request mapping meta data.
3. Get the handler method.
4. Add the registration.

#### 1.4.3 Header Medhods ####

@RequestMapping

Method Arguments

JDK 8’s java.util.Optional is supported as a method argument in combination with annotations that have a required attribute — e.g. @RequestParam, @RequestHeader, etc, and is equivalent to required=false.

### 1.8 Web Security ###



### 1.9 HTTP Caching ###


### 1.10 View Technologies ###

视图
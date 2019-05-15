# Base 2.1.4.RELEASE #

## Part I. Spring Boot Documentation ##

## Part II. Getting Started ##

### 8. Introducing Spring Boot ###


### 11. Developing Your First Spring Boot Application ###

#### 11.1 Creating the POM ####

	<?xml version="1.0" encoding="UTF-8"?>
	<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
		<modelVersion>4.0.0</modelVersion>
	
		<groupId>com.example</groupId>
		<artifactId>myproject</artifactId>
		<version>0.0.1-SNAPSHOT</version>
	
		<parent>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-parent</artifactId>
			<version>2.1.4.RELEASE</version>
		</parent>
	
		<!-- Additional lines to be added here... -->
	
	</project>

----

	mvn package

#### 11.2 Adding Classpath Dependencies ####

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
	</dependencies

----

	mvn dependency:tree

#### 11.3 Writing the Code ####

	import org.springframework.boot.*;
	import org.springframework.boot.autoconfigure.*;
	import org.springframework.web.bind.annotation.*;
	
	@RestController
	@EnableAutoConfiguration
	public class Example {
	
		@RequestMapping("/")
		String home() {
			return "Hello World!";
		}
	
		public static void main(String[] args) {
			SpringApplication.run(Example.class, args);
		}
	
	}

**11.3.1 The @RestController and @RequestMapping Annotations**

stereotype annotation

@RestController、@Controller

**11.3.2 The @EnableAutoConfiguration Annotation**



**11.3.3 The “main” Method**


#### 11.4 Running the Example ####

#### 11.5 Creating an Executable Jar ####

### 12. What to Read Next ###

## Part III. Using Spring Boot ##

### 13. Build Systems ###

#### 13.1 Dependency Management ####



#### 13.2 Maven ####

* Java 1.8 as the default compiler level.
* UTF-8 source encoding.
* A Dependency Management section, inherited from the spring-boot-dependencies pom, that manages the versions of common dependencies. This dependency management lets you omit <version> tags for those dependencies when used in your own pom.
* An execution of the repackage goal with a repackage execution id.
* Sensible resource filtering.
* Sensible plugin configuration (exec plugin, Git commit ID, and shade).
* Sensible resource filtering for application.properties and application.yml including profile-specific files (for example, application-dev.properties and application-dev.yml)

**13.2.1 Inheriting the Starter Parent**

	<!-- Inherit defaults from Spring Boot -->
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.1.4.RELEASE</version>
	</parent>

**13.2.2 Using Spring Boot without the Parent POM**

**13.2.3 Using the Spring Boot Maven Plugin**
	
	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

#### 13.3 Gradle ####

#### 13.4 Ant ####

#### 13.5 Starters ####

### 14. Structuring Your Code ###

#### 14.1 Using the “default” Package ####

`@ComponentScan`, `@EntityScan`, or `@SpringBootApplication` annotations

#### 14.2 Locating the Main Application Class ####

### 15. Configuration Classes ###

`SpringApplication` with XML sources, we generally recommend that your primary source be a single `@Configuration` class. 

#### 15.1 Importing Additional Configuration Classes ####

The `@Import` annotation can be used to import additional configuration classes. Alternatively, you can use `@ComponentScan` to automatically pick up all Spring components, including `@Configuration` classes.

#### 15.2 Importing XML Configuration ####

recommend that you still start with a `@Configuration` class

use an @ImportResource annotation to load XML configuration files.

### 16. Auto-configuration ###

`@EnableAutoConfiguration` or `@SpringBootApplication`

#### 16.1 Gradually Replacing Auto-configuration ####



#### 16.2 Disabling Specific Auto-configuration Classes ####

	@Configuration
	@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
	public class MyConfiguration {
	}

If the class is not on the classpath, you can use the excludeName attribute of the annotation and specify the fully qualified name instead.Finally, you can also control the list of auto-configuration classes to exclude by using the spring.autoconfigure.exclude property.

### 17. Spring Beans and Dependency Injection ###

If you structure your code as suggested above (locating your application class in a root package), you can add `@ComponentScan` without any arguments. All of your application components (`@Component`, `@Service`, `@Repository`, `@Controller` etc.) are automatically registered as Spring Beans.

	package com.example.service;
	
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;
	
	@Service
	public class DatabaseAccountService implements AccountService {
	
		private final RiskAssessor riskAssessor;
	
		@Autowired
		public DatabaseAccountService(RiskAssessor riskAssessor) {
			this.riskAssessor = riskAssessor;
		}
	
		// ...
	
	}

### 18. Using the @SpringBootApplication Annotation ###

`@EnableAutoConfiguration`: enable Spring Boot’s auto-configuration mechanism
`@ComponentScan`: enable @Component scan on the package where the application is located (see the best practices)
`@Configuration`: allow to register extra beans in the context or import additional configuration classes

	package com.example.myapplication;
	
	import org.springframework.boot.SpringApplication;
	import org.springframework.boot.autoconfigure.SpringBootApplication;
	
	@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
	public class Application {
	
		public static void main(String[] args) {
			SpringApplication.run(Application.class, args);
		}
	
	}

----

	@SpringBootApplication also provides aliases to customize the attributes of @EnableAutoConfiguration and @ComponentScan.

### 19. Running Your Application ###

#### 19.1 Running from an IDE ####

#### 19.2 Running as a Packaged Application ####

#### 19.3 Using the Maven Plugin ####

#### 19.4 Using the Gradle Plugin ####

#### 19.5 Hot Swapping ####

### 20. Developer Tools ###

#### 20.4 Global Settings ####

#### 20.5 Remote Applications ####

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<excludeDevtools>false</excludeDevtools>
				</configuration>
			</plugin>
		</plugins>
	</build>

### 21. Packaging Your Application for Production ###

### 22. What to Read Next ###

## Part IV. Spring Boot features ##

### 23. SpringApplication ###

#### 23.1 Startup Failure ####

#### 23.2 Customizing the Banner ####

|Variable|Description|
|--|--|
|${application.version}

#### 23.3 Customizing SpringApplication ####

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(MySpringConfiguration.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

#### 23.4 Fluent Builder API ####

	new SpringApplicationBuilder()
			.sources(Parent.class)
			.child(Application.class)
			.bannerMode(Banner.Mode.OFF)
			.run(args);

#### 23.5 Application Events and Listeners ####

#### 23.6 Web Environment ####

#### 23.7 Accessing Application Arguments ####

#### 23.8 Using the ApplicationRunner or CommandLineRunner ####

#### 23.9 Application Exit ####

### 24. Externalized Configuration ###

#### 24.1 Configuring Random Values ####

#### 24.2 Accessing Command Line Properties ####

#### 24.3 Application Property Files ####

`SpringApplication` loads properties from `application.properties` files in the following locations and adds them to the Spring `Environment`

1. A `/config` subdirectory of the current directory
2. The current directory
3. A classpath `/config` package
4. The classpath root

#### 24.4 Profile-specific Properties ####

#### 24.5 Placeholders in Properties ####

app.name=MyApp
app.description=${app.name} is a Spring Boot application

#### 24.6 Encrypting Properties ####

#### 24.7 Using YAML Instead of Properties ####

**24.7.4 YAML Shortcomings【缺陷】**




#### 24.8 Type-safe Configuration Properties ####

### 26 Logging ###

Default configurations are provided for Java Util *Logging*, *Log4J2*, and *Logback*.

#### 26.1 Log Format ####

	2019-05-14 15:09:21.043  INFO 21988 --- [           main] c.x.learn.spring.base.SimpleApplication  : Starting SimpleApplication on XJSBAER-PLACE with PID 21988 (E:\WorkPlace\MyBook\Java\Spring\SpringBoot\base\target\classes started by xjsaber in E:\WorkPlace\MyBook\Java\Spring\SpringBoot\base)
	2019-05-14 15:09:21.046  INFO 21988 --- [           main] c.x.learn.spring.base.SimpleApplication  : No active profile set, falling back to default profiles: default
	2019-05-14 15:09:21.207  WARN 21988 --- [           main] ConfigServletWebServerApplicationContext : Exception encountered during context initialization - cancelling refresh attempt: org.springframework.context.ApplicationContextException: Unable to start web server; nested exception is org.springframework.context.ApplicationContextException: Unable to start ServletWebServerApplicationContext due to missing ServletWebServerFactory bean.
	2019-05-14 15:09:21.453 ERROR 21988 --- [           main] o.s.boot.SpringApplication               : Application run failed

* Date and Time: Millisecond precision and easily sortable.
* Log Level: ERROR, WARN, INFO, DEBUG, or TRACE.
* Process ID.
* A --- separator to distinguish the start of actual log messages.
* Thread name: Enclosed in square brackets (may be truncated for console output).
* Logger name: This is usually the source class name (often abbreviated).
* The log message.

#### 26.2 Console Output ####

**26.2.1 Color-coded Output**

	spring.output.ansi.enable

|Level|Color|
|--|--|
|FATAL|Red|
|ERROR|Red|
|WARN|Yellow|
|INFO|Green|
|DEBUG|Green|
|TRACE|Green|

#### 26.3 File Output ####

#### 26.4 Log Levels ####

	logging.level.<logger-name>=<level> where level is one of TRACE, DEBUG, INFO, WARN, ERROR, FATAL, or OFF. 

The `root` logger can be configured by using `logging.level.root`.

	logging.level.root=WARN
	logging.level.org.springframework.web=DEBUG
	logging.level.org.hibernate=ERROR

#### 26.5 Log Groups ####

	logging.group.tomcat=org.apache.catalina, org.apache.coyote, org.apache.tomcat

	logging.level.tomcat=TRACE

|Name|Loggers|
|--|--|
|web|`org.springframework.core.codec`, `org.springframework.http`, `org.springframework.web`|
|sql|`org.springframework.jdbc.core`, `org.hibernate.SQL`|

#### 26.6 Custom Log Configuration ####

|Logging System|Customization|
|--|--|
|Logback|	
`logback-spring.xml`, `logback-spring.groovy`, `logback.xml`, or `logback.groovy`|
|Log4j2|`log4j2-spring.xml` or `log4j2.xml`|
|JDK (Java Util Logging)|`logging.properties`|

#### 26.7 Logback Extensions ####

### 27. Internationalization ###

### 28. JSON ###

#### 28.1 Jackson ####

	spring-boot-starter-json

#### 28.2 Gson ####

#### 28.3 JSON-B ####

### 29. Developing Web Applications ###

	spring-boot-starter-web

#### 29.1 The “Spring Web MVC Framework” ####

Spring MVC lets you create special `@Controller` or `@RestController` beans to handle incoming HTTP requests.

Methods in your controller are mapped to HTTP by using `@RequestMapping` annotations.

	@RestController
	@RequestMapping(value="/users")
	public class MyRestController {
	
		@RequestMapping(value="/{user}", method=RequestMethod.GET)
		public User getUser(@PathVariable Long user) {
			// ...
		}
	
		@RequestMapping(value="/{user}/customers", method=RequestMethod.GET)
		List<Customer> getUserCustomers(@PathVariable Long user) {
			// ...
		}
	
		@RequestMapping(value="/{user}", method=RequestMethod.DELETE)
		public User deleteUser(@PathVariable Long user) {
			// ...
		}
	
	}

**29.1.1 Spring MVC Auto-configuration**

* Inclusion of `ContentNegotiatingViewResolver` and `BeanNameViewResolver` beans.
* Support for serving static resources, including support for WebJars (covered later in this document)).
* Automatic registration of `Converter`, `GenericConverter`, and `Formatter` beans.

**29.1.2 HttpMessageConverters**

**29.1.3 Custom JSON Serializers and Deserializers**

`@JsonComponent` annotation directly on `JsonSerializer` or `JsonDeserializer` implementations. 

	import java.io.*;
	import com.fasterxml.jackson.core.*;
	import com.fasterxml.jackson.databind.*;
	import org.springframework.boot.jackson.*;
	
	@JsonComponent
	public class Example {
	
		public static class Serializer extends JsonSerializer<SomeObject> {
			// ...
		}
	
		public static class Deserializer extends JsonDeserializer<SomeObject> {
			// ...
		}
	
	}

**29.1.4 MessageCodesResolver**

**29.1.5 Static Content**

**29.1.6 Welcome Page**

**29.1.7 Custom Favicon**

	favicon.ico

**29.1.8 Path Matching and Content Negotiation**

29.1.9 ConfigurableWebBindingInitializer


29.1.10 Template Engines

29.1.11 Error Handling

#### 29.2 The “Spring WebFlux Framework” ####

#### 29.3 JAX-RS and Jersey ####

	spring-boot-starter-jersey

----

	@Component
	public class JerseyConfig extends ResourceConfig {
	
		public JerseyConfig() {
			register(Endpoint.class);
		}
	
	}


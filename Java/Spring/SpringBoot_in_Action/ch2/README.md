# ch2 Developing your first Spring Boot application #

## 2.1 Putting Spring Boot to work ##

### 2.1.1 Examining a newly initialized Spring Boot project ###

* build.gradle—The Gradle build specification
* ReadingListApplication.java—The application’s bootstrap class and primary
Spring configuration class
* application.properties—A place to configure application and Spring Boot
properties
* ReadingListApplicationTests.java—A basic integration test class

**ReadingListApplication**: configuration and bootstrapping配置和引导程序

* Spring’s  @Configuration —Designates a class as a configuration class using
Spring’s Java-based configuration. Although we won’t be writing a lot of config-
uration in this book, we’ll favor Java-based configuration over  XML configura-
tion when we do.
* Spring’s  @ComponentScan —Enables component-scanning so that the web con-
troller classes and other components you write will be automatically discovered
and registered as beans in the Spring application context. A little later in this
chapter, we’ll write a simple Spring  MVC controller that will be annotated with
@Controller so that component-scanning can find it.
* Spring Boot’s  @EnableAutoConfiguration —This humble little annotation might
as well be named  @Abracadabra because it’s the one line of configuration that
enables the magic of Spring Boot auto-configuration. This one line keeps you
from having to write the pages of configuration that would be required otherwise.

#### TESTING SPRING BOOT APPLICATIONS ####

	@RunWith(SpringRunner.class)
	@SpringBootTest

#### CONFIGURING APPLICATION PROPERTIES ####

	server.port=8000

### 2.1.2 Dissecting a Spring Boot project build  ###

Gradle or Maven

## 2.2 Using starter dependencies ##

	compile("org.springframework:spring-web:4.1.6.RELEASE")
	compile("org.thymeleaf:thymeleaf-spring4:2.1.4.RELEASE")
	compile("org.springframework.data:spring-data-jpa:1.8.0.RELEASE")
	compile("org.hibernate:hibernate-entitymanager:jar:4.3.8.Final")
	compile("com.h2database:h2:1.4.187")

### 2.2.1 Specifying facet-based dependencies ###

	dependencies {
		compile "org.springframework.boot:spring-boot-starter-web"
		compile "org.springframework.boot:spring-boot-starter-thymeleaf"
		compile "org.springframework.boot:spring-boot-starter-data-jpa"
		compile "com.h2database:h2"
		testCompile("org.springframework.boot:spring-boot-starter-test")
	}

### 2.2.2 Overriding starter transitive dependencies ###

## 2.3 Using automatic configuration ##

* Is Spring’s  JdbcTemplate available on the classpath? If so and if there is a  Data-
Source bean, then auto-configure a  JdbcTemplate bean.
* Is Thymeleaf on the classpath? If so, then configure a Thymeleaf template
resolver, view resolver, and template engine.
* Is Spring Security on the classpath? If so, then configure a very basic web security setup.

### 2.3.1 Focusing on application functionality ###

#### DEFINING THE DOMAIN ####

jpa

#### DEFINING THE REPOSITORY INTERFACE ####

define the repository through which the  ReadingList objects will
be persisted to the database.Because we’re using Spring Data  JPA , that task is a simple matter of creating an interface that extends Spring Data  JPA ’s  JpaRepository interface

#### CREATING THE WEB INTERFACE ####

@Controller in order to be picked up by
component-scanning and automatically be registered as a bean in the Spring application context。
@RequestMapping to map all of its handler methods to a base  URL path of “/”

using Thymeleaf to define the application views

### 2.3.2 Running the application ###

### 2.3.3 What just happened? ###

Configuration is a central element of the Spring Framework, and there must be something that tells Spring how to run your application.

It’s easy enough to write your own conditions in Spring. All you have to do is
implement the  Condition interface and override its  matches() method. 

	@Conditional(JdbcTemplateCondition.class)
		public MyService myService() {
		...
	}
the MyService bean will only be created if the  JdbcTemplateCondition passes.

## 2.4 Summary ##
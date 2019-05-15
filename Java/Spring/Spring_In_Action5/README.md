# Spring in Action5 #

Part1. FOUNDATIONAL SPRING

# ch1 Getting started with Spring #

## 1.1 What is Spring？ ##

assure

## 1.2 ##

### 1.2.2 Examining the Spring project structure ###



### 1.4.5 Spring Integration and Spring Batch ###

### 1.4.6 Spring Cloud ###

## Summary ##



# ch2 Developing web applications #

## 2.1 Displaying information ##

tacos

### 2.1.1 Establishing the domain ###

	<depen

### 2.1.2 Creating a controller class ###

* Handle HTTP GET requests where the request path is /design
* Build a list of ingredients
* Hand the request and the ingredient data off to a view template to rendered as HTML and sent to the requesting web browser。

@Slf4j,is a Lombok-provided annotation that，at runtime，will automatically generate an SLF4J(Simple Logging Facade for Java)Logger in the classs.This modest annotation has the same effect as if you were to explicitly add the following lines within the class:

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DesignTacoController.class);

The next annotation applied to DesignTacoController is @Controller . This
annotation serves to identify this class as a controller and to mark it as a candidate for
component scanning, so that Spring will discover it and automatically create an
instance of DesignTacoController as a bean in the Spring application context.

DesignTacoController is also annotated with @RequestMapping . The @Request-
Mapping annotation, when applied at the class level, specifies the kind of requests that
this controller handles. In this case, it specifies that DesignTacoController will han-
dle requests whose path begins with /design.

#### HANDLING A GET REQUEST ####

|Annotation|Description|
|--|--|
|@RequestMapping|General-purpose request handling|
|@GetMapping|Handles HTTP GET requests|
|@PostMapping|Handles HTTP POST requests|
|@PutMapping|Handles HTTP PUT requests|
|@DeleteMapping|Handles HTTP DELETE requests|
|@PathchMapping|Handles HTTP PATCH requests|

### 2.1.3 Designing the view ###

	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-thymeleaf</artifactId>
	</dependency>

## 2.2 Processing form submission ##


## 2.3 Validating form input ##

### 2.3.1 Declaring validation rules ###

### 2.3.2 Performing validation at form binding ###

### 2.3.3 Displaying validation errors ###

2.4 Working with view controllers

# ch3 Working with data #

# ch4 Securing Spring #

# ch5 Working with configuration properies #

Part2. INTEGERATED SPRING

# ch6 Createing REST services #

# ch7 Consuming REST #

# ch8 Sending messages asynchronously #

# ch9 Integrating Spring #

Part3 REACTIVE SPRING

# ch10 Introducing Reactor #

# ch11 Developing reactive APIs #

# ch12 Persisting data reactively #

Part4 CLOUD-NATIVE SPRING

# ch13 Discovering services #

# ch14 Managing configuration #

# ch15 Handling failure and latency #

Part5 DEPLOYED SPRING

# ch16 Working with Spring Boot Actuator #

# ch17 Administering Spring #

# ch18 Monitoring Spring with JMX #

# ch19 Deloying Spring #



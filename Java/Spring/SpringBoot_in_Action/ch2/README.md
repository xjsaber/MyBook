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

### 2.1.2 Dissecting a Spring Boot project build  ###

## 2.2 Using starter dependencies ##

	compile("org.springframework:spring-web:4.1.6.RELEASE")
	compile("org.thymeleaf:thymeleaf-spring4:2.1.4.RELEASE")
	compile("org.springframework.data:spring-data-jpa:1.8.0.RELEASE")
	compile("org.hibernate:hibernate-entitymanager:jar:4.3.8.Final")
	compile("com.h2database:h2:1.4.187")

### 2.2.1 Specifying facet-based dependencies ###

### 2.2.2 Overriding starter transitive dependencies ###

## 2.3 Using automatic configuration ##

### 2.3.1 Focusing on application functionality ###

## 2.4 Summary ##


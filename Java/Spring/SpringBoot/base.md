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


# Customizing configuration #

## 3.1 Overriding Spring Boot auto-configuration ##

### 3.1.1 Securing the application ###

	compile("org.springframework.boot:spring-boot-starter-security")

### 3.1.2 Creating a custom security configuration ###

In the case of Spring Security, this means writing a configuration class that extends WebSecurityConfigurerAdapter.

SecurityConfig is a very basic Spring Security configuration. 

### 3.1.3 Taking another peek under the covers of auto-configuration ###

Spring Boot auto-configuration comes with several
configuration classes, any of which can be applied in your application.

@ConditionalOnMissingBean annotation described in
table 2.1 is what makes it possible to override auto-configuration.

SpringBootWebSecurityConfiguration is annotated with a few
conditional annotations

@Enable-WebSecurity annotation must be available on the classpath

## 3.2 Externalizing configuration with properties ##


### 3.2.1 Fine-tuning auto-configuration ###


#### DISABLING TEMPLATE CACHING ####
If you’ve been tinkering around much with the reading-list application, you may have
noticed that changes to any of the Thymeleaf templates aren’t applied unless you
restart the application. That’s because Thymeleaf templates are cached by default.

application.yml

	spring:
		thymeleaf:
			cache: false

#### CONFIGURING THE EMBEDDED SERVER ####

	server:
		port: 8443
		ssl:
			key-store: file:///path/to/mykeys.jks
			key-store-password: letmein
			key-password: letmein

server.ssl.key-store-password and server.ssl.key-password properties

#### CONFIGURING LOGGING ####

	#logging配置
	logging:
	  level:
	    root: WARN
	    org:
	      springframework:
	        security: DEBUG
	  path: /var/logs/
	  file: BookWorm.log

#### CONFIGURING A DATA SOURCE ####

	spring:
		datasource:
			url: jdbc:mysql://localhost/readinglist
			username: dbuser
			password: dbpass
			driver-class-name: com.mysql.jdbc.Driver

### 3.2.2 Externally configuring application beans ###

## 3.3 Customizing application error pages ##

* Any bean that implements Spring’s  View interface and has a bean  ID of “error”
(resolved by Spring’s  BeanNameViewResolver )
* A Thymeleaf template named “error.html” if Thymeleaf is configured
* A FreeMarker template named “error.ftl” if FreeMarker is configured
* A Velocity template named “error.vm” if Velocity is configured
* A JSP template named “error.jsp” if using JSP views
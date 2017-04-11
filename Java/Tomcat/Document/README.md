# Tomcat8 官方文档 #

## 1. Introduction ##

* /bin - Startup, shutdown, and other scripts. The *.sh files (for Unix systems) are functional duplicates of the *.bat files (for Windows systems). Since the Win32 command-line lacks certain functionality, there are some additional files in here.
* /conf - Configuration files and related DTDs. The most important file in here is server.xml. It is the main configuration file for the container.
* /logs - Log files are here by default.
* /webapps - This is where your webapps go.

## 2 Tomcat Setup ##

#### Windows ####

* Installation as a service: Tomcat will be installed as a Windows service no matter what setting is selected. Using the checkbox on the component page sets the service as "auto" startup, so that Tomcat is automatically started when Windows starts. For optimal security, the service should be run as a separate user, with reduced permissions (see the Windows Services administration tool and its documentation).
* Java location: The installer will provide a default JRE to use to run the service. The installer uses the registry to determine the base path of a Java 7 or later JRE, including the JRE installed as part of the full JDK. When running on a 64-bit operating system, the installer will first look for a 64-bit JRE and only look for a 32-bit JRE if a 64-bit JRE is not found. It is not mandatory to use the default JRE detected by the installer. Any installed Java 7 or later JRE (32-bit or 64-bit) may be used.
* Tray icon: When Tomcat is run as a service, there will not be any tray icon present when Tomcat is running. Note that when choosing to run Tomcat at the end of installation, the tray icon will be used even if Tomcat was installed as a service.
* Refer to the Windows Service HOW-TO for information on how to manage Tomcat as a Windows service.

## 3. First web application ##


### Source Organization ###

* **docs/** - Documentation for your application, in whatever format your development team is using.
* **src/** - Java source files that generate the servlets, beans, and other Java classes that are unique to your application. If your source code is organized in packages (highly recommended), the package hierarchy should be reflected as a directory structure underneath this directory.(JavaBeans事实上有三层含义。首先，JavaBeans是一种规范，一种在Java（包括JSP）中使用可重复使用的Java组件的技术规范,也可以说成我们常说的接口。其次，JavaBeans是一个Java的类，一般来说，这样的Java类将对应于一个独立的 .java文件 ，在绝大多数情况下，这应该是一个public类型的类。最后，当JavaBeans这样的一个Java类在我们的具体的Java程序中被实例之后，这就是我们面向对象的对象，我们有时也会将这样的一个JavaBeans的实例称之为JavaBeans。总之，就是Java中的接口、类和对象。)
* **web/** - The static content of your web site (HTML pages, JSP pages, JavaScript files, CSS stylesheet files, and images) that will be accessible to application clients. This directory will be the document root of your web application, and any subdirectory structure found here will be reflected in the request URIs required to access those files.
* **web/WEB-INF/** - The special configuration files required for your application, including the web application deployment descriptor (web.xml, defined in the Servlet Specification), tag library descriptors for custom tag libraries you have created, and other resource files you wish to include within your web application. Even though this directory appears to be a subdirectory of your document root, the Servlet Specification prohibits serving the contents of this directory (or any file it contains) directly to a client request. Therefore, this is a good place to store configuration information that is sensitive (such as database connection usernames and passwords), but is required for your application to operate successfully.
 
During the development process, two additional directories will be created on a temporary basis:
* **build/** - When you execute a default build (ant), this directory will contain an exact image of the files in the web application archive for this application. Tomcat allows you to deploy an application in an unpacked directory like this, either by copying it to the $CATALINA_BASE/webapps directory, or by installing it via the "Manager" web application. The latter approach is very useful during development, and will be illustrated below.
* **dist/** - When you execute the ant dist target, this directory will be created. It will create an exact image of the binary distribution for your web application, including an license information, documentation, and README files that you have prepared. 

## 4. Deployer ##

## 5. Manager ##

## 6. Realms and Access Control ##

## 7. Security Manager ##

 







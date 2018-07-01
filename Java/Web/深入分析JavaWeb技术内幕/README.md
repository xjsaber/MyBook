# 深入分析Java Web技术内幕 #

## 第9章 Servlet工作原理解析 ##

### 9.1 从Servlet容器说起 ###




## 第14章 Spring MVC 工作机制和设计模式 ##

### 14.1 Spring MVC的总体设计 ###

DispathcerServlet类继承了HttpServlet

HttpServlet初始化调用HttpServletBean的init方法，在该方法的作用是获取Servlet的init方法，在该方法的作用是获取Servlet中的init参数，并创建一个BeanWrapper对象，然后由子类处真正执行BeanWrapper的初始化工作。

Spring容器的创建是在FrameworkServlet的initServletBean()方法中完成的。这个方法会创建WebApplicationContext对象，并调用其refresh()方法来完成配置文件的加载，撇脂文件的加载通用是先查找Servlet的init-param参数中设置的路径。如果没有，会根据namespace+Servlet的名称来查找XML文件。

Spring容器加载时会调用DispathcerServlet的initStrategies方法来完成DispatchServlet中定义的初始化工作。initStrategies方法中会初始化Spring MVC框架需要的8个组件，这个8个组件对应的8个Bean对象都保存在DispatcherServlet类中。

1. HttpServletBean.init() => initBeanWrapper => BeanWrapper
2. HttpServletBean.initServletBean() => FrameworkServlet.HttpServletBean()
3. FrameworkServlet.initWebApplicationContext() => WebApplicationContext
4. FrameworkServlet.onRefresh() => DispatcherServlet.onRefresh()
5. DispatcherServlet.initStrategies()

**initStrategies执行方法如下：**

1. initMultipartResolver：初始化MultipartResolver，用于处理文件上传服务，如果有文件上传，那么会将当前的HttpServletRequest包装成DefaultMultipartHttpServletRequest，并且将每个上传的内容封装成CommonsMultipartFile对象。
2. initLocaleResolver：用于处理应用的国际化问题，通过解析请求的Local和设置响应的Locale来控制应用中的字符编码问题。
3. initThemeResolver：用于定义一个主题，例如，可以根据用户的喜好来设置用户访问的页面的样式，可以将这个样式作为一个ThemeName保存，用于请求的Cookie中或者保存在服务端的Session中，以后每次请求根据这个ThemeName来返回特定的内容。
4. initHandlerMappings：用于定义用户设置的请求映射关系，例如，前面实例的SimpleUrlHandlerMapping把用于用户请求的URL映射成一个个Handler实例。HandlerMapping必须定义，如果没有定义，将获取DispatcherServlet.properties文件中默认的两个HandlerMapping，分别是BeanNameUrlHandlerMapping和DefaultAnnotationHandlerMapping
5. initHandlerAdapters：用于根据Handler的类型定义不同的处理规则，例如，定义SimpleControllerHanderAdapter处理所有Controller的实例对象，在HandlerMapping中将URL映射成一个COntroller实例，那么Spring MVC在解析时SimpleControllerHandlerAdapter就会调用这个Controller实例。同样HandlerAdapters也必须定义，如果没有定义，将获取DispathcherServlet.properties文件中默认的四个HandlerAdapters，分别是HttpRequestHandlerAdapter、SimpleControllerHandlerAdapter、ThrowawayControllerHandlerAdapter和AnnotationMethodHandlerAdapter
6. initHandlerExceptionResolvers：当Handler处理出错时，通过这个Handler来统一做处理，默认的实现类是SimpleMappingExceptionResolver，将错误日志记录在log文件中，并且转到默认的错误页面
7. initRequestToViewNameTranslator：将指定的ViewName按照定义的RequestToViewNameTranslator替换成想要的格式，如加上前缀或者后缀等。
8. initViewResolvers:用于将View解析成页面，在ViewResolvers中可以设置多个解析策略，如可以根据JSP来解析，或者按照Velocity模板解析。默认的解析策略是InternalResourceViewResolver，按照JSP页面来解析

### 14.2 Control设计 ###

Control主要是由HandlerMapping和HandlerAdapter两个逐渐

Handler负责映射用户的URL和对应的处理类

#### 14.2.1 HandlerMapping初始化 ####

将URL和Handler的对应关系保存在handlerMap集合中。



#### 14.2.2 HandlerAdapter初始化 ####

最常见的办法就是我们的所有handler都继承某个接口，然后Spring MVC自然就调用这个接口中定义的特殊方法。

#### 14.2.3 Control的调用逻辑 ####

DispatcherServlet的doService方法开始的，在doService方法会将ApplicationContext、localeResolver、themeResolver等对象添加到request中便于在后面使用。

doDispatch方法，主要处理用户请求的方法。

Control的处理逻辑关键就是在DispatcherServlet的handlerMappings集合中根据请求的URL匹配每个HandlerMapping对象的某个Handler,匹配成功后将会返回这个Handler的处理链HanderExecutionChain对象，而这个HandlerExecutionChain对象将会包含用户自定义的多个HandlerInterceptor对象。


DispatcherServlet
AbstractHandlerMapping
HandlerExceutionChain
HandlerInterceptor
HandlerAdapter 

### 14.3 Model设计 ###


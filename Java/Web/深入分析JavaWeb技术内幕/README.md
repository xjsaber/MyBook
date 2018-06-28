# 深入分析Java Web技术内幕 #


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

### 14.2 Control设计 ###
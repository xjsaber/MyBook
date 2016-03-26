#4.1 拦截器在Struts2中的默认应用
在Struts2的ServletDispatcher接收请求时，Struts2会查找配置文件，如struts.xml文件。根据xml文件中定义的拦截器配置，去调用拦截器。

1. 在xml配置文件中配置拦截器和拦截器栈都是以“<interceptors>”开头，以“</interceptiors>”结尾。
2. 配置拦截器的格式的格式如上面代码所示以“<interceptor/>”格式所示，其中两个属性
3. 拦截器栈的格式是以“<interceptor-stack>”开头，以"</interceptor-stack>"结尾。其中属性name是拦截器栈名字。在“<interceptor-stack>”和</interceptor-stack>
4. 针对struts-default.xml文件中各个拦截器配置进行介绍

	* alias：对于HTTP请求包含的参数设置别名。
	* autowiring：将某些JavaBean实例自动绑定到其他Bean对应的属性中。有点类似Spring的自动绑定。
	* Chain：在Web项目开发中，以前使用Struts开发时经常碰到两个Action互相传递参数或属性的情况。该拦截器就是让前一个Action的参数可以在现有Action中使用
	* conversionError:从ActionContext中将转化类型时发生的错误添加到Action的值域错误中，在校验时经常被使用来显示类型转化错误的信息。
	* cookie：从struts2.0.7开始，把cookie注入Action中可设置的名字或值中。
	* createSession：自动创建一个HTTP的Session，尤其是对需要HTTP的Session的拦截器特别有用。
	* debugging:用来对在视图间传递的数据进行调试。
	* ExecAndWait：不显式执行Action，在视图上显示给用户的是一个正在等待的页面，但是Action其实正在“背后”执行着。该拦截器尤其对进度条进行开发的时候特别有用。
	* exception：将异常和Action返回的result相映射。
	* fileUpload：支持文件上传功能的拦截器。
	* i18n：支持国际化的拦截器。
	* logger：拥有日志功能的拦截器。
	* modelDriven：执行该拦截器时，可以将getModel方法得到的result值放入值栈中。
	* scopedModelDriven：执行该拦截器时，可以从一个scope范围检索和存储model值，通过调用setModel方法去设置model值。
	* params：将HTTP请求中包含的的参数值设置到Action中。
	* prepare：假如Action继承了Preparable接口，则会调用prepare方法。
	* staticParams：对于在struts.xml文件中Action中设置的参数设置到对应的Action中。
	* scope：在session或者application范围中设置Action的状态。
	* servletConfig：该拦截器提供访问包含HttpServletRequest和HttpServletResponse对象的map的方法。
	* timer：输出Action的执行时间。
	* token：避免重复提交的校验拦截器。
	* tokenSession：和token拦截器类似，但它还能存储提交的数据到session中。
	* validation：运行在action-validation.xml()文件中定义的校验规则。
	* workflow：在Action中调用validate校验方法。如果Action有错误则返回到input视图。
	* store：执行校验功能时，该拦截器提供存储和检索Action的所有错误和正确信息的功能。
	* checkbox：视图中如果有checkbox存在的情况，该拦截器自动将unchecked的checkbox当做一个参数（通常值为“false”）记录下来。这样可以用一个隐藏的表单值来记录所有未提交的checkbox，而且缺省unchecked的checkbox值是布尔类型的，如果视图中checkbox的值设置的不是布尔类型，它就会被覆盖成布尔类型的值。
	* profiling：通过参数来激活或不激活分析检测功能，前提是Web项目是在开发模式下（涉及调试和性能检验时使用）。
	* roles：进行权限配置的拦截器，如果登录用户拥有相应权限才去执行某一特定Action。
# 第12章：StandardContext #

一个上下文容器（Context）代表一个 web 应用，每
一个上下文包括多个包装器（Wrapper），每个包装器代表一个 Servlet。

### StandardContext 配置 ###

创建一个 StandardContext 实例之后，必须调用它的 start 方法，这样它就能为受到的 HTTP 请求服务了。一个 StandardContext 对象可能启动失败，这时候属性 available 被设置为 false，属性 available 表示了 StandardContext 对象的可用性。

如果 start 方法启动成功，StandardContext 对象需要配置它的属性。在一个Tomcat 部署中，StandardContext 的配置过程做了以下事情：准备读取和解析%CATALINA_HOME%/conf 目录下面的 web.xml，部署所有应用程序，确保StandardContext 实例可以处理应用级别的 web.xml。

StandardContext 的属性之一是它属性 configured,用来表示该
StandardContext 是否已经配置了。StandardContext 使用一个事件监听器来作为它的配置器。当 StandardContext 实例的 start 方法被调用的时候，首先触发一个生命周期事件。该事件唤醒一个监听器来配置该 StandardContext 实例。配置成功后，该监听器将 configured 属性设置为 true。否则，StandardContext对象拒绝启动，这样就不能对 HTTP 请求进行服务了。

### StandardContext  构造函数 ###

	public StandardContext() {
		super();
		pipeline.setBasic(new StandardContextValve());
		namingResources.setContainer(this);
	}

在StandardContext的流水线上添加了一个类型为org.apache.catalina.core.StandardContextValue的基本阀门，该阀门用于连接器获得HTTP请求

### 启动StandardContext ###

Start 方法初始化 StandardContext 对象并让生命周期监听器配置该
StandardContext 实例。如果配置成功，生命周期监听器会将 configured 属性设置为 true。最后 start 方法，将 available 属性设置为 true 或者 false。如果是 true 的话表示该 StandardContext 属性配置完毕并且所有相关子容器和组件已经成功启动，这样就能对 HTTP 请求进行服务了，如果是 false 则表示出现了错误。

StandardContext 类将 configured 的值初始化为 false，如果生命周期监听器的配置过程成功，则将该值设置为 true。在 start 方法的最后，它检查StandardContext 对象的 configured 属性，如果该值为 true，则启动该StandardContext 成，否则调用 stop 方法停止所有已经启动的组件。

### Invoke方法 ###


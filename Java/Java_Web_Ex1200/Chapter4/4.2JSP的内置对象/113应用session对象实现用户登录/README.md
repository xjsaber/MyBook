#Q&A
##什么是session
Session 是 用于保持状态的基于 Web服务器的方法。Session 允许通过将对象存储在 Web服务器的内存中在整个用户会话过程中保持任何对象。

Session 通常用于执行以下操作
存储需要在整个用户会话过程中保持其状态的信息，例如登录信息或用户浏览 Web应用程序时需要的其它信息。
存储只需要在页重新加载过程中或按功能分组的一组页之间保持其状态的对象。  

Session 的作用就是它在 Web服务器上保持用户的状态信息供在任何时间从任何设备上的页面进行访问。因为浏览器不需要存储任何这种信息，所以可以使用任何浏览器，即使是像 Pad 或手机这样的浏览器设备。
持久性方法的限制
随着越来越多用户登录，Session 所需要的服务器内存量也会不断增加。
访问 Web应用程序的每个用户都生成一个单独的 Session 对象。每个 Session 对象的持续时间是用户访问的时间加上不活动的时间。
如果每个 Session 中保持许多对象，并且许多用户同时使用 Web应用程序（创建许多 Session），则用于 Session 持久性的服务器内存量可能会很大，从而影响了可伸缩性。
##关键技术
通过session对象可以存储或读取客户相关的信息，如用户名、购物信息等。

1.setAttribute()方法  
该方法用于将信息保存在session范围内，语法格式如下：

	session.setAttribute(String name, Object obj)
	参数说明
	1.name:用于指定作用域在session范围内的变量名。
	2.obj:保存在session范围内的对象。

2.getAttribute()方法  
该方法用于获取保存在session范围内的信息，语法格式如下：

	getAttribute(String name)
	参数说明
	name:指定保存在session范围内的关键字。

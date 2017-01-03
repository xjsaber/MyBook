# 第3章 创建第一个Servlet #

## 3.1 创建Servlet类 ##
Servlet用于接收的响应终端用户的请求。

Servlet是一个运行在Web服务器中的Java小程序。Servlet将会接收和响应来自Web客户端请求，使用HTTP（超文本传输协议）进行通信。
### 3.1.1 选择要继承的Servlet类 ###

### 3.1.2 使用初始化方法和销毁方法 ###
init方法在Servlet构件完成之后，但在响应第一个请求之前被调用。

destory在Servlet不再接受请求之后立即调用。

## 3.2 配置可部署的Servlet ##

### 3.2.1 向描述符中添加Servlet ###

servlet

servlet-mapping
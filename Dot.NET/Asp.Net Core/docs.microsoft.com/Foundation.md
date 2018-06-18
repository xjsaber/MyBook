# ASP.NET Core 基础知识 #

ASP.NET Core 2.x

## 基础 ##

Main方法调用WebHost.CreateDefaultBuilder，后者按照生成器模式来创建Web应用程序主机。

## 启动 ##

WebHostBuilder上的UseStartup方法为你的应用指定Startup类

Startup类用于定义请求处理管道和配置应用所需的任何服务。Startup必须是公共类，并包含以下方法

* ConfigureServices 定义应用所使用的服务
* Configure 定义请求管道的中间件

### 内容根 ###



### Web根 ###



### 依赖关系注入（服务） ###



### 中间件 ###

在ASP.NET Core，使用中间件来撰写请求管道。ASP.NEt Core中间件在HttpContext上执行异步逻辑，然后调用序列中的下一个中间件或直接终止请求。

通过在Configure方法中调用UseXYZ扩展方法来添加UseXYZ扩展方法来添加名为“XYZ”的中间件组件

* 静态文件
* 路由
* 身份验证
* 响应压缩中间件
* URL重写中间件

可以将任何基于OWIN的中间件与ASP.NET Core应用结合使用。


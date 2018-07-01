# ASP .NET Core 中的 Web 服务器实现  #

ASP.NET Core应用与进程内HTTP服务器实现一起运行。服务器实现侦听HTTP请求，并在一系列请求功能被撰写到HttpContext时将这些请求展现到应用中。

* Kestrel是适用于ASP.NET Core的默认跨平台HTTP服务器
* HTTP.sys是仅适用于Windows的HTTP服务器，基于核心驱动程序和HTTP服务器API。

### Kestrel ###

Hestrel是ASP.NET Core项目模板中包括的默认Web服务器。

Kestrel可以单独使用，也可以与反向代理服务器（如IIS、Nginx和Apache）一起使用。反向代理服务器接收到来自Internet的HTTP请求，并在进行一些处理后将这些请求转发到Kestrel。

#### IIS与Kestrel ####

将IIS或IIS Express用作ASP.NET Core的反向代理时，ASP.NET Core应用在独立于IIS工作进程的某个进程中运行。在IIS进程中，ASP.NET Core模块协调反向代理关系。ASP.NET Core模块的主要功能是启动ASP.NET Core应用，在其出现故障时重启应用，并向应用转发HTTP流量。

#### Nginx与Kestrel ####

#### Apache与Kestrel ####

### HTTP.sys ###

## ASP.NET Core服务器基础结构 ##

Startup.Configure方法中提供IApplicationBuilder公开了类型IFeatureCollection的ServerFeatures属性。Kestrel和HTTP.sys各自仅公开单个功能。即IServerAddressesFeature，但是不同的服务器实现可能公开功能。

`IServerAddressesFeature`可用于查找服务器实现在运行时绑定的端口。

### 自定义服务器 ###

### 服务器启动 ###

### 其他资源 ###

### 启动HTTP请求 ###

## ASP.NEt Core中的Kestrel Web服务器实现 ##

Kestrel是一个跨平台的适用于ASP.NET Core的Web服务器。Kestrel是Web服务器，默认包括在ASP.NET Core项目模板中。

Kestrel支持以下功能：

* HTTPS
* 用于启用WebScoket的不透明升级
* 用于获得Nginx高性能的Unix套接字

### 何时结合使用Kestrel和反向代理 ###

即使不需要反向代理服务器，使用反向代理服务器可能也是个不错的选择：

* 它可以限制所承载的应用中的公开的公共外围应用。
* 它可以提供额外的配置和防护层
* 它可以更好地与现有基础结构集成。
* 它可以简化负载均衡和SSL配置。仅反向代理服务器需要SSL证书，并且该服务器可使用普通HTTP在内部网络上与应用服务器通信。

## 如何在ASP.NET Core应用中使用Kestrel ##

Microsoft.AspNetCore.All元包 中包括 Microsoft.AspNetCore.Server.Kestrel 包

**Kestrel选项**

**客户端最大连接数**

**请求正文自打大小**

**请求正文最大大小**

TODO 限制HTTP请求可以之后回来继续看

**请求正文最小数据速率**

**终结点配置**

默认情况下，ASP.NET Core绑定到 http://localhost:5000。在KestrelServerOptions上调用Listen或ListenUnixSocket方法，从而配置Kestrel的URL前缀和端口。UseUrls、--urls命令行参数、urls主机配置健以及ASPNETCORE_URLS环境变量。

urls主机配置键必须来自主机配置，而不是应用配置。将urls健和值添加到appsetting.json不影响主机配置，因为是在从配置文件读取配置时对主机进行完全初始化。



# ASP.NET Core中的请求功能 #

### 功能接口 ###

ASP.NET Core在`Microsoft.AspNetCore.Http.Features`中定义了许多HTTP功能接口，服务器使用这些接口来标识其支持的功能。

`IHttpRequestFeature` 定义HTTP请求的结构，包括协议、路径、查询字符串、标头和正文。

`IHttpResponseFeature` 定义HTTP响应的结构，包括状态代码、标头、响应的正文。

`IHttpAuthenticationFeature` 定义支持基于`ClaimsPrincipal`来标识用户并指定身份验证处理程序。

P1041

### 功能集合 ###

HttpContext的Features属性为获取和设置当前请求的可用HTTP功能提供了一个接口。由于功能集合即使在请求的上下文是可变的，所以可使用中间件来修改集合并添加对其他功能的支持。

### 中间件和请求功能 ###



### 摘要 ###
# ASP.NET Core 2.1 安全性 #

## ASP.NET Core 安全性概述 ##

#### ASP.NET Core 安全性功能 ####

#### 身份验证 vs授权 ####

#### 软件中的常见漏洞 ####

防止跨站点脚本 (XSS) 在 ASP.NET 核心

## 身份验证 ##

### ASP.NET Core 上的标识简介 ###

#### 标识的概述 ####

### ASP.NET 核心项目中的基架标识 ###

## 启用跨域请求（CORS） ##

### 启用 ASP.NET Core 中的跨域请求 (CORS) ###

#### 什么是“相同源” ####

#### CORS设置 ####

若要为整个应用程序启用 CORS，请使用 `UseCors` 扩展方法向请求管道添加 CORS 中间件。

#### 启用CORS的中间件 ####

**每个操作**

**将预检过期时间设置**

	options.AddPolicy("SetPreflightExpiration",
    builder =>
    {
        builder.WithOrigins("http://example.com")
               .SetPreflightMaxAge(TimeSpan.FromSeconds(2520));
    });

#### CORS策略选项 ####

**设置允许的来源**

#### CORS的工作原理 ####

CORS 规范引入了几个新的 HTTP 标头启用跨域请求。 如果浏览器支持 CORS，则将设置为跨源请求自动这些标头。 不需要启用 CORS 自定义 JavaScript 代码。

`Origin`标头提供的域的正在发出请求的站点

	GET http://myservice.azurewebsites.net/api/test HTTP/1.1
	Referer: http://myclient.azurewebsites.net/
	Accept: */*
	Accept-Language: en-US
	Origin: http://myclient.azurewebsites.net
	Accept-Encoding: gzip, deflate
	User-Agent: Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; WOW64; Trident/6.0)
	Host: myservice.azurewebsites.net

如果服务器允许该请求，则将在响应中设置的访问控制的允许的域标头。 此标头的值匹配的 Origin 标头请求，或是通配符值"*"，允许任何来源的含义

	HTTP/1.1 200 OK
	Cache-Control: no-cache
	Pragma: no-cache
	Content-Type: text/plain; charset=utf-8
	Access-Control-Allow-Origin: http://myclient.azurewebsites.net
	Date: Wed, 20 May 2015 06:27:30 GMT
	Content-Length: 12
	
	Test message

**预检请求**

对于某些 CORS 请求，浏览器发送一个其他的请求，调用"预检请求"，再将发送实际请求的资源。

* 请求方法是 GET、 HEAD 或 POST 和
* 应用程序不设置任何请求标头以外，接受语言内容 Accept-language、 内容类型或最后一个事件 ID 和
* 内容类型标头 (如果设置) 是以下之一：
   application/x-www-form-urlencoded、multipart/窗体的数据、文本/无格式

## 在应用之间共享Cookie ##

### 与ASP.NET和ASP.NET Core之间共享在应用之间的cookie ###

数据保护堆栈允许在共享Katana cookie身份验证和ASP.NET Core cookie身份验证票证。

共享ASP.NET Core 应用之间的身份验证cookie

* 身份验证cookie名称设置为一个常见值
* AuthenticationType
* 常见的应用程序

#### 共享 ASP.NET Core 应用之间的身份验证 cookie ####

当使用 ASP.NET Core Identity：

在`ConfigureServices`方法，请使用ConfigureApplicationCookie扩展方法，以设置 cookie 数据保护服务。

	services.AddDataProtection()
	    .PersistKeysToFileSystem(GetKeyRingDirInfo())
	    .SetApplicationName("SharedCookieApp");
	
	services.ConfigureApplicationCookie(options => {
	    options.Cookie.Name = ".AspNet.SharedCookie";
	});

必须在应用之间共享数据保护密钥和应用程序名称。 

当直接使用 cookie

	services.AddDataProtection()
	    .PersistKeysToFileSystem(GetKeyRingDirInfo())
	    .SetApplicationName("SharedCookieApp");
	
	services.AddAuthentication("Identity.Application")
	    .AddCookie("Identity.Application", options =>
	    {
	        options.Cookie.Name = ".AspNet.SharedCookie";
	    });

必须在应用之间共享数据保护密钥和应用程序名称。 在示例应用中，GetKeyRingDirInfo返回到的公共密钥存储位置PersistKeysToFileSystem方法。 使用SetApplicationName配置公用的共享应用程序名 (SharedCookieApp示例中)。

#### 加密存放的数据保护密钥 ####

对于生产部署，配置`DataProtectionProvider`来加密存放使用 DPAPI 或 x509 证书的密钥。

	services.AddDataProtection()
    .ProtectKeysWithCertificate("thumbprint");

#### 共享身份验证 cookie 之间 ASP.NET 4.x 和 ASP.NET Core 应用 ####

ASP.NET 4.x 应用程序使用 Katana cookie 身份验证中间件该对话框可以配置为生成与 ASP.NET Core cookie 身份验证中间件兼容的身份验证 cookie。

1. 安装包Microsoft.Owin.Security.Interop到每个 ASP.NET 4.x 应用程序。
2. 在Startup.Auth.cs，找到调用`UseCookieAuthentication`和对其进行修改，如下所示。 更改要匹配使用 ASP.NET Core cookie 身份验证中间件的名称的 cookie 名称。 提供的一个实例`DataProtectionProvider`初始化为常见的数据保护密钥的存储位置。 

	app.UseCookieAuthentication(new CookieAuthenticationOptions
	{
	    AuthenticationType = "Identity.Application",
	    CookieName = ".AspNet.SharedCookie",
	    LoginPath = new PathString("/Account/Login"),
	    Provider = new CookieAuthenticationProvider
	    {
	        OnValidateIdentity =
	            SecurityStampValidator
	                .OnValidateIdentity<ApplicationUserManager, ApplicationUser>(
	                    validateInterval: TimeSpan.FromMinutes(30),
	                    regenerateIdentity: (manager, user) =>
	                        user.GenerateUserIdentityAsync(manager))
	    },
	    TicketDataFormat = new AspNetTicketDataFormat(
	        new DataProtectorShim(
	            DataProtectionProvider.Create(GetKeyRingDirInfo(), 
	                (builder) => { builder.SetApplicationName("SharedCookieApp"); })
	            .CreateProtector(
	                "Microsoft.AspNetCore.Authentication.Cookies.CookieAuthenticationMiddleware",
	                "Identity.Application",
	                "v2"))),
	    CookieManager = new ChunkingCookieManager()
	});

#### 使用常见的用户数据库 ####

确认每个应用程序的标识系统指向相同的用户数据库。 否则，标识系统会生成在运行时失败时它尝试匹配针对其数据库中的信息的身份验证 cookie 中的信息。
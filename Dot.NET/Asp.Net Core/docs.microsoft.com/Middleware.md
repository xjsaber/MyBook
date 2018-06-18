# ASP.NET Core中的中间件 #

## ASP.NET Core中的静态文件 ##

### 提供静态文件 ###

采用 `WebHost.CreateDefaultBuilder` 方法可将内容根目录设置为当前目录。

如果以 .NET Framework 为目标，请将 Microsoft.AspNetCore.StaticFiles 包添加到项目。 如果以 .NET Core 为目标，请将 Microsoft.AspNetCore.All 元包加入此包。

#### 提供Web根目根目录内的文件  ####

调用`Startup.Configure`中的UseStaticFiles方法

#### 提供Web根目根目录外的文件  ####



#### 设置HTTP响应标头 ####


### 静态文件授权 ###


### 启用目录浏览 ###

### 提供默认文档 ###

	public void Configure(IApplicationBuilder app) 
	{    
		app.UseDefaultFiles();    
		app.UseStaticFiles(); 
	}

使用UseDefaultFiles请求文件搜索：

* default.htm
* default.html
* index.htm
* index.html

### UseFileServer ###

## ASP.NET Core中的路由 ##

### 路由基础知识 ###

路由使用路由（IRouter的实现）来：

* 将传入请求映射到路由处理程序
* 生成响应中使用的URL

URL匹配
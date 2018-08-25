# 第26章 使用Spring Security验证用户 #

## 26.1 选择并配置认证提供者 ##

### 26.1.1 配置用户细节提供者 ###

AuthenticationProvider实现之一就是org.springframework.security.authenticcation.dao.DaoAuthenticationProvider。

以使用数据访问对象以org.springframework.security.core.userdetails.UserDetailsService实现的形式

#### 1. 创建Spring Security过滤器 ####

* 处理几乎所有类型登录的过滤器
* 所有的Spring Security过滤器
* 处理安全敏感日志的过滤器。
* 处理多租户决定的过滤器（在多租户应用程序中，需要今早决定请求属于哪个承租人）。
* 按照正确的顺序执行所有其他的过滤器。


#### 4.基本认证和摘要认证 ####

基本的访问认证就是一个HTTP认证协议，通过它可以主动请求，并认证返回的响应。当由基本认证保护的资源接受到缺少凭证的请求时，它将返回一个状态码为401 Not Authorized、头为WWW-Authenticate的响应。

当浏览器接受到该响应时，将在一个模拟窗口中提示用户输入的用户名和密码，该窗口中包含了realm头参数的文本值。如果用户单击取消，浏览器也将取消该请求，如果用户输入了用户名和密码，那么浏览器将重新发送完全相同的请求，但包含了一个Authorization头，它由单次Basic加上空格再加上由冒号分隔的用户名和密码的Base64编码值。

服务器将对头进行编码，然后将用户名和密码与服务器凭证数据库中存储的值相比较。因为该凭证是不正确的，服务器仍然返回的是一个包含401 Not Authorized状态码和WWW-Authenticate头的响应。

* Algorithm
* qop
* opaque
* nonce

5.表单证书

6.Microsoft Windows认证

7.客户端证书

8.智能卡和生物识别

9.基于声明的认证

10.多因素认证

### 25.1.2 了解授权 ###
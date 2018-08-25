# 第25章 介绍Spring Security #

## 25.1 认证的概念 ##

### 25.1.1 集成认证 ###

1.匿名认证

2.密码认证

3.用户名和密码

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

低权限用户和管理员。

#### 1.使用主体和标识 ####

	java.security.Principal

#### 2.角色、组、活动和权限 ####

#### 3. 基于声明的授权 ####

## 25.2 选择Spring Security的原因 ##


### 25.2.1 了解Spring Security基础 ###

SpringSecurity使用的核心接口是：org.springframework.security.core.Authentication。扩展了Principal，并提供与标识相关的一些额外信息。

Authentication还通过getAuthorities方法提供了用户的org.springframework.security.core.GrantedAuthority。

org.springframework.security.authentication.AuthenticationProvider

### 25.2.2 使用Spring Security的授权服务 ###

1. 使用全局方法安全注解。
2. Spring Security配置中定义方法拦截规则

### 25.2.3 配置Spring Security ###

紧密使用Spring Framework和Spring Security。Spring Security的配置与Spring Framework的配置紧密地绑定在一起，并且大量使用了ApplicationContext来管理它的安全上下文。

@Configuration类（实现了配置接口）的方法以编程的方式创建安全上下文。尽管SpringFramework每个应用上下文都只有一个配置，但是Spring Security在一个应用上下文中可以有多个配置。

## 25.3 小结 ##

认证和授权之间的区别，并了解了在实施安全过程中的作用。

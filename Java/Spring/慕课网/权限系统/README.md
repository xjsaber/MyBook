# 第一章 #

## 1-1 为什么企业级项目需要权限管理 ##

* 安全性：误操作性、人为破坏、数据泄漏等
* 数据隔离：不同的权限能看到及操作不同的数据
* 明确职责：运营、客服等不同角色，leader和dev等不同级别

## 1-2 权限管理核心 ##

用户-权限：

RBAC（Role-Based Access Control）：用户-角色-权限

## 1-3 理想中的权限功能 ##

能实现角色级权限：RBAC

能实现功能级、数据级权限

功能级：查看当前登录的用户的权限，是否有改功能权限

数据级：硬编码、规则引擎、第三方软件

### 相关操作页面 ###

* 权限管理界面、角色管理界面、用户管理界面
* 角色和权限关系维护界面、用户和角色关系维护界面

## 1-4 开源权限管理项目 ##

Spring Security

Apache Shiro

## 1-5 课程主要内容 ##

* Spring Security 介绍与使用

* Apache Shiro 介绍与使用

* 自己实现一套权限管理系统（模块）

### 涉及到的知识 ###

Spring MySQL Redis Java jQuery

# 第2章 #

## 2-1 Spring Security权限管理介绍 ##

* Spring Security 介绍
* 环境搭建及使用
* 优缺点总结

### Spring Security介绍 ###

Basic认证 用户名和密码才能访问，密码用明文传输
Digest 用户名和密码，md5加密
x.509
LDAP
Form

### Spring Security-权限拦截 ###

SecurityContextPerisitenceFilter

LogoutFilter

Ab

## 2-3 Spring Security数据库管理讲解 ##




## 2-4 Spring Security权限缓存 ##

CacheingUserDetailsServices

## 2-8 基于SpringSecurity权限管理Case实操 ##

@PreAuthorize("hasRole('ROLE_ADMIN')")

## 2-9 SpringSecurity权限管理框架总结 ##

* 配置文件多，角色被“编码”到配置文件和源文件中，RBAC不明显
* 对于系统中用户、角色、权限之间的关系，没有可操作性的界面
* 大数据量情况下，几乎不可用

# 第3章 #

## 3-1 Apache Shiro ##

* Apache Shiro 介绍
* 环境搭建及使用

### Apache Shiro 介绍 ###

Authentication、AUthorization、Session Management、Cryptography

WebSupport Caching Concurrery Testing “Run As” RememberMe

## 3-2 Apache Shiro-身份认证 ##

## 3-3 Apache Shiro-授权 ##

主体（用户） 资源 权限（CRUD） 角色

## 3-4 Apache Shiro-权限拦截 ##

Filter<-AbstractFilter<-NameableFilter<-OncePerRequestFilter<-AbstractShiroFilter<-ShiroFilter

Filter<-AbstractFilter<-NameableFilter<-OncePerRequestFilter<-AdviceFilter<-PathMatchingFilter<-AccessControlFilter

	AdviceFilter	
		preHandler 预处理
		postHandler 后续处理
		afterCompletion  

	PathMatch
		preHandler 预处理
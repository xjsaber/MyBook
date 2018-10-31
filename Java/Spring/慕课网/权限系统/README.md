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
	
	ProxiedFilterChain

	AdviceFilter	
		preHandler 预处理
		postHandler 后续处理
		afterCompletion  

	PathMatch
		preHandler 预处理

	onAccessDenied

Diagram

## 3-5 Apache Shiro会话管理讲解 ##

SessionManager

## 3-6 Apache Shiro 权限缓存 ##

	Cache 
	EaCache
	MapCache

## 3-7 环境搭建及使用 ##

* 快速搭建Spring Boot + Apache Shiro 环境
* 常用Case实现		preHandler 预处理


# 第4章 #

## 4-1 为什么要自己开发一套权限关系系统 ##

* 满足框架的要求进行配置
* 没有界面操作和查看
* 期望更细致的管理

## 4-2 我们要开发一套怎样的权限关系系统 ##

* 基于扩展的RBAC实现
* 易于扩展，能灵活适应需求的变化
* 所有管理都有界面方便操作

## 4-3 部门表设计 ##

### 详细表结构设计 ###

用户表、部门表

	[Class]SysDept(sys表示系统级别的)
	id Integer
	name String
	level String
	seq Integer
	remark String
	parentId Integer
	operator String
	operateTime Date
	operateIp String

## 4-4 用户表设计 ##

### 详细表结构设计 ###

	[Class]SysUser(sys表示系统级别的)
	id Integer
	username String
	telphonse String
	mail String
	password String
	remark String
	deptId Integer
	status Integer
	operator String
	operateTime Date
	operateIp String

## 4-5 权限模块表设计 ##

### 详细表结构设计 ###

	[Class]SysAclModule(sys表示系统级别的)
	id Integer
	name String
	parentId Integer
	level String
	seq Integer
	remark String
	operator String
	operateTime Date
	operateIp String

## 4-6 权限表设计 ##

### 详细表结构设计 ###

	[Class]SysAcl(sys表示系统级别的)
	id Integer
	code String
	name String
	aclModuleId Integer
	url	String
	type Integer
	status Integer
	seq Integer
	remark String
	operator String
	operateTime Date
	operateIp String


## 4-7 角色表设计 ##

## 4-8 角色-用户，角色-权限关联关系表设计 ##

## 4-9 权限相关Log表设计 ##


# 第5章 #

code

# 第6章 #

编码部分

## 6-1 核心类生成-Mybatis Generator ##

mysql test和varchar的用途（6-1 视频）






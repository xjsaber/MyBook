# 第15章 在Spring Framework i18n 国际化应用程序 #

## 15.1 使用Spring Framework i18n的原因 ##

### 15.1.1 使国际化变得更容易 ###

HttpServletRequest包含了getLocal和getLocals方法。

### 15.1.2 直接本地化错误信息 ###

## 15.2 使用基本的国际化和本地化API ##

### 15.2.1 了解资源包和消息格式 ###

### 15.2.2 使用消息源进行挽救 ###

Spring消息源提供了对资源包的抽象和封装。

* org.springframework.context.support.ResourceBundleMessageSource
* org.springframework.context.support.ReloadableResourceBundleMessageSource

### 15.2.3 使用消息源国际化JSP ###



## 15.3 在Spring Framework中配置国际化 ##

### 15.3.1 创建消息源 ###

### 15.3.3 使用处理拦截器修改区域设置 ###

## 15.5 小结 ##

<spring:message>、<fmt:message>、Java字符串以及Spring的MessageSource对JSP视图进行国际化处理。

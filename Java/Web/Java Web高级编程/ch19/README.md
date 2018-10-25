# 第19章 介绍Java Persistence API 和 Hibernate ORM #

## 19.1 了解Web服务 ##

### 19.1.1 平面文件实体存储 ###

XML或JSON

### 19.1.2 结构化文件存储 ###

Btrieve

### 19.1.3 关系数据库系统 ###

PL/SQL

### 19.1.4 面向对象数据库 ###

### 19.1.5 无模式数据库系统 ###

## 19.2 对象-关系映射的定义 ##

### 19.2.1 了解持久化实体的问题 ###

### 19.2.2 创建单独的Web和REST应用上下文 ###

### 19.2.3 JPA提供了一种标准 O/RM API ###

MyBatis，Hibernate ORM

JPA 1.0 属于 JSR220

* 不适用JPQL，缺少一种根据复杂条件查询对象的标准方式
* JPA的核心原则之一就是将SQL数据类型的有限集合转换成Java数据类型。
* JPA1.0缺少对实体中的实体集合、多层嵌套实体和有序列表的支持。

## 19.3 使用Hibernate ORM的原因 ##

## 19.4 Hibernate ORM简介 ##

### 19.4.1 使用 Hibernate 映射文件 ###

### 19.4.2 了解会话API ###

### 19.4.3 从SessionFactory中获得会话 ###

会话不会凭空出现，会话会被关联到一个JDBC数据库连接，因此使用会话之前，必须创建连接或者从数据源中获得一个连接，实例化会话实现，并将会话附着到连接上。

	Session session = sessionFactory.openSession();

	Session session = sessionFactory.withOptions().connection(connection).openSession();

Hibernate ORM也有无状态会话的概念。

SessionFactory最重要的特性之一就是保持和获取“当前”Session的能力

org.hibernate.context.internal.ThreadLocalSessionContext，在java.lang.ThreadLocal中存储了当前的会话。所有对getCurrentSession的调用都将获得之前在当前线程中打开的会话。

	Session session = sessionFactory.openSession();

	Session session = sessionFactory.getCurrentSession();

### 19.4.4 使用Spring Framework 创建 SessionFactory ###

如果使用XML配置Spring，那么定义一个org.springframework.orm.hibernate4.LocalSessionFactoryBean bean更简单，Spring bean将创建和返回一个SessionFactory。LocalSessionFactoryBean还实现了org.springframework.dao.support.PersistenceExceptionTranslator，还可以被用作转换器，将Hibernate ORM异常转换为Spring Framework的持久化异常。

使用Java配置时，使用org.springframework.orm.hibernate4.LocalSessionFactoryBuilder就时简单的方式，继承了org.hibernate.cfg.Configuration，为Spring Framework应用程序提供了配置SessionFactory的一些简洁方式。

## 19.5 准备关系数据库 ##

## 19.6 小结 ##






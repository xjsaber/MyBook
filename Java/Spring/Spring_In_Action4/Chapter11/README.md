# 第11章 使用对象-关系映射持久化数据 #

* 借助上下文Session，编写不依赖于Spring的Repository
* 通过Spring使用JPA
* 借助Spring Data实现自动化的JPA Repository

## 11.1 在Spring中集成Hibernate ##
数据访问的功能都放到一个或多个专注于此项任务的组件中。（data access object，DAO）或Repository

#### 1. 展现了设计数据访问层的合理方式 ####
![图10.1](img/2016-12-13_21-52-19.jpg)
服务对象本身并不会处理数据访问，而是将数据访问委托给Repository。Repository接口确保其与服务对象的松耦合。

### 11.1.1 声明Hibernate的Session工厂 ###
org.hibernate.Session。Session接口提供了基本的数据访问功能，如保存、更新、删除以及从数据库加载对象的功能。通过Hibernate的Session接口，应用程序的Repository能够满足所有的持久化需求。

org.springframework.orm.hibernate4.LocalSessionFac

### 11.1.2 数据访问模板化 ###
编写Repository类将会涉及到使用Spring的HibernateTemplate能够保证每个事务事务使用同一个Session。但是最佳实践是不再使用HibernateTemplate，而是使用上下文Session(Contextual session)。通过这种方式，会直接将Hibernate SessionFactory装配到Repository中，并使用它来获取Session。




## 10.2 配置数据源 ##

### 10.2.1 使用JNDI数据源 ###

### 10.2.2 使用数据源连接池 ###

## 10.3 在Spring中使用JDBC ##
和Ado.net
### 10.3.1 应对失控的JDBC代码 ###
大量的JDBC代码就是用于创建连接和语句以及异常处理的样板代码。但实际上，这些样板代码是非常重要的。清理资源和处理错误确保了数据访问的健壮性。
### 10.3.2 使用JDBC模板 ###
Spring的JDBC框架承担了资源管理和异常处理的工作。

Spring为JDBC提供了三个模板类供选择：
JdbcTemplate:最基本的Spring

#### 使用JdbcTemplate来插入数据 ####
需要设置DataSource

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource){
		return new JdbcTemplate(dataSource);
	}
通过构造器参数注入进来

	@Repository
	public class JdbcSpitterRepository implements SpitterRepository {
		private JdbcOperations jdbcOperations;
		@Inject
		public JdbcSpitterRepository(JdbcOperations jdbcOperations) {
			this.jdbcOperations = jdbcOperations;
		}
	}
@Repository注解，这表明它将会在组件扫描的时候自动创建。它的构造器上使用了@Inject注解，因此在创建的时候，会自动获得一个JdbcTemplate所实现的操作。通过注入JdbcOperations，而不是具体的JdbcTemplate，能过保证JdbcSpitterRepository通过JdbcOperations接口达到与JdbcTemplate保持松耦合。

#### 使用JdbcTemplate来读取数据 ####

#### 在JdbcTemplate中使用Java 8的Lambda表达式 ####

#### 使用命名参数 ####

## 10.4 小结 ##


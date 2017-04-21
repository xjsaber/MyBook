# 第13章 缓存数据 #

缓存（Cacheing）

## 13.1 启用对缓存的支持 ##

* 注解驱动的缓存
* XML声明的缓存

@Cacheable和@CacheEvict注解。

使用缓存之前，必须要启用Spring对注解驱动缓存的支持。
如 Java的@EnableCaching和XML的<cache:annotation-driven>
他们都会创建一个切面（aspect）并触发Spring缓存注解的切点（pointcut）。根据所使用的注解以及缓存的状态，这个切面会从缓存中获取数据，将数据添加到缓存之中或者从缓存中移除某个值。

ConcurrentMapCacheManager

### 13.1.1 配置缓存管理器 ###
* SimpleCacheManager
* NoOpCacheManager
* ConcurrentMapCacheManager
* CompositeCacheManager
* EhCacheCacheManager
* RedisCacheManager
* GemfireCacheManager

#### 使用Ehcache缓存 ####

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


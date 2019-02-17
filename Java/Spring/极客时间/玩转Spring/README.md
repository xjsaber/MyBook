# 玩转Spring笔记 #

## 06 如何配置多数据源 ##

### 配置多数据源的注意事项 ###

不同数据源的配置要分开

关注每次使用的数据源

* 有多个DataSource时系统如何判断
* 对应的设施（事务、ORM等）如何选择DataSource

### SpringBoot中的多数据源配置 ###

手工配置两组DataSource及香瓜内容

与SpringBoot协同工作（二选一）

* 配置@Primary类型的Bean
* 排除SpringBoot的自动配置

	* DataSourceAutoConfiguration
	* DataSourceTransactionManagerAutoConfiguration
	* JdbcTemplateAutoConfiguration

## 07 那些好用的连接池 HikariCP ##

1. 字节码级别优化（JavaAssist生成）
2. 大量小改进
	* 用FastStatementList代替ArrayList
	* 无锁集合ConcurrentBag
	* 代理类的优化（比如，用invokestatic代替了invokevirtual，字节码的优化，少了几个步骤，导致它速度变快）


### 在SpringBoot中的配置 ###



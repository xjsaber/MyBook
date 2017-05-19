# 深入浅出Mybatis技术原理与实战.md #

1. [mybatis简介]()
2. [mybatis入门]()
3. [配置]()



第1章 Mybatis

## 第3章 配置 ##

	<configuration> <!--配置-->
		<properties/> <!--属性-->
		<settings/> <!--设置-->
		<settings/> <!--设置-->
		<typeAliases/> <!--类型命名-->
		<typeHandlers/> <!--类型处理器-->
		<objectFactory/> <!--对象工厂-->
		<plugins/> <!--插件-->
		<environments> <!--配置环境-->
			<environment>
				<transactionManager/> <!--事务管理器-->
				<dataSource/> <!--数据源-->
			</environment> 
		</environments>
		<databaseIdProvider/> <!--数据库厂商标识-->
		<mappers/> <!--映射器-->

### 3.1 properties元素 ###

* property子元素
* property配置文件
* 程序参数传递

#### 3.1.1 property子元素 ####

	<properties>
		<properties name="driver" value="">
		<properties name="url" value="">
		<properties name="username" value="">
		<properties name="password" value="">
	</properties>

#### 3.1.2 properties配置文件 ####

数据库配置文件

	driver=com.mysql.jdbc.Driver
	url=xxx
	username=xxx
	password=xxx

主程序
	<properties resource="jdbc.properties" />

#### 3.1.3程序参数传递 ####

#### 3.1.4优先级 ####

### 3.2 设置 ###

1. cacheEnabled 该设置影响所有映射器中配置的缓存全局开关。
2. lazyLoadingEnabled 延迟加载的全局开关。
3. aggressiveLazyLoading 当启用时，对任意延迟属性的调用会使带有延迟加载属性的对象完整加载；反之，每种属性将会按需加载。
4. multipleResultSetsEnabled 是否允许单一语句返回多结果集（驱动兼容）。
5. autoMappingBehavior 使用列标签代替列名。
6. defaultExecutorType 允许JDBC自动生成主键（驱动兼容）。
7. defaultStatementTimeout 指定Mybatis应如何自动映射列到字段和属性。NONE取消自动映射、PARTIAL自动映射没有定义嵌套结果集映射的结果集、FULL自动映射任意复杂的结果集
8. safeRowBoundsEnabled
9. mapUnderscoreToCamelCase 是否开启自动驼峰命名规则（camel case）映射
10. localCacheScope MyBatis利用本地缓存（Local Cache）防止循环引用（circular references）和加速重复嵌套查询。默认值为SESSION（缓存一个会话中执行的所有查询）。若设置为STATEMENT，本地会话仅用在语句执行上，对相同SqlSession的不同调用
11. jdbcTypeForNull 当没有为参数提供特定的JDBC类型时，为空值指定JDBC类型。
12. lazyLoadTriggerMethods 指定对象的方法触发一次延迟加载。
13. defaultScriptingLanguage 指定动态SQL生成的默认语言。
14. callSettersOnNull 
15. logPrefix 指定MyBatis增加到日志名称的前缀。
16. logImpl 指定MyBatis所用日志的具体实现，未指定时将自动查找。
17. proxyFactory 指定MyBatis创建具有延迟加载能力的对象所用到的代理工具。

### 3.3 别名 ###
别名（typeAliases）是一个指代的名称。我们遇到的类全限定名过长，所以用一个简短的名称去指代它。一个typeAliases的实例是在解析配置时生成的，然后长期保存在Configuration对象中，当我们使用它时，再把它拿出来，这样就没有必要运行的时候再次生成它的实例了。

3.3.1 系统定义别名

MyBatis已经定义type Aliases

3.3.2 自定义别名

	<!--定义别名-->
	<typeAliases>
		<typeAlias alias="role" type="com.learn.chapter2.po.Role" />
	</typeAliases>

## 3.4 typeHandler ##

typeHandler常用的配置为Java类型（javaType）、JDBC类型（jdbcType）。typeHandler的作用就是将参数从javaType转化为jdbcType，或者从数据库取出结果时把jdbcType转化为javaType。

### 3.4.1 系统定义的typeHandler ###

|类型处理器|Java类型|JDBC类型|
|--|--|

* 数值类型的精度，数据库int、double、decimal这些类型和java的精度、长度都是不一样的。
* 时间精度，取数据到日用DateOnlyTypeHandler即可，用到精度为秒的用SqlTimestampTypeHandler等。

### 3.4.2 自定义typeHandler ###

	<typeHandlers>
		<typeHandler jdbcType="VARCHAR" javaType="string" handler = "com.learn.chapter3.typeHandler.MyStringTypeHandler" />
	</typeHandlers>

自定义typeHandler里用注解配置JdbcType和JavaType。

* @MappedTypes定义的是JavaType类型，可以指定哪些Java类型被拦截。
* @MappedJdbcTypes定义的是JdbcType类型，它需要满足枚举类org.apache.ibatis.type.JdbcType所列的枚举类型。

#### 3.4.3 枚举类型typeHandler ####

**3.4.3.1 EnumOrdinalTypeHandler**

**3.4.3.2 EnumTypeHandler**

EnumTypeHandler是使用枚举名称去处理Java枚举类型。EnumTypeHandler对用的是一个字符串。

1. 定义一个字符串，VARCHAR型的字典项。
2. 然后把POJO的属性set从整数型修改为String型。（EnumTypeHandler是通过Enum.name方法将其转化为字符串，通过Enum.valueOf()方法将字符串转化为枚举的）。

**3.4.3.3 自定义枚举类的typeHandler**

### 3.5 ObjectFactory ###

当MyBatis在构建一个结果返回的时候，都会使用ObjectFactory(对象工厂)去构建POJO，在MyBatis中可以定制自己的对象工厂。

MyBatis中默认的ObjectFactory是由org.apache.ibatis.reflection.factory.DefaultObjectFactory来提供服务的。
	
	如果要定制特定的工厂则需要进行配置
	<objectFactory type="com.learn.chapter3.objectFactory.MyObjectFactory">
		<property name="name" value="MyObjectFactory" />
	</objectFactory>

### 3.6 插件 ###

### 3.7 environments 配置环境 ###

#### 3.7.1 概述 ####

注册多个数据源(dataSource)，每一个数据源分为两大部分：一个是数据库源的配置，另一个是数据库事务（transactionManager）的配置。

* environments
* environment
* transactionManager
* property
* dataSource

#### 3.7.2 数据库事务 ####

数据库事务MyBatis是交由SqlSession去控制，通过SqlSession提交(commit)或者回滚(rollback)。

	try {
		sqlSession.commit()
	} catch(Exception ex) {
		sqlSession.rollback();
	} finally {
		sqlSession.close();
	}

#### 3.7.3 数据源 ####

### 3.8 databaseIdProvider数据库厂商标识 ###

#### 3.8.1 使用系统默认规则 ####

#### 3.8.2 不使用系统默认规则 ####

### 3.9 引入映射器的方法 ###

## 第4章 映射器 ##

4.1 映射器的主要元素

select、insert、update、delete、parameterMap、sql、resultMap、cache、cache=ref

### 4.2 select元素 ###

#### 4.2.1 概述 ####

select

#### 4.2.2 简易数据类型的例子 ####

* id标出这条SQL
* parameterType定义参数类型
* resultType定义返回值类型

#### 4.2.3 自动映射 ####

settings元素中配置autoMappingBehavior属性值来设置其策略。

* NONE，取消自动映射
* PARTIAL，只会自动映射，没有定义嵌套结果集映射的结果集。
* FULL，会自动映射任意复杂的结果集（无论是否嵌套）。

#### 4.2.4 传递多个参数 ####

## 第9章 实用的场景 ##

9.1 数据库BLOB字段读写

POJO




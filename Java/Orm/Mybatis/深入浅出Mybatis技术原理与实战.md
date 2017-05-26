# 深入浅出Mybatis技术原理与实战.md #

1. [mybatis简介]()
2. [mybatis入门]()
3. [配置]()
4. [映射器]()
5. [动态SQL]()

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

**4.2.4.1 使用Map传递参数**

	<select id="findRoleByMap" parameterType="map" resultMap="roleMap">
		select id, role_anem, note from t_role
		where role_name like concat('%', #{roleName}, '%')
		and note like concat('%', #{note}, '%')
	</select>
	Map需要键值对应，由于业务关联性不强，需要深入到程序中看代码，造成可读性下降。

**4.2.4.2 使用注解方式传递参数**

	@Param(org.apache.ibatis.annotations.Param)
	
	public List<Role> findRoleByAnnotation(@Param("roleName")String rolename, @Param("note")String note);
	<select id="findRoleByAnnotation" resultMap="roleMap">
		select id, role_name, note from t_role
		where role_anem like concat('%', #{roleName}, '%')
		and note like concat('%', #{note}, '%')
	</select>

**4.2.4.3 使用JavaBean传递参数**
组织一个JavaBean，通过setter和getter方法设置参数。定义一个RoleParams的JavaBean。
	
	RoleParam {
		private String roleName;
		private String note;
		...
	}
	<select id="findRoleByParams" parameterType="com.learn.chapter4.params.RoleParam" resultMap="roleMap">
		select id, role_name, note from t_role
		where role_name like concat('%', #{roleName}, '%')
		and note like concat('%', #{note}, '%')
	</select>

**4.2.4.4 总结**

* 使用Map传递参数。因为Map导致业务可读性的丧失，从而导致后续扩展和维护的困难，应该在实际的应用中果断废弃这样的传递参数的方式。
* 使用@Param注解传递多个参数，使用受到参数个数（n）的影响。当n<=5时，她是最佳的传递方式，它比用JavaBean更好，因为它更加直观；当n>5时，多个参数将给调用带来困难。
* 当参数个数多余5个时，建议使用JavaBean方式。

**4.2.5 使用resultMap映射结果集**

### 4.3 insert元素 ###

#### 4.3.1 概述 ####

insert元素配置详解

|属性名称|描述|备注|
|--|--|
|id|它和|如不|
|parameterType|
|parameterMap|
|flushCache|
|timeout|
|statementType|
|keyProperty|
|useGeneratedKeys|
|keyColumn|
|databaseId|
|lang|

## 第5章 动态SQL ##

### 5.1 概述 ###

|元素|作用|备注|
|--|--|--|
|if|判断语句|单条件分支判断|
|choose(when、otherwise)|相当于Java中的case when语句|多条件分支判断|
|trim(where、set)|辅助元素|用于处理一些SQL拼装问题|
|foreach|循环语句|在in语句等列举条件常用|

### 5.2 if元素 ###

	<select id="findRoles" parameterType="String" resultMap="roleResultMap">
		select role_no, role_name, role from t_role where 1 = 1
		<if test="roleName" != null and roleName != ''">
			and role_name like concat('%', #{roleName}, '%')
		</if>
	</select>

### 5.3 choose、when、otherwise元素 ###

### 5.4 trim、where、set元素 ###
	
	<select id="findRoles" parameterType="string" 
	resultMap="roleResultMap">
		<trim prefix="where" prefixOverrides="and">
			<if test="roleName != null and roleName != ''">
				and role_name like concat('%', #{roleName}, '%')
			</if>
		</trim>
	</select>
trim元素意味着我们要去掉一些特殊的字符串，prefix代表的是语句的前缀，prefixOverrides代表的是需要去掉的那种字符串。

	<set>
		...
	</set>
### 5.5 foreach元素 ###

	<select id="findUserBySex" resultType="user">
		select * from t_user where sex in
		<foreach item="sex" index="index" collection="sexList" open="(" separator ="," close=")">
			#{sex}
		</foreach>
	</select>

* collection配置的sexList是传递进来的参数名称
* item配置的是循环中当前的元素。
* index配置的是当前元素在集合的位置下标。
* open和close配置的是以什么符号将这些集合元素包装起来。

### 5.6 test的属性 ###

	test的属性用于条件判断的语句，作用判断真假。

### 5.7 bind元素 ###

bind元素的作用是通过OGNL表达式去自定义一个上下文变量。

## 第6章 MyBatis的解析和运行原理 ##

## 第8章 MyBatis-Spring ##

IOC（反转控制）和AOP（面向切面编程）

### 8.1 Spring的基础知识 ###

#### 8.1.1 Spring IOC基础 ####

Spring中的IOC注入方式分为下面：

* 构造方法注入
* setter注入
* 接口注入




## 第9章 实用的场景 ##

9.1 数据库BLOB字段读写

POJO




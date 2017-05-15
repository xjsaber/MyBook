# mybatis 官方文档 笔记 #

[1.XML配置]()
[XML映射文件]()
3. [动态SQL]()
4. [JavaAPI]()

## XML配置 ##

## XML映射文件 ##

* cache
* cache-ref
* resultMap
* sql
* insert
* update
* delete
* select

### select ###

	<select id="selectPerson" parameterType="int" resultType="hashmap">
	  SELECT * FROM PERSON WHERE ID = #{id}
	</select>
这个语句被称作 selectPerson，接受一个 int（或 Integer）类型的参数，并返回一个 HashMap 类型的对象，其中的键是列名，值便是结果行中的对应值。

	#{id}
参数符号

	// Similar JDBC code, NOT MyBatis…
	String selectPerson = "SELECT * FROM PERSON WHERE ID=?";
	PreparedStatement ps = conn.prepareStatement(selectPerson);
	ps.setInt(1,id);

|属性|描述|
|--|--|
|id|在命名空间中唯一的标识符，可以被用来应用这条语句|
|parameterType|将会传入这条语句的参数类的完全限定名或别名。这个属性是可选的，因为 MyBatis 可以通过 TypeHandler 推断出具体传入语句的参数，默认值为 unset|
|resultType|--|
|resultMap|--|
|flushCache|--|
|useCache|--|
|timeout|--|
|fetchSize|--|
|statementType|--|
|resultSetType|--|
|databaseId|--|
|resultOrdered|--|
|resultSets|--|

### insert, update 和 delete ###

### 参数（Parameters） ###

### Result Maps ###

#### 关联的嵌套结果 ####

|属性|描述|
|--|--|
|resultMap	|这是结果映射的 ID,可以映射关联的嵌套结果到一个合适的对象图中。这 是一种替代方法来调用另外一个查询语句。这允许你联合多个表来合成到 resultMap 一个单独的结果集。这样的结果集可能包含重复,数据的重复组需要被分 解,合理映射到一个嵌套的对象图。为了使它变得容易,MyBatis 让你“链 接”结果映射,来处理嵌套结果。一个例子会很容易来仿照,这个表格后 面也有一个示例。|
|columnPrefix	|当连接多表时，你将不得不使用列别名来避免ResultSet中的重复列名。指定columnPrefix允许你映射列名到一个外部的结果集中。 请看后面的例子。|
|notNullColumn	|默认情况下，子对象仅在至少一个列映射到其属性非空时才创建。 通过对这个属性指定非空的列将改变默认行为，这样做之后Mybatis将仅在这些列非空时才创建一个子对象。 可以指定多个列名，使用逗号分隔。默认值：未设置(unset)。|
|autoMapping	|如果使用了，当映射结果到当前属性时，Mybatis将启用或者禁用自动映射。 该属性覆盖全局的自动映射行为。 注意它对外部结果集无影响，所以在select or resultMap属性中这个是毫无意义的。 默认值：未设置(unset)。| 

	<select id="selectBlog" resultMap="blogResult">
	  select
	    B.id            as blog_id,
	    B.title         as blog_title,
	    B.author_id     as blog_author_id,
	    A.id            as author_id,
	    A.username      as author_username,
	    A.password      as author_password,
	    A.email         as author_email,
	    A.bio           as author_bio
	  from Blog B left outer join Author A on B.author_id = A.id
	  where B.id = #{id}
	</select>

	<resultMap id="blogResult" type="Blog">
	  <id property="id" column="blog_id" />
	  <result property="title" column="blog_title"/>
	  <association property="author" column="blog_author_id" javaType="Author" resultMap="authorResult"/>
	</resultMap>
	
	<resultMap id="authorResult" type="Author">
	  <id property="id" column="author_id"/>
	  <result property="username" column="author_username"/>
	  <result property="password" column="author_password"/>
	  <result property="email" column="author_email"/>
	  <result property="bio" column="author_bio"/>
	</resultMap>

#### 集合 ####

#### 集合的嵌套查询 ####

### 缓存 ###

#### Mybatis Cache ####

默认情况下是没有开启缓存的,除了局部的 session 缓存,可以增强变现而且处理循环 依赖也是必须的。要开启二级缓存,你需要在你的 SQL 映射文件中添加一行:

	<cache/>

效果如下：

* 映射语句文件中的所有 select 语句将会被缓存。
* 映射语句文件中的所有 insert,update 和 delete 语句会刷新缓存。
* 缓存会使用 Least Recently Used(LRU,最近最少使用的)算法来收回。
* 根据时间表(比如 no Flush Interval,没有刷新间隔), 缓存不会以任何时间顺序 来刷新。
* 缓存会存储列表集合或对象(无论查询方法返回什么)的 1024 个引用。
* 缓存会被视为是 read/write(可读/可写)的缓存,意味着对象检索不是共享的,而 且可以安全地被调用者修改,而不干扰其他调用者或线程所做的潜在修改。

参数如下：

	<cache
	  eviction="FIFO"
	  flushInterval="60000"
	  size="512"
	  readOnly="true"/>

* LRU – 最近最少使用的:移除最长时间不被使用的对象。
* FIFO – 先进先出:按对象进入缓存的顺序来移除它们。
* SOFT – 软引用:移除基于垃圾回收器状态和软引用规则的对象。
* WEAK – 弱引用:更积极地移除基于垃圾收集器状态和弱引用规则的对象。

默认是LRU。

flushInterval(刷新间隔)可以被设置为任意的正整数,而且它们代表一个合理的毫秒 形式的时间段。默认情况是不设置,也就是没有刷新间隔,缓存仅仅调用语句时刷新。

size(引用数目)可以被设置为任意正整数,要记住你缓存的对象数目和你运行环境的 可用内存资源数目。默认值是 1024。

readOnly(只读)属性可以被设置为 true 或 false。只读的缓存会给所有调用者返回缓 存对象的相同实例。因此这些对象不能被修改。这提供了很重要的性能优势。可读写的缓存 会返回缓存对象的拷贝(通过序列化) 。这会慢一些,但是安全,因此默认是 false。

#### 使用自定义缓存 ####

通过实现你自己的缓存或为其他第三方缓存方案 创建适配器来完全覆盖缓存行为。

	<cache type="com.domain.something.MyCustomCache"/>

#### 参照缓存 ####

## 动态SQL ##

### 动态SQL ###

* if
* choose(when,otherwise)
* trim(where,set)
* foreach

#### if ####

#### choose, when, otherwise ####

#### trim, where, set ####

#### foreach ####

动态 SQL 的另外一个常用的必要操作是需要对一个集合进行遍历，通常是在构建 IN 条件语句的时候。

	<select id="selectPostIn" resultType="domain.blog.Post">
	  SELECT *
	  FROM POST P
	  WHERE ID in
	  <foreach item="item" index="index" collection="list"
	      open="(" separator="," close=")">
	        #{item}
	  </foreach>
	</select>

index是当前迭代的次数，item的值是本次迭代获取的元素。当使用字典（或者Map.Entry对象的集合）时，index是键，item是值。collection是指向的数组。

#### bind ####

bind 元素可以从 OGNL 表达式中创建一个变量并将其绑定到上下文

### Multi-db vendor support ###

### 动态 SQL 中可插拔的脚本语言 ###

## JavaAPI ##

## SQL语句构建器 ##

## 日志 ##
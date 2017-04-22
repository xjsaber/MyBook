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

缓存管理器是EhCacheCacheManager

Spring提供了EhCacheManagerFactoryBean来生成EhCache的CacheManager。方法ehcache()会创建并返回一个EhCacheManagerFactoryBean实例。

[官方配置指南](http://www.ehcache.org/generated/2.10.4/html/ehc-all/#page/Ehcache_Documentation_Set%2Fto-cfgbasics_configuring_cache.html%23)

#### 使用Redis缓存 ####

Spring Data Redis提供了RedisCacheManager，这事CacheManager的一个实现。RedisCacheManager会与一个Redis服务器写作，宾馆通过RedisTemplate将缓存条目存储到Redis中。

RedisTemplate bean以及RedisConnectionFactory实现类（如JedisConnectionFactory）的一个bean。

#### 使用多个缓存管理器 ####
CompostieCacheManager要通过一个或多个的缓存管理器来进行配置。迭代JCacheCacheManager、EhCacheCacheManager和RedisCacheManager。

## 13.2 为方法添加注解以主持缓存 ##

注解到方法则只针对单个方法，注解到类则针对该类下面的所有方法。

@Cacheable 表明Spring在调用方法之前，首先应该在缓存中查找方法的返回值。如果这个值能够找到，就会返回缓存的值。否则的话，这个方法就会被调用，返回值会放到缓存之中 @CachePut 不会检查直接写 @CacheEvict 只删
@Cacheing 分组的注解

### 13.2.1 填充缓存 ###

@Cacheable和@CachePut

|属性 | 类型 | 描述 |
|--|--|
|value | String[] | 要使用的缓存的名称|
|condition | String | SpEL表达式，如果得到的值是false的话，缓存不会应用到方法调用上。|
|key | String | SpEL表达式，如果得到的值是false的话，缓存不会应用到方法调用上。 |
|unless | boolean | SpEL表达式，如果得到的值是true的话，返回值不会放到缓存之中。|

#### 将值放到缓存之中 ####

#### 自定义缓存Key ####

SpEL中的可用缓存元数据

|表达式|描述|
|--|--|
|#root.args|传递给缓存方法的参数，形式为数组|
|#root.caches|该方法执行时所对应的缓存，形式为数组|
|#root.target|目标对象|
|#root.targetClass|目标对象的类，是#root.target.class的简写形式|
|#root.method|缓存方法|
|#root.methodName|缓存方法的名字，是#root.method.name的简写形式|
|#result|方法调用的返回值（不能用在@Cacheable注解上）|
|#Argutment|任意的方法参数名（如#argName）或参数索引（#a0或#p0）|

#### 条件化缓存 ####

@Cacheable和@CachePut提供了两个属性实现条件话缓存：unless和condition

condition为false时，缓存是被禁用的。unless属性为true时，阻止将对象放进缓存。 
### 10.2.2 使用数据源连接池 ###

@CacheEvict

|属性 | 类型 | 描述 |
|--|--|
|value | String[] | 要使用的缓存的名称|
|key | String | SpEL表达式，用来计算自定义的缓存key|
|condition | String | SpEL表达式，如果得到的值是false的话，缓存不会应用到方法调用上。 |
|allEntries | boolean | 如果true的话，特定缓存的所有条目都会被移除。|
|beforeInvocation | boolean | 如果为true的话，在方法调用之前移除条目。如果为false(默认值)的话，在方法成功调用之后再移除条目。|

## 13.3 使用XML声明缓存 ##

因为缓存是一种面向切面的行为，所以cache命名空间会与Spring的aop命名空间结合起来使用，用来声明缓存所应用的切点在哪里。

//TODO 以后了解，先放放

## 10.4 小结 ##



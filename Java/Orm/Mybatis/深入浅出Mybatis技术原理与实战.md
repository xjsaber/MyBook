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


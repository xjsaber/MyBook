# 第七章 对JDBC的支持 #

## 7.1 概述 ##

### 7.1.1 JDBC回顾 ###

	try {  
	      conn = getConnection();              //1.获取JDBC连接  
	                                       //2.声明SQL  
	      String sql = "select * from INFORMATION_SCHEMA.SYSTEM_TABLES";  
	      pstmt = conn.prepareStatement(sql);    //3.预编译SQL  
	      ResultSet rs = pstmt.executeQuery();   //4.执行SQL  
	      process(rs);                       //5.处理结果集  
	      closeResultSet(rs);                 //5.释放结果集  
	      closeStatement(pstmt);              //6.释放Statement  
	      conn.commit();                    //8.提交事务  
	} catch (Exception e) {  
	//9.处理异常并回滚事务  
		conn.rollback();  
		throw e;  
	} finally {  
		//10.释放JDBC连接，防止JDBC连接不关闭造成的内存泄漏  
		closeConnection(conn);  
	}  

Spring JDBC提供了一套JDBC抽象框架，用于简化JDBC开发

### 7.1.2  Spring对JDBC的支持 ###
**声明SQL、调用合适的Spring JDBC框架API、处理结果集**
Spring主要提供JDBC模板方式、关系数据库对象化方式和SimpleJdbc方式三种方式来简化JDBC编程，这三种方式就是Spring JDBC的工作模式：
JDBC模板方式：Spring JDBC框架提供以下几种模板类来简化JDBC编程，实现GoF模板设计模式，将可变部分和非可变部分分离，可变部分采用回调接口方式由用户来实现：如JdbcTemplate、NamedParameterJdbcTemplate、SimpleJdbcTemplate。
关系数据库操作对象化方式：Spring JDBC框架提供了将关系数据库操作对象化的表示形式，从而使用户可以采用面向对象编程来完成对数据库的访问；如MappingSqlQuery、SqlUpdate、SqlCall、SqlFunction、StoredProcedure等类。这些类的实现一旦建立即可重用并且是线程安全的。
SimpleJdbc方式：Spring JDBC框架还提供了SimpleJdbc方式来简化JDBC编程，SimpleJdbcInsert 、 SimpleJdbcCall用来简化数据库表插入、存储过程或函数访问。
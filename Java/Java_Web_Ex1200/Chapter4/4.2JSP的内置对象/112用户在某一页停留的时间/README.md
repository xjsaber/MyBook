#Q&A
当用户访问页面时记录用户的访问时间，然后求出当前时间与用户登录时间的时间差，即为用户停留时间。将session的有效活动时间设置为稍大于页面刷新的时间。

1.Date类的构造函数
	
	Date time = new Date()
功能：获取当前时间。

2.Date类的getTime()方法
	
	DateObject.getTime()
功能：获取从1970年1月1日午夜起的毫秒数，返回long类型的数值。

3.Date类的toLocalString()方法

	DateObject.toLocalString()
功能：将时间格式化为本地时间格式，返回值为字符型  
其中参数DateObject为Date类对象。

4.JSP内置对象session的setMaxInactiveInterval()方法
	
	session.setMaxInactiveInterval(int num)
功能：设置session对象的有效活动时间，该时间以秒为单位。  
其中参数num表示设置的秒数。

5.JSP内置对象session的isNew()方法

功能：判断当前用户是否是新用户，返回boolean类型值。  
当用户刷新页面时通过此方法得到的值为false
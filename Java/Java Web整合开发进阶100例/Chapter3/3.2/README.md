#3.2 使用配置文件struts.xml实现页面导航定义

1. structs.xml第一行是所有xml文件都具有的声明。
2. structs.xml文件中所有的属性定义都是以“<structs>”开始，以“</structs>”结束。
3. package中定义了Action映射申明。它也可以包含很多<action>或者一个也不包含。其中name属性内容是开发的web项目名称  
4. Action是之前所述package中包含的Action映射申明。<action>中的name属性是在JSP页面上定义的Action名字。在Structs2中系统主动寻找名字为它的Action，一旦找到就根据class属性中定义的Action类路径去执行该Action类。

result相当于在Structs1的forward属性。因为Action对象都是配置对象，这些配置对象都有唯一的标识，其中name就是标识。通过检索这些标识，Action对象封装了需要指向的URL，系统就会将最后响应信息转到URL所指的JSP页面。也就是代码在<result>和</result>中定义的JSP页面路径。
<%--
  Created by IntelliJ IDEA.
  User: xjsaber
  Date: 3/8/2016
  Time: 8:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*"%>
<html>
<head>
    <title>写入cookie</title>
</head>
<body>
<%
    String[][] userList = {{"mr", "mrsoft"}, {"wgh", "111"}, {"sk", "111"}};    //定义一个保存用户列表的二维数组
    boolean flag = false; //登录状态
    request.setCharacterEncoding("GB18030"); //设置编码
    String username = request.getParameter("name"); //获取用户名
    String pwd = request.getParameter("pwd"); //获取密码
    for (int i = 0; i < userList.length; i++){
        if (userList[i][0].equals(username)){    //用户名
            if (userList[i][1].equals(pwd)) {
                flag = true;    //表示登录成功
                break;
            }
        }
    }
    if (flag){  //如果值为true，表示登录成功
        session.setAttribute("username", username); //保存用户名到session范围的变量中
        response.sendRedirect("main.jsp");
    }
    else{
        response.sendRedirect("index.jsp");
    }
%>
<script type="text/javascript">window.location.href="index.jsp"</script>
</body>
</html>

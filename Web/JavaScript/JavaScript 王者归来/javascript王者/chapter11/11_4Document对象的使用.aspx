<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="11_4Document对象的使用.aspx.cs" Inherits="javascript王者.chapter11._11_4Document对象的使用" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Document对象的使用</title>
</head>
<body>
    Input your name : <input type="text" id="name" value="" />
    <button onclick="Greeting()">Greeting</button>
    <script type="text/javascript">
    <!--
        function Greeting() {
            //打开一个新窗口,讲句柄赋给newWin
            var newWin = window.open();
            //获得id为"name"的DOM元素,这里是指上面的那个input框
            var name = document.getElementById("name");
            with (newWin.document) {
                write("Hello,");
                write("<br/>");
                write(name.value);
            }
        }

        document.alinkColor = "";
        document.linkColor = "";
        document.vlinkColor = "";

        document.bgColor = "";
        document.fgColor = "";
    -->
    </script>
</body>
</html>

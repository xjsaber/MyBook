<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="chapter6.aspx.cs" Inherits="javascript王者.chapter06.chapter6" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
    <div>
    
    </div>
    </form>
    <script type="text/javascript">
        <!--
        function dwn(s) {
            document.write(s + "<br/>");
        }
        function t1() {
            dwn("t1");
        }
        t1();
        function t1() {
            dwn("new t1");
        }
        t1();
        t1 = function() {
            dwn("new new t1");
        }
        t1();
        //声明式的会优先解析,但引用式的不会
        -->
    </script>
</body>
</html>

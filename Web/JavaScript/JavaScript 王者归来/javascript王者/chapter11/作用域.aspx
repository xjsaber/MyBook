<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="作用域.aspx.cs" Inherits="javascript王者.chapter" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form onsubmit="return check(this)">
        <input type="text" name="a" />
        <input type="text" name="b" />
        <input type="text" name="c" />
        <input type="text" name="d" />
        <br /><br />
        <input type="submit" />
    </form>
    <script type="text/javascript">

        //作用域
//        while{
//            var color = "red";
//        }
        // color = "red"
//        if{
//        
//        }
        //虽然都有{}
        //但都是在全局环境下
        //if同理

    </script>
</body>
</html>

<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="11_5_3客户端表单校验.aspx.cs" Inherits="javascript王者.chapter11._11_5_3客户端表单校验" %>

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
        <!--
        function check(form) {
            //遍历form的每一个表单元素
            for (var i = 0; i < form.elements.length; i++) {
                var element = form.elements[i];
                //判断其value是否为空
//                if (/^[\s\n\r\t]*$/.test(element.value)) {
//                    //如果为控则提示返回false
//                    alert(element.name + "不能为空!");
//                    element.focus();
//                    return false;
                //                }
                  if(/^([+-])?(0|([1-9][0-9]*))([.][0-9]+)?$/.test(element.value)){
                    alert(element.name + "必须为数字");
                    element.focus();
                    return false;
                  }
            }
            return true;
                }
        -->
    </script>
</body>
</html>

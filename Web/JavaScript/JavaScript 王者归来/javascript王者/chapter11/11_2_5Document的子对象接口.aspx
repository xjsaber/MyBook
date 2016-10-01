<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="11_2_5Document的子对象接口.aspx.cs" Inherits="javascript王者.chapter11._11_2_5Document的子对象接口" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
    <div>
    <a name="a1">1</a>
    <a name="a2">2</a>
    <a name="a3">3</a>
    </div>
    </form>
    <script type="text/javascript">
        window.onload = function () {
            for (var i = 0; i < document.anchors.length; i++) {
                alert(document.anchors[i].name);
            }
        }
    </script>
</body>
</html>

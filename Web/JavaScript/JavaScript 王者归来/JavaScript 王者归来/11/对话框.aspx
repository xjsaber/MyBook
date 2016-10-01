<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="对话框.aspx.cs" Inherits="JavaScript_王者归来._11.对话框" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
        <button onclick="opennew()">打开</button>
        <script type="text/javascript">
            <!--
            function opennew() {
                var w = window.open("", "main", "height=200,width=400");
                w.document.write("<center><input></input></center>");
                w.focus();
            }
            -->
        </script>
    </form>
</body>
</html>

<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="一个可拖动的窗口.aspx.cs" Inherits="JavaScript_王者归来.一个可拖动的窗口" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
    <div style=" height:600px;">
      <table border="1px" style=" border-style: none; border-color: inherit; border-width: 1px; width:100px; position:absolute; left:50px; top:50px; height: 84px;" 
       onmousemove="f_mdown(this)" onmousedown="f_move(this)" 
            onselectstart="return false">
        <tr><td style="background-color:Orange;cursor:move" align="center"></td></tr>
        <tr><td style="background-color:Blue;height:60px;" align="center" ></td></tr>
      </table>
    </div>
    </form>
  <script type="text/javascript">
  <!--
        var currentMoveObj = null; //当前拖动的对象
        var relLeft;
        var relTop;
        function f_mdown(obj) {           
            //当对象被按下时，记录该对象
            currentMoveObj = obj;
            //setCapture()可以让对象捕捉到鼠标事件，跟随鼠标做出响应
            currentMoveObj.setCapture();
            //设置对象的定位方式为absolute，便于计算拖动位置
            currentMoveObj.style.positon = "abssolute";
            //记录鼠标按下时距离被移动物体左上角的偏移量，以便在移动鼠标的时候更加正确地计算位移
            relLeft = event.x - currentMoveObj.style.pixelLeft;
            relTop = event.y - currentMoveObj.style.pixelTop;
        }
        window.document.attachEvent("onmouseup", function () {
            //releaseCapture()执行与setCapture()相反
            currentMoveObj.releaseCapture();
            currentMoveObj = null;
        })
        function f_move(obj) {
            if (currentMoveObj != null) {
                //当真正移动鼠标的时候，计算被移动物体的实际位置
                currentMoveObj.style.pixelLeft = event.x - relLeft;
                currentMoveObj.style.pixelTip = event.y - relTop;
            }
            else
                alert("去死吧");
        }
   //--!>
</script>
</body>
</html>

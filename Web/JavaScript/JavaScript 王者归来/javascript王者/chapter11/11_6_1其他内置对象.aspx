<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="11_6_1其他内置对象.aspx.cs" Inherits="javascript王者.chapter11._11_6_1其他内置对象" %>

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
        //客户端模拟服务器端的Request发送和获得参数
        (function () {
            //定义外部接口
            //Request开放接口给外部,提供两个接口:getParameter和getParameterValues
            //这样外部的javascript文件就可以通过调用Request.getParameter()来执行相应的动作
            var Request = {
                getParameter: getParameter,
                getParameterValues: getParameterValues
            };
            //得到URL后的参数, 例如URL:http://abc?x=1&y=2
            //getParameter("x") 得到1
            function getParameter(paraName, wnd) {
                //如果不提供wnd,则默认当前页面
                if (wnd == null) {
                    wnd = self;
                }
                //得到地址栏上"?" 后边的字符串
                var paraStr = wnd.location.search.slice(1);
                //根据"&"符号分割字符串
                var paraList = paraStr.split(/\&/g);
                for (var i = 0; i < paraList.length; i++) {
                    //用正则表达式判断字符串是否是"paraName=value"的格式
                    var pattern = new RegExp("^" + paraName + "[?=\\=]", "g");
                    if (pattern.test(paraList[i])) {
                        //若是,则返回解码后的value的内容
                        return decodeURIComponent(paraList[i].split(/\=/g)[1]);
                    }
                }
            }

            //如果有多重复的paraName的情况下, 下面方面则返回一个包含了所有所有值的数组
            //例如http://abc?x=1&x=2&x=3
            function getParameterValues(paraName, wnd) {
                if (wnd == null) {
                    wnd = self;
                }
                var paraStr = wnd.location.search.slice(1);
                var paraList = paraStr.split(/\&/g)
                
            }
        })
    </script>
</body>
</html>

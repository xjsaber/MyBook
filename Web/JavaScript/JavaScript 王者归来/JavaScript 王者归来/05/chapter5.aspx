<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="chapter5.aspx.cs" Inherits="JavaScript_王者归来.第五章.chapter5" %>

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
//        进制转换
//        var x = 33;
//        var y = x.toString(2);
//        alert(y);

//        var str = 'www.51js.com';
//        var pos = str.indexOf('.');
//        document.writeln(str.substring(pos + 1) + "<br/>");
//        var parts = str.split(".");
        //        document.write(parts[1] + "<br/>");

       // var a = [1.2, 'javascript', true, { x: 1, y: 2 }, [1, 2, 3]];

//        var point = { x: 1, y: 2 };
//        var rectangle = {
//            upperLeft: point,
//            lowerRight: { x: point.x + 4, y: point.y + 2 },
//            Area: function () {
//                var width = this.lowerRight.x - this.upperLeft.x;
//                var height = this.lowerRight.y - this.upperLeft.y;
//                return width * height;
//            }
//        }
        //        alert(rectangle.Area());

        //闭包
//        function parabola(a, b, c) {
//            var ret = function (x) {
//                return a * x * x + b * x + c;
//            }
//            ret.toString = function () {
//                return a + "x^2+" + b + "x+" + c;
//            }
//            return ret;
//        }
//        var p1 = parabola(2, 3, 4);
//        document.write(p1 + " ->" + p1(15) + "<br/>");
//        var p2 = parabola(2, -3, 14);
//        document.write(p2 + " ->" + p2(15));

        //装箱,拆箱
//        var a = 10, b = 'JavaScript ', c = true;
//        var o_a = new Number(a);
//        var o_b = new String(b);
//        var o_c = new Boolean(c);

        //运行时类型识别
//        Object.prototype.InstanceOf = function (type) {
//            try {
//                //typeof,conhstructor或者instanceof三个条件之一满足,就返回true,否则返回false
//                return typeof (this) == type ||
//                    this.constructor == type ||
//                        this instanceof type;
//            }
//            catch (ex) {
//                return false;
//            }
//        }
//        var a = new Object();
//        document.write(a.InstanceOf(Object) + "<br/>");
//        var b = "abc";
//        document.write(b.InstanceOf(String) + "<br/>");
//        var c = new String("abc");
//        document.write(c.InstanceOf(String) + "<br/>");
//        var d = new Array();
//        document.write(d.InstanceOf(Array));

//        function $() {
//            var _args = Array.apply([], arguments);
//            if (_args.length == 0) {
//                return $void;  //如果不带参数,返回一个空函数
//                //arguments.length 可以得到实参的个数
//            }
//            if (_args.length == 1) {
//                var obj = $id(_args[0]) || _args[0];
//                if (obj instanceof Iterator) //啥意思? 如果对象是一个迭代器对象,转为数组
//                    return obj.toArray();
//                if (obj.length) {
//                    var _set = [];
//                    for (var i = 0; i < obj.length; i++) {
//                        _set.push(obj[i]);
//                        return _set;  //转化为ArrayList
//                    }
//                    return obj;
//                }
//            }
//            return _args.each(function (obj) {
//                return $id(obj) || obj;
//            });
//        }
//        function $id(id) {
//            return document.getElementById(id);  //根据id返回dom对象
        //        }

        function dwn(s) {
            document.write(s + "<br/>");
        }
        //自定义了一个Double 类型用来表示双精度浮点数
        function Double(value) {
            this.valueOf = function ()  //重新定义valueOf() 方法
            {
                return value;
            }
            this.doubleValue = this.valueOf;
            this.toString = function ()  //重新定义toString()方法
            {
                return "jsDouble:" + this.valueOf();
            }
        }
        var d = new Double(10.11);
        dwn(d);
        dwn(String(d));
        dwn(d + 10);
        dwn(d + "abc");
    </script>
</body>
</html>

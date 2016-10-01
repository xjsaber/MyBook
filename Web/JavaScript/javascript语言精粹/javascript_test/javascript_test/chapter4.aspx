<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="chapter4.aspx.cs" Inherits="javascript_test.chapter4" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
    <script type="text/javascript">
        //Object.prototype

//        var sum = function () {
//            var i, sum = 0;
//            for (i = 0; i < arguments.length; i++) {
//                sum += arguments[i];
//            }
//            return sum;s
//        }
//        alert(sum(1, 2, 3))
        //arguments 类似函数自带的数组

//        var add = function (a, b) {
//            if (typeof a !== 'number' || typeof b != 'number') {
//                throw {
//                    name: 'TypeErrow',
//                    message: 'add needs numbers'
//                };
//                return a + b;
//            }
//        }
//        alert(add('2', 'b'));
//        alert(add(2, 3));

//        Function.prototype.method = function (name, func) {
//            this.prototype[name] = func;
//            return this;
//        }

//        Number.method("integer", function () {
//            return Math[this < 0 ? 'ceil' : 'floor'](this);
//        });

//        document.writeln((3.50).integer());

//        String.method('trim', function () {
//            return this.replace(/^\s+|\s+$/g, '');
//        });

        //        document.writeln("             " + "   asdasd asdasd    asdasd   ".trim());

        var hanoi = function (disc, src, aux, dst) {
            if (disc > 0) {
                hanoi(disc - 1, src, aux, dst);
                document.writeln('Move disc ' + disc + ' from ' + src + ' to ' + dst);
                hanoi(disc - 1, src, aux, dst);
            }
        }

        hanoi(3, 'src', 'aux', 'dst');
    </script>
</head>
<body>
    <form id="form1" runat="server">
    <div>
    
    </div>
    </form>
</body>
</html>

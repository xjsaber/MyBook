<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="chapter5.aspx.cs" Inherits="javascript_test.chapter5" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
    <script type="text/javascript">
//        Function.method('new', function () {
//            //创建一个新对象,它继承自构造器函数的原型对象
//            var that = Object.beget(this.prototype)
//            //调用构造器函数, 绑定 -this- 到新对象上
//            var other = this.apply(that, arguments);
//            //如果它的返回值不是一个对象, 就返回该新对象
//            return (typeof other = 'object' && other) || that;
//        });

//        var Mammal = function (name) {
//            this.name = name;
//        };
//        Mammal.prototype.get_name = function (){
//            return this.name;
//        };
//        Mammal.prototype.says = function () {
//            return this.saying || '';
//        };
//        var myMammal = new Mammal('Herb the Mammal ');
//        var name = myMammal.get_name();
//        alert(name);

//        var Cat = function (name) {
//            this.name = name;
//            this.saying = 'meow';
//        }
//        //替换Cat.prototype 作为 
//        Cat.prototype = new Mammal();
//        //function b(x, y, a) { arguments[2] = 10; alert(a); } b(1, 2, 3);
//        //function a(x) { return x * 2; } var a; alert(a);
//        //扩充新原型对象,增加purr和get_name方法
//        Cat.prototype.purr = function (n) {
//            var i, s = '';
//            for (i = 0; i < n; i++) {
//                if (s) {
//                    s += '-';
//                }
//                s += 'r';
//            } 
//            return s;
//        }
//        Cat.prototype.get_name = function () {
//            return this.says() + ' ' + this.name +
//                ' ' + this.says();
//        }
//        var myCat = new Cat('Henrietta');
//        var says = myCat.says(); //'meow'
//        var purr = myCat.purr(5); //'r-r-r-r-r'
//        var name = myCat.get_name();
        //         'meow Henrietta  meow'
//        document.writeln(purr);

//        Function.method('inherits', function (Parent) {
//            this.protoType = new Parent();
//            return this;
//        });

//        var myObject = maker(f, l, m, c, s);
//        var myObject = maker({
//            first: r,
//            last: l,
//            middle: m,
//            city: c,
//            state: s
        //        });
    
//        Object.beget = function (o) {
//            var F = function (o) { };
//            F.prototype = o;
//            return new F;
//        };         
//        var myMammal = {
//            name: 'Herb the Mammal',
//            get_name: function () {
//                return this.name;
//            },
//            says: function () {
//                return this.saying || '';
//            }
//        };
//        var myCat = Object.beget(myMammal);        
//        myCat.name = 'Henrietta';
//        myCat.saying = 'meow';
//        myCat.purr = function (n) {
//            var i, s = '';
//            for (i = 0; i < n; i += 1) {
//                if (s) {
//                    s = s + '-';
//                }
//                else {
//                    s = s + 'r';
//                }
//            }
//            return s;
//        };
//        myCat.get_name = function () {
//            return this.says() + ' ' + this.name + ' ' + this.says();
//        };
        //        document.writeln(myCat.get_name());

//        var block = function () {
//            //记住当前的作用域,构造一个包含了当前作用域中所有对象的新作用域
//            var oldScope = scope;
//            scope = Object.beget(scope);
//            //传递左花挂号作为参数调用advance
//            advance('{');
//            //使用新的作用域
//            parse(scope);
//            //传递右花括号作为参数调用advance并抛弃新作用域
//            advance('}');
//            scope = oldScope;
//        };
        //parse 解析

        var constructor = function (spec, my) {
            var that, 其他的私有实例变量;
            my = my || {};
            把共享的变量和函数添加到my中
            that = 一个新对象
            添加给that的特权方法
            return that;
        }
    </script>
</head>
<body>
    <form id="form1" runat="server">
    <div>
    
    </div>
    </form>
</body>
</html>

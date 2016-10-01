<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="通用表单校验组件.aspx.cs" Inherits="javascript王者.chapter11.通用表单校验组件" %>

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
        function Validate(pettern, msg) {

        }
        Validate.prototype = new Attribute();
        function ValidateAttribute(pattern, msg) {

        }

        //封装未能通过校验的对象
        function ValidatePack(target, validate, checkValue) {
            //校验对象本身
            this.target = target;
            //校验规则
            this.validate = validate;
            //被检查的值
            this.checkValue = checkValue;

            //默认的生成出错提示信息的规则
            ValidatePack.prototype.getMessage = function () {
                if (this.targer.errorMsg != null) {
                    return this.target.errorMsg;
                }

                var msg = "";

                if (this.target.tag != null) {

                }
                else {

                }
                if (this.checkValue == true) {

                }
                else if (this.checkValue == false) {

                }
                else {

                }

                return msg;
            }
        }

        //封装校验事件的参数,这个参数会伴随着校验对象产生的事件而传递
        //上一小节最后例子中的自定义处理函数就需要接收这个事件参数进行处理
        function ValidateEventArgs() {
            //存放校验结果的数组,这个数组中包含所有的校验失败的ValidatePack对象
            this.result = new Array();
            //存放所有校验对象的数组
            this.checkedObjects = new Array();

            //默认的结果展示方法
            ValidateEventArgs.prototype.getResult = function () {
                var msg = "";
            }
        }

        function Validator() {
            //用来存放校验结果的堆栈
            this.validateResult = new ValidateEventArgs(); // new Array();
            //用来存放已校验对象的队列
            this.checkObjects = new Array();

            //检查并执行一段表达式
            var $eval = function (expr) {
                try {
                    return eval(expr);
                }
                catch (e) {
                    return expr;
                }
            }
        }

        Validator.prototype.checkPassedEvent = function (sender, event) {
            if (sender.onCheckPassed != null) { 

            }
        }
    </script>
</body>
</html>

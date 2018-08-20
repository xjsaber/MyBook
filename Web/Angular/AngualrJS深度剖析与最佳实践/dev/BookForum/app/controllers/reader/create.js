//引用应用模块
angular.module("com.xjsaber.angular")
    //注册一个控制器
    .controller(
        //控制器名称
        'ReaderCreateCtrl',
        //控制器实现
        function ReaderCreateCtrl(){
            // vm语法中，当前函数的this指针指向的是$scope.vm变量，作为一项约定和最佳实践，把它赋值给vm变量。
            var vm = this;
        });
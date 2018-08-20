'use strict';

// 这里只能注入constant和各种Provider。本阶段主要用于通过Provider对各种服务进行配置。
// 引用模块——已经在app.js中创建过了，所以这里只需要引用
// 声明config函数，它的参数是一个回调函数，这个回调函数将在模块加载时运行，以便对模块进行配置，路由就是配置的一种。
angular.module('app').config(
    //声明config回调函数，它需要两个参数，一个叫做$stateProvider，一个叫$urlRouterProvider，这两个都是第三方模块angular-ui-router提供的服务。注意，这些参数不可随便修改
    function ($stateProvider, $urlRouterProvider) {
        // 声明一个state
        $stateProvider.state(
            // 路由名称
            'home',
            // 路由定义对象
            {
                //URL
                url: '/',
                // 模板所在路径，从app起算
                templateUrl: "controller/home/index",
                // 控制器名称
                controller: "HomeIndexCtrl as vm"
            });
            // 定义 “页面未找到”路由
            $stateProvider.state('notFound', {
                url: '/notFound',
                templateUrl: 'controller/home/notFound.html',
                controller: 'HomeNotFoundCtrl as vm'
            });
            // 定义默认路由，即遇到未定义过的URL时跳转到那里
            $urlRouterProvider.otherwise('/notFound')
            // 自己的路由
        // 定义一个父路由，只用于提供URL
        $urlRouterProvider.state('reader', {
            // 所有子路由都会继承这个URL
            url: "/reader",
            // 父路由中一般只要提供一个这样的template就够了，不必使用templateUrl，页面中公共的部分通过组件型指令去实现会更灵活、更漂亮
            template: "<div ui-view></div>",
            // 抽象路由不能通过URL直接访问，比如直接访问/reader路径会跳转到otherwise中去
            abstract: true
        });
            // 定义一个子路由
        $urlRouterProvider.state(
            // 名称，注意，这个名称是随便取，angular-ui-router会使用“点”对其进行分割，并且从前往后逐个执行，所以这个名称中的每一段都要存在
            'reader.create', {
                // 子路由的路径，angular-ui-router会把各级父路由与当前路由的URL组合起来，作为最终的访问路径，如：'/reader/create'
                url: '/create',
                // 子路由模板
                templateUrl: 'controllers/reader/create.html',
                // 子路由控制器
                controller: 'ReaderCreateCtrl as vm'
            }
        )
});

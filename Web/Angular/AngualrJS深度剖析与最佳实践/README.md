# AngualrJS深度剖析与最佳实践 #

## 第1章 从实战开始 ##

### 1.1 环境准备 ###

1. Node
2. cnpm
3. Java
4. IntelliJ
5. IntelliJ的AngularJS插件
6. Git
7. cygwin
8. 开发智能与API

### 1.2 需求分析与迭代计划 ###

https://github.com/ng-nice/code

1. 系统隐喻
2. 业务目标
3. 需求分析


### 1.3 创建项目 ###

#### 1.3.1 Yeoman ####

	cnpm install -g yo //yeoman
	cnpm install -g generator-gulp-angular@0.8.1 //angualr的项目模版
	cnpm install -g gulp bower
	
	yo gulp-angular@0.8.1

#### 1.3.2 FrontJet ####

1. 使用FrontJet创建项目
2. 启动开发服务器
3. 项目结构

### 1.4 实现第一个页面：注册 ###

#### 1.4.1 约定优于配置 ####

将reader称为controller，create称作action，中间还可以有一个id。

	/$controller/:id?/$action，其中的id字段是可以神略的，取决于具体的action。

拿到一个URL，如/reader/1/edit，其中reader是$controller，edit是$action，其模版为edit.html，控制器为edit.js，样式为edit.scss。

#### 1.4.2 定义路由 ####

	app/config.router.js

如果已经有UX（用户体验设计师）或BA（业务分析师）给出的原型图
，俺么建议从设计Model的数据结构开始，这样有助于更深入的理解Angular开发中最显著的特点：模型驱动。

#### 1.4.3 把后端程序跑起来 ####

#### 1.4.4 连接后端程序 ####

5000端口跑前端程序，在5080端口上跑着后端程序，并且通过FrontJet的反向代理功能把后端也代理到了5000端口下，从而避免了跨域。

#### 1.4.5 添加验证器 ####

数据验证

#### 1.5.6 实现“查看详情”功能 ####

### 1.6 实现AOP功能 ###

#### 1.6.1 实现登录功能 ####

传统登录

1. 浏览器访问一个网址。
2. 服务器判断这个网址是否需要登录才能访问。
3. 如果需要，则给浏览器回复一个Redirect头。
4. Redirect的地址是的呢公路也，地址中还带有一个登录成功后的回调地址。
5. 浏览器把登录页显示给用户，用户输入有效的用户名密码之后提交到服务器。
6. 服务器检查用户密码是否有效，如果有效则给浏览器回复Redirect头来跳转到回调地址。

Ajax登录

1. 前端发起一个请求。
2. 服务端判断这个网址是否需要登录才能访问。
3. 如果需要，则给前端发回一个状态嘛401（位认证身份，即：未登录）
4. 前端收到01状态码，则弹出一个对话框，提示用户输入用户名和密码。
5. 用户输入用户名和密码之后提交。
6. 前端发起一个登录请求，并等待登录成功。
7. 登录成功后，前端重新发送刚才被拒绝的请求。

优点：

* 把控制逻辑完全交给前端，后端只提供“纯业务API”就勾勒，这样钱后端的分工非常明确。
* 完全在当前页面中执行，不用多次加载页面用户操作非常顺畅。
* 在Promise机制的支持下，登录过程中对前端的应用逻辑可以是完全透明的（调用API的代码不需要区分中间是否发生过，也不需要对此做任何处理）。

## 第2章 概念介绍 ##

### 2.1 什么是UI ###

* 内容
* 外观
* 交互

对应Angular中的概念，“静态内容”对应模版，“动态内容”对应Scope，交互对应Controller，外观部分略微复杂点：CSS决定样式，过滤器（filter）则决定格式。

### 2.2 模块 ###

所谓模块是指相关的一组编程元素（如类、函数、变量等）组织到同一个发布包。这些编程元素之间紧密协作，隐藏实现细节，只通过公开的接口与其他模块合作。

angular.module('com.ngice.app')：引用惩恶模块，查找一个名叫app的模块并返回其引用，如果模块不存在，则会触发一个异常[$injector:nomod]
angular.module('com.ngince.app', ['common'])：创建一个模块，并且声明这个模块依赖一个名为common 的模块，第二个参数是个数组，所以还可以声明依赖多个模块。

### 2.3 作用域 ###

使用了原型继承的方式，凡是上级scope拥有的属性，都可以从下级scope中读取，但是当需要对这些继承下来的属性进行写入的时候。问题来了：*写入会导致在下级scope上创建一个同名属性，而不是修改上级scope上的属性*。

Controller操作scope，View则展现scope的内容，传统前端程序中大量复杂的DOM操纵逻辑都被转变成scope的操作。

### 2.4 控制器 ###

Angular的控制器（controller）用来对模块（scope）进行操作，包括初始化数据和定义事件响应函数。

	angular.module('com.ngnice.app').controller('UserListCtrl', function() {......})；
其中：angular.module('com.ngnice.app')返回一个现有的module实例，而Controller就是这个module实例上的一个方法，作用是把后面的函数以UserListCtrl为名字，注册到模块中去，以便需要时可以根据名字找到他。

### 2.5 视图 ###

Angular中实现的视图的主题是模版。最常见的模版形式当然是HTML，也有通过Jade等中间语言编译为HTML的。模版中包括静态信息和动态信息，静态信息是指直接写死（hard code）在模版中，而动态信息则是对scope中内容的展示。

展示动态信息的方式有两种：

* 绑定表达式：形式如{sername}，绑定表达式可以出现在HTML中的文本部分或节点的属性部分。
* 指令：形式如<span ng-bind="username"></span>，事实上任何指令都可以用来展示动态信息，展示的方式取决于指令的内在实现逻辑。

### 2.6 指令 ###

指令（directive），相当于一个自定义的HTML元素，在Angular官方文档中称它为HTML语言的DSL（特定领域语言）扩展。

按照使用场景和作用可以分为两类类型的指令：组件型指令（Component）和装饰型器指令（Decorator）。

组件型指令主要是为了将复杂而庞大的View分离，使得页面的View具有更强的可读性和维护性，实现“高内聚低耦合”和“分离关注点”的有效手段；而装饰器型指令则是为DOM添加行为，使其具有某种能力，如自动聚焦（autoFocus）、双向绑定，可点击（ngClick）、条件显示/隐藏(ngShow/ngHide)等能力，同时它也是链接Model和View之间的桥梁，保持View和Model的同步。在Angular中内置的大多数指令，是属于装饰期型指令，他们负责收集和创建$watch，然后利用Angular的“脏检查机制”保持View的同步。

#### 2.6.1 组件型指令 ####

PS.拆除两个指令的直接目的不是为了复用，而是分离View，促进代码结构的优化。

	//声明一个指令
	angular.module('com.ngnice.app').directive('jobCategory', function() {
		return {
			//可以用作HTML元素，也可以用作HTML属性
			restrict: 'EA',
			//使用独立作用域
			scope: {
				configure: '='
			},
			//指定模板
			templateUrl: 'components/configure/tree.html',
			//声明指令的控制器
			controller: function JobCategoryCtrl($scope) {
				...
			}
		}
	}
指令中return，称之为“指令定义对象”。

restrict属性用来表示这个指令的应用放肆，取值可以是E（元素）、A（属性）、C（类名）、M（注释）

scope有三种取值：不指定（undefined）/false、true或一个哈希对象。

不指定或为false时，表示这个指令不需要新作用域。直接访问现有作用域上的属性或方法，也可以不访问作用域。如果同一节点上有新作用域或独立作用域指令，则直接法师用它，否则直接使用父级作用域。

#### 2.6.2 装饰器型指令 ####

	angular.module("com.ngince.app').directive('twTitle', 	function(){
			return {
				//用作属性
				restrict: 'A',
				link: function(scope, element, attrs){
					...
				}
			}
		}
	}

### 2.7 过滤器 ###

filter

	angular.module('com.ngnice.app').filter('myFilter', function(){
	    /* 这里可以用参数进行依赖注入 */
	    return function(input) {
	
	        var result;
	        //TODO：把input变换成result
	        return result;
	    };
	});

非常适合复用。

	filter出了可以用在绑定表达式之外，可以用在指令中通过值绑定的属性，如<li ng-repeat="item in items|filter: 'a'">....</li>

### 2.8 路由 ###

前端“路由”(router)的概念和后端的路由是一样的，也就是根据URL找到view-controller组合的机制。Angular的路由库合并在核心库中，现在，路由库从Angular核心库中剥离出来。官方的路由库称为ngRoute，由于其过于简陋，比较常用的是一个第三方路由库：angular-ui-router。

ngRoute的写法是：

	$routeProvider.when("/url", {
		templateUrl: 'path/to/template.html',
		controller: 'SomeCrl'
	});

angular-ui-router

	$stateProvider.state('name', {
		url: '/url',
		templateUrl: 'path/to/template.html',
		controller: 'SomeCrl'
	})

原理：监听$locationChangeSuccess事件，将在每次URL（包括#后面的hash部分）发生变化时触发。将根据$routeProvider/$stateProvider中注册的路由表中的URL部分查询其路由对象。
	
	{
		url: '/url',
		templateUrl: 'path/to/template.html',
		controller: 'SomeCtrl'
	}

* 创建一个scope对象。
* 加载模板，借助浏览器的能力把它解析为静态DOM。
* 使用Controller对scope进行初始化，添加属性和方法。
* 使用$compile服务把刚才生成的DOM和scope关联起来，变成一个Live DOM。
* 用这个Live DOM替换成ng-view/ui-view中的所有内容

### 2.9 服务 ###

服务是对公共代码的抽象，比如，如果在多个控制器中都出现了相似的代码，那么把它们提取出来，封装成一个服务，将更加遵循DRY原则（即：不要重复）

#### 2.9.1 服务 ####

	angular.module('com.ngnice.app').service('greeting', function() {
		this.sayHello = function(name) {
			return 'Hello, ' + name;
		};
	});

等价于：

	angular.module('com.ngnice.app').provider('greeting', function() {
		this.$get = function() {
			var Greeting = function(){
				this.sayHello = function(name){
					return 'Hello,' + name;
				};
			};
			return new Greeting();
		}
	};

使用时：

	angualr.module('com.ngnice.app').controller('SomeCtrl', functon($scope, greeting) {
		$scope.message = greeting.sayHello('world');
	});


#### 2.9.2 工厂 ####

	angular.module('com.ngnice.app').factory('greeting', function() {
		return 'Hello, world';
	});

等价于：

	angular.module('com.ngnice.app').provider('greeting', function() {
		this.$get = function(){
			var greeting = function(){
				return 'Hellom world';
			}
			return greeting();
		}
	});

使用时：

	angualr.module('com.ngnice.app').controller('SomeCtrl', functon($scope, greeting) {
		$scope.message = greeting;
	});

Angular提供多种形式的服务：

1. 需要全局的可配置参数？用Provider。
2. 是纯数据，没有行为？用Value。
3. 只new一次，不用参数？用Service。
4. 拿到类，自己new出实例？用Factory。
5. 拿到函数，自己调用？用Factory。

更加敏捷的方法：

1. 纯数据时，先用Value；当发现需要添加行为时，改写为Service；或当发现需要通过计算给出结果时，改写为Factory；当发现需要进行全局配置时，改写为Provider。

|类型|Factory|Service|Value|Constant|Provider|
|--|--|--|--|--|
|可以依赖其他服务|是|是|否|否|是|
|使用类型友好的注入|否|是|是|是|否|
|在config阶段可用|否|否|否|是|是|
|可用于创建函数/原生对象|是|否|是|是|是|

### 2.10 承诺 ###

流行的库叫做Q（https://github.com/kriskowal/q）。而Angular的$q就是从它引入的。

**1. 生活中的一个例子**

Promise解决的是异步编程的问题。


**2. 回调地域和Promise**

Promise在任何时刻都处于以下三种状态之一：未完成（pending）、已完成（resolved）和拒绝（rejected）三个状态。以CommonJS Promise/A标准为例，Promise对象上的then方法负责添加针对已完成和拒绝状态下的处理函数。then方法会返回一个Promise对象，以便于形成Promise管道，这种返回Promise对象的方式能够让开发人员把异步操作串联起来，如then(resolvedHandler，rejectedHandler)。resolvedHandler回调函数在Promise对象进入完成状态时会触发，并传递结果；rejectedHandler函数会在拒绝状态下调用。

	async1().then(async2).then(async3).catch(showError);

**3. Angular中的Promise**

Promise，最简单的是$timeout的实现。

	function timeout(fn, delay, invokeApply) {
		//创建一个延期请求
		var deferred = $q.defer(),
			promise = deferred.promise,
			skipApply = (isDefined(invokeApply) && !invokeApply),
			timeoutId;
			timeoutId = $browser.defer(function(){
				try {
					//成功，将触发then的第一个回调函数
					deferred.resolve(fn());
				} catch(e) {
					//失败，将触发then的第二个回调函数或catch的回调函数
					deferred.reject(e);
					$exceptionHandler(e);
				} finally{
					delete deferreds[promise.$$timeoutId];
				}
				if (!skipApply)$rootScope.$apply();
			}, delay);
			promise.$$timeoutId = timeoutId;
			deferreds[timeoutId] = deferred;
			//返回承诺
			reutrn promise;
	}
	timeout.cancel = function(promise){
		if(promise && promise.$$timeoutId in deferreds){
			deferreds[promise.$$timeoutId].reject('canceled');
			delete deferreds[promise.$$timetoutId];
			return $browser.defer.cancel(promise.$$timeoutId);
		}
		return false;
	}



### 2.11 消息 ###

消息（message）机制非常有用，特别是消息冒泡机制，让我们不用额外的代码可以实现“职责链”模式。

* $broadcast(name, args):向当前scope及其所有下级scope递归广播名为name的消息，并带上args参数。
* $emit(name, args):向当前scope及其所有直线上级scope发送名为name的消息，并带上args参数。
* $on(name. listener):监听本scope收到的消息，listener的形式为：function(event, args){}, event参数的结构和DOM中的event类似。

当rootScope上调用$broadcast广播一个消息时，任何一个scope（包括rootScope）上通过$on注册的listener都将收到这个消息。当scope1上调用$broatcast广播一个消息时，scope1/scope1.1/scope1.2将依次收到这个消息。当我们在rootScope上调用$emit上传一个消息时，rootScope将收到这个消息。当我们在scope1.1上调用$emit上传一个消息时，scope1.1/scope1/rootScope将依次收到这个消息。

当通过$emit上传一个消息时，将使用冒泡机制，比如，假设我们在scope1.1调用$emit，我们在scope1上注册一个listener

	scope1.$on('name', funcction(event)) {
		event.stopPropagation();
	}
scope1.1和scope1都将正常收到这个消息，但rootScope就接收不到这个消息了。


### 2.12 单元测试 ###

#### 2.12.1 MOCK的使用方式 ####

Mock一个普通对象不需要进行特别处理。比如，如果一个测试函数需要

* 网络的不稳定性
* 网络响应的速度会拖慢整体速度。
* 网络的异步性

#### 2.12.2 测试工具与断言库 ####

Test Runner

Karma

jasmine

### 2.13 端到端测试 ###

端到端测试（e2e test）， 也称为场景测试。

* 用户打开http://xxx地址。
* 在搜索框中输入了abc。
* 然后点击其后的搜索按钮。

1)

2)

## 第3章 背后的原理 ##

### 3.1 Angular中的MVVM模式 ###

* 低耦合：View可以独立于Model变化和修改，同一个ViewModel可以被多个View复用；并且可以做到View和Model的变化互不影响。
* 可重用性：可以把一些视图的逻辑放在ViewModel，让多个View复用。
* 独立开发：开发人员可以专注于业务逻辑和数据的开放（ViewModel），界面设计人员可以专注于UI（View）的设计
* 可测试性：清晰的View分层，使得针对表现层业务逻辑的测试更容易，更简单。

* View：专注于界面的显示和渲染，在Angular中则是包含一堆声明式Directive的视图模板。
* ViewModel：它是View和Model的粘合体，负责View和Model的交互与协作，它负责给View提供显示的数据，以及供View操作Model的途径。在Angular中$scope对象充当了这个ViewModel的角色，ViewModel上有两种不同来源的数据：一种是展示信息的业务数据，另一种是描述交互的派生数据，如：表格上的复选框，如果点击“全选”则会选中所有列表中的复选框，在这里就需要一个类似“isSelectAll”的派生数据被放置在ViewModel上。
* Model：它是与业务逻辑相关的数据封装载体，也就是领域对象。Model不应该包含任何与界面显示有关的逻辑。在Web页面中，大部分Model都是来自Ajax的服务端返回数据或者全局配置对象。Angular中的Service正是封装和处理这些与Model相关的业务逻辑的最佳方式，这些领域对象可以被Controller或其他Service复用。
* Controller：这并不是MVVM模式中的核心元素，但它负责ViewModel对象的初始化。它将调用一个或者多个Service来获取领域对象，并把结果放在ViewModel对象上。这样，应用界面在启动加载时候，可以达到一种最初的可用状态。它还可以在ViewModel上加入用于描述交互的行为函数，如用于响应ng-click事件的"addItemTOShopCar();"等

View不能直接与Mode交互，而是通过$scope这个ViewModel来实现与Model的交互。

**1. 绝不要先设计你的页面，然后用DOM操作去改变它。**

jQuery: 首先设计页面DOM结构，然后再利用jQuery来改变DOM结构或者实现动态交互效果。对于负责交互逻辑的项目，JavaScript代码会变得越来越臃肿，让交互逻辑分散到各处。

Angular：拥有或者需要怎么样的Model数据，然后设计交互数据和交互逻辑，最后采取实现视图，并用$scope来粘合他们。

**2. 指令不是封装jQuery代码的“天堂”**

封装到Angular的指令中

**3. Angular启动过程**

1. 浏览器下载HTML/CSS/JavaScript等

2. 浏览器开始构建DOM树

3. jQuery初始化

4. Angular初始化

5. jQuery启动

6. Angular启动

7. 加载子模块

8. 启动子模块

9. 渲染页面

10. 数据绑定与摘要循环

Angular会给每一个Scope成员变量求出一个摘要值（能够唯一标识一个变量），并在保存在一个变量中。当调用Scope对象的$digest/$apply方法的时候，会重新算一遍摘要值，只要数据变化

### 3.3 依赖注入 ###

DI

#### 3.3.1 什么是依赖注入 ####

**1. 自己创建它**

需要一系列参数，可能还需要这样、那样的初始化操作，甚至可能还需要创建它的相关的对象，相关对象的相关的对象。

这种方法无法适应复杂的对象，随着对象的复杂化，自己动手丰衣足食已经不再是好的选择。

**2. 使用者从全局注册表查阅它**

对象的实现者自己负责创建对象，然后对象注册到某个全局注册表，需要它的时候，就去注册表根据名字或者其他特征（比如强类型语言中的类名）查询：obj=globalRegistry.get(objectId)。

全局变量是单元测试的魔鬼，因为会让各个“单元”互相耦合在一起，那将是单元测试的噩梦。

**3. 衣来伸手，饭来张口**

在函数的参数中声明。目前Angular所采用的反射光hi是函数的参数形式，和一种特殊的Annotation形式来防止代码压缩过程破坏参数名。而Angular2.0中会使用新的语言特性强化Annotation的方式。

#### 3.3.2 如何在JavaScript中实现DI ####

	// giveMe函数声明了一个叫config的参数，希望容器根据这个名字找到同名对象，并且注入进来
	var giveMe = function(config){
	    //经过注入后，此处config的内容为{delay: 1}
	    //跟registry中保存的是同一个实例
	};
	//注册表，这里保存了可注入对象，包括一个名为config的对象
	var registry = {
	    config: {
	        delay: 1
	    }
	}
	
	var inject = function(func, thisForFunc){
	    var sourceCode = func.toString();
	    var matcher = sourceCode.match();
	    var objects = [];
	    for (var i = 0; i < objectIds.length; i++){
	        var objectName = objectIds[i];
	        var object = registry[objectName];
	        objects.push(object);
	    }
	    func.apply(thisForFunc || func, objects);
	};

#### 3.3.3 Angular中的DI ####

minify

#### 3.3.4 DI与minify ####

### 3.4 赃检查机制 ###

“脏检查”是Angular中的核心机制之一，实现双向绑定、MVVM模式的重要基础。

Angular将双向绑定转换为一堆watch表达式，然后递归检查这些watch表达式的结果是否变了，如果变了，则执行相应的watcher函数。等到Model的值不再变化，也就不会再有watcher函数被触发，一个完整的digest循环就结束了。这时，浏览器就会重新渲染DOM来体现model的改变。

#### 3.4.1 浏览器事件循环和Angular的MVW ####

JavaScript是靠事件循环工作的。浏览器中存在一个事件循环池，它被设计成一个无限循环以保持执行过程的可用，等待事件（例如layout、paint、用户点击事件、交互控件的键盘事件等）并执行它们。

#### 3.4.2 Angular中的$watch函数 ####

大部分指令都会依赖watcher函数来监听Model的改变，以更新View的显示，它是Angular中“脏检查机制”的核心之一。


#### 3.4.3 Angular的$digest函数 ####

当接受View的事件指令所转发的事件时，就会切换到Angular的上下文环境，来响应这类事件，$digest循环就会触发。

$digest循环发生包括两个while循环，它们分别是：处理$evalAsync的异步运算队列，处理$watch的watchers队列。

当$digest循环发生的时候，会遍历当前$scope及其所有子$scope上已注册的所有watchers函数。

#### 3.4.4 Angular中的$apply ####

$digest是一个内部函数，正常的应用代码中是不应该直接调用它的。要想主动触发它，就要调用scope.$apply函数，它是触发Angular“脏检查机制”的常用公开接口。
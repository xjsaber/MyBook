# 3全面解析Module模式 #

Module模式是JavaScript编程中一个非常通用的模式

1. 模块化
2. 封装了变量和function，和全局的namespace不接触，松耦合
3. 只暴露可用public的方法，其他私有方法全部隐藏

## 基本用法 ##

	var Calculator = function (eq) {
		//这里可以声明私有变量
		
		var eqCtl = document.getElementById(eq);

		return {
			//暴露公开的成员
			add: function (x, y) {
				var val = x + y;
				eqCtl.innerHTML = val;
			}
		}
	}
每次用的时候都要new一下，也就是说每个实例在内存里都是一份copy，如果你不需要传参数或者没有一些特殊苛刻的要求的话，我们可以在最后一个}后面加上一个括号，来达到自执行的目的。

## 匿名闭包 ##
匿名闭包是让一切成为可能的基础，而这也是JavaScript最好的特性，我们来创建一个最简单的闭包函数，函数内部的代码一直存在于闭包内，在整个运行周期内，该闭包都保证了内部的代码处于私有状态。

	(function () {
	    // ... 所有的变量和function都在这里声明，并且作用域也只能在这个匿名闭包里
	    // ...但是这里的代码依然可以访问外部全局的对象
	}());

## 引用全局变量 ##
JavaScript解释器反向遍历作用域链来查找整个变量的var声明，如果没有找到var，解释器则假定该变量是全局变量，如果该变量用于了赋值操作的话，之前如果不存在的话，解释器则会自动创建它，这就是说在匿名闭包里使用或创建全局变量非常容易。

## 高级用法 ##

	var blogModule = (function (my) {
	    my.AddPhoto = function () {
	        //添加内部代码  
	    };
	    return my;
	} (blogModule)); 

## 松耦合扩展 ##

	var cnblogs = cnblogs || {} ;

这是确保cnblogs对象，在存在的时候直接用，不存在的时候直接赋值为{}，我们来看看如何利用这个特性来实现Module模式的任意加载顺序：

	var blogModule = (function (my) {
	
	    // 添加一些功能   
	    
	    return my;
	} (blogModule || {}));  

## 紧耦合扩展 ##
紧耦合扩展限制了加载顺序，但是提供了我们重载的机会。

	var blogModule = (function (my) {
	    var oldAddPhotoMethod = my.AddPhoto;
	
	    my.AddPhoto = function () {
	        // 重载方法，依然可通过oldAddPhotoMethod调用旧的方法
	    };
	
	    return my;
	} (blogModule));
通过这种方式，我们达到了重载的目的，当然如果你想在继续在内部使用原有的属性，你可以调用oldAddPhotoMethod来用。


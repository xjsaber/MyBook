# Chapter 3: Pure Happiness with Pure Functions #
            
## 再次强调“纯” ##
         
纯函数是这样一种函数，即相同的输入，永远会得到相同的输出，而且没有任何可观察的副作用。
         
## 副作用可能包括... ##

## 八年级数学 ##

> 函数是不同数值之间的特殊关系：每一个输入值返回且只返回一个输出值。
    
函数只是两种数值之间的关系：输入和输出。尽管每个输入都只会有一个输出，但不同的输入却可以有相同的输出。下图展示了一个合法的从 x 到 y 的函数关系；

## 追求“纯”的理由 ##

### 可缓存性（Cacheable） ###
纯函数总能够根据输入来做缓存。实现缓存的一种典型方式是 memoize 技术：

### 可移植性／自文档化（Portable / Self-Documenting） ###
纯函数是完全自给自足的。

	// 不纯的
	var signUp = function(attrs) {
	  var user = saveUser(attrs);
	  welcomeUser(user);
	};
	
	// 纯的
	var signUp = function(Db, Email, attrs) {
	  return function() {
	    var user = saveUser(Db, attrs);
	    welcomeUser(Email, user);
	  };
	};

着代码能够在 web workers 中运行。总之，可移植性是一个非常强大的特性。

命令式编程中“典型”的方法和过程都深深地根植于它们所在的环境中，通过状态、依赖和有效作用（available effects）达成；纯函数与此相反，它与环境无关，只要我们愿意，可以在任何地方运行它。

### 可测试性（Testable） ###
第三点，纯函数让测试更加容易。我们不需要伪造一个“真实的”支付网关，或者每一次测试之前都要配置、之后都要断言状态（assert the state）。只需简单地给函数一个输入，然后断言输出就好了。


### 合理性（Reasonable） ###

纯函数最大的好处是引用透明性（referential transparency）。如果一段代码可以替换成它执行所得的结果，而且是在不改变整个程序行为的前提下替换的，那么我们就说这段代码是引用透明的。

### 并行代码 ###

### 总结 ###

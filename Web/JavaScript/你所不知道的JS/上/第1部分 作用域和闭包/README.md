你不知道的JS

第一部分 作用域和闭包

序

[第1章 作用域是什么](ch1/README.md)

[第2章 词法作用域](ch2/README.md)

[第3章 函数作用域和块作用域](ch3/README.md)

[第4章 提升](ch4/README.md)

[第5章 作用域闭包](ch5/README.md)

#### 附录A 动态作用域 ####

动态作用域，重申它与词法作用域的区别。但实际上动态作用域是
JavaScript 另一个重要机制 this 的表亲。

动态作用域似乎暗示有很好的理由让作用域作为一个在运行时就被动态确定的形式，而不是在写代码时进行静态确定的形式。

	function foo() {
		console.log( a ); // 2
	}
	function bar() {
		var a = 3;
		foo();
	}
	var a = 2;
	bar();

词法作用域让foo()中的a通过RHS引用到了全局作用域中的a，因此会输出2。

而动态作用域并不关心函数和作用域是如何声明以及在何处声明的，只关心它们从何处调用。换句话说，作用域链是基于调用栈的，而不是代码中的作用域嵌套。

如果 JavaScript 具有动态作用域，理论上，下面代码中的 foo() 在执行时将会输出 3 。

因为当 foo() 无法找到 a 的变量引用时，会顺着调用栈在调用 foo() 的地方查找 a ，而不是在嵌套的词法作用域链中向上查找。由于 foo() 是在 bar() 中调用的，引擎会检查 bar() 的作用域，并在其中找到值为 3 的变量 a 。

需要明确的是，事实上 JavaScript 并不具有动态作用域。它只有词法作用域，简单明了。

但是 this 机制某种程度上很像动态作用域。

主要区别：词法作用域是在写代码或者说定义时确定的，而动态作用域是在运行时确定的。（ this 也是！）词法作用域关注函数在何处声明，而动态作用域关注函数从何处调用。

#### 附录B 块作用域的替代方案 ####

ES6

	{
		let a = 2;
		console.log( a ); // 2
	}
	console.log( a ); // ReferenceError

ES6之前

	try{throw 2;}catch(a){
		console.log( a ); // 2
	}
	console.log( a ); // ReferenceError

这是向 ES6 中的所有（好吧，不是所有而是大部分）功能迁移的首选方式：在从ES6 之前的环境向 ES6 过渡时，使用代码转换工具来对 ES6 代码进行处理，生成兼容 ES5的代码。

**B.1**

Traceur

**B.2 隐式和显式作用域**

	let (a = 2) {
		console.log( a ); // 2
	}
	console.log( a ); // ReferenceError

同隐式地劫持一个已经存在的作用域不同， let 声明会创建一个显示的作用域并与其进行绑定。显式作用域不仅更加突出，在代码重构时也表现得更加健壮。

**B.3 性能**

#### 附录C this词法 ####

将this同词法作用域联系起来。ES6天加了一个特殊的词法形式用于函数声明，叫做箭头函数。

ES6以前

	var obj = {
		count: 0,
		cool: function coolFn() {
			var self = this;
			if (self.count < 1) {
				setTimeout( function timer(){
					self.count++;
					console.log( "awesome?" );
				}, 100 );
			}
		}
	};
	obj.cool(); // 酷吧？

var self = this 这种解决方案圆满解决了理解和正确使用 this 绑定的问题。词法作用域和闭包。

ES6

	var obj = {
		count: 0,
		cool: function coolFn() {
			if (this.count < 1) {
				setTimeout( () => { // 箭头函数是什么鬼东西？
					this.count++;
					console.log( "awesome?" );
				}, 100 );
			}
		}
	};
	obj.cool(); // 很酷吧 ?

箭头函数在涉及 this 绑定时的行为和普通函数的行为完全不一致。它放弃了所有普通 this 绑定的规则，取而代之的是用当前的词法作用域覆盖了 this 本来的值。

这个代码片段中的箭头函数并非是以某种不可预测的方式同所属的 this 进行了解绑定，而只是“继承”了 cool() 函数的 this 绑定（因此调用它并不会出错）。

	var obj = {
		count: 0,
		cool: function coolFn() {
			if (this.count < 1) {
				setTimeout(function timer() {
					this.count++;   //this 是安全的
									//因为bind(..)
				}.bind(this), 100); //look, bind()!
			}
		}
	};
	obj.cool(); 

喜欢箭头函数中 this 词法的新行为模式，还是喜欢更靠得住的 bind() ，都需要注意箭头函数不仅仅意味着可以少写代码。

附录D 致谢


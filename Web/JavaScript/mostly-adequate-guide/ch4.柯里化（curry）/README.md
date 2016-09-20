# 第 4 章: 柯里化（curry）#

## 不可或缺的 curry ##

curry 的概念很简单：只传递给函数一部分参数来调用它，让它返回一个函数去处理剩下的参数。

	var add = function(x) {
	  return function(y) {
	    return x + y;
	  };
	};
	
	var increment = add(1);
	var addTen = add(10);
	
	increment(2);
	// 3
	
	addTen(2);
	// 12

> 这里表明的是一种“预加载”函数的能力，通过传递一到两个参数调用函数，就能得到一个记住了这些参数的新函数。

## 不仅仅是双关语／咖喱 ##

	var getChildren = function(x) {
	  return x.childNodes;
	};
	
	var allTheChildren = map(getChildren);
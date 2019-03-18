# TypeScript Handbook（中文版） 读书笔记 #

目录

## 1. 快速上手 ##

## 2. 新增功能 ##

## 3. 手册指南 ##

### 基础类型 ###

介绍

#### 布尔值 ####

boolean

	let isDone: boolean = false;

#### 数字 ####

number

	let decLiteral: number = 6;
	let hexLiteral: number = 0xf00d;
	let binaryLiteral: number = 0b1010;
	let octalLiteral: number = 0o744;

#### 字符串 ####

string

	let name: string = 'bob';
	name = 'smith';

还可以使用模版字符串，它可以定义多行文本和内嵌表达式。这种字符串是被反引号包围（ `），并且以${ expr }这种形式嵌入表达式。

	let name: string = `Gene`;
	let sentence: string = `Hello, my name is ${ name }.

#### 数组 ####

1. 可以在元素类型后面接上 []，表示由此类型元素组成的一个数组

	let list: number[] = [1, 2, 3];

2. 使用数组泛型

	let list: Array<number> = [1, 2, 3];

#### 元组 Tuple ####

元组类型允许表示一个已知元素数量和类型的数组，各元素的类型不必相同。

	let x: [string, number];
	x = ['hello', 10]; // OK
	x = [10, 'hello']; // Error

当访问一个已知索引的元素，会得到正确的类型:

	console.log(x[0].substr(1)); // OK
	console.log(x[1].substr(1)); // Error, 'number' does not have 'substr'

当访问一个越界的元素，会使用联合类型替代：

	x[3] = 'world'; // OK, 字符串可以赋值给(string | number)类型
	console.log(x[5].toString()); // OK, 'string' 和 'number' 都有 toString
	x[6] = true; // Error, 布尔不是(string | number)类型

#### 枚举 ####

默认情况下，从0开始为元素编号。

	enum Color {Red, Green, Blue}
	let c: Color = Color.Green;

	//Color["Red"] = 0
	//Color["Green"] = 1
	//Color["Blue"] = 2

	enum Color {Red = 1, Green, Blue}
	let c: Color = Color.Green;

	//Color["Red"] = 1
	//Color["Green"] = 2
	//Color["Blue"] = 3

或者，全部都采用手动赋值：

	enum Color {Red = 1, Green = 2, Blue = 4}
	let c: Color = Color.Green;

	//Color["Red"] = 1
	//Color["Green"] = 2
	//Color["Blue"] = 4

#### Any ####

不希望类型检查器对这些值进行检查而是直接让它们通过编译阶段的检查

	let notSure: any = 4;
	notSure = "maybe a string instead";
	notSure = false; // okay, definitely a boolean

	let prettySure: Object = 4;
	prettySure.toFixed(); // Error: Property 'toFixed' doesn't exist on type 'Object'.

当你只知道一部分数据的类型时，any类型也是有用的。 比如，你有一个数组，它包含了不同的类型的数据： 

	let list: any[] = [1, true, "free"];
	list[1] = 100;

#### Void ####

void类型像是与any类型相反，它表示没有任何类型

	function warnUser(): void {
    	alert("This is my warning message");
	}

声明一个void类型的变量没有什么大用，因为你只能为它赋予undefined和null

#### Null 和 Undefined ####

undefined和null两者各自有自己的类型分别叫做undefined和null

默认情况下null和undefined是所有类型的子类型。 就是说你可以把 null和undefined赋值给number类型的变量。也许在某处你想传入一个 string或null或undefined，你可以使用联合类型string | null | undefined。

#### Never ####

never类型表示的是那些永不存在的值的类型。 

never类型是任何类型的子类型，也可以赋值给任何类型；然而，没有类型是never的子类型或可以赋值给never类型（除了never本身之外）。 即使 any也不可以赋值给never。

	// 返回never的函数必须存在无法达到的终点
	function error(message: string): never {
    	throw new Error(message);
	}

	// 推断的返回值类型为never
	function fail() {
   		return error("Something failed");
	}

	// 返回never的函数必须存在无法达到的终点
	function infiniteLoop(): never {
    	while (true) {
    	}
	}

#### 类型断言 ####

1. “尖括号”语法

	let someValue: any = "this is a string";
	let strLength: number = (<string>someValue).length;

2. as语法

	let someValue: any = "this is a string";
	let strLength: number = (someValue as string).length;

#### 关于let ####

使用let关键字来代替大家所熟悉的JavaScript关键字var

### 变量声明 ###

#### 变量声明 ####

let在很多方面与var相似。

const阻止对一个变量再次赋值。

#### var 声明 ####

通过var关键字定义JavaScript变量

	var a = 10;

	function f() {
    	var message = "Hello, world!";
    	return message;
	}

并且我们也可以在其它函数内部访问相同的变量。
	
	function f() {
	    var a = 10;
	    return function g() {
	        var b = a + 1;
	        return b;
	    }
	}
	
	var g = f();
	g(); // returns 11;

g可以获取f函数里定义的a变量。每当g被调用时，都可以访问f里的a变量，即使当g在f已经执行完才被调用，它仍然可以访问及修改a。

	function f() {
		var a = 1;
	
		a = 2;
		var b = g();
		a = 3;
	
		return b;
	
		function g() {
			return a;
		}
	}
	
	f(); //2

**作用域规则**

var声明有些奇怪的作用域规则

	function f(shouldInitialize: boolean) {
		if (shouldInitialize) {
			var x = 10;
		}
	
		return x;
	}
	
	f(true); //returns '10'
	f(false);//returns 'undefined'

变量 x是定义在*if语句里面*，但是我们却可以在语句的外面访问它。 这是因为 var声明可以在包含它的函数，模块，命名空间或全局作用域内部任何位置被访问。

	function sumMatrix(matrix: number[][]) {
	    var sum = 0;
	    for (var i = 0; i < matrix.length; i++) {
	        var currentRow = matrix[i];
	        for (var i = 0; i < currentRow.length; i++) {
	            sum += currentRow[i];
	        }
	    }
	
	    return sum;
	}

里层的for循环会覆盖变量i，因为所有i都引用相同的函数作用域内的变量。

**变量获取怪异之处**

setTimeout在若干毫秒后执行一个函数，并且是在for循环结束后。 for循环结束后，i的值为10。 所以当函数被调用的时候，它会打印出 10。

	for (var i = 0; i < 10; i++) {
	    // capture the current state of 'i'
	    // by invoking a function with its current value
	    (function(i) {
	        setTimeout(function() { console.log(i); }, 100 * i);
	    })(i);
	}



#### let 声明 ####

	let hello = "Hello!";

**块作用域**

当用let声明一个变量，它使用的是词法作用域或块作用域。 不同于使用 var声明的变量那样可以在包含它们的函数外访问，块作用域变量在包含它们的块或for循环之外是不能访问的。

	function f(input: boolean) {
		let a = 100
	
		if (input) {
			//Still okay to reference 'a'
			let b = a + 1;
			return b;
		}
	
		// Error: 'b' doesn't exist here
	    return b;
	}

在catch语句里声明的变量也具有同样的作用域规则。

	try {
	    throw "oh no!";
	}
	catch (e) {
	    console.log("Oh well.");
	}
	
	// Error: 'e' doesn't exist here
	console.log(e);

拥有块级作用域的变量的另一个特点是，它们不能在被声明之前读或写。 虽然这些变量始终“存在”于它们的作用域里，但在直到声明它的代码之前的区域都属于 暂时性死区。 它只是用来说明我们不能在 let语句之前访问它们。

	a++; // illegal to use 'a' before it's declared;
	let a;

**重定义及屏蔽**

var声明时，它不在乎你声明多少次；你只会得到1个

	function f(x) {
	    var x;
	    var x;
	
	    if (true) {
	        var x;
	    }
	}

所有x的声明实际上都引用一个相同的x

	let x = 10;
	let x = 20; // 错误，不能在1个作用域里多次声明`x`

并不是要求两个均是块级作用域的声明TypeScript才会给出一个错误的警告。

	function f(x) {
    	let x = 100; // error: interferes with parameter declaration
	}

	function g() {
    	let x = 100;
    	var x = 100; // error: can't have both declarations of 'x'
	}	

并不是说块级作用域变量不能用函数作用域变量来声明。 而是块级作用域变量需要在明显不同的块里声明。 

	function f(condition, x) {
    	if (condition) {
        	let x = 100;
        	return x;
   		}

    	return x;
	}

	f(false, 0); // returns 0
	f(true, 0);  // returns 100

在一个嵌套作用域里引入一个新名字的行为称做屏蔽。

	function sumMatrix(matrix: number[][]) {
	    let sum = 0;
	    for (let i = 0; i < matrix.length; i++) {
	        var currentRow = matrix[i];
	        for (let i = 0; i < currentRow.length; i++) {
	            sum += currentRow[i];
	        }
	    }
	
	    return sum;
	}

内层循环的i可以屏蔽掉外层循环的i。

**块级作用域变量的获取**

获取用var声明的变量时，每次进入一个作用域时，它创建了一个变量的 环境。 就算作用域内代码已经执行完毕，这个环境与其捕获的变量依然存在。

	function theCityThatAlwaysSleeps() {
	    let getCity;
	
	    if (true) {
	        let city = "Seattle";
	        getCity = function() {
	            return city;
	        }
	    }
	
	    return getCity();
	}

当let声明出现在循环体里时拥有完全不同的行为。 不仅是在循环里引入了一个新的变量环境，而是针对 每次迭代都会创建这样一个新作用域。 这就是我们在使用立即执行的函数表达式时做的事，所以在 setTimeout例子里我们仅使用let声明就可以了。

	for (let i = 0; i < 10 ; i++) {
	    setTimeout(function() {console.log(i); }, 100 * i);
	}

#### const 声明 ####

const 声明是声明变量的另一种方式。

	const numLivesForCat = 9;

它们拥有与 let相同的作用域规则，但是不能对它们重新赋值。

#### let vs const ####

使用最小特权原则，所有变量除了你计划去修改的都应该使用const。 基本原则就是如果一个变量不需要对它写入，那么其它使用这些代码的人也不能够写入它们，并且要思考为什么会需要对这些变量重新赋值。 使用 const也可以让我们更容易的推测数据的流动。

用户很喜欢let的简洁性

#### 结构 ####

**解构数组**

	let input = [1, 2];
	let [first, second] = input;
	console.log(first); // outputs 1
	console.log(second); // outputs 2

**对象解构**

	let o = {
		a: "foo",
		b: 12,
		c: "bar"
	};
	let { a, b} = o

通过 o.a and o.b创建了 a 和 b。

可以在对象里使用...语法创建剩余变量：

	let { a, ...passthrough } = o;
	let total = passthrough.b + passthrough.c.length

**属性重命名** 


**默认值**

默认值可以让你在属性为 undefined 时使用缺省值

	function keepWholeObject(wholeObject: { a: string, b?: number }) {
	    let { a, b = 1001 } = wholeObject;
	}

即使b为undefined， keepWhioleObject函数的变量wholeObject属性a和b都会有值。

#### 函数声明 ####

解构也能用于函数声明。

	type C = {a: string, b?: number}
	function f({a, b}: C): void {
		// ...
	}

但是，通常情况下更多的是指定默认值。

	function f({a, b} = {a: "", b: 0}): void {
		// ...
	}
	f(); // ok, default to {a: "", b: 0}

其次，需要结构属性给予一个默认或可选的属性用来替换主初始化列表。

	function f({a, b = 0}) = {a: ""}): void {
		//
	}
	f({ a: "yes" }); // ok, default b = 0
	f(); // ok, default to {a: ""}, which then defaults b = 0
	f({}); // error, 'a' is required if you supply an argument

**展开**

展开操作符正与解构相反。 它允许你将一个数组展开为另一个数组，或将一个对象展开为另一个对象。

	let first = [1, 2];
	let second = [3, 4];
	let bothPlus = [0, ...first, ...second, 5];

这会令bothPlus的值为[0, 1, 2, 3, 4, 5]。 展开操作创建了 first和second的一份浅拷贝。 它们不会被展开操作所改变。 

	let defaults = { food: "spicy", price: "$$", ambiance: "noisy" };
	let search = { ...defaults, food: "rich" };

search的值为{ food: "rich", price: "$$", ambiance: "noisy" }。 对象的展开比数组的展开要复杂的多。 像数组展开一样，它是从左至右进行处理，但结果仍为对象。 这就意味着出现在展开对象后面的属性会覆盖前面的属性。

	let defaults = { food: "spicy", price: "$$", ambiance: "noisy" };
	let search = { food: "rich", ...defaults };

它只包含自身的可枚举的属性，当你展开一个对象实例时，你会丢失其方法

	class C {
	    p = 12;
	    m() {}
	}
	let d = new C();
	let clone = { ...d };
	console.log(clone.p);
	clone.m();

### 接口 ###

#### 介绍 ####

TypeScript的核心原则之一是对值所具有的结构进行类型检查。

#### 接口初探 ####

类型检查器会查看printLabel的调用。 printLabel有一个参数，并要求这个对象参数有一个名为label类型为string的属性。 需要注意的是，我们传入的对象参数实际上会包含很多属性，但是编译器只会检查那些必需的属性是否存在，并且其类型是否匹配。

LabelledValue接口就好比一个名字，用来描述上面例子里的要求。 它代表了有一个 label属性且类型为string的对象。

#### 可选属性 ####

带有可选属性的接口与普通的接口定义差不多，只是在可选属性名字定义的后面加一个?符号。

#### 只读属性 ####

	interface Point {
		readonly x: number;
		readonly y: number;
	}

	let p1: Point = {x: 10, y:20 };
	p1.x = 5; //error!

做为变量使用的话用 const，若做为属性则使用readonly。

#### 额外的属性检查 ####

	interface SquareConfig {
		color?: string;
		width?: number;
		[propName: string]: any;
	}

#### 函数类型 ####

接口能够描述JavaScript中对象拥有的各种各样的外形。 除了描述带有属性的普通对象外，接口也可以描述函数类型。

	interface SearchFunc {
	  (source: string, subString: string): boolean;
	}

	let mySearch: SearchFunc;
		mySearch = function(source: string, subString: string) {
		  let result = source.search(subString);
		  return result > -1;
	}

	let mySearch: SearchFunc;
	mySearch = function(src: string, sub: string): boolean {
	  let result = src.search(sub);
	  return result > -1;
	}

#### 可索引的类型 ####

可索引类型具有一个 *索引签名*，它描述了对象索引的类型，还有相应的索引返回值类型。 

	interface StringArray {
	  [index: number]: string;
	}
	
	let myArray: StringArray;
	myArray = ["Bob", "Fred"];
	
	let myStr: string = myArray[0];

TypeScript支持两种索引签名：字符串和数字。

#### 类类型 ####

### 类 ###

#### 介绍 ####

#### 继承 ####

基于类的程序设计中最基本的模式是允许使用继承来扩展现有的类。

我们使用 extends关键字来创建子类。你可以看到Horse和Snake类是基类Animal的子类，并且可以访问其属性和方法。

包含构造函数的派生类必须调用super()，它会执行基类的构造方法。

#### 公共，私有与受保护的修饰符 ####

**默认为public**

在TypeScript里，成员都默认为 public。

**理解private**

当成员被标记成private时，它就不能在声明它的类的外部访问。

**理解protected**

protected修饰符与private修饰符的行为很相似，但有一点不同，protected成员在派生类中仍然可以访问。

#### readonly修饰符 ####

只读属性必须在声明时或构造函数里被初始化。 

	class Octopus {
	    readonly name: string;
	    readonly numberOfLegs: number = 8;
	    constructor (theName: string) {
	        this.name = theName;
	    }
	}
	let dad = new Octopus("Man with the 8 strong legs");
	dad.name = "Man with the 3-piece suit"; // error! name is readonly.

**参数属性**

参数属性可以方便地让我们在一个地方定义并初始化一个成员。

	class Animal {
		constructor(private name: string) {}
		move(distanceInMeters: number) {
			console.log(`${this.name} moved ${distanceInMeters}m.`)
		}
	}

#### 存取器 ####

TypeScript支持通过getters/setters来截取对对象成员的访问。

	class Employee {
	    fullName: string;
	}
	
	let employee = new Employee();
	employee.fullName = "Bob Smith";
	if (employee.fullName) {
	    console.log(employee.fullName);
	}



#### 静态属性 ####

创建类的静态成员，这些属性存在于类本身上面而不是类的实例上。 

#### 抽象类 ####

抽象类做为其它派生类的基类使用。 abstract关键字是用于定义抽象类和在抽象类内部定义抽象方法。

	abstract class Animal {
		abstract makeSound(): void,
		move(): void {
			console.log('roaming the earch...');
		}
	}

#### 高级技巧 ####

**构造函数**

首先就是类的实例的类型。

	class Greeter {
		greeting: string;
		constructor(message: string) {
			this.greeting = message;
		}
		greet() {
			return "Hello, " + this.greeting;
		}
	}
	let greeter: Greeter;
	greeter = new Greeter("world");
	console.log(greeter.greet());

let greeter: Greeter，意思是Greeter类的实例的类型是Greeter。 

**把类当作接口使用**

类定义会创建两个东西：类的实例类型和一个构造函数。因为类可以创建出类型，所以你能够在允许使用接口的地方使用类。 

	class Point {
	    x: number;
	    y: number;
	}
	
	interface Point3d extends Point {
	    z: number;
	}
	
	let point3d: Point3d = {x: 1, y: 2, z: 3};

### 函数 ###

#### 介绍 ####

函数是JavaScript应用程序的基础。 它帮助你实现抽象层，模拟类，信息隐藏和模块。 在TypeScript里，虽然已经支持类，命名空间和模块，但函数仍然是主要的定义 行为的地方。

#### 函数 ####

和JavaScript一样，TypeScript函数可以创建有名字的函数和匿名函数。 

	// Named function
	function add(x, y) {
	    return x + y;
	}
	
	// Anonymous function
	let myAdd = function(x, y) { return x + y; };



### 泛型 ###

#### 介绍 ####

#### 泛型之Hello World ####

identity函数

	function identity<T>(arg: T): T {
	    return arg;
	}

	let output = identity<string>("myString");  // type of output will be 'string'

	let output = identity("myString");  // type of output will be 'string'

#### 使用泛型变量 ####

	function loggingIdentity<T>(arg: T): T {
	    console.log(arg.length);  // Error: T doesn't have .length
	    return arg;
	}

	function loggingIdentity<T>(arg: T[]): T[] {
	    console.log(arg.length);  // Array has a .length, so no more error
	    return arg;
	}

#### 泛型类型 ####

	function identity<T>(arg: T): T {
	    return arg;
	}
	
	let myIdentity: <T>(arg: T) => T = identity;

**在泛型约束中使用类型参数**


**在泛型里使用类类型**

### 枚举 ###




### 声明合并 ###

## 4. 工程配置 ##

## 5. Wiki ##
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

### 类型断言 ###

1. “尖括号”语法

	let someValue: any = "this is a string";
	let strLength: number = (<string>someValue).length;

2. as语法

	let someValue: any = "this is a string";
	let strLength: number = (someValue as string).length;

### 关于let ###

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

## 4. 工程配置 ##

## 5. Wiki ##
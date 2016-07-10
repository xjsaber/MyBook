# 3.手册 #
目录

3.1 基础类型
3.2 变量声明

## 3.1 基础类型 ##
### 介绍 ###
数字，字符串，结构体，布尔值
### 布尔值 ###
最基本的数据类型就是简单的true/false值，在js和ts里叫做boolean。
	
	let isDone: boolean = false;
### 数字 ###
和js一样，ts里的所有数字都是浮点数。这些浮点数的类型是number。除了支持十进制和十六进制字面量，ts还支持ECMAScript2015中引入的二进制和八进制字面量

	let decLiteral: number = 6;
	let hexLiteral: number = 0xfood;
	let binaryLiteral: number = 0b1010;
	let octalLiteral: number = 0o744;
### 字符串 ###
处理网页或服务器端的文本数据，ts使用string表示文本数据类型。和js一样，可以使用双引号（"）或单引号(')表示字符串。

	let name: string = "bob";
	name = "smith";
还可以使用模板字符串，定义多行文本和内嵌表达式。这种字符串被反引号保卫（`），并且以${ expr }这种形式嵌入表达式

	let name: string = `Gene`;
	let age: number = 37;
	let sentence: string = `Hello, my name is ${ name }.
	I'll be ${ age + 1 } years old next month.`;
### 数组 ###
ts像js一样可以操作数组元素。

1. 可以在元素类型后面接上[]，表示由此类型元素组成的一个数组：
let list: number[] = [1, 2, 3];
2. 使用数组泛型，`Array<元素类型>`：
let list: Array<number> = [1, 2, 3];
### 元祖Tuple ###
元组类型允许表示一个已知元素数量和类型的数组，各元素的类型不必相同。比如，你可以定义一对值分别为string和number类型的元组。
	
	//Declare a tuple type
	let x: [string, number];
	//Initialize it
	x = ['hello', 10]; //OK
	//Initialize it incorrectly
	x = [10, 'hello']; //Error
当访问一个已知索引的元素，会得到正确的类型：

	console.log(x[0].substr(1)); //OK
	console.log(x[1].substr(1)); //Error, 'number' does not have 'substr'
当访问一个越界的元素，会使用联合类型替代：

	x[3] = 'world'; //OK,字符串可以赋值给（string|number）类型
	console.log(x[5].toString()); //OK, 'string'和'number'都有toString
	x[6] = true; //Error, 布尔不是(string|number)类型

### 枚举 ###
enum类型是对JavaScript标准数据类型的一个补充。像C#等其他语言一样，使用枚举类型可以为一组数值赋予友好的名字。

	enum Color {Red, Green,Blue};
	let c: Color = Color.Green;
或者，全部都采用手动赋值：
	
	enum Color {Red = 1, Green = 2, Blue = 4};
	let c : Color = Color.Green;
枚举类型提供一个便利是你可以由枚举的值得到它的名字。例如，我们知道数值为2，但是不确定它映射到Color里的哪个名字，我们可以查找相应的名字：

	enum Color {Red = 1, Green, Blue};
	let colorName: string = Color[2];
	alert(colorName);

### 任意值 ###
不希望类型检查器对这些值进行检查而是直接让它们通过编译阶段的检查。
那么我们可以使用any类型来标记这些变量：

	let notSure: any = 4;
	notSure = "maybe a string instead";
	notSure = false; // okay, definitely a boolean
在对现有代码进行改写的时候，any类型是十分有用的，它允许你在编译时可选择地包含或移除类型检查。你可能认为Object有相似的作用，就像它在其他语言中那样，但是Object类型的变量只是允许你给它赋任意值——但是却不能够在它上面调用任意的方法，即便它真的有这些方法：

	let notSure: any = 4;
	notSure.ifItExists(); //okay, ifItExists might exist a runtime
	notSure.toFixed(); //okay, toFixed exists (but the compiler doesn't check)

	let prettySure: Object = 4;
	prettySure.toFixed(); //Error: Property 'toFixed' does't exist on type 'Object'.
当你只知道一部分数据的类型时， `any`类型也是有用的。比如，它有一个数组，它包含了不同的类型的数据。

	let list: any[] = [1, true, "free"];
	list[1] = 100;

### 空值 ###
某种程度上来说，`void`类型像是与`any`类型相反，它表示没有任何类型。当一个函数没有返回值时，你通常会见到其返回值类型是`void`:
	
	function warnUser(): void {
		alert("This is my warning message");
	}
声明一个void类型的变量没有什么大勇，因为你只能为它赋予undefined和null:
	
	let unusable: void = undefined;

### 类型断言 ###
类型断言好比其他语言里的类型转换，但是不进行特殊的数据检查和解构。它没有运行时的影响，只是在编译阶段起作用。
类型断言有两种形式。其一是“尖括号”语法：
	let someValue: any = "this is a string";
	let strLength: number = (<string>someValue).length;
另一个为as语法：
	let someValue: any = "this is a string";
	let strLength: number = (someValue as string).length;
两种形式是等价的，然而当你在TypeScript里使用JSX时，只有as语法断言是被允许地。
### 关于let ###
let关键字是Js的一个新概念，ts实现了它，很多常见的问题都可以通过使用let来解决，尽可能地使用let来代替var。
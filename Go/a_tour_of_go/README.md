# Go指南 #

go官网的指南

## 基础 ##

### 包、变量和函数 ###

#### 包 ####

每个 Go 程序都是由包组成的。

程序运行的入口是包 `main` 。

这个程序使用并导入了包 "fmt" 和 "math/rand" 。

按照惯例，包名与导入路径的最后一个目录一致。例如，"math/rand" 包由 package rand 语句开始。

注意：这个程序的运行环境是确定性的，因此 rand.Intn 每次都会返回相同的数字。 （为了得到不同的随机数，需要提供一个随机数种子，参阅 rand.Seed。）

	package main
	
	import (
		"fmt"
		"math/rand"
	)
	
	func main() {
		fmt.Println("My favorite number is", rand.Intn(10))
	}

#### 导入 ####

同样可以编写多个导入语句，例如：

	import "fmt"
	import "math"

不过使用打包的导入语句是更好的形式。

	package main

	import (
		"fmt"
		"math"
	)
	
	func main() {
		fmt.Printf("Now you have %g problems.", math.Sqrt(7))
	}

#### 导出名 ####

在 Go 中，首字母大写的名称是被导出的。

在导入包之后，你只能访问包所导出的名字，任何未导出的名字是不能被包外的代码访问的。

Foo 和 FOO 都是被导出的名称。名称 foo 是不会被导出的。

	package main
	
	import (
		"fmt"
		"math"
	)
	
	func main() {
		fmt.Println(math.Pi)
	}

#### 函数 ####

函数可以没有参数或接受多个参数。

在这个例子中， add 接受两个 int 类型的参数。

注意类型在变量名 之后 

	package main
	
	import "fmt"
	
	func add(x int, y int) int {
		return x + y
	}
	
	func main() {
		fmt.Println(add(42, 13))
	}

当两个或多个连续的函数命名参数是同一类型，则除了最后一个类型之外，其他都可以省略。

	x int, y int -> x, y int

	package main
	
	import "fmt"
	
	func add(x, y int) int {
		return x + y
	}
	
	func main() {
		fmt.Println(add(42, 13))
	}

#### 多值返回 ####

函数可以返回任意数量的返回值。

`swap` 函数返回了两个字符串。

	package main
	
	import "fmt"
	
	func swap(x, y string) (string, string) {
		return y, x
	}
	
	func main() {
		a, b := swap("hello", "world")
		fmt.Println(a, b)
	}

#### 命名返回值 ####

Go 的返回值可以被命名，并且就像在函数体开头声明的变量那样使用。

返回值的名称应当具有一定的意义，可以作为文档使用。

没有参数的 return 语句返回各个返回变量的当前值。这种用法被称作“裸”返回。

	package main
	
	import "fmt"
	
	func split(sum int) (x, y int) {
		x = sum * 4 / 9
		y = sum - x
		return
	}
	
	func main() {
		fmt.Println(split(17))
	}

#### 变量 ####

`var` 语句定义了一个变量的列表；跟函数的参数列表一样，类型在后面。

`var` 语句可以定义在包或函数级别。

	package main
	
	import "fmt"
	
	var c, python, java bool
	
	func main() {
		var i int
		fmt.Println(i, c, python, java)
	}

#### 初始化变量 ####

变量定义可以包含初始值，每个变量对应一个。

如果初始化是使用表达式，则可以省略类型；变量从初始值中获得类型。

	package main
	
	import "fmt"
	
	var i, j int = 1, 2
	
	func main() {
		var c, python, java = true, false, "no!"
		fmt.Println(i, j, c, python, java)
	}

#### 短声明变量 ####

在函数中， := 简洁赋值语句在明确类型的地方，可以用于替代 var 定义。

函数外的每个语句都必须以关键字开始（ var 、 func 、等等）， := 结构不能使用在函数外。

	package main
	
	import "fmt"
	
	func main() {
		var i, j int = 1, 2
		k := 3
		c, python, java := true, false, "no!"
	
		fmt.Println(i, j, k, c, python, java)
	}

#### 基本类型 ####

Go 的基本类型有Basic types

	bool
	
	string
	
	int  int8  int16  int32  int64
	uint uint8 uint16 uint32 uint64 uintptr
	
	byte // uint8 的别名
	
	rune // int32 的别名
	     // 代表一个Unicode码
	
	float32 float64
	
	complex64 complex128

这个例子演示了具有不同类型的变量。 同时与导入语句一样，变量的定义“打包”在一个语法块中。

int，uint 和 uintptr 类型在32位的系统上一般是32位，而在64位系统上是64位。当你需要使用一个整数类型时，你应该首选 int，仅当有特别的理由才使用定长整数类型或者无符号整数类型。

	package main
	
	import (
		"fmt"
		"math/cmplx"
	)
	
	var (
		ToBe   bool       = false
		MaxInt uint64     = 1<<64 - 1
		z      complex128 = cmplx.Sqrt(-5 + 12i)
	)
	
	func main() {
		const f = "%T(%v)\n"
		fmt.Printf(f, ToBe, ToBe)
		fmt.Printf(f, MaxInt, MaxInt)
		fmt.Printf(f, z, z)
	}

#### 零值 ####

变量在定义时没有明确的初始化时会赋值为 `零值` 。

零值是：

	数值类型为 0 ，
	布尔类型为 false ，
	字符串为 "" （空字符串）。

	package main
	
	import "fmt"
	
	func main() {
		var i int
		var f float64
		var b bool
		var s string
		fmt.Printf("%v %v %v %q\n", i, f, b, s)
	}

#### 类型转换 ####

表达式 T(v) 将值 v 转换为类型 T 。

一些关于数值的转换：

	var i int = 42
	var f float64 = float64(i)
	var u uint = uint(f)

或者，更加简单的形式：

	i := 42
	f := float64(i)
	u := uint(f)

与 C 不同的是 Go 的在不同类型之间的项目赋值时需要显式转换。 试着移除例子中 float64 或 int 的转换看看会发生什么。

	package main
	
	import (
		"fmt"
		"math"
	)
	
	func main() {
		var x, y int = 3, 4
		var f float64 = math.Sqrt(float64(x*x + y*y))
		var z uint = uint(f)
		fmt.Println(x, y, z)
	}

#### 类型推导 ####

在定义一个变量却并不显式指定其类型时（使用 := 语法或者 var = 表达式语法）， 变量的类型由（等号）右侧的值推导得出。

当右值定义了类型时，新变量的类型与其相同：

	var i int
	j := i // j 也是一个 int

但是当右边包含了未指名类型的数字常量时，新的变量就可能是 int 、 float64 或 complex128 。 这取决于常量的精度：

	i := 42           // int
	f := 3.142        // float64
	g := 0.867 + 0.5i // complex128

尝试修改演示代码中 v 的初始值，并观察这是如何影响其类型的。

	package main
	
	import "fmt"
	
	func main() {
		v := 42 // change me!
		fmt.Printf("v is of type %T\n", v)
	}

#### 常量 ####

常量的定义与变量类似，只不过使用 `const` 关键字。

常量可以是字符、字符串、布尔或数字类型的值。

常量不能使用 := 语法定义。

	package main
	
	import "fmt"
	
	const Pi = 3.14
	
	func main() {
		const World = "世界"
		fmt.Println("Hello", World)
		fmt.Println("Happy", Pi, "Day")
	
		const Truth = true
		fmt.Println("Go rules?", Truth)
	}

#### 数值常量 ####

数值常量是高精度的 *值* 。

一个未指定类型的常量由上下文来决定其类型。

也尝试一下输出 needInt(Big) 吧。

（int 可以存放最大64位的整数，根据平台不同有时会更少。）

	package main
	
	import "fmt"
	
	const (
		Big   = 1 << 100
		Small = Big >> 99
	)
	
	func needInt(x int) int { return x*10 + 1 }
	func needFloat(x float64) float64 {
		return x * 0.1
	}
	
	func main() {
		fmt.Println(needInt(Small))
		fmt.Println(needFloat(Small))
		fmt.Println(needFloat(Big))
	}

### 流程控制语句：for、if、else 、switch 和 defer ###

#### for  ####

Go 只有一种循环结构—— for 循环。

基本的 for 循环包含三个由分号分开的组成部分：

* 初始化语句：在第一次循环执行前被执行
* 循环条件表达式：每轮迭代开始前被求值
* 后置语句：每轮迭代后被执行

初始化语句一般是一个短变量声明，这里声明的变量仅在整个 for 循环语句可见。

如果条件表达式的值变为 false，那么迭代将终止。

	package main
	
	import "fmt"
	
	func main() {
		sum := 0
		for i := 0; i < 10; i++ {
			sum += i
		}
		fmt.Println(sum)
	}

循环初始化语句和后置语句都是可选的。

	package main

	import "fmt"

	func main() {
		sum := 1
		for ; sum < 1000; {
			sum += sum
		}
		fmt.Println(sum)
	}

#### for 是 Go 的 “while” ####

基于此可以省略分号：C 的 while 在 Go 中叫做 for 。

	package main
	
	import "fmt"
	
	func main() {
		sum := 1
		for sum < 1000 {
			sum += sum
		}
		fmt.Println(sum)
	}

#### 死循环 ####

如果省略了循环条件，循环就不会结束，因此可以用更简洁地形式表达死循环。

	package main
	
	func main() {
		for {
		}
	}

#### if ####

就像 for 循环一样，Go 的 if 语句也不要求用 ( ) 将条件括起来，同时， { } 还是必须有的。
	
	package main
	
	import (
		"fmt"
		"math"
	)
	
	func sqrt(x float64) string {
		if x < 0 {
			return sqrt(-x) + "i"
		}
		return fmt.Sprint(math.Sqrt(x))
	}
	
	func main() {
		fmt.Println(sqrt(2), sqrt(-4))
	}

#### if 的便捷语句 ####

跟 for 一样， if 语句可以在条件之前执行一个简单语句。

由这个语句定义的变量的作用域仅在 if 范围之内。

	package main
	
	import (
		"fmt"
		"math"
	)
	
	func pow(x, n, lim float64) float64 {
		if v := math.Pow(x, n); v < lim {
			return v
		}
		return lim
	}
	
	func main() {
		fmt.Println(
			pow(3, 2, 10),
			pow(3, 3, 20),
		)
	}

#### if 和 else ####

在 if 的便捷语句定义的变量同样可以在任何对应的 else 块中使用。

	package main
	
	import (
		"fmt"
		"math"
	)
	
	func pow(x, n, lim float64) float64 {
		if v := math.Pow(x, n); v < lim {
			return v
		} else {
			fmt.Printf("%g >= %g\n", v, lim)
		}
		// 这里开始就不能使用 v 了
		return lim
	}
	
	func main() {
		fmt.Println(
			pow(3, 2, 10),
			pow(3, 3, 20),
		)
	}

#### 练习：循环和函数 ####


#### switch ####

除非以 fallthrough 语句结束，否则分支会自动终止。

	package main
	
	import (
		"fmt"
		"runtime"
	)
	
	func main() {
		fmt.Print("Go runs on ")
		switch os := runtime.GOOS; os {
		case "darwin":
			fmt.Println("OS X.")
		case "linux":
			fmt.Println("Linux.")
		default:
			// freebsd, openbsd,
			// plan9, windows...
			fmt.Printf("%s.", os)
		}
	}

#### switch 的执行顺序 ####

switch 的条件从上到下的执行，当匹配成功的时候停止。

（例如，

	switch i {
	case 0:
	case f():
	}

当 i==0 时不会调用 f 。）

注意：Go playground 中的时间总是从 2009-11-10 23:00:00 UTC 开始， 如何校验这个值作为一个练习留给读者完成。

	package main
	
	import (
		"fmt"
		"time"
	)
	
	func main() {
		fmt.Println("When's Saturday?")
		today := time.Now().Weekday()
		switch time.Saturday {
		case today + 0:
			fmt.Println("Today.")
		case today + 1:
			fmt.Println("Tomorrow.")
		case today + 2:
			fmt.Println("In two days.")
		default:
			fmt.Println("Too far away.")
		}
	}

#### 没有条件的 switch ####

没有条件的 switch 同 switch true 一样。

这一构造使得可以用更清晰的形式来编写长的 if-then-else 链。

	package main
	
	import (
		"fmt"
		"time"
	)
	
	func main() {
		t := time.Now()
		switch {
		case t.Hour() < 12:
			fmt.Println("Good morning!")
		case t.Hour() < 17:
			fmt.Println("Good afternoon.")
		default:
			fmt.Println("Good evening.")
		}
	}

#### defer ####

defer 语句会延迟函数的执行直到上层函数返回。

延迟调用的参数会立刻生成，但是在上层函数返回前函数都不会被调用。

	package main
	
	import "fmt"
	
	func main() {
		defer fmt.Println("world")
	
		fmt.Println("hello")
	}

#### defer 栈 ####

延迟的函数调用被压入一个栈中。当函数返回时， 会按照后进先出的顺序调用被延迟的函数调用。

	package main
	
	import "fmt"
	
	func main() {
		fmt.Println("counting")
	
		for i := 0; i < 10; i++ {
			defer fmt.Println(i)
		}
	
		fmt.Println("done")
	}

### 复杂类型： struct、slice 和 map。 ###

#### 指针 ####

Go 具有指针。 指针保存了变量的内存地址。

类型 *T 是指向类型 T 的值的指针。其零值是 nil 。

	var p *int

& 符号会生成一个指向其作用对象的指针。

	i := 42
	p = &i

* 符号表示指针指向的底层的值。

	fmt.Println(*p) // 通过指针 p 读取 i
	*p = 21         // 通过指针 p 设置 i



## 方法和接口 ##



## 并发 ##
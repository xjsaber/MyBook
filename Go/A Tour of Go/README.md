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

没有参数的 `return` 语句返回各个返回变量的当前值。这种用法被称作“裸”返回。

直接返回语句应当仅用在下面这样的段函数种。

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

	import "fmt"
	
	func Sqrt(x float64) float64 {
		z := 1.0
		for i := 0; i < 10; i++ {
			z = z - (z - x) / 2
		}
		return z
	}
	
	func main() {
		fmt.Print(Sqrt(2))
	}

#### switch ####

switch 是编写一连串 if - else 语句的简便方法。它运行第一个值等于条件表达式的 case 语句。 

实际上，Go 自动提供了在这些语言中每个 case 后面所需的 break 语句。 除非以 `fallthrough` 语句结束，否则分支会自动终止。 Go 的另一点重要的不同在于 switch 的 case 无需为常量，且取值不必为整数。 

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

\* 符号表示指针指向的底层的值。

	fmt.Println(*p) // 通过指针 p 读取 i
	*p = 21         // 通过指针 p 设置 i

这也就是通常所说的“间接引用”或“非直接引用”。

	package main
	
	import "fmt"
	
	func main() {
		i, j := 42, 2701
	
		p := &i         // point to i
		fmt.Println(*p) // read i through the pointer
		*p = 21         // set i through the pointer
		fmt.Println(i)  // see the new value of i
	
		p = &j         // point to j
		*p = *p / 37   // divide j through the pointer
		fmt.Println(j) // see the new value of j
	}

#### 结构体 ####

一个结构体（ struct ）就是一个字段的集合。

（而 type 的含义跟其字面意思相符。）

	package main
	
	import "fmt"
	
	type Vertex struct {
		X int
		Y int
	}
	
	func main() {
		fmt.Println(Vertex{1, 2})
	}

**结构体字段**

结构体字段使用点号来访问。

	package main
	
	import "fmt"
	
	type Vertex struct {
		X int
		Y int
	}
	
	func main() {
		v := Vertex{1, 2}
		v.X = 4
		fmt.Println(v.X)
	}

**结构体指针**

结构体字段可以通过结构体指针来访问。

通过指针间接的访问是透明的。

	package main
			
	import "fmt"
	
	type Vertex struct {
		X int
		Y int
	}
	
	func main() {
		v := Vertex{1, 2}
		p := &v
		p.X = 1e9
		fmt.Println(v)
	}

**结构体文法**

结构体文法表示通过结构体字段的值作为列表来新分配一个结构体。

使用 Name: 语法可以仅列出部分字段。（字段名的顺序无关。）

特殊的前缀 & 返回一个指向结构体的指针。

	package main
	
	import "fmt"
	
	type Vertex struct {
		X, Y int
	}
	
	var (
		v1 = Vertex{1, 2}  // 类型为 Vertex
		v2 = Vertex{X: 1}  // Y:0 被省略
		v3 = Vertex{}      // X:0 和 Y:0
		p  = &Vertex{1, 2} // 类型为 *Vertex
	)
	
	func main() {
		fmt.Println(v1, p, v2, v3)
	}

#### 数组 ####

类型 [n]T 是一个有 n 个类型为 T 的值的数组。

表达式

var a [10]int
定义变量 a 是一个有十个整数的数组。

数组的长度是其类型的一部分，因此数组不能改变大小。 这看起来是一个制约，但是请不要担心； Go 提供了更加便利的方式来使用数组。

	package main
	
	import "fmt"
	
	func main() {
		var a [2]string
		a[0] = "Hello"
		a[1] = "World"
		fmt.Println(a[0], a[1])
		fmt.Println(a)
	}

**slice**

一个 slice 会指向一个序列的值，并且包含了长度信息。

[]T 是一个元素类型为 T 的 slice。

len(s) 返回 slice s 的长度。

**slice 的 slice**

slice 可以包含任意的类型，包括另一个 slice。

	package main
	
	import (
		"fmt"
		"strings"
	)

	func main() {
		// Create a tic-tac-toe board.
		game := [][]string{
			[]string{"_", "_", "_"},
			[]string{"_", "_", "_"},
			[]string{"_", "_", "_"},
		}
	
		// The players take turns.
		game[0][0] = "X"
		game[2][2] = "O"
		game[2][0] = "X"
		game[1][0] = "O"
		game[0][2] = "X"
	
		printBoard(game)
	}
	
	func printBoard(s [][]string) {
		for i := 0; i < len(s); i++ {
			fmt.Printf("%s\n", strings.Join(s[i], " "))
		}
	}

**对slice切片**

slice 可以重新切片，创建一个新的 slice 值指向相同的数组。

表达式

	s[lo:hi]

表示从 lo 到 hi-1 的 slice 元素，含前端，不包含后端。因此

	s[lo:lo]

是空的，而

	s[lo:lo+1]

有一个元素。

	package main
	
	import "fmt"
	
	func main() {
		s := []int{2, 3, 5, 7, 11, 13}
		fmt.Println("s ==", s)
		fmt.Println("s[1:4] ==", s[1:4])
	
		// 省略下标代表从 0 开始
		fmt.Println("s[:3] ==", s[:3])
	
		// 省略上标代表到 len(s) 结束
		fmt.Println("s[4:] ==", s[4:])
	}

**构造 slice**

slice 由函数 `make` 创建。这会分配一个全是零值的数组并且返回一个 slice 指向这个数组：

	a := make([]int, 5)  // len(a)=5

为了指定容量，可传递第三个参数到 `make`：

	b := make([]int, 0, 5) // len(b)=0, cap(b)=5
	
	b = b[:cap(b)] // len(b)=5, cap(b)=5
	b = b[1:]      // len(b)=4, cap(b)=4

----
	package main
	
	import "fmt"
	
	func main() {
		a := make([]int, 5)
		printSlice("a", a)
		b := make([]int, 0, 5)
		printSlice("b", b)
		c := b[:2]
		printSlice("c", c)
		d := c[2:5]
		printSlice("d", d)
	}
	
	func printSlice(s string, x []int) {
		fmt.Printf("%s len=%d cap=%d %v\n",
			s, len(x), cap(x), x)
	}

**nil slice**

slice 的零值是 nil 。

一个 nil 的 slice 的长度和容量是 0。

	package main
	
	import "fmt"
	
	func main() {
		var z []int
		fmt.Println(z, len(z), cap(z))
		if z == nil {
			fmt.Println("nil!")
		}
	}

**向 slice 添加元素**

向 slice 的末尾添加元素是一种常见的操作，因此 Go 提供了一个内建函数 append 。 内建函数的文档对 append 有详细介绍。

	func append(s []T, vs ...T) []T

append 的第一个参数 s 是一个元素类型为 T 的 slice ，其余类型为 T 的值将会附加到该 slice 的末尾。

append 的结果是一个包含原 slice 所有元素加上新添加的元素的 slice。

如果 s 的底层数组太小，而不能容纳所有值时，会分配一个更大的数组。 返回的 slice 会指向这个新分配的数组。

	package main
	
	import "fmt"
	
	func main() {
		var a []int
		printSlice("a", a)
	
		// append works on nil slices.
		a = append(a, 0)
		printSlice("a", a)
	
		// the slice grows as needed.
		a = append(a, 1)
		printSlice("a", a)
	
		// we can add more than one element at a time.
		a = append(a, 2, 3, 4)
		printSlice("a", a)
	}
	
	func printSlice(s string, x []int) {
		fmt.Printf("%s len=%d cap=%d %v\n",
			s, len(x), cap(x), x)
	}

#### range ####

for 循环的 range 格式可以对 slice 或者 map 进行迭代循环。

当使用 for 循环遍历一个 slice 时，每次迭代 range 将返回两个值。 第一个是当前下标（序号），第二个是该下标所对应元素的一个拷贝。

	package main
	
	import "fmt"
	
	var pow = []int{1, 2, 4, 8, 16, 32, 64, 128}
	
	func main() {
		for i, v := range pow {
			fmt.Printf("2**%d = %d\n", i, v)
		}
	}

可以通过赋值给 _ 来忽略序号和值。

如果只需要索引值，去掉 “ , value ” 的部分即可。

	package main
	
	import "fmt"
	
	func main() {
		pow := make([]int, 10)
		for i := range pow {
			pow[i] = 1 << uint(i)
		}
		for _, value := range pow {
			fmt.Printf("%d\n", value)
		}
	}

#### 练习：slice ####

#### map ####

map 映射键到值。

map 在使用之前必须用 make 来创建；值为 nil 的 map 是空的，并且不能对其赋值。

	package main
	
	import "fmt"
	
	type Vertex struct {
		Lat, Long float64
	}
	
	var m map[string]Vertex
	
	func main() {
		m = make(map[string]Vertex)
		m["Bell Labs"] = Vertex{
			40.68433, -74.39967,
		}
		fmt.Println(m["Bell Labs"])
	}

**map 的文法**

map 的文法跟结构体文法相似，不过必须有键名。

	package main
	
	import "fmt"
	
	type Vertex struct {
		Lat, Long float64
	}
	
	var m = map[string]Vertex{
		"Bell Labs": Vertex{
			40.68433, -74.39967,
		},
		"Google": Vertex{
			37.42202, -122.08408,
		},
	}
	
	func main() {
		fmt.Println(m)
	}

若顶级类型只是一个类型名，你可以在文法的元素中省略它。

	package main
	
	import "fmt"
	
	type Vertex struct {
		Lat, Long float64
	}
	
	var m = map[string]Vertex{
		"Bell Labs": {40.68433, -74.39967},
		"Google":    {37.42202, -122.08408},
	}
	
	func main() {
		fmt.Println(m)
	}

#### 修改 map ####

在 map m 中插入或修改一个元素：

	m[key] = elem

获得元素：

	elem = m[key]

删除元素：

	delete(m, key)

通过双赋值检测某个键存在

	elem, ok = m[key]

如果 key 在 m 中， ok 为 true。否则， ok 为 false，并且 elem 是 map 的元素类型的零值。

	package main
	
	import "fmt"
	
	func main() {
		m := make(map[string]int)
	
		m["Answer"] = 42
		fmt.Println("The value:", m["Answer"])
	
		m["Answer"] = 48
		fmt.Println("The value:", m["Answer"])
	
		delete(m, "Answer")
		fmt.Println("The value:", m["Answer"])
	
		v, ok := m["Answer"]
		fmt.Println("The value:", v, "Present?", ok)
	}


#### 练习：map ####

#### 函数值 ####

函数也是值。他们可以像其他值一样传递，比如，函数值可以作为函数的参数或者返回值。

	package main
	
	import (
		"fmt"
		"math"
	)
	
	func compute(fn func(float64, float64) float64) float64 {
		return fn(3, 4)
	}
	
	func main() {
		hypot := func(x, y float64) float64 {
			return math.Sqrt(x*x + y*y)
		}
		fmt.Println(hypot(5, 12))
	
		fmt.Println(compute(hypot))
		fmt.Println(compute(math.Pow))
	}

#### 函数的闭包 ####

Go 函数可以是一个闭包。闭包是一个函数值，它引用了函数体之外的变量。 这个函数可以对这个引用的变量进行访问和赋值；换句话说这个函数被“绑定”在这个变量上。

例如，函数 adder 返回一个闭包。每个返回的闭包都被绑定到其各自的 sum 变量上。

#### 练习：斐波纳契闭包 ####

## 方法和接口 ##

### 方法 ###

Go 没有类。然而，仍然可以在结构体类型上定义方法。

*方法接收者* 出现在 func 关键字和方法名之间的参数中。

	package main
	
	import (
		"fmt"
		"math"
	)
	
	type Vertex struct {
		X, Y float64
	}
	
	func (v *Vertex) Abs() float64 {
		return math.Sqrt(v.X*v.X + v.Y*v.Y)
	}
	
	func main() {
		v := &Vertex{3, 4}
		fmt.Println(v.Abs())
	}

你可以对包中的 任意 类型定义任意方法，而不仅仅是针对结构体。

但是，不能对来自其他包的类型或基础类型定义方法。

	package main
	
	import (
		"fmt"
		"math"
	)
	
	type MyFloat float64
	
	func (f MyFloat) Abs() float64 {
		if f < 0 {
			return float64(-f)
		}
		return float64(f)
	}
	
	func main() {
		f := MyFloat(-math.Sqrt2)
		fmt.Println(f.Abs())
	}

#### 接收者为指针的方法 ####

方法可以与命名类型或命名类型的指针关联。

刚刚看到的两个 Abs 方法。一个是在 *Vertex 指针类型上，而另一个在 MyFloat 值类型上。 有两个原因需要使用指针接收者。首先避免在每个方法调用中拷贝值（如果值类型是大的结构体的话会更有效率）。其次，方法可以修改接收者指向的值。

尝试修改 Abs 的定义，同时 Scale 方法使用 Vertex 代替 *Vertex 作为接收者。

当 v 是 Vertex 的时候 Scale 方法没有任何作用。Scale 修改 v。当 v 是一个值（非指针），方法看到的是 Vertex 的副本，并且无法修改原始值。

Abs 的工作方式是一样的。只不过，仅仅读取 v。所以读取的是原始值（通过指针）还是那个值的副本并没有关系。

	package main
	
	import (
		"fmt"
		"math"
	)
	
	type Vertex struct {
		X, Y float64
	}
	
	func (v *Vertex) Scale(f float64) {
		v.X = v.X * f
		v.Y = v.Y * f
	}
	
	func (v *Vertex) Abs() float64 {
		return math.Sqrt(v.X*v.X + v.Y*v.Y)
	}
	
	func main() {
		v := &Vertex{3, 4}
		fmt.Printf("Before scaling: %+v, Abs: %v\n", v, v.Abs())
		v.Scale(5)
		fmt.Printf("After scaling: %+v, Abs: %v\n", v, v.Abs())
	}

#### 接口 ####

接口类型是由一组方法定义的集合。

接口类型的值可以存放实现这些方法的任何值。

注意： 示例代码的 22 行存在一个错误。 由于 Abs 只定义在 *Vertex（指针类型）上， 所以 Vertex（值类型）不满足 Abser。

	package main
	
	import (
		"fmt"
		"math"
	)
	
	type Abser interface {
		Abs() float64
	}
	
	func main() {
		var a Abser
		f := MyFloat(-math.Sqrt2)
		v := Vertex{3, 4}
	
		a = f  // a MyFloat 实现了 Abser
		a = &v // a *Vertex 实现了 Abser
	
		// 下面一行，v 是一个 Vertex（而不是 *Vertex）
		// 所以没有实现 Abser。
		a = v
	
		fmt.Println(a.Abs())
	}
	
	type MyFloat float64
	
	func (f MyFloat) Abs() float64 {
		if f < 0 {
			return float64(-f)
		}
		return float64(f)
	}
	
	type Vertex struct {
		X, Y float64
	}
	
	func (v *Vertex) Abs() float64 {
		return math.Sqrt(v.X*v.X + v.Y*v.Y)
	}

#### 隐式接口 ####

类型通过实现那些方法来实现接口。 没有显式声明的必要；所以也就没有关键字“implements“。

隐式接口解藕了实现接口的包和定义接口的包：互不依赖。

因此，也就无需在每一个实现上增加新的接口名称，这样同时也鼓励了明确的接口定义。

包 io 定义了 Reader 和 Writer；其实不一定要这么做。

	package main
	
	import (
		"fmt"
		"os"
	)
	
	type Reader interface {
		Read(b []byte) (n int, err error)
	}
	
	type Writer interface {
		Write(b []byte) (n int, err error)
	}
	
	type ReadWriter interface {
		Reader
		Writer
	}
	
	func main() {
		var w Writer
	
		// os.Stdout 实现了 Writer
		w = os.Stdout
	
		fmt.Fprintf(w, "hello, writer\n")
	}

#### Stringers ####

一个普遍存在的接口是 fmt 包中定义的 Stringer。

	type Stringer interface {
	    String() string
	}

Stringer 是一个可以用字符串描述自己的类型。`fmt`包 （还有许多其他包）使用这个来进行输出。

	package main
	
	import "fmt"
	
	type Person struct {
		Name string
		Age  int
	}
	
	func (p Person) String() string {
		return fmt.Sprintf("%v (%v years)", p.Name, p.Age)
	}
	
	func main() {
		a := Person{"Arthur Dent", 42}
		z := Person{"Zaphod Beeblebrox", 9001}
		fmt.Println(a, z)
	}

#### 练习：Stringers ####

#### 错误 ####

Go 程序使用 error 值来表示错误状态。

与 fmt.Stringer 类似， error 类型是一个内建接口：

	type error interface {
	    Error() string
	}

（与 fmt.Stringer 类似，fmt 包在输出时也会试图匹配 error。）

通常函数会返回一个 error 值，调用的它的代码应当判断这个错误是否等于 nil， 来进行错误处理。

	i, err := strconv.Atoi("42")
	if err != nil {
	    fmt.Printf("couldn't convert number: %v\n", err)
	    return
	}
	fmt.Println("Converted integer:", i)

error 为 nil 时表示成功；非 nil 的 error 表示错误。

	package main
	
	import (
		"fmt"
		"time"
	)
	
	type MyError struct {
		When time.Time
		What string
	}
	
	func (e *MyError) Error() string {
		return fmt.Sprintf("at %v, %s",
			e.When, e.What)
	}
	
	func run() error {
		return &MyError{
			time.Now(),
			"it didn't work",
		}
	}
	
	func main() {
		if err := run(); err != nil {
			fmt.Println(err)
		}
	}

#### 练习：错误 ####

从先前的练习中复制 Sqrt 函数，并修改使其返回 error 值。

由于不支持复数，当 Sqrt 接收到一个负数时，应当返回一个非 nil 的错误值。

创建一个新类型

	type ErrNegativeSqrt float64

为其实现

	func (e ErrNegativeSqrt) Error() string

使其成为一个 error， 该方法就可以让 ErrNegativeSqrt(-2).Error() 返回 `"cannot Sqrt negative number: -2"`。

*注意：* 在 Error 方法内调用 fmt.Sprint(e) 将会让程序陷入死循环。可以通过先转换 e 来避免这个问题：fmt.Sprint(float64(e))。请思考这是为什么呢？

修改 Sqrt 函数，使其接受一个负数时，返回 ErrNegativeSqrt 值。

	package main
	
	import (
		"fmt"
	)
	
	func Sqrt(x float64) (float64, error) {
		return 0, nil
	}
	
	func main() {
		fmt.Println(Sqrt(2))
		fmt.Println(Sqrt(-2))
	}

#### Readers ####

io 包指定了 io.Reader 接口， 它表示从数据流结尾读取。

Go 标准库包含了这个接口的许多实现， 包括文件、网络连接、压缩、加密等等。

io.Reader 接口有一个 Read 方法：

	func (T) Read(b []byte) (n int, err error)

Read 用数据填充指定的字节 slice，并且返回填充的字节数和错误信息。 在遇到数据流结尾时，返回 io.EOF 错误。

例子代码创建了一个 strings.Reader。 并且以每次 8 字节的速度读取它的输出。

	package main
	
	import (
		"fmt"
		"io"
		"strings"
	)
	
	func main() {
		r := strings.NewReader("Hello, Reader!")
	
		b := make([]byte, 8)
		for {
			n, err := r.Read(b)
			fmt.Printf("n = %v err = %v b = %v\n", n, err, b)
			fmt.Printf("b[:n] = %q\n", b[:n])
			if err == io.EOF {
				break
			}
		}
	}

#### 练习：Reader ####

#### 练习：rot13Reader ####

#### Web 服务器 ####

包 http 通过任何实现了 http.Handler 的值来响应 HTTP 请求：

	package http
	
	type Handler interface {
	    ServeHTTP(w ResponseWriter, r *Request)
	}

在这个例子中，类型 Hello 实现了 http.Handler。

访问 http://localhost:4000/ 会看到来自程序的问候。

	package main
	
	import (
		"fmt"
		"log"
		"net/http"
	)
	
	type Hello struct{}
	
	func (h Hello) ServeHTTP(
		w http.ResponseWriter,
		r *http.Request) {
		fmt.Fprint(w, "Hello!")
	}
	
	func main() {
		var h Hello
		err := http.ListenAndServe("localhost:4000", h)
		if err != nil {
			log.Fatal(err)
		}
	}

#### 练习：HTTP 处理 ####

#### 图片 ####

Package image 定义了 Image 接口：

	package image
	
	type Image interface {
	    ColorModel() color.Model
	    Bounds() Rectangle
	    At(x, y int) color.Color
	}

注意：Bounds 方法的 Rectangle 返回值实际上是一个 image.Rectangle， 其定义在 image 包中。

color.Color 和 color.Model 也是接口，但是通常因为直接使用预定义的实现 image.RGBA 和 image.RGBAModel 而被忽视了。这些接口和类型由image/color 包定义。

	package main
	
	import (
		"fmt"
		"image"
	)
	
	func main() {
		m := image.NewRGBA(image.Rect(0, 0, 100, 100))
		fmt.Println(m.Bounds())
		fmt.Println(m.At(0, 0).RGBA())
	}

#### 练习：图片 ####

自定义的 Image 类型，要实现必要的方法，并且调用 pic.ShowImage。

Bounds 应当返回一个 image.Rectangle，例如 `image.Rect(0, 0, w, h)`。

ColorModel 应当返回 color.RGBAModel。

At 应当返回一个颜色；在这个例子里，在最后一个图片生成器的值 v 匹配 `color.RGBA{v, v, 255, 255}`。

	package main
	
	import "golang.org/x/tour/pic"
	
	type Image struct{}
	
	func main() {
		m := Image{}
		pic.ShowImage(m)
	}

## 并发 ##

### 并发 ###

#### goroutine ####

goroutine 是由 Go 运行时环境管理的轻量级线程。

	go f(x, y, z)

开启一个新的 goroutine 执行

	f(x, y, z)

f，x，y 和 z 是当前 goroutine 中定义的，但是在新的 goroutine 中运行 f。

goroutine 在相同的地址空间中运行，因此访问共享内存必须进行同步。sync 提供了这种可能，不过在 Go 中并不经常用到，因为有其他的办法。

	package main
	
	import (
		"fmt"
		"time"
	)
	
	func say(s string) {
		for i := 0; i < 5; i++ {
			time.Sleep(100 * time.Millisecond)
			fmt.Println(s)
		}
	}
	
	func main() {
		go say("world")
		say("hello")
	}

#### channel ####

channel 是有类型的管道，可以用 channel 操作符 <- 对其发送或者接收值。

	ch <- v    // 将 v 送入 channel ch。
	v := <-ch  // 从 ch 接收，并且赋值给 v。

（“箭头”就是数据流的方向。）

和 map 与 slice 一样，channel 使用前必须创建：

	ch := make(chan int)

默认情况下，在另一端准备好之前，发送和接收都会阻塞。这使得 goroutine 可以在没有明确的锁或竞态变量的情况下进行同步。

	package main
	
	import "fmt"
	
	func sum(a []int, c chan int) {
		sum := 0
		for _, v := range a {
			sum += v
		}
		c <- sum // 将和送入 c
	}
	
	func main() {
		a := []int{7, 2, 8, -9, 4, 0}
	
		c := make(chan int)
		go sum(a[:len(a)/2], c)
		go sum(a[len(a)/2:], c)
		x, y := <-c, <-c // 从 c 中获取
	
		fmt.Println(x, y, x+y)
	}

#### 缓冲 channel ####

channel 可以是 带缓冲的。为 make 提供第二个参数作为缓冲长度来初始化一个缓冲 channel：

	ch := make(chan int, 100)

向带缓冲的 channel 发送数据的时候，只有在缓冲区满的时候才会阻塞。 而当缓冲区为空的时候接收操作会阻塞。

	package main
	
	import "fmt"
	
	func main() {
		ch := make(chan int, 2)
		ch <- 1
		ch <- 2
		fmt.Println(<-ch)
		fmt.Println(<-ch)
	}

#### range 和 close ####

发送者可以 close 一个 channel 来表示再没有值会被发送了。接收者可以通过赋值语句的第二参数来测试 channel 是否被关闭：当没有值可以接收并且 channel 已经被关闭，那么经过

	v, ok := <-ch

之后 ok 会被设置为 false。

循环 `for i := range c` 会不断从 channel 接收值，直到它被关闭。

*注意：* 只有发送者才能关闭 channel，而不是接收者。向一个已经关闭的 channel 发送数据会引起 panic。 *还要注意：* channel 与文件不同；通常情况下无需关闭它们。只有在需要告诉接收者没有更多的数据的时候才有必要进行关闭，例如中断一个 range。

#### select ####

select 语句使得一个 goroutine 在多个通讯操作上等待。

select 会阻塞，直到条件分支中的某个可以继续执行，这时就会执行那个条件分支。当多个都准备好的时候，会随机选择一个。

	package main
	
	import "fmt"
	
	func fibonacci(c, quit chan int) {
		x, y := 0, 1
		for {
			select {
			case c <- x:
				x, y = y, x+y
			case <-quit:
				fmt.Println("quit")
				return
			}
		}
	}
	
	func main() {
		c := make(chan int)
		quit := make(chan int)
		go func() {
			for i := 0; i < 10; i++ {
				fmt.Println(<-c)
			}
			quit <- 0
		}()
		fibonacci(c, quit)
	}

#### 默认选择 ####

当 select 中的其他条件分支都没有准备好的时候，default 分支会被执行。

为了非阻塞的发送或者接收，可使用 default 分支

	select {
	case i := <-c:
	    // 使用 i
	default:
	    // 从 c 读取会阻塞
	}

----

	package main
	
	import (
		"fmt"
		"time"
	)
	
	func main() {
		tick := time.Tick(100 * time.Millisecond)
		boom := time.After(500 * time.Millisecond)
		for {
			select {
			case <-tick:
				fmt.Println("tick.")
			case <-boom:
				fmt.Println("BOOM!")
				return
			default:
				fmt.Println("    .")
				time.Sleep(50 * time.Millisecond)
			}
		}
	}

#### 练习：等价二叉树 ####

#### 练习：等价二叉树 ####

#### sync.Mutex ####

但是如果我们并不需要通信呢？比如说，如果我们只是想保证在每个时刻，只有一个 goroutine 能访问一个共享的变量从而避免冲突？

这里涉及的概念叫做 互斥，通常使用 _互斥锁_(mutex)_来提供这个限制。

Go 标准库中提供了 sync.Mutex 类型及其两个方法：

	Lock
	Unlock

我们可以通过在代码前调用 Lock 方法，在代码后调用 Unlock 方法来保证一段代码的互斥执行。 参见 Inc 方法。

我们也可以用 defer 语句来保证互斥锁一定会被解锁。参见 Value 方法。

#### 练习：Web 爬虫 ####


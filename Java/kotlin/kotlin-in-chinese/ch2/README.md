# 第二章 基础 #

## 2.1 基础类型 ##

### 数值 ###

|---|----|
|类型|字宽|
|Double|64|
|Float|32|
|Long|64|
|Int|32|
|Short|16|
|Byte|8|

### 字面值常量 ###
主要是以下几种字面值常量：

	--数型: 123 --长整型要加大写 L : 123L --16进制：0x0f --二进制：0b00001011

注意不支持８进制
Kotlin 也支持传统的浮点数表示：

	-- 默认 Doubles : 123.5 , 123.5e10 -- Floats 要添加 f 或 F ：123.5f
### 表示 ###
在 java 平台上，数值被 JVM 虚拟机以字节码的方式物理存储的，除非我们需要做可空标识(比如说 Int?) 或者涉及泛型。在后者中数值是装箱的。
注意装箱过的数值是不保留特征的：

	val a: Int = 10000
	print (a === a ) //打印 'true'
	val boxedA: Int? =a
	val anotherBoxedA: Int? = a

print (boxedA === anotherBoxedA ) //注意这里打印的是 'false'
另一方面，它们是值相等的：

	val a: Int = 10000
	print(a == a) // Prints 'true'
	val boxedA: Int? = a
	val anotherBoxedA: Int? = a
	print(boxedA == anotherBoxedA) // Prints 'true'

### 显式转换 ###

由于不同的表示，短类型不是长类型的子类型。如果是的话我们就会碰到下面这样的麻烦了

	//这是些伪代码，不能编译的
	val a: Int? =1 //一个装箱过的 Int (java.lang.Integer)
	val b: Long? = a // 一个隐式装箱的 Long (java.lang.Long)
	print( a == b )// 很惊讶吧　这次打印出的是 'false' 这是由于 Long 类型的 equals() 只有和 Long 比较才会相同

因此不止是恒等，有时候连等于都会悄悄丢失。
所以，短类型是不会隐式转换为长类型的。这意味着我们必须显式转换才能把 Byte 赋值给 Int 

	val b: Byte = 1 // OK, literals are checked statically
	val i: Int = b //ERROR
我们可以通过显式转换把数值类型提升

	val i: Int = b.toInt() // 显式转换
每个数值类型都支持下面的转换：

	toByte(): Byte
	toShort(): Short
	toInt(): Int
	toLong(): Long
	toFloat(): Float
	toDouble(): Double
	toChar(): Char
隐式转换一般情况下是不容易被发觉的，因为我们可以使用上下文推断出类型，并且算术运算会为合适的转换进行重载，比如

	val l = 1.toLong + 1 //Long  + Int => Long
### 运算符 ###

### 字符 ###
字符类型用 Char 表示。不能直接当做数值来使用

	fun check(c: Char) {
	    if (c == 1) { //ERROR: 类型不匹配
	        //...
	    }
	}
字符是由单引号包裹的'1'，特殊的字符通过反斜杠\转义，下面的字符序列支持转义：\t,\b,\n,\r,\',\",\和$。编码任何其他字符，使用 Unicode 转义语法：\uFF00。
我们可以将字符显示的转义为Int数字：

	fun decimalDigitValue(c: Char): Int {
	    if (c !in '0'..'9') 
	        throw IllegalArgumentException("Out of range")
	    return c.toInt() - '0'.toInt() //显示转换为数值类型
	}

和数值类型一样，需要一个可空引用时，字符会被装箱。特性不会被装箱保留。

### 布尔值 ###
布尔值只有 true 或者 false
布尔值的内建操作包括

	|| – lazy disjunction && – lazy conjunction

### Array ###

Arrays在 Kotlin 中由 Array 类表示，有 get 和 set 方法(通过运算符重载可以由[]调用)，以及 size 方法，以及一些常用的函数：

	class Array<T> private () {
	    fun size(): Int
	    fun get(index: Int): T
	    fun set(Index: Int, value: T): Uint
	    fun iterator(): Iterator<T>
	    //...
	}

我们可以给库函数 arrayOf() 传递每一项的值来创建Array，arrayOf(1, 2, 3) 创建了一个[1, 2, 3] 这样的数组。也可以使用库函数 arrayOfNulls() 创建一个指定大小的空Array。
或者通过指定Array大小并提供一个迭代器
(原文Another option is to use a factory function that takes the array size and the function that can return the initial value of each array element given its index)：

	// 创建一个 Array<String>  内容为 ["0", "1", "4", "9", "16"]
	val asc = Array(5, {i -> (i * i).toString() })

像我们上面提到的，[] 操作符表示调用　get() set() 函数
注意：和 java 不一样，arrays 在 kotlin 中是不可变的。这意味这 kotlin 不允许我们把 Array<String> 转为 Array<Any> ,这样就阻止了可能的运行时错误(但你可以使用 Array<outAny> , 参看 Type Projections)
Kotlin 有专门的类来表示原始类型从而避免过度装箱： ByteArray, ShortArray, IntArray 等等。这些类与 Array 没有继承关系，但它们有一样的方法与属性。每个都有对应的库函数：

	val x: IntArray = intArray(1, 2, 3)
	x[0] = x[1] + x[2]
### 字符串 ###

字符串是有 String 表示的。字符串是不变的。字符串的元素可以通过索引操作读取: s[i] 。字符串可以用 for 循环迭代：

	for (c in str) {
	    println(c)
	}

Kotlin 有俩种类型的 string ：一种是可以带分割符的，一种是可以包含新行以及任意文本的。带分割符的 string 很像 java 的 string:

	val s = "Hello World!\n"
整行String 是由三个引号包裹的("""),不可以包含分割符但可以包含其它字符：

	val text = """
	    for (c in "foo")
	        print(c)
	"""

### 模板 ###

字符串可以包含模板表达式。一个模板表达式由一个 $ 开始并包含另一个简单的名称：

	val i = 10
	val s = "i = $i" // 识别为 "i = 10"
或者是一个带大括号的表达式：

	val s = "abc"
	val str = "$s.length is ${s.length}" //识别为 "abc.length is 3"

## 2.2 包 ##
一个源文件以包声明开始

	package foo.bar
	
	fun bza() {}
	
	class Goo {}
	
	//...
源文件的所有内容(比如类和函数)都被包声明包括。因此在上面的例子中， bza() 的全名应该是 foo.bar.baz ，Goo 的全名是 foo.bar.Goo。
如果没有指定包名，那这个文件的内容就从属于没有名字的 "default" 包。

### Imports ###
除了模块中默认导入的包，每个文件都可以有它自己的导入指令。导入语法的声明在grammar中描述。
我们可以导入一个单独的名字，比如下面这样：

	import foo.Bar //Bar 现在可以不用条件就可以使用
或者范围内的所有可用的内容 (包，类，对象，等等):

	import foo.*/ /foo 中的所有都可以使用
如果命名有冲突，我们可以使用 as 关键字局部重命名解决冲突

	import foo.Bar // Bar 可以使用
	import bar.Bar as bBar // bBar 代表 'bar.Bar'

### 可见性和包嵌套 ###
如果最顶的声明标注为 private , 那么它是自己对应包私有 (参看 Visibility Modifiers)。如果包内有私有的属性或方法，那它对所有的子包是可见的。
注意包外的的成员是默认不导入的，比如在导入 foo.bar 后我们不能获得 foo 的成员
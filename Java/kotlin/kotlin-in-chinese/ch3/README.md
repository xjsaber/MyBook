# 第3章 类和对象 #

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

## 3.2 属性和字段 ##

### 属性声明 ###
在 Kotlin 中类可以有属性，我们可以使用 var 关键字声明可变属性，或者用 val 关键字声明只读属性。

	public class Address {     
	    public var name: String = ...
	      public var street: String = ...
	    public var city: String = ...
	      public var state: String? = ...
	    public var zip: String = ...
	}
我们可以像使用 java 中的字段那样,通过名字直接使用一个属性：

	fun copyAddress(address: Address) : Address {
	    val result = Address() //在 kotlin 中没有 new 关键字
	    result.name = address.name //accessors are called
	    result.street = address.street
	}

### Getter 和 Setter ###
声明一个属性的完整语法如下：

	var <propertyName>: <PropertyType> [ = <property_initializer> ]
    <getter>
    <setter>

语法中的初始化语句，getter 和 setter 都是可选的。如果属性类型可以从初始化语句或者类的成员函数中推断出来,那么他的类型也是忽略的。

例子：

	var allByDefault: Int? // 错误: 需要一个初始化语句, 默认实现了 getter 和 setter 方法
	var initialized = 1 // 类型为 Int, 默认实现了 getter 和 setter

只读属性的声明语法和可变属性的声明语法相比有两点不同: 它以 val 而不是 var 开头，不允许 setter 函数：

	val simple: Int? // 类型为 Int ，默认实现 getter ，但必须在构造函数中初始化
	val inferredType = 1 // 类型为 Int 类型,默认实现 getter
我们可以像写普通函数那样在属性声明中自定义的访问器，下面是一个自定义的 getter 的例子:

	var isEmpty: Boolean
    get() = this.size == 0
下面是一个自定义的setter:

	var stringRepresentation: String
	    get() = this.toString()
	    set (value) {
	        setDataFormString(value) // 格式化字符串,并且将值重新赋值给其他元素
	}
为了方便起见,setter 方法的参数名是value,你也可以自己任选一个自己喜欢的名称.
如果你需要改变一个访问器的可见性或者给它添加注解，但又不想改变默认的实现，那么你可以定义一个不带函数体的访问器:

	var settVisibilite: String = "abc"//非空类型必须初始化
	    private set // setter 是私有的并且有默认的实现
	var setterVithAnnotation: Any?
	    @Inject set // 用 Inject 注解 setter

### 备用字段 ###

如果你想要做一些事情但不适合这种 "隐含备用字段" 方案，你可以试着用备用属性的方式：
	private var _table: Map<String, Int>? = null
	public val table: Map<String, Int>
	    get() {
	    if (_table == null)
	        _table = HashMap() //参数类型是推导出来的
	        return _table ?: throw AssertionError("Set to null by another thread")
	    }
综合来讲，这些和 java 很相似，可以避免函数访问私有属性而破坏它的结构

### 编译时常量 ###

那些在编译时就能知道具体值的属性可以使用 const 修饰符标记为 编译时常量. 这种属性需要同时满足以下条件:

* Top-level or member of an object 
* 以String或基本类型进行初始化
* 没有自定义getter

这种属性可以被当做注解使用:

	const val SUBSYSTEM_DEPRECATED: String = "This subsystem is deprecated"
	@Deprected(SUBSYSTEM_DEPRECATED) fun foo() { ... }

### 延迟初始化属性 ###
通常,那些被定义为拥有非空类型的属性,都需要在构造器中初始化.但有时候这并没有那么方便.例如在单元测试中,属性应该通过依赖注入进行初始化, 或者通过一个 setup 方法进行初始化.在这种条件下,你不能在构造器中提供一个非空的初始化语句,但是你仍然希望在访问这个属性的时候,避免非空检查.
为了处理这种情况,你可以为这个属性加上 lateinit 修饰符

	public class MyTest {
	    lateinit var subject: TestSubject
	
	    @SetUp fun setup() {
	        subject = TestSubject()
	    }
	
	    @Test fun test() {
	        subject.method() 
	    }
	}

这个修饰符只能够被用在类的 var 类型的可变属性定义中,不能用在构造方法中.并且属性不能有自定义的 getter 和 setter访问器.这个属性的类型必须是非空的,同样也不能为一个基本类型.

在一个延迟初始化的属性初始化前访问他,会导致一个特定异常,告诉你访问的时候值还没有初始化.

### 复写属性 ###
参看复写成员
### 代理属性 ###
最常见的属性就是从备用属性中读（或者写）。另一方面，自定义的 getter 和 setter 可以实现属性的任何操作。有些像懒值( lazy values )，根据给定的关键字从 map 中读出，读取数据库，通知一个监听者等等，像这些操作介于 getter setter 模式之间。

像这样常用操作可以通过代理属性作为库来实现。更多请参看这里。
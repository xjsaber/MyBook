# Getting Started #

## Basic Syntax ##

### Defining packages ###

	package my.demo
	
	import java.util.*
	
	// ...

#### Defining functions ####

	fun sum(a: Int, b: Int): Int {
	    return a + b
	}

#### Defining local variables ####

	val a: Int = 1  // immediate assignment
	val b = 2   // `Int` type is inferred
	val c: Int  // Type required when no initializer is provided
	c = 3       // deferred assignment

#### Comments ####

	// This is an end-of-line comment
	
	/* This is a block comment
	   on multiple lines. */

#### Using string templates ####

	var a = 1
	// simple name in template:
	val s1 = "a is $a" 
	
	a = 2
	// arbitrary expression in template:
	val s2 = "${s1.replace("is", "was")}, but now is $a"

#### Using conditional expressions ####

	fun maxOf(a: Int, b: Int): Int {
	    if (a > b) {
	        return a
	    } else {
	        return b
	    }
	}

	fun maxOf(a: Int, b: Int) = if (a > b) a else b

#### Using nullable values and checking for null ####

#### Using type checks and automatic casts ####

#### Using a for loop ####



#### Using a while loop ####

	val items = listOf("apple", "banana", "kiwi")
	var index = 0
	while (index < items.size) {
	    println("item at $index is ${items[index]}")
	    index++
	}

#### Using when expression ####

	fun describe(obj: Any): String =
	when (obj) {
	    1          -> "One"
	    "Hello"    -> "Greeting"
	    is Long    -> "Long"
	    !is String -> "Not a string"
	    else       -> "Unknown"
	}

#### Using ranges ####

	for (x in 1..5) {
	    print(x)
	}

	for (x in 1..10 step 2) {
	    print(x)
	}
	for (x in 9 downTo 0 step 3) {
	    print(x)
	}

#### Using collections ####

	for (item in items) {
	    println(item)
	}

Basic Syntax
Idioms
Coding Conventions
Basics
Basic Types
Packages
Control Flow
Returns and Jumps
Classes and Objects
Functions and Lambdas
Other
Reference
Java Interop
JavaScript
Tools
FAQ
Full Kotlin Reference
 Edit Page
Basic Syntax

Defining packages

Package specification should be at the top of the source file:

package my.demo

import java.util.*

// ...
It is not required to match directories and packages: source files can be placed arbitrarily in the file system.

See Packages.

Defining functions

Function having two Int parameters with Int return type:


fun sum(a: Int, b: Int): Int {
    return a + b
}
fun sum(a: Int, b: Int): Int {
    return a + b
}
sum of 3 and 5 is 8
Target platform: JVMRunning on kotlin v. 1.1.2
Function with an expression body and inferred return type:


fun sum(a: Int, b: Int) = a + b
Target platform: JVMRunning on kotlin v. 1.1.2
Function returning no meaningful value:


fun printSum(a: Int, b: Int): Unit {
    println("sum of $a and $b is ${a + b}")
}
sum of -1 and 8 is 7
Target platform: JVMRunning on kotlin v. 1.1.2
Unit return type can be omitted:


fun printSum(a: Int, b: Int) {
    println("sum of $a and $b is ${a + b}")
}
sum of -1 and 8 is 7
Target platform: JVMRunning on kotlin v. 1.1.2
See Functions.

Defining local variables

Assign-once (read-only) local variable:


val a: Int = 1  // immediate assignment
val b = 2   // `Int` type is inferred
val c: Int  // Type required when no initializer is provided
c = 3       // deferred assignment
val a: Int = 1  // immediate assignment
val b = 2   // `Int` type is inferred
val c: Int  // Type required when no initializer is provided
c = 3       // deferred assignment
Target platform: JVMRunning on kotlin v. 1.1.2
Mutable variable:


var x = 5 // `Int` type is inferred
x += 1
Target platform: JVMRunning on kotlin v. 1.1.2
See also Properties And Fields.

Comments

Just like Java and JavaScript, Kotlin supports end-of-line and block comments.

// This is an end-of-line comment

/* This is a block comment
   on multiple lines. */
Unlike Java, block comments in Kotlin can be nested.

See Documenting Kotlin Code for information on the documentation comment syntax.

Using string templates


var a = 1
// simple name in template:
val s1 = "a is $a" 

a = 2
// arbitrary expression in template:
val s2 = "${s1.replace("is", "was")}, but now is $a"
var a = 1
// simple name in template:
val s1 = "a is $a" 
​
a = 2
// arbitrary expression in template:
val s2 = "${s1.replace("is", "was")}, but now is $a"
Target platform: JVMRunning on kotlin v. 1.1.2
See String templates.

Using conditional expressions


fun maxOf(a: Int, b: Int): Int {
    if (a > b) {
        return a
    } else {
        return b
    }
}
fun maxOf(a: Int, b: Int): Int {
    if (a > b) {
        return a
    } else {
        return b
    }
}
Target platform: JVMRunning on kotlin v. 1.1.2
Using if as an expression:


fun maxOf(a: Int, b: Int) = if (a > b) a else b
fun maxOf(a: Int, b: Int) = if (a > b) a else b
max of 0 and 42 is 42
Target platform: JVMRunning on kotlin v. 1.1.2
See if-expressions.

Using nullable values and checking for null

A reference must be explicitly marked as nullable when null value is possible.

Return null if str does not hold an integer:

fun parseInt(str: String): Int? {
    // ...
}
Use a function returning nullable value:


fun printProduct(arg1: String, arg2: String) {
    val x = parseInt(arg1)
    val y = parseInt(arg2)
​
    // Using `x * y` yields error because they may hold nulls.
    if (x != null && y != null) {
        // x and y are automatically cast to non-nullable after null check
        println(x * y)
    }
    else {
        println("either '$arg1' or '$arg2' is not a number")
    }    
}
Target platform: JVMRunning on kotlin v. 1.1.2
or


// ...
if (x == null) {
    println("Wrong number format in arg1: '${arg1}'")
    return
}
if (y == null) {
    println("Wrong number format in arg2: '${arg2}'")
    return
}
​
// x and y are automatically cast to non-nullable after null check
println(x * y)
Target platform: JVMRunning on kotlin v. 1.1.2
See Null-safety.

Using type checks and automatic casts

The is operator checks if an expression is an instance of a type. If an immutable local variable or property is checked for a specific type, there's no need to cast it explicitly:


fun getStringLength(obj: Any): Int? {
    if (obj is String) {
        // `obj` is automatically cast to `String` in this branch
        return obj.length
    }
​
    // `obj` is still of type `Any` outside of the type-checked branch
    return null
}
Target platform: JVMRunning on kotlin v. 1.1.2
or


fun getStringLength(obj: Any): Int? {
    if (obj !is String) return null
​
    // `obj` is automatically cast to `String` in this branch
    return obj.length
}
Target platform: JVMRunning on kotlin v. 1.1.2
or even


fun getStringLength(obj: Any): Int? {
    // `obj` is automatically cast to `String` on the right-hand side of `&&`
    if (obj is String && obj.length > 0) {
        return obj.length
    }
​
    return null
}
Target platform: JVMRunning on kotlin v. 1.1.2
See Classes and Type casts.

Using a for loop


val items = listOf("apple", "banana", "kiwi")
for (item in items) {
    println(item)
}
Target platform: JVMRunning on kotlin v. 1.1.2
or


val items = listOf("apple", "banana", "kiwi")
for (index in items.indices) {
    println("item at $index is ${items[index]}")
}
Target platform: JVMRunning on kotlin v. 1.1.2
See for loop.

Using a while loop


val items = listOf("apple", "banana", "kiwi")
var index = 0
while (index < items.size) {
    println("item at $index is ${items[index]}")
    index++
}
val items = listOf("apple", "banana", "kiwi")
var index = 0
while (index < items.size) {
    println("item at $index is ${items[index]}")
    index++
}
item at 0 is apple
item at 1 is banana
item at 2 is kiwi
Target platform: JVMRunning on kotlin v. 1.1.2
See while loop.

Using when expression


fun describe(obj: Any): String =
when (obj) {
    1          -> "One"
    "Hello"    -> "Greeting"
    is Long    -> "Long"
    !is String -> "Not a string"
    else       -> "Unknown"
}
Target platform: JVMRunning on kotlin v. 1.1.2
See when expression.

#### Using ranges ####

Check if a number is within a range using in operator:


val x = 10
val y = 9
if (x in 1..y+1) {
    println("fits in range")
}
fits in range
Target platform: JVMRunning on kotlin v. 1.1.2
Check if a number is out of range:


val list = listOf("a", "b", "c")
​
if (-1 !in 0..list.lastIndex) {
    println("-1 is out of range")
}
if (list.size !in list.indices) {
    println("list size is out of valid list indices range too")
}
Target platform: JVMRunning on kotlin v. 1.1.2
Iterating over a range:


for (x in 1..5) {
    print(x)
}

Target platform: JVMRunning on kotlin v. 1.1.2
or over a progression:


for (x in 1..10 step 2) {
    print(x)
}
for (x in 9 downTo 0 step 3) {
    print(x)
}
for (x in 1..10 step 2) {
    print(x)
}
for (x in 9 downTo 0 step 3) {
    print(x)
}
135799630
Target platform: JVMRunning on kotlin v. 1.1.2
See Ranges.

#### Using collections ####

Iterating over a collection:

	for (item in items) {
	    println(item)
	}

Checking if a collection contains an object using in operator:

	fun main(args: Array<String>) {
	    val items = setOf("apple", "banana", "kiwi")
	    when {
	        "orange" in items -> println("juicy")
	        "apple" in items -> println("apple is fine too")
	    }
	}

Using lambda expressions to filter and map collections:

	fun main(args: Array<String>) {
	    val fruits = listOf("banana", "avocado", "apple", "kiwi")
	    fruits
	    .filter { it.startsWith("a") }
	    .sortedBy { it }
	    .map { it.toUpperCase() }
	    .forEach { println(it) }
	}

## Idioms ##

#### Creating DTOs (POJOs/POCOs) ####

	data class Customer(val name: String, val email: String)

* getters (and setters in case of vars) for all properties
* equals()
* hashCode()
* toString()
* copy()
* component1(), component2(), …, for all properties

#### Default values for function parameters ####


#### Filtering a list ####

	val positives = list.filter { x -> x > 0 }
	
	val positives = list.filter { it > 0 }

#### String Interpolation ####

#### Instance Checks ####

	when (x) {
	    is Foo -> ...
	    is Bar -> ...
	    else   -> ...
	}

#### Traversing a map/list of pairs ####

	for ((k, v) in map) {
	    println("$k -> $v")
	}
	k, v can be called anything.

#### Using ranges ####

	for (i in 1..100) { ... }  // closed range: includes 100
	for (i in 1 until 100) { ... } // half-open range: does not include 100
	for (x in 2..10 step 2) { ... }
	for (x in 10 downTo 1) { ... }
	if (x in 1..10) { ... }

#### Read-only list ####

	val list = listOf("a", "b", "c")

#### Read-only map ####

	val map = mapOf("a" to 1, "b" to 2, "c" to 3)

#### Accessing a map ####

	println(map["key"])
	map["key"] = value

#### Lazy property ####

	val p: String by lazy {
	    // compute the string
	}

#### Extension Functions ####

	fun String.spaceToCamelCase() { ... }
	
	"Convert this to camelcase".spaceToCamelCase()

#### Creating a singleton ####

	object Resource {
	    val name = "Name"
	}

#### If not null shorthand ####

	val files = File("Test").listFiles()
	
	println(files?.size)

#### If not null and else shorthand ####

	val files = File("Test").listFiles()
	
	println(files?.size ?: "empty")

#### Executing a statement if null ####

	val data = ...
	val email = data["email"] ?: throw IllegalStateException("Email is missing!")

#### Single-expression functions ####

	fun theAnswer() = 42
	fun theAnswer(): Int {
		return 42
	}

#### Calling multiple methods on an object instance ('with') ####

	class Turtle {
	    fun penDown()
	    fun penUp()
	    fun turn(degrees: Double)
	    fun forward(pixels: Double)
	}
	
	val myTurtle = Turtle()
	with(myTurtle) { //draw a 100 pix square
	    penDown()
	    for(i in 1..4) {
	        forward(100.0)
	        turn(90.0)
	    }
	    penUp()
	}

#### Java 7's try with resources ####

	val stream = Files.newInputStream(Paths.get("/some/file.txt"))
	stream.buffered().reader().use { reader ->
	    println(reader.readText())
	}

#### Convenient form for a generic function that requires the generic type information ####

	//  public final class Gson {
	//     ...
	//     public <T> T fromJson(JsonElement json, Class<T> classOfT) throws JsonSyntaxException {
	//     ...
	
	inline fun <reified T: Any> Gson.fromJson(json): T = this.fromJson(json, T::class.java)

#### Consuming a nullable Boolean ####

	val b: Boolean? = ...
	if (b == true) {
	    ...
	} else {
	    // `b` is false or null
	}

## Coding Conventions ##

#### Naming Style ####

* use of camelCase for names (and avoid underscore in names)
* types start with upper case
* methods and properties start with lower case
* use 4 space indentation
* public functions should have documentation such that it appears in Kotlin Doc

#### Colon ####
There is a space before colon where colon separates type and supertype and there's no space where colon separates instance and type

	interface Foo<out T : Any> : Bar {
	    fun foo(a: Int): T
	}

#### Lambdas ####

In lambda expressions, spaces should be used around the curly braces, as well as around the arrow which separates the parameters from the body. Whenever possible, a lambda should be passed outside of parentheses.

	list.filter { it > 10 }.map { element -> element * 2 }

#### Class header formatting ####

Classes with a few arguments can be written in a single line:

	class Person(id: Int, name: String)

#### Unit ####

#### Functions vs Properties ####

* does not throw
* has a O(1) complexity
* is cheap to calculate (or caсhed on the first run)
* returns the same result over invocations
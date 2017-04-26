# Scala Collection #

## Scala List(列表) ##

Scala 列表类似于数组，它们所有元素的类型都相同，但是它们也有所不同：列表是不可变的，值一旦被定义了就不能改变，其次列表 具有递归的结构（也就是链接表结构）而数组不是。

列表基本操作

Scala列表有三个基本操作：

* head 返回列表第一个元素
* tail 返回一个列表，包含除了第一元素之外的其他元素
* isEmpty 在列表为空时返回true

连接列表
你可以使用 ::: 运算符或 List.:::() 方法或 List.concat() 方法来连接两个或多个列表

List.fill()

使用 List.fill() 方法来创建一个指定重复数量的元素列表

List.tabulate()
List.tabulate() 方法是通过给定的函数来创建列表。 
方法的第一个参数为元素的数量，可以是二维的，第二个参数为指定的函数，我们通过指定的函数计算结果并返回值插入到列表中，起始值为 0

List.tabulate()
List.tabulate() 方法是通过给定的函数来创建列表。 
方法的第一个参数为元素的数量，可以是二维的，第二个参数为指定的函数，我们通过指定的函数计算结果并返回值插入到列表中，起始值为 0

List.reverse
List.reverse 用于将列表的顺序反转

def +:(elem: A): List[A]
为列表预添加元素
def ::(x: A): List[A]
在列表开头添加元素
def :::(prefix: List[A]): List[A]
在列表开头添加指定列表的元素

## Scala Set(集合) ##

Scala Set(集合)是没有重复的对象集合，所有的元素都是唯一的。
Scala 集合分为可变的和不可变的集合。
默认情况下，Scala 使用的是不可变集合，如果你想使用可变集合，需要引用 scala.collection.mutable.Set 包。
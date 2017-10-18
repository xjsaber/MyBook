# 第18章 Java I/O系统 #

对程序语言的设计者来说，创建一个好的输入/输出(I/O)系统是一项艰难的任务。

自从Java 1.0版本以来，Java的I/O类库发生了明显改变，在原来面向字节的类中添加了面向字符和基于Unicode的类。在JDK1.4中，添加了nio类。

## 18.1 File类 ##

File(文件)类既能代表一个特定文件的名称，又能代表一个目录下的一组文件的名称。如果它指的是一个文件集，可以对此集合调用list()方法，会返回一个字符数组。

如果我们想取得不同的目录列表，只需要再创建一个不同的File对象就可以了，实际上可以使用FilePath(文件路径)，还包括了与它相关的FilenameFilter接口。

### 18.1.1 目录列表器 ###

File，调用不带参数的list()方法，便可以获得此File对象包含的全部列表。然后，如果还想获得一个受限列表，就需要使用“目录过滤器”。

	java.utils.Arrays.sort()和String.CASE_INSENSITIVE.ORDERComparator，对结果进行排序（按字符顺序）


filter类“实现”了FilenameFilter接口

accept()会使用一个正则表达式的match对象，来查看此正则表达式regex是否匹配这个文件的名字。通过使用accept()，list()方法会返回一个数组。

#### 匿名内部类 ####

解决特定问题的代码隔离、聚拢于一点。而另一方面，这种方法却不易阅读，因此谨慎使用。

### 18.1.2 目录使用工具 ###

实用工具类就可以通过使用local()方法产生由本地目录目录中的文件构成的File对象数组，或者通过使用walk()方法产生给定目录下的由整个目录树中所有文件构成的List<File>

local()方法使用被称为listFile()的File.list()的变体来产生File数组。FilenameFilter -> filter，Arrays.asList() -> list

walk()方法将开始目录的名字转换为File对象，然后调用recurseDirs()，该方法将递归地遍历目录，并在每次递归中都收集更多的信息。

### 18.1.3 目录的检查及创建 ###


## 18.2 输入和输出 ##

编程语言的I/O类库中常使用流“这个抽象概念”，它该表任何有能力产出数据的数据源对象或者是有能力接收数据的接收端对象。“流”屏蔽了实际的I/O设备中处理数据的细节。

Java类库中的I/O类分成输入和输出两部分。

1. 通过继承，任何自InputStream或Reader派生而来的类都含有名为read()的基本方法，用来读取单个字节或者字节数组。
2. 任何自OutputStream或Writer派生而来的类都含有名为write()的基本方法，用于写单个字节或者字节数组。

类库的设计者首先限定与输入有关的所有类都应该从InputStream继承，而与输出有关的所有类都应该从OutputStream继承。

### 18.2.1 InputStream类型 ###

InputStream的作用是用来表示那些从不同数据源产生输入的类。

1. 字节数组
2. String对象
3. 文件
4. “管道”，工作方式与实际管道相似，即，从一端输入，从另一端输出。
5. 一个由其他种类的流组成的序列，以便我们可以将它们收集合并到一个流内。
6. 其他数据源，如Internet连接等。

每一种数据源都有相应的InputStream子类。另外，FilterInputStream也属于一种InputStream，为“装饰器”（decorator）类提供基类，其中，“装饰器”类可以把属性或有用的接口与输入流连接在一起。

#### InputStream类型 ####

|类|功能|构造器参数|
|--|--|--|
|||如何使用|
|ByteArrayInputStream|允许将内存的缓冲区当做InputStream使用|缓冲区，字节将从中取出  作为一种数据源：将其与FilterInputStream对象相连以提供有用接口|
|StringBufferInputStream|将String转换成InputStream|字符串，底层实现实际使用StringBuffer  作为一种数据源：将其与FilterInputStream对象相连以提供有用接口|
|FileInputStream|用于从文件中读取信息|字符串，表示文件名、文件或FileDescriptor对象  作为一种数据源：将其与FilterInputStream对象相连以提供有用接口|


## 18.3 添加属性和有用的接口 ##

## 18.4 Reader和Writer ##

InputStream和OutputStream在以面向字节形式的I/O中扔可以提供极有价值的功能，Reader和Writer则提供兼容Unicode与面向字符的I/O功能

1）Java 1.1向InputStream和OutputStream继承层次结构中添加了一些新类，所以显然这两个类是不会被取代的。
2）来自于“字节”层次结构中的类和“字符”层次结构中的类结合起来使用。为了实现这个目的，要用到“适配器”（adapter）类：InputStreamReader可以把InputStream转换为Reader，而OutputStreamWriter可以把OutputStream转换为Writer。

### 18.4.1 数据的来源和去处 ###



## 18.15 总结 ##
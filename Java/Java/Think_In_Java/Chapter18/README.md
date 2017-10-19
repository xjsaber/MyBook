# 第18章 Java I/O系统 #

对程序语言的设计者来说，创建一个好的输入/输出(I/O)系统是一项艰难的任务。

自从Java 1.0版本以来，Java的I/O类库发生了明显改变，在原来面向字节的类中添加了面向字符和基于Unicode的类。在JDK1.4中，添加了nio类。

## 18.1 File类 ##

File（文件）类这个名字有一定的误导性。

File(文件)类既能代表一个特定文件的名称，又能代表一个目录下的一组文件的名称。如果它指的是一个文件集，可以对此集合调用list()方法，会返回一个字符数组。

如果我们想取得不同的目录列表，只需要再创建一个不同的File对象就可以了，实际上可以使用FilePath(文件路径)，还包括了与它相关的FilenameFilter接口。

### 18.1.1 目录列表器 ###

File，调用不带参数的list()方法，便可以获得此File对象包含的全部列表。然后，如果还想获得一个受限列表，就需要使用“目录过滤器”。

	java.utils.Arrays.sort()和String.CASE_INSENSITIVE.ORDERComparator，对结果进行排序（按字符顺序）


filter类“实现”了FilenameFilter接口

accept()会使用一个正则表达式的match对象，来查看此正则表达式regex是否匹配这个文件的名字。通过使用accept()，list()方法会返回一个数组。

#### 匿名内部类 ####

解决特定问题的代码隔离、聚拢于一点。而另一方面，这种方法却不易阅读，因此谨慎使用。

//TODO 练习 1-3

### 18.1.2 目录使用工具 ###

实用工具类就可以通过使用local()方法产生由本地目录目录中的文件构成的File对象数组，或者通过使用walk()方法产生给定目录下的由整个目录树中所有文件构成的List<File>

local()方法使用被称为listFile()的File.list()的变体来产生File数组。FilenameFilter -> filter，Arrays.asList() -> list

walk()方法将开始目录的名字转换为File对象，然后调用recurseDirs()，该方法将递归地遍历目录，并在每次递归中都收集更多的信息。

### 18.1.3 目录的检查及创建 ###

File类不仅仅只代表存在的文件或目录。也可以用File对象来创建新的目录或商不存在的整个目录路径。

可以查看文件的特性（如：大小、最后修改日期，读/写），检查某个File对象代表的是一个文件还是一个目录，并可以删除文件。

//TODO 练习 6

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
|PipedInputStream|产生用于写入相关PipedOutput Stream的数据。实现“管道化”概念|PipedOutputStream 作为多线程中数据源：将其与FilterInputStream对象相连以提供有用接口|
|SequenceInputStream|将两个或多个InputStream对象转换成单一InputStream|两个InputStream对象或一个容纳InputStream对象的容器Enumeration  作为一种数据源：将其与FilterInputStream对象相连以提供有用接口|
|FilterInputStream|抽象类，作为“装饰器”的接口。其中，“装饰器”为其他的InputStream类提供有用功能||

### 18.2.2 OutputStream类型 ###

该类别的类决定了输出所要去往的目标：字节数组（但不是String，不过当然可以用字节数组自己创建）、文件或管道。

FilterOutputStream为“装饰器”类提供了一个基类，“装饰器”类把属性或者有用的接口与输出流连接了起来。

#### OutputStream类型 ####

|类|功能|构造器参数|
|--|--|--|
|||如何使用|
|ByteArrayOutputStream|再内存中创建缓冲区。所有送往“流”的数据都要放置再此缓冲区|缓冲区初始化尺寸（可选的）  用于指定数据的目的地：将其与FilterOutputStream对象相连以提供有用接口|
|FileOutputStream|用于将信息写至文件|字符串，表示文件名、文件或FileDescriptor对象  指定数据的目的地：将其与FilterOutputStream对象相连以提供有用接口|
|PipedOutputStream|任何写入其中的信息都会自动作为相关PipedInputStream的输出。实现“管道化”概念|PipedInputStream  指定用于多线程的数据的目的地：将其与FilterOutputStream对象相连以提供有用接口|
|FilterOutputStream|抽象类，作为“装饰器”的接口。其中，“装饰器”为其他的OutputStream类提供有用功能||

## 18.3 添加属性和有用的接口 ##

Java I/O类库需要多种不同功能的组合，这正是使用装饰器模式的理由所在。

### 18.3.1 通过FilterInputStream从InputStream读取数据 ###

FilterInputStream

DataInputStream允许读取不同的基本类型数据以及String对象（）

#### FilterInputStream类型 ####

|类|功能|构造器参数
|--|--|--|
|||如何使用|
|DataInputStream|与DataOutputStream搭配使用，因此可以按照可移植方式从流读取基本数据类型（int，char，long等）|InputStream包含用于读取基本类型数据的全部接口
|BufferedInputStream|使用它可以防止每次读取时都得进行实际写操作。代表“使用缓冲区”|InputStream，可以指定缓冲区大小（可选的）  本质上不提供接口，只不过是向进程中添加缓冲区所必需的。与接口对象搭配
|LineNumberInputStream|跟踪输入流的行号；可调用getLineNumber（）和setLineNumber（int）|InputStream 仅增加了行号，因此可能要与接口对象搭配使用
|PushbackInputStream|具有“能弹出一个字节的缓冲区”。因此可以将读到的最后一个字符回退|InputStream 通常作为编译器的扫描器，之所以包含再内是因为Java编译器的需要，可能永远不会用到 

### 18.3.2 通过FilterOutPutStream向OutputStream写入 ###

与DataInputStream对应的是DataOutputStream，可以将各种基本数据类型以及String对象格式化输出到“流”中；这样一来，任何机器上的任何DataInputStream都能够读取它们。

PrintStream最初的目的是以可视化格式打印所有的基本数据以及String对象。这和DataOutputStream不同，后者的目的是将数据元素置入“流”中，使DataInputStream能够可移植地重构他它们

PrintStream内有两个重要的方法：print()和println()。对它们进行了重载，以便可打印出各种数据类型。print()和println()之间的差异是，后者在操作完毕后添加一个换行符。

BufferedOutputStream是一个修改过的OutputStream，它对数据流使用缓冲技术；因此当每次向流写入时，不必每次都进行实际的物理写动作。所以在进行输出时。

**FilterOutputStream类型**

|类|功能|构造器参数|
|--|--|--|
|||如何使用|
|DataOutputStream|与DataInputStream搭配使用，因此可以按照可移植方式向流中写入基本类型数据（int，char，long等）|OutputStream 包含用于写入基本类型数据的全部接口|
|PintStream|用于产生格式化输出。其中DataOutputStream处理数据的存储，PrintStream处理显示|OutputStream，可以用boolean值指示是否在每次换行时清空缓冲区（可选的）应该是对OutputStream对象的“final”封装。|
|BufferedOutputStream|使用它以避免每次发送数据都要进行实际的写操作。代表“使用缓冲区”。可以调用flush()清空缓冲区|OutputStream，可以指定缓冲区大小（可选的） 本质上并不提供接口，只不过是向进程中添加缓冲区所需的。与接口对象搭配|

## 18.4 Reader和Writer ##

InputStream和OutputStream在以面向字节形式的I/O中扔可以提供极有价值的功能，Reader和Writer则提供兼容Unicode与面向字符的I/O功能

1）Java 1.1向InputStream和OutputStream继承层次结构中添加了一些新类，所以显然这两个类是不会被取代的。
2）来自于“字节”层次结构中的类和“字符”层次结构中的类结合起来使用。为了实现这个目的，要用到“适配器”（adapter）类：InputStreamReader可以把InputStream转换为Reader，而OutputStreamWriter可以把OutputStream转换为Writer。

设计Reader和Writer继承层次结构主要是为了国际化。老的I/O流继承层次结构仅支持8位字节流，并且不能很好地处理16位的Unicode字符。由于Unicode用于字符国际化（Java本身的char也是16位的Unicode）。所以添加Reader和Writer继承层次就是为了在所有的I/O操作中都支持Unicode。

另外，新类哭的设计使得它的操作比旧类库更快。

### 18.4.1 数据的来源和去处 ###

I/O流类都有相应的Reader和Writer类来提供天然的Unicode操作。然而再某些场合，面向自己的InputStream和OutputStream才是正确的解决方案；特别是java.util.zip类库就是面向自己的而不是面向字符的。

尽量尝试Reader和Writer


|来源与去处Java1.0类:|:相应的Java1.1类|
|--|--|
|InputStream|Reader 适配器：InputStreamReader|
|OutputStream|Writer 适配器：InputStreamReader|

### 18.4.2 更改流的行为 ###

对于InputStream和Out

## 18.15 总结 ##
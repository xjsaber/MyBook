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

|过滤器：Java1.0类 |相应的Java1.1类|
|--|--|
|FilterInputStream|FilterReader|
|FilterOutputStream|FilterWriter(抽象类，没有子类)|
|BuffererdInputStream|BufferedReader(也有readLine())|
|DataInputStream|使用DataInputStream（除了当需要使用readLine()时以外，这时应该使用BufferedReader）|
|PrintStream|PrintWriter|
|LineNumberInputStream(已弃用)|LineNumberReader|
|StreamTokenizer|StreamTokenizer（使用接受Reader的构造器）|
|PushbackInputStream|PubshbackReader|

无论何时使用readerLine()，都不应该使用DataInputStream（这会遭到编译器的警告），而应该使用BufferedReader。除了这点，DataInputStream仍是I/O类库的首选成员。

PrintWriter的格式化接口实际上与PrintStream相同。

在Java SE5中天加了PrintWriter构造器，以简化在输出写入时文件的创建过程。

PrintWriter构造器还有一个选项，就是“自动执行清空”选项。如果构造器设置此选项，则在每次Println()执行之后，便会自动清空。

### 18.4.3 未发生变化的类 ###

|以下这些Java1.0类在Java1.1类中没有相应类|
|--|
|DataOutputStream|
|File|
|RandomAccessFile|
|SequenceInputStream|

特别是DataOutputStream，在使用时没有任何边划；因此如果想以“可传输的”格式存储和检索数据，可以使用InputStream和OutputStream继承层次结构。

## 18.5 自我独立的类：RandomAccessFile ##

## 18.6 I/O流的典型使用方式 ##

### 18.6.1 缓冲输入文件 ###

如果想要打开一个文件用于字符输入，可以使用以String或File对象作为文件名的FileInputReader。（为了提高速度，对文件进行缓冲，产生的引用传给一个BufferedReader构造器）由于BufferedReader也提供readLine()方法，所以这是我们的最终对象和进行读取的结构。当readLine()将返回null，你就达到了文件的末尾。

练习 7-11

### 18.6.2 从内存输入 ###

从BufferedInputFile.read()读入的String结果被用来创建一个StringReader。然后调用read()每次读取一个字符，并把它发送到控制台。

唯一安全的方式就是对文件显式地调用close()

### 18.6.3 格式化的内存输入 ###

要读取格式化数据，可以使用DataInputStream，它是一个面向字节的I/O类（不是面向字符的）。因此必须使用InputStream类而不是Reader类。可以用InputStream以字节的形式读取任何数据（例如一个文件）。

如果从DataInputStream用readByte()一次一次字节地读取字符，那么任何字节的值都是合法的结果，因此返回值不能用来检测输入是否结束。相反，可以使用available()方法查看还有多少可供存取的字符。

available()的工作方式会随着所读取的媒介类型的不同而有所不同；字面意思就是“在没有阻塞的情况下所能读取的字节数”。对于文件，这意味着整个文件；但是对于不同类型的流，可能就不是这样了，因此要谨慎使用。

### 18.6.4 基本的文件输出 ###

FileWriter对象可以向文件写入数据。首先，创建一个与指定文件连接的FileWriter。实际上，通常会用BufferedWriter将其包装起来用以缓冲输出（尝试移除此包装来感受对性能的影响——缓冲往往能显著地增加I/O操作的性能）。

#### 文本文件输出的快捷方式 ####

Java SE5在PrintWriter中添加了一个辅助构造器，使得不必每次希望创建文本文件并想其中写入时，都去执行所有的装饰工作。

TODO 练习 12-14

### 18.6.5 存储和恢复数据 ###

PrintWriter可以对数据进行格式化。但为了输出可供另一个“流”恢复的数据，我们需要用DataOutputStream写入数据，并用DataInputStream恢复数据。

DataOutputStream和DataInputStream是面向字节的，因此使用InputStream和OutputStream。

### 18.6.6 读写随机访问文件 ###

使用RandomAccessFile，类似于组合使用了DataInputStream和DataOutputStream（因为实现了相同的接口：Datainput和DataOutput）。利用seek()可以在文件中到处移动，并修改文件中的某个值。

可以自行选择的是第二个构造器参数：指定以“只读”(r)方式或“读写”(rw)方式打开一个RandowmAccessFile文件。

使用“内存映射文件”来代替RandomAccessFile。

//TODO 16

### 18.6.7 管道流 ###

PipedInputStream、PipedOutputStream、PipedReader以及PipedWriter。

主要用于多线程，用于任务之间的通信。

## 18.7 文件读写的实用工具 ##

考虑把File IO封装成类库

使用在Java SE5引入的java.util.Scanner类。但是，它只能用于读取文件，而不能写入文件，并且这个工具（该类不在java.io包中）主要设计用来创建编程语言的扫描器“小语言”的。

// TODO 练习17 18

### 18.7.1 读写二进制文件 ###

与TextFile类似，简化了读写二进制文件的过程。

一个重载方法接受File对象，第二个重载方法接受表示文件名的String参数。这两个方法都会返回byte数组。avaliable()方法都被用来产生恰当的数组尺寸，并且read()方法的特定重载版本填充了数组。

// 练习19-20

## 18.8 标准I/O ##

程序的所有输入都可以来自*标准输入*，它的所有输出都可以发送到*标准输出*，以及所有的错误信息都可以发送到*标准错误*。

标准I/O的意义在于，可以把程序串联起来，一个程序的标准输出可以成为另一个程序的标准输入。

### 18.8.1 从标准输入中读取 ###

按照标准I/O模型，Java提供了System.in、System.out和System.err。

通常用readLine()一次一行地读取输入，为此，将System.in包装城BufferedReader来使用这要求我们必须用InputStreamReader把System.in转换成Reader。

### 18.8.2 将System.out转换成PrintWriter ###

## 18.10 新I/O ##

JDK 1.4的java.nio.*包引入了新的JavaI/O类库，其目的在于提高速度。实际上，旧的I/O包已经使用nio重新实现过，以便充分利用这种速度。

速度的提高来自于所使用的结构更接近于操作系统执行I/O的方式：*通道和缓冲区*。

唯一直接与通道交互的缓冲器是BytteBuffer——也就是说，可以存储未加工字节的缓冲器。

### 18.10.1 转换数据 ###

每次只读取一个字节的数据，然后将每个byte类型强制转换成char类型。

缓冲器容纳的是普通的字节，为了把它们转换成字符，要在输入他们的时候对他们进行*编码*，要么在

旧I/O类库中有三个类被修改了，用以产生FileChannel。这三个被修改的类是FileInputStream、FileOutputStream以及用于既读又写的RandomAccessFile。

//TODO 

## 18.11 压缩 ##

Java I/O类库中的类支持读写压缩格式的数据流。可以用他们对其他的I/O类进行封装，以提供压缩功能。

这些类不是从Reader和Writer类派生而来的，而是属于InputStream和OutputStream继承层次结构的一部分。这样做是因为压缩类库是按字节方式而不是字符方式处理的。

|压缩类|功能|
|--|--|
|CheckedInputStream|GetCheckSum()在任何InputStream产生校验和（不仅是解压缩）
|CheckedOutputStream|GetCheckSum()为任何OutputStream产生校验和（不仅是压缩）压缩类的基类|
|DeflaterOutputStream|压缩类的基类|
|ZipOutputStream|一个DeflaterOutputStream，用于将数据压缩成Zip文件格式|
|GZipOutputStream|一个DeflaterOutputStream，用于将数据压缩成GZip文件格式|
|InflaterInputStream|解压缩类的基类
|ZipInputStream|一个InflaterInputStream，用于解压缩Zip文件格式的数据|
|GZIPInputStream|一个InflaterInputStream，用于解压缩GZIP文件格式的数据|

### 18.11.1 用GZIP进行简单压缩 ###

压缩类直接将输出流封装成GZIPOutputStream或ZipOutputStream，并将输入流封装成GZIpInputStream或ZipInputStream即可。其他全部操作就是通常的I/O读写。

这个例子把面向字符的流和面向字节的流混合了起来；输入（in）用Reader类，而GZIPOutputStream的构造器只能接受OutputStream对象，不能接受Writer对象。在打开文件时，GZIPInputStream就会被转换成Reader。

### 18.11.2 用Zip进行多文件保存 ###



## 18.12 对象序列化 ##

Java的*对象序列化*将哪些实现了Serializable接口的对象转换成一个字节序列，并能够在以后将这个字节序列完全恢复为原来的对象。——这一过程可以通过网络进行，意味着序列化机制能自动弥补不同操作系统之间的差异。

对象的序列化，*轻量级持久性*（lightweight persistence）。“持久性”意味着一个对象的生存周期并不取决于程序是否正在执行；可以生存于程序的调用之间。通过将一个序列化对象写入磁盘，然后在重新调用程序时恢复该对象，就能够实现持久化的效果。

对象必须在程序中显式地序列化（serialize）和反序列化还原（deserialize）

对象序列化的概念加入到语言中是为了支持两种主要特性。

1. Java的远程方法调用（Remote Method Invocation，RMI）
2. 对Java Beans来说，对象的序列化也是必需的。使用一个Bean时，一般情况下是在设计阶段对它的状态信息进行配置。

只要对象实现了Serializable接口（该接口仅是一个标记接口，不包括任何方法），对象的序列化处理就会非常简单。当序列化的概念被加入到语言中时，许多标准库类都发生了改变，以便具备序列化特性——其中包括所有基本数据类型的封装器、所有容器类以及许多其他的东西。甚至Class对象也可以被序列化。

要序列化一个对象，首先要创建某些OutputStream对象，然后将其封装在一个ObjectOutputStream对象内。这时，只需调用writeObject()即可将对象序列化，并将其发送给OutputStream（对象化序列是基于字节，因要使用InputStream和OutputStream继承层次结构）。要反向进行该过程（即将一个序列还原为一个对象），需要将一个InputStream封装在ObjectInputStream内，然后调用readObject()。和往常一样，将最后获得的是一个引用，它指向一个向上转型的Object，所以必须向下转型才能设置它们。

//TODO 练习27

两段相对独立的代码
1. 读写的是文件
2. 读写的是字节数组（ByteArray）

### 18.12.1 寻找类 ###

打开文件和读取mystery对象中的内容都需要Alien的class对象；而Java虚拟机找不到Alien.class（除非它正好在类路径Classpath内，而本例却不在类路径之内）。这样就会得到一个名叫ClassNotFoundException的异常（同样，除非能够验证Alien存在，否则它等于消失）。必须保证Java虚拟机能找到相关的.class文件。

### 18.12.2 序列化的控制 ###

考虑的安全问题，而且你不希望对象的某一部分被序列化；或者一个对象被还原之后，某子对象需要重新创建，从而不必将该子对象序列化。

特殊的情况，可通过实现Externalizable接口——代替实现Serializable接口——来对序列化过程进行控制。这个Externalizable接口继承了Serializable接口，同时增添了两个方法：writeExternal()和readExternal()。这两个方法会在序列化和反序列化还原的过程被自动调用。

#### Externalizable的代替方法 ####

不是特别坚持实现Externalizable接口，我们可以实现Serializable接口，并添加名为writeObject()和readObject()的方法。一旦对象被序列化或者被反序列化还原，都会自动地分别调用这两个方法。

	private void writeObject(ObjectOutputStream stream) throws IOException;
	private void readObject(ObjectOutputStream stream) throws IOException, ClassNotFoundException;

从设计的观点
1. 这些方法不是基类或者Serializable接口的一部分，所以应该在他们自己的接口中进行定义，但是他们被定义成了private，意味着仅能被这个类的其他成员调用。
2.  在调用ObjectOutputStream.writeObject()时，会检查所传递的Serializable对象，看看是否实现了自己的writeObject()。如果是这样，就跳过了正常的序列化过程调用它的writeObject()。

#### 版本控制 ####

### 18.12.3 使用“持久性” ###

存储程序的一些状态，以便随后可以很容易地将程序恢复到当前的状态。

如果将两个对象——都具有指向第三个对象的引用——进行序列化

通过一个字节数组来使用对象序列化，从而实现对任何可Serializable的“深度复制”（deep copy）——深度复制意味着我们复制的是整个对象网，而不仅仅是基本对象及其引用。

Shape类实现了Serializable，所以任何自Shape继承的类也都会自动是Serializable的。

//TODO 30

## 18.13 XML ##

对象序列化的一个重要限制是它只是Java的解决方案：只有Java程序才能反序列化这种对象。一种更具操作性的解决方案是将数据转换为XML格式，可以被各种各样的平台和语言使用。

1. javax.xml.* 类库
2. Elliotte Rusty Harold的开源XOM类库

//TODO 练习31，32

XOM的方法具有相当的自解释性，可以在XOM文档中找到它们。XOM还包含一个Serializer类，可以在format()方法中看到它被用来将XML转换为更具可读性的格式。如果只调用toXML()，那么所有东西都会混在一起，因此Serializer是一种便利工具

## 18.14 Preferences ##

Preferences API与对象序列化相比，前者与对象持久化更密切，因为它可以自动存储和读取信息。不过，它只能用于小的、受限的数据集合——只能存储基本类型和字符串，并且每个字符串的存储长度不能超过8K。

Preferences是一个键-值集合（类似映射），存储在一个节点层次结构中。

再运行PreferencesDemo会发现每次运行程序时，UsageCount的值都会增加1.但是数据存储到哪里了？再程序第一次运行之后，并没有出现任何本地文件。Preferences API利用合适的系统资源完成了这个任务，并且这些资源会随操作系统不同而不同。（例如在Windows里，就使用注册表（因为它已经有“键值对”这样的节点对层次结构了））

//TODO 练习33

## 18.15 总结 ##

Java I/O流类库的满足基本的需求：可以通过控制台、文件、内存块，甚至因特网进行读写。通过继承，可以创建新类型的输入和输出对象。并且通过重新定义toString()方法，甚至可以对流接受的对象类型进行简单扩充。

当我们向一个期望收到字符串的方法传送一个对象时，会自动调用toString()方法（这事Java有限的自动类型转换功能）。

在I/O流类库的文档和设计中，仍留有一些没有解决的问题。例如，当我们打开一个文件用于输出时，可以指定一旦试图覆盖该文件就抛出一个异常——有的编程系统允许我们自行指定想要打开的输出文件，只要它尚不存在。在Java中，应该使用一个File对象来判断某个文件是否存在，因为如果我们以FileOutputStream或者FileWriter打开，那么它肯定会覆盖。

**I/O类库，需要理解“装饰器”模式**
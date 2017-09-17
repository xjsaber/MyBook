# Java核心技术 卷2 高级特性 #

## 第1章 流与文件 ##

### 1.1 流 ###

在Java API中，可以从其中读入一个字节序列的对象称作*输入流*，而可以向其中写入一个字节序列的对象称作*输出流*。抽象类InputStream和OutputStream构成了输入/输出（I/O）类层次结构的基础。

#### 1.1.1 读写字节 ####

InputStream类有一个抽象方法：

	abstract int read()
读入一个字节，并返回读入的字节，或者在遇到输入源结尾时返回-1.

	abstract void write(int b)
它可以向某个输出位置写出一个字节。

read和write方法在执行时都将*阻塞*，直至字节确实被读入或写出。这就意味着如果流不能被立即访问（通常是因为网络连接忙），那么当前的线程将被阻塞。这使得在这两个方法等待指定流变为可用的这段时间里，其他的线程就有机会去执行游泳的工作。

available方法使我们可以去检查当前可读入的字节数量，这意味这下面这样的代码片段就不可能被阻塞：

**java.io.InputStream 1.0**

* abstract int read() 从数据中读入一个字节，并返回该字节。这个read方法在碰到流的结尾时返回-1.
* int read(byte[] b) 读入一个字节数组，并返回实际读入的字节数，或者在碰到流的结尾时返回-1.这个read方法最多读入b.length个字节。
* int read(byte[] b, int off, int len)读入一个字节数组。这个read方法返回实际读入的字节数，或者碰到流的结尾时返回 -1。

|参数||
|--|--|
|b|数据读入的数组|
|off|第一个读入字节应该被放置的位置在b中的偏移量|
|len|读入字节的最大数量|

* long skip(long n) 在输入流中跳过n个字节，返回实际跳过的字节数（如果碰到流的结尾，则可能小于n）。
* int available() 
* void close() 关闭这个输入流
* void mark(int readlimit)  
* void reset() 返回到最后一个标记，随后对read的调用将
* boolean markSupported() 如果这个流支持打标记，则返回true

**Java.io.OutputStream**

* abstract void write(int n) 写出一个字节的数据
* void write(byte[] b)
* void write(byte[] b, int off, int len) 写出所有字节或者某个范围的字节到数组b中。

|参数|说明|
* void close() 冲刷并关闭输出流
* void flush() 冲刷输出流，也就是将所有缓冲的数据发送到目的地

#### 1.1.2 完整的流家族 ####

与C语言只有单一类型 FILE*包打天下不同，Java拥有一个流家族，包含各种流类型，其数量超过60个！

让我们把流家族中的成员按照它们的使用方法来进行划分，这样就形成了处理字节和字符的两个单独的层次结构。InputStream和OutputStream类可以读写单个字节或字节数组。

	abstract int read()
	abstract void write(int c)
read方法将返回一个Unicode码元（一个在0~65535之间的整数），或者在碰到文件结尾时返回-1.write方法在被调用时，需要传递一个Unicode码元。

**java.io.Closeable 5.0**

* void close() 关闭这个Closeable，这个方法可能会抛出IOException

**java.io.Flushable 5.0**

* void flush() 冲刷这个Flushable。

**java.io.Flushable 5.0**

* void flush() 冲刷这个Flushable。

**java.io.Readable 5.0**

* int read(CharBuffer cb) 尝试着向cb读入其可持有数量的char值。返回读入的char值的数量，或者当从这个Radable中无法再获得更多的值返回-1。


**java.io.Appendable 5.0**

* Appendable append(char c)
* Appendable append(charSequence cs)

向这个Appendable中追加给定的码元或者给定的序列中的所有码元，返回this。

**java.io.CharSequence 1.4**

* char charAt(int index) 返回给定索引处的码元。
* int length() 返回在这个序列中的码元的数量。
* CharSequence subSequence(int startIndex, int endIndex) 返回由存储在startIndex到endIndex-1处的所有码元构成的CharSequence。
* String toString() 返回这个序列中所有码元构成的字符串。

#### 1.1.3 组合流过滤器 ####

FileInputStream和FileOutputStream可以提供附着在一个磁盘文件上的输入流和输出流，而只需向其构造器提供文件名或文件的完整路径名。

	FileInputStream fin = new FileInputStream("employee.dat")

所有再java.io中的类都将相对路径名解释为以用户工作目录开始，可以通过调用System.getProperty("user.dir")来获得这个信息。

与抽象类InputStream和OutputStream一样，这些类只支持在字节级别上的读写，也就是说，我们只能从fin对象中读入字节和字节数组。
	
	byte b = (byte)fin.read();

DataInputStream，只能读入数值类型：

	DataInputStream din = ...;
	double s = din.readDouble();

某些流（例如FileInputStream和由URL类的openStream方法返回的输入流）可以从文件和其他更外部的位置上获取字节，而其他的流（例如DataInputStream和PrintWriter）可以从法国字节组装到更有用的数据类型中。

	FileInputStream fin = new FileInputStream("employee.dat");
	DataInputStream din = new DataInputStream(fin);
	double s = din.readDouble();

FilterInputStream和FileterOutputStream类的子类可以向原生字节流添加额外的功能。

	DataInputStream din = new DataInputStream(new BufferedInputStream(new FileInputStream("employee.dat")));

**java.io.FileInputStream 1.0**

* FileOutputStream(String name)
* FileOutputStream(String name, boolean append) 
* FileOutputStream(File file)
* FileOutputStream(File file, boolean append)  
使用由name字符串或file对象指定路径名的文件创建一个新的文件输出流。如果append参数为true，那么数据将被添加到文件尾，而具有相同名字的已有文件不会被删除；否则，这个方法会删除所有具有相同的已有文件。

**java.io.BufferedInputStream 1.0**

* BufferedInputStream(InputStream in) 创建一个带缓冲区的流。带缓冲区的输入流在从流中读入字符时，不会每次都对设备访问。当缓冲区为空时，会向缓冲区读入一个新的数据块。

**java.io.BufferedOutputStream 1.0**

* BufferedOutputStream(OutputStream out) 创建一个带缓冲区的流。带缓冲区的输出流在收集要写出的字符时，不会每次都对设备访问。当缓冲区填满或当流被冲刷时，数据就被写出。

**java.io.PushbackInputStream 1.0**

* PushbackInputStream(InputStream in)
* PushbackInputStream(InputStream in, int size) 构建一个可以预览一个字节或者具有指定尺寸的回推缓冲区的流。
* void unread(int b) 回推一个字节，它可以在下次调用read时被再次获取。 参数： b 要再次读入的字节。

## 1.2 文本输入与输出 ##

在保存数据时，可以选择二进制格式或文本格式。

在存储文本字符串时，需要考虑字符编码（cuaracter encoding）方式。在UTF-16编码方式中，字符串“1234”编码为 00 31 00 32 00 33 00 34 十六进制）。但是，许多程序都希望文本文件按照其他的编码方式编码。在ISO 8859-1中，字符串将写出31 32 33 34，其中没有任何0字节。

OutputStreamWriter类将使用选定的字符编码方式，把Unicode字符流转换为字节流。而InputStreamReader类将包含字节（用某种字符编码方式表示的字符）的输入流转换为可以产生Unicode码元的读入器。

	InputStreamReader in = new InputStreamReader(System.in);

假定使用主机系统所使用的默认字符。

	InputStreamReader in = new InputStreamReader(new FileInputStream("kremlin.dat"), "ISO8859_5")

### 1.2.1 如何写出文本输出 ###

文本输出，可以使用PrintWriter。这个类拥有以文本格式打印字符串和数字的方法，还有一个将PrintWriter链接到FileWriter的便捷方法。

	PrintWriter out = new PrintWriter("employee.txt");
	等价： PrintWriter out = new PrintWriter(new FileWriter("employee.txt"));

通过使用PrintWriter(Writer out, Boolean autoFlush)来启用或禁用自动冲刷机制：

	PrintWriter out = new PrintWriter(new FileWriter("employee.txt"), true); //autoflush

**java.io.Printer 1.1**

* PrintWriter(Writer out) 
* PrintWriter(Writer out, boolean autoFlush) 创建一个新的PrintWriter。 参数： out 一个用于字符输出的写出器。 autoflush 如果为true， 则println方法将冲刷输出缓冲区（默认值：false）
* PrintWriter(OutputStream out)
* PrintWriter(OutputStream out, boolean autoflush) 通过创建必须的中介OutputStreamWriter，从已有的OutputStream中创建一个新的PrintWriter。
* PrintWriter(String filename)
* PrintWriter(File file) 通过创建必须的中介FileWriter，创建一个向给定的文件写出的新的PrintWriter。
* void print(Object obj) 通过打印从toString产生的字符串来打印一个对象。 参数：obj 要打印的对象。
* void print(String s) 打印一个包含Unicode码元的字符串。
* void println(String s) 打印一个字符串，后面紧跟一个行终止符。如果这个流处于自动冲刷模式，那么就会冲刷这个流。
* void print(char[] s) 打印在给定的字符串中的所有Unicode码元。
* void print(char c) 打印一个Unicode码元。
* void print(int i)
* void print(long l)
* void print(float f)
* void print(double d)
* void print(boolean b) 以文本格式打印给定的值
* void printf(String format, Object ... args) 按照格式字符串指定的方式打印给定的值。
* boolean checkError() 如果产生格式化或输出错误，则返回true。一旦这个流碰到了错误，它就收到了忽然，并且所有对checkError的调用都将返回true。

#### 1.2.2 如何读入文本输入 ####

* 以二进制格式写出数据，需要使用DataOutputStream。
* 以文本格式写出数据，需要使用PrintWriter。

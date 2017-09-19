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

#### 1.2.3 以文本格式存储对象 ####

#### 1.2.4 字符集 ####

在Java SE1.4中引入的java.nio包用Charset类统一了对字符集的转换。
字符集建立了两字节Unicode码元序列与使用本地字符编码方式的字节序列之间的映射。

Charset类使用的是由IANA字符集注册中心标准化的字符集名字。

aliases方法可以返回由别名构成的Set对象。下面是迭代遍历这些别名的代码：

	Set<String> aliases = cset.aliases();
	for (String alias : aliases)
		System.out.porintln(alias);

字符集名字是大小写不敏感的。

为了确定在某个特定实现中哪些字符集是可用的，可以调用静态的availableCharsets方法。使用下面的代码可以确定所在可用字符集的名字。

一旦有了字符集，就可以使用它在包含Unicode码元的Java字符串和编码而成的字节序列之间进行转换。

	String str = ...;
	ByteBuff buff = cset.encode(str);
	byte[] bytes = buffer.array()

与之相反，要想解码字节序列，需要有字节缓冲区。使用ByteBuffer数组的静态方法wrap可以将一个字节数组转换成一个字节缓冲区。decode方法的结果是一个CharBuffer，调用它的toString方法可以获得一个字符串。

	byte[] bytes = ...;
	ByteBuffer bbuf = ByteBuffer.wrap(bytes, offset, length);
	CharBuffer cbuf = cset.decode(bbuf);
	String str = cbuf.toSgtring();

java.nio.charset.Charset 1.4

#### 1.2.3 以文本格式存储对象 ####

## 1.4 ZIP文档 ##

ZIP文档（通常）以压缩格式存储了一个或多个文件，每个ZIP文档都由一个头，包含诸如每个文件名字和所使用的压缩方法等消息。

在Java中，可以使用ZipInputStream来读入ZIP文档。你可能需要浏览文档中每个单独的项，getNextEntry方法就可以返回一个描述这些项的ZipEntry类型的对象。ZipInputStream的read方法被修改为在碰到当前项的结尾时返回-1（而不是碰到ZIP文件的末尾），然后你必须调用closeEntry来读入下一项。

**java.util.zip.ZipInputStream 1.1**

* ZipInputStream(InputStream in) 创建一个ZipInputStream，使得我们可以从给定的InputStream向其中填充数据。 
* ZipEntry getNextEntry() 为下一项返回ZipEntry对象，或者在没有更多的项时返回null。
* void closeEntry() 关闭这个ZIP文件中当前打开的项。之后可以通过使用getNextEntry()读入下一项。

**java.util.zip.ZipOutStream 1.1**

* ZipOutputStream(OutputStream out) 创建一个将压缩数据写出到指定的OutputStream的ZipOutputStream。
* void putNextEntry(ZipEntry ze) 将给定的ZipEntry中的信息写出到流中，并定位用于写出数据的流，然后这些数据可以通过write写出到这个流中。
* void closeEntry()
* void setLEevel(int level) 设置后续的各个DEFLATED项的默认压缩级别。这里默认值是Deflater.DEFAULT_COMPRESSION。如果级别无效，则抛出IllegalArgumentException。
* void setMethod(int method) 设置用于这个ZipOutputStream的默认压缩方法，这个压缩方法会作用于所有没有指定压缩方法的顶上。 参数：method 压缩方法， DELATED或STORED

**java.util.zip.ZipEntry 1.1**

* ZipEntry(String name) 用给定的名字构建一个Zip项。 参数：name
* long getCrc() 返回用于这个ZipEntry的CRC32校验和的值。 

**java.util.zip.ZipFile 1.1**

* ZipFile(String name)
* ZipFile(File file) 创建一个ZipFile，用于从给定的字符串或File对象中读入数据
* Enumeration entries()
* ZipEntry getEntry

### 1.5 对象流与序列化 ###

### 1.6 操作文件 ###

### 1.7 内存映射文件 ###

## 第2章 XML ##

### 2.1 XML概述 ###

尽管HTML与XML同宗同源，但是两者之间存在着重要的区别：

* 与HTML不同，XML是大小写敏感的。例如，<H1>和<h1>是不同的XML标签。

**XML文档的结构**

XML文档应当以一个文档头开始，

	<?xml version="1.0"?>
或者
	<?xml version="1.0" encoding="UTF-8"?>

最后，XML文档的正文包含根元素，根元素包含其他元素。

	<?xml version="1.0"?>
	<!DOCTYPE configuration ...>
		<title>
			<font>
				<name>Helvetica</name>
				<size>36</size>
			</font>
		</title>
	</configuration>

元素可以有子元素(child element)、文本或两者皆有。在上述例子中，font元素有两个子元素，它们是name和size。name元素包含文本“Helvetica”。

XML元素可以包含属性

	<size unit="pt">36</size>

转而使用元素，许多游泳的文档根本不使用属性。

* 字符引用（character reference）的形式是&#十进制值；或&#x十六进制；
* 实体引用（entity reference）的形式是&name
* CDATA部分（CDATA Section）用<![CDATA[ 和 ]]>来限定其界限。
* 处理指令（processing instruction）是那些专门在处理XML文档的应用程序中使用的执行，它们将用<?和?>来限定其界限。

### 2.2 解析XML文档 ###

* 像文档对象模型（Docuemtn Object Model，DOM）解析器这样的树型解析器（tree parser），它们将读入的XML文档转换成树结构。
* 像XML简单API（Simple API for XML，SAX）解析器这样的流机制解析器（streaming parser），它们在读入XML文档时生成相应的事件。

DOM解析器的接口已经被W3C标准化了。org.w3c.dom包包含了这些接口类型的定义，比如：Document和Element等。

要读入一个XML文档，首先需要一个DocuemtBuilder对象，可以从DocumentBuilderFactory中得到这个对象。

	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	DocumentBuilder builder = factory.newDocumentBuilder();

从文件中读入某个文档：

	File f = ...
	Document doc = builder.parse(f);

或者，可以用一个URL：

	URL u = ...
	Document doc = builder.parse(u);

设置可以指定一个任意的输入流：

	InputStream in = ...
	Document doc = builder.parse(in);

Document对象是XML文档的树型结构在内存中的表现，它由实现了Node接口及其各种子接口的类的对象构成。

可以通过调用getDocumentElement方法来启动对文档内容的分析，它将返回根元素。

	Element root = doc.getDocumentElement();

	NodeList children = root.getChildNodes();
	for (int i = 0; i < children.getLength(); i++) {
			Node child = children.item(i)
	}

也可以用getLastChild方法得到最后一项子元素，用getNextSibling得到下一个兄弟节点。
	
	for (Node childNode = element.getFirstChild();
		childNode != nill;
		childNode = childNode.getNextSibling()) {
		...
	}

如果要枚举结点的属性，可以调用getAttributes方法。它返回一个NamedNodeMap对象，其中包含了描述属性的Node对象。可以用和遍历NodeList一样的办法在NamedNodeMap中遍历各子结点。调用getNodeName和getNodeValue方法可以得到属性名和属性值。

表的单元格渲染器显示了以下内容：

* 对元素，显示的是元素标签名和由所有的属性构成的一张表。
* 对字符数据，显示的是界面（文本、注释、CDATA部分），后面跟着数据，其中换行和回车字符被\n和\r取代。
* 对其他所有的结点类型，显示的是类名，后面跟着toString的结果。

**javax.xml.parsers.DocumentBuilderFactory 1.4**

**javax.xml.parsers.DocumentBuilder 1.4**

**org.w3c.dom.Document 1.4**

**org.w3c.dom.Element 1.4**

**org.w3c.dom.Node 1.4**

**org.w3c.dom.CharacterData 1.4**

**org.w3c.dom.NodeList 1.4**

**org.w3c.dom.NamedNodeMap 1.4**

* int getLength() 返回该节点映射表中的节点数。
* Node item(int index) 返回给定索引值的节点。索引值范围在0到getLength()-1之间。

### 2.3 验证XML文档 ###

	<font>
		<name>Helvetica</name>
		<size>36</size>
	</font>

首先得到第一个子节点，这事一个含有空白字符“\n”的文本节点。跳过文本节点找到第一个元素节点。然后，检查它的标签名是不是“name”，还要检查它是否有一个Text类型的子节点。接下去，转到下一个非空白字符的子节点，并进行同样的检查。那么，当文档作者改变了子元素的顺序或是加入另一个子元素会进行代码检查。

指定文档结构，可以提供一个文档类型定义（DTD）或一个XML Schema定义。DTD或schema包含了用于解释文档应如何构成的规则，这些规则指定了每个元素的合法子元素和属性。
	
	<!ELEMENT font (name, size)>

这个规则表示，一个font元素必须总是有两个子元素，分别是name和size。将同样的约束表示如下：

	<xsd:element name="font">
		<xsd:sequence>
			<xsd:element name="name" type="xsd:string" />
			<xsd:element name="size" type="xsd:int" />
		</xsd:sequence>
	</xsd:element>

与DTD相比，XML Schema可以表达更加复杂的验证条件。与DTD语法不同，Schema使用XML，这为处理Schema文件带来了方便。

#### 2.3.1 文档类型定义 ####

	<?xml version="1.0"?>
	<!DOCTYPE configuration [
		<!ELEMENT configuration ...>
		more rules
		...
	]>
	<configuration>
		...
	</configuration>

#### 2.3.2 XML Schema ####

### 2.4 使用XPath来定义信息 ###

### 2.5 使用命名空间 ###
	
### 2.7 生成XML文档 ###

用文档的内容构建一个DOM树，然后再写出该树的所有内容

#### 2.7.1 不带命名空间的文档 ####

建立一课DOM树，可以从一个空的文档开始。通过调用DocumentBuilder类的newDocument方法可以得到一个空文档。

	Document doc = builder.newDocument();

使用Document类的createElement方法可以构建文档里的元素：

	Element rootElement = doc.createElement(rootName);
	Element childElement = doc.createElement(childElement);

使用createTextNode方法可以构建文本节点：

	Text textNode = doc.createTextNode(textContens);

使用以下方法可以给文档添加根元素，给父节点添加子节点：

	doc.appendChild(rootElement);
	rootElement.appendChild(childElement);
	childElement.appendChild(textNode);

在建立DOM树时，还需要设置元素属性，只需要调用Element类的setAttribute方法：

	rootElement.setAttribute(name, value);

#### 2.7.2 带命名空间的文档 ####

如果要使用命名空间，那么创建文档的过程就会稍微有些区别。

首先，需要将生成器工厂设置为是命名空间敏感的，然后再创建生成器：

	DocumentBuilderFactory factory = DocumentBuildFactory.newInstance();
	factory.setNamespaceAware(true);
	...

然后使用createElementNS而不是createElement来创建所有节点：

	String namespace = "...";
	Element rootElement = doc.createElementNS(namespace, "svg");

	Element svgElement = doc.createElement(namespace, "svg:svg");

	rootElement.setAttributeNS(namespace, qulifiedName, value);

### 2.7.3 写出文档 ###

把DOM树写出到输出流中，使用可扩展的格式页转换（Extensible Stylesheet Language Transformations XSLT）API。

	Transformer t = TransformerFactory.newInstance().newTransformer();
	t.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, systemIdentifier);
	t.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, publicIdentifier);
	t.setOutputProperty(OutputKeys.INDENT, "yes");
	t.setOutputProperty(OutputKeys.METHOD, "xml");
	t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
	t.transform(new DOMSource(doc) new StreamResult(new FileOutputStream(file)));

另一种使用LSSerializer接口

	DOMImplementation impl = doc.getImplementation();

#### 2.7.4 示例：生成SVG文件 ####

**javax.xml.parsers.DocumentBuilder 1.4** 

* Document newDocument() 返回一个空文档

**org.w3c.dom.Document 1.4**

* Element createElement(String name)返回具有给定名字的元素
* Text createTextNode(String data) 返回具有给定数据的文本节点

**org.w3c.dom.Node 1.4**

* Node appendChild(Node child) 将一个节点附加到该节点的子节点列表，返回该节点

**org.w3c.dom.Element 1.4**

* void setAttribute(String name, String value) 
* void setAttributeNS(String uri, String qname, String value) 将有给定名字的属性设置为指定的值。

|参数|||
|--|--|--|
||uri|名字空间的URI或null|
||qname|限定名。如果有别的前缀，uri不能为null|
||value|限定值|

**javax.xml.transform.TramsformerFactory 1.4**

* static TransformerFactory newInstance() 返回TransformerFactory类的一个实例。
* transformer newTransformer() 返回Transformer类的一个实例，它用来实现标识符转换。

#### 2.7.5 使用StAx写XML文档 ####

一旦套接字被打开，java.net.Socket类中的getInputStream方法就会返回一个InputStream对象，该对象可以像其他任何流对象一样使用。

## 第3章 网络 ##

第一行代码用于打开一个套接字，网络软件的一个抽象概念，负责启动改程序内部和外部之间的通信。

UDP比较适合可以忍受数据包丢失的应用，例如用于音频流和视频流的传输，或者用于连续测量的应用领域

**java.net.Socket 1.0**

* Socket(String host, int port) 构建一个套接字，用来连接给定的主机和端口
* InputStream getInputStream()
* OutputStream getOutputStream() 获取可以从套接字读取数据的流，以及可以向套接字写出数据的流。

### 3.1.1 套接字超时 ###

	Socket s = new Socket(...);
	s.setSoTimeout(10000); //time out after 10 seconds

SocketTimeoutException异常

超时问题必须解决

	Socket(String host, int port) 会一直无限期阻塞下去，直到建立了到达主机的初始连接位置。
	可以通过先构建一个无连接的套接字，然后再使用一个超时来进行连接的方法解决问题。
	Socket s = new Socket();
	s.connect = (new InetSocketAddress(host, port), timeout);

**java.net.Socket 1.0**

* Socket() 创建一个还未被连接的套接字
* void connect()
* boolean isConnected() 1.4

### 3.1.2 因特网地址 ###



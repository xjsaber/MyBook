# 4.1 基础知识 #

## 4.1.1  概述 ##
Spring 提供一个Resource接口来统一这些底层资源一致的访问，而且提供了一些便利的接口，从而能提供我们的生产力。

## 4.1.2  Resource接口 ##
Spring的Resource接口代表底层外部资源，提供了对底层外部资源的一致性访问接口。

	public interface InputStreamSource {  
	    InputStream getInputStream() throws IOException;  
	}  

	public interface Resource extends InputStreamSource {  
       boolean exists();  
       boolean isReadable();  
       boolean isOpen();  
       URL getURL() throws IOException;  
       URI getURI() throws IOException;  
       File getFile() throws IOException;  
       long contentLength() throws IOException;  
       long lastModified() throws IOException;  
       Resource createRelative(String relativePath) throws IOException;  
       String getFilename();  
       String getDescription();  
	}  

1）InputStreamSource接口解析：

**getInputStream**：每次调用都将返回一个新鲜的资源对应的java.io. InputStream字节流，调用者在使用完毕后必须关闭该资源。

2）Resource接口继承InputStreamSource接口，并提供一些便利方法：

**exists**：返回当前Resource代表的底层资源是否存在，true表示存在。

**isReadable**：返回当前Resource代表的底层资源是否可读，true表示可读。

**isOpen**：返回当前Resource代表的底层资源是否已经打开，如果返回true，则只能被读取一次然后关闭以避免资源泄露；常见的Resource实现一般返回false。

**getURL**：如果当前Resource代表的底层资源能由java.util.URL代表，则返回该URL，否则抛出IOException。

**getURI**：如果当前Resource代表的底层资源能由java.util.URI代表，则返回该URI，否则抛出IOException。

**getFile**：如果当前Resource代表的底层资源能由java.io.File代表，则返回该File，否则抛出IOException。

**contentLength**：返回当前Resource代表的底层文件资源的长度，一般是值代表的文件资源的长度。

**lastModified**：返回当前Resource代表的底层资源的最后修改时间。

**createRelative**：用于创建相对于当前Resource代表的底层资源的资源，比如当前Resource代表文件资源“d:/test/”则createRelative（“test.txt”）将返回表文件资源“d:/test/test.txt”Resource资源。
getFilename：返回当前Resource代表的底层文件资源的文件路径，比如File资源“file://d:/test.txt”将返回“d:/test.txt”，而URL资源http://www.javass.cn将返回“”，因为只返回文件路径。

**getDescription**：返回当前Resource代表的底层资源的描述符，通常就是资源的全路径（实际文件名或实际URL地址）

Resource接口提供了足够的抽象，足够满足我们日常使用。而且提供了很多内置Resource实现：ByteArrayResource、InputStreamResource 、FileSystemResource 、UrlResource 、ClassPathResource、ServletContextResource、VfsResource等。

原创内容 转自请注明【http://sishuok.com/forum/blogPost/list/0/2455.html】
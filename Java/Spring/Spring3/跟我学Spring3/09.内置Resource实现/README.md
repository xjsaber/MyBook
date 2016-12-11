# 4.2  内置Resource实现 #

## 4.2.1  ByteArrayResource ##

ByteArrayResource代表byte[]数组资源，对于“getInputStream”操作将返回一个ByteArrayInputStream。

首先让我们看下使用ByteArrayResource如何处理byte数组资源：

	package cn.javass.spring.chapter4;  
	import java.io.IOException;  
	import java.io.InputStream;  
	import org.junit.Test;  
	import org.springframework.core.io.ByteArrayResource;  
	import org.springframework.core.io.Resource;  
	public class ResourceTest {  
	@Test  
	public void testByteArrayResource() {  
	Resource resource = new ByteArrayResource("Hello World!".getBytes());  
	        if(resource.exists()) {  
	            dumpStream(resource);  
	        }  
	}  
	} 

是不是很简单，让我们看下“dumpStream”实现：

	private void dumpStream(Resource resource) {  
	        InputStream is = null;  
	        try {  
	            //1.获取文件资源  
	            is = resource.getInputStream();  
	            //2.读取资源  
	            byte[] descBytes = new byte[is.available()];  
	            is.read(descBytes);  
	            System.out.println(new String(descBytes));  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	        finally {  
	            try {  
	                //3.关闭资源  
	                is.close();  
	            } catch (IOException e) {  
	            }  
	        }  
	    }   

让我们来仔细看一下代码，dumpStream方法很抽象定义了访问流的三部曲：打开资源、读取资源、关闭资源，所以dunpStrean可以再进行抽象从而能在自己项目中使用；byteArrayResourceTest测试方法，也定义了基本步骤：定义资源、验证资源存在、访问资源。

ByteArrayResource可多次读取数组资源，即isOpen()永远返回false。

## 4.2.2  InputStreamResource ##

InputStreamResource代表java.io.InputStream字节流，对于“getInputStream ”操作将直接返回该字节流，因此只能读取一次该字节流，即“isOpen”永远返回true。

让我们看下测试代码吧：

	@Test  
	public void testInputStreamResource() {  
	   ByteArrayInputStream bis = new ByteArrayInputStream("Hello World!".getBytes());  
	   Resource resource = new InputStreamResource(bis);  
	    if(resource.exists()) {  
	       dumpStream(resource);  
	    }  
	    Assert.assertEquals(true, resource.isOpen());  
	}  

测试代码几乎和ByteArrayResource测试完全一样，注意“isOpen”此处用于返回true。

## 4.2.3  FileSystemResource ##
FileSystemResource代表java.io.File资源，对于“getInputStream ”操作将返回底层文件的字节流，“isOpen”将永远返回false，从而表示可多次读取底层文件的字节流。

让我们看下测试代码吧：

	@Test  
	public void testFileResource() {  
	File file = new File("d:/test.txt");  
	    Resource resource = new FileSystemResource(file);  
	    if(resource.exists()) {  
	        dumpStream(resource);  
	    }  
	    Assert.assertEquals(false, resource.isOpen());  
	}  

注意由于“isOpen”将永远返回false，所以可以多次调用dumpStream(resource)。

## 4.2.4  ClassPathResource ##
ClassPathResource代表classpath路径的资源，将使用ClassLoader进行加载资源。classpath 资源存在于类路径中的文件系统中或jar包里，且“isOpen”永远返回false，表示可多次读取资源。

ClassPathResource加载资源替代了Class类和ClassLoader类的“getResource(String name)”和“getResourceAsStream(String name)”两个加载类路径资源方法，提供一致的访问方式。

ClassPathResource提供了三个构造器：
public ClassPathResource(String path)：使用默认的ClassLoader加载“path”类路径资源；

public ClassPathResource(String path, ClassLoader classLoader)：使用指定的ClassLoader加载“path”类路径资源；
比如当前类路径是“cn.javass.spring.chapter4.ResourceTest”，而需要加载的资源路径是“cn/javass/spring/chapter4/test1.properties”，则将加载的资源在“cn/javass/spring/chapter4/test1.properties”；

public ClassPathResource(String path, Class<?> clazz)：使用指定的类加载“path”类路径资源，将加载相对于当前类的路径的资源；
比如当前类路径是“cn.javass.spring.chapter4.ResourceTest”，而需要加载的资源路径是“cn/javass/spring/chapter4/test1.properties”，则将加载的资源在“cn/javass/spring/chapter4/cn/javass/spring/chapter4/test1.properties”；
而如果需要 加载的资源路径为“test1.properties”，将加载的资源为“cn/javass/spring/chapter4/test1.properties”。

让我们直接看测试代码吧：
1）使用默认的加载器加载资源，将加载当前ClassLoader类路径上相对于根路径的资源：

	@Test  
	public void testClasspathResourceByDefaultClassLoader() throws IOException {  
		Resource resource = new ClassPathResource("cn/javass/spring/chapter4/test1.properties");  
	    if(resource.exists()) {  
	        dumpStream(resource);  
	    }  
	    System.out.println("path:" + resource.getFile().getAbsolutePath());  
	    Assert.assertEquals(false, resource.isOpen());  
	}  
2）使用指定的ClassLoader进行加载资源，将加载指定的ClassLoader类路径上相对于根路径的资源：

	@Test  
	public void testClasspathResourceByClassLoader() throws IOException {  
	    ClassLoader cl = this.getClass().getClassLoader();  
	    Resource resource = new ClassPathResource("cn/javass/spring/chapter4/test1.properties" , cl);  
	    if(resource.exists()) {  
	        dumpStream(resource);  
	    }  
	    System.out.println("path:" + resource.getFile().getAbsolutePath());  
	    Assert.assertEquals(false, resource.isOpen());  
	}  

3）使用指定的类进行加载资源，将尝试加载相对于当前类的路径的资源：
	
	@Test  
	public void testClasspathResourceByClass() throws IOException {  
   		Class clazz = this.getClass();  
    	Resource resource1 = new ClassPathResource("cn/javass/spring/chapter4/test1.properties" , clazz);  
	    if(resource1.exists()) {  
	        dumpStream(resource1);  
	    }  
	    System.out.println("path:" + resource1.getFile().getAbsolutePath());  
	    Assert.assertEquals(false, resource1.isOpen());  
         
	    Resource resource2 = new ClassPathResource("test1.properties" , this.getClass());  
	    if(resource2.exists()) {  
	        dumpStream(resource2);  
	   	}  
	    System.out.println("path:" + resource2.getFile().getAbsolutePath());  
	    Assert.assertEquals(false, resource2.isOpen());  
	} 

“resource1”将加载cn/javass/spring/chapter4/cn/javass/spring/chapter4/test1.properties资源；“resource2”将加载“cn/javass/spring/chapter4/test1.properties”；

4）加载jar包里的资源，首先在当前类路径下找不到，最后才到Jar包里找，而且在第一个Jar包里找到的将被返回：

	@Test  
	public void classpathResourceTestFromJar() throws IOException {  
		Resource resource = new ClassPathResource("overview.html");  
	    if(resource.exists()) {  
	        dumpStream(resource);  
	    }  
	    System.out.println("path:" + resource.getURL().getPath());  
	    Assert.assertEquals(false, resource.isOpen());  
	}  

如果当前类路径包含“overview.html”，在项目的“resources”目录下，将加载该资源，否则将加载Jar包里的“overview.html”，而且不能使用“resource.getFile()”，应该使用“resource.getURL()”，因为资源不存在于文件系统而是存在于jar包里，URL类似于“file:/C:/.../***.jar!/overview.html”。
类路径一般都是相对路径，即相对于类路径或相对于当前类的路径，因此如果使用“/test1.properties”带前缀“/”的路径，将自动删除“/”得到“test1.properties”。

## 4.2.5  UrlResource ##

UrlResource代表URL资源，用于简化URL资源访问。“isOpen”永远返回false，表示可多次读取资源。
UrlResource一般支持如下资源访问：
http：通过标准的http协议访问web资源，如new UrlResource(“http://地址”)；
ftp：通过ftp协议访问资源，如new UrlResource(“ftp://地址”)；
file：通过file协议访问本地文件系统资源，如new UrlResource(“file:d:/test.txt”)；

## 4.2.6  ServletContextResource ##
ServletContextResource代表web应用资源，用于简化servlet容器的ServletContext接口的getResource操作和getResourceAsStream操作；

## 4.2.7  VfsResource ##
VfsResource代表Jboss 虚拟文件系统资源。
 
Jboss VFS(Virtual File System)框架是一个文件系统资源访问的抽象层，它能一致的访问物理文件系统、jar资源、zip资源、war资源等，VFS能把这些资源一致的映射到一个目录上，访问它们就像访问物理文件资源一样，而其实这些资源不存在于物理文件系统。
在示例之前需要准备一些jar包，在此我们使用的是Jboss VFS3版本，可以下载最新的Jboss AS 6x，拷贝lib目录下的“jboss-logging.jar”和“jboss-vfs.jar”两个jar包拷贝到我们项目的lib目录中并添加到“Java Build Path”中的“Libaries”中。
让我们看下示例（cn.javass.spring.chapter4.ResourceTest）：

@Test  
public void testVfsResourceForRealFileSystem() throws IOException {  
//1.创建一个虚拟的文件目录  
VirtualFile home = VFS.getChild("/home");  
//2.将虚拟目录映射到物理的目录  
VFS.mount(home, new RealFileSystem(new File("d:")));  
//3.通过虚拟目录获取文件资源  
VirtualFile testFile = home.getChild("test.txt");  
//4.通过一致的接口访问  
Resource resource = new VfsResource(testFile);  
if(resource.exists()) {  
        dumpStream(resource);  
}  
System.out.println("path:" + resource.getFile().getAbsolutePath());  
Assert.assertEquals(false, resource.isOpen());         
}  
@Test  
public void testVfsResourceForJar() throws IOException {  
//1.首先获取jar包路径  
    File realFile = new File("lib/org.springframework.beans-3.0.5.RELEASE.jar");  
    //2.创建一个虚拟的文件目录  
    VirtualFile home = VFS.getChild("/home2");  
    //3.将虚拟目录映射到物理的目录  
VFS.mountZipExpanded(realFile, home,  
TempFileProvider.create("tmp", Executors.newScheduledThreadPool(1)));  
//4.通过虚拟目录获取文件资源  
    VirtualFile testFile = home.getChild("META-INF/spring.handlers");  
    Resource resource = new VfsResource(testFile);  
    if(resource.exists()) {  
            dumpStream(resource);  
    }  
    System.out.println("path:" + resource.getFile().getAbsolutePath());  
    Assert.assertEquals(false, resource.isOpen());  
}  

通过VFS，对于jar里的资源和物理文件系统访问都具有一致性，此处只是简单示例，如果需要请到Jboss官网深入学习。

原创内容 转自请注明出处【http://sishuok.com/forum/blogPost/list/0/2456.html】


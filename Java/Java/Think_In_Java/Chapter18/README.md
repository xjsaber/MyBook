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



### 20.1.2 元注解 ###

Java目前只内置了三种标准注解，以及四种元注解。

|--|--|
|@Target|表示该注解可以用于什么地方。 CONSTRUCTOR:构造器的声明 FIELD：域声明（包括enum实例） LOCAL_VARIABLE：局部变量声明 METHOD：方法声明 PACKAGE：包声明 PARAMETER：参数声明 TYPE：类、接口（包括注解类型）或enum声明|
|@Retemtion|表示需要在什么级别保存该注解信息可选的RetentionPolicy参数包括：SOURCE：注解将被编译器丢弃。 CLASS：注解在class文件中可用，但会被VM丢弃。 RUNTIME：VM将在运行期也保留注解，因此可以通过反射机制读取注解的的信息|
|@Documented|将此注解包含在Javadoc中|
|@Inherited|允许子类继承父类中的注解|

## 20.2 编写注解处理器 ##

使用注解的过程中，很重要的一个部分就是创建与使用 *注解处理器*。

### 20.2.1 注解元素 ###

标签@UseCase由UseCase.java定义，其中包含int元素id，以及一个String元素description

* 所有基本类型（int， float， boolean等）
* String
* Class
* enum
* Annotation
* 以上类型的数组

### 20.2.2 默认值限制 ###

1. 元素不能由不确定的值
2. 元素必须要么具有默认值，要么在使用注解时提供元素的值。

对于非基本类型的元素，在源代码中声明时，或是在注解接口中定义默认值时，都不能以null作为其值。

### 20.2.3 生成外部文件 ###

Web Service、自定义标签库以及对象/关系映射工具，一般需要XML描述文件，而这些描述文件脱离于源代码之外。

假设希望提供一些基本的对象/关系映射功能，能够自动生成数据库表，用以存储JavaBean对象。可以选择使用XML描述文件，指明类的名字、每个成员以及数据库映射的相关信息。然而，如果使用注解的话，可以将搜娱哦的信息保存在JavaBean源文件中。

### 20.2.4 注解不支持继承 ###

不能使用关键字extends来继承某个@interface。

### 20.2.5 实现处理器 ###


## 20.3 使用apt处理注解 ##
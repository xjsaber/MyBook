# Spark快速大数据分析 #

## 第2章 Spark下载与入门 ##

### 2.1 下载Spark ###

### 2.2 Spark中Python和Scala的shell ###

Scala行数统计

	scala> val lines = sc.textFile("README.md") // 创建一个名为lines的RDD lines: spark.RDD[String] = MappedRDD[...] 
	 
	scala> lines.count() // 统计RDD中的元素个数 res0: Long = 127 
	 
	scala> lines.first() // 这个RDD中的第一个元素，也就是README.md的第一行 res1: String = # Apache Spark

### 2.3 Spark核心概念简介 ###

每个 Spark 应用都由一个驱动器程序（driver program）来发起集群上的各种 并行操作。驱动器程序包含应用的 main 函数，并且定义了集群上的分布式数据集，还对这 些分布式数据集应用了相关操作。

驱动器程序通过一个 SparkContext 对象来访问 Spark。这个对象代表对计算集群的一个连 接。shell 启动时已经自动创建了一个 SparkContext 对象，是一个叫作 sc 的变量。

要执行这些操作，驱动器程序一般要管理多个执行器（executor）节点。

Scala 版本筛选的例子 

	scala> val lines = sc.textFile("README.md") // 创建一个叫lines的RDD  lines: spark.RDD[String] = MappedRDD[...] 
	 
	scala> val pythonLines = lines.filter(line => line.contains("Python"))  

	pythonLines: spark.RDD[String] = FilteredRDD[...] 
	 
	scala> pythonLines.first() res0: String = ## Interactive Python Shell

### 2.4 独立应用 ###

对应的 Maven 索引是：

	groupId = org.apache.spark 
	artifactId = spark-core_2.10 
	version = 1.2.0

#### 2.4.1 初始化SparkContext ####

导入 Spark 包并且创建 SparkContext。你可以通过先创建一个 SparkConf 对象来配置你的应用，然后基于这个 SparkConf 创建一个 SparkContext 对象。

	import org.apache.spark.SparkConf import org.apache.spark.SparkContext import org.apache.spark.SparkContext._ 
	 
	val conf = new SparkConf().setMaster("local").setAppName("My App") val sc = new SparkContext(conf)

创建SparkContext的最基本的方法，

* 集群URL：告诉 Spark 如何连接到集群上。在这几个例子中我们使用的是 local，这个 特殊值可以让 Spark 运行在单机单线程上而无需连接到集群。 
* 应用名：在例子中我们使用的是 My App。当连接到一个集群时，这个值可以帮助你在 集群管理器的用户界面中找到你的应用。

#### 2.4.2 构建独立应用 ####

Maven构建与运行

	mvn clean && mvn compile && mvn package $SPARK_HOME/bin/spark-submit \   --class com.oreilly.learningsparkexamples.mini.java.WordCount \   ./target/learning-spark-mini-example-0.0.1.jar \   ./README.md ./wordcounts


### 2.5 总结 ###

下载并在单机的本地模式下运行 Spark，以及 Spark 的使用方式，包 括交互式方式和通过一个独立应用进行调用。另外我们还简单介绍了 Spark 编程的核心概 念：通过一个驱动器程序创建一个 SparkContext 和一系列 RDD，然后进行并行操作。

## 第3章 RDD编程 ##

 Spark 对数据的核心抽象——弹性分布式数据集（Resilient Distributed Dataset，简 称 RDD）。RDD 其实就是分布式的元素集合。在 Spark 中，对数据的所有操作不外乎创 建 RDD、转化已有 RDD 以及调用 RDD 操作进行求值。而在这一切背后，Spark 会自动将 RDD 中的数据分发到集群上，并将操作并行化执行。

### 3.1 RDD基础 ###

用户可以使用两种方法创建 RDD：读取一个外部数据集，或在驱动器程序里分发驱动器程 序中的对象集合（比如 list 和 set）。

	 lines = sc.textFile("README.md")

创建出来后，RDD支持两种类型的操作：转化操作（transformation）和行动操作（action）。

1. 转化操作会由一个RDD生成一个新的RDD。
2. 行动操作会对RDD计算出一个结果，并把结果返回到驱动器程序中，或把结果存储到外部存储系统（如HDFS）中。

### 3.2 创建RDD ###

 Spark提供了两种创建RDD的方式：

* 读取外部数据集
* 在驱动器程序中对一个数据集进行并行化

创建RDD最简单地方式就是把程序中一个已有的集合传给SparkContext的parallelize()方法。这种方法虽然可以在shell中快速创建出自己的RDD，然后对这些RDD进行操作。但除了开发原型和测试时，这种方式用得并不多，因为它需要把整个数据集先放在一台机器的内存中。

	val lines = sc.parallelize(List("pandas", "i like pandas"))

	val lines = sc.textFile("path/to/README.md")

### 3.3 RDD操作 ###

RDD支持两种操作：转化操作和行动操作。

* RDD的转化操作是返回一个新的RDD的操作，比如map()和filter()
* 行动操作则是向驱动器程序返回结果或把结果写入外部系统的操作，会触发实际的计算，比如count()和first()。
* 转化操作返回的是RDD，而行动操作返回的是其他的数据类型。

#### 3.3.1 转化操作 ####

RDD的转化操作是返回新RDD的操作。

	val inputRDD = sc.textFile("log.txt")
	errorRDD = inputRDD.filter(line => line.contains("error"))

通过转化操作，从已有的RDD中派生出新的RDD，Spark会使用谱系图（lineage graph）来记录这些不同RDD之间的依赖关系。Spark需要用这些信息来按需计算每个RDD，也可以依靠谱系图在持久化的RDD丢失部分数据时恢复所丢失的数据。

#### 3.3.2 行动操作 ####

	println("Input")




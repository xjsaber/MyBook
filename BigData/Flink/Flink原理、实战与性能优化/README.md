# Flink原理、实战与性能优化 #

## 第2章 环境准备 ##

### 2.1 运行环境介绍 ###

### 2.2 Flink项目模板 ###

#### 2.2.1 基于Java实现的项目模板 ####

1. 创建项目

### 2.3 Flink开发环境配置 ###

#### 2.3.1 下载IntelliJ IDEA IDE ####

#### 2.3.2 安装Scala Plugins ####

#### 2.3.3 导入Flink应用代码 ####

### 2.4 运行Scala REPL ###

和Spark Shell一样，Flink也提供了一套交互式解释器（Scala-Shell），用户能够在客户端命令行交互式编程，执行结果直接交互式地显示在客户端控制台上，不需要每次进行编译打包在集群环境中运行。

#### 2.4.1 环境支持 ####

目前支持Local、RemoteCluster和Yarn Cluster模式

* 通过start-scala-shell.sh启动本地环境：`./start-scala-shell.sh local`
* 可以启动远程集群环境，指定远程Flink集群的hostname和端口号：`./start-scala-shell.sh remote <hostnam e> <portnumber>`
* 启动Yarn集群环境，环境中需要含有hadoop客户端配置文件：`./start-scala-shell.sh yarn -n 2`

#### 2.4.2 运行程序 ####

启动Scala Shell交互式解释器后，就可以进行Flink流式应用或批量应用的开发。Flink已经在启动的执行环境中初始化好了相应的Environment，分别使用“benv”和“senv”获取批量计算环境和流式计算环境，然后使用对应环境中的API开发Flink应用。

* 通过Scala-Shell运行批量计算程序，调用benv完成对单词数量的统计。
* 通过Scala-Shell运行流式计算，调用senv完成对单词数量的统计。

### 2.5 Flink源码编译 ###

（1）Hadoop版本指定

（2）Scala版本指定

### 2.6 本章小结 ###



## 第3章 Flink编程模型 ##

包括Flink支持的数据集类型，有界数据集和无界数据集的区别，以及有界数据集和无界数据集之间的转换。同时针对无界和有界数据集的处理，将介绍Flink分别提供对应的开发接口DataStream API和DataSet API的使用。

### 3.1 数据集类型 ###

#### 1. 有界数据集 ####

有界数据集具有世间边界，在处理过程中数据一定会在某个时间范围内起始和结束，有可能是一分钟，也有可能是一天内的交易数据。对有界数据集的数据处理方式被称为批计算（Batch Processing），针对批处理数据处理（Apache Hadoop和Apache Spark）

#### 2. 无界数据集 ####

对于无界数据集，数据从开始生成就一直持续不断地产生新的数据，因此数据是没有边界的（ApacheStorm、Spark Streaming、Apache Flink）

#### 3. 统一数据处理 ####

有界数据集和无界数据集只是一个相对的概念，主要根据时间的范围而定，可以认为一段时间内的无界数据集其实就是有界数据集，同时有界数据也可以通过一些方法转换为无界数据。

### 3.2 Flink编程接口 ###

Flink根据数据集类型的不同将核心数据处理接口分为两大类，一类是支持批计算的接口DataSet API，另外一类是支持流计算的接口DataStream API。同时Flink将数据处理接口抽象成四层，由上向下分别为SQL API、Table API、DataStream /DataSet API以及Stateful StreamProcessing API，用户可以根据需要选择任意一层抽象接口来开发Flink应用。

#### （1）Flink SQL ####

高级语言（SQL）->声明式API（Table API）->核心API（DataStream/DataSetAPI）->低级构建模块（流，状态，事件「世间」）（Runtime:Stateful Stream Processing）

#### （2）Table API ####

Table API将内存中的DataStream和DataSet数据集在原有的基础之上增加Schema信息，将数据类型统一抽象成表结构，然后通过Table API提供的接口处理对应的数据集。Schema信息，将数据类型统一抽象。Table API在转换为DataStream和DataSet的数据处理过程中，也应用了大量的优化规则对处理逻辑进行了优化。同时Table API中的Table可以和DataStream及DataSet之间进行相互转换。

#### （3）DataStream API和DataSet API ####

* 使用DataStream API处理无界流数据
* 使用DataSet API处理批量数据

#### （4）Stateful Stream Process API ####

Stateful Stream Process API是Flink中处理Stateful Stream最底层的接口，用户可以使用Stateful Stream Process接口操作状态、时间等底层数据。

### 3.3 Flink程序结构 ###


### 3.4 Flink数据类型 ###

#### 3.4.1 数据类型支持 ####

Flink支持非常完善的数据类型，数据类型的描述信息都是由TypeInformation定义，比较常用的TypeInformation有BasicTypeInfo、TupleTypeInfo、CaseClassTypeInfo、PojoTypeInfo类等。

TypeInformation：为了在Flink系统内有效地对数据结构类型进行管理，能够在分布式计算过程中对数据的类型进行管理和推断。

**1. 原生数据类型**

Flink通过实现BasicTypeInfo数据类型，能够支持任意Java原生基本类型（装箱）或String类型。

	// 创建Int类型的数据集
	val intStream:DataStream[Int] = env.fromElements(3, 1, 2, 1, 5)
	// 创建String类型的数据集
	val dataStream:DataStream[String] = env.fromElements("hello", "flink")

Flink实现另外一种TypeInfomation是BasicArrayTypeInfo，对应的是Java基本类型数组（装箱）或String对象的数组

**2.Java Tuples类型**

**3.Scala Case Class类型**

Flink通过实现CaseClassTypeInfo支持任意的Scala Case Class，包括Scalatuples类型，支持的字段数量上限为22，支持通过字段名称和位置索引获取指标，不支持存储空值。通过fromElements方法创建input数据集，调用keyBy()[keyBy未找到]方法对数据集根据word字段重新分区。
	
**4.POJOs类型**

POJOs：

* POJOs类必须使Public修饰且必须独立定义，不能是内部类；
* POJOs类中必须含有默认空构造器；
* POJOs类中所有的Fields必须是Public或者具有Public修饰的getter和setter方法；
* POJOs类中的字段类型必须是Flink支持的。

**5.Flink Value类型**

Value数据类型实现了org.apache.flink.types.Value，其中包括read()和write()两个方法完成序列化和反序列化操作，相对于通用的序列化工具会有着比较高效的性能。

**6.特殊数据类型**

一些特殊的数据类型，例如Scala中的List、Map、Either、Option、Try数据类型，以及Java中Either数据类型，还有Hadoop的Writable数据类型。

#### 3.4.2 TypeInformation信息获取 ####

**1.Scala API类型信息**

	import org.apache.flink.api.scala._

**2.Java API类型信息**

由于Java的泛型会出现类型擦除的问题，Flink通过Java反射机制尽可能重构类型信息。同时类型推断在当输出类型依赖于输入参数类型时相对比较容易做到，但是如果函数的输出类型不依赖于输入参数的类型信息，这个时候就需要借助于类型提示（Ctype Himts）来告诉系统函数中传入的参数类型信息和输出参数信息。

**3.自定义TypeInformation**

除了使用已有的TypeInformation所定义的数据格式类型之外，用户也可以自定义实现TypeInformation，来满足的不同的数据类型定义需求。

* 通过@TypeInfo注解创建数据类型，定义CustomTuple数据类型。
* 然后定义CustomTypeInfoFactory类继承于TypeInfoFactory，参数类型指定CustomTuple。
	@TypeInfo

### 3.5 本章小结 ###

## 第4章 DataStream API介绍与使用 ##

DataStream API开发流式应用：其中包括基本的编程模型、常用操作、时间概念、窗口计算、作业链等。

### 4.1 DataStream编程模型 ###

在Flink整个系统架构中，对流计算的支持是其最重要的功能之一，Flink基于Google提出的DataFlow模型，实现了支持原生数据流处理的计算引擎。Flink中定义了DataStream API让用户灵活且高效地编写Flink流式应用。

DataStream API主要可为分为三个部分：

1. DataSource模块
2. Transformation模块
3. DataSink模块

#### 4.1.1 DataSources数据输入 ####

DataSources模块定义了DataStream API中的数据输入操作，Flink将数据源主要分为的内置数据源和第三方数据源两种类型。

* 内置数据源包含文件、Socket网络端口以及集合类型数据，其不需要引入其他依赖库，且在Flink系统内部已经实现，用户可以直接调用相关方法使用。
* 第三方数据源定义了Flink和外部系统数据交互的逻辑，包括数据的读写接口。在Flink中定义了非常丰富的第三方数据源连接器（Connector）。

**1. 内置数据源**

（1）文件数据源


### 4.2 时间概念与Watermark ###

#### 4.2.1 时间概念类型 ####

对于流式数据处理，最大的特点是数据上具有时间的属性特征，Flink跟布局时间产生的位置不同，将时间区分为三种时间概念，分别为事件生成时间（Event Time）、事件接入时间（Ingestion Time）和事件处理时间（Processing Time）。

Source Operator -> Windows Operator -> DataSink Operator

**1. 事件时间（Event Time）**

事件时间（Event Time）是每个独立时间在产生它的设备上发生的时间，这个时间通常在事件进入Flink之前就已经嵌入到事件中，时间顺序取决于事件产生的地方，和下游数据处理系统的时间无关。

**2. 接入时间（Ingestion）**

接入时间（Ingestion Time）是数据进入Flink系统的时间，Ingestion Time依赖于Source Operator所在主机的系统时钟。

**3. 处理时间（Processing TIme）**

处理时间（Processing Time）是指数据在操作算子计算过程中获取到的所在主机时间。当用户选择使用Processing Time时，所有和时间相关的计算算子。

**4. 时间概念指定**

在Flink中默认情况下使用是Process Time时间概念，如果用户选择使用 Event Time或者Ingestion Time概念，则需要在创建StreamExecutionEnvironment中调用setStream-TimeCharacteristic()方法设定系统的时间概念。

#### 4.2.2 EventTime和Watermark ####

（1）顺序事件中的Watermarks
**（2）乱序事件中的Watermarks**

现实情况下数据元素往往并不是按照其产生顺序接入到Flink系统中进行处理，而频繁出现乱序或迟到的情况，这种情况就需要使用Watermarks来应对。

（3）并行数据流中的Watermarks

### 4.3 Windows窗口计算 ###

#### 4.3.1 Windows Assigner ####

**1. Keyed和Non-Keyed窗口**

在运用窗口计算时，Flink根据上游数据集是否为KeyedStream类型（讲数据集按照Key分区），对应的Windows Assigned也会有所不同。

**2. Windows Assigner**

Flink支持两种类型的窗口，一种是基于时间的窗口，窗口基于起始时间戳（闭区间）和终止时间戳（开区间）来决定窗口的大小，数据根据时间戳被分配到不同的窗口中完成计算。

（1）滚动窗口

滚动窗口是根据固定时间或大小进行切分，且窗口和窗口之间的元素互不重叠。这种类型的窗口

### 4.6 小结 ###

DreamStream API编程模型以及开发环境准备，并通过简单的实例介绍如何使用DataStream API编写简单的流式应用，同时介绍了DataStream API中DataSource数据源支持，包括基本数据源和外部第三方数据源，以及DataStream API提供的常规的数据处理方法，包括针对单个Stream和多个Stream的转换方法，然后介绍利用DataSink组件将DataStream的数据输出到外部系统中。

## 第5章 Flink状态管理和容错 ##

## 第6章 DataSet API介绍与使用 ##

### 6.1 DataSet API ###

和DataStream API一样，Flink提出DataSet API用于处理批量数据。Flink将接入数据转换成DataSet数据集，并行分布在集群的每个节点上，基于DataSet数据集完成各种转换操作（map，filter等）

#### 开发环境配置 ####

在使用Flink DataSet API进行批量应用程序开发之前，需要在工程中引入Flink批量计算相关依赖库，可以在项目工程中的pom.xml文件中添加flink-java对应的Dependency配置，引入DataSet API所需要的依赖库


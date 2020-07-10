# Flink入门与实战 #

## 第1章 Flink概述 ##

### 1.1 Flink原理分析 ###

Flink是一个开源的流处理框架，特点如下：

* 分布式：Flink程序可以运行在多台机器上。
* 高性能：处理性能比较高。
* 高可用：由于Flink本身是稳定的，因此它支持高可用性。
* 准确：Flink可以保证数据处理的准确定。

 * 流式优先：Flink可以连续处理流式数据。
 * 容错：Flink提供有状态的计算，可以记录数据的处理状态，当数据处理失败的时候，能够无缝地从失败中恢复，并保持Exactly-once。
 * 可伸缩：Flink中的一个集群支持上千个节点。
 * 性能：Flink支持高吞吐、低延迟。

### 1.2 Flink架构分析 ###

Flink架构可以分为4层，包括Deploy层、Core层、API层和Library层。

* Deploy层：该层主要涉及Flink的部署模式，Flink支持多种部署模式——本地、集群（Standalone/YARN）和云服务器（GCE/EC2）。
* Core层：该层提供了支持Flink计算的全部核心实现，为API层提供基础服务。
* API层：该层主要实现了面向无界Stream的流处理和面向Batch的批处理API，其中流处理对应DataStream API，批处理对应DataSet API。
* Library层：该层也被称为Flink应用框架层，根据API层的划分，在API层之上构建的满足特定应用的实现计算框架，也分别对应于面向流处理和面向批处理两类。面向流处理支持CEP（复杂事件处理）、基于SQL-like的操作（基于Table的关系操作）；面向批处理支持FlinkML（机器学习库）、Gelly（图处理）、Table 操作。

### 1.3 Flink基本组件 ###

在Hadoop中实现一个MapReduce需要两个阶段——Map和Reduce，而在Storm中实现一个Topology则需要Spout和Bolt组件。

Flink中提供了3个组件，包括DataSource、Transformation和DataSink。

* DataSource：表示数据源组件，主要用来接收数据，目前官网提供了readTextFile、socketTextStream、fromCollection以及一些第三方的Source。
* Transformation：表示算子，主要用来对数据进行处理，比如Map、FlatMap、Filter、Reduce、Aggregation等。
* DataSink：表示输出组件，主要用来把计算的结果输出到其他存储介质中，比如writeAsText以及Kafka、Redis、Elasticsearch等第三方Sink组件。

Flink Job = DataSource+Transformation+DataSink

### 1.4 Flink流处理（Streaming）与批处理（Batch） ###

### 1.5 Flink典型应用场景分析 ###

Flink主要应用于流式数据分析场景，涉及领域如下：

* 实时ETL
* 实时报表
* 监控预警
* 在线系统

### 1.6 流式计算框架对比 ###



## 第2章 Flink快速入门 ##

### 2.1 Flink开发环境分析 ###

#### 2.1.1 开发工具推荐 ####

1. IDE
	1. IntellijIDEA
2. 开发语言
	1. Scala
3. 管理
	1. Maven管理
	2. 
#### 2.1.2 Flink程序依赖配置 ####

### 2.2 Flink程序开发步骤 ###

1. 获得一个执行环境。
2. 加载/创建初始化数据。
3. 指定操作数据的Transaction算子。
4. 指定计算好的数据的存放位置。
5. 调用execute()触发执行程序。

注意：Flink程序是延迟计算的，只有最后调用execute()方法的时候才会真正触发执行程序。

后面的Java代码全部存放在src/main/Java目录下，Scala代码全部存放在src/main/Scala目录下，流计算相关的代码存放在对应的streaming目录下，批处理相关的代码则存放在对应的batch目录下。
 
### 2.3 Flink流处理（Streaming）案例开发 ###

通过Socket手工实时产生一些单词，使用Flink实时接收数据，对指定时间窗口内（如2s）的数据进行聚合统计，并且把时间窗口内计算的结果打印出来。

#### 2.3.1 Java代码开发 ####

1. 添加Java代码对应的Maven依赖
2. 创建WordWithCount类，方便统计每个单词出现的总次数

需求：实现每隔1s对最近2s内的数据进行汇总计算。
分析：通过Socket模拟产生单词，使用Flink程序对数据进行汇总计算。

## 第3章 Flink的安装和部署 ##

Flink的安装和部署主要分为本地模式和集群模式，其中本地模式只需直接解压就可以使用，不以修改任何参数，一般在做一些简单测试的时候使用。集群模式包含Standalone、Flink on Yarn等模式，适合在生产环境下面使用，且需要修改对应的配置参数。

### 3.1 Flink本地模式 ###

* Linux环境
* JDK1.8
* Flink依赖Hadoop

### 3.2 Flink集群模式 ###

Flink提供了多种集群模式：

* Standalone。
* Flink on Yarn。
* Mesos。
* Docker。
* Kubernetes。
* AWS。
* Google Compute Engine。
* MapR。

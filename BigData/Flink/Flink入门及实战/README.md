# Flink入门和实战 #

#### Flink基本原理即入门场景 ####

* 分布式、高性能、高可用、准确的
* 事实流（stream）处理和批（batch）处理

#### Flink基本组件介绍 ####

DataSource->Transformations->DataSink

* Storm只支持流处理
* MapReduce、Spark只支持批处理，Spark Streaming采用了一种micro-batch的架构，即把输入的数据流切分成细粒度的batch，并为每一个batch数据提交一个批处理的Spark任务。